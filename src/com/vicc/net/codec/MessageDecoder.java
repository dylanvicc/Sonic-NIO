package com.vicc.net.codec;

import com.vicc.net.ClientSession;

public interface MessageDecoder {

	boolean decode(ClientSession session);
}
