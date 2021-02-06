package lib.brinfc;

import android.annotation.SuppressLint;
import android.nfc.tech.IsoDep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Brizzi
{
  public static final byte[] BrizziAPDUSelectDF = { -112, 90, 0, 0, 3, 1, 0, 0, 0 };
  private static final String TAG = "CardReader";
  private static final byte[] Brizzib = { -112, 90, 0, 0, 3, 2, 0, 0, 0 };
  private static final byte[] Brizzic = { -112, 90, 0, 0, 3, 3, 0, 0, 0 };
  private static final byte[] Brizzid = { -112, -67, 0, 0, 7, 0, 0, 0, 0, 23, 0, 0, 0 };
  private static final byte[] Brizzie = { -112, -67, 0, 0, 7, 1, 0, 0, 0, 32, 0, 0, 0 };
  private static final byte[] Brizzif = { -112, 10, 0, 0, 1, 0, 0 };
  private static final byte[] Brizzig = { -112, 10, 0, 0, 1, 1, 0 };
  private static final byte[] Brizzih = { -112, 108, 0, 0, 1, 0, 0 };
  private static final byte[] Brizzii = { -112, -67, 0, 0, 7, 3, 0, 0, 0, 7, 0, 0, 0 };
  private static final byte[] Brizzij = { -112, -69, 0, 0, 7, 1, 0, 0, 0, 0, 0, 0, 0 };
  private String bp;
  private ku kul;
  private String mCardNumber;
  private String mCardStatus;
  private IsoDep mIsoDep;
  private String mIssueBranch;
  private String mIssueDate;
  private String mTagId;
  private String Brizzir;
  private String Brizzis;
  private String Brizziu;
  @NonNull
  private String Brizziv = "";
  private Integer Brizziw;

  public Brizzi(@NonNull IsoDep paramIsoDep)
  {
    this.mIsoDep = paramIsoDep;
    this.mTagId = Brizzibooleanb(paramIsoDep.getTag().getId());
    this.kul = new ku();
  }

  @NonNull
  public static byte[] Brizzibytea(@NonNull String paramString)
  {
    int n = paramString.length();
    byte[] arrayOfByte = new byte[n / 2];
    int m;
    for (int k = 0; k < n - 1; k = m)
    {
      int i1 = k / 2;
      m = k + 2;
      arrayOfByte[i1] = ((byte)(Integer.parseInt(paramString.substring(k, m), 16) & 0xFF));
    }
    return arrayOfByte;
  }

  @Nullable
  public Boolean Brizzibytea()
  {
    Boolean localBoolean = Boolean.valueOf(false);
    Object localObject1;
    try
    {
      String str = Brizzibooleanb(this.mIsoDep.transceive(Brizzid));
      if (str.substring(str.length() - 4, str.length()).equalsIgnoreCase("9100"))
      {
        localObject1 = Boolean.valueOf(true);
        try
        {
          this.mCardNumber = str.substring(6, 22);
          this.mIssueDate = str.substring(22, 28);
          this.mIssueBranch = str.substring(34, 38);
          this.mCardStatus = Brizzibooleanb(this.mIsoDep.transceive(Brizzie)).substring(6, 10);
          return (Boolean)localObject1;
        }
        catch (IOException ioe1) {}
      }
      else
      {
        return null;
      }
    }
    catch (IOException ioe2)
    {
      localObject1 = null;
      Object localObject2 = ioe2;
      ((IOException)localObject2).printStackTrace();
    }
    return (Boolean)localObject1;
  }

  @NonNull
  public Boolean Brizzibooleanb()
  {
    Boolean localBoolean = Boolean.valueOf(false);
    try
    {
      this.mIsoDep.transceive(Brizzic);
      Object localObject1 = Brizzibooleanb(this.mIsoDep.transceive(Brizzif));
      if (((String)localObject1).substring(((String)localObject1).length() - 4, ((String)localObject1).length()).equalsIgnoreCase("91AF"))
      {
        this.Brizzis = ((String)localObject1).substring(0, ((String)localObject1).length() - 4);
        localObject1 = this.kul;
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(this.mCardNumber);
        ((StringBuilder)localObject2).append(this.mTagId);
        ((StringBuilder)localObject2).append("FF");
        ((ku)localObject1).kustringa(((StringBuilder)localObject2).toString(), "0000030080000000", this.Brizzis);
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("90AF000010");
        ((StringBuilder)localObject1).append(this.kul.kustringa().substring(0, 32));
        ((StringBuilder)localObject1).append("00");
        Brizzibytea(((StringBuilder)localObject1).toString());
        localObject1 = this.kul;
        localObject2 = this.mIsoDep;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("90AF000010");
        localStringBuilder.append(this.kul.kustringa().substring(0, 32));
        localStringBuilder.append("00");
        localObject1 = ((ku)localObject1).kustringa(((IsoDep)localObject2).transceive(Brizzibytea(localStringBuilder.toString())));
        if (((String)localObject1).substring(((String)localObject1).length() - 4, ((String)localObject1).length()).equalsIgnoreCase("9100")) {
          return Boolean.valueOf(true);
        }
      }
      return localBoolean;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return localBoolean;
  }

  @NonNull
  public String Brizzibooleanb(@NonNull String paramString)
  {
    String str = "";
    int k = 0;
    while (k < paramString.length())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(paramString.substring(paramString.length() - k - 2, paramString.length() - k));
      str = localStringBuilder.toString();
      k += 2;
    }
    return str.toUpperCase();
  }

  @NonNull
  public String Brizzibooleanb(@NonNull byte[] paramArrayOfByte)
  {
    String str = "";
    int k = 0;
    while (k < paramArrayOfByte.length)
    {
      Object localObject2 = Integer.toHexString(paramArrayOfByte[k] & 0xFF);
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
      k += 1;
    }
    return str.toUpperCase();
  }

  @NonNull
  public String Brizzistringd()
  {
    if (this.mCardStatus.equals("6161"))
    {
      if (Brizzistringg().booleanValue()) {
        return "PASSIVE";
      }
      return "ACTIVE";
    }
    if ((!this.mCardStatus.equals("636C")) && (!this.mCardStatus.equals("434C"))) {
      return "";
    }
    return "CLOSED";
  }

  @NonNull
  public String Brizzistringe()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.mIssueDate.substring(0, 2));
    localStringBuilder.append("/");
    localStringBuilder.append(this.mIssueDate.substring(2, 4));
    localStringBuilder.append("/");
    localStringBuilder.append(this.mIssueDate.substring(4, 6));
    return localStringBuilder.toString();
  }

  public String Brizzistringf()
  {
    return this.mIssueBranch;
  }

  @NonNull
  public Boolean Brizzistringg()
  {
    Date localDate1 = new Date();
    Object localObject = new SimpleDateFormat("yyMMdd");
    try
    {
      Date localDate2 = ((SimpleDateFormat)localObject).parse(this.bp);
      localObject = Boolean.valueOf(true);
      if ((localDate1.getTime() - localDate2.getTime()) / 86400000L < 365L) {
        localObject = Boolean.valueOf(false);
      }
      if (this.bp.equals("000000")) {
        localObject = Boolean.valueOf(false);
      }
      return (Boolean)localObject;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return Boolean.valueOf(false);
  }

  public long getBalance()
  {
    try
    {
      this.bp = Brizzibooleanb(this.mIsoDep.transceive(Brizzii)).substring(0, 6);
      String str = Brizzibooleanb(this.mIsoDep.transceive(Brizzih));
      this.Brizziu = str.substring(0, 6);
      this.Brizziw = Integer.parseInt(Brizzibooleanb(str.substring(0, 8)), 16);
      long l1 = Long.parseLong(Brizzibooleanb(str.substring(0, 8)), 16);
      return l1;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }

  public String getCardNumber()
  {
    return this.mCardNumber;
  }

  @SuppressLint("DefaultLocale")
  public String h()
  {
    try
    {
      this.bp = Brizzibooleanb(this.mIsoDep.transceive(Brizzii)).substring(0, 6);
      String str = Brizzibooleanb(this.mIsoDep.transceive(Brizzih));
      this.Brizziu = str.substring(0, 6);
      this.Brizziw = Integer.parseInt(Brizzibooleanb(str.substring(0, 8)), 16);
      this.Brizzir = String.format("%,.2f", (double) this.Brizziw.intValue());
      str = this.Brizzir;
      return str;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }

  @Nullable
  public Boolean readCard()
  {
    Boolean localBoolean = Boolean.FALSE;
    Object localObject1;
    try
    {
      String str = Brizzibooleanb(this.mIsoDep.transceive(Brizzid));
      if (str.substring(str.length() - 4, str.length()).equalsIgnoreCase("9100"))
      {
        localObject1 = Boolean.TRUE;
        try
        {
          this.mCardNumber = str.substring(6, 22);
          this.mIssueDate = str.substring(22, 28);
          this.mIssueBranch = str.substring(34, 38);
          this.mCardStatus = Brizzibooleanb(this.mIsoDep.transceive(Brizzie)).substring(6, 10);
          return (Boolean)localObject1;
        }
        catch (IOException ioe1) {}
      }
      else
      {
        return null;
      }
    }
    catch (IOException ioe2)
    {
      localObject1 = null;
      Object localObject2 = ioe2;
      ((IOException)localObject2).printStackTrace();
    }
    return (Boolean)localObject1;
  }

  public void setTagId(@NonNull byte[] paramArrayOfByte)
  {
    this.mTagId = Brizzibooleanb(paramArrayOfByte);
  }
}
