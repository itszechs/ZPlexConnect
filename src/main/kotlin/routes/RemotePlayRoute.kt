package zechs.zplex.connect.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import zechs.zplex.connect.models.RemotePlay

fun Application.remotePlayRoute() {
    routing {
        post("/remote-play") {
            val remotePlay = call.receive<RemotePlay>()
            println("Remote play: $remotePlay")
            call.respond(HttpStatusCode.OK)
        }
    }
}