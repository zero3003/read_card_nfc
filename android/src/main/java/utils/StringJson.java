package utils;//package utils;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.os.Build;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
//import com.bm.main.fpl.activities.BaseActivity;
//import com.bm.main.fpl.constants.Info;
//import com.bm.main.fpl.constants.ProdukCode;
//
//import java.net.NetworkInterface;
//import java.text.SimpleDateFormat;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//import timber.log.Timber;
//
///**
// * Created by sarifhidayat on 10/31/17.
// */
//
//public class StringJson {
//
//    private static final String TAG = StringJson.class.getSimpleName();
//    private static String version;
//    private static String via = "MOBILE_SKALTIM";
//
//    @NonNull
//    private final String uuid;
//    //    private final PreferenceClass PreferenceClass;
//    private String location = "0,0";
//    private String place = "";
//    @NonNull
//    private String networkOperatorName = "";
//    @NonNull
//    private String subscriberId = "";
//    @NonNull
//    private String line1Number = "";
//    // SavePref savePref;
//    @NonNull
//    private String app_id = "SAMQ";
//
////    private TelephonyManager mTm;
//
//
//    private String mVersionCode;
//    private String mVersionName;
//    private String mScreenRes;
//    @NonNull
//    private String deviceId = "";
//
//
//    @SuppressLint("MissingPermission")
//    public StringJson(@NonNull Context context) {
//        uuid = getMacAddr() + "#" + System.getProperties().getProperty("http.agent");
//        Log.d("StringJson", uuid);
//
//        version = Info.getPackageInfo(context).versionName;
//        try {
//            mScreenRes = BaseActivity.displayMetrics.widthPixels + "x" + BaseActivity.displayMetrics.heightPixels;
//        } catch (Exception ignored) {
//            Log.d(TAG, "StringJson: " + ignored);
//
//        }
//        mVersionCode = String.valueOf(Info.getPackageInfo(context).versionCode);
//        mVersionName = Info.getPackageInfo(context).versionName;
//        location = PreferenceClass.getString("location", "0,0");
//        place = PreferenceClass.getString("place", "");
//    }
//
//    private static String getCurrentTimeStamp() {
//        @SuppressLint("SimpleDateFormat")
//        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
//        Date now = new Date();
//        return sdfDate.format(now);
//    }
//
//    @NonNull
//    private String getMacAddr() {
//        try {
//            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
//            for (NetworkInterface nif : all) {
//                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
//
//                byte[] macBytes = nif.getHardwareAddress();
//                if (macBytes == null) {
//                    return "";
//                }
//
//                StringBuilder res1 = new StringBuilder();
//                for (byte b : macBytes) {
//                    res1.append(Integer.toHexString(b & 0xFF)).append(":");
//                }
//
//                if (res1.length() > 0) {
//                    res1.deleteCharAt(res1.length() - 1);
//                }
//                return res1.toString();
//            }
//        } catch (Exception ex) {
//            return "02:00:00:00:00:00";
//        }
//        return "02:00:00:00:00:00";
//    }
//
//    /**
//     * Append additional parameters to user agent.
//     *
//     * @return Additional paramters.
//     */
//    @NonNull
//    private String appendUserAgent() {
//
//        return "ANDROID:" +
//                Build.MANUFACTURER +
//                ",MODEL:" +
//                Build.MODEL +
////                ",BRAND:" +
////                Build.BRAND +
//                ",RELEASE:" +
//                Build.VERSION.RELEASE +
//                ",SDK VERSION:" +
//                String.valueOf(Build.VERSION.SDK_INT) +
//                ",VERSION CODE:" +
//                mVersionCode +
//                ",VERSION NAME:" +
//                mVersionName +
//                ",SCREEN RES:" +
//                mScreenRes +
//                ",DEVICE ID:" +
//                deviceId +
//                ",OPERATOR NAME:" +
//                networkOperatorName +
//                ",LINE1 NUMBER:" +
//                line1Number +
//                ",LOCATION:" +
//                location +
//                ",PLACE:" +
//                place +
//                ",SUBCRIBER ID:" +
//                subscriberId;
//    }
//
//    public String requestCheckValidation(String pin) {
//        return requestCheckValidation(pin, PreferenceClass.getLoggedUser().getId());
//    }
//
//    @NonNull
//    public String requestAddtionalData() {
//        int delimIndex = location.indexOf(',');
//        String lat = location.substring(0, delimIndex);
//        Timber.d("lat: %s", lat);
//        String longi = location.substring(delimIndex+2, location.length() - 1);
//        Timber.d("longi: %s", longi);
//        return "{"
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\":\"" + appendUserAgent() + "\","
//                + "\"fcmregid\":\"\","
//                + "\"longitude\":\"" + longi + "\","
//                + "\"latitude\":\"" + lat + "\","
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"version\":\"" + version + "\","
//                + "\"version_code\":\"" + mVersionCode + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"tokenizer\" : \"\""
//                + "}";
//    }
//
//    @NonNull
//    public String requestCheckValidation(String pin, String id) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"getCheckValidation\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + id + "\","
//                + "\"pin\" : \"" + pin + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestCheckStatus(String id, String token) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"request_check_expired_time\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + id + "\","
//                + "\"pin\" : \"\","
//                + "\"session_id\" : \"\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"version\":\"" + version + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"fcmregid\":\"" + token + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestGetContenAjakBisnis() {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"get_content_link_ajak_bisnis\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestGetContenPanduanAjakBisnis() {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"get_content_panduan_ajak_bisnis\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestCheckConfig(String id) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"getChekConfig\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestCheckAktif(String id) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"getCheckAktivasi\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + id + "\","
//                + "\"pin\" : \"\","
//                + "\"session_id\" : \"\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestCheckAvailKey(String id) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"request_available_key\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + id + "\","
//                + "\"pin\" : \"\","
//                + "\"session_id\" : \"\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestGetDemo() {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"getDemo\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"\","
//                + "\"pin\" : \"\","
//                + "\"session_id\" : \"\""
//                + "},"
//
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestDataUploadBukti(String img_url, String bank, String nominal, String keterangan) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"getDataUploadBukti\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"keterangan\":\"" + keterangan + "\","
//                + "\"img_url\":\"" + img_url + "\","
//                + "\"nominal\":\"" + nominal + "\","
//                + "\"bank\":\"" + bank + "\","
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestCaraDeposit() {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"getCaraDeposit\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestSliderLogin() {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"list_slide_login\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"\","
//                + "\"pin\" : \"\","
//                + "\"session_id\" : \"\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestKey(String id, String pin, String rk) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"request_key\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + id + "\","
//                + "\"pin\"     : \"" + pin + "\","
//                + "\"rk\"     : \"" + rk + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String getReferalUpline(String upline) {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"getNamaPemilikUpline\","
//
//                + "\"credential_data\" : {"
//                + "\"id_upline\"     : \"" + upline + "\""
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestSignOn(String id, String pin, String key, String token) {
//        return "{\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"signon\","
//                + "\"credential_data\" : {"
//                + "\"id_outlet\" : \"" + id + "\","
//                + "\"pin\" : \"" + pin + "\","
//                + "\"key_validation\" : \"" + key + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\","
//                + "\"fcmregid\":\"" + token + "\""
//                + "}"
//                + "}";
//
//    }
//
//    @NonNull
//    public String requestSignOut(String id) {
//        return "{\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"signout\","
//                + "\"credential_data\" : {"
//                + "\"id_outlet\" : \"" + id + "\""
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//
//                + "}"
//                + "}";
//
//    }
//
//    @NonNull
//    public String update_long_lat(String key, String lat, String longi) {
//        return "{\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"update_long_lat\","
//                + "\"includes\" : {"
//                + "\"longitude\" : \"" + longi + "\","
//                + "\"latitude\" : \"" + lat + "\" },"
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"key_validation\" : \"" + key + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//
//    }
//
//    @NonNull
//    public String update_jenis_struk(String jenis_struk) {
//        return "{\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"update_jenis_struk\","
//                + "\"includes\" : {"
//                + "\"jenis_struk\" : \"" + jenis_struk + "\""
//                + " },"
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"key_validation\" : \""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestSliderHome() {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"list_slide\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestTomoProdukList() {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"list_produk_tomo\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestFMCGProdukList(String page, String query) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"list_produk_fmcg_new\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"page\" : \"" + page + "\","
//                + "\"query\" : \"" + query + "\""
//                + "},"
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestListKategoryNotif() {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"list_kategory_notif\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestListNotifHome() {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"list_notif_home\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPopUpPromoProduk(String produk) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"popupPromoProduk\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product\" : \"" + produk + "\""
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestListPelanggan(String id_produk) {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"list_pelanggan\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//                + "\"includes\" : {"
//                + "\"id_product\" : \"" + id_produk + "\""
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestHapusListPelanggan(String id_history, String id_produk) {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"hapus_list_pelanggan\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//                + "\"includes\" : {"
//                + "\"id_product\" : \"" + id_produk + "\","
//                + "\"id_history\" : \"" + id_history + "\""
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestRatingProduk(String id, String pin, String key, String token, String id_produk, String rating, String komentar) {
//        return "{\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"rating_produk\","
//                + "\"credential_data\" : {"
//                + "\"id_outlet\" : \"" + id + "\","
//                + "\"pin\" : \"" + pin + "\","
//                + "\"key_validation\" : \"" + key + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"version\":\"" + version + "\","
//                + "\"id_produk\":\"" + id_produk + "\","
//                + "\"rating\":\"" + rating + "\","
//                + "\"komentar\":\"" + komentar + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestOperatorSel() {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"list_opsel\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestHargaPulsa(String opsel_code) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"harga_pulsa\","
//
//                + "\"includes\" : {"
//                + "\"opsel_code\" : \"" + opsel_code + "\""
//                + "},"
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestKomisi() {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"rekap_komisi\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestInfo(String info, String id_kat) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"info\","
//
//                + "\"includes\" : {"
//                + "\"isi_komplain\" : \"" + info + "\","
//                + "\"id_kategory_komplain\" : \"" + id_kat + "\""
//                + "},"
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPropinsi() {
//        return "{"
//                + "\"msg_type\" : \"support\","
//                + "\"processing_code\" : \"list_propinsi\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"\","
//                + "\"pin\" : \"\","
//                + "\"session_id\" : \"\","
//                + "\"key_validation\" : \"\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestKota(String prop_code) {
//        return "{"
//                + "\"msg_type\" : \"support\","
//                + "\"processing_code\" : \"list_kota\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"\","
//                + "\"pin\" : \"\","
//                + "\"session_id\" : \"\","
//                + "\"key_validation\" : \"\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"prop_code\" : \"" + prop_code + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestKecamatan(String city_code) {
//        return "{"
//                + "\"msg_type\" : \"support\","
//                + "\"processing_code\" : \"list_kecamatan\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"\","
//                + "\"pin\" : \"\","
//                + "\"session_id\" : \"\","
//                + "\"key_validation\" : \"\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"city_code\" : \"" + city_code + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestKodePos(String kecamatan_code) {
//        return "{"
//                + "\"msg_type\" : \"support\","
//                + "\"processing_code\" : \"list_kodepos\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"\","
//                + "\"pin\" : \"\","
//                + "\"session_id\" : \"\","
//                + "\"key_validation\" : \"\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"kecamatan_code\" : \"" + kecamatan_code + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestListTypetLocket() {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"list_tipe_loket\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestLocket_register(String upline) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"list_tipe_loket_register\","
//
//                + "\"credential_data\" : {"
//
//                + "\"id_outlet\"     : \"\","
//                + "\"pin\" : \"\","
//                + "\"session_id\" : \"\""
//
//                + "},"
//                + "\"includes\" : {"
//
//                + "\"upline\" : \"" + upline + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPendaftaran(String no_Hape, String nama, String alamat, String prop_code, String city_code, String kode_pos, String type_loket) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"agensi\","
//
//                + "\"includes\" : {"
//                + "\"no_hp\" : \"" + no_Hape + "\","
//                + "\"nama\" : \"" + nama + "\","
//                + "\"alamat\" : \"" + alamat + "\","
//                + "\"prop_code\" : \"" + prop_code + "\","
//                + "\"city_code\" : \"" + city_code + "\","
//                + "\"kode_pos\" : \"" + kode_pos + "\","
//                + "\"tipe_loket\" : \"" + type_loket + "\""
//                + "},"
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestRegister(String no_Hape, String nama, String nama_toko, String alamat, String prop_code, String city_code, String kec_code, String email, String kode_pos, String type_loket, String upline) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"register_pos\","
//
//                + "\"includes\" : {"
//                + "\"up_line\" : \"" + upline + "\","
//                + "\"no_hp\" : \"" + no_Hape + "\","
//                + "\"nama\" : \"" + nama + "\","
//                + "\"nama_toko\" : \"" + nama_toko + "\","
//                + "\"alamat\" : \"" + alamat + "\","
//                + "\"prop_code\" : \"" + prop_code + "\","
//                + "\"city_code\" : \"" + city_code + "\","
//                + "\"kec_code\" : \"" + kec_code + "\","
//                + "\"email\" : \"" + email + "\","
//                + "\"kode_pos\" : \"" + kode_pos + "\","
//                + "\"tipe_loket\" : \"" + type_loket + "\""
//                + "},"
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"\","
//                + "\"pin\" : \"\","
//                + "\"session_id\" : \"\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestTransfer(String id_tujuan, String transfer, String pin) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"transfer\","
//
//                + "\"includes\" : {"
//                + "\"id_outlet_tujuan\" : \"" + id_tujuan + "\","
//                + "\"nilai_transfer\" : \"" + transfer + "\""
//                + "},"
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + pin + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestTypeKomplain() {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"list_kategori_komplain\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestBank(String jenis) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"list_bank\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"jenis\":\"" + jenis + "\","
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestDeposit(String code_bank, String amount) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"deposit\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"bank_code\" : \"" + code_bank + "\","
//                + "\"deposit_amount\" : \"" + amount + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestHistoryDeposit() {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"list_tiket_deposit\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestListProduct(String typeProduct) {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"list_produk\","
//
//                + "\"includes\" : {"
//                + "\"main_product_code\" : \"" + typeProduct + "\""
//                + "},"
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPLNpascabayar(String id_pelanggan, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.PLNPASC + "\","
//                + "\"id_pelanggan\" : \"" + id_pelanggan + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentPLN(String id_pelanggan, String id_inq, String nominal_inq, String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.PLNPASC + "\","
//                + "\"id_pelanggan\" : \"" + id_pelanggan + "\","
//                + "\"reff_id_inq\" : \"" + id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPLNNontalgis(String id_pelanggan, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.PLNNON + "\","
//                + "\"nomor_registrasi\" : \"" + id_pelanggan + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentPLNNontalgis(String id_pelanggan, String id_inq, String nominal_inq, String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.PLNNON + "\","
//                + "\"nomor_registrasi\" : \"" + id_pelanggan + "\","
//                + "\"reff_id_inq\" : \"" + id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPLNPrabayar(String id_pelanggan, String denom, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.PLNPRA + "\","
//                + "\"id_pelanggan\" : \"" + id_pelanggan + "\","
//                + "\"denom\" : \"" + denom + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentPLNPrabayar(String id_pelanggan, String id_inq, String nominal_inq, String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.PLNPRA + "\","
//                + "\"id_pelanggan\" : \"" + id_pelanggan + "\","
//                + "\"reff_id_inq\" : \"" + id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"denom\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPulsaPrefix(String notel) {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"init_prefix_pulsa\","
//
//                + "\"includes\" : {"
//                + "\"nomor_handphone\" : \"" + notel + "\""
//
//                + "},"
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPulsaList(String notel, String type) {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"list_produk_pulsa\","
//
//                + "\"includes\" : {"
//                + "\"nomor_handphone\" : \"" + notel + "\","
//                + "\"type\" : \"" + type + "\""
//                + "},"
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPulsaPayment(String code, String notel) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + code + "\","
//                + "\"nomor_handphone\" : \"" + notel + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestInquiryPDAM(String product_code, String id, String nometer, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + product_code + "\","
//                + "\"kode_pelanggan\" : \"" + id + "\","
//                + "\"nomor_sambungan\" : \"" + nometer + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentPDAM(String product_code,
//                                     String id_pelanggan,
//                                     String nometer,
//                                     String reff_id_inq,
//                                     String nominal_inq,
//                                     String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + product_code + "\","
//                + "\"kode_pelanggan\" : \"" + id_pelanggan + "\","
//                + "\"nomor_sambungan\" : \"" + nometer + "\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestInquiryBPJSKS(String nomor_va, String no_hp, String month_value, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.ASRBPJSKS + "\","
//                + "\"nomor_va\" : \"" + nomor_va + "\","
//                + "\"nomor_hp\" : \"" + no_hp + "\","
//                + "\"month_value\" : \"" + month_value + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentBPJSKES(
//            String nomor_va,
//            String no_hp,
//            String month_value,
//            String reff_id_inq,
//            String nominal_inq,
//            String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.ASRBPJSKS + "\","
//                + "\"nomor_va\" : \"" + nomor_va + "\","
//                + "\"nomor_hp\" : \"" + no_hp + "\","
//                + "\"month_value\" : \"" + month_value + "\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestSpeedyInquiry(String no_speedy, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.TELKOM_SPEEDY + "\","
//                + "\"nomor_speedy\" : \"" + no_speedy + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentSpeedy(String nomor_speedy,
//                                       String reff_id_inq,
//                                       String nominal_inq,
//                                       String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.TELKOM_SPEEDY + "\","
//                + "\"nomor_speedy\" : \"" + nomor_speedy + "\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPgnInquiry(String id_pelanggan, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.PGN + "\","
//                + "\"id_pelanggan\" : \"" + id_pelanggan + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentPgn(String id_pelanggan,
//                                    String reff_id_inq,
//                                    String nominal_inq,
//                                    String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.PGN + "\","
//                + "\"id_pelanggan\" : \"" + id_pelanggan + "\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestInquiryPajak(String product_code, String id, String tahun, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + product_code + "\","
//                + "\"id_pelanggan\" : \"" + id + "\","
//                + "\"tahun\" : \"" + tahun + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentPajak(String product_code,
//                                      String id_pelanggan,
//                                      String tahun,
//                                      String reff_id_inq,
//                                      String nominal_inq,
//                                      String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + product_code + "\","
//                + "\"id_pelanggan\" : \"" + id_pelanggan + "\","
//                + "\"tahun\" : \"" + tahun + "\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//
//    /*TODO PKB*/
//    @NonNull
//    public String requestInquiryPKB(String product_code, String id, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + product_code + "\","
//                + "\"id_pelanggan\" : \"" + id + "\","
//
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentPKB(String product_code,
//                                    String id_pelanggan,
//
//                                    String reff_id_inq,
//                                    String nominal_inq,
//                                    String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + product_code + "\","
//                + "\"id_pelanggan\" : \"" + id_pelanggan + "\","
//
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestTeleponInquiry(String kodeArea, String noTelepon, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.TELKOM_TELEPON + "\","
//                + "\"kode_area\" : \"" + kodeArea + "\","
//                + "\"no_telp\" : \"" + noTelepon + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentTelkom(String kode_area,
//                                       String no_telepon,
//                                       String reff_id_inq,
//                                       String nominal_inq,
//                                       String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.TELKOM_TELEPON + "\","
//                + "\"kode_area\" : \"" + kode_area + "\","
//                + "\"no_telp\" : \"" + no_telepon + "\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestProviderGame() {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"list_provider_game\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestProviderTopup() {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"list_produk_topup\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestProductGame(String code) {
//        return "{"
//                + "\"msg_type\" : \"menu\","
//                + "\"processing_code\" : \"list_produk_game\","
//
//                + "\"includes\" : {"
//                + "\"provider_code\" : \"" + code + "\""
//                + "},"
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentGame(String code, String notel) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + code + "\","
//                + "\"nomor_handphone\" : \"" + notel + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//
//    }
//
//    @NonNull
//    public String requestInquiryCicilan(String code, String id, String setoran, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + code + "\","
//                + "\"nomor_kontrak\" : \"" + id + "\","
//                + "\"jumlah_setoran\" : \"" + setoran + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentCicilan(String product_code,
//                                        String id_pelanggan,
//                                        String nominal,
//                                        String reff_id_inq,
//                                        String nominal_inq,
//                                        String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + product_code + "\","
//                + "\"nomor_kontrak\" : \"" + id_pelanggan + "\","
//                + "\"jumlah_setoran\" : \"" + nominal + "\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPertagasInquiry(String id_pelanggan, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.PERTAGAS + "\","
//                + "\"id_pelanggan\" : \"" + id_pelanggan + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestInquiryKartuKredit(String code, String id, String setoran, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + code + "\","
//                + "\"nomor_kartu_kredit\" : \"" + id + "\","
//                + "\"jumlah_setoran\" : \"" + setoran + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentKartuKredit(String product_code,
//                                            String id_pelanggan,
//                                            String nominal,
//                                            String reff_id_inq,
//                                            String nominal_inq,
//                                            String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + product_code + "\","
//                + "\"nomor_kartu_kredit\" : \"" + id_pelanggan + "\","
//                + "\"jumlah_setoran\" : \"" + nominal + "\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestInquiryTelpPasca(String code, String id, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + code + "\","
//                + "\"nomor_handphone\" : \"" + id + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentTelpPasca(String product_code,
//                                          String id_pelanggan,
//                                          String reff_id_inq,
//                                          String nominal_inq,
//                                          String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + product_code + "\","
//                + "\"nomor_handphone\" : \"" + id_pelanggan + "\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestInquiryTvKabel(String code, String id, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + code + "\","
//                + "\"nomor_pelanggan\" : \"" + id + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentTvKabel(String product_code,
//                                        String id_pelanggan,
//                                        String reff_id_inq,
//                                        String nominal_inq,
//                                        String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + product_code + "\","
//                + "\"nomor_pelanggan\" : \"" + id_pelanggan + "\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    /*TODO ASURANSI*/
//    @NonNull
//    public String requestInquiryAsuransi(String code, String nomor_polis, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + code + "\","
//                + "\"nomor_polis\" : \"" + nomor_polis + "\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentAsuransi(String product_code,
//                                         String nomor_polis,
//                                         String reff_id_inq,
//                                         String nominal_inq,
//                                         String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + product_code + "\","
//                + "\"nomor_polis\" : \"" + nomor_polis + "\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//
//    @NonNull
//    public String requestCheckUpdateApps() {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"checkUpdate\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"\","
//                + "\"pin\" : \"\","
//                + "\"session_id\" : \"\""
//                + "},"
//
////                +"\"includes\" : {"
////                +"\"recent_version_code\" : \""+recent_version_code+"\","
////                +"\"recent_version_name\" : \""+recent_version_name+"\""
//
////                +"},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestReportKomisi(String startDate, String endDate) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"data_komisi\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//                + "\"includes\" : {"
//                + "\"start_date\" : \"" + startDate + "\","
//                + "\"end_date\" : \"" + endDate + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    /*laporan transaksi*/
//    @NonNull
//    public String requestLaporanTransaksi(String start_date, String end_date, String no_pelanggan) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"data_transaksi\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"start_date\" : \"" + start_date + "\","
//                + "\"end_date\" : \"" + end_date + "\","
//                + "\"offset\" : \"" + 0 + "\","
//                + "\"limit\" : \"" + 100 + "\","
//                + "\"id_pelanggan\" : \"" + no_pelanggan + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestCetakTransaksi(String id_transaksi) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"cetak_ulang\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"id_transaksi\" : \"" + id_transaksi + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//
//    /*laporan mutasi*/
//    @NonNull
//    public String requestLaporanMutasi(String start_date, String end_date) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"data_report_mutasi\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"start_date\" : \"" + start_date + "\","
//                + "\"end_date\" : \"" + end_date + "\","
//                + "\"offset\" : \"" + 0 + "\","
//                + "\"limit\" : \"" + 100 + "\""
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//
//    @NonNull
//    public String requestCekSaldo() {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"saldo\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//
//    }
//
//    @NonNull
//    public String requestDescreptionMerchant(String kode) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"list_desc_merchant_deposit\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"kode_merchant\" : \"" + kode + "\""
//
//
//                + "},"
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//
//    }
//
//    @NonNull
//    public String requestCheckVaOutlet(String kode) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"check_no_va_outlet\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"bank_code\" : \"" + kode + "\""
//
//
//                + "},"
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//
//    }
//
//    @NonNull
//    public String requestListBankVA() {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"list_bank_va\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//
//    }
//
//
////    public String requestListDescBankVA(String type_va, String code_va) {
////        return "{"
////                + "\"msg_type\" : \"admin\","
////                + "\"processing_code\" : \"list_desc_va_deposit\","
////
////                + "\"credential_data\" : {"
////                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
////                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
////                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\""
////                + "},"
////                + "\"includes\" : {"
////                + "\"type_va\" : \"" + type_va + "\","
////                + "\"code_va\" : \"" + code_va + "\","
////                + "\"no_va\" : \"34534534534543\""
////
////
////                + "},"
////
////
////                + "\"additional_data\" : {"
////                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
////                + "\"uuid\":\"" + uuid + "\","
////                + "\"app_id\":\"MobileSBF\","
////                + "\"version\":\"" + version + "\""
////                + "}"
////                + "}";
////
////    }
//
//    @NonNull
//    public String requestListDownline() {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"getLevel1Downline\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\""
//                + "},"
//                + "\"includes\" : {"
//                + "\"upline\" :  \"" + PreferenceClass.getLoggedUser().getId() + "\""
//
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//
//    @NonNull
//    public String requestJumlahDownline() {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"getJumlahDownline\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\""
//                + "},"
//                + "\"includes\" : {"
//                + "\"upline\" :  \"" + PreferenceClass.getLoggedUser().getId() + "\""
//
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestKomisiDownline() {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"getKomisiDownline\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\""
//                + "},"
//                + "\"includes\" : {"
//                + "\"upline\" :  \"" + PreferenceClass.getLoggedUser().getId() + "\""
//
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
////    public String requestLaporanKomisi(String tanggalAwal, String tanggalAkhir) {
////        return "{"
////                + "\"msg_type\" : \"admin\","
////                + "\"processing_code\":\"data_komisi\","
////                + "\"credential_data\" : {"
////                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
////                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
////                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\""
////                + "},"
////                + "\"includes\" : {"
////                + "\"start_date\":\"" + tanggalAwal + "\","
////                + "\"end_date\":\"" + tanggalAkhir + "\""
////                + "\" },"
////                + "\"additional_data\" : {"
////                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
////                + "\"uuid\":\"" + uuid + "\","
////                + "\"app_id\":\"MobileSBF\","
////                + "\"version\":\"" + version + "\""
////                + "}"
////                + "}";
////    }
//
//
//    @NonNull
//    public String requestUpdateLoket(String nama_loket, String alamat_loket, String notelp_loket, String nomor_whatapp) {
//
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"update_loket\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"nama_loket\" : \"" + nama_loket + "\","
//                + "\"alamat_loket\" : \"" + alamat_loket + "\","
//                + "\"notelp_loket\" : \"" + notelp_loket + "\","
//                + "\"nomor_whatapp\" : \"" + nomor_whatapp + "\""
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//
//
//    }
//
//    // TODO: 11/30/18  perbankan request json
////    public String requestListBankLakuPandai() {
////        return "{"
////                + "\"msg_type\" : \"menu\","
////                + "\"processing_code\" : \"list_bank_lakupandai\","
////
////                + "\"credential_data\" : {"
////                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
////                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
////                + "\"session_id\" : \"" + PreferenceClass.getUser().getSession_id() + "\","
////                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
////                + "},"
////
////
////                + "\"additional_data\" : {"
////                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
////                + "\"uuid\":\"" + uuid + "\","
////                + "\"app_id\":\"" + app_id + "\","
////                + "\"device_information\" : \"" + appendUserAgent() + "\","
////                + "\"version\":\"" + version + "\""
////                + "}"
////                + "}";
////    }
////
////    /*TODO TV BERLANGGANAN*/
//
//    @NonNull
//    public String requestInquirySetorTunai(String no_rek, String nominal, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.SETORTUNAI + "\","
//                + "\"bni_nomor_rekening\" : \"" + no_rek + "\","
//                + "\"bni_nominal\" : \"" + nominal + "\","
//                + "\"tipe\" : \"SETORTUNAI\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentSetorTunai(
//            String no_rek,
//            String reff_id_inq,
//            String nominal_inq,
//            String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.SETORTUNAI + "\","
//                + "\"bni_nomor_rekening\" : \"" + no_rek + "\","
//                + "\"bni_nominal\" : \"" + nominal_inq + "\","
//                + "\"tipe\" : \"SETORTUNAI\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestInquiryTarikTunai(String no_rek, String nominal, String isSave) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.TARIKTUNAI1 + "\","
//                + "\"bni_nomor_rekening\" : \"" + no_rek + "\","
//                + "\"bni_nominal\" : \"" + nominal + "\","
//                + "\"tipe\" : \"TARIKTUNAI\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentTarikTunai(String otp,
//                                           String no_rek,
//                                           String nominal,
//                                           String reff_id_inq,
//
//                                           String nominal_inq,
//                                           String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.TARIKTUNAI1 + "\","
//                + "\"bni_nomor_rekening\" : \"" + no_rek + "\","
//                + "\"bni_nominal\" : \"" + nominal + "\","
//                + "\"bni_otp\" : \"" + otp + "\","
//                + "\"tipe\" : \"TARIKTUNAI\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestInquiryTransferTunaiAllBank(String no_rek, String bank_code,
//                                                     String bank_name, String nominal, String isSave, String phone) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"inquiry\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.TRANSFERTUNAIALLBANK + "\","
//                + "\"bni_nomor_rekening\" : \"" + no_rek + "\","
//                + "\"bni_bank_tujuan\" : \"" + bank_code + "\","
//                + "\"bni_bank_tujuan_nama\" : \"" + bank_name + "\","
//                + "\"bni_nominal\" : \"" + nominal + "\","
//                + "\"bni_nomor_hp\" : \"" + phone + "\","
//                + "\"tipe\" : \"TRANSFERBNIALL\","
//                + "\"is_save\" : \"" + isSave + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//    @NonNull
//    public String requestPaymentTransferTunaiAllBank(
//            String no_rek,
//            String bank_code,
//            String bank_name,
//            String reff_id_inq,
//            String nominal_inq,
//            String admin_inq) {
//        return "{"
//                + "\"msg_type\" : \"transaction\","
//                + "\"processing_code\" : \"payment\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"product_code\" : \"" + ProdukCode.TRANSFERTUNAIALLBANK + "\","
//                + "\"bni_nomor_rekening\" : \"" + no_rek + "\","
//                + "\"bni_nominal\" : \"" + nominal_inq + "\","
//                + "\"bni_bank_tujuan\" : \"" + bank_code + "\","
//                + "\"bni_bank_tujuan_nama\" : \"" + bank_name + "\","
//                + "\"tipe\" : \"TRANSFERBNIALL\","
//                + "\"reff_id_inq\" : \"" + reff_id_inq + "\","
//                + "\"nominal_inq\" : \"" + nominal_inq + "\","
//                + "\"nominal_admin_inq\" : \"" + admin_inq + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//
//    @NonNull
//    public String requestTopUpIndoMaret(
//
//            String nominal) {
//        return "{"
//                + "\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"request_otp_indomaret\","
//
//                + "\"credential_data\" : {"
//                + "\"id_outlet\"     : \"" + PreferenceClass.getLoggedUser().getId() + "\","
//                + "\"pin\" : \"" + PreferenceClass.getLoggedUser().getPin() + "\","
//                + "\"session_id\" : \"" + PreferenceClass.getLoggedUser().getSession_id() + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"includes\" : {"
//                + "\"nominal\" : \"" + nominal + "\""
//
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"version\":\"" + version + "\""
//                + "}"
//                + "}";
//    }
//
//
////    TRAVEL
////GENERAL
//
//    @NonNull
//    public String requestSignOnTravel(String id, String pin, String token) {
//
////        return "{"
////                + "\"outletId\" : \"" + id + "\","
////                + "\"pin\" : \"" + pin + "\","
////                + "\"key\" : \"" + key + "\","
////                + "\"fcmregid\" : \"" + token + "\""
////                + "}";
//        return "{\"msg_type\" : \"admin\","
//                + "\"processing_code\" : \"sign_in_FT\","
//                + "\"credential_data\" : {"
//                + "\"id_outlet\" : \"" + id + "\","
//                + "\"pin\" : \"" + pin + "\","
//                + "\"key_validation\" : \"" + PreferenceClass.getLoggedUser().getKey() + "\""
//                + "},"
//
//                + "\"additional_data\" : {"
//                + "\"transmission_datetime\":\"" + getCurrentTimeStamp() + "\","
//                + "\"uuid\":\"" + uuid + "\","
//                + "\"app_id\":\"" + app_id + "\","
//                + "\"device_information\" : \"" + appendUserAgent() + "\","
//                + "\"version\":\"" + version + "\","
//                + "\"via\" : \"" + via + "\","
//                + "\"fcmregid\":\"" + token + "\""
//                + "}"
//                + "}";
////
//    }
//
////    public String requestSignOnTravel(String id, String pin, String key,String token) {
////
//////        return "{"
//////                + "\"outletId\" : \"" + id + "\","
//////                + "\"pin\" : \"" + pin + "\","
//////                + "\"key\" : \"" + key + "\","
//////                + "\"fcmregid\" : \"" + token + "\""
//////                + "}"
////        return "{\"outletId\" : \"" + id + "\","
////                + "\"pin\" : \"" + pin + "\","
////                + "\"key\" :\"" + key + "\","
////                + "\"fcmregid\":\"" + token + "\""
////
////                + "}";
////
////    }
//}
