package utils;//package utils;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.os.Build;
//import android.text.Html;
//import android.text.Spanned;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.core.content.ContextCompat;
//
//import com.bm.main.scm.R;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//import timber.log.Timber;
//
///*
// * Created by papahnakal on 09/11/17.
// */
//
//public class FormatString {
//    public static Spanned htmlString(String html) {
//        Spanned result;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
//        } else {
//            result = Html.fromHtml(html);
//        }
//        return result;
//    }
//
//    public static int setColor(@NonNull Context context, int colors) {
//        int color;
//        color = ContextCompat.getColor(context, colors);
//        return color;
//    }
//
//    @Nullable
//    public static Drawable setImage(@NonNull Context context, int image) {
//        Drawable img;
//        img = ContextCompat.getDrawable(context, image);
//        return img;
//    }
//
//    @NonNull
//    public static String CurencyIDR(@NonNull String curency) {
//        String result = "";
//        if (curency.equalsIgnoreCase("0")) {
//            result = "0";
//            return result;
//        } else {
//            int lenght = curency.length();
//            if (lenght <= 3) {
//                result = curency;
//            } else if ((lenght >= 4) && (lenght <= 6)) {
//                String reverse = new StringBuilder(curency).reverse().toString();
//                String tigablkng = reverse.substring(0, 3);
//                String depan = reverse.substring(3, reverse.length());
//                result = new StringBuilder(tigablkng + "." + depan).reverse().toString();
//            } else if (lenght >= 7 && lenght <= 9) {
//                String reverse = new StringBuilder(curency).reverse().toString();
//                String tigablkng = reverse.substring(0, 3);
//                String tigatengah = reverse.substring(3, 6);
//                String depan = reverse.substring(6, reverse.length());
//                result = new StringBuilder(tigablkng + "." + tigatengah + "." + depan).reverse().toString();
//            } else if (lenght >= 10 && lenght <= 12) {
//                String reverse = new StringBuilder(curency).reverse().toString();
//                String tigablkng = reverse.substring(0, 3);
//                String tigatengah = reverse.substring(3, 6);
//                String tigatengah2 = reverse.substring(6, 9);
//                String depan = reverse.substring(9, reverse.length());
//                result = new StringBuilder(tigablkng + "." + tigatengah + "." + tigatengah2 + "." + depan).reverse().toString();
//            }
//            return result;
//        }
//    }
//
//    @NonNull
//    public static String md5(@NonNull final String s) {
//        final String MD5 = "MD5";
//        try {
//            // Create MD5 Hash
//            MessageDigest digest = MessageDigest
//                    .getInstance(MD5);
//            digest.update(s.getBytes());
//            byte messageDigest[] = digest.digest();
//
//            // Create Hex String
//            StringBuilder hexString = new StringBuilder();
//            for (byte aMessageDigest : messageDigest) {
//                String h = Integer.toHexString(0xFF & aMessageDigest);
//                while (h.length() < 2)
//                    h = "0" + h;
//                hexString.append(h);
//            }
//            return hexString.toString();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public static Spanned fromHtml(String html) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
//        } else {
//            return Html.fromHtml(html);
//        }
//    }
//
//    public final static int PDFcodeWidth = 380;
//    public final static int PDFcodeHight = 190;
//    public final static int QRcodeWidth = 150;
//    public final static int QRcodeHight = 150;
//
//    @Nullable
//    public static Bitmap TextToImageEncode(Context context, String Value) throws WriterException {
//        BitMatrix bitMatrix;
//        QRCodeWriter writer = new QRCodeWriter();
//        try {
//            bitMatrix = new MultiFormatWriter().encode(Value, BarcodeFormat.QR_CODE, QRcodeWidth, QRcodeHight, null
//            );
//
//        } catch (IllegalArgumentException Illegalargumentexception) {
//
//            return null;
//        }
//        int bitMatrixWidth = bitMatrix.getWidth();
//
//        int bitMatrixHeight = bitMatrix.getHeight();
//
//        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];
//
//        for (int y = 0; y < bitMatrixHeight; y++) {
//            int offset = y * bitMatrixWidth;
//
//            for (int x = 0; x < bitMatrixWidth; x++) {
//
//                pixels[offset + x] = ContextCompat.getColor(context, bitMatrix.get(x, y) ? R.color.md_black_1000 : R.color.md_white_1000);
//            }
//        }
//        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_8888);
//        bitmap.setPixels(pixels, 0, 150, 0, 0, bitMatrixWidth, bitMatrixHeight);
//        return bitmap;
//    }
//
//    @Nullable
//    public static Bitmap TextToImageEncodeMatrix(String Value) throws WriterException {
//        BitMatrix bitMatrix;
//        try {
//            bitMatrix = new MultiFormatWriter().encode(Value, BarcodeFormat.PDF_417, PDFcodeWidth, PDFcodeHight, null
//            );
//
//        } catch (IllegalArgumentException Illegalargumentexception) {
//
//            return null;
//        }
//        int bitMatrixWidth = bitMatrix.getWidth();
//
//        int bitMatrixHeight = bitMatrix.getHeight();
//        Timber.d("TextToImageEncodeMatrix: " + bitMatrixWidth + " " + bitMatrixHeight);
//
//        Bitmap bitmap = Bitmap.createBitmap(PDFcodeWidth, PDFcodeHight, Bitmap.Config.ARGB_8888);
//        for (int i = 0; i < bitMatrixWidth; i++) {//width
//            for (int j = 0; j < bitMatrixHeight; j++) {//height
//                bitmap.setPixel(i, j, bitMatrix.get(i, j) ? Color.BLACK : Color.WHITE);
//            }
//        }
//        return bitmap;
//    }
//}
