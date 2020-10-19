package nl.omererdem.nmeaserver

import java.io.OutputStream
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.Charset
import kotlin.concurrent.thread

const val port: Int = 5000
const val interval: Long = 1000
val sentences = listOf(
    "\$SDDBT,25.0,f,7.6,M,4.2,F*36\n\$SDDPT,7.6,0.3*55\n\$VWVHW,,,,,06.37,N,,\n\$VWVLW,000963,N,0956.8,N\n\$YXMTW,36.8,C*1F",
    "\$SDDBT,25.0,f,8.6,M,4.2,F*36\n\$SDDPT,8.6,0.3*55\n\$VWVHW,,,,,06.27,N,,\n\$VWVLW,000962,N,0956.9,N\n\$YXMTW,36.8,C*1F",
    "\$SDDBT,25.0,f,9.6,M,4.2,F*36\n\$SDDPT,9.6,0.3*55\n\$VWVHW,,,,,06.17,N,,\n\$VWVLW,000961,N,0956.0,N\n\$YXMTW,36.8,C*1F",
    "\$SDDBT,25.0,f,10.6,M,4.2,F*36\n\$SDDPT,10.6,0.3*55\n\$VWVHW,,,,,06.07,N,,\n\$VWVLW,000960,N,0956.1,N\n\$YXMTW,36.8,C*1F",
    "\$SDDBT,25.0,f,11.6,M,4.2,F*36\n\$SDDPT,11.6,0.3*55\n\$VWVHW,,,,,06.97,N,,\n\$VWVLW,000969,N,0956.2,N\n\$YXMTW,36.8,C*1F",
    "\$SDDBT,25.0,f,12.6,M,4.2,F*36\n\$SDDPT,12.6,0.3*55\n\$VWVHW,,,,,06.87,N,,\n\$VWVLW,000968,N,0956.3,N\n\$YXMTW,36.8,C*1F",
    "\$SDDBT,25.0,f,13.6,M,4.2,F*36\n\$SDDPT,13.6,0.3*55\n\$VWVHW,,,,,06.77,N,,\n\$VWVLW,000967,N,0956.4,N\n\$YXMTW,36.8,C*1F",
    "\$SDDBT,25.0,f,14.6,M,4.2,F*36\n\$SDDPT,14.6,0.3*55\n\$VWVHW,,,,,06.67,N,,\n\$VWVLW,000966,N,0956.5,N\n\$YXMTW,36.8,C*1F",
    "\$SDDBT,25.0,f,15.6,M,4.2,F*36\n\$SDDPT,15.6,0.3*55\n\$VWVHW,,,,,06.57,N,,\n\$VWVLW,000965,N,0956.6,N\n\$YXMTW,36.8,C*1F",
    "\$SDDBT,25.0,f,16.6,M,4.2,F*36\n\$SDDPT,16.6,0.3*55\n\$VWVHW,,,,,06.47,N,,\n\$VWVLW,000964,N,0956.7,N\n\$YXMTW,36.8,C*1F"
)

fun main(args: Array<String>) {
    val server = ServerSocket(port)
    println("Server is running on port ${server.localPort}")

    while (true) {
        val client = server.accept()
        println("${client.inetAddress.hostAddress} opened the connection")

        thread { Server(client).run() }
    }
}

class Server(client: Socket) {
    private val client: Socket = client
    private val writer: OutputStream = client.getOutputStream()
    private var running: Boolean = false


    fun run() {
        var i = 0
        running = true
        write("You are now connected with the NMEA Emulator Server Script by The Mobile Company")

        while (running) {
            try {
                write(sentences[i])
                if (i < sentences.size - 1) {
                    i + 1
                } else {
                    i = 0
                }
                Thread.sleep(interval)
            } catch (e: Exception) {
                shutdown()
            }
        }
    }

    private fun write(message: String) {
        writer.write((message + "\n").toByteArray(Charset.defaultCharset()))
    }

    private fun shutdown() {
        running = false
        client.close()
        println("${client.inetAddress.hostAddress} closed the connection")
    }
}