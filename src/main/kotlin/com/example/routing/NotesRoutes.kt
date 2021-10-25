package com.example.routing

import com.example.db.DatabaseConnection
import com.example.entities.NotesEntity
import com.example.models.Note
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.ktorm.dsl.from
import org.ktorm.dsl.map
import org.ktorm.dsl.select


fun Application.notesRoutes() {
    val db = DatabaseConnection.database
    routing {
        get("/notes") {
            val notes = db.from(NotesEntity).select()
                .map {
                    val id = it[NotesEntity.id]
                    val note = it[NotesEntity.note]
                    Note(id ?: -1, note ?: "")
                }
            call.respond(notes)
        }
    }
}