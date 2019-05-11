package bulletinboard

import io.micronaut.websocket.WebSocketBroadcaster
import io.micronaut.websocket.WebSocketSession
import io.micronaut.websocket.annotation.OnClose
import io.micronaut.websocket.annotation.OnMessage
import io.micronaut.websocket.annotation.OnOpen
import io.micronaut.websocket.annotation.ServerWebSocket
import org.reactivestreams.Publisher

@ServerWebSocket("/websocket/{userId}")
class BoardWebSocket(private val broadcaster: WebSocketBroadcaster) {

    @OnOpen
    fun onOpen(userId: String, session: WebSocketSession): Publisher<String> {
        val msg = "Socket connected! $userId"
        println(msg)
        return broadcaster.broadcast(msg)
    }

    @OnMessage
    fun onMessage(userId: String, message: String, session: WebSocketSession): Publisher<String> {
        val msg = "Received message from $userId: $message"
        println(msg)
        return broadcaster.broadcast(msg)
    }

    @OnClose
    fun onClose(userId: String, session: WebSocketSession): Publisher<String> {
        val msg = "Socket disconnected $userId"
        println(msg)
        return broadcaster.broadcast(msg)
    }
}
