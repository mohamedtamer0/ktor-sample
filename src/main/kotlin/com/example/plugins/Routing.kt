package com.example.plugins

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*


fun Application.configureRouting() {

    routing {
        get("/") {
            println("URI: ${call.request.uri}")
            println("Headers: ${call.request.headers}")
                call.respondText("Hello Ktor!")
            }
    }
}
