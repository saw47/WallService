internal data class Post(

    internal val ownerId: Int,
    internal var fromId: Int,
    internal var createdBy: Int,
    internal var text: String,
    internal var replyOwnerId: Int,
    internal var replyPostId: Int,
    internal var friendsOnly: Boolean,
    internal var postType: String, //Type of the post, can be: post, copy, reply, postpone, suggest.
    internal var signerId: Int,
    internal var canPin: Boolean,
    internal var canDelete: Boolean,
    internal var canEdit: Boolean,
    internal var isPinned: Boolean,
    internal var markedAsAds: Boolean,
    internal var isFavorite: Boolean

) {
    internal var id: Int = -1
    private val date: Int = (System.currentTimeMillis() * 1000).toInt()
    internal var copyHistory = emptyArray<Post>()

    internal var comments = Comments()
    internal var likes = Likes()
    internal var reposts = Reposts()

    internal inner class Comments() {
        internal var count: UInt = 0u
        internal var canPost: Boolean = true
        internal var groupsCanPost: Boolean = true
    }

    internal inner class Likes() {
        internal var count: UInt = 0u
        internal var userLikes: Boolean = true
        internal var canLike: Boolean = true
        internal var canPublish: Boolean = true
    }

    internal inner class Reposts() {
        internal var count: UInt = 0u
        internal var userReposted: Boolean = true
    }
}

internal object WallService {
    internal var posts = emptyArray<Post>()

    internal fun add(post: Post): Post {
        post.id = if (posts.isNotEmpty()) {
            (posts.lastIndex + 1)
        } else 0
        posts += post
        return post
    }

    internal fun update(post: Post): Boolean {
        if (post.id in posts.indices && post.id == posts[post.id].id) {
            posts[post.id].fromId = post.fromId
            posts[post.id].createdBy = post.createdBy
            posts[post.id].text = post.text
            posts[post.id].replyOwnerId = post.replyOwnerId
            posts[post.id].replyPostId = post.replyPostId
            posts[post.id].friendsOnly = post.friendsOnly
            posts[post.id].postType = post.postType
            posts[post.id].signerId = post.signerId
            posts[post.id].canPin = post.canPin
            posts[post.id].canDelete = post.canDelete
            posts[post.id].canEdit = post.canEdit
            posts[post.id].isPinned = post.isPinned
            posts[post.id].markedAsAds = post.markedAsAds
            posts[post.id].isFavorite = post.isFavorite

            posts[post.id].comments.count = post.comments.count
            posts[post.id].comments.canPost = post.comments.canPost
            posts[post.id].comments.groupsCanPost = post.comments.groupsCanPost

            posts[post.id].likes.count = post.likes.count
            posts[post.id].likes.userLikes = post.likes.userLikes
            posts[post.id].likes.canLike = post.likes.canLike
            posts[post.id].likes.canPublish = post.likes.canPublish

            posts[post.id].reposts.count = post.reposts.count
            posts[post.id].reposts.userReposted = post.reposts.userReposted
            return true
        } else {
            return false
        }
    }
}

