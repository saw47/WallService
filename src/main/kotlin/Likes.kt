internal data class Likes(
    var count: UInt = 0u,
    val userLikes: Boolean,
    val canLike: Boolean,
    val canPublish: Boolean
)