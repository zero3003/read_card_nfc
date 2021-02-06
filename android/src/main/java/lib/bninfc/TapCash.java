package lib.bninfc;

import android.nfc.tech.IsoDep;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.simalliance.openmobileapi.util.ISO7816;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import lib.UtilNfcSBF;

//import lib.bninfc.tapcashgo.card.tapcash.TAPCASHTransaction;

public class TapCash
{

private static final String TAG = "CardReader";
//  public static final byte[] TapCashAPDUSelectDF = { 0, -92, 4, 0, 8, -96, 0, 66, 78, 73, 16, 0, 1 };
//  private byte[] commandAPDUSelectDF_TapCash2 = { 0, -92, 4, 0, 8, -96, 0, 66, 78, 73, -103, -103, -103 };
public static final byte[] TapCashAPDUSelectDF = new byte[]{(byte) 0, ISO7816.INS_SELECT, (byte) 4, (byte) 0, (byte) 8, ISO7816.INS_SEARCH_BINARY_A0, (byte) 0, (byte) 66, (byte) 78, (byte) 73, (byte) 16, (byte) 0, (byte) 1};
  @NonNull
  private byte[] commandAPDUSelectDF_TapCash2 = new byte[]{(byte) 0, ISO7816.INS_SELECT, (byte) 4, (byte) 0, (byte) 8, ISO7816.INS_SEARCH_BINARY_A0, (byte) 0, (byte) 66, (byte) 78, (byte) 73, (byte) -103, (byte) -103, (byte) -103};
  private byte[] TapCashbytef;
  private long mBalance;
  private String mCardNumber;
  private IsoDep mIsoDep;
  private String mTagId;

  public TapCash(IsoDep paramIsoDep)
  {
    this.mIsoDep = paramIsoDep;
  }

  @Nullable
  private byte[] TapCashbytea(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4)
  {
    try
    {
      Log.d(TAG, "TapCashbytea: try");
      byte[] paramArrayOfBytex = TapCashbyteb(paramByte1, paramByte2, paramByte3, paramByte4, null);
      Log.d(TAG, "TapCashbytea: after try");
//      byte[] paramArrayOfBytec = this.mIsoDep.transceive(paramArrayOfBytex);
//      return paramArrayOfBytec;
      return this.mIsoDep.transceive(paramArrayOfBytex);
    }
    catch (IOException e)
    {
     // for (;;) {}
      Log.d(TAG, "TapCashbytea: "+e.toString());
      return null;
    }

  }

