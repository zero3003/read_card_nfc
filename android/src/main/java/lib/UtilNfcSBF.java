package lib;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.simalliance.openmobileapi.util.ISO7816;

public class UtilNfcSBF {
  private static final byte[] HEX_CHAR_TABLE = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, ISO7816.INS_GENERATE_ASYMMETRIC_KEY_PAIR};
  private static final String TAG = "CardReader";

//
//
//  public static byte[] StringHexToByteArray()
//  {
//    return null;
//  }
//
//  public static String getHexString(byte[] paramArrayOfByte)
//  {
//    return getHexString(paramArrayOfByte, paramArrayOfByte.length);
//  }
//
//  public static String getHexString(byte[] paramArrayOfByte, int paramInt)
//  {
//    byte[] arrayOfByte = new byte[paramInt * 2];
//    int j = 0;
//    int m = paramArrayOfByte.length;
//    int k = 0;
//    int i = k;
//    while (j < m)
//    {
//      int n = paramArrayOfByte[j];
//      if (k >= paramInt) {
//        break;
//      }
//      k += 1;
//      n &= 0xFF;
//      int i1 = i + 1;
//      arrayOfByte[i] = HEX_CHAR_TABLE[(n >>> 4)];
//      i = i1 + 1;
//      arrayOfByte[i1] = HEX_CHAR_TABLE[(n & 0xF)];
//      j += 1;
//    }
//    return new String(arrayOfByte);
//  }
//
//  public static byte[] tapcashgo2a_a(String paramString)
//  {
//    int j = paramString.length();
//    byte[] arrayOfByte = new byte[j / 2];
//    int i = 0;
//    while (i < j)
//    {
//      arrayOfByte[(i / 2)] = ((byte)((Character.digit(paramString.charAt(i), 16) << 4) + Character.digit(paramString.charAt(i + 1), 16)));
//      i += 2;
//    }
//    return arrayOfByte;
//  }
//
//  public static byte[] mergeArray(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
//  {
//    int i = paramArrayOfByte1.length;
//    int j = paramArrayOfByte2.length;
//    byte[] arrayOfByte = new byte[i + j];
//    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, 0, i);
//    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, i, j);
//    return arrayOfByte;
//  }
//
//  public static boolean validApduResponse(byte[] paramArrayOfByte)
//  {
//    int j = 0;
//    int i = paramArrayOfByte[(paramArrayOfByte.length - 2)];
//    int k = paramArrayOfByte[(paramArrayOfByte.length - 1)];
//    if ((i != -112) && (i != -111)) {
//      i = 0;
//    } else {
//      i = 1;
//    }
//    if (k == 0) {
//      j = 1;
//    }
//    return (i & j) > 0;
//  }
//}


  @Nullable
  public static byte[] StringHexToByteArray() {
    return null;
  }

  public static boolean validApduResponse(@NonNull byte[] paramArrayOfByte) {
    int j = 0;
    int i = paramArrayOfByte[(paramArrayOfByte.length - 2)];
    int k = paramArrayOfByte[(paramArrayOfByte.length - 1)];
    if ((i != -112) && (i != -111)) {
      i = 0;
    } else {
      i = 1;
    }
    if (k == 0) {
      j = 1;
    }
    return (i & j) > 0;
//    int i;
//    int i2 = 0;
//    byte c = bArr[bArr.length - 2];
//    bArr = bArr[bArr.length - 1];
//    if (c != (byte) -112) {
//      if (c != (byte) -111) {
//        i = 0;
//        if (bArr == null) {
//          i2 = 1;
//        }
//        return i & i2;
//      }
//    }
//    i = 1;
//    if (bArr == null) {
//      i2 = 1;
//    }
//    return i & i2;
  }


  @NonNull
  public static String getHexString(@NonNull byte[] bArr) {
    return getHexString(bArr, bArr.length);
  }

  @NonNull
  public static String getHexString(@NonNull byte[] bArr, int i) {
    byte[] bArr2 = new byte[(i * 2)];
    int i2 = 0;
    int length = bArr.length;
    int i3 = 0;
    int i4 = i3;
    while (i2 < length) {
      byte b = bArr[i2];
      if (i3 >= i) {
        break;
      }
      i3++;
      int i5 = b & 0xFF;
      int i6 = i4 + 1;
      bArr2[i4] = HEX_CHAR_TABLE[i5 >>> 4];
      i4 = i6 + 1;
      bArr2[i6] = HEX_CHAR_TABLE[i5 & 0xF];
      i2++;
    }
    return new String(bArr2);
  }

  @NonNull
  public static byte[] mergeArray(@NonNull byte[] bArr, @NonNull byte[] bArr2) {
    int length = bArr.length;
    int length2 = bArr2.length;
    byte[] obj = new byte[(length + length2)];
    System.arraycopy(bArr, 0, obj, 0, length);
    System.arraycopy(bArr2, 0, obj, length, length2);
    return obj;
  }

  @NonNull
  public static byte[] m16a(@NonNull String str) {
    int length = str.length();
    byte[] bArr = new byte[(length / 2)];
    for (int i = 0; i < length; i += 2) {
      bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
    }
    return bArr;
  }
}