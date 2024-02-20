package zechs.zplex.connect

import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import zechs.zplex.connect.routes.remotePlayRoute

fun main() {
    embeddedServer(Netty, applicationEngineEnvironment {
        module {
            remotePlayRoute()
        }
        connector {
            host = "0.0.0.0"
            port = 62941
        }
        developmentMode = false
    }).start(wait = true)
}