package com.example

import com.example.entities.NotesEntity
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.select


fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        install(ContentNegotiation) {
            json()
        }
        //MYSQLl
        val database = Database.connect(
            url = "jdbc:mysql://localhost:3306/notes",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "tamer hosny1996"
        )


        // Inserting Values
        database.insert(NotesEntity) {
            set(it.note, "study Ktor")
        }
        database.insert(NotesEntity) {
            set(it.note, "study JetPackCompose")
        }
        database.insert(NotesEntity) {
            set(it.note, "study GoLanguage")
        }

        // Reading Value From Database
        var notes = database.from(NotesEntity)
            .select()
        for (row in notes){
            println("${row[NotesEntity.id]}: ${row[NotesEntity.note]}")
        }





        configureRouting()
        contactUsModule()
    }.start(wait = true)
}
