package presentation.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
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
    val parsedTree = MarkdownParser(CommonMarkFlavourDescriptor()).buildMarkdownTreeFromString(src)
    val annotatedString = buildAnnotatedString {
        parseAst(src, parsedTree)
    }

    Text(modifier = modifier, text = annotatedString)
}

private fun AnnotatedString.Builder.parseAst(
    src: String,
    ast: ASTNode,
) {
    val spanStyle = SpanStyle(
        fontFamily = body2.fontFamily,
        fontSize = if (ast.parent?.type?.name?.startsWith("ATX") == true) title3.fontSize else body2.fontSize,
        fontWeight = if (ast.parent?.type?.name?.startsWith("ATX") == true) title3.fontWeight else body2.fontWeight,
    )

    when (ast.type.name) {
        "MARKDOWN_FILE", "UNORDERED_LIST", "PARAGRAPH", "LIST_ITEM" -> {
            ast.children.forEach {
                parseAst(src, it)
            }
        }

        "TEXT", "ATX_CONTENT" -> {
            withStyle(style = spanStyle) {
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

        "EOL" -> append("\n")

        else -> {
            ast.children.forEach {
                parseAst(src, it)
            }
        }
    }
}