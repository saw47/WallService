import java.time.Instant

class Note(
    val ownerId: Int,
    val title: String,
    val text: String,
    val privacyView: String,
    val canComment: Int,
) : NoteImplementable {


    var textWiki: String? = null
    var viewUrl: String? = null
    var comments: Int = 0
    var readComments: Int = 0

    override var id: Int = -1
    override val date: Int = Instant.now().epochSecond.toInt();
    override var isDelete = false //добавил признак удалённого объекта, в API нет
}

class NoteComments(
    val replyTo: UInt? = null,
    val message: String,
    val noteId: UInt,
    val ownerId: UInt
) : NoteImplementable {
    var gUid: String? = null


    override var id: Int = -1
    override var isDelete = false //добавил признак удалённого объекта, в API нет
    override val date: Int = Instant.now().epochSecond.toInt()
    //добавил date, в API нет, а в методе notes.getComments - есть
}

object NoteService {
    var commentsInNotes = mutableMapOf<Int, Array<NoteComments>>()
    var noteIdMap = mutableMapOf<Int, Array<Note>>()

    fun add(
        title: String,
        text: String,
        privacy: Int,
        commentPrivacy: Int,
        privacyView: String,
        privacyComment: String
    ): Int {
        val note = Note(
            ownerId = OwnerService.getId().toInt(),
            title = title,
            text = text,
            privacyView = privacyView,
            canComment = commentPrivacy
        )
        var returnId: Int = -1

        if (noteIdMap.keys.contains(note.ownerId)) {
            returnId = noteIdMap[note.ownerId]!!.lastIndex + 1
            noteIdMap[note.ownerId] = noteIdMap[note.ownerId]!! + note
        } else {
            returnId = 0
            noteIdMap[note.ownerId] = emptyArray<Note>() + note
        }
        if (returnId == -1) {
            error("Note don't added exception")
        }
        note.id = returnId
        return returnId
    }

    fun createComment(
        noteId: UInt,
        ownerId: UInt,
        replyTo: UInt? = null,
        message: String,
    ): Int {
        var cId: Int = -1
        val note = noteIdMap[ownerId.toInt()]?.get(noteId.toInt())
        if (note != null) {
            if (note.isDelete) throw ObjectDeleteException("Can't added")

            val comment = NoteComments(
                replyTo = replyTo,
                message = message,
                noteId = noteId,
                ownerId = ownerId
            )
            comment.gUid = "${note.ownerId}_${(System.currentTimeMillis() / 1000).toInt()}"

            if (commentsInNotes.keys.contains(comment.ownerId.toInt())) {
                cId = commentsInNotes[comment.ownerId.toInt()]!!.lastIndex + 1
                comment.id = cId
                commentsInNotes[comment.ownerId.toInt()] = commentsInNotes[note.ownerId]!! + comment
            } else {
                cId = 0
                comment.id = cId
                commentsInNotes[comment.ownerId.toInt()] = emptyArray<NoteComments>() + comment
            }

            if (cId < 0) {
                error("Comment don't added exception")
            }
        }
        return cId
    }


    fun delete(noteId: UInt): Int {
        val note: Note? = NoteService.noteIdMap[OwnerService.getId().toInt()]?.get(noteId.toInt())

        return if (note != null && !note.isDelete) {
            note.isDelete = true
            1
        } else {
            throw ObjectDeleteException("The object already has been deleted or does not exist")
        }
    }

    fun deleteComment(
        commentId: UInt,
        ownerId: UInt,
    ): Int {
        var returnValue: Int = 0
        if (commentsInNotes[ownerId.toInt()]?.get(commentId.toInt()) != null) {
            val comment = commentsInNotes[ownerId.toInt()]?.get(commentId.toInt())
            if (comment?.isDelete != true) {
                comment?.isDelete = true
                returnValue = 1
            } else {
                throw ObjectDeleteException("The object already has been deleted or does not exist")
            }
        }
        return returnValue
    }

