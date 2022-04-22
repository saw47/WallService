import WallService.add
import WallService.copyPostsArrayToTests
import org.junit.Test
import org.junit.Assert.*
import kotlin.random.Random

class WallServiceTest {

    @Test
    fun add_firstPostInTheWall() {
        val post = Post(
            fromId = Random.nextInt(1000000),
            createdBy = Random.nextInt(1000000),
            text = "some text in Post",
            replyOwnerId = Random.nextInt(1000000),
            replyPostId = Random.nextInt(1000000),
            friendsOnly = Random.nextBoolean(),
            postType = "post",
            signerId = Random.nextInt(1000000),
            canPin = Random.nextBoolean(),
            canDelete = Random.nextBoolean(),
            canEdit = Random.nextBoolean(),
            isPinned = Random.nextBoolean(),
            markedAsAds = Random.nextBoolean(),
            isFavorite = Random.nextBoolean(),
            commentsCanPost = Random.nextBoolean(),
            commentsGroupsCanPost = Random.nextBoolean(),
            likesUserLikes = Random.nextBoolean(),
            likesCanLike = Random.nextBoolean(),
            likesCanPublish = Random.nextBoolean(),
            repostsUserReposted = Random.nextBoolean()
        )

        WallService.clearPostsArrayToTests()

        val addedPost = add(post)
        val addedPostId = addedPost.id

        assertEquals(0, addedPostId)
    }

    @Test
    fun add_noFirstPostInTheWall() {
        val somePost = Post(
            fromId = Random.nextInt(1000000),
            createdBy = Random.nextInt(1000000),
            text = "some text in somePost",
            replyOwnerId = Random.nextInt(1000000),
            replyPostId = Random.nextInt(1000000),
            friendsOnly = Random.nextBoolean(),
            postType = "post",
            signerId = Random.nextInt(1000000),
            canPin = Random.nextBoolean(),
            canDelete = Random.nextBoolean(),
            canEdit = Random.nextBoolean(),
            isPinned = Random.nextBoolean(),
            markedAsAds = Random.nextBoolean(),
            isFavorite = Random.nextBoolean(),
            commentsCanPost = Random.nextBoolean(),
            commentsGroupsCanPost = Random.nextBoolean(),
            likesUserLikes = Random.nextBoolean(),
            likesCanLike = Random.nextBoolean(),
            likesCanPublish = Random.nextBoolean(),
            repostsUserReposted = Random.nextBoolean()
        )
        val someOnePost = Post(
            fromId = Random.nextInt(1000000),
            createdBy = Random.nextInt(1000000),
            text = "some text in someOnePost",
            replyOwnerId = Random.nextInt(1000000),
            replyPostId = Random.nextInt(1000000),
            friendsOnly = Random.nextBoolean(),
            postType = "post",
            signerId = Random.nextInt(1000000),
            canPin = Random.nextBoolean(),
            canDelete = Random.nextBoolean(),
            canEdit = Random.nextBoolean(),
            isPinned = Random.nextBoolean(),
            markedAsAds = Random.nextBoolean(),
            isFavorite = Random.nextBoolean(),
            commentsCanPost = Random.nextBoolean(),
            commentsGroupsCanPost = Random.nextBoolean(),
            likesUserLikes = Random.nextBoolean(),
            likesCanLike = Random.nextBoolean(),
            likesCanPublish = Random.nextBoolean(),
            repostsUserReposted = Random.nextBoolean()
        )

        add(somePost)
        add(someOnePost)

        val lastAddedPostId = WallService.copyPostsArrayToTests()[(WallService.copyPostsArrayToTests().lastIndex)].id
        val lastArrayIndex = WallService.copyPostsArrayToTests().lastIndex
        assertEquals(lastArrayIndex, lastAddedPostId)
    }

