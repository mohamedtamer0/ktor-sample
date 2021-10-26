package com.example.routing

import com.example.db.DatabaseConnection
import com.example.entities.NotesEntity
import com.example.models.Note
import com.example.models.NoteRequest
import com.example.models.NoteResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
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

        post("/notes") {
            val request = call.receive<NoteRequest>()
            val result = db.insert(NotesEntity) {
                set(it.note, request.note)
            }

            if (result == 1) {
                call.respond(
                    HttpStatusCode.OK, NoteResponse(
                        success = true,
                        data = "Values has been Successfully inserted"
                    )
                )
            } else {
                call.respond(
                    HttpStatusCode.BadRequest, NoteResponse(
                        success = false,
                        data = "Failed to insert Values"
                    )
                )
            }
        }

    }
}