    fun edit(
        noteId: UInt,
        title: String,
        text: String,
        privacy: Int,
        commentPrivacy: Int,
        privacyView: String,
        privacyComment: String
    ): Int {
        val note: Note? = noteIdMap[OwnerService.getId().toInt()]?.get(noteId.toInt())
        val returnValue: Int

        if (note != null && !note.isDelete) {
            val newNote = Note(
                ownerId = note.ownerId,
                title = title,
                text = text,
                privacyView = privacyView,
                canComment = commentPrivacy
            )
            newNote.id = note.id
            noteIdMap[newNote.ownerId]?.set(newNote.id, newNote)
            returnValue = 1
        } else {
            returnValue = 0
        }
        return returnValue
    }

    fun editComment(
        commentId: UInt,
        ownerId: UInt,
        message: String
    ): Int {
        val oldComment = commentsInNotes[ownerId.toInt()]?.get(commentId.toInt())
        if (oldComment!!.isDelete) {
            throw ObjectDeleteException("Object is delete")
        }
        var returnValue = -1
        if (commentsInNotes[ownerId.toInt()]?.get(commentId.toInt()) != null) {
            val tempComment = NoteComments(
                message = message,
                noteId = oldComment.noteId,
                ownerId = ownerId
            )
            tempComment.gUid = oldComment.gUid
            tempComment.isDelete = oldComment.isDelete
            tempComment.id = oldComment.id
            commentsInNotes[ownerId.toInt()]?.set(tempComment.id, tempComment)
            returnValue = 1
        }
        return returnValue
    }

    fun get(
        noteIds: String? = null,
        userId: UInt,
        offset: UInt,
        count: UInt,
        sort: UInt

    ): List<Note> {

        var returnObject = getNoteImplementableObject(
            userId = userId,
            offset = offset,
            count = count,
            sort = sort
        )
        return returnObject as List<Note>
    }

    fun getById(
        noteId: UInt,
        ownerId: UInt,
        needWiki: Boolean = false
    ): Note? {
        if (noteIdMap[ownerId.toInt()]?.get(noteId.toInt()) != null) {
            if (noteIdMap[ownerId.toInt()]?.get(noteId.toInt())?.isDelete == true) {
                throw ObjectDeleteException("Note is delete")
            }
            return noteIdMap[ownerId.toInt()]?.get(noteId.toInt())
        } else {
            error("Note not found")
        }
    }

    fun getComments(
        noteId: UInt,
        ownerId: UInt,
        sort: UInt,
        offset: UInt,
        count: UInt
    ): List<NoteComments> {

        var returnObject = getNoteImplementableObject(
            noteId = noteId,
            userId = ownerId,
            offset = offset,
            count = count,
            sort = sort
        )
        return returnObject as List<NoteComments>
    }

    fun getFriendNotes(
        offset: UInt,
        count: UInt
    ) {
        error(
            "Данный метод устарел и может быть отключён через некоторое время, " +
                    "пожалуйста, избегайте его использования."
        )
    }

    fun restoreComment(
        commentId: UInt,
        ownerId: UInt
    ): Int {
        var returnIndex: Int = -1
        if (commentsInNotes[ownerId.toInt()]?.get(commentId.toInt()) != null) {
            val comment = commentsInNotes[ownerId.toInt()]?.get(commentId.toInt())
            if (comment?.isDelete == true) {
                comment.isDelete = false
                commentsInNotes[ownerId.toInt()]?.set(commentId.toInt(), comment)
                returnIndex = 1
            } else {
                throw ObjectDeleteException(" Object is not deleted")
            }
        }
        return returnIndex
    }


    private fun getNoteImplementableObject(
        noteIds: String? = null,
        noteId: UInt? = null,
        userId: UInt,
        offset: UInt,
        count: UInt,
        sort: UInt
    ): List<NoteImplementable> {

        val tempArray = if (noteId == null) {
            noteIdMap[userId.toInt()]
        } else {
            commentsInNotes[userId.toInt()]
        }

        if (tempArray == null || tempArray.size < (offset + count).toInt()
        ) {
            error("Index out of bounds or comments is not found from this user")
        } else {
            val returnList =
                tempArray.toList().filter { !it.isDelete }
                    .filter { it.id in offset.toInt()..(offset + count - 1U).toInt() }

            return when (sort) {
                1U -> {
                    returnList.sortedBy { n: NoteImplementable -> n.date }
                }
                0U -> {
                    returnList.sortedBy { n: NoteImplementable -> n.date }.asReversed()
                }
                else -> error("illegal sort type exception")
            }
        }
    }
}


