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

import java.io.*;

/**
 * This class represents Segment Map Description component of a PDB file.  This class is only
 *  suitable for reading; not for writing or modifying a PDB.
 *  <P>
 *  We have intended to implement according to the Microsoft PDB API (source); see the API for
 *   truth.
 */
public class SegmentMapDescription {

	private int flags;
	private int ovl;
	private int group;
	private int frame;
	private int segNameIndex;
	private int classNameIndex;
	private long segOffset;
	private long segLength;

	/**
	 * Returns the {@link SegmentMapDescription} flags.
	 * @return the flags.
	 */
	public int getFlags() {
		return flags;
	}

	/**
	 * Returns the {@link SegmentMapDescription} ovl (overlay?).
	 * @return the ovl.
	 */
	public int getOvl() {
		return ovl;
	}

	/**
	 * Returns the {@link SegmentMapDescription} group.
	 * @return the group.
	 */
	public int getGroup() {
		return group;
	}

	/**
	 * Returns the {@link SegmentMapDescription} frame.
	 * @return the frame.
	 */
	public int getFrame() {
		return frame;
	}

	/**
	 * Returns the {@link SegmentMapDescription} segNameIndex.
	 * @return the segNameIndex.
	 */
	public int getSegNameIndex() {
		return segNameIndex;
	}

	/**
	 * Returns the {@link SegmentMapDescription} classNameIndex.
	 * @return the classNameIndex.
	 */
	public int getClassNameIndex() {
		return classNameIndex;
	}

	/**
	 * Returns the {@link SegmentMapDescription} segment offset.
	 * @return the segment offset.
	 */
	public long getOffset() {
		return segOffset;
	}

	/**
	 * Returns the segment offset.
	 * @return The offset of the segment.
	 */
	public long getSegmentOffset() {
		return segOffset;
	}

	/**
	 * Returns the {@link SegmentMapDescription} segment length.
	 * @return The length of the segment.
	 */
	public long getLength() {
		return segLength;
	}

	/**
	 * Deserializes the {@link SegmentMapDescription}.
	 * @param substreamReader {@link PdbByteReader} from which to deserialize the data.
	 * @throws PdbException Upon not enough data left to parse.
	 */
	void deserialize(PdbByteReader substreamReader) throws PdbException {
		flags = substreamReader.parseUnsignedShortVal();
		ovl = substreamReader.parseUnsignedShortVal();
		group = substreamReader.parseUnsignedShortVal();
		frame = substreamReader.parseUnsignedShortVal();
		segNameIndex = substreamReader.parseUnsignedShortVal();
		classNameIndex = substreamReader.parseUnsignedShortVal();
		segOffset = substreamReader.parseUnsignedIntVal();
		segLength = substreamReader.parseUnsignedIntVal();
	}

	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			dump(writer);
			return writer.toString();
		}
		catch (IOException e) {
			return "Issue in " + getClass().getSimpleName() + " toString(): " + e.getMessage();
		}
	}

	/**
	 * Dumps the {@link SegmentMapDescription} to writer.  This method is for debugging only
	 * @param writer the writer
	 * @throws IOException upon issue with writing to the writer
	 */
	void dump(Writer writer) throws IOException {
		PdbReaderUtils.dumpHead(writer, this);
		writer.write(String.format("\nflags: 0x%04x", flags));
		writer.write("\novl: " + ovl);
		writer.write("\ngroup: " + group);
		writer.write("\nframe: " + frame);
		writer.write("\nsegNameIndex: " + segNameIndex);
		writer.write("; classNameIndex: " + classNameIndex);
		writer.write("; segOffset: " + segOffset);
		writer.write("; segLength: " + segLength);
		writer.write("\n");
		PdbReaderUtils.dumpTail(writer, this);
	}

}
