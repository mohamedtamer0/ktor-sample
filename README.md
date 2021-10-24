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


