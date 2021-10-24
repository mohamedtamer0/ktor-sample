package com.example.plugins

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*


fun Application.contactUsModule() {

    routing {
        get("/contactus") {
            call.respondText { "Contact Us" }
        }


        get("/aboutus") {
            call.respondText { "About Us" }
        }
    }
}