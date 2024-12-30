package test;

import com.vicc.net.ClientSession;
import com.vicc.net.codec.MessageDecoder;

public class TestMessageDecoder implements MessageDecoder {

	@Override
	public boolean decode(ClientSession session) {
		final int opcode = session.getBuffer().get() & 0xFF;
		System.out.println("Read an opcode of " + opcode + ".");

		session.encode(new TestMessageResponse(10, 10));
		return true;
	}
}