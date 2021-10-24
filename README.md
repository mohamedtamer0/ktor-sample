# ktor-sample

<img src="https://repository-images.githubusercontent.com/40136600/f3f5fd00-c59e-11e9-8284-cb297d193133" alt="Ktor" width="500" style="max-width:100%;">

Ktor is an asynchronous framework for creating microservices, web applications and more. Written in Kotlin from the ground up.


- Application.kt

```kotlin

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureRouting()
    }.start(wait = true)
}


```


- Routing.kt

```kotlin

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

```

* Runs embedded web server on `localhost:8080`
* Installs routing and responds with `Hello, world!` when receiving a GET http request for the root path

==================================================

- Ktor Request API With PostMan
- Routing.kt

```kotlin

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
    }
}

```


```code
Put This Url In Postman And Send Request
http://127.0.0.1:8080?name=Your Name&email=YourEmail8@gmail.com
```


```build
This Is Result After Send Url In Run Application IntelliJ IDEA Ultimate

URI: /?name=YourName&email=YourEmail8@gmail.com
Headers: [User-Agent, Accept, Postman-Token, Host, Accept-Encoding, Connection]
User-Agent: PostmanRuntime/7.28.4
Accept: */*
Query Params: [name, email]
Name: YourName
Email: YourEmail8@gmail.com

```


==================================================

- Ktor Url Parameters With PostMan


- Routing.kt
```kotlin

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*


fun Application.configureRouting() {

    routing {
        
        // Url Parameters
        get("iphones/{page}") {
            val pageNumber = call.parameters["page"]
            call.respondText("Your are no Page number : $pageNumber")
        }
    }
}

```


```code
Put This Url In Postman And Send Request
http://127.0.0.1:8080/iphones/2
```


==================================================

- Ktor Request Body With PostMan

- build.gradle.kts

```gradle

plugins {
    application
    kotlin("jvm") version "1.5.31" // or kotlin("multiplatform") or any other kotlin plugin
    kotlin("plugin.serialization") version "1.5.31"
}

dependencies {
    implementation("io.ktor:ktor-serialization:$ktor_version")
}

```


- Routing.kt
```kotlin

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import kotlinx.serialization.*

fun Application.configureRouting() {

    routing {
        
        // Request Body
        post("/login") {
            val userInfo = call.receive<UserInfo>()
            println(userInfo)
            call.respondText("Everything Working Fine!")
        }
    }
}

```


```kotlin
@Serializable
data class UserInfo(
    val name:String,
    val email:String
)

```


- Application.kt

```kotlin

install(ContentNegotiation){
    json()
}

```


```code
1- Put This Url In Postman And Send Request
http://127.0.0.1:8080/login

2- select POST

3- In Body choose format JSON and write json code
{
    "name": "YourName",
    "email": "YourEmail@gmail.com"
}
4- Send
```



```build
This Is Result After Send Url In Run Application IntelliJ IDEA Ultimate

UserInfo(name=YourName, email=YourEmail@gmail.com)

```

==================================================

- Ktor Response - Sending JSON Response With PostMan


- Routing.kt

```kotlin

//GatewayTimeout
call.respondText("Wrong!!!!!!!!!", status = HttpStatusCode.GatewayTimeout)

// OR NotFound
call.respondText("Not Found !!!!!!!!!", status = HttpStatusCode.NotFound)
```

- if you want Sending JSON Response To PostMan


```kotlin
@Serializable
data class UserInfo(
    val name:String,
    val email:String
)

```


```kotlin
    val responseObj = UserInfo("YourName","YourEmail@gmail.com")
    call.respond(responseObj)

```

```code
In PostMan
Select GET and Put This Url http://127.0.0.1:8080/
and SEND
The Result in PostMan
=======
{
    "name": "YourName",
    "email": "YourEmail@gmail.com"
}

```

==================================================

- Ktor Response - Downloading a File

-- Step one
```code
1- First create a New Directory name = files
2- add some photo in file
```

-- Step Two

- Routing.kt

```kotlin
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


        // Ktor Response - Open a File In Browser
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
        
```

