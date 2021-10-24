package com.example.plugins

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*


fun Application.configureRouting() {

    routing {
        get("/") {
            println("URI: ${call.request.uri}")
            println("Headers: ${call.request.headers.names()}")

            // Request
            println("User-Agent: ${call.request.headers["User-Agent"]}")
            println("Accept: ${call.request.headers["Accept"]}")
            println("Query Params: ${call.request.queryParameters.names()}")
            println("Name: ${call.request.queryParameters["name"]}")
            println("Email: ${call.request.queryParameters["email"]}")


            call.respondText("Hello Ktor!")
        }


        // Url Parameters
        get("iphones/{page}") {
            val pageNumber = call.parameters["page"]
            call.respondText("Your are no Page number : $pageNumber")
        }
    }
}
