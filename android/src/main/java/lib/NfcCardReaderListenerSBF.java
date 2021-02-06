package lib;

//import android.graphics.Bitmap;
//
//public abstract interface NfcCardReaderListenerSBF
//{
//  public abstract void onBrizziCardFound(String paramString, long paramLong);
//
//  public abstract void onEKTPCardFound(Bitmap paramBitmap);
//
//  public abstract void onEMoneyCardFound(String paramString, long paramLong);
//
//  public abstract void onError(String paramString);
//
//  public abstract void onFlazzCardFound(String paramString, long paramLong);
//
//  public abstract void onTapCashCardFound(String paramString, long paramLong);
//}

import android.graphics.Bitmap;

public interface NfcCardReaderListenerSBF {
  void onBrizziCardFound(String str, long j);

  void onEKTPCardFound(Bitmap bitmap);

  void onEMoneyCardFound(String str, long j);

  void onError(String str);

  void onFlazzCardFound(String str, long j);

  void onTapCashCardFound(String str, long j);
}