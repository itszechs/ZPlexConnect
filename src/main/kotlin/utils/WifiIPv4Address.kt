package zechs.zplex.connect.utils

import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException

fun getWifiIPv4Address(): String? {
    try {
        NetworkInterface.getNetworkInterfaces().toList().forEach { networkInterface ->
            if (networkInterface.isUp && networkInterface.hardwareAddress != null &&
                !networkInterface.isLoopback && !networkInterface.isVirtual
            ) {
                networkInterface.interfaceAddresses.forEach { address ->
                    if (address.address is Inet4Address && networkInterface.name.startsWith("w")) {
                        return address.address.hostAddress
                    }
                }
            }
        }
    } catch (e: SocketException) {
        e.printStackTrace()
    }
    return null
}
