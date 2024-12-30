package com.vicc.net.envelope.in.impl;

import com.vicc.net.ClientSession;
import com.vicc.net.envelope.in.InboundEnvelope;
import com.vicc.net.envelope.in.InboundEnvelopeListener;
import com.vicc.net.envelope.in.InboundEnvelopeOpcodes;

@InboundEnvelopeOpcodes(opcodes = { 0x8C, 0x8D })
public class DefaultInboundEnvelopeListener implements InboundEnvelopeListener {

	@Override
	public boolean onInboundEnvelope(ClientSession session, InboundEnvelope packet) {
		if (session.isDisconnected())
			return false;

		return packet.getLength() > 0;
	}
}
