package test;

import java.nio.ByteBuffer;

import com.vicc.net.ClientSession;
import com.vicc.net.codec.MessageEncoder;

public class TestMessageEncoder implements MessageEncoder<TestMessageResponse> {

    @Override
    public ByteBuffer encode(ClientSession session, TestMessageResponse response) {
        final ByteBuffer buffer = ByteBuffer.allocate(Byte.BYTES + Byte.BYTES);
        buffer.put((byte) response.getApples());
        buffer.put((byte) response.getOranges());
        return buffer;
    }
}