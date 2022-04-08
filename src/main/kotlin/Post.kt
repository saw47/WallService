data class Post(
    val fromId: Int,
    val createdBy: Int,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val postType: String, //Type of the post, can be: post, copy, reply, postpone, suggest.
    val signerId: Int,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val commentsCanPost: Boolean,
    val commentsGroupsCanPost: Boolean,
    val likesUserLikes: Boolean,
    val likesCanLike: Boolean,
    val likesCanPublish: Boolean,
    val repostsUserReposted: Boolean
) {
    var id: Int = -1
    var date: Int = (System.currentTimeMillis() * 1000).toInt()

    val copyHistory = emptyArray<Post>()

    var comment = Comments(
        canPost = commentsCanPost,
        groupsCanPost = commentsGroupsCanPost
    )

    var likes = Likes(
        userLikes = likesUserLikes,
        canLike = likesCanLike,
        canPublish = likesCanPublish
    )

    var reposts = Reposts(
        userReposted = repostsUserReposted
    )

    val geo: Geo = Geo()
    val postSource = PostSource()
    var attachments = emptyArray<Attachments?>()


}

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    fun clearCommentsArrayToTests() {
        if (comments.isNotEmpty()) {
            comments = emptyArray()
        }
    }
    fun clearPostsArrayToTests() {
        if (posts.isNotEmpty()) {
            posts = emptyArray()
        }
    }
    fun copyPostsArrayToTests(): Array<Post> {
        return posts
    }
    fun copyCommentsArrayToTests(): Array<Comment> {
        return comments
    }


    fun createComment(comment: Comment) {
        val ownerId: Int = comment.fromId
        val postId: Int = comment.postId
        var fromGroup: UInt = 0u
        var message: String? = null
        val stickerId: UInt
        val guid: Int
        val replyToComment: Int
        var attachments: String = ""
        var postInPosts = -1

        for (post in posts) {
            if (post.id == postId) {
                if (comment.attachments.isEmpty()) {
                    if (comment.text == null) {
                        error("aren't content Exception")
                    } else {
                        message = comment.text
                    }
                } else {
                    val comma: String = ",";
                    for (i: Int in 0 until comment.attachments.size) {
                        val internalAttachments: String =
                            comment.attachments[i].type + comment.fromId + "_" + comment.attachments[i].returnAttachableMediaId()
                        attachments += internalAttachments
                        if (i < (comment.attachments.size - 1)) {
                            attachments += comma
                        }
                    }
                }
                postInPosts = 1
                post.comment.count += 1u
                comments += comment
            }
        }

        if (postInPosts == -1) throw PostNotFoundException("Post not found")
    }

    fun add(post: Post): Post {
        val addedPost = post.copy()
        addedPost.id = if (posts.isNotEmpty()) (posts.lastIndex + 1) else 0
        posts += addedPost
        return addedPost
    }

    fun update(post: Post): Boolean {
        if (post.id in posts.indices && post.id == posts[post.id].id) {
            val updatePost: Post = post.copy(
                fromId = post.fromId,
                createdBy = post.createdBy,
                text = post.text,
                replyOwnerId = post.replyOwnerId,
                replyPostId = post.replyPostId,
                friendsOnly = post.friendsOnly,
                postType = post.postType,
                signerId = post.signerId,
                canPin = post.canPin,
                canDelete = post.canDelete,
                canEdit = post.canEdit,
                isPinned = post.isPinned,
                markedAsAds = post.markedAsAds,
                isFavorite = post.isFavorite,
                commentsCanPost = post.comment.canPost,
                commentsGroupsCanPost = post.comment.groupsCanPost,
                likesUserLikes = post.likes.userLikes,
                likesCanLike = post.likes.canLike,
                likesCanPublish = post.likes.canPublish,
                repostsUserReposted = post.reposts.userReposted
            )

            updatePost.comment = post.comment.copy(
                count = post.comment.count,
                canPost = updatePost.commentsCanPost,
                groupsCanPost = updatePost.commentsGroupsCanPost
            )

            updatePost.likes = post.likes.copy(
                count = post.likes.count,
                userLikes = updatePost.likesUserLikes,
                canLike = updatePost.likesCanLike,
                canPublish = updatePost.likesCanPublish
            )

            updatePost.reposts = post.reposts.copy(
                count = updatePost.reposts.count,
                userReposted = updatePost.repostsUserReposted
            )

            updatePost.id = post.id
            updatePost.date = post.date
            posts[post.id] = updatePost
            return true
        } else {
            return false
        }
    }
}

