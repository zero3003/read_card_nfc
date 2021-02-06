package utils;

import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by skyshi on 19/01/17.
 */

public class PreferenceClass implements Parcelable {
    private static final String TAG = "sharedPreference";
    private static SharedPreferences sharedPreferences;
    private static int PRIVATE_MODE = 0;
    @NonNull
    private static String PREF_NAME = "SBFPOS";
    //TOKEN = "token"

    @NonNull
    private static String token_pos = "token_pos";
    private static String macAddrPrinter = "macAddrPrinter";

    private static final String TAG_TOKEN = "tagtoken";

    @NonNull
    private static String id = "id";
    @NonNull
    private static String pin = "pin";
    @NonNull
    private static String key = "key";
    @NonNull
    private static String token = "token";

    @NonNull
    private static String id_demo = "id_demo";
    @NonNull
    private static String pin_demo = "pin_demo";
    @NonNull
    private static String key_demo = "key_demo";
    @NonNull
    private static String key_edukasi_login = "edukasi_login";

    @NonNull
    private static String nama_pemilik = "nama_pemilik";
    @NonNull
    private static String notelp_pemilik = "notelp_pemilik";

    @NonNull
    private static String info = "info";

    private static final String session_id = "session_id";
    private static final String expired_time = "expired_time";
    private static final String response_code = "response_code";
    private static final String response_desc = "response_desc";
    private static final String is_Loggedin = "isLoggedin";
    private static final String is_locked = "isLocked";
    private static final String upline = "upline";
    private static final String auth = "auth";
    private static final String linkAfiliasi = "linkAfiliasi";
    private static final String webReport = "webReport";
    private static final String keagenan = "keagenan";
    private static final String belanja = "belanja";
    private static final String bayar = "bayar";
    private static final String lionParcel = "lionParcel";

    @NonNull
    private static String lastUrlGrosir = "lastUrlGrosir";
    //new preference based object user
    private static final String usermodel = "user";

    public static final String PREF_LAUNCH_COUNT = "launch_count";
    public static final String PREF_EVENT_COUNT = "event_count";
    public static final String PREF_RATE_CLICKED = "rateclicked";
    public static final String PREF_DONT_SHOW = "dontshow";
    public static final String PREF_DATE_REMINDER_PRESSED = "date_reminder_pressed";
    public static final String PREF_DATE_FIRST_LAUNCHED = "date_firstlaunch";
    public static final String PREF_APP_VERSION_CODE = "versioncode";
    public static final String PREF_APP_LOVE_CLICKED = "loveclicked";

    public PreferenceClass(Parcel in) {
    }

    public static final Creator<PreferenceClass> CREATOR = new Creator<PreferenceClass>() {
        @NonNull
        @Override
        public PreferenceClass createFromParcel(Parcel in) {
            return new PreferenceClass(in);
        }

        @NonNull
        @Override
        public PreferenceClass[] newArray(int size) {
            return new PreferenceClass[size];
        }
    };

    public static void clear() {
        sharedPreferences.edit().clear().apply();
    }

//    public static void storedLoggedUser(@NonNull SignOn signOn) {
//        sharedPreferences.edit().
//                putBoolean(is_Loggedin, true).
//                putString(response_code, signOn.getResponse_code()).
//                putString(response_desc, signOn.getResponse_desc()).
//                putString(session_id, signOn.getSession_id()).
//                putString(expired_time, signOn.getExpired_time()).
//                putString(id, signOn.getId()).
//                putString(pin, signOn.getPin()).
//                putString(key, signOn.getKey()).
//                putString(nama_pemilik, signOn.getNama_pemilik()).
//                putString(notelp_pemilik, signOn.getNotelp_pemilik()).
//                apply();
//    }
//
//    @NonNull
//    public static SignOn getLoggedUser() {
//        SignOn signOn = new SignOn();
//        signOn.setResponse_code(sharedPreferences.getString(response_code, ""));
//        signOn.setResponse_desc(sharedPreferences.getString(response_desc, ""));
//        signOn.setSession_id(sharedPreferences.getString(session_id, ""));
//        signOn.setExpired_time(sharedPreferences.getString(expired_time, ""));
//        signOn.setId(sharedPreferences.getString(id, ""));
//        signOn.setPin(sharedPreferences.getString(pin, ""));
//        signOn.setKey(sharedPreferences.getString(key, ""));
//        signOn.setNama_pemilik(sharedPreferences.getString(nama_pemilik, ""));
//        signOn.setNotelp_pemilik(sharedPreferences.getString(notelp_pemilik, ""));
//        return signOn;
//    }
//
//    private static void getClearLoggedUser() {
//        SignOn signOn = new SignOn();
//        signOn.setResponse_code("");
//        signOn.setResponse_desc("");
//        signOn.setSession_id("");
//        signOn.setExpired_time("");
//
//        signOn.setId("");
//        signOn.setPin("");
//        signOn.setKey("");
//        signOn.setNama_pemilik("");
//        signOn.setNotelp_pemilik("");
//
//        putString("last_id", getId());
//
//        setId("");
//        setPin("");
//        setLocked();
//
////        putBoolean("otp_set", false);
////        setKey("");
//
//        setAuth("");
//
//        setNotelp_pemilik("");
//    }
//
//    public static boolean isLoggedIn() {
//        long timeNow = Calendar.getInstance().getTimeInMillis();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        Timber.d("timenow : %s", sdf.format(timeNow));
//        try {
//            Timber.d("TimeExpiry : %s", sharedPreferences.getString(expired_time, ""));
//        } catch (Exception ignored) {
//        }
//
//        return sharedPreferences.getBoolean(is_Loggedin, false);
//    }

//    public static void setLogOut() {
//        sharedPreferences.edit().putBoolean(is_Loggedin, false).apply();
//        getClearLoggedUser();
//        clearToken();
//        clearTokenPos();
//    }

