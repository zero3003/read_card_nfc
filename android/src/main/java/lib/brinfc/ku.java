package lib.brinfc;

import androidx.annotation.NonNull;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ku
{
  private String kua;
  private String kub;
  private String kuc;
  private String kud;
  @NonNull
  private String kue = "";
  @NonNull
  private String kuf = "";

  @NonNull
  private String kustringb()
  {
    return "0000000000000000";
  }

  @NonNull
  private String kustringb(@NonNull String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString.substring(2, 16));
    localStringBuilder.append(paramString.substring(0, 2));
    return localStringBuilder.toString();
  }

  @NonNull
  private String kustringc()
  {
    try
    {
      String str = kustringa(kustringa(stringToByteArray("8DC0DC40FE1DC582CF7099E2AACFBC10"), "C152153D5807784C721A433B5B59636D", kustringb())).substring(0, 32);
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
 //   return "";
  }

  @NonNull
  private String kustringd()
  {
    try
    {
      String str = kustringa(kustringa(stringToByteArray("3C37029CA595FE4E7E62FCB2F7909B2C"), "C152153D5807784C721A433B5B59636D", kustringb())).substring(0, 32);
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    //return "";
  }

  private void kustringe()
  {
    try
    {
      this.kub = kustringa(kustringb(this.kua, kustringc(), kustringb())).substring(0, 32);
      return;
    }
    catch (Exception localException) {}
  }

  private void kustringf()
  {
    try
    {
      this.kue = kustringa(kustringb(this.kub, kustringd(), this.kuc));
      return;
    }
    catch (Exception localException) {}
  }

  @NonNull
  public static byte[] stringToByteArray(@NonNull String paramString)
  {
    int k = paramString.length();
    byte[] arrayOfByte = new byte[k / 2];
    int j;
    for (int i = 0; i < k - 1; i = j)
    {
      int m = i / 2;
      j = i + 2;
      arrayOfByte[m] = ((byte)(Integer.parseInt(paramString.substring(i, j), 16) & 0xFF));
    }
    return arrayOfByte;
  }

  @NonNull
  public String kustringa()
  {
    try
    {
      String str1 = kustringb(kustringa(kustringa(stringToByteArray(kustringa(kustringc(this.kud, this.kue))), stringToByteArray(kustringb()))));
      String str2 = kustringa(kustringc(kustringa(kustringa(stringToByteArray("1122334455667788"), stringToByteArray(this.kud))), this.kue));
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str2);
      localStringBuilder.append(kustringa(kustringc(kustringa(kustringa(stringToByteArray(str1), stringToByteArray(str2))), this.kue)));
      str1 = localStringBuilder.toString();
      return str1;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
   // return "";
  }

  @NonNull
  public String kustringa(@NonNull byte[] paramArrayOfByte)
  {
    String str = "";
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      Object localObject2 = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      Object localObject1 = localObject2;
      if (((String)localObject2).length() == 1)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("0");
        ((StringBuilder)localObject1).append((String)localObject2);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(str);
      ((StringBuilder)localObject2).append((String)localObject1);
      str = ((StringBuilder)localObject2).toString();
      i += 1;
    }
    return str.toUpperCase();
  }

  public void kustringa(String paramString1, String paramString2, String paramString3)
  {
    this.kua = paramString1;
    this.kuc = paramString2;
    this.kud = paramString3;
    kustringe();
    kustringf();
  }

  public byte[] kustringa(@NonNull String paramString1, @NonNull String paramString2)
  {
    SecretKeySpec paramString22 = new SecretKeySpec(stringToByteArray(paramString2), "DES");
    try
    {
      Cipher localCipher = Cipher.getInstance("DES/ECB/Nopadding");
      localCipher.init(1, paramString22);
      byte[] paramString11 = localCipher.doFinal(stringToByteArray(paramString1));
      return paramString11;
    }
    catch (Exception e)
    {
      for (;;) {}
    }
//    return null;
  }

  public byte[] kustringa(byte[] paramArrayOfByte, @NonNull String paramString1, @NonNull String paramString2)
  {
    Object localObject;
    byte[] paramString11 = new byte[0];
    if (paramString1.length() == 48)
    {
     paramString11 = stringToByteArray(paramString1);
    }
    else if (paramString1.length() == 32)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString1);
      ((StringBuilder)localObject).append(paramString1.substring(0, 16));
      paramString11 = stringToByteArray(((StringBuilder)localObject).toString());
    }
    else if (paramString1.length() == 16)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString1);
      ((StringBuilder)localObject).append(paramString1);
      ((StringBuilder)localObject).append(paramString1);
      paramString11 = stringToByteArray(((StringBuilder)localObject).toString());
    }
    else
    {
      paramString11 = stringToByteArray("00000000000000000000000000000000");
    }
    SecretKeySpec paramString111 = new SecretKeySpec(paramString11, "DESede");
    IvParameterSpec paramString22 = new IvParameterSpec(stringToByteArray(paramString2));
    try
    {
      localObject = Cipher.getInstance("DESede/CBC/Nopadding");
      ((Cipher)localObject).init(2, paramString111, paramString22);
      paramArrayOfByte = ((Cipher)localObject).doFinal(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (Exception e)
    {
      for (;;) {}
    }
   // return null;
  }

  @NonNull
  public byte[] kustringa(@NonNull byte[] paramArrayOfByte1, @NonNull byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte = new byte[paramArrayOfByte1.length];
    if (paramArrayOfByte2.length == 0) {
      throw new IllegalArgumentException("empty security key");
    }
    int j = 0;
    int i = j;
    while (j < paramArrayOfByte1.length)
    {
      arrayOfByte[j] = ((byte)(paramArrayOfByte1[j] ^ paramArrayOfByte2[i]));
      int k = i + 1;
      i = k;
      if (k >= paramArrayOfByte2.length) {
        i = 0;
      }
      j += 1;
    }
    return arrayOfByte;
  }

  public byte[] kustringb(@NonNull String paramString1, @NonNull String paramString2)
  {
    SecretKeySpec paramString22 = new SecretKeySpec(stringToByteArray(paramString2), "DES");
    try
    {
      Cipher localCipher = Cipher.getInstance("DES/ECB/Nopadding");
      localCipher.init(2, paramString22);
      byte[] paramString11 = localCipher.doFinal(stringToByteArray(paramString1));
      return paramString11;
    }
    catch (Exception e)
    {
      for (;;) {}
    }
//    return null;
  }

  public byte[] kustringb(@NonNull String paramString1, @NonNull String paramString2, @NonNull String paramString3)
  {
    Object localObject;
    byte[] paramString22;
    if (paramString2.length() == 48)
    {
       paramString22 = stringToByteArray(paramString2);
    }
    else if (paramString2.length() == 32)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString2);
      ((StringBuilder)localObject).append(paramString2.substring(0, 16));
      paramString22 = stringToByteArray(((StringBuilder)localObject).toString());
    }
    else if (paramString2.length() == 16)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString2);
      ((StringBuilder)localObject).append(paramString2);
      ((StringBuilder)localObject).append(paramString2);
      paramString22 = stringToByteArray(((StringBuilder)localObject).toString());
    }
    else
    {
      paramString22 = stringToByteArray("00000000000000000000000000000000");
    }
    SecretKeySpec paramString222 = new SecretKeySpec(paramString22, "DESede");
    IvParameterSpec paramString33 = new IvParameterSpec(stringToByteArray(paramString3));
    try
    {
      localObject = Cipher.getInstance("DESede/CBC/Nopadding");
      ((Cipher)localObject).init(1, paramString222, paramString33);
      byte[] paramString111 = ((Cipher) localObject).doFinal(stringToByteArray(paramString1));
      return paramString111;
    }
    catch (Exception e)
    {
      for (;;) {}
    }
//    return null;
  }

  @NonNull
  public byte[] kustringc(@NonNull String paramString1, @NonNull String paramString2)
  {
    String str = paramString2.substring(0, 16);
    paramString2 = paramString2.substring(16, 32);
    byte[] paramString11=stringToByteArray("");
    try
    {
       paramString11 = stringToByteArray(kustringa(kustringb(kustringa(kustringa(kustringa(kustringb(paramString1, str)), paramString2)), str)));
      return paramString11;
    }
    catch (Exception e)
    {
      for (;;) {}
    }
   // return paramString11;
  }
}