    @Test
    fun update_success() {
        val post = Post(
            fromId = Random.nextInt(1000000),
            createdBy = Random.nextInt(1000000),
            text = "some text in Post",
            replyOwnerId = Random.nextInt(1000000),
            replyPostId = Random.nextInt(1000000),
            friendsOnly = Random.nextBoolean(),
            postType = "post",
            signerId = Random.nextInt(1000000),
            canPin = Random.nextBoolean(),
            canDelete = Random.nextBoolean(),
            canEdit = Random.nextBoolean(),
            isPinned = Random.nextBoolean(),
            markedAsAds = Random.nextBoolean(),
            isFavorite = Random.nextBoolean(),
            commentsCanPost = Random.nextBoolean(),
            commentsGroupsCanPost = Random.nextBoolean(),
            likesUserLikes = Random.nextBoolean(),
            likesCanLike = Random.nextBoolean(),
            likesCanPublish = Random.nextBoolean(),
            repostsUserReposted = Random.nextBoolean()
        )
        val updatePost = Post(
            fromId = Random.nextInt(1000000),
            createdBy = Random.nextInt(1000000),
            text = "some text in UPDATE post",
            replyOwnerId = Random.nextInt(1000000),
            replyPostId = Random.nextInt(1000000),
            friendsOnly = Random.nextBoolean(),
            postType = "post",
            signerId = Random.nextInt(1000000),
            canPin = Random.nextBoolean(),
            canDelete = Random.nextBoolean(),
            canEdit = Random.nextBoolean(),
            isPinned = Random.nextBoolean(),
            markedAsAds = Random.nextBoolean(),
            isFavorite = Random.nextBoolean(),
            commentsCanPost = Random.nextBoolean(),
            commentsGroupsCanPost = Random.nextBoolean(),
            likesUserLikes = Random.nextBoolean(),
            likesCanLike = Random.nextBoolean(),
            likesCanPublish = Random.nextBoolean(),
            repostsUserReposted = Random.nextBoolean()
        )

        val addedPost = add(post)
        updatePost.id = addedPost.id

        updatePost.likes.count = 100u
        updatePost.reposts.userReposted = false
        updatePost.comment.count = 100u

        WallService.update(updatePost)

        val result = WallService.update(updatePost)

        assertEquals(updatePost.likes.count, copyPostsArrayToTests()[updatePost.id].likes.count)
        assertEquals(updatePost.reposts.userReposted, copyPostsArrayToTests()[updatePost.id].reposts.userReposted)
        assertEquals(updatePost.comment.count, copyPostsArrayToTests()[updatePost.id].comment.count)
        assertEquals(true, result)

    }

    @Test
    fun update_failed() {
        val post = Post(
            fromId = Random.nextInt(1000000),
            createdBy = Random.nextInt(1000000),
            text = "OLD POST",
            replyOwnerId = Random.nextInt(1000000),
            replyPostId = Random.nextInt(1000000),
            friendsOnly = Random.nextBoolean(),
            postType = "post",
            signerId = Random.nextInt(1000000),
            canPin = Random.nextBoolean(),
            canDelete = Random.nextBoolean(),
            canEdit = Random.nextBoolean(),
            isPinned = Random.nextBoolean(),
            markedAsAds = Random.nextBoolean(),
            isFavorite = Random.nextBoolean(),
            commentsCanPost = Random.nextBoolean(),
            commentsGroupsCanPost = Random.nextBoolean(),
            likesUserLikes = Random.nextBoolean(),
            likesCanLike = Random.nextBoolean(),
            likesCanPublish = Random.nextBoolean(),
            repostsUserReposted = Random.nextBoolean()
        )
        val nextPost = Post(
            fromId = Random.nextInt(1000000),
            createdBy = Random.nextInt(1000000),
            text = "OLD TEXT",
            replyOwnerId = Random.nextInt(1000000),
            replyPostId = Random.nextInt(1000000),
            friendsOnly = Random.nextBoolean(),
            postType = "post",
            signerId = Random.nextInt(1000000),
            canPin = Random.nextBoolean(),
            canDelete = Random.nextBoolean(),
            canEdit = Random.nextBoolean(),
            isPinned = Random.nextBoolean(),
            markedAsAds = Random.nextBoolean(),
            isFavorite = Random.nextBoolean(),
            commentsCanPost = Random.nextBoolean(),
            commentsGroupsCanPost = Random.nextBoolean(),
            likesUserLikes = Random.nextBoolean(),
            likesCanLike = Random.nextBoolean(),
            likesCanPublish = Random.nextBoolean(),
            repostsUserReposted = Random.nextBoolean()
        )
        val updatePost = Post(
            fromId = Random.nextInt(1000000),
            createdBy = Random.nextInt(1000000),
            text = "UPDATE TEXT",
            replyOwnerId = Random.nextInt(1000000),
            replyPostId = Random.nextInt(1000000),
            friendsOnly = Random.nextBoolean(),
            postType = "post",
            signerId = Random.nextInt(1000000),
            canPin = Random.nextBoolean(),
            canDelete = Random.nextBoolean(),
            canEdit = Random.nextBoolean(),
            isPinned = Random.nextBoolean(),
            markedAsAds = Random.nextBoolean(),
            isFavorite = Random.nextBoolean(),
            commentsCanPost = Random.nextBoolean(),
            commentsGroupsCanPost = Random.nextBoolean(),
            likesUserLikes = Random.nextBoolean(),
            likesCanLike = Random.nextBoolean(),
            likesCanPublish = Random.nextBoolean(),
            repostsUserReposted = Random.nextBoolean()
        )

        add(post)
        add(nextPost)

        updatePost.id = -5
        val result = WallService.update(updatePost)
        assertEquals(false, result)
    }
}