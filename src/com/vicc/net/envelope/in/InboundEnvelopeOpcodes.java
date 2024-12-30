package com.vicc.net.envelope.in;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InboundEnvelopeOpcodes {

	/**
	 * The opcodes that an individual {@link InboundEnvelopeListener} implementation
	 * will handle.
	 * 
	 * @return The opcodes of interest. Represented as thirty-two bit integers.
	 */
	int[] opcodes();
}