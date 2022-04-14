import java.time.Instant

fun main() {

    val x: Int = NoteService.add(
        title = "test title",
        text = "first text",
        privacy = 1,
        commentPrivacy = 1,
        privacyView = "all",
        privacyComment = "all"
    )

    val y: Int = NoteService.add(
        title = "second title",
        text = "second text",
        privacy = 2,
        commentPrivacy = 1,
        privacyView = "all",
        privacyComment = "all"
    )


    val z: Int = NoteService.add(
        title = "delete",
        text = "i am delete",
        privacy = 2,
        commentPrivacy = 1,
        privacyView = "all",
        privacyComment = "all"
    )

    val w: Int = NoteService.add(
        title = "delete",
        text = "4th note",
        privacy = 2,
        commentPrivacy = 1,
        privacyView = "all",
        privacyComment = "all"
    )

    val b: Int = NoteService.add(
        title = "delete",
        text = "5th note",
        privacy = 2,
        commentPrivacy = 1,
        privacyView = "all",
        privacyComment = "all"
    )
    val bd: Int = NoteService.add(
        title = "delete",
        text = "6th note",
        privacy = 2,
        commentPrivacy = 1,
        privacyView = "all",
        privacyComment = "all"
    )


    val c = NoteService.createComment(
        noteId = 1U,
        ownerId = 100000U,
        message = "1 comment message"
    )

    val d = NoteService.createComment(
        noteId = 1U,
        ownerId = 100000U,
        message = "2 comment message"
    )

    val r = NoteService.createComment(
        noteId = 1U,
        ownerId = 100000U,
        message = "3 comment message"
    )


    val v = NoteService.noteIdMap
    for (value in v) {
        val arr = value.value
        println(arr[0].text)
        println(arr[0].id)
        println(arr[1].text)
        println(arr[1].id)
        println(arr[2].text)
        println(arr[2].id)
    }

    val edit = NoteService.edit(
        noteId = 0U,
        title = "new title",
        text = "new text",
        privacy = 2,
        commentPrivacy = 2,
        privacyView = "all",
        privacyComment = "all"
    )

//    println("______________________________________")
//    println(NoteService.commentsInNotes[OwnerService.getId()]?.get(1)?.message)

    val edComment = NoteService.editComment(
        commentId = 0U,
        ownerId = 100000U,
        message = "edit message"
    )

    NoteService.delete(z.toUInt())
//    println(NoteService.noteIdMap[OwnerService.getId().toInt()]?.size!!)

    val list = NoteService.get(
        noteIds = "1,3",
        userId = OwnerService.getId().toUInt(),
        offset = 2U,
        count = 4U,
        sort = 1U
    )

    val com = NoteService.getComments(
        noteId = 1U,
        ownerId = OwnerService.getId().toUInt(),
        sort = 0U,
        offset = 1U,
        count = 2U
    )

    println("q______________________________________")
    println("q______________________________________")
    list.forEach { println(it.text) }
    println("q______________________________________")
    com.forEach { println(it.message) }
    println("q______________________________________")
    println("q______________________________________")
//    println("comment index $c  comment text ${NoteService.commentsInNotes[OwnerService.getId()]?.get(c)?.message}")
//    println("delete z index ${NoteService.noteIdMap[OwnerService.getId()]?.get(z)?.noteIsDelete}")
//    println(edit)
//        println("______________________________________")
//        println(NoteService.commentsInNotes[OwnerService.getId()]?.get(1)?.message)

    println("q______________________________________")
    println("q___----------------------------------------------------------_____")
    NoteService.delete(x.toUInt())
    NoteService.delete(x.toUInt())
    println(NoteService.noteIdMap[OwnerService.getId().toInt()]?.get(x)?.isDelete)
    println("q___----------------------------------------------------------_____")




    val gbyd = NoteService.getById(
        noteId = 4u,
        ownerId = OwnerService.getId().toUInt(),
    )

    //println("get by id ${gbyd!!.text}")


//    val testPost = Post(
//        fromId = 205445,
//        createdBy = 556933,
//        text = "Test text post bla",
//        replyOwnerId = 225666,
//        replyPostId = 558455,
//        friendsOnly = false,
//        postType = "post",
//        signerId = 447885,
//        canPin = false,
//        canDelete = false,
//        canEdit = true,
//        isPinned = false,
//        markedAsAds = false,
//        isFavorite = true,
//        commentsCanPost = true,
//        commentsGroupsCanPost = true,
//        likesUserLikes = true,
//        likesCanLike = true,
//        likesCanPublish = true,
//        repostsUserReposted = true
//    )
//
//    val oneMoreTestPost = Post(
//        fromId = 223332,
//        createdBy = 456975,
//        text = "it is another post!",
//        replyOwnerId = 334443,
//        replyPostId = 332223,
//        friendsOnly = false,
//        postType = "post",
//        signerId = 323232,
//        canPin = false,
//        canDelete = false,
//        canEdit = true,
//        isPinned = false,
//        markedAsAds = false,
//        isFavorite = true,
//        commentsCanPost = true,
//        commentsGroupsCanPost = true,
//        likesUserLikes = true,
//        likesCanLike = true,
//        likesCanPublish = true,
//        repostsUserReposted = true
//    )
//
//    WallService.add(testPost)
//    WallService.add(oneMoreTestPost)
//
//
//    val commentToTestPost: Comment = Comment(
//        canPost = true,
//        showReplyButton = true,
//        groupsCanPost = true,
//        isDon = true,
//        id = 554447,
//        fromId = 558998,
//        date = Instant.now().epochSecond.toInt(),
//        postId = 0
//    )
//    commentToTestPost.text = "some text to test post"
//
//    WallService.createComment(commentToTestPost)
//
//
//    println(testPost.comment.count)
//    println(oneMoreTestPost.comment.count)
//    println(WallService.copyPostsArrayToTests().toString())
//    for( post in WallService.copyPostsArrayToTests()) {
//        println(post.toString())
//        println(post.id)
//        println(post.comment.count)
//    }

}