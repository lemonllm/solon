package org.noear.solon.boot.undertow.websocket;

import io.undertow.websockets.core.*;
import org.noear.solon.core.event.EventBus;
import org.noear.solon.core.message.Message;
import org.noear.solon.core.message.MessageSession;
import org.noear.solon.extend.xsocket.XListenerProxy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class UtWsChannelListener extends AbstractReceiveListener {

    public void onOpen(WebSocketChannel channel) {
        XListenerProxy.getGlobal().onOpen(_SocketSession.get(channel));
    }


    @Override
    protected void onFullBinaryMessage(WebSocketChannel channel, BufferedBinaryMessage msg) throws IOException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            for (ByteBuffer buf : msg.getData().getResource()) {
                out.write(buf.array());
            }

            MessageSession session = _SocketSession.get(channel);
            Message message = Message.wrap(channel.getUrl(), out.toByteArray());

            XListenerProxy.getGlobal().onMessage(session, message, false);
        } catch (Throwable ex) {
            EventBus.push(ex);
        }
    }

    @Override
    protected void onFullTextMessage(WebSocketChannel channel, BufferedTextMessage msg) throws IOException {
        try {
            MessageSession session = _SocketSession.get(channel);
            Message message = Message.wrap(channel.getUrl(),
                    msg.getData().getBytes("UTF-8"));

            XListenerProxy.getGlobal().onMessage(session, message, true);
        } catch (Throwable ex) {
            EventBus.push(ex);
        }
    }

    @Override
    protected void onClose(WebSocketChannel channel, StreamSourceFrameChannel frameChannel) throws IOException {
        XListenerProxy.getGlobal().onClose(_SocketSession.get(channel));

        _SocketSession.remove(channel);
    }

    @Override
    protected void onError(WebSocketChannel channel, Throwable error) {
        XListenerProxy.getGlobal().onError(_SocketSession.get(channel), error);
    }
}
