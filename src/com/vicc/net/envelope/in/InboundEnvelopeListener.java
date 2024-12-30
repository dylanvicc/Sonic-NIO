package com.vicc.net.envelope.in;

import com.vicc.net.ClientSession;

public interface InboundEnvelopeListener {

	/**
	 * Listens for an {@link InboundEnvelope} that was received for a
	 * {@link ClientSession}. A {@link MessageDecoder} should delegate packets to
	 * this listener to separate application logic and network logic.
	 * 
	 * @param session The {@link ClientSession} that received the envelope.
	 *                Identified by a {@link SelectionKey} registration token by the
	 *                {@link ChannelDemultipelxer}.
	 * 
	 * @param packet  The {@link InboundEnvelope} that was received. Contains the
	 *                byte pay-load, opcode, and byte data length.
	 * 
	 * @return Denotes the success or failure of the implementation. Returns either
	 *         <code>true</code> or <code>false</code>
	 */
	boolean onInboundEnvelope(ClientSession session, InboundEnvelope packet);
}