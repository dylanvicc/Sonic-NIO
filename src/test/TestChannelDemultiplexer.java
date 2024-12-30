package test;

import java.io.IOException;

import com.vicc.net.ChannelDemultiplexer;
import com.vicc.net.ClientSession;

public class TestChannelDemultiplexer extends ChannelDemultiplexer {

	public TestChannelDemultiplexer() throws IOException {
		super();
	}

	@Override
	public void accept(ClientSession session) {
		System.out.println("Accepted a new connection from " + session.getSocket() + ".");

		session.setDecoder(new TestMessageDecoder());
		session.setEncoder(new TestMessageEncoder());
	}

	@Override
	public void disconnect(ClientSession session) {
		session.setDisconnected(true);
		System.out.println("Removed an existing connection for " + session.getSocket() + ".");
	}
}
