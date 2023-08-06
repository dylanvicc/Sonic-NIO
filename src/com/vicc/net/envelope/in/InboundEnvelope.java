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