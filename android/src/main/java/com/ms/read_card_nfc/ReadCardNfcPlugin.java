package com.ms.read_card_nfc;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.UUID;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import lib.NfcCardReaderListenerSBF;
import lib.NfcCardReaderSBF;

import static android.content.ContentValues.TAG;
/**
 * ReadCardNfcPlugin
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ReadCardNfcPlugin implements FlutterPlugin, MethodCallHandler, NfcAdapter.ReaderCallback, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    static MethodChannel channel;
    NfcAdapter mNfcAdapter;
    NfcBroacastReceiver mNfcBroadNfcBroacastReceiver = new NfcBroacastReceiver();//=new NfcBroacastReceiver();
    IntentFilter mNfcBroadcastReceiverFilter = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);//=new IntentFilter(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED);
    NfcCardReaderSBF mCardReader;
    NfcCardReaderListenerSBF mCardReaderListener;

    Activity activity;

    int flag = NfcAdapter.FLAG_READER_NFC_A |
            NfcAdapter.FLAG_READER_NFC_B |
            NfcAdapter.FLAG_READER_NFC_F |
            NfcAdapter.FLAG_READER_NFC_V |
            NfcAdapter.FLAG_READER_NFC_BARCODE |
            NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "read_card_nfc");
//    channel = MethodChannel(flutterPluginBinding.flutterEngine.dartExecutor, "read_card_nfc")
        channel.setMethodCallHandler(this);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(flutterPluginBinding.getApplicationContext());

//    if (mNfcAdapter == null) {
//      //NFC tidak ada
//      Log.d(TAG, "NFC null");
//    } else if (!mNfcAdapter.isEnabled()) {
//      //NFC Tidak Aktif
//      Log.d(TAG, "NFC Mati");
//    } else {
//      //NFC Aktif
//      Log.d(TAG, "NFC Aktif");
//    }
        mCardReaderListener = new cekSaldo(channel);
        mCardReader = new NfcCardReaderSBF(flutterPluginBinding.getApplicationContext(), mCardReaderListener);


    }


    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        }
        if (call.method.equals("Nfc#startSession")) {
            handleNfcStartSession(call, result);
        }else if(call.method.equals("Nfc#stopSession")) {
            disableReaderMode();
        } else {
            result.notImplemented();
        }
    }


    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    public void onNFCStart() {
        Log.d(TAG, "Hey Hey");
    }

    public void handleNfcStartSession(@NonNull MethodCall call, @NonNull Result result) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            result.error("unavailable", "Requires API level 19.", null);
        } else {
//      NfcAdapter adapter = mNfcAdapter ?: run {
//        result.error("unavailable", "NFC is not available for device.", null)
//        return
//      }
            if (mNfcAdapter == null) {
                //NFC tidak ada
                Log.d(TAG, "NFC null");
            } else if (!mNfcAdapter.isEnabled()) {
                //NFC Tidak Aktif
                Log.d(TAG, "NFC Mati");
            } else {
                //NFC Aktif
                Log.d(TAG, "NFC Aktif");
            }

            mNfcAdapter.enableReaderMode(activity, this, flag, null);
//            result.success("result");
//            channel.invokeMethod("onDiscovered", "cek masuk");
        }
    }

    public void disableReaderMode(){
        mNfcAdapter.disableReaderMode(activity);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        activity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        //do nothing
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        activity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivity() {
        //do nothing
    }


    private static class NfcBroacastReceiver extends BroadcastReceiver {

        public NfcBroacastReceiver() {
        }

        public void onReceive(Context context, @NonNull Intent intent) {
            //    Log.d(TAG, "onReceive: ");
            final String action = intent.getAction();
            Log.d(TAG, "onReceive: " + action);
//            intent = intent.getIntExtra("android.nfc.extra.ADAPTER_STATE",1);
//            if (action.equals(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED)) {
//            if (context == 1) {
//                if (mTextViewNfcStatus != null) {
//                    mTextViewNfcStatus.setText("NFCBrizzi TIDAK AKTIF");
//                }
//                if (mNfcSettings != null) {
//                    mNfcSettings.setImageResource(R.drawable.nfc_off);
//                }
//            } else if (context == 3) {
//                if (mTextViewNfcStatus != null) {
//                   mTextViewNfcStatus.setText("NFCBrizzi AKTIF");
//                }
//                if (mNfcSettings != null) {
//                    mNfcSettings.setImageResource(R.drawable.nfc_on);
//                }
//            }
            if (action.equals(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED)) {
                final int state = intent.getIntExtra(NfcAdapter.EXTRA_ADAPTER_STATE,
                        NfcAdapter.STATE_OFF);
                switch (state) {
                    case NfcAdapter.STATE_OFF:
                        break;
                    case NfcAdapter.STATE_TURNING_OFF:
//            if (mTextViewNfcStatus != null) {
//              mTextViewNfcStatus.setText("NFC TIDAK AKTIF");
//            }
//            if (mNfcSettings != null) {
//              mNfcSettings.setImageResource(R.drawable.nfc_off);
//            }
                        break;
                    case NfcAdapter.STATE_ON:
//            if (mTextViewNfcStatus != null) {
//              mTextViewNfcStatus.setText("NFC AKTIF");
//            }
//            if (mNfcSettings != null) {
//              mNfcSettings.setImageResource(R.drawable.nfc_on);
//            }
                        break;
                    case NfcAdapter.STATE_TURNING_ON:
                        break;
                }
            }
        }
    }

    static class cekSaldo implements NfcCardReaderListenerSBF {
        @NonNull
        Locale localeID = new Locale("in", "ID");

        cekSaldo(MethodChannel cekSaldo) {
            Log.d(TAG, "cekSaldo: ");
        }

        @Override
        public void onBrizziCardFound(@NonNull String paramAnonymousString, long paramAnonymousLong) {

            DecimalFormat localDecimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols localDecimalFormatSymbols = new DecimalFormatSymbols();
//      localDecimalFormatSymbols.setCurrencySymbol(getString(R.string.currency_symbol));
            localDecimalFormatSymbols.setMonetaryDecimalSeparator(',');
            localDecimalFormatSymbols.setGroupingSeparator('.');
            localDecimalFormat.setDecimalFormatSymbols(localDecimalFormatSymbols);
            System.out.println("ccc");
            try {
                JSONObject obj = new JSONObject();
                obj.put("card_id", "");
                obj.put("card_name", "Brizzi");
                obj.put("card_number", paramAnonymousString);
                obj.put("balance", String.valueOf(paramAnonymousLong));
                obj.put("another_info", "");
                channel.invokeMethod("onDiscovered",
                        obj.toString()
                );
            }catch (Exception e) {
                e.printStackTrace();
                channel.invokeMethod("onDiscovered",
                        "Error " + e.toString()
                );
            }


////      if (Build.VERSION.SDK_INT >= 21) {
////        mCardBackground.setBackground(getDrawable(R.drawable.brizzi));
////      } else {
////        mCardBackground.setBackground(getResources().getDrawable(R.drawable.brizzi));
////      }
//      StringBuilder paramAnonymousStringBuilder = new StringBuilder(paramAnonymousString);
//      int i = paramAnonymousString.length() - 4;
//      while (i > 0) {
//        paramAnonymousStringBuilder.insert(i, ' ');
//        i -= 4;
//      }
////      mImageViewPhotoKtp.setVisibility(View.GONE);
//      paramAnonymousString = paramAnonymousStringBuilder.toString();
////      if (mTextViewCardNumber != null) {
////        mTextViewCardNumber.setText(paramAnonymousString);
////
////        mTextViewCardNumber.setTextColor(ContextCompat.getColor(SplashScreenActivity.this, R.color.md_white_1000));
////
////        mTextViewCardNumber.setVisibility(View.VISIBLE);
////      }
////      if (mTextViewCardStatus != null) {
////        mTextViewCardStatus.setText("BRI BRIZZI");
////      }
////      if (mTextViewCardNumberValue != null) {
////        mTextViewCardNumberValue.setText(paramAnonymousString);
////        PreferenceClass.putString("emoney_card_number", paramAnonymousString.replaceAll(" ", ""));
////      }
////      if (mTextViewBalanceValue != null) {
////        mTextViewBalanceValue.setText(localDecimalFormat.format(paramAnonymousLong));
////      }
////      if (mMute) {
////        recite(Long.toString(paramAnonymousLong));
////      }
////      setShowTimer(SHOW_CARD_INFO_TIME_OUT);
        }

        @Override
        public void onEKTPCardFound(Bitmap paramBitmap) {
//      if (Build.VERSION.SDK_INT >= 21) {
//        mCardBackground.setBackground(getDrawable(R.drawable.ektp));
//      } else {
//        mCardBackground.setBackground(getResources().getDrawable(R.drawable.ektp));
//      }
//      mImageViewPhotoKtp.setImageBitmap(paramBitmap);
//      mImageViewPhotoKtp.setVisibility(View.VISIBLE);
//      if (mTextViewCardStatus != null) {
//        mTextViewCardStatus.setText("E-KTP");
//      }
//      setShowTimer(SHOW_CARD_INFO_TIME_OUT);


        }

        @Override
        public void onEMoneyCardFound(@NonNull String paramAnonymousString, long paramAnonymousLong) {
            Log.d(TAG, "onEMoneyCardFound: " + paramAnonymousString + " " + paramAnonymousLong);
            DecimalFormat localDecimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols localDecimalFormatSymbols = new DecimalFormatSymbols();
//      localDecimalFormatSymbols.setCurrencySymbol(getString(R.string.currency_symbol));
            localDecimalFormatSymbols.setMonetaryDecimalSeparator(',');
            localDecimalFormatSymbols.setGroupingSeparator('.');
            localDecimalFormat.setDecimalFormatSymbols(localDecimalFormatSymbols);
//      if (Build.VERSION.SDK_INT >= 21) {
//        mCardBackground.setBackground(getDrawable(R.drawable.emoney));
//      } else {
//        mCardBackground.setBackground(getResources().getDrawable(R.drawable.emoney));
//      }
//      mImageViewPhotoKtp.setVisibility(View.GONE);
            StringBuilder paramAnonymousStringBuilder = new StringBuilder(paramAnonymousString);
            int i = paramAnonymousString.length() - 4;
            while (i > 0) {
                paramAnonymousStringBuilder.insert(i, ' ');
                i -= 4;
            }
            paramAnonymousString = paramAnonymousStringBuilder.toString();
//      if (mTextViewCardNumber != null) {
//        mTextViewCardNumber.setText(paramAnonymousString);
//        mTextViewCardNumber.setTextColor(ContextCompat.getColor(SplashScreenActivity.this, R.color.md_white_1000));
//        mTextViewCardNumber.setVisibility(View.VISIBLE);
//      }
//      if (mTextViewCardStatus != null) {
//        mTextViewCardStatus.setText("Mandiri e-money");
//      }
//      if (mTextViewCardNumberValue != null) {
//        mTextViewCardNumberValue.setText(paramAnonymousString);
//        PreferenceClass.putString("emoney_card_number", paramAnonymousString.replaceAll(" ", ""));
//      }
//      if (mTextViewBalanceValue != null) {
//        mTextViewBalanceValue.setText(localDecimalFormat.format(paramAnonymousLong));
//      }
//      if (mMute) {
//        recite(Long.toString(paramAnonymousLong));
//      }
//      setShowTimer(SHOW_CARD_INFO_TIME_OUT);

        }

        @Override
        public void onError(String paramAnonymousString) {
            Log.d(TAG, "onError: " + paramAnonymousString);
//      if (Build.VERSION.SDK_INT >= 21) {
//        mCardBackground.setBackground(getDrawable(R.drawable.tap_fastpay));
//      } else {
//        mCardBackground.setBackground(ContextCompat.getDrawable(SplashScreenActivity.this, R.drawable.tap_fastpay));
//      }
//      mImageViewPhotoKtp.setVisibility(View.GONE);
//      mTextViewCardNumber.setVisibility(View.GONE);
//      if (mTextViewCardStatus != null) {
//        mTextViewCardStatus.setText(paramAnonymousString);
//      }
//      if (mTextViewCardNumberValue != null) {
//        mTextViewCardNumberValue.setText(getString(R.string.default_card_number));
//      }
//      if (mTextViewBalanceValue != null) {
//        mTextViewBalanceValue.setText(getString(R.string.default_balance));
//      }
//      setShowTimer(SHOW_ERROR_INFO_TIME_OUT);

        }

        @Override
        public void onFlazzCardFound(@NonNull String paramAnonymousString, long paramAnonymousLong) {
            DecimalFormat localDecimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols localDecimalFormatSymbols = new DecimalFormatSymbols();
//      localDecimalFormatSymbols.setCurrencySymbol(getString(R.string.currency_symbol));
            localDecimalFormatSymbols.setMonetaryDecimalSeparator(',');
            localDecimalFormatSymbols.setGroupingSeparator('.');
            localDecimalFormat.setDecimalFormatSymbols(localDecimalFormatSymbols);
//      if (Build.VERSION.SDK_INT >= 21) {
//        mCardBackground.setBackground(getDrawable(R.drawable.flazzcard));
//      } else {
//        mCardBackground.setBackground(getResources().getDrawable(R.drawable.flazzcard));
//      }
//      mImageViewPhotoKtp.setVisibility(View.GONE);
            StringBuilder paramAnonymousStringBuilder = new StringBuilder(paramAnonymousString);
            int i = paramAnonymousString.length() - 4;
            while (i > 0) {
                paramAnonymousStringBuilder.insert(i, ' ');
                i -= 4;
            }
            paramAnonymousString = paramAnonymousStringBuilder.toString();
//      if (mTextViewCardNumber != null) {
//        mTextViewCardNumber.setText(paramAnonymousString);
//        mTextViewCardNumber.setVisibility(View.VISIBLE);
//        mTextViewCardNumber.setTextColor(ContextCompat.getColor(SplashScreenActivity.this, R.color.md_grey_900));
//      }
//      if (mTextViewCardStatus != null) {
//        mTextViewCardStatus.setText("BCA Flazz");
//      }
//      if (mTextViewCardNumberValue != null) {
//        mTextViewCardNumberValue.setText(paramAnonymousString);
//        PreferenceClass.putString("emoney_card_number", paramAnonymousString.replaceAll(" ", ""));
//      }
//      if (mTextViewBalanceValue != null) {
//        mTextViewBalanceValue.setText(localDecimalFormat.format(paramAnonymousLong));
//      }
//      if (mMute) {
//        recite(Long.toString(paramAnonymousLong));
//      }
//      setShowTimer(SHOW_CARD_INFO_TIME_OUT);

        }

        @Override
        public void onTapCashCardFound(@NonNull String paramAnonymousString, long paramAnonymousLong) {
            DecimalFormat localDecimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols localDecimalFormatSymbols = new DecimalFormatSymbols();
//      localDecimalFormatSymbols.setCurrencySymbol(getString(R.string.currency_symbol));
            localDecimalFormatSymbols.setMonetaryDecimalSeparator(',');
            localDecimalFormatSymbols.setGroupingSeparator('.');
            localDecimalFormat.setDecimalFormatSymbols(localDecimalFormatSymbols);
//      if (Build.VERSION.SDK_INT >= 21) {
//        mCardBackground.setBackground(getDrawable(R.drawable.tapcash));
//      } else {
//        mCardBackground.setBackground(getResources().getDrawable(R.drawable.tapcash));
//      }
//      mImageViewPhotoKtp.setVisibility(View.GONE);
            StringBuilder paramAnonymousStringBuilder = new StringBuilder(paramAnonymousString);
            int i = paramAnonymousString.length() - 4;
            while (i > 0) {
                paramAnonymousStringBuilder.insert(i, ' ');
                i -= 4;
            }
            paramAnonymousString = paramAnonymousStringBuilder.toString();
//      if (mTextViewCardNumber != null) {
//        mTextViewCardNumber.setText(paramAnonymousString);
//        mTextViewCardNumber.setVisibility(View.VISIBLE);
//        mTextViewCardNumber.setTextColor(ContextCompat.getColor(SplashScreenActivity.this, R.color.md_grey_900));
//      }
//      if (mTextViewCardStatus != null) {
//        mTextViewCardStatus.setText("BNI TapCash");
//        branch = "BNI";
//      }
//      if (mTextViewCardNumberValue != null) {
//        mTextViewCardNumberValue.setText(paramAnonymousString);
//        PreferenceClass.putString("emoney_card_number", paramAnonymousString.replaceAll(" ", ""));
//      }
//      if (mTextViewBalanceValue != null) {
//        mTextViewBalanceValue.setText(localDecimalFormat.format(paramAnonymousLong));
//      }
//
//      if (mMute) {
//        recite(Long.toString(paramAnonymousLong));
//      }
//      setShowTimer(SHOW_CARD_INFO_TIME_OUT);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onTagDiscovered(@NonNull Tag paramTag) {
        Log.d(TAG, "onTagDiscovered: " + paramTag.toString());

        activity.runOnUiThread(new ExecuteInUI(paramTag,paramTag.getId()));
    }
    Tag tag;
    private class ExecuteInUI implements Runnable {
        Tag mTag;
        byte[] bytes;

        public ExecuteInUI(Tag paramTag, byte[] bytes) {
            this.mTag = paramTag;
            tag = paramTag;
            this.bytes = bytes;
        }

        public void run() {

            mCardReader.handleNfcTag(this.mTag, this.bytes);
        }
    }

}
