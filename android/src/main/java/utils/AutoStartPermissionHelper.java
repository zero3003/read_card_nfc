package utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;

import java.util.List;

/**
 * Created by sarifhidayat on 2019-08-25.
 **/
public class AutoStartPermissionHelper  {
String TAG= AutoStartPermissionHelper.class.getSimpleName();
    private static AutoStartPermissionHelper instance = null;
    AutoStartPermissionHelper(){

    }

    /***
     * Xiaomi
     */
    private String BRAND_XIAOMI = "xiaomi";
    private String PACKAGE_XIAOMI_MAIN = "com.miui.securitycenter";
    private String PACKAGE_XIAOMI_COMPONENT = "com.miui.permcenter.autostart.AutoStartManagementActivity";

    /***
     * Letv
     */
    private String BRAND_LETV = "letv";
    private String PACKAGE_LETV_MAIN = "com.letv.android.letvsafe";
    private String PACKAGE_LETV_COMPONENT = "com.letv.android.letvsafe.AutobootManageActivity";

    /***
     * ASUS ROG
     */
    private String BRAND_ASUS = "asus";
    private String PACKAGE_ASUS_MAIN = "com.asus.mobilemanager";
    private String PACKAGE_ASUS_COMPONENT = "com.asus.mobilemanager.powersaver.PowerSaverSettings";

    /***
     * Honor
     */
    private String BRAND_HONOR = "honor";
    private String PACKAGE_HONOR_MAIN = "com.huawei.systemmanager";
    private String PACKAGE_HONOR_COMPONENT = "com.huawei.systemmanager.optimize.process.ProtectActivity";

    /**
     * Oppo
     */
    private String BRAND_OPPO = "oppo";
    private String PACKAGE_OPPO_MAIN = "com.coloros.safecenter";
    private String PACKAGE_OPPO_FALLBACK = "com.oppo.safe";
    private String PACKAGE_OPPO_COMPONENT = "com.coloros.safecenter.permission.startup.StartupAppListActivity";
    private String PACKAGE_OPPO_COMPONENT_FALLBACK = "com.oppo.safe.permission.startup.StartupAppListActivity";
    private String PACKAGE_OPPO_COMPONENT_FALLBACK_A = "com.coloros.safecenter.startupapp.StartupAppListActivity";

    /**
     * Vivo
     */

    private String BRAND_VIVO = "vivo";
    private String PACKAGE_VIVO_MAIN = "com.iqoo.secure";
    private String PACKAGE_VIVO_FALLBACK = "com.vivo.permissionmanager";
    private String PACKAGE_VIVO_COMPONENT = "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity";
    private String PACKAGE_VIVO_COMPONENT_FALLBACK = "com.vivo.permissionmanager.activity.BgStartUpManagerActivity";
    private String PACKAGE_VIVO_COMPONENT_FALLBACK_A = "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager";

    /**
     * Nokia
     */

    private String BRAND_NOKIA = "nokia";
    private String PACKAGE_NOKIA_MAIN = "com.evenwell.powersaving.g3";
    private String PACKAGE_NOKIA_COMPONENT = "com.evenwell.powersaving.g3.exception.PowerSaverExceptionActivity";

    public void getAutoStartPermission(Context context) {

        String build_info = Build.BRAND.toLowerCase();
        Log.d(TAG, "getAutoStartPermission: "+build_info);
        switch (build_info) {
            case "asus":

            autoStartAsus(context);
                break;
            case "xiaomi":
                autoStartXiaomi(context);
                break;

            case "letv":
            autoStartLetv(context);
                break;

            case "honor":
                autoStartHonor(context);
                break;

            case "oppo":
                autoStartOppo(context);
                break;
            case "vivo":
                autoStartVivo(context);
                break;
            case "nokia" :
                autoStartNokia(context);
                break;

                default:
                    break;
        }

    }

