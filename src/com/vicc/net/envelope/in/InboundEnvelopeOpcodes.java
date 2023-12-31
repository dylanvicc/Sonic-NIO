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

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InboundEnvelopeOpcodes {

   /**
    * The opcodes that an individual {@link InboundEnvelopeListener} implementation
    * will handle. Processed at runtime.
    * 
    * @return The opcodes of interest. Represented as thirty-two bit integers.
    */
   int[] opcodes();
}