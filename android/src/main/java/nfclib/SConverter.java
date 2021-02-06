package nfclib;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SConverter
{
  public static final char[] HEX_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  
  @NonNull
  public static String Ascii2Hexchar(@NonNull String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
      char[] paramStringB = paramString.toCharArray();
    int i = 0;
    while (i < paramStringB.length)
    {
      localStringBuilder.append(Integer.toHexString(paramStringB[i]));
      i += 1;
    }
    return localStringBuilder.toString().toUpperCase();
  }
  
  @NonNull
  public static String Byte2Hexchar(@NonNull Byte paramByte)
  {
    return Integer.toHexString(paramByte.byteValue()).toUpperCase();
  }
  
  @NonNull
  public static String Bytes2Hexchar(@NonNull byte[] paramArrayOfByte)
  {
    int i = 0;
    int k = paramArrayOfByte.length;
    char[] arrayOfChar = new char[k * 2];
    int j = 0;
    while (i < k)
    {
      int m = paramArrayOfByte[i];
      int n = j + 1;
      arrayOfChar[j] = HEX_DIGITS[(m >>> 4 & 0xF)];
      j = n + 1;
      arrayOfChar[n] = HEX_DIGITS[(m & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar).toUpperCase();
  }
  
  @NonNull
  public static int[] Bytes2Ints(@NonNull byte[] paramArrayOfByte)
  {
    int i = 0;
    int j = paramArrayOfByte.length;
    int[] arrayOfInt = new int[j];
    while (i < j)
    {
      arrayOfInt[i] = paramArrayOfByte[i];
      i += 1;
    }
    return arrayOfInt;
  }
  
  public static long CalcJulianDay(long paramLong1, long paramLong2, long paramLong3)
  {
    return GregorianDateToJulianDate(paramLong1, paramLong2, paramLong3) - 2444240L;
  }
  
  public static long CalcJulianSecond(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    return paramLong2 * 3600L + paramLong3 * 60L + paramLong4 + paramLong1 * 86400L;
  }
  
  @NonNull
  public static String Double2Hexchar(double paramDouble)
  {
    return Double.toHexString(paramDouble).toUpperCase();
  }
  
  @NonNull
  public static byte[] DoubleHexchars2Bytes(@NonNull String paramString)
  {
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    int i = 0;
    while (i < paramString.length() / 2)
    {
      int j = i * 2;
      arrayOfByte[i] = ((byte)Hexchar2Int(paramString.substring(j, j + 2)));
      i += 1;
    }
    return arrayOfByte;
  }
  
  @NonNull
  public static int[] DoubleHexchars2Int(@NonNull String paramString)
  {
    int[] arrayOfInt = new int[paramString.length() / 2];
    int i = 0;
    while (i < paramString.length() / 2)
    {
      int j = i * 2;
      arrayOfInt[i] = Hexchar2Int(paramString.substring(j, j + 2));
      i += 1;
    }
    return arrayOfInt;
  }
  
  @NonNull
  public static short[] DoubleHexchars2Short(@NonNull String paramString)
  {
    short[] arrayOfShort = new short[paramString.length() / 2];
    int i = 0;
    while (i < paramString.length() / 2)
    {
      int j = i * 2;
      arrayOfShort[i] = ((short)Hexchar2Int(paramString.substring(j, j + 2)));
      i += 1;
    }
    return arrayOfShort;
  }
  
  public static long GregorianDateToJulianDate(long paramLong1, long paramLong2, long paramLong3)
  {
    paramLong1 += 8000L;
    if (paramLong2 < 3L)
    {
      paramLong1 -= 1L;
      paramLong2 += 12L;
    }
    return 365L * paramLong1 + paramLong1 / 4L - paramLong1 / 100L + paramLong1 / 400L - 1200820L + (paramLong2 * 153L + 3L) / 5L - 92L + paramLong3 - 1L;
  }
  
  @NonNull
  public static String Hexchar2Ascii(@NonNull String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
      byte[] paramStringx = Hexchar2Bytes(paramString);
    int i = 0;
    while (i < paramStringx.length)
    {
      localStringBuilder.append((char)paramStringx[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static byte Hexchar2Byte(@NonNull String paramString)
  {
    return Byte.parseByte(paramString, 16);
  }
  
  @NonNull
  public static byte[] Hexchar2Bytes(@NonNull String paramString)
  {
    int k = paramString.length();
    byte[] arrayOfByte = new byte[(k + 1) / 2];
    int i = 0;
    int j = 1;
    if (k % 2 == 1)
    {
      arrayOfByte[0] = ((byte)Hexchar2Decimal(paramString.charAt(0)));
      i = 1;
    }
    else
    {
      j = 0;
    }
    while (i < k)
    {
      int m = i + 1;
      arrayOfByte[j] = ((byte)(Hexchar2Decimal(paramString.charAt(i)) << 4 | Hexchar2Decimal(paramString.charAt(m))));
      j += 1;
      i = m + 1;
    }
    return arrayOfByte;
  }
  
  public static int Hexchar2Decimal(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9')) {
      return paramChar - '0';
    }
    if ((paramChar >= 'A') && (paramChar <= 'F')) {
      return paramChar - 'A' + 10;
    }
    if ((paramChar >= 'a') && (paramChar <= 'f')) {
      return paramChar - 'a' + 10;
    }
    return 0;
  }
  
  public static int Hexchar2Int(@NonNull String paramString)
  {
    return Integer.parseInt(paramString, 16);
  }
  
  public static long Hexchar2Long(@NonNull String paramString)
  {
    return Long.parseLong(paramString, 16);
  }
  
  public static short Hexchar2Short(String paramString)
  {
    return Short.parseShort(paramString, 16);
  }
  
  @NonNull
  public static String Int2Hexchar(int paramInt)
  {
    return Integer.toHexString(paramInt).toUpperCase();
  }
  
  @NonNull
  public static byte[] Ints2Bytes(@NonNull int[] paramArrayOfInt)
  {
    int i = 0;
    int j = paramArrayOfInt.length;
    byte[] arrayOfByte = new byte[j];
    while (i < j)
    {
      arrayOfByte[i] = ((byte)paramArrayOfInt[i]);
      i += 1;
    }
    return arrayOfByte;
  }
  
  @NonNull
  public static String JulianDate(Date paramDate)
  {
      String[] paramDatex = new SimpleDateFormat("yyyy;MM;dd;HH;mm;ss").format(paramDate).split(";");
    long l1 = Long.parseLong(paramDatex[0]);
    long l2 = Long.parseLong(paramDatex[1]);
    long l3 = Long.parseLong(paramDatex[2]);
    long l4 = Long.parseLong(paramDatex[3]);
    long l5 = Long.parseLong(paramDatex[4]);
    long l6 = Long.parseLong(paramDatex[5]);
    return String.format("%8s", new Object[] { Long2Hexchar(CalcJulianSecond(CalcJulianDay(l1, l2, l3), l4, l5, l6)) }).replace(' ', '0').toUpperCase();
  }
  
  @NonNull
  public static String JulianDateTimeToString(@NonNull String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = Hexchar2Int(paramString);
    localStringBuilder.append(JulianDateToString(i / 86400));
    int j = i % 86400;
    i = j / 3600;
    j %= 3600;
    int k = j / 60;
    localStringBuilder.append(String.format("%2s", new Object[] { Integer.valueOf(i) }).replace(' ', '0'));
    localStringBuilder.append(String.format("%2s", new Object[] { Integer.valueOf(k) }).replace(' ', '0'));
    localStringBuilder.append(String.format("%2s", new Object[] { Integer.valueOf(j % 60) }).replace(' ', '0'));
    return localStringBuilder.toString();
  }
  
  @NonNull
  public static String JulianDateToString(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramInt + 2444240 + 68569;
    paramInt = 4 * i / 146097;
    int j = i - (146097 * paramInt + 3) / 4;
    i = 4000 * (j + 1) / 1461001;
    j = j - 1461 * i / 4 + 31;
    int k = 80 * j / 2447;
    int m = 2447 * k / 80;
    int n = k / 11;
    localStringBuilder.append(String.format("%2s", new Object[] { Integer.valueOf(((paramInt - 49) * 100 + i + n) % 100) }).replace(' ', '0'));
    localStringBuilder.append(String.format("%2s", new Object[] { Integer.valueOf(k + 2 - 12 * n) }).replace(' ', '0'));
    localStringBuilder.append(String.format("%2s", new Object[] { Integer.valueOf(j - m) }).replace(' ', '0'));
    return localStringBuilder.toString();
  }
  
  @NonNull
  public static String LeftPad(@NonNull String paramString, int paramInt, char paramChar)
  {
    Object localObject = paramString;
    if (paramString.length() < paramInt)
    {
        char[] localObjectarr = new char[paramInt];
      int m = paramInt - paramString.length();
      int k = 0;
        int i = 0;
        int j = k;
      if (m > 0)
      {
        paramInt = 0;
        for (;;)
        {
          j = k;
          i = paramInt;
          if (paramInt >= m) {
            break;
          }
            localObjectarr[paramInt] = paramChar;
          paramInt += 1;
        }
      }
//        int i = 0;
//        int j = k;
      while (j < paramString.length())
      {
          localObjectarr[i] = paramString.charAt(j);
        i += 1;
        j += 1;
      }
      localObject = new String((char[])localObject);
    }
    return (String)localObject;
  }
  
  @NonNull
  public static String Long2Hexchar(long paramLong)
  {
    return Long.toHexString(paramLong).toUpperCase();
  }
  
  @NonNull
  public static String NibblesRotate(@NonNull String paramString)
  {
    Object localObject = paramString;
    if (paramString.length() % 2 == 1)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("0");
      localObject = ((StringBuilder)localObject).toString();
    }
      StringBuilder paramStringB = new StringBuilder(((String) localObject).length());
    int i = 0;
    while (i < ((String)localObject).length() / 2)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int j = i * 2;
      int k = j + 1;
      localStringBuilder.append(((String)localObject).substring(k, j + 2));
      localStringBuilder.append(((String)localObject).substring(j, k));
        paramStringB.append(localStringBuilder.toString());
      i += 1;
    }
    return paramString.toString();
  }
  
  @NonNull
  public static String Short2Hexchar(short paramShort)
  {
    return Integer.toHexString(paramShort).toUpperCase();
  }
}
