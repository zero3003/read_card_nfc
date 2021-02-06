package utils;//package utils;
//
///*
// * Created by sarifhidayat on 1/11/18.
// */
//
//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Build;
//import android.util.DisplayMetrics;
//import android.view.Display;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.bm.main.scm.R;
//
//public class Appirater {
//    private static final String PREF_LAUNCH_COUNT = "launch_count";
//    private static final String PREF_EVENT_COUNT = "event_count";
//    private static final String PREF_RATE_CLICKED = "rateclicked";
//    private static final String PREF_DONT_SHOW = "dontshow";
//    private static final String PREF_DATE_REMINDER_PRESSED = "date_reminder_pressed";
//    private static final String PREF_DATE_FIRST_LAUNCHED = "date_firstlaunch";
//    private static final String PREF_APP_VERSION_CODE = "versioncode";
//    private static final String PREF_APP_LOVE_CLICKED = "loveclicked";
//
//    public static void appLaunched(@NonNull Context mContext) {
//        boolean testMode = mContext.getResources().getBoolean(R.bool.appirator_test_mode);
//        SharedPreferences prefs = mContext.getSharedPreferences(mContext.getPackageName() + ".appirater", 0);
//        if (!testMode && (prefs.getBoolean(PREF_DONT_SHOW, false) || prefs.getBoolean(PREF_RATE_CLICKED, false))) {
//            return;
//        }
//
//        SharedPreferences.Editor editor = prefs.edit();
//
//        if (testMode) {
//            if (prefs.getBoolean(PREF_APP_LOVE_CLICKED, false)) {
//                showRateDialog(mContext, editor);
//            } else {
//                showLoveDialog(mContext, editor);
//            }
//            return;
//        }
//
//        // Increment launch counter
//        long launch_count = prefs.getLong(PREF_LAUNCH_COUNT, 0);
//
//        // Get events counter
//        long event_count = prefs.getLong(PREF_EVENT_COUNT, 0);
//
//        // Get date of first launch
//        long date_firstLaunch = prefs.getLong(PREF_DATE_FIRST_LAUNCHED, 0);
//
//        // Get reminder date pressed
//        long date_reminder_pressed = prefs.getLong(PREF_DATE_REMINDER_PRESSED, 0);
//
//        try {
//            int appVersionCode = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
//            if (prefs.getInt(PREF_APP_VERSION_CODE, 0) != appVersionCode) {
//                //Reset the launch and event counters to help assure users are rating based on the latest version.
//                launch_count = 0;
//                event_count = 0;
//                editor.putLong(PREF_EVENT_COUNT, event_count);
//            }
//            editor.putInt(PREF_APP_VERSION_CODE, appVersionCode);
//        } catch (Exception e) {
//            //do nothing
//        }
//
//        launch_count++;
//        editor.putLong(PREF_LAUNCH_COUNT, launch_count);
//
//        if (date_firstLaunch == 0) {
//            date_firstLaunch = System.currentTimeMillis();
//            editor.putLong(PREF_DATE_FIRST_LAUNCHED, date_firstLaunch);
//        }
//
//        // Wait at least n days or m events before opening
//        if (launch_count >= mContext.getResources().getInteger(R.integer.appirator_launches_until_prompt)) {
//            long millisecondsToWait = mContext.getResources().getInteger(R.integer.appirator_days_until_prompt) * 24 * 60 * 60 * 1000L;
//            if (System.currentTimeMillis() >= (date_firstLaunch + millisecondsToWait) || event_count >= mContext.getResources().getInteger(R.integer.appirator_events_until_prompt)) {
//                if (date_reminder_pressed == 0) {
//                    if (prefs.getBoolean(PREF_APP_LOVE_CLICKED, false)) {
//                        showRateDialog(mContext, editor);
//                    } else {
//                        showLoveDialog(mContext, editor);
//                    }
//                } else {
//                    long remindMillisecondsToWait = mContext.getResources().getInteger(R.integer.appirator_days_before_reminding) * 24 * 60 * 60 * 1000L;
//                    if (System.currentTimeMillis() >= (remindMillisecondsToWait + date_reminder_pressed)) {
//                        if (prefs.getBoolean(PREF_APP_LOVE_CLICKED, false)) {
//                            showRateDialog(mContext, editor);
//                        } else {
//                            showLoveDialog(mContext, editor);
//                        }
//                    }
//                }
//            }
//        }
//
//        editor.commit();
//    }
//
//    public static void rateApp(@NonNull Context mContext) {
//        SharedPreferences prefs = mContext.getSharedPreferences(mContext.getPackageName() + ".appirater", 0);
//        SharedPreferences.Editor editor = prefs.edit();
//        rateApp(mContext, editor);
//    }
//
//    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
//    public static void significantEvent(@NonNull Context mContext) {
//        boolean testMode = mContext.getResources().getBoolean(R.bool.appirator_test_mode);
//        SharedPreferences prefs = mContext.getSharedPreferences(mContext.getPackageName() + ".appirater", 0);
//        if (!testMode && (prefs.getBoolean(PREF_DONT_SHOW, false) || prefs.getBoolean(PREF_RATE_CLICKED, false))) {
//            return;
//        }
//
//        long event_count = prefs.getLong(PREF_EVENT_COUNT, 0);
//        event_count++;
//        prefs.edit().putLong(PREF_EVENT_COUNT, event_count).apply();
//    }
//
//    private static void rateApp(@NonNull Context mContext, @Nullable final SharedPreferences.Editor editor) {
//        String apps = "com.android.chrome";
//        boolean installed = Device.checkAppInstall(mContext, apps);
//        if (installed) {
//            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(mContext.getString(R.string.appirator_market_url), mContext.getPackageName()))));
//            if (editor != null) {
//                editor.putBoolean(PREF_RATE_CLICKED, true);
//                editor.commit();
//            }
//        } else {
//            Toast.makeText(mContext, "Aplikasi Google Chrome tidak di temukan, Silahkan Intall Google Chrome terlebih dahulu", Toast.LENGTH_LONG).show();
//            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + apps));
//            mContext.startActivity(webIntent);
//        }
//    }
//
//    @SuppressLint("NewApi")
//    private static void showRateDialog(@NonNull final Context mContext, @Nullable final SharedPreferences.Editor editor) {
//        String appName = mContext.getString(R.string.app_name);
//        final Dialog dialog = new Dialog(mContext);
//
//        if (Build.VERSION.RELEASE.startsWith("1.") || Build.VERSION.RELEASE.startsWith("2.0") || Build.VERSION.RELEASE.startsWith("2.1")) {
//            //No dialog title on pre-froyo devices
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        } else if (mContext.getResources().getDisplayMetrics().densityDpi == DisplayMetrics.DENSITY_LOW || mContext.getResources().getDisplayMetrics().densityDpi == DisplayMetrics.DENSITY_MEDIUM) {
//            Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
//            int rotation = display.getRotation();
//            if (rotation == 90 || rotation == 270) {
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            } else {
//                dialog.setTitle(String.format(mContext.getString(R.string.rate_title), appName));
//            }
//        } else {
//            dialog.setTitle(String.format(mContext.getString(R.string.rate_title), appName));
//        }
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        ViewGroup parent = dialog.findViewById(R.id.contentHost);
////        LinearLayout layout = (LinearLayout)LayoutInflater.from(mContext).inflate(R.layout.appirater, null);
//        View layout = View.inflate(mContext, R.layout.appirater, parent);
//
//        TextView tv = (TextView) layout.findViewById(R.id.message);
//        tv.setText(String.format(mContext.getString(R.string.rate_message), appName));
//
//        Button rateButton = (Button) layout.findViewById(R.id.rate);
//        rateButton.setText(String.format(mContext.getString(R.string.rate), appName));
//        rateButton.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                rateApp(mContext, editor);
//                dialog.dismiss();
//            }
//        });
//
//        Button rateLaterButton = (Button) layout.findViewById(R.id.rateLater);
//        rateLaterButton.setText(mContext.getString(R.string.rate_later));
//        rateLaterButton.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                if (editor != null) {
//                    editor.putLong(PREF_DATE_REMINDER_PRESSED, System.currentTimeMillis());
//                    editor.commit();
//                }
//                dialog.dismiss();
//            }
//        });
//
//        Button cancelButton = (Button) layout.findViewById(R.id.cancel);
//        cancelButton.setText(mContext.getString(R.string.rate_cancel));
//        cancelButton.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                if (editor != null) {
//                    editor.putBoolean(PREF_DONT_SHOW, true);
//                    editor.commit();
//                }
//                dialog.dismiss();
//            }
//        });
//
//        dialog.setContentView(layout);
//        dialog.show();
//    }
//
//    private static void showLoveDialog(@NonNull final Context mContext, @Nullable final SharedPreferences.Editor editor) {
//
//        final Dialog dialog = new Dialog(mContext);
////        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        ViewGroup parent = dialog.findViewById(R.id.contentHost);
////        final LinearLayout dialogView = (LinearLayout) inflater.inflate(R.layout.promptdialog_custom_view, parent);
////        LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.loveapp, null);
//        View layout = View.inflate(mContext, R.layout.loveapp, parent);
//
//        TextView textView = layout.findViewById(R.id.love_dialog_message);
//        Button yesButton = layout.findViewById(R.id.love_dialog_yes);
//        Button noButton = layout.findViewById(R.id.love_dialog_no);
//
//        textView.setText(mContext.getString(R.string.love_dialog_content));
//        yesButton.setText(mContext.getString(R.string.love_dialog_yes));
//        noButton.setText(mContext.getString(R.string.love_dialog_no));
//
//        yesButton.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (editor != null) {
//                    editor.putBoolean(PREF_APP_LOVE_CLICKED, true);
//                    editor.commit();
//                }
//                dialog.dismiss();
//                showRateDialog(mContext, editor);
//            }
//        });
//
//        noButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(@NonNull View v) {
//                if (editor != null) {
//                    editor.putBoolean(PREF_DONT_SHOW, true);
//                    editor.commit();
//                }
//                dialog.dismiss();
//
//                Intent intent = new Intent();
////                intent.setAction("com.sbstrm.appirater.Appirater");
//                intent.setAction("com.bm.main.fpl.utils.Appirater");
//                intent.putExtra("HATE_APP", true);
//                v.getContext().sendBroadcast(intent);
//            }
//        });
//
//
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(layout, dialog.getWindow().getAttributes());
//        dialog.show();
//    }
//}