    public static void setLocked() {
        sharedPreferences.edit().putBoolean(is_locked, true).apply();
    }

    public static void setUnlock() {
        sharedPreferences.edit().putBoolean(is_locked, false).apply();
    }

    public static boolean isLocked() {
        return sharedPreferences.getBoolean(is_locked, false);
    }

    @Nullable
    public static String getPin() {
        return sharedPreferences.getString(pin, "");
    }

    public static void setPin(String value) {
        sharedPreferences.edit().putString(pin, value).apply();
    }


    public static String getKey() {
        return sharedPreferences.getString(key, "");
    }

    public static void setKey(String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    @Nullable
    public static String getId() {
        return sharedPreferences.getString(id, "");
    }

    public static void setId(String value) {
        sharedPreferences.edit().putString(id, value).apply();
    }

    @Nullable
    public static String getToken() {
        return sharedPreferences.getString(token, "");
    }

    public static void setToken(String value) {
        sharedPreferences.edit().putString(token, value).apply();
    }

    public static void clearToken() {
        sharedPreferences.edit().putString(token, "").apply();
    }

    @Nullable
    public static String getTokenPos() {
        return sharedPreferences.getString(token_pos, "");
    }

    public static void setTokenPos(String value) {
        sharedPreferences.edit().putString(token_pos, value).apply();
    }

    public static void clearTokenPos() {
        sharedPreferences.edit().putString(token_pos, "").apply();
    }

    public static void saveMacAddrPrinter(String value) {
        sharedPreferences.edit().putString(macAddrPrinter, value).apply();
    }

    @Nullable
    public static String getMacAddrPrinter() {
        return sharedPreferences.getString(macAddrPrinter, "");
    }

    @Nullable
    public static String getNotelp_pemilik() {
        return sharedPreferences.getString(notelp_pemilik, "");
    }

    public static void setNotelp_pemilik(String value) {
        sharedPreferences.edit().putString(notelp_pemilik, value).apply();
    }


    public static void setUpline(String sUpline) {
        sharedPreferences.edit().putString(upline, sUpline).apply();
    }

    @Nullable
    public static String getUpline() {
        return sharedPreferences.getString(upline, "");
    }

    public static void setAuth(String sAuth) {
        sharedPreferences.edit().putString(auth, sAuth).apply();
    }

    @Nullable
    public static String getAuth() {
        return sharedPreferences.getString(auth, "");
    }

//    public static void storedUser(UserModel userModel) {
//        Gson gson = new Gson();
//        String user = gson.toJson(userModel);
//        sharedPreferences.edit().putString(usermodel, user).apply();
//    }
//
//    public static UserModel getUser() {
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString(usermodel, "");
//        return gson.fromJson(json, UserModel.class);
//    }


    @Nullable
    public static String getInfo() {
        return sharedPreferences.getString(info, "");
    }

    public static void setInfo(String value) {
        sharedPreferences.edit().putString(info, value).apply();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Nullable
    public static JSONObject getJSONObject(String key) {
        try {
            return new JSONObject(sharedPreferences.getString(key, "{}"));
        } catch (JSONException e) {
            return null;
        }
    }

    public static void putJSONObject(String key, @NonNull JSONObject value) {
        sharedPreferences.edit().putString(key, value.toString()).apply();
    }

    @Nullable
    public static JSONArray getJSONArray(String key) {
        try {
            return new JSONArray(sharedPreferences.getString(key, "[]"));
        } catch (JSONException e) {
            return null;
        }
    }

    public static void putJSONArray(String key, @NonNull JSONArray value) {
        sharedPreferences.edit().putString(key, value.toString()).apply();
    }

    @Nullable
    public static String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }


    public static Boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }


    public static long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static void putLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }


    public static float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public static void putFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public static void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }


    public static boolean saveDeviceToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TOKEN, token);
        editor.apply();
        return true;
    }

    //this method will fetch the device token from shared preferences
//    @Nullable
//    public static String getDeviceToken() {
//        Timber.d("getDeviceToken: %s", sharedPreferences.getString(TAG_TOKEN, ""));
//        //SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(TAG_TOKEN, "");
//    }

    @Nullable
    public static String getIdDemo() {
        return sharedPreferences.getString(id_demo, "");
    }

    public static void setIdDemo(String value) {
        sharedPreferences.edit().putString(id_demo, value).apply();
//        sEditor.commit();
    }

    @Nullable
    public static String getPinDemo() {
        return sharedPreferences.getString(pin_demo, "");
    }

    public static void setPinDemo(String value) {
        sharedPreferences.edit().putString(pin_demo, value).apply();
//        sEditor.commit();
    }

    @Nullable
    public static String getKeyDemo() {
        return sharedPreferences.getString(key_demo, "");
    }

    public static void setKeyDemo(String value) {
        sharedPreferences.edit().putString(key_demo, value).apply();
//        sEditor.commit();
    }

    @Nullable
    public static String getEdukasiLogin() {
        return sharedPreferences.getString(key_edukasi_login, "");
    }

    public static void setEdukasiLogin(String value) {
        sharedPreferences.edit().putString(key_edukasi_login, value).apply();
//        sEditor.commit();
    }

    @Nullable
    public static String getLastUrlGrosir() {
        return sharedPreferences.getString(lastUrlGrosir, "");
    }

    public static void setLastUrlGrosir(String value) {
        sharedPreferences.edit().putString(lastUrlGrosir, value).apply();
    }


//    public static void initialize() {
//        if (sharedPreferences == null) {
//            sharedPreferences = SBFApplication.getInstance().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//            //  sEditor = sharedPreferences.edit();
//        }
//    }
}
