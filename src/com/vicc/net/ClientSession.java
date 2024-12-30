package com.vicc.net;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import com.vicc.net.codec.MessageDecoder;
import com.vicc.net.codec.MessageEncoder;

public class ClientSession {

    /**
     * The amount of bytes that can be read per iteration of the network's thread.
     */
    public static final int BYTES_PER_CYCLE = 512;

    /**
     * The registration token.
     */
    private SelectionKey token;

    /**
     * A channel for stream-oriented connecting sockets.
     */
    private SocketChannel socket;

    /**
     * The data that has been received from the client.
     */
    private final ByteBuffer buffer = ByteBuffer.allocate(BYTES_PER_CYCLE);

    private MessageDecoder decoder;
    private MessageEncoder encoder;

    private final ChannelDemultiplexer demultiplexer;
    private Object attachment;

    private boolean disconnected;

    public ClientSession(SelectionKey token, SocketChannel socket, ChannelDemultiplexer demultiplexer) {
        this.token = token;
        this.socket = socket;
        this.demultiplexer = demultiplexer;
    }

    public void encode(Object message) {
        if (encoder == null)
            throw new NullPointerException("A valid encoder must be registered.");
        write(encoder.encode(this, message));
    }

    public void write(ByteBuffer buffer) {
        if (disconnected)
            return;
        buffer.flip();
        try {
            socket.write(buffer);
        } catch (IOException exception) {
            demultiplexer.disconnect(this);
        }
    }

    public void read() {
        if (decoder == null)
            throw new NullPointerException("A valid decoder must be registered.");
        if (disconnected)
            return;
        buffer.clear();
        try {
            if (socket.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    if (!decoder.decode(this))
                        demultiplexer.disconnect(this);
                }
            }
        } catch (IOException exception) {
            demultiplexer.disconnect(this);
        }
    }

    public void disconnect() {
        token.cancel();
    }

    public SelectionKey getToken() {
        return token;
    }

    public void setToken(SelectionKey token) {
        this.token = token;
    }

    public SocketChannel getSocket() {
        return socket;
    }

    public void setSocket(SocketChannel socket) {
        this.socket = socket;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public MessageDecoder getDecoder() {
        return decoder;
    }

    public void setDecoder(MessageDecoder decoder) {
        this.decoder = decoder;
    }

    public MessageEncoder getEncoder() {
        return encoder;
    }

    public void setEncoder(MessageEncoder encoder) {
        this.encoder = encoder;
    }

    public ChannelDemultiplexer getDemultiplexer() {
        return demultiplexer;
    }

    public Object getAttachment() {
        return attachment;
    }

    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }

    public boolean isDisconnected() {
        return disconnected;
    }

    public void setDisconnected(boolean disconnected) {
        this.disconnected = disconnected;
    }
}