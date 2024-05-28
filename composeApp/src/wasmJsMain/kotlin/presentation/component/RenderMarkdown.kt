package presentation.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.parser.MarkdownParser
import presentation.ui.body2
import presentation.ui.title3

@Composable
fun RenderMarkdown(
    src: String,
    modifier: Modifier = Modifier,
) {
    val defaultSpanStyle = SpanStyle(
        fontFamily = body2.fontFamily,
        fontSize = body2.fontSize,
        fontWeight = body2.fontWeight,
    )
    val parsedTree = MarkdownParser(CommonMarkFlavourDescriptor()).buildMarkdownTreeFromString(src)
    val annotatedString = buildAnnotatedString {
        parseAst(defaultSpanStyle, src, parsedTree)
    }

    Text(modifier = modifier, text = annotatedString)
}

private fun AnnotatedString.Builder.parseAst(
    spanStyle: SpanStyle,
    src: String,
    ast: ASTNode,
) {
    when (val type = ast.type.name) {
        "MARKDOWN_FILE", "UNORDERED_LIST", "PARAGRAPH", "LIST_ITEM" -> {
            ast.children.forEach {
                parseAst(spanStyle, src, it)
            }
        }

        "TEXT" -> {
            withStyle(style = spanStyle) {
                append(src.substring(ast.startOffset, ast.endOffset))
            }
        }

        "ATX_CONTENT" -> {
            withStyle(
                style = spanStyle.copy(
                    fontSize = title3.fontSize,
                    fontWeight = title3.fontWeight
                )
            ) {
                append(src.substring(ast.startOffset, ast.endOffset))
            }
        }

        "LIST_BULLET" -> {
            withStyle(style = spanStyle) {
                append("·")
            }
        }

        "WHITE_SPACE" -> {
            withStyle(style = spanStyle) {
                append(" ")
            }
        }

        "STRONG" -> {
            ast.children.forEach {
                parseAst(spanStyle.copy(fontWeight = FontWeight.SemiBold), src, it)
            }
        }

        ":", "(", ")" -> {
            withStyle(style = spanStyle) {
                append(type)
            }
        }

        "EOL" -> append("\n")

        else -> {
            ast.children.forEach {
                parseAst(spanStyle, src, it)
            }
        }
    }
}