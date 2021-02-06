package lib.bmandirinfc;

import org.simalliance.openmobileapi.util.ISO7816;

public class EMoney
{
//  public static final byte[] EMoneyAPDUSelectDF = { 0, -92, 4, 0, 8, 0, 0, 0, 0, 0, 0, 0, 1 };
//  public static final byte[] EMoneyAPDUforBalance = { 0, -75, 0, 0, 10 };
//  public static final byte[] EMoneyAPDUforNumber = { 0, -77, 0, 0, 63 };
public static final byte[] EMoneyAPDUSelectDF = new byte[]{(byte) 0, ISO7816.INS_SELECT, (byte) 4, (byte) 0, (byte) 8, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 1};
  public static final byte[] EMoneyAPDUforBalance = new byte[]{(byte) 0, (byte) -75, (byte) 0, (byte) 0, (byte) 10};
  public static final byte[] EMoneyAPDUforNumber = new byte[]{(byte) 0, ISO7816.INS_READ_RECORD_B3, (byte) 0, (byte) 0, (byte) 63};
}
