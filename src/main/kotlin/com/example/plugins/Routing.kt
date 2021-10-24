package com.example.plugins

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*


fun Application.configureRouting() {

    routing {
        get("/") {
                call.respondText("Hello Ktor!")
            }
    }
}
