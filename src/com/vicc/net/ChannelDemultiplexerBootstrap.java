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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ChannelDemultiplexerBootstrap {

   /**
    * The default execution rate that this bootstrap will schedule for.
    */
   public static final int DEFAULT_EXECUTION_RATE = 30;

   private ScheduledExecutorService executor;
   private ChannelDemultiplexer demultiplexer;

   public ChannelDemultiplexerBootstrap(ScheduledExecutorService executor, ChannelDemultiplexer demultiplexer) {
      this.executor = executor;
      this.demultiplexer = demultiplexer;
   }

   public ScheduledFuture<?> initialize(InetSocketAddress address) {
      try {
         demultiplexer.initialize(address);
      } catch (IOException exception) {
         exception.printStackTrace(System.out);
      }
      return executor.scheduleAtFixedRate(demultiplexer, 0, DEFAULT_EXECUTION_RATE, TimeUnit.MILLISECONDS);
   }

   public ScheduledExecutorService getExecutor() {
      return executor;
   }

   public void setExecutor(ScheduledExecutorService executor) {
      this.executor = executor;
   }

   public ChannelDemultiplexer getDemultiplexer() {
      return demultiplexer;
   }

   public void setDemultiplexer(ChannelDemultiplexer demultiplexer) {
      this.demultiplexer = demultiplexer;
   }
}