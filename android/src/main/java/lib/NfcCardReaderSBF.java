package lib;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import a.a;
import a.h;
import a.i;
import dalvik.system.DexClassLoader;
import lib.bcanfc.Flazz;
import lib.bmandirinfc.EMoney;
import lib.bninfc.TapCash;
import lib.brinfc.Brizzi;
import nfclib.FlazzCardReader;
import nfclib.SClassLoader;
import utils.PreferenceClass;

//import com.bm.main.fpl.utils.PreferenceClass;
//import lib.bninfc.tapcashgo.card.Card;
//import lib.bninfc.tapcashgo.provider.CardProvider;
//import lib.bninfc.tapcashgo.tapcashgo783m;

//import static com.bm.main.fpl.activities.BaseActivity.mInstanceBaseActivity;

public class NfcCardReaderSBF {
    private static final String TAG = NfcCardReaderSBF.class.getSimpleName();
    static NfcCardReaderSBF mNfcCardReaderSBF;
    private byte[] commandAPDUSelectDF_Brizzi;
    private byte[] commandAPDUSelectDF_EMoney;
    private byte[] commandAPDUSelectDF_Flazz;
    private byte[] commandAPDUSelectDF_TapCash;
    private byte[] commandAPDUSelectDF_TapCash2;
    private byte[] commandAPDUforBalance;
    byte[] commandAPDUforNumber;
    private byte[] commandAPDUforRetrieveData;
    private byte[] commandAPDUforSelectDF_EKTP;
    private byte[] commandAPDUforSelectEF_Photo;
    private Context mContext;
    private NfcAdapter mNfcAdapter;
    private NfcCardReaderListenerSBF mNfcCardReaderListenerSBF;

    public NfcCardReaderSBF(Context paramContext) {
        this(paramContext, null);
    }

    public NfcCardReaderSBF(Context paramContext, NfcCardReaderListenerSBF paramNfcCardReaderListenerSBF) {
        this.mContext = paramContext;
        this.mNfcCardReaderListenerSBF = paramNfcCardReaderListenerSBF;
        this.mNfcAdapter = NfcAdapter.getDefaultAdapter(paramContext);
        this.commandAPDUSelectDF_EMoney = EMoney.EMoneyAPDUSelectDF;
        this.commandAPDUforNumber = EMoney.EMoneyAPDUforNumber;
        this.commandAPDUforBalance = EMoney.EMoneyAPDUforBalance;
        this.commandAPDUforSelectDF_EKTP = new byte[]{0, -92, 0, 0, 2, 127, 10};
        this.commandAPDUforSelectEF_Photo = new byte[]{0, -92, 0, 0, 2, 111, -14};
        this.commandAPDUforRetrieveData = new byte[]{0, -80};
        this.commandAPDUSelectDF_Flazz = Flazz.FlazzAPDUSelectDF;
        this.commandAPDUSelectDF_Brizzi = Brizzi.BrizziAPDUSelectDF;
        this.commandAPDUSelectDF_TapCash = TapCash.TapCashAPDUSelectDF;
    }

    public static NfcCardReaderSBF getInstance(Context paramContext, NfcCardReaderListenerSBF paramNfcCardReaderListenerSBF) {
        Log.d(TAG, "getInstance: ");
        if (mNfcCardReaderSBF == null) {
            mNfcCardReaderSBF = new NfcCardReaderSBF(paramContext, paramNfcCardReaderListenerSBF);
        }
        return mNfcCardReaderSBF;
    }

//  public void handleNfcIntent(Intent intent)
//  {
//      String action = intent.getAction();
//   // String str = paramIntent.getAction();
////    StringBuilder localStringBuilder = new StringBuilder();
////    localStringBuilder.append("Action detected: ");
////    localStringBuilder.append(action);
////    Log.d("CardReader", localStringBuilder.toString());
////    if ((this.mNfcAdapter.isEnabled()) && ("android.nfc.action.TAG_DISCOVERED".equals(str))) {
//        if ((this.mNfcAdapter.isEnabled()) && NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
//      // handleNfcTag((Tag)intent.getParcelableExtra("android.nfc.extra.TAG"));
//            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//       handleNfcTag(tag);
//      // handleNfcTag((Tag)intent.getParcelableExtra(NfcAdapter.EXTRA_TAG));
//    }
//  }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void handleNfcIntent(@NonNull Intent intent) {
        Log.d(TAG, "handleNfcIntent: ");
        String action = intent.getAction();
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Action detected: ");
        stringBuilder.append(action);
        Log.d(str, stringBuilder.toString());



