/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.app.util.bin.format.pdb2.pdbreader;

import java.io.IOException;
import java.io.Writer;

/**
 * This class is the version of {@link SectionContribution} for Microsoft v2.00 PDB.
 */
public class SectionContribution200 extends SectionContribution {

	//==============================================================================================
	// Abstract Methods
	//==============================================================================================
	@Override
	void deserialize(PdbByteReader reader) throws PdbException {
		isect = reader.parseUnsignedShortVal();
		offset = reader.parseInt();
		length = reader.parseInt();
		imod = reader.parseUnsignedShortVal();
	}

	@Override
	void dumpInternals(Writer writer) throws IOException {
		writer.write("isect: " + isect);
		writer.write("\noffset: " + offset);
		writer.write("\nlength: " + length);
		writer.write("\nimod: " + imod);
		writer.write("\n");
	}

}
