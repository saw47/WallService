data class Comment(
    private val canPost: Boolean,
    private val showReplyButton: Boolean,
    private val groupsCanPost: Boolean,
    private val isDon: Boolean,
    val id: Int,
    val fromId: Int,
    private val date: Int,
    val postId: Int // в api этого нет, добавил от себя, т.к передачу в метод createComment второго параметра забраковали.
                    //если есть какой-то третий способ передать несуществующий в объекте параметр в метод внутри этого метода - поделитесь пжлст
) {
    var text: String? = null
    private val replyToUser: Int? = null

    val attachments = emptyArray<AbleAttachToComment>()
    private val parentsStack = emptyArray<Comment>()

    private val donut: Donut = Donut(isDon)
    private val thread = Thread(canPost, showReplyButton, groupsCanPost)

    class Thread(
        private val canPost : Boolean,
        private val showReplyButton: Boolean,
        private val groupsCanPost : Boolean
    ) {
        private var count: Int = 0
        private var items = emptyArray<Comment>()
    }

    class Donut(private val isDon: Boolean) {
        private val placeholder: String = "Some text for users, who isn't have Donut subscribe "

    }


}