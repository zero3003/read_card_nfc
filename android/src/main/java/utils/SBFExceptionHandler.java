package utils;//package utils;
//
//import android.app.AlarmManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.bm.main.scm.SBFApplication;
//import com.bm.main.scm.SplashScreenActivity;
//
///**
// * Created by sarifhidayat on 12/28/18.
// **/
//public class SBFExceptionHandler implements Thread.UncaughtExceptionHandler {
//    private AppCompatActivity activity;
//
//    public SBFExceptionHandler(AppCompatActivity a) {
//        activity = a;
//    }
//
//    @Override
//    public void uncaughtException(Thread thread, Throwable ex) {
//        Intent intent = new Intent(activity, SplashScreenActivity.class);
//        intent.putExtra("crash", true);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//                | Intent.FLAG_ACTIVITY_CLEAR_TASK
//                | Intent.FLAG_ACTIVITY_NEW_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(SBFApplication.getInstance().getBaseContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
//        AlarmManager mgr = (AlarmManager) SBFApplication.getInstance().getBaseContext().getSystemService(Context.ALARM_SERVICE);
//        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);
//        activity.finish();
//        System.exit(2);
//    }
//}
