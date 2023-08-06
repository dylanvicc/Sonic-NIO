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

import java.io.IOException;

import com.vicc.net.ChannelDemultiplexer;
import com.vicc.net.ClientSession;

public class TestChannelDemultiplexer extends ChannelDemultiplexer {

   public TestChannelDemultiplexer() throws IOException {
      super();
   }

   @Override
   public void accept(ClientSession session) {
      System.out.println("Accepted a new connection from " + session.getSocket() + ".");

      session.setDecoder(new TestMessageDecoder());
      session.setEncoder(new TestMessageEncoder());
   }

   @Override
   public void disconnect(ClientSession session) {
      session.setDisconnected(true);
      System.out.println("Removed an existing connection for " + session.getSocket() + ".");
   }
}