  private byte[] TapCashbyteb(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte[] paramArrayOfByte)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    localByteArrayOutputStream.write(-112);
    localByteArrayOutputStream.write(paramByte1);
    localByteArrayOutputStream.write(paramByte2);
    localByteArrayOutputStream.write(paramByte3);
    localByteArrayOutputStream.write(paramByte4);
   // if (paramArrayOfByte != null) {}
//    if(paramArrayOfByte == null) {
//      try {
//
//        Log.d(TAG, "TapCashbyteb: try");
//
//
//      } catch (IOException e) {
//        Log.d(TAG, "TapCashbyteb catch: " + e.toString());
//        //  for (;;) {}
//        // return localByteArrayOutputStream.toByteArray();
//      }
//    }
    return localByteArrayOutputStream.toByteArray();
  }

  public void explodeCard(int paramInt, @Nullable byte[] paramArrayOfByte)
  {
    int i = 0;
    boolean tapCashboolean_s;
    String tapCashstring_t;
    if (paramArrayOfByte == null)
    {
      paramArrayOfByte = new byte[''];
      tapCashboolean_s = false;
      tapCashstring_t = "";
    }
    else
    {
      tapCashboolean_s = true;
      tapCashstring_t = "";
    }
    int tapCashint_a = paramInt;
    byte tapCashbyte_b = paramArrayOfByte[0];
    byte tapCashbyte_c = paramArrayOfByte[1];
    int j = paramArrayOfByte[2] << 16 & 0xFF0000 | paramArrayOfByte[3] << 8 & 0xFF00 | paramArrayOfByte[4] & 0xFF;
    paramInt = j;
    if ((paramArrayOfByte[2] & 0x80) != 0) {
      paramInt = j | 0xFF000000;
    }
    int tapCashint_d = paramInt;
    j = paramArrayOfByte[5] << 16 & 0xFF0000 | paramArrayOfByte[6] << 8 & 0xFF00 | paramArrayOfByte[7] & 0xFF;
    paramInt = j;
    if ((paramArrayOfByte[5] & 0x80) != 0) {
      paramInt = j | 0xFF000000;
    }
    int tapCashint_e = paramInt;
    this.TapCashbytef = new byte[8];
    paramInt = 0;
    while (paramInt < this.TapCashbytef.length)
    {
      this.TapCashbytef[paramInt] = paramArrayOfByte[(paramInt + 8)];
      paramInt += 1;
    }
    byte[] tapCashbyteg = new byte[8];
    paramInt = 0;
    while (paramInt < tapCashbyteg.length)
    {
      tapCashbyteg[paramInt] = paramArrayOfByte[(paramInt + 16)];
      paramInt += 1;
    }
    int tapCashint_h = ((paramArrayOfByte[24] << 8 & 0xFF00 | paramArrayOfByte[25] << 0 & 0xFF) * 86400 + 788947200);
    int tapCashint_i = (788947200 + 86400 * (paramArrayOfByte[26] << 8 & 0xFF00 | paramArrayOfByte[27] << 0 & 0xFF));
    int tapCashint_j = (paramArrayOfByte[28] << 24 & 0xFF000000 | paramArrayOfByte[29] << 16 & 0xFF0000 | paramArrayOfByte[30] << 8 & 0xFF00 | paramArrayOfByte[31] << 0 & 0xFF);
    byte[] tapCashbytek = new byte[8];
    paramInt = 0;
    while (paramInt < 8)
    {
      tapCashbytek[paramInt] = paramArrayOfByte[(paramInt + 32)];
      paramInt += 1;
    }
    byte tapCashbyte_l = paramArrayOfByte[40];
    byte tapCashbyte_m = paramArrayOfByte[71];
    int tapCashint_n = (paramArrayOfByte[41] & 0xFF);
    int tapCashint_o = (paramArrayOfByte[42] << 24 & 0xFF000000 | paramArrayOfByte[43] << 16 & 0xFF0000 | paramArrayOfByte[44] << 8 & 0xFF00 | paramArrayOfByte[45] << 0 & 0xFF);
    byte[] arrayOfByte = new byte[16];
    paramInt = 0;
    while (paramInt < arrayOfByte.length)
    {
      arrayOfByte[paramInt] = paramArrayOfByte[(paramInt + 46)];
      paramInt += 1;
    }
//    TAPCASHTransaction tapcashTransaction = new TAPCASHTransaction(arrayOfByte);
    byte[] tapCashbyteq = new byte[tapCashint_n];
    paramInt = i;
    while (paramInt < tapCashbyteq.length)
    {
      tapCashbyteq[paramInt] = paramArrayOfByte[(paramInt + 62)];
      paramInt += 1;
    }
    byte tapCashbyte_r = paramArrayOfByte[(tapCashint_n + 62)];
  }

  public long getBalance()
  {
    return this.mBalance;
  }

  public String getCardNumber()
  {
    if (this.mCardNumber == null) {
      this.mCardNumber = "7546 0200 0266 2256";
    }
    return this.mCardNumber;
  }

  public boolean readCard()
  {
//    try
//    {
//
//      byte[] arrayOfByte = this.mIsoDep.transceive(this.commandAPDUSelectDF_TapCash2);
//
//    //  int[] arrayOfByte_;
//      int ix = arrayOfByte[(arrayOfByte.length - 1)];
//      byte[] arrayOfBytex = TapCashbytea((byte) 50, (byte) 3, (byte) 0, (byte) 0);
//
//      int j = arrayOfBytex[2] << 16 & 16711680 | arrayOfBytex[3] << 8 & 65280 | arrayOfBytex[4] & 0xFF;
//      Log.d(TAG, "readCard: try");
//      ix = j;
//
//      if ((arrayOfByte[2] & 0x80) != 0) {
//        ix = j | 0xFF000000;
//      }
//      this.mBalance = ix;
//      this.TapCashbytef = new byte[8];
//     int i1 = 0;
//      while (i1 < this.TapCashbytef.length)
//      {
//        this.TapCashbytef[i1] = arrayOfByte[(i1 + 8)];
//        i1 += 1;
//      }
//      this.mCardNumber = UtilNfcSBF.getHexString(this.TapCashbytef);
//      return true;
//    }
//    catch (IOException ioe) {
//      Log.d(TAG, "readCard: "+ioe.toString());
//      return false;
//    }
//
    try
    {
      byte[] arrayOfByte = this.mIsoDep.transceive(this.commandAPDUSelectDF_TapCash2);
      int i1 = arrayOfByte[(arrayOfByte.length - 1)];
      arrayOfByte = TapCashbytea((byte)50, (byte)3, (byte)0, (byte)0);
      int j = arrayOfByte[2] << 16 & 16711680 | arrayOfByte[3] << 8 & 65280 | arrayOfByte[4] & 255;
      i1 = j;
      if ((arrayOfByte[2] & 128) != 0) {
        i1 = j | -16777216;
      }
      this.mBalance = i1;
      Log.d(TAG, "readCard: "+this.mBalance);
      this.TapCashbytef = new byte[8];
//      i = 0;
//      while (i < this.TapCashbytef.length)
//      {
//        this.TapCashbytef[i] = arrayOfByte[(i + 8)];
//        i += 1;
//      }
      System.arraycopy(arrayOfByte, 8, this.TapCashbytef, 0, this.TapCashbytef.length);
      this.mCardNumber = UtilNfcSBF.getHexString(this.TapCashbytef);
      Log.d(TAG, "readCard: "+this.mCardNumber);
      return true;
    }
    catch (IOException e) { return false;}


 }
}