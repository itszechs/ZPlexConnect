package zechs.zplex.connect

import io.ktor.serialization.gson.gson
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.ApplicationStopped
import io.ktor.server.application.install
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

import zechs.zplex.connect.routes.remotePlayRoute
import zechs.zplex.connect.utils.getWifiIPv4Address


fun main() {
    val remotePlayPort = 62941
    embeddedServer(Netty, applicationEngineEnvironment {
        module {
            remotePlayRoute()

            install(ContentNegotiation) { gson() }

            environment.monitor.subscribe(ApplicationStarted) {
                val wifiIPv4Address = getWifiIPv4Address()
                if (wifiIPv4Address == null) {
                    println("Could not find a WiFi interface with an IPv4 address.")
                } else {
                    println("ZPlex Connect is running at: http://$wifiIPv4Address:$remotePlayPort ")
                }
            }
            environment.monitor.subscribe(ApplicationStopped) {
                println("ZPlex Connect stopped!")
                environment.monitor.unsubscribe(ApplicationStarted) {}
                environment.monitor.unsubscribe(ApplicationStopped) {}
            }
        }
        connector {
            host = "0.0.0.0"
            port = remotePlayPort
        }
        developmentMode = false
    }).start(wait = true)
}