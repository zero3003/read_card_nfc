package utils;//package utils;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
///**
// * Created by sarifhidayat on 4/8/19.
// **/
//public class CampaignHelper {
//    public static final String REFERRER = "referrer";
//
//    private static final String SOURCE = "source";
//    private static final String MEDIUM = "medium";
//    private static final String TERM = "term";
//    private static final String CONTENT = "content";
//    private static final String CAMPAIGN = "campaign";
//
//    private static String referrer;
//
//    private static String getReferrer() {
//        if (referrer == null || referrer.isEmpty()) {
//            referrer = PreferenceClass.getString(REFERRER, "");
//        }
//        return referrer;
//    }
//
//    @Nullable
//    private static String parseReferrer(@NonNull String field) {
//        String[] array = getReferrer().split("&");
//
//        for (String string : array) {
//            if (string.contains(field)) {
//                int index = string.indexOf("=");
//
//                return string.substring(index + 1);
//            }
//        }
//        return null;
//    }
//
//    @Nullable
//    public static String getSource() {
//        return parseReferrer(SOURCE);
//    }
//
//    @Nullable
//    public static String getMedium() {
//        return parseReferrer(MEDIUM);
//    }
//
//    @Nullable
//    public static String getTerm() {
//        return parseReferrer(TERM);
//    }
//
//    @Nullable
//    public static String getContent() {
//        return parseReferrer(CONTENT);
//    }
//
//    @Nullable
//    public static String getCampaign() {
//        return parseReferrer(CAMPAIGN);
//    }
//}
