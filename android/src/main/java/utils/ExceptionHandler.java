package utils;//package utils;
//
///*
// * Created by sarifhidayat on 4/3/18.
// */
//
//import android.content.Intent;
//import android.os.Build;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.bm.main.fpl.constants.Info;
//import com.bm.main.scm.SplashScreenActivity;
//import com.crashlytics.android.Crashlytics;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//
//import io.fabric.sdk.android.Fabric;
//
//public class ExceptionHandler implements
//        Thread.UncaughtExceptionHandler {
//    private final AppCompatActivity myContext;
//
//    public ExceptionHandler(AppCompatActivity context) {
//        myContext = context;
//
//        Fabric.with(myContext, new Crashlytics());
//    }
//
//    public void uncaughtException(Thread thread, @NonNull Throwable exception) {
//        String LINE_SEPARATOR = "\n";
//        StringWriter stackTrace = new StringWriter();
//        exception.printStackTrace(new PrintWriter(stackTrace));
////        String subject = "Error From: " + myContext.getClass().getName() + "\nEx: "
//        String subject = "Error From: " + myContext.getClass().getName();
//        StringBuilder errorReport = new StringBuilder();
//        errorReport.append(subject);
//        errorReport.append("************ CAUSE OF ERROR ************\n\n");
////        errorReport.append(stackTrace.toString().substring(0,500));
//        errorReport.append(stackTrace.toString());
//
//        errorReport.append("\n************ VERSION ***********\n");
//        errorReport.append("CODE: ");
//        errorReport.append(Info.getPackageInfo(myContext).versionName).append(" Rev 1");
//
//        errorReport.append(LINE_SEPARATOR);
//        errorReport.append("\n************ DEVICE INFORMATION ***********\n");
//        errorReport.append("Brand: ");
//        errorReport.append(Build.BRAND);
//
//        errorReport.append(LINE_SEPARATOR);
//        errorReport.append("Device: ");
//        errorReport.append(Build.DEVICE);
//        errorReport.append(LINE_SEPARATOR);
//        errorReport.append("Model: ");
//        errorReport.append(Build.MODEL);
//        errorReport.append(LINE_SEPARATOR);
//        errorReport.append("Id: ");
//        errorReport.append(Build.ID);
//        errorReport.append(LINE_SEPARATOR);
//        errorReport.append("Product: ");
//        errorReport.append(Build.PRODUCT);
//        errorReport.append(LINE_SEPARATOR);
//        errorReport.append("\n************ FIRMWARE ************\n");
//        errorReport.append("SDK: ");
//        errorReport.append(Build.VERSION.SDK_INT);
//        errorReport.append(LINE_SEPARATOR);
//        errorReport.append("Release: ");
//        errorReport.append(Build.VERSION.RELEASE);
//        errorReport.append(LINE_SEPARATOR);
//        errorReport.append("Incremental: ");
//        errorReport.append(Build.VERSION.INCREMENTAL);
//        errorReport.append(LINE_SEPARATOR);
//
//        Crashlytics.logException(exception);
//        Crashlytics.log(stackTrace.toString());
//
//        Crashlytics.setUserIdentifier(PreferenceClass.getId());
//        Crashlytics.setUserName(PreferenceClass.getUser().getNama_pemilik());
//        Crashlytics.setUserEmail(PreferenceClass.getUser().getEmail_pemilik());
//        Intent intent = new Intent(myContext, SplashScreenActivity.class);
//
//        myContext.startActivity(intent);
//        System.exit(2);
//        android.os.Process.killProcess(android.os.Process.myPid());
//    }
//}
