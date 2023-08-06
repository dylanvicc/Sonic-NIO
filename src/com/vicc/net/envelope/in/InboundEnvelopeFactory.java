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

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import com.vicc.net.ClientSession;
import com.vicc.net.envelope.in.impl.DefaultInboundEnvelopeListener;

public class InboundEnvelopeFactory {

   /**
    * The {@link InboundEnvelopeListener}(s) that have been registered. Identified
    * by opcode which points to their position in this array.
    */
   private final InboundEnvelopeListener[] listeners = new InboundEnvelopeListener[256];

   /**
    * Registers a new {@link InboundEnvelopeListener}. Intrinsically locks the
    * array of memory to guarantee thread safety.
    * 
    * @param opcode   The opcode of interest.
    * @param listener The {@link InboundEnvelopeListener} to register.
    */
   public void register(int opcode, InboundEnvelopeListener listener) {
      synchronized (listeners) {
         listeners[opcode] = listener;
      }
   }

   /**
    * Receives an in-bound envelope of data and delegates it to the proper
    * {@link InboundEnvelopeListener}.
    * 
    * @param session  The {@link ClientSession} that received this envelope of
    *                 data.
    * @param envelope The in-bound envelope of data that was received.
    */
   public void receive(ClientSession session, InboundEnvelope envelope) {
      final int opcode = envelope.getOpcode();
      if (opcode == -1)
         return;
      listeners[opcode].onInboundEnvelope(session, envelope);
   }

   /**
    * Creates a new instance of this class file and registers the appropriate
    * listeners via annotation.
    * 
    * @throws Exception The exception thrown when a class can't be located or a
    *                   method fails to instantiate.
    * @path The path to the package that houses the listener implementations.
    *       Iterates over each class file and registers the appropriate opcodes
    *       from that class's annotation. Avoids third party libraries to remain
    *       lightweight and dependency independent.
    */
   @SuppressWarnings("unchecked")
   public InboundEnvelopeFactory(String path) throws Exception {
      for (int it = 0; it < listeners.length; it++) {
         listeners[it] = new DefaultInboundEnvelopeListener();
      }
      
      final List<Class<InboundEnvelopeListener>> commands = new ArrayList<Class<InboundEnvelopeListener>>();

      final File[] files = new File(Thread.currentThread().getContextClassLoader().getResource(path.replace(".", "/")).getFile())
            .listFiles(new FilenameFilter() {
               public boolean accept(File directory, String name) {
                  return name.endsWith(".class");
               }
            });
      for (File file : files) {
         final Class<?> clazz = Class.forName(path + "." + file.getName().replaceAll(".class$", ""));

         if (InboundEnvelopeListener.class.isAssignableFrom(clazz))
            commands.add((Class<InboundEnvelopeListener>) clazz);
      }
      for (Class<?> clazz : commands) {
         final InboundEnvelopeOpcodes opcodes = (InboundEnvelopeOpcodes) clazz.getAnnotation(InboundEnvelopeOpcodes.class);

         for (int opcode : opcodes.opcodes()) {
            register(opcode, (InboundEnvelopeListener) clazz.getDeclaredConstructor().newInstance());
         }
      }
   }

   public InboundEnvelopeListener[] getListeners() {
      return listeners;
   }
}