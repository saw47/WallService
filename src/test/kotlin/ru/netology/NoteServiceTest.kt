package ru.netology
import NoteService
import OwnerService
import org.junit.Test
import org.junit.Assert.*

class NoteServiceTest {
    @Test
    fun add() {
        val firstAdd: Int = NoteService.add(
            title = "first",
            text = "first text",
            privacy = 1,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val secondAdd: Int = NoteService.add(
            title = "second",
            text = "second text",
            privacy = 2,
            commentPrivacy = 2,
            privacyView = "all",
            privacyComment = "all"
        )
        val thirdAdd: Int = NoteService.add(
            title = "third",
            text = "third text",
            privacy = 2,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val fourthAdd: Int = NoteService.add(
            title = "fourth",
            text = "4th text",
            privacy = 2,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val fifthAdd: Int = NoteService.add(
            title = "fifth",
            text = "5th note",
            privacy = 3,
            commentPrivacy = 3,
            privacyView = "all",
            privacyComment = "all"
        )
        val sixthAdd: Int = NoteService.add(
            title = "sixth",
            text = "6th note",
            privacy = 2,
            commentPrivacy = 3,
            privacyView = "all",
            privacyComment = "all"
        )
        val index = NoteService.noteIdMap[OwnerService.getId().toInt()]?.get(2)?.id
        assertEquals(index, 2)
        val sizeArrayWIthNote = NoteService.noteIdMap[OwnerService.getId().toInt()]?.size
        assertEquals(sizeArrayWIthNote, 6)
        NoteService.noteIdMap.clear()
    }

    @Test
    fun createComment() {
        val firstAdd: Int = NoteService.add(
            title = "first",
            text = "first text",
            privacy = 1,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val secondAdd: Int = NoteService.add(
            title = "second",
            text = "second text",
            privacy = 2,
            commentPrivacy = 2,
            privacyView = "all",
            privacyComment = "all"
        )
        val thirdAdd: Int = NoteService.add(
            title = "third",
            text = "third text",
            privacy = 2,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val fourthAdd: Int = NoteService.add(
            title = "fourth",
            text = "4th text",
            privacy = 2,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val fifthAdd: Int = NoteService.add(
            title = "fifth",
            text = "5th note",
            privacy = 3,
            commentPrivacy = 3,
            privacyView = "all",
            privacyComment = "all"
        )
        val sixthAdd: Int = NoteService.add(
            title = "sixth",
            text = "6th note",
            privacy = 2,
            commentPrivacy = 3,
            privacyView = "all",
            privacyComment = "all"
        )
        val commentOne = NoteService.createComment(
            noteId = 1U,
            ownerId = 100000U,
            message = "1 comment message"
        )
        val commentTwo = NoteService.createComment(
            noteId = 1U,
            ownerId = 100000U,
            message = "2 comment message"
        )
        val commentTree = NoteService.createComment(
            noteId = 1U,
            ownerId = 100000U,
            message = "3 comment message"
        )
        val commentFour = NoteService.createComment(
            noteId = 2U,
            ownerId = 100000U,
            message = "4 comment message"
        )
        val commentFive = NoteService.createComment(
            noteId = 2U,
            ownerId = 100000U,
            message = "5 comment message"
        )
        val commentSix = NoteService.createComment(
            noteId = 3U,
            ownerId = 100000U,
            message = "6 comment message"
        )
        assertEquals(commentFive, 4)
        assertEquals(NoteService.commentsInNotes[OwnerService.getId().toInt()]?.get(commentSix)?.message, "6 comment message")
        assertEquals(NoteService.commentsInNotes[OwnerService.getId().toInt()]?.get(commentFour)?.noteId, 2U)
        NoteService.noteIdMap.clear()
        NoteService.commentsInNotes.clear()
    }

    @Test
    fun delete() {
        val firstAdd: Int = NoteService.add(
            title = "first",
            text = "first text",
            privacy = 1,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val secondAdd: Int = NoteService.add(
            title = "second",
            text = "second text",
            privacy = 2,
            commentPrivacy = 2,
            privacyView = "all",
            privacyComment = "all"
        )
        val isNotDelete = NoteService.noteIdMap[OwnerService.getId().toInt()]?.first()?.isDelete
        assertEquals(isNotDelete, false)
        val isDeleteIndex =
            NoteService.noteIdMap[OwnerService.getId().toInt()]?.first()?.id?.let { NoteService.delete(it.toUInt()) }
        assertEquals(isDeleteIndex, 1)
        val isNowDelete = NoteService.noteIdMap[OwnerService.getId().toInt()]?.first()?.isDelete
        assertEquals(isNowDelete, true)
        NoteService.noteIdMap.clear()
    }

    @Test
    fun deleteComment() {
        val firstAdd: Int = NoteService.add(
            title = "first",
            text = "first text",
            privacy = 1,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val secondAdd: Int = NoteService.add(
            title = "second",
            text = "second text",
            privacy = 2,
            commentPrivacy = 2,
            privacyView = "all",
            privacyComment = "all"
        )
        val commentOne = NoteService.createComment(
            noteId = 1U,
            ownerId = 100000U,
            message = "1 comment message"
        )
        val commentTwo = NoteService.createComment(
            noteId = 1U,
            ownerId = 100000U,
            message = "2 comment message"
        )
        assertEquals(NoteService.commentsInNotes[100000]?.get(commentTwo)?.isDelete, false)
        NoteService.deleteComment(
            commentId = commentTwo.toUInt(),
            ownerId = OwnerService.getId().toUInt()
        )
        assertEquals(NoteService.commentsInNotes[100000]?.get(commentTwo)?.isDelete, true)
        NoteService.noteIdMap.clear()
        NoteService.commentsInNotes.clear()
    }

    @Test
    fun edit() {
        val firstAdd: Int = NoteService.add(
            title = "first",
            text = "usual text",
            privacy = 1,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val note = NoteService.noteIdMap[OwnerService.getId().toInt()]?.get(0)
        assertEquals(note?.text, "usual text")
        if (note != null) {
            NoteService.edit(
                noteId = note.id.toUInt(),
                title = note.title,
                text = "modified text",
                privacy = 1,
                commentPrivacy = note.canComment,
                privacyView = note.privacyView,
                privacyComment = ""
            )
        }
        val noteMod = NoteService.noteIdMap[OwnerService.getId().toInt()]?.get(0)
        assertEquals(noteMod?.text, "modified text")
        NoteService.noteIdMap.clear()
    }

    @Test
    fun editComment() {
        val firstAdd: Int = NoteService.add(
            title = "first",
            text = "first text",
            privacy = 1,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val commentOne = NoteService.createComment(
            noteId = 0U,
            ownerId = 100000U,
            message = "old message"
        )
        assertEquals(NoteService.commentsInNotes[OwnerService.getId().toInt()]?.get(commentOne)?.message, "old message")

        val returnNumber = NoteService.editComment(
            commentId = NoteService.commentsInNotes[OwnerService.getId().toInt()]?.get(commentOne)?.id!!.toUInt() ,
            ownerId = OwnerService.getId().toUInt(),
            message = "new message"
        )
        assertEquals(returnNumber,1)
        assertEquals(NoteService.commentsInNotes[OwnerService.getId().toInt()]?.get(commentOne)?.message, "new message")
        NoteService.noteIdMap.clear()
        NoteService.commentsInNotes.clear()
    }

    @Test
    fun get() {
        val firstAdd: Int = NoteService.add(
            title = "first",
            text = "first text",
            privacy = 1,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val secondAdd: Int = NoteService.add(
            title = "second",
            text = "second text",
            privacy = 2,
            commentPrivacy = 2,
            privacyView = "all",
            privacyComment = "all"
        )
        val thirdAdd: Int = NoteService.add(
            title = "third",
            text = "third text",
            privacy = 2,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val fourthAdd: Int = NoteService.add(
            title = "fourth",
            text = "4th text",
            privacy = 2,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val fifthAdd: Int = NoteService.add(
            title = "fifth",
            text = "5th note",
            privacy = 3,
            commentPrivacy = 3,
            privacyView = "all",
            privacyComment = "all"
        )
        val noteList = NoteService.get(
        userId = OwnerService.getId().toUInt(),
        offset = 1U,
        count = 2U,
        sort = 1U
        )
        assertEquals(noteList.size, 2)
        assertEquals(noteList.first().text, "second text" )
        NoteService.noteIdMap.clear()
    }

    @Test
    fun getById() {
        val firstAdd: Int = NoteService.add(
            title = "first",
            text = "first text",
            privacy = 1,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val secondAdd: Int = NoteService.add(
            title = "second",
            text = "second text",
            privacy = 2,
            commentPrivacy = 2,
            privacyView = "all",
            privacyComment = "all"
        )
        val note = NoteService.getById(
            noteId = 1U,
            ownerId = OwnerService.getId().toUInt()
        )
        assertEquals(note?.title,"second")
    }


    @Test
    fun getComments() {
        val firstAdd: Int = NoteService.add(
            title = "first",
            text = "first text",
            privacy = 1,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val secondAdd: Int = NoteService.add(
            title = "second",
            text = "second text",
            privacy = 2,
            commentPrivacy = 2,
            privacyView = "all",
            privacyComment = "all"
        )
        val commentOne = NoteService.createComment(
            noteId = 0U,
            ownerId = 100000U,
            message = "1 comment message"
        )
        val commentTwo = NoteService.createComment(
            noteId = 0U,
            ownerId = 100000U,
            message = "2 comment message"
        )
        val commentTree = NoteService.createComment(
            noteId = 0U,
            ownerId = 100000U,
            message = "3 comment message"
        )
        val commentFour = NoteService.createComment(
            noteId = 0U,
            ownerId = 100000U,
            message = "4 comment message"
        )
        val commentFive = NoteService.createComment(
            noteId = 0U,
            ownerId = 100000U,
            message = "5 comment message"
        )
        val commentSix = NoteService.createComment(
            noteId = 1U,
            ownerId = 100000U,
            message = "6 comment message"
        )
        NoteService.deleteComment(
            commentId = commentOne.toUInt(),
            ownerId = OwnerService.getId().toUInt()
        )
        val list = NoteService.getComments(
            noteId = firstAdd.toUInt(),
            ownerId = OwnerService.getId(),
            sort = 0U,
            offset = 2U,
            count = 3U
        )
        assertEquals(list.size, 3)
        assertEquals(list.last().message, "3 comment message")
        NoteService.noteIdMap.clear()
        NoteService.commentsInNotes.clear()
    }

    @Test
    fun restoreComment() {
        val firstAdd: Int = NoteService.add(
            title = "first",
            text = "first text",
            privacy = 1,
            commentPrivacy = 1,
            privacyView = "all",
            privacyComment = "all"
        )
        val mustDelete = NoteService.createComment(
            noteId = firstAdd.toUInt(),
            ownerId = OwnerService.getId(),
            message = "i`am must delete"
        )
        val commentTwo = NoteService.createComment(
            noteId = firstAdd.toUInt(),
            ownerId = OwnerService.getId(),
            message = "2 comment message"
        )
        NoteService.deleteComment(mustDelete.toUInt(),OwnerService.getId())
        assertEquals(NoteService.commentsInNotes[OwnerService.getId().toInt()]?.get(mustDelete)?.isDelete, true)
        val restore = NoteService.restoreComment(
            commentId = mustDelete.toUInt(),
            ownerId = OwnerService.getId()
        )
        assertEquals(restore, 1)
        assertEquals(NoteService.commentsInNotes[OwnerService.getId().toInt()]?.get(mustDelete)?.isDelete, false)
        NoteService.noteIdMap.clear()
        NoteService.commentsInNotes.clear()
    }
}