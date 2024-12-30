package com.vicc.net.envelope.in;

import java.nio.ByteBuffer;

/**
 * Represents an in-bound envelope of byte data.
 */
public class InboundEnvelope {

	/**
	 * The numeric identifier for this data envelope.
	 */
	private int opcode;

	/**
	 * The length of the data stored in this envelope.
	 */
	private int length;

	/**
	 * The data stored in this envelope.
	 */
	private ByteBuffer payload;

	public InboundEnvelope(int opcode, int length, ByteBuffer payload) {
		this.opcode = opcode;
		this.length = length;
		this.payload = payload;
	}

	public int getOpcode() {
		return opcode;
	}

	public void setOpcode(int opcode) {
		this.opcode = opcode;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public ByteBuffer getPayload() {
		return payload;
	}

	public void setPayload(ByteBuffer payload) {
		this.payload = payload;
	}
}