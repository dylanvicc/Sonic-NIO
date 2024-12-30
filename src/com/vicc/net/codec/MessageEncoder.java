package com.vicc.net.codec;

import java.nio.ByteBuffer;

import com.vicc.net.ClientSession;

public interface MessageEncoder<T> {

	ByteBuffer encode(ClientSession session, T message);
}