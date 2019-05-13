package bulletinboard

import bulletinboard.model.ClientEvent
import bulletinboard.model.ServerEvent
import io.micronaut.websocket.WebSocketBroadcaster
import io.micronaut.websocket.WebSocketSession
import io.micronaut.websocket.annotation.OnClose
import io.micronaut.websocket.annotation.OnMessage
import io.micronaut.websocket.annotation.OnOpen
import io.micronaut.websocket.annotation.ServerWebSocket
import org.reactivestreams.Publisher

@ServerWebSocket("/websocket/{sessionId}")
class BoardWebSocket(private val broadcaster: WebSocketBroadcaster) {

    @OnOpen
    fun onOpen(sessionId: String, session: WebSocketSession): Publisher<ServerEvent> {
        println("Socket connected! $sessionId")
        val msg = ServerEvent(type = "ping")
        return broadcaster.broadcast(msg)
    }

    @OnMessage
    fun onMessage(sessionId: String, message: ClientEvent, session: WebSocketSession) {
        println("Received message from $sessionId: $message")
    }

    @OnClose
    fun onClose(sessionId: String, session: WebSocketSession) {
        val msg = "Socket disconnected $sessionId"
        println(msg)
    }
}
