import org.junit.Test
import java.time.Instant
import org.junit.Assert.*

class AddCommentTest {
    @Test(expected = PostNotFoundException::class)
    fun shouldPostNotFoundException() {
        val testPost = Post(
            fromId = 205445,
            createdBy = 556933,
            text = "Test text post bla",
            replyOwnerId = 225666,
            replyPostId = 558455,
            friendsOnly = false,
            postType = "post",
            signerId = 447885,
            canPin = false,
            canDelete = false,
            canEdit = true,
            isPinned = false,
            markedAsAds = false,
            isFavorite = true,
            commentsCanPost = true,
            commentsGroupsCanPost = true,
            likesUserLikes = true,
            likesCanLike = true,
            likesCanPublish = true,
            repostsUserReposted = true
        )

        val oneMoreTestPost = Post(
            fromId = 223332,
            createdBy = 456975,
            text = "it is another post!",
            replyOwnerId = 334443,
            replyPostId = 332223,
            friendsOnly = false,
            postType = "post",
            signerId = 323232,
            canPin = false,
            canDelete = false,
            canEdit = true,
            isPinned = false,
            markedAsAds = false,
            isFavorite = true,
            commentsCanPost = true,
            commentsGroupsCanPost = true,
            likesUserLikes = true,
            likesCanLike = true,
            likesCanPublish = true,
            repostsUserReposted = true
        )

        WallService.add(testPost)
        WallService.add(oneMoreTestPost)

        val commentToTestPost: Comment = Comment(
            canPost = true,
            showReplyButton = true,
            groupsCanPost = true,
            isDon = true,
            id = 554447,
            fromId = 558998,
            date = Instant.now().epochSecond.toInt(),
            postId = -5
        )

        commentToTestPost.text = "some text to test post"
        WallService.createComment(commentToTestPost)
    }

    @Test
    fun successAddCommentToPost() {
        WallService.clearPostsArrayToTests()
        WallService.clearCommentsArrayToTests()

        val testPost = Post(
            fromId = 205445,
            createdBy = 556933,
            text = "Test text post bla",
            replyOwnerId = 225666,
            replyPostId = 558455,
            friendsOnly = false,
            postType = "post",
            signerId = 447885,
            canPin = false,
            canDelete = false,
            canEdit = true,
            isPinned = false,
            markedAsAds = false,
            isFavorite = true,
            commentsCanPost = true,
            commentsGroupsCanPost = true,
            likesUserLikes = true,
            likesCanLike = true,
            likesCanPublish = true,
            repostsUserReposted = true
        )

        val oneMoreTestPost = Post(
            fromId = 223332,
            createdBy = 456975,
            text = "it is another post!",
            replyOwnerId = 334443,
            replyPostId = 332223,
            friendsOnly = false,
            postType = "post",
            signerId = 323232,
            canPin = false,
            canDelete = false,
            canEdit = true,
            isPinned = false,
            markedAsAds = false,
            isFavorite = true,
            commentsCanPost = true,
            commentsGroupsCanPost = true,
            likesUserLikes = true,
            likesCanLike = true,
            likesCanPublish = true,
            repostsUserReposted = true
        )

        val commentToTestPost = Comment(
            canPost = true,
            showReplyButton = true,
            groupsCanPost = true,
            isDon = true,
            id = 554447,
            fromId = 558998,
            date = Instant.now().epochSecond.toInt(),
            postId = 0
        )
        commentToTestPost.text = "some text to test post"

        WallService.add(testPost)
        WallService.add(oneMoreTestPost)
        WallService.createComment(commentToTestPost)
        var counter: UInt? = null
        for(post in WallService.copyPostsArrayToTests()) {
            if(post == testPost) {
                counter = post.comment.count
            }
        }
        assertEquals(counter, 1U)

        var checkCommentId: Boolean = false
        val copyCommentToTest = WallService.copyCommentsArrayToTests()
        for (comment in copyCommentToTest) {
            if (comment.id == commentToTestPost.id) {
                checkCommentId = true
            }
        }
        assertEquals(true, checkCommentId)
    }
}
