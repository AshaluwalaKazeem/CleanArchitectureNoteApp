package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class AddNoteTest {

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var addNote: AddNote

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        addNote = AddNote(fakeNoteRepository)
    }

    @Test
    fun `Test for blank title Input`() = runBlocking {
        val note = addNote(
            Note(
                title = "",
                content = "sk",
                1L,
                23,
            )
        )

        assertThat(note).isInstanceOf(InvalidNoteException::class.java)
    }

    @Test
    fun `Test for blank content Input`() = runBlocking {
        val note = addNote(
            Note(
                title = "2sd",
                content = "",
                1L,
                23,
            )
        )

        assertThat(note).isInstanceOf(InvalidNoteException::class.java)
    }

    @Test
    fun `Test add note`() = runBlocking {
        val note = addNote(
            Note(
                title = "2sd",
                content = "sdf",
                1L,
                23,
            )
        )

        val notes = fakeNoteRepository.getNotes().first()
        assertThat(notes).isNotEmpty()
    }
}