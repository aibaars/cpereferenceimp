// Copyright (c) 2011, The MITRE Corporation
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are
// permitted provided that the following conditions are met:
//
//    * Redistributions of source code must retain the above copyright notice, this list
//      of conditions and the following disclaimer.
//    * Redistributions in binary form must reproduce the above copyright notice, this
//      list of conditions and the following disclaimer in the documentation and/or other
//      materials provided with the distribution.
//    * Neither the name of The MITRE Corporation nor the names of its contributors may be
//      used to endorse or promote products derived from this software without specific
//      prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
// EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
// OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
// SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
// OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
// HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
// TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
// EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.mitre.cpe.naming.util;

import java.text.ParseException;

import org.mitre.cpe.common.WellFormedName;
import org.mitre.cpe.naming.CPENameBinder;
import org.mitre.cpe.naming.CPENameUnbinder;

/**
 * A concrete implementation of a formatted string unbound to a WellFormedName.
 * 
 * @author <a href="mailto:david.waltermire@nist.gov">David Waltermire</a>
 */
public class CPEFormattedStringName extends AbstractCPEName {

	public CPEFormattedStringName(String formattedString) throws ParseException {
		super(CPENameUnbinder.unbindFS(formattedString));
	}

	/**
	 * @return the {@link WellFormedName} bound to a formatted string
	 */
	public String toString() {
		return CPENameBinder.bindToFS(getWellFormedName());
	}

}
