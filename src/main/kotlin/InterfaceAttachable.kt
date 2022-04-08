interface Attachments {
    val type: String
}

interface InternalMediaToCommentAttachable {
    val mediaId: Int
}

interface AbleAttachToComment {
    val type: String
    fun returnAttachableMediaId(): String {
        return "attachment id aren't detected"
    }
}