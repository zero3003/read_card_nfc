package utils;//package utils;
//
////import java.util.Calendar;
//
//import androidx.annotation.NonNull;
//
//import org.apache.commons.codec.binary.Base64;
//
//import java.util.Date;
//import java.util.Random;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//
///**
// * Created by sarifhidayat on 2019-06-24.
// **/
//
//
//public class FmssRequestEncryptor {
//
//    //    public static String encrypt(String plainText, Calendar wibCalendar) throws Exception {
//    @NonNull
//    public static String encrypt(String plainText) throws Exception {
//        String encryptedText = "";
//
//        //Date expiredTime = new Date(wibCalendar.getTime().getTime());
//        Date expiredTime = new Date();
//
//        plainText = Math.round((Long) (expiredTime.getTime() / 1000)) + "|" + plainText;
//
////        //->////System.out.println(expiredTime.toString());
////        //->////System.out.println(plainText);
//
//        String strkey = generateKey();
//        String key = strkey.substring(0, 16);
//        String iv = strkey.substring(16, 24);
//        String encryptedText1 = encryptBlowfish(plainText, key, iv);
//        if (!"".equals(encryptedText1)) {
//            String encryptedText2 = mergeText(encryptedText1, key, iv);
//            String encryptedText3 = replaceNumber(encryptedText2);
//            String encryptedText4 = reverseString(encryptedText3);
////            byte[] encryptedText5 = encryptRsa(encryptedText4.getBytes());
////            byte[] encoded = Base64.encodeBase64(encryptedText5);
//            byte[] encoded = Base64.encodeBase64(encryptedText4.getBytes());
//            encryptedText = new String(encoded);
//        }
//
//        return encryptedText;
//    }
//
//    @NonNull
//    private static byte[] encryptRsa(@NonNull byte[] bufferedText) throws Exception {
//        CastleCrypt cc = new CastleCrypt();
//        String rsaPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw3kgCeFI5Vp6z5tzGhbBpW5faguhrOrP"
//                + "9Wlth2gDU1b/nFXgImpJVbnhyrzQU1oT8NQ6NOqfPE7aD9dd/tVIXHAL54kjCBc5QGik9Vskpl0k"
//                + "p9Ri5IOx9/XDabtKyL2T20p11vilEcfGKy0jsUHYO+tZ7DRguxLyhe7tzND3fhAZoLliv2K8ntAq"
//                + "+cnUU4Xa3SNuGRSTmvwYWMKZ8ZxgSbPSw50qejrQt1Qm83td03tdDDMLQQERUa3jM61eZHl9BWky"
//                + "ALPC/SEUmKd8g4OVmkJ8kLDBwTC36Lk6h5gJAbSGDqnMjsqxf9oTcAo6eG/ArH1pBASaeM1MYShE"
//                + "+/7qPQIDAQAB";
//        cc.setPublicKey(Base64.decodeBase64(rsaPublicKey.getBytes()));
//
//        return cc.encrypt(bufferedText);
//    }
//
//    @NonNull
//    private static String encryptBlowfish(@NonNull String plainText, @NonNull String key, @NonNull String iv) {
//        try {
//            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "Blowfish");
//            Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
//            IvParameterSpec ivs = new IvParameterSpec(iv.getBytes());
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivs);
//            byte[] cipherText = cipher.doFinal(plainText.getBytes());
//
//            return bin2hex(cipherText);
//        } catch (Exception e) {
//            return "";
//        }
//    }
//
//    @NonNull
//    private static String bin2hex(@NonNull byte[] binary) {
//        String HEX_STRING = "0123456789ABCDEF";
//        StringBuffer buf = new StringBuffer();
//        int block = 0;
//
//        for (byte b : binary) {
//            block = b & 0xFF;
//            buf.append(HEX_STRING.charAt(block >> 4));
//            buf.append(HEX_STRING.charAt(b & 0x0F));
//        }
//
//        return buf.toString();
//    }
//
//    @NonNull
//    private static String reverseString(@NonNull String foo) {
//        char[] inArr = foo.toCharArray();
//        char[] outArr = new char[inArr.length];
//        int len = inArr.length;
//        for (int i = 0; i < len; i++) {
//            outArr[len - i - 1] = inArr[i];
//        }
//
//        return new String(outArr);
//    }
//
//    @NonNull
//    private static String generateKey() {
//        Random rnd = new Random();
//        String chars = "GHIJKLMNOP";
//        int charsLen = chars.length();
//        char[] key = new char[24];
//
//        for (int i = 0; i < 24; i++) {
//            key[i] = (char) chars.charAt(rnd.nextInt(charsLen));
//        }
//
//        return new String(key);
//    }
//
//    @NonNull
//    private static String mergeText(@NonNull String encryptedText, String key, String iv) {
//        Random rnd = new Random();
//        String keyAll = key + iv;
//        int length1 = encryptedText.length();
//        int length2 = keyAll.length();
//        int length3 = length1 + length2;
//        int length4 = 2 * length3;
//        char[] result = new char[length4];
//        int i, j;
//
//        for (i = 0; i < length4; i++) {
//            result[i] = '-';
//        }
//
//        j = 0;
//        for (i = 0; i < length1; i++) {
//            j = j + rnd.nextInt(2) + 1;
//            result[j] = encryptedText.charAt(i);
//        }
//
//        j = 0;
//        for (i = 0; i < length4; i++) {
//            if (result[i] == '-') {
//                result[i] = keyAll.charAt(j);
//                j++;
//                if (j == length2) {
//                    break;
//                }
//            }
//        }
//
//        return new String(result).replaceAll("-", "");
//    }
//
//    @NonNull
//    private static String replaceNumber(@NonNull String encryptedText) {
//        int length = encryptedText.length();
//        char[] result = new char[length];
//        char chr;
//        int i;
//
//        for (i = 0; i < length; i++) {
//            chr = encryptedText.charAt(i);
//            switch (chr) {
//                case '0':
//                    result[i] = 'Z';
//                    break;
//                case '1':
//                    result[i] = 'Y';
//                    break;
//                case '2':
//                    result[i] = 'X';
//                    break;
//                case '3':
//                    result[i] = 'W';
//                    break;
//                case '4':
//                    result[i] = 'V';
//                    break;
//                case '5':
//                    result[i] = 'U';
//                    break;
//                case '6':
//                    result[i] = 'T';
//                    break;
//                case '7':
//                    result[i] = 'S';
//                    break;
//                case '8':
//                    result[i] = 'R';
//                    break;
//                case '9':
//                    result[i] = 'Q';
//                    break;
//                default:
//                    result[i] = chr;
//                    break;
//            }
//        }
//
//        return new String(result);
//    }
//
//}
