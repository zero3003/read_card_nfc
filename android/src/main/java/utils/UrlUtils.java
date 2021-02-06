package utils;//package utils;
//
//import android.annotation.SuppressLint;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import java.net.URL;
//import java.security.SecureRandom;
//import java.security.cert.X509Certificate;
//
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.X509TrustManager;
//
//import timber.log.Timber;
//
//public class UrlUtils {
//
//    @Nullable
//    public static URL verifyUrl(String url) {
//        Timber.d("verifyUrl: %s", url);
//        URL verifiedUrl;
//        try {
//            verifiedUrl = new URL(url);
//        } catch (Exception e) {
//            Timber.e(e);
//            return null;
//        }
//
//        return verifiedUrl;
//    }
//
//    public static void trustEveryone() {
//        try {
//            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> {
//                if (hostname.equalsIgnoreCase("mpdesktop.fastpay.co.id") ||
//                        hostname.equalsIgnoreCase("www.fastpay.co.id") ||
//                        hostname.equalsIgnoreCase("www.fpkita.net") ||
//                        hostname.equalsIgnoreCase("tomofastpay.id") ||
//                        hostname.equalsIgnoreCase("api.fastravel.co.id") ||
//                        hostname.equalsIgnoreCase("tomosbf.fastpay.co.id") ||
//                        hostname.equalsIgnoreCase("dashboard.fastpay.co.id")
//                ) {
//                    return true;
//                } else {
//                    return false;
//                }
//            });
//
//            SSLContext context = SSLContext.getInstance("TLS");
//            context.init(null, new X509TrustManager[]{new X509TrustManager() {
//                @SuppressLint("TrustAllX509TrustManager")
//                public void checkClientTrusted(X509Certificate[] chain, String authType) {
//                }
//
//                @SuppressLint("TrustAllX509TrustManager")
//                public void checkServerTrusted(X509Certificate[] chain, String authType) {
//                }
//
//                @NonNull
//                public X509Certificate[] getAcceptedIssuers() {
//                    return new X509Certificate[0];
//                }
//            }}, new SecureRandom());
//
//            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
//        } catch (Exception e) {
//            Timber.e(e);
//        }
//    }
//}
