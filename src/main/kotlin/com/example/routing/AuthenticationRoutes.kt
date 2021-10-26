package com.example.routing

import com.example.db.DatabaseConnection
import com.example.entities.UserEntity
import com.example.models.UserCredentials
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.ktorm.dsl.insert


fun Application.authenticationRoutes() {
    val db = DatabaseConnection.database

    routing {
        post("/register") {
            val userCredentials =call.receive<UserCredentials>()

            val username = userCredentials.username
            val password = userCredentials.password

            db.insert(UserEntity) {
                set(it.userName, username)
                set(it.password, password)
            }

            call.respondText("Data inserted!")

        }
    }
}