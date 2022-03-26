import kotlin.random.Random

//small visual test
fun main() {
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
    val postTwo = Post(
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
    println(post)
    println(post.comments.count)
    println(post.comments.canPost)
    post.comments.count += 1u
    post.comments.canPost = false
    println(post.comments.count)
    println(post.comments.canPost)
    println("____________________________________________________________________")
    WallService.add(post)
    WallService.add(postTwo)
    println(WallService.posts.size)
    println(WallService.posts[post.id])
    println("____________________________________________________________________")
    println(WallService.posts[postTwo.id])
    println("____________________________________________________________________")
    println(postTwo)
    println(postTwo.id)
    postTwo.text = "NEW UPDATE TEXT"
    WallService.update(postTwo)
    println(postTwo)
    println(postTwo.id)

}