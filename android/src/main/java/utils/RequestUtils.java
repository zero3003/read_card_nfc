package utils;//package utils;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.bm.main.fpl.constants.FCMConstants;
//import com.bm.main.fpl.constants.RConfig;
//import com.bm.main.fpl.handlers.JsonObjectResponseHandler;
//import com.bm.main.fpl.handlers.JsonObjectResponsePaymentHandler;
//import com.bm.main.fpl.handlers.ProgressResponseBody;
//import com.bm.main.fpl.handlers.ProgressResponseHandler;
//import com.bm.main.fpl.interfaces.JsonObjectResponseCallback;
//import com.bm.main.fpl.interfaces.ProgressListener;
//import com.bm.main.fpl.interfaces.ProgressResponseCallback;
//import com.bm.main.scm.BuildConfig;
//
//import org.json.JSONObject;
//
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.Call;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import okhttp3.logging.HttpLoggingInterceptor;
//
////import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor;
//
//public class RequestUtils {
//    public static OkHttpClient client;
//    static OkHttpClient.Builder builder;
//
//    @Nullable
//    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//    public static String url;
//
//
//    public static void transportWithJSONObjectResponse(@NonNull AppCompatActivity context, @NonNull JSONObject requestObject, int actionCode, JsonObjectResponseCallback callback) {
//        transportWithJSONObjectResponse(context, getUrl(), requestObject, actionCode, callback);
//    }
//
//    public static void transportWithJSONObjectResponse(@NonNull AppCompatActivity context, String url, @NonNull JSONObject requestObject, int actionCode, JsonObjectResponseCallback callback) {
//        try {
//            FCMConstants.isStillRunningRequest = true;
//            RequestBody body = RequestBody.create(JSON, requestObject.toString());
//
//            Request request = new Request.Builder()
//                    .header("Accept-Encoding", "identity")
//                    .header("Content-Type", "application/json")
//                    .url(url)
//                    .post(body).tag("fastpay")
//                    .build();
//
////            Timber.d("transportWithJSONObjectResponse: " + request + " " + requestObject.toString());
////            Timber.d("transportWithJSONObjectResponse: " + request + " " + FmssRequestEncryptor.encrypt(requestObject.toString()));
//
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.connectTimeout(1, TimeUnit.MINUTES).
//                    readTimeout(1, TimeUnit.MINUTES).
//                    writeTimeout(1, TimeUnit.MINUTES).
//                    retryOnConnectionFailure(true);
//
//            if (BuildConfig.DEBUG) {
////                builder.addInterceptor(new OkHttpProfilerInterceptor());
//                builder.addInterceptor(getLoggingInterceptor());
//            }
//
//            client = builder.build();
//            client.newCall(request).enqueue(new JsonObjectResponseHandler(context, callback, actionCode));
//        } catch (Exception e) {
//            callback.onFailure(actionCode, "", "Terjadi kesalahan", e);
//            e.printStackTrace();
//        }
//    }
//
//    public static void transportWithJSONObjectResponsePayment(@NonNull AppCompatActivity context, @NonNull JSONObject requestObject, int actionCode, JsonObjectResponseCallback callback) {
//        try {
//            FCMConstants.isStillRunningRequest = true;
//            RequestBody body = RequestBody.create(JSON, requestObject.toString());
//
//            Request request = new Request.Builder()
//                    .header("Accept-Encoding", "identity")
//                    .header("Content-Type", "application/json")
//                    .url(getUrl())
//                    .post(body).tag("fastpay")
//                    .build();
//
////            Timber.d("transportWithJSONObjectResponsePayment: " + request + " " + requestObject.toString());
////            Timber.d("transportWithJSONObjectResponsePayment: " + request + " " + FmssRequestEncryptor.encrypt(requestObject.toString()));
//
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.connectTimeout(1, TimeUnit.MINUTES).
//                    readTimeout(1, TimeUnit.MINUTES).
//                    writeTimeout(1, TimeUnit.MINUTES).
//                    retryOnConnectionFailure(false);
//
//            if (BuildConfig.DEBUG) {
////                builder.addInterceptor(new OkHttpProfilerInterceptor());
//                builder.addInterceptor(getLoggingInterceptor());
//            }
//
//            client = builder.build();
//            client.newCall(request).enqueue(new JsonObjectResponsePaymentHandler(context, callback, actionCode));
//        } catch (Exception e) {
//            callback.onFailure(actionCode, "", "Terjadi kesalahan", e);
//            e.printStackTrace();
//        }
//    }
//
//    public static void transportWithProgressResponse(@NonNull AppCompatActivity context, @NonNull JSONObject requestObject, final int actionCode, @NonNull final ProgressResponseCallback callback) {
//        try {
//            FCMConstants.isStillRunningRequest = true;
//            RequestBody body = RequestBody.create(JSON, requestObject.toString());
//
//            Request request = new Request.Builder()
//                    .url(getUrl())
//                    .post(body)
//                    .tag("fastpay")
//                    .build();
//
//            final ProgressListener progressListener = (bytesRead, contentLength, done) -> callback.onUpdate(actionCode, bytesRead, contentLength, done);
//
////            Timber.d("transportWithProgressResponse: " + request + " " + requestObject.toString());
////            Timber.d("transportWithProgressResponse: " + request + " " + FmssRequestEncryptor.encrypt(requestObject.toString()));
//
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder
//                    .addNetworkInterceptor(chain -> {
//                        Response originalResponse = chain.proceed(chain.request());
//                        return originalResponse.newBuilder()
//                                .body(new ProgressResponseBody(originalResponse.body(), progressListener))
//                                .build();
//                    })
//                    .connectTimeout(1, TimeUnit.MINUTES)
//                    .readTimeout(1, TimeUnit.MINUTES)
//                    .writeTimeout(1, TimeUnit.MINUTES)
//                    .retryOnConnectionFailure(true);
//
//            if (BuildConfig.DEBUG) {
//                builder.addInterceptor(getLoggingInterceptor());
////                builder.addInterceptor(new OkHttpProfilerInterceptor());
//            }
//
//            client = builder.build();
//            client.newCall(request).enqueue(new ProgressResponseHandler(context, callback, actionCode));
//        } catch (Exception e) {
//            callback.onFailure(actionCode, "", "Terjadi kesalahan", e);
//            e.printStackTrace();
//        }
//    }
//
//    public static void transportWithProgressResponseUpdateLocation(@NonNull AppCompatActivity context, @NonNull JSONObject requestObject, final int actionCode, @NonNull final ProgressResponseCallback callback) {
//        try {
//            FCMConstants.isStillRunningRequest = true;
//            RequestBody body = RequestBody.create(JSON, FmssRequestEncryptor.encrypt(requestObject.toString()));
//
//            Request request = new Request.Builder()
//                    .url(getUrl())
//                    .post(body)
//                    .build();
//            final ProgressListener progressListener = (bytesRead, contentLength, done) -> callback.onUpdate(actionCode, bytesRead, contentLength, done);
//
////            Timber.d("transportWithProgressResponse: " + request + " " + requestObject.toString());
////            Timber.d("transportWithProgressResponse: " + request + " " + FmssRequestEncryptor.encrypt(requestObject.toString()));
//
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.addNetworkInterceptor(chain -> {
//                Response originalResponse = chain.proceed(chain.request());
//                return originalResponse.newBuilder()
//                        .body(new ProgressResponseBody(originalResponse.body(), progressListener))
//                        .build();
//            })
//                    .connectTimeout(1, TimeUnit.MINUTES)
//                    .readTimeout(1, TimeUnit.MINUTES)
//                    .writeTimeout(1, TimeUnit.MINUTES)
//                    .retryOnConnectionFailure(true);
//
//            if (BuildConfig.DEBUG) {
////                builder.addInterceptor(new OkHttpProfilerInterceptor());
//                builder.addInterceptor(getLoggingInterceptor());
//            }
//
//            client = builder.build();
//            client.newCall(request).enqueue(new ProgressResponseHandler(context, callback, actionCode));
//        } catch (Exception e) {
//            callback.onFailure(actionCode, "", "Terjadi kesalahan", e);
//            e.printStackTrace();
//        }
//    }
//
//    public static void cancel() {
//        for (Call call : client.dispatcher().queuedCalls()) {
//            if (call.request().tag().equals("fastpay"))
//                call.cancel();
//
//        }
//        for (Call call : client.dispatcher().runningCalls()) {
//            if (call.request().tag().equals("fastpay"))
//                call.cancel();
//        }
//    }
//
//    private static HttpLoggingInterceptor loggingInterceptor;
//    private static HttpLoggingInterceptor getLoggingInterceptor() {
//        if (loggingInterceptor == null) {
//            loggingInterceptor = new HttpLoggingInterceptor();
//            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        }
//        return loggingInterceptor;
//    }
//
//    public static String getUrl() {
//        if (BuildConfig.DEBUG) {
//            url = url == null ? PreferenceClass.getString(RConfig.API_URL_FP_DEVEL, "") : url;
//        } else {
//            url = url == null ? PreferenceClass.getString(RConfig.API_URL_FP, "") : url;
//        }
//
//        return url;
//    }
//
//    public static void setUrl(String url) {
//        RequestUtils.url = url;
//    }
//
//
//    public static void initialize() {
//        if (client == null) {
////            client = new OkHttpClient();
//            builder = new OkHttpClient.Builder();
////            builder.callTimeout(1,TimeUnit.MINUTES).connectTimeout(1, TimeUnit.MINUTES).
////                    readTimeout(1, TimeUnit.MINUTES).
////                    writeTimeout(1, TimeUnit.MINUTES).
////                    retryOnConnectionFailure(true);
////            if (BuildConfig.DEBUG) {
////                builder.addInterceptor(new OkHttpProfilerInterceptor());
////            }
//            client =  builder.build();
//        }
//    }
//}
