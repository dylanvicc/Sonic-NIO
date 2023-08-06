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
package test;

import java.nio.ByteBuffer;

import com.vicc.net.ClientSession;
import com.vicc.net.codec.MessageEncoder;

public class TestMessageEncoder implements MessageEncoder<TestMessageResponse> {

   @Override
   public ByteBuffer encode(ClientSession session, TestMessageResponse response) {
      ByteBuffer buffer = ByteBuffer.allocate(Byte.BYTES + Byte.BYTES);
      buffer.put((byte) response.getApples());
      buffer.put((byte) response.getOranges());
      return buffer;
   }
}