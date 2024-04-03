package data

import presentation.model.ContentModel

class ProjectRepository {

    /**
     * projects는 .gitignore에 추가되어 있으므로 반환 타입에 맞게 커스텀해서 넣어주세요.
     *
     * @return 내가 진행한 프로젝트 목록을 반환한다.
     */
    fun getProjects(): List<ContentModel> = projects
}