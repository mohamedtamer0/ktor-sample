package com.example

import com.example.entities.NotesEntity
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.routing.authenticationRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import org.ktorm.database.Database
import org.ktorm.dsl.*


fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        install(ContentNegotiation) {
            json()
        }



//        // Inserting Values
//        database.insert(NotesEntity) {
//            set(it.note, "study Ktor")
//        }
//        database.insert(NotesEntity) {
//            set(it.note, "study JetPackCompose")
//        }
//        database.insert(NotesEntity) {
//            set(it.note, "study GoLanguage")
//        }
//


//        // Reading Value From Database
//        val notes = database.from(NotesEntity)
//            .select()
//        for (row in notes) {
//            println("${row[NotesEntity.id]}: ${row[NotesEntity.note]}")
//        }



//        // Updating And Deleting Values From A DB
//        database.update(NotesEntity) {
//            set(it.note, "Learning Ktor")
//            where {
//                it.id eq 1
//                it.id eq 2
//            }
//        }
//
//        database.delete(NotesEntity){
//            it.id eq 3
//        }





        configureRouting()
        contactUsModule()
    }.start(wait = true)
}
