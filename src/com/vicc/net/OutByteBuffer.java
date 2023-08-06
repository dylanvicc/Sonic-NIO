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
package com.vicc.net;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class OutByteBuffer {

   /**
    * The maximum value of a byte.
    */
   public static final int BYTE_CAPACITY = 255;

   /**
    * The amount of values that a byte can store.
    */
   public static final int BYTE_VALUES = 256;

   /**
    * The amount of bits in a byte.
    */
   public static final int BITS_IN_A_BYTE = 8;

   /**
    * A unit of digital information in computing and telecommunications that
    * consists of eight bits.
    */
   public static final int OCTET_LENGTH = 8;

   /**
    * The array of bytes.
    */
   private ByteBuffer buffer;

   public OutByteBuffer(int capacity) {
      this(ByteBuffer.allocate(capacity));
   }

   public OutByteBuffer(ByteBuffer buffer) {
      this.buffer = buffer;
   }

   public OutByteBuffer writeByte(int value) {
      buffer.put((byte) value);
      return this;
   }

   public OutByteBuffer writeByteC(int value) {
      buffer.put((byte) -value);
      return this;
   }

   public OutByteBuffer writeInt(int value) {
      buffer.putInt(value);
      return this;
   }

   public OutByteBuffer writeLong(long value) {
      buffer.putLong(value);
      return this;
   }

   public OutByteBuffer writeShort(int value) {
      buffer.putShort((short) value);
      return this;
   }

   public OutByteBuffer writeByteA(int value) {
      buffer.put((byte) (value + (BITS_IN_A_BYTE * 16)));
      return this;
   }

   public OutByteBuffer writeByteS(int value) {
      buffer.put((byte) ((BITS_IN_A_BYTE * 16) - value));
      return this;
   }

   public OutByteBuffer writeLEShort(int value) {
      buffer.put((byte) value);
      buffer.put((byte) (value >> BITS_IN_A_BYTE));
      return this;
   }

   public OutByteBuffer writeLEShortA(int value) {
      buffer.put((byte) (value + (BITS_IN_A_BYTE * 16)));
      buffer.put((byte) (value >> BITS_IN_A_BYTE));
      return this;
   }

   public OutByteBuffer writeBEInt(int value) {
      buffer.put((byte) (value >> (BITS_IN_A_BYTE * 2)));
      buffer.put((byte) (value >> (BITS_IN_A_BYTE * 3)));
      buffer.put((byte) value);
      buffer.put((byte) (value >> (BITS_IN_A_BYTE * 1)));
      return this;
   }

   public OutByteBuffer writeInvInteger(int value) {
      buffer.put((byte) (value >> (BITS_IN_A_BYTE * 1)));
      buffer.put((byte) (value));
      buffer.put((byte) (value >> (BITS_IN_A_BYTE * 3)));
      buffer.put((byte) (value >> (BITS_IN_A_BYTE * 2)));
      return this;
   }

   public OutByteBuffer writeShortA(int value) {
      buffer.put((byte) (value >> BITS_IN_A_BYTE));
      buffer.put((byte) (value + (BITS_IN_A_BYTE * 16)));
      return this;
   }

   public OutByteBuffer writeString(String string, int termination) {
      buffer.put(string.getBytes(StandardCharsets.US_ASCII));
      buffer.put((byte) termination);
      return this;
   }

   public OutByteBuffer writeBytes(byte[] source) {
      buffer.put(source);
      return this;
   }

   public int position() {
      return buffer.position();
   }

   public int limit() {
      return buffer.limit();
   }

   public void flip() {
      buffer.flip();
   }

   public ByteBuffer getBuffer() {
      return buffer;
   }
}