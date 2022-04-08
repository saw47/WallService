internal data class Post(
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

    var comments = Comments(
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

internal object WallService {

    private var posts = emptyArray<Post>()

    fun copyPostsArrayToTests(): Array<Post> {
        var copyPosts = emptyArray<Post>()
        copyPosts = posts
        return copyPosts
    }

    fun clearPostsArrayToTests(){
        posts = emptyArray()
    }

    internal fun add(post: Post): Post {
        val addedPost = post.copy()
        addedPost.id = if (posts.isNotEmpty()) (posts.lastIndex + 1) else 0
        posts += addedPost
        return addedPost
    }

    internal fun update(post: Post): Boolean {
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
                commentsCanPost = post.comments.canPost,
                commentsGroupsCanPost = post.comments.groupsCanPost,
                likesUserLikes = post.likes.userLikes,
                likesCanLike = post.likes.canLike,
                likesCanPublish = post.likes.canPublish,
                repostsUserReposted = post.reposts.userReposted
            )

            updatePost.comments = post.comments.copy(
                count = post.comments.count,
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