    private void autoStartXiaomi(Context context) {
        if (isPackageExists(context, PACKAGE_XIAOMI_MAIN)) {
            try {
                startIntent(context, PACKAGE_XIAOMI_MAIN, PACKAGE_XIAOMI_COMPONENT);
//                ifAlert(context,"Xiomi",PACKAGE_XIAOMI_MAIN, PACKAGE_XIAOMI_COMPONENT);
            } catch ( Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void autoStartAsus(Context context) {
        if (isPackageExists(context, PACKAGE_ASUS_MAIN)) {
            try {
                startIntent(context, PACKAGE_ASUS_MAIN, PACKAGE_ASUS_COMPONENT);
//                ifAlert(context, "Asus",PACKAGE_ASUS_MAIN, PACKAGE_ASUS_COMPONENT);
            } catch ( Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void autoStartLetv(Context context) {
        if (isPackageExists(context, PACKAGE_LETV_MAIN)) {
            try {
                startIntent(context, PACKAGE_LETV_MAIN, PACKAGE_LETV_COMPONENT);
//                ifAlert(context,"Letv", PACKAGE_LETV_MAIN, PACKAGE_LETV_COMPONENT);
            } catch ( Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void autoStartHonor(Context context) {
        if (isPackageExists(context, PACKAGE_HONOR_MAIN)) {
            try {
                startIntent(context, PACKAGE_HONOR_MAIN, PACKAGE_HONOR_COMPONENT);
//                ifAlert(context, "Honor",PACKAGE_HONOR_MAIN, PACKAGE_HONOR_COMPONENT);
            } catch ( Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void autoStartOppo(Context context) {
        if (isPackageExists(context, PACKAGE_OPPO_MAIN) || isPackageExists(context, PACKAGE_OPPO_FALLBACK)) {
            try {
                startIntent(context, PACKAGE_OPPO_MAIN, PACKAGE_OPPO_COMPONENT);
//                ifAlert(context, "Oppo",PACKAGE_OPPO_MAIN, PACKAGE_OPPO_COMPONENT);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    startIntent(context, PACKAGE_OPPO_FALLBACK, PACKAGE_OPPO_COMPONENT_FALLBACK);
//                    ifAlert(context, "Oppo",PACKAGE_OPPO_FALLBACK, PACKAGE_OPPO_COMPONENT_FALLBACK);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        startIntent(context, PACKAGE_OPPO_MAIN, PACKAGE_OPPO_COMPONENT_FALLBACK_A);
//                        ifAlert(context, "Oppo",PACKAGE_OPPO_MAIN, PACKAGE_OPPO_COMPONENT_FALLBACK_A);
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }

                }

            }

        }
    }

    private void autoStartVivo(Context context) {
      //  Log.d(TAG, "autoStartVivo: ");
        if (isPackageExists(context, PACKAGE_VIVO_MAIN) || isPackageExists(context, PACKAGE_VIVO_FALLBACK)) {
            try {
                startIntent(context, PACKAGE_VIVO_MAIN, PACKAGE_VIVO_COMPONENT);
//                ifAlert(context, "Vivo",PACKAGE_VIVO_MAIN, PACKAGE_VIVO_COMPONENT);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    startIntent(context, PACKAGE_VIVO_FALLBACK, PACKAGE_VIVO_COMPONENT_FALLBACK);
//                    ifAlert(context, "Vivo",PACKAGE_VIVO_FALLBACK, PACKAGE_VIVO_COMPONENT_FALLBACK);
                } catch ( Exception ex) {
                    ex.printStackTrace();
                    try {
                        startIntent(context, PACKAGE_VIVO_MAIN, PACKAGE_VIVO_COMPONENT_FALLBACK_A);
//                        ifAlert(context,"Vivo", PACKAGE_VIVO_MAIN, PACKAGE_VIVO_COMPONENT_FALLBACK_A);
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }

                }

            }

        }
    }

    private void autoStartNokia(Context context)  {
        if (isPackageExists(context, PACKAGE_NOKIA_MAIN)) {
            try {
                startIntent(context, PACKAGE_NOKIA_MAIN, PACKAGE_NOKIA_COMPONENT);
//                ifAlert(context,"Nokia", PACKAGE_NOKIA_MAIN, PACKAGE_NOKIA_COMPONENT);
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
    }
//    private void ifAlert(final Context context, String title, final String packageName, final String componentName) {
//        Log.d(TAG, "ifAlert: "+title+" "+packageName+" "+componentName);
//        final String saveIfSkip = "skipProtectedAppsMessage";
//        boolean skipMessage = PreferenceClass.getBoolean(saveIfSkip, false);
//        if (!skipMessage) {
////        DisplayUtils.showDialog(context, "Ask for permission", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i) {
////                try {
////                    Intent intent = new Intent();
////                    intent.setComponent(new ComponentName(packageName, componentName));
////                    context.startActivity(intent);
////                } catch (ActivityNotFoundException e) {
////                    Log.d(TAG, "onClick: Failed to launch AutoStart Screen ", e);
////                } catch (Exception e) {
////                    Log.d(TAG, "Failed to launch AutoStart Screen ", e);
////                }
////            }
////        }, new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i) {
////
////            }
////        });
//        final AppCompatCheckBox dontShowAgain = new AppCompatCheckBox(context);
//                dontShowAgain.setText("Do not show again");
//                dontShowAgain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        PreferenceClass.putBoolean(saveIfSkip, isChecked);
//                       // editor.apply();
//                    }
//                });
//        new AlertDialog.Builder(context)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setTitle(title+" Protected Apps")
//                        .setMessage(String.format("%s requires to be enabled in 'Protected Apps' to function properly.%n", context.getString(R.string.app_name)))
//                        .setView(dontShowAgain)
//                        .setPositiveButton("Protected Apps", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                try {
//                                    Intent intent = new Intent();
//                                    intent.setComponent(new ComponentName(packageName, componentName));
//                                    context.startActivity(intent);
//                                } catch (ActivityNotFoundException e) {
//                                    Log.d(TAG, "onClick: Failed to launch AutoStart Screen ", e);
//                                } catch (Exception e) {
//                                    Log.d(TAG, "Failed to launch AutoStart Screen ", e);
//                                }
//                            }
//                        })
//                        .setNegativeButton(android.R.string.cancel, null)
//                .setCancelable(false)
//                .show();
//
//    } else {
//                PreferenceClass.putBoolean(saveIfSkip, true);
////               // editor.apply();
//            }
//
//    }
//    private void ifAlert(Context context,String title,String packageName, String componentName) {
//       // final SharedPreferences settings = getSharedPreferences("ProtectedApps", MODE_PRIVATE);
//        final String saveIfSkip = "skipProtectedAppsMessage";
//        boolean skipMessage = PreferenceClass.getBoolean(saveIfSkip, false);
//        if (!skipMessage) {
//           // final SharedPreferences.Editor editor = settings.edit();
//            Intent intent = new Intent();
////            intent.setClassName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity");
//            intent=startIntent(context,packageName, componentName);
//            if (isCallable(intent,context)) {
//                final AppCompatCheckBox dontShowAgain = new AppCompatCheckBox(context);
//                dontShowAgain.setText("Do not show again");
//                dontShowAgain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        PreferenceClass.putBoolean(saveIfSkip, isChecked);
//                       // editor.apply();
//                    }
//                });
//
//                new AlertDialog.Builder(context)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setTitle("Huawei Protected Apps")
//                        .setMessage(String.format("%s requires to be enabled in 'Protected Apps' to function properly.%n", context.getString(R.string.app_name)))
//                        .setView(dontShowAgain)
//                        .setPositiveButton("Protected Apps", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                huaweiProtectedApps();
//                            }
//                        })
//                        .setNegativeButton(android.R.string.cancel, null)
//                        .show();
//            } else {
//                PreferenceClass.putBoolean(saveIfSkip, true);
//               // editor.apply();
//            }
//        }
//    }

    private boolean isCallable(Intent intent,Context context) {
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
   // @Throws(Exception::class)
    private void startIntent(Context context,String packageName, String componentName) {
        Intent intent = new Intent();
        try {


            ComponentName component = new ComponentName(packageName, componentName);
            intent.setComponent(component);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception exception) {
            exception.printStackTrace();

        }
       // return intent;

    }

//    List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
//        if  (list.size() > 0) {
//        startActivity(intent);
//    }


//    private boolean isPackageExists(Context context, String targetPackage) {
//         List<ApplicationInfo> packages;
//        PackageManager pm = context.getPackageManager();
//        packages = pm.getInstalledApplications(0);
//        for (ApplicationInfo packageInfo: packages) {
//            if (packageInfo.packageName == targetPackage) {
//                return true;
//            }
//        }
//        return false;
//    }

    private static boolean isPackageExists(Context context, String app){
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo pkgInfo = packageManager.getPackageInfo(app, 0);
            return (pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static boolean isPackageExists(Context context, String targetPackage){
//List<ApplicationInfo> packages=new ArrayList<ApplicationInfo>();
//        PackageManager pm = context.getPackageManager();
//        //try {
//           // PackageInfo pkgInfo = packageManager.getInstalledApplications(0);
////            return (pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
////        } catch (PackageManager.NameNotFoundException e) {
////            e.printStackTrace();
////        }
//        packages = pm.getInstalledApplications(0);
//        for (packages packageInfo:pm) {
//            if (pm.packageName == targetPackage) {
//                return true;
//            }
//        }
//        return false;
//
//    }



    public static AutoStartPermissionHelper getInstance() {
        if (instance == null) {
            instance = new AutoStartPermissionHelper();
        }
        return instance;
    }
}
