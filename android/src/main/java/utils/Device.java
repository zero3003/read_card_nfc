package utils;//package utils;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Build;
//import android.os.VibrationEffect;
//import android.os.Vibrator;
//import android.text.Spanned;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.bm.main.fpl.activities.BaseActivity;
//
//import java.io.File;
//
//import timber.log.Timber;
//
//public class Device {
//
//    public static void vibrate(Context context) {
//        Vibrator vibrator = ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE));
//        if (vibrator != null) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                vibrator.vibrate(VibrationEffect.createOneShot(150, 10));
//            } else {
//                vibrator.vibrate(150);
//            }
//        }
//    }
//
//    public static boolean checkAppInstall(Context context, String uri) {
//        PackageManager pm = context.getPackageManager();
//        try {
//            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
//            return true;
//        } catch (PackageManager.NameNotFoundException e) {
//            Timber.e(e);
//        }
//
//        return false;
//    }
//
//    public static void openWhatsApp(Context context, String apps, Uri url, @NonNull File file) {
//        try {
//            Intent share = new Intent();
//            share.setAction(Intent.ACTION_SEND);
//            share.setType("application/pdf");
//            share.setPackage(apps);
//            share.putExtra(Intent.EXTRA_STREAM, url);
//            context.startActivity(share);
//        } catch (IllegalArgumentException e) {
//            Timber.e("The selected file can't be shared: " + e.toString() + " " + file.toString());
//        }
//
//        DialogUtils.closeBootomSheetDialog();
//    }
//
//    public static void sendGmail(Context context, String apps, String subject, Spanned textPesan, Uri uri) {
//        Timber.i("Send email");
//        String[] TO = {""};
//        String[] CC = {""};
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//
//        emailIntent.setData(Uri.parse("mailto:"));
//        emailIntent.setType("text/plain");
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, "");
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        emailIntent.putExtra(Intent.EXTRA_TEXT, textPesan);
//        emailIntent.setType("application/pdf");
//        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
//        try {
//            emailIntent.setPackage(apps);
//            context.startActivity(emailIntent);
//            Timber.i("Finished sending email...");
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(context, "There is no Gmail client installed.", Toast.LENGTH_SHORT).show();
//        }
//        DialogUtils.closeBootomSheetDialog();
//    }
//
//    public static void sendYmail(Context context, String apps, String subject, Spanned textPesan, Uri uri) {
//        Timber.i("Send email");
//        String[] TO = {""};
//        String[] CC = {""};
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//
//        emailIntent.setData(Uri.parse("mailto:"));
//        emailIntent.setType("text/html");
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        emailIntent.putExtra(Intent.EXTRA_TEXT, textPesan);
//        emailIntent.setType("application/pdf");
//        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
//
//        try {
//            emailIntent.setPackage(apps);
//            context.startActivity(emailIntent);
//            Timber.i("Finished sending email...");
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(context, "There is no Yahoo Mail client installed.", Toast.LENGTH_SHORT).show();
//        }
//
//        DialogUtils.closeBootomSheetDialog();
//    }
//
//    public static void sendEmail(Context context, String apps, String subject, Spanned textPesan, Uri uri) {
//        Timber.i("Send email");
//        String[] TO = {""};
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//
//        emailIntent.setData(Uri.parse("mailto:"));
//        emailIntent.setType("text/html");
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        emailIntent.putExtra(Intent.EXTRA_TEXT, textPesan);
//        emailIntent.setType("application/pdf");
//        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
//        try {
//            emailIntent.setPackage(apps);
//            context.startActivity(emailIntent);
//            Timber.i("Finished sending email...");
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(context, "There is no Email client installed.", Toast.LENGTH_SHORT).show();
//        }
//
//        DialogUtils.closeBootomSheetDialog();
//    }
//
//    //    Facebook - "com.facebook.katana"
//    //    Twitter - "com.twitter.android"
//    //    Instagram - "com.instagram.android"
//    //    Pinterest - "com.pinterest"
//
//    /**
//     * This method SharingToSocialMedia.
//     *
//     * @param application share ke applikasi
//     *                    WA => Whatsup Apps,
//     *                    FB => Facebook Apps,
//     *                    TW => Twitter Apps
//     * @param url         url yang akan di bagikan
//     */
//
//    public static void SharingToSocialMedia(@NonNull Context context, @NonNull String application, Uri url) {
//        String apps = "";
//        if (application.equals("WA")) {
//            apps = "com.whatsapp";
//            boolean installed = Device.checkAppInstall(context, apps);
//            if (!installed) {
//                apps = "com.whatsapp.w4b";
//            }
//        } else if (application.equals("GMAIL")) {
//            apps = "com.google.android.gm";
//        } else if (application.equals("TELEGRAM")) {
//            apps = "org.telegram.messenger";
//        } else if (application.equals("BBM")) {
//            apps = "com.bbm";
//        }
//
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_SEND);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_STREAM, url);
//
//        boolean installed = Device.checkAppInstall(context, apps);
//        if (installed) {
//            intent.setPackage(apps);
//            if (PreferenceClass.getId().equals(PreferenceClass.getIdDemo()) && context instanceof BaseActivity) {
//                BaseActivity activity = ((BaseActivity) context);
//                activity.new_popup_alertDemo(activity, "Info", "Anda belum bisa menikmati fiture ini.\n" +
//                        "Daftar & Aktifasi sekarang juga ID Anda");
//            } else {
//                context.startActivity(intent);
//            }
//        } else {
//            Toast.makeText(context, "Installed application first", Toast.LENGTH_LONG).show();
//        }
//
//        DialogUtils.closeBootomSheetDialog();
//    }
//}
