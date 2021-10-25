package com.example.plugins

import com.example.routing.notesRoutes
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import kotlinx.serialization.*
import java.io.File


fun Application.configureRouting() {

    routing {
        get("/") {
            //call.respondText("Wrong!!!!!!!!!", status = HttpStatusCode.GatewayTimeout)
            //call.respondText("Not Found !!!!!!!!!", status = HttpStatusCode.NotFound)
//            val responseObj = UserInfo("YourName","YourEmail@gmail.com")
//            call.respond(responseObj)

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


        // Request Body
        post("/login") {
            val userInfo = call.receive<UserInfo>()
            println(userInfo)
            call.respondText("Everything Working Fine!")
        }


        // Ktor Response - Downloading a File
        get("/fileDownload") {
            val file = File("files/pic.png")

            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(
                    ContentDisposition.Parameters.FileName, "pic.png"
                ).toString()
            )
            call.respondFile(file)

        }

        get("/fileOpen") {
            val file = File("files/pic.png")

            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Inline.withParameter(
                    ContentDisposition.Parameters.FileName, "pic.png"
                ).toString()
            )
            call.respondFile(file)

        }

    }


    notesRoutes()

}

@Serializable
data class UserInfo(
    val name: String,
    val email: String
)