            if (this.mNfcAdapter.isEnabled() && NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
               // handleNfcTag(intent);
                handleNfcTag((Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG),intent.getByteArrayExtra("android.nfc.extra.ID"));
                //  handleNfcTag((Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_ID));
            }

       // localTag = (Tag)intent.getParcelableExtra("android.nfc.extra.TAG");
      //  byteArrayExtra = intent.getByteArrayExtra("android.nfc.extra.ID");


    }


//  public void handleNfcTag(Tag paramTag)
//  {
//    Log.d(TAG, "handleNfcTag: "+paramTag);
//      String[] techList = paramTag.getTechList();
//    for (;;)
//    {
//      int j;
//    //  int i;
//   //   byte[] localObject3;
//      byte[] localObject3;
//      try
//      {
//          IsoDep localObject2 = IsoDep.get(paramTag);
//        if (localObject2 != null)
//        {
//          localObject2.setTimeout(1500);
//          localObject2.connect();
//          if (Build.VERSION.SDK_INT >= 26) {
//            ((Vibrator)this.mContext.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(150L, 10));
//          } else {
//            ((Vibrator)this.mContext.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(150L);
//          }
//          boolean bool = UtilNfcSBF.validApduResponse(localObject2.transceive(this.commandAPDUSelectDF_EMoney));
//         int x = 8;
//          j = 0;
//          if (bool)
//          {
//            byte[] paramTagz = localObject2.transceive(commandAPDUforNumber);
//            if (!UtilNfcSBF.validApduResponse(paramTagz))
//            {
//              if (localObject2.isConnected()) {
//                localObject2.close();
//              }
//              throw new Exception("Not E-Money valid card number");
//            }
//            String paramTagq = UtilNfcSBF.getHexString(paramTagz, 8);
//            localObject3 = localObject2.transceive(this.commandAPDUforBalance);
//            if (localObject3.length >= 4) {
//              break ;//label1206;
//            }
//            if (localObject2.isConnected()) {
//              localObject2.close();
//            }
//            throw new Exception("Not E-Money valid balance");
//           // long localObject1 =0L;
////            if (this.mNfcCardReaderListenerSBF != null)
////            {
////
//////              Object localObject1 = null;
////             // this.mNfcCardReaderListenerSBF.onEMoneyCardFound(paramTagq, 0L);
////            }
//          }
//          else if (UtilNfcSBF.validApduResponse(localObject2.transceive(this.commandAPDUforSelectDF_EKTP)))
//          {
//            if (!UtilNfcSBF.validApduResponse(localObject2.transceive(this.commandAPDUforSelectEF_Photo)))
//            {
//              if (localObject2.isConnected()) {
//                localObject2.close();
//              }
//              throw new Exception("E-KTP failed select photo");
//            }
//            localObject3 = localObject2.transceive(UtilNfcSBF.mergeArray(this.commandAPDUforRetrieveData, UtilNfcSBF.tapcashgo2a_a("000008")));
//            if (!UtilNfcSBF.validApduResponse((byte[])localObject3))
//            {
//              if (localObject2.isConnected()) {
//                localObject2.close();
//              }
//              throw new Exception("E-KTP failed to retrieve photo data");
//            }
//            int k = localObject3[0] << 8 & 0xFF00 | localObject3[1] & 0xFF;
//            byte[] paramTagz = new byte[k];
//            System.arraycopy(localObject3, 2, paramTagz, 0, localObject3.length - 4);
//            if (x < k)
//            {
//              j = 112 + x;
//              if (j > k)
//              {
//                localObject3 = localObject2.transceive(UtilNfcSBF.mergeArray(this.commandAPDUforRetrieveData, UtilNfcSBF.tapcashgo2a_a(String.format("%04X%02X", x, k - x + 2))));
//                if (!UtilNfcSBF.validApduResponse((byte[])localObject3))
//                {
//                  if (localObject2.isConnected()) {
//                    localObject2.close();
//                  }
//                  throw new Exception("Error APDU");
//                }
//                System.arraycopy(localObject3, 0, paramTag, x - 2, localObject3.length - 2);
//              }
//              else
//              {
//                localObject3 = localObject2.transceive(UtilNfcSBF.mergeArray(this.commandAPDUforRetrieveData, UtilNfcSBF.tapcashgo2a_a(String.format("%04X%02X", x, 112))));
//                if (!UtilNfcSBF.validApduResponse((byte[])localObject3))
//                {
//                  if (localObject2.isConnected()) {
//                    localObject2.close();
//                  }
//                  throw new Exception("Error APDU:");
//                }
//                System.arraycopy(localObject3, 0, paramTag, x - 2, localObject3.length - 2);
//              }
//              x = j;
//              continue;
//            }
//            try
//            {
//              Bitmap paramTagd = BitmapFactory.decodeByteArray(paramTagz, 0, paramTagz.length);
//              if (this.mNfcCardReaderListenerSBF == null) {
//                continue;
//              }
//              this.mNfcCardReaderListenerSBF.onEKTPCardFound(paramTagd);
//            }
//            catch (Exception e)
//            {
//              if (localObject2.isConnected()) {
//                localObject2.close();
//              }
//              StringBuilder localObject2B = new StringBuilder();
//              ((StringBuilder)localObject2B).append("E-KTP Error decode photo");
//              ((StringBuilder)localObject2B).append(e.getMessage());
//              throw new Exception(((StringBuilder)localObject2B).toString());
//            }
//          }
//          else
//          {
//            bool = UtilNfcSBF.validApduResponse(localObject2.transceive(this.commandAPDUSelectDF_Flazz));
//            if (bool) {
//              try
//              {
//                File localObject3f = new File(this.mContext.getCacheDir(), "s.dex");
//                if (((File)localObject3f).exists()) {
//                  ((File)localObject3f).delete();
//                }
//                AssetManager paramTagas = this.mContext.getAssets();
//                if (paramTagas == null) {
//                  break ;
//                }
//                InputStream paramTagg = paramTagas.open("flazz.txt");
//                Object localObject4 = new BufferedOutputStream(new FileOutputStream((File)localObject3f));
//                byte[] arrayOfByte = new byte[' '];
//                arrayOfByte = i.a(i.a(paramTagg));
//                ((OutputStream)localObject4).write(arrayOfByte, 0, arrayOfByte.length);
//                ((OutputStream)localObject4).close();
//                paramTagg.close();
//                Context paramTagv = this.mContext;
//                this.mContext.getApplicationContext();
//                File paramTagt = paramTagv.getDir("outdex", 0);
//                StringBuilder localObject4B = new StringBuilder();
//                ((StringBuilder)localObject4B).append(paramTagt.getCanonicalPath());
//                ((StringBuilder)localObject4B).append("/s.dex");
//                File localObject4f = new File(((StringBuilder) localObject4B).toString());
//                if (((File)localObject4f).exists()) {
//                  ((File)localObject4f).delete();
//                }
//                SClassLoader paramTagm = new SClassLoader(new DexClassLoader(((File) localObject3f).getAbsolutePath(), paramTagt.getAbsolutePath(), null, this.mContext.getClassLoader()));
//                FlazzCardReader localObject3flaz = new FlazzCardReader(localObject2);
//                a aClass = new h().a(paramTagm, (e) localObject3flaz, true);
//                if (aClass.a())
//                {
//                  if (this.mNfcCardReaderListenerSBF == null) {
//                    continue;
//                  }
//                  this.mNfcCardReaderListenerSBF.onFlazzCardFound(aClass.c(), aClass.c());
//                  continue;
//                }
//                if (localObject2.isConnected()) {
//                  localObject2.close();
//                }
//                throw new Exception("Error read card");
//              }
//              catch (IOException ioe)
//              {
//                ioe.printStackTrace();
//                if (localObject2.isConnected()) {
//                  localObject2.close();
//                }
//                StringBuilder localObject2B = new StringBuilder();
//                ((StringBuilder)localObject2B).append("Error ");
//                ((StringBuilder)localObject2B).append(ioe.getMessage());
//                throw new Exception(((StringBuilder)localObject2B).toString());
//              }
//            }
//            if (UtilNfcSBF.validApduResponse(localObject2.transceive(this.commandAPDUSelectDF_Brizzi)))
//            {
//              Brizzi brizzi = new Brizzi(localObject2);
//              if (brizzi.readCard()) {
//                brizzi.c();
//              }
//              if (this.mNfcCardReaderListenerSBF != null) {
//                this.mNfcCardReaderListenerSBF.onBrizziCardFound(brizzi.getCardNumber(), brizzi.getBalance());
//              }
//            }
//            else if (UtilNfcSBF.validApduResponse(localObject2.transceive(this.commandAPDUSelectDF_TapCash)))
//            {
//              TapCash tapCash = new TapCash(localObject2);
//              tapCash.readCard();
//              if (this.mNfcCardReaderListenerSBF != null) {
//                this.mNfcCardReaderListenerSBF.onTapCashCardFound(tapCash.getCardNumber(), tapCash.getBalance());
//              }
//            }
//            else if (this.mNfcCardReaderListenerSBF != null)
//            {
//              this.mNfcCardReaderListenerSBF.onError("Kartu Tidak Dikenal");
//            }
//          }
//          if (localObject2.isConnected())
//          {
//            localObject2.close();
//            return;
//          }
//        }
//      }
//      catch (Exception e)
//      {
//        if (this.mNfcCardReaderListenerSBF != null) {
//          this.mNfcCardReaderListenerSBF.onError(e.getMessage());
//        }
//      }
//      return;
//     // label1206:
//      //long l1;// = 0L;
////      for (int zz = j; zz < 4; zz = j)
////      {
////        long l2 = localObject3[zz];
////        j = zz + 1;
////        long l1 = ((l2 & 0xFF) << zz * 8);
////      }
////      //label1248:
////      paramTag = null;
//    }
//  }
//}


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void handleNfcTag(@NonNull Tag tagx, byte[] bytes) {

       // Tag tagx=intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Log.d(TAG, "handleNfcTag: " + tagx.toString());

//        if(PreferenceClass.getBoolean("update", false)==true)
//        {
//
//            Log.d(TAG, "handleNfcTag: masuk sini");
//            new AsyncUpdateBalanceBNI(mContext,tagx,bytes).execute();
//
//        }else {


            StringBuilder stringBuilder;
        Log.d(TAG, "isodep1");
            IsoDep tagIsoDep = null;
        Log.d(TAG, "isodep2");
            try {
                Log.d(TAG, "isodep");
                tagIsoDep = IsoDep.get(tagx);
                Log.d(TAG, "isodep"+ tagIsoDep.toString());
                if (tagIsoDep != null) {
                    Log.d(TAG, "masuk iso dep");
                    tagIsoDep.setTimeout(5000);
                    tagIsoDep.connect();
                    //     Log.d(TAG, "handleNfcTag: "+UtilNfcSBF.validApduResponse(tagIsoDep.transceive(this.commandAPDUSelectDF_Flazz)));
                    if (VERSION.SDK_INT >= 26) {
                        ((Vibrator) this.mContext.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(150, 10));
                    } else {
                        ((Vibrator) this.mContext.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(150);
                    }
                    Log.d(TAG, "masuk iso dep2");
                    int q = 8;
                    int j = 0;
                    if (UtilNfcSBF.validApduResponse(tagIsoDep.transceive(this.commandAPDUSelectDF_EMoney))) {
                        byte[] transceive = tagIsoDep.transceive(this.commandAPDUforNumber);
                        Log.d(TAG, "handleNfcTag: " + new String(transceive, "UTF-8"));
                        if (UtilNfcSBF.validApduResponse(transceive)) {
                            String hexString = UtilNfcSBF.getHexString(transceive, 8);
                            byte[] transceive2 = tagIsoDep.transceive(this.commandAPDUforBalance);
                            Log.d(TAG, "handleNfcTag: " + new String(transceive2, "UTF-8"));
                            if (transceive2.length < 4) {

                                if (this.mNfcCardReaderListenerSBF != null) {
                                    this.mNfcCardReaderListenerSBF.onError("Not E-Money valid balance");
                                }

                                if (tagIsoDep.isConnected()) {
                                    tagIsoDep.close();
                                }
                                //      throw new Exception("Not E-Money valid balance");
                            }
                            //   long j = 0;

//            while (i2 < 4) {
//              i2++;
//              Log.d(TAG, "handleNfcTag: "+ transceive2[i2]);
//              j += (((long) transceive2[i2]) & 0xFF) << (i2 * 8);
//            }
                            long l1 = 0L;
                            for (int ix = j; ix < 4; ix = j) {
                                long l2 = transceive2[ix];
                                j = ix + 1;
                                l1 += ((l2 & 0xFF) << ix * 8);
                            }
                            if (this.mNfcCardReaderListenerSBF != null) {
                                this.mNfcCardReaderListenerSBF.onEMoneyCardFound(hexString, l1);
                            }
                        } else {

                            if (this.mNfcCardReaderListenerSBF != null) {//
                                this.mNfcCardReaderListenerSBF.onError("Not E-Money valid card number");

                            }
                            if (tagIsoDep.isConnected()) {
                                tagIsoDep.close();
                            }


                        }//
                    } else if (UtilNfcSBF.validApduResponse(tagIsoDep.transceive(this.commandAPDUforSelectDF_EKTP))) {

                        if (UtilNfcSBF.validApduResponse(tagIsoDep.transceive(this.commandAPDUforSelectEF_Photo))) {

                            byte[] transceive3 = tagIsoDep.transceive(UtilNfcSBF.mergeArray(this.commandAPDUforRetrieveData, UtilNfcSBF.m16a("000008")));
                            if (UtilNfcSBF.validApduResponse(transceive3)) {

                                //  int i3 = ((transceive3[0] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (transceive3[1] & 255);
                                int k = transceive3[0] << 8 & 0xFF00 | transceive3[1] & 0xFF;
                                byte[] obj = new byte[k];
                                System.arraycopy(transceive3, 2, obj, 0, transceive3.length - 4);

                                while (q < k) {
                                    int j4 = 112 + q;
                                    //   byte[] transceive4;
                                    //transceive4;
                                    if (j4 > k) {
                                        byte[] transceive4 = tagIsoDep.transceive(UtilNfcSBF.mergeArray(this.commandAPDUforRetrieveData, UtilNfcSBF.m16a(String.format("%04X%02X", q, (k - q) + 2))));
                                        if (UtilNfcSBF.validApduResponse(transceive4)) {
                                            System.arraycopy(transceive4, 0, obj, q - 2, transceive4.length - 2);
                                        } else {

                                            if (this.mNfcCardReaderListenerSBF != null) {

                                                this.mNfcCardReaderListenerSBF.onError("Error APDU:");

                                            }
                                            if (tagIsoDep.isConnected()) {
                                                tagIsoDep.close();
                                            }
                                            // throw new Exception("Error APDU");
                                        }
                                    } else {
                                        byte[] transceive4 = tagIsoDep.transceive(UtilNfcSBF.mergeArray(this.commandAPDUforRetrieveData, UtilNfcSBF.m16a(String.format("%04X%02X", q, 112))));
                                        if (UtilNfcSBF.validApduResponse(transceive4)) {
                                            System.arraycopy(transceive4, 0, obj, q - 2, transceive4.length - 2);
                                        } else {

                                            if (this.mNfcCardReaderListenerSBF != null) {

                                                this.mNfcCardReaderListenerSBF.onError("Error APDU:");

                                            }
                                            if (tagIsoDep.isConnected()) {
                                                tagIsoDep.close();
                                            }
                                            //    throw new Exception("Error APDU:");
                                        }
                                    }
                                    q = j4;
                                    // continue;
                                }
                                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(obj, 0, obj.length);

                                if (this.mNfcCardReaderListenerSBF != null) {

                                    this.mNfcCardReaderListenerSBF.onEKTPCardFound(decodeByteArray);
                                }
                            } else {
                                if (this.mNfcCardReaderListenerSBF != null) {
                                    this.mNfcCardReaderListenerSBF.onError("E-KTP failed to retrieve photo data");
                                }
                                if (tagIsoDep.isConnected()) {
                                    tagIsoDep.close();
                                }


                                //throw new Exception("Not E-Money valid card number");//
                            }
                        } else {


                            if (this.mNfcCardReaderListenerSBF != null) {
                                this.mNfcCardReaderListenerSBF.onError("E-KTP failed select photo");
                            }

                            if (tagIsoDep.isConnected()) {
                                tagIsoDep.close();
                            }
                        }
                    } else if (UtilNfcSBF.validApduResponse(tagIsoDep.transceive(this.commandAPDUSelectDF_Flazz))) {

                        File file = new File(this.mContext.getCacheDir(), "s.dex");
                        if (file.exists()) {
                            file.delete();
                        }
                        AssetManager assets = this.mContext.getAssets();
                        InputStream open = assets != null ? assets.open("s.txt") : null;
                        OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                        byte[] bArr = new byte[8192];
                        bArr = i.a(i.a(open));
                        bufferedOutputStream.write(bArr, 0, bArr.length);
                        bufferedOutputStream.close();
                        open.close();

                        this.mContext.getApplicationContext();
                        File dir = this.mContext.getDir("outdex", 0);
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(dir.getCanonicalPath());
                        stringBuilder2.append("/s.dex");
                        File file2 = new File(stringBuilder2.toString());
                        if (file2.exists()) {
                            file2.delete();
                        }
                        a a = new h().a(new SClassLoader(new DexClassLoader(file.getAbsolutePath(), dir.getAbsolutePath(), null, this.mContext.getClassLoader())), new FlazzCardReader(tagIsoDep), true);
                        if (a.a()) {
                            if (this.mNfcCardReaderListenerSBF != null) {
                                // continue;
                                this.mNfcCardReaderListenerSBF.onFlazzCardFound(a.b(), a.c());
                            }

                            //  continue;
                        } else {

                            if (this.mNfcCardReaderListenerSBF != null) {
                                this.mNfcCardReaderListenerSBF.onError("Error read card");
                            }

                            if (tagIsoDep.isConnected()) {
                                tagIsoDep.close();
                            }

                            // throw new Exception("Error read card");
                        }


                    } else if (UtilNfcSBF.validApduResponse(tagIsoDep.transceive(this.commandAPDUSelectDF_Brizzi))) {
                        Brizzi brizzi = new Brizzi(tagIsoDep);
                        Log.d(TAG, "handleNfcTag: " + brizzi.readCard());
                        if (brizzi.readCard()) {
                            brizzi.Brizzibooleanb();
                        }
                        Log.d(TAG, "handleNfcTag mNfcCardReaderListenerSBF: " + this.mNfcCardReaderListenerSBF + "" + brizzi.getCardNumber() + " " + brizzi.getBalance() + " " + brizzi.Brizzistringd() + " " + brizzi.Brizzistringe() + " " + brizzi.Brizzistringf());
                        if (this.mNfcCardReaderListenerSBF != null) {
                            this.mNfcCardReaderListenerSBF.onBrizziCardFound(brizzi.getCardNumber(), brizzi.getBalance());
                        }
                    } else if (UtilNfcSBF.validApduResponse(tagIsoDep.transceive(this.commandAPDUSelectDF_TapCash))) {

                        TapCash tapCash = new TapCash(tagIsoDep);

                        if (tapCash.readCard()) {
                            Log.d(TAG, "handleNfcTag BNI: " + tapCash.getCardNumber());
                            if (this.mNfcCardReaderListenerSBF != null) {
                                this.mNfcCardReaderListenerSBF.onTapCashCardFound(tapCash.getCardNumber(), tapCash.getBalance());

                            }
                        }

                    } else if (this.mNfcCardReaderListenerSBF != null) {
                        this.mNfcCardReaderListenerSBF.onError("Kartu Tidak Dikenal");
                    }
                    if (tagIsoDep.isConnected()) {
                        tagIsoDep.close();
                    }
                }
            } catch (IOException e) {
                Log.d(TAG, "masuk eror");
                e.printStackTrace();
                if (tagIsoDep.isConnected()) {
                    try {
                        tagIsoDep.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            } catch (Exception e2) {
                Log.d(TAG, "masuk eror 2" + e2.toString());
                stringBuilder = new StringBuilder();
                if (tagIsoDep.isConnected()) {
                    try {
                        tagIsoDep.close();
                    } catch (IOException e) {


                        stringBuilder.append("E-KTP Error decode photo");
                        stringBuilder.append(e.getMessage());
                        // Log.e(TAG, "handleNfcTag: ",stringBuilder.toString() );

                    }
                }

            } catch (IOError tag2) {
                Log.d(TAG, "masuk eror 3");
                if (this.mNfcCardReaderListenerSBF != null) {
                    this.mNfcCardReaderListenerSBF.onError(tag2.getMessage());
                }
            }

    }


    @SuppressLint("StaticFieldLeak")
    private class AsyncUpdateBalanceBNI extends AsyncTask<Void, String, Uri> {

        final boolean z = PreferenceClass.getBoolean("update", false);
        @Nullable
        final String string = PreferenceClass.getString("host_address", "");

        Context context;
        Tag localTag;
        byte[]byteArrayExtra;
//        public AsyncUpdateBalanceBNI(Context mContext, Intent intent){
        public AsyncUpdateBalanceBNI(Context mContext,Tag tag,byte[] byteArrayExtrax){
            this.context=mContext;
            this.localTag = tag;
            this.byteArrayExtra =byteArrayExtrax;
            //z = PreferenceClass.getBoolean("update", false);
        }
        private Exception f2296f;

        @Override
        protected void onPreExecute() {
            // progressDialog = ProgressDialog.show(MainActivity.this,
//                    "ProgressDialog",
//                    "Wait for "+time.getText().toString()+ " seconds");
        }

        @Nullable
        @Override
        protected Uri doInBackground(Void... voids) {
//            try {
//                Card a = Card.card(byteArrayExtra, localTag, z, string);
//                assert a != null;
//                String a2 = tapcashgo783m.tapcashgo783m_a(a.Cardelement_f().getOwnerDocument());
//                Log.i("cardXml", "card is " + a2);
//                String a3 = tapcashgo783m.tapcashgo783m_a(a.Cardbyte_b());
//                Log.i("tagId", "tagID " + a3);
//                ContentValues contentValues = new ContentValues();
//                contentValues.put("type", a.cardenum_a().Cardint_a());
//                contentValues.put("serial", a3);
//                contentValues.put("data", a2);
//                contentValues.put("scanned_at", a.Carddate_c().getTime());
//                Uri insert = context.getContentResolver().insert(CardProvider.CardProvideruri, contentValues);
//
//                PreferenceClass.putString("last_read_id", a3);
//                PreferenceClass.putLong("last_read_at", new Date().getTime());
//
//                return insert;
//            } catch (Exception e) {
//                this.f2296f = e;
//                Log.e(TAG, "doInBackground: ", e);
//                return null;
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Uri result) {
            // execution of result of Long time consuming operation
//            progressDialog.dismiss();
//            finalResult.setText(result);
            Log.i("post", "execute "+result);
//            if (this.f2296f == null) {
//                context.startActivity(new Intent("android.intent.action.VIEW", result));
//                ((ReadingTagActivity)context).finish();
//            } else if (this.f2296f instanceof tapcashgo779l) {
//                new AlertDialog.Builder(context).setTitle("Unsupported Tag").setMessage(this.f2296f.getMessage()).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        ((ReadingTagActivity)context).finish();
//                    }
//                }).show();
//            } else {
//                tapcashgo783m.tapcashgo783m_b(  ((ReadingTagActivity)context), this.f2296f);
//            }
        }
    }

}
