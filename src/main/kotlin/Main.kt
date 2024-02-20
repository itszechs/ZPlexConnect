package zechs.zplex.connect

import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, applicationEngineEnvironment {
        connector {
            host = "0.0.0.0"
            port = 62941
        }
        developmentMode = false
    }).start(wait = true)
}