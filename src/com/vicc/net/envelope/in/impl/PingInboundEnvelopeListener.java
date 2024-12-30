package com.vicc.net.envelope.in.impl;

import java.nio.ByteBuffer;

import com.vicc.net.ClientSession;
import com.vicc.net.envelope.in.InboundEnvelope;
import com.vicc.net.envelope.in.InboundEnvelopeListener;
import com.vicc.net.envelope.in.InboundEnvelopeOpcodes;

@InboundEnvelopeOpcodes(opcodes = { 0x8E })
public class PingInboundEnvelopeListener implements InboundEnvelopeListener {

    @Override
    public boolean onInboundEnvelope(ClientSession session, InboundEnvelope packet) {
        if (session.isDisconnected())
            return false;

        final ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE);
        buffer.put((byte) 0);
        session.write(buffer);

        return true;
    }
}
