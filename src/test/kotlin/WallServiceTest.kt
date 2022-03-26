import WallService.add
import WallService.posts
import WallService.update
import org.junit.Test

import org.junit.Assert.*
import kotlin.random.Random

class WallServiceTest {

    @Test
    fun `getPosts$WallPost`() {
    }

    @Test
    fun `setPosts$WallPost`() {
    }

    @Test
    fun add_firstPostInTheWall() {
        val post = Post(
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            "some text",
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextBoolean(),
            "post", //Type of the post, can be: post, copy, reply, postpone, suggest.
            Random.nextInt(1000000),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean()
        )
        posts = emptyArray()
        val addedPostId = add(post).id
        assertEquals(0,addedPostId)
        posts = emptyArray()
    }

    @Test
    fun add_noFirstPostInTheWall() {
        val post = Post(
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            "some text",
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextBoolean(),
            "post", //Type of the post, can be: post, copy, reply, postpone, suggest.
            Random.nextInt(1000000),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean()
        )
        val somePost = Post(
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            "some text",
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextBoolean(),
            "post", //Type of the post, can be: post, copy, reply, postpone, suggest.
            Random.nextInt(1000000),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean()
        )
        val someOnePost = Post(
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            "some text",
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextBoolean(),
            "post", //Type of the post, can be: post, copy, reply, postpone, suggest.
            Random.nextInt(1000000),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean()
        )

        add(post)
        add(somePost)
        add(someOnePost)

        val lastAddedPostId = posts[(posts.size - 1)].id
        val lastArrayIndex = posts.size - 1
        assertEquals(lastArrayIndex,lastAddedPostId)
        posts = emptyArray()
    }

    @Test
    fun update_success() {
        val post = Post(
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            "OLD TEXT",
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextBoolean(),
            "post", //Type of the post, can be: post, copy, reply, postpone, suggest.
            Random.nextInt(1000000),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean()
        )
        val updatePost = Post(
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            "UPDATE TEXT",
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextBoolean(),
            "post", //Type of the post, can be: post, copy, reply, postpone, suggest.
            Random.nextInt(1000000),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean()
        )
        add(post)
        updatePost.id = post.id
        updatePost.likes.count = 100u
        updatePost.reposts.userReposted = false
        updatePost.comments.count = 100u
        val result = update(updatePost)

        assertEquals(updatePost.likes.count, post.likes.count)
        assertEquals(updatePost.reposts.userReposted, post.reposts.userReposted)
        assertEquals(updatePost.comments.count, post.comments.count)
        assertEquals(true, result)

        posts = emptyArray()
    }

    @Test
    fun update_failed() {
        val post = Post(
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            "OLD TEXT",
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextBoolean(),
            "post", //Type of the post, can be: post, copy, reply, postpone, suggest.
            Random.nextInt(1000000),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean()
        )
        val nextPost = Post(
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            "OLD TEXT",
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextBoolean(),
            "post", //Type of the post, can be: post, copy, reply, postpone, suggest.
            Random.nextInt(1000000),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean()
        )
        val updatePost = Post(
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            "UPDATE TEXT",
            Random.nextInt(1000000),
            Random.nextInt(1000000),
            Random.nextBoolean(),
            "post", //Type of the post, can be: post, copy, reply, postpone, suggest.
            Random.nextInt(1000000),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean(),
            Random.nextBoolean()
        )
        posts = emptyArray()

        add(post)
        add(nextPost)
        updatePost.id = 4
        val result = update(updatePost)
        assertEquals(false, result)

        posts = emptyArray()
    }
}