package com.vicc.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public abstract class ChannelDemultiplexer implements Runnable {

	public static final boolean CHANNEL_BLOCKING_MODE = false;

	private Selector selector;
	private ServerSocketChannel server;

	/**
	 * Called upon reception of a new connection.
	 * 
	 * @param session The client that has been connected.
	 */
	public abstract void accept(ClientSession session);

	/**
	 * Called upon removal of an existing connection.
	 * 
	 * @param session The client that has been disconnected.
	 */
	public abstract void disconnect(ClientSession session);

	public ChannelDemultiplexer() throws IOException {
		this(Selector.open(), ServerSocketChannel.open());
	}

	public ChannelDemultiplexer(Selector selector, ServerSocketChannel server) {
		this.selector = selector;
		this.server = server;
	}

	public void initialize(InetSocketAddress address) throws IOException {
		server.configureBlocking(CHANNEL_BLOCKING_MODE);
		server.register(selector, SelectionKey.OP_ACCEPT);
		server.bind(address);
	}

	@Override
	public void run() {
		try {
			selector.selectNow();
		} catch (IOException exception) {
			exception.printStackTrace(System.out);
		}
		Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
		while (iterator.hasNext()) {

			SelectionKey token = iterator.next();
			if (token.isValid()) {
				if (token.isAcceptable()) {
					try {
						final SocketChannel socket = server.accept();
						if (socket == null)
							return;
						socket.configureBlocking(CHANNEL_BLOCKING_MODE);

						final SelectionKey register = socket.register(selector, SelectionKey.OP_READ);
						final ClientSession session = new ClientSession(register, socket, this);
						register.attach(session);

						accept(session);
					} catch (IOException exception) {
						exception.printStackTrace(System.out);
					}
				} else if (token.isReadable()) {
					final ClientSession session = (ClientSession) token.attachment();
					if (session == null)
						return;
					session.read();
				}
			}
			iterator.remove();
		}
	}
}