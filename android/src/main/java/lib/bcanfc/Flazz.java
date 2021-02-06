package lib.bcanfc;

import org.simalliance.openmobileapi.util.ISO7816;

public class Flazz
{
 // public static final byte[] FlazzAPDUSelectDF = { 0, -92, 4, 0, 11, -96, 0, 0, 0, 24, 15, 0, 0, 1, -128, 1 };
 public static final byte[] FlazzAPDUSelectDF = new byte[]{(byte) 0, ISO7816.INS_SELECT, (byte) 4, (byte) 0, (byte) 11, ISO7816.INS_SEARCH_BINARY_A0, (byte) 0, (byte) 0, (byte) 0, (byte) 24, ISO7816.INS_ERASE_BINARY_0F, (byte) 0, (byte) 0, (byte) 1, Byte.MIN_VALUE, (byte) 1};
}
