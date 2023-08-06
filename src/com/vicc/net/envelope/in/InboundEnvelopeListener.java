/**
 * Copyright (C) 2021 Dylan Vicchiarelli
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.vicc.net.envelope.in;

import com.vicc.net.ClientSession;

public interface InboundEnvelopeListener {

   /**
    * Listens for a single {@link InboundEnvelope} that was received for a
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