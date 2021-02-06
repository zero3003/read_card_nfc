package utils;//package utils;
//
///**
// * Created by sarifhidayat on 8/3/17.
// */
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
//import com.googlecode.gwt.crypto.client.AESCipher;
//
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class MobileAES {
//
//    @Nullable
//    private static byte[] key = null;
//
//    public static void decryptFileToFile(File sourceData, File destination) throws Exception {
//        if (key == null) {
//        } else {
//            File dataText = sourceData;
//            byte[] bufferedText = new byte[(int) dataText.length()];
//            DataInputStream in2 = new DataInputStream(new FileInputStream(dataText));
//            in2.readFully(bufferedText);
//            in2.close();
//            decrypt(bufferedText, destination);
//        }
//    }
//
//    private static void decrypt(byte[] bufferedText, File destination) throws Exception {
//        // Encrypt
//        byte[] decrypted = decrypt(new String(bufferedText)).getBytes();
//
//        FileWriter fw = new FileWriter(destination);
//        fw.write(new String(decrypted));
//        fw.close();
//    }
//
//    public static void encryptFileToFile(File sourceData, File destination) throws Exception {
//        if (key == null) {
//            //->//->////System.out.println("Silahkan mendifiniskan key terlebih dahulu");
//        } else {
//            File dataText = sourceData;
//            byte[] bufferedText = new byte[(int) dataText.length()];
//            DataInputStream in2 = new DataInputStream(new FileInputStream(dataText));
//            in2.readFully(bufferedText);
//            in2.close();
//            encrypt(bufferedText, destination);
//        }
//    }
//
//    private static void encrypt(byte[] bufferedText, File destination) throws Exception {
//        if (key == null) {
//            //->//->////System.out.println("Silahkan mendifiniskan key terlebih dahulu");
//        } else {
//            // Encrypt
//            byte[] encrypted = encrypt(new String(bufferedText)).getBytes();
//
//            // Save to file
//            FileOutputStream fos = new FileOutputStream(destination);
//            fos.write(encrypted);
//            fos.flush();
//            fos.close();
//        }
//    }
//
//    public static void defineKey(@NonNull String combinedKey) {
//        try {
//            key = (combinedKey).getBytes("UTF-8");
//            MessageDigest sha = MessageDigest.getInstance("SHA-1");
//            key = sha.digest(key);
//            key = Arrays.copyOf(key, 16);
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(MobileAES.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(MobileAES.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Nullable
//    public static String encrypt(@NonNull String data) {
//        if (key == null) {
//            System.out.println("Silahkan mendifiniskan key terlebih dahulu");
//            return null;
//        } else {
//            try {
//                AESCipher aes = new AESCipher();
//                aes.setKey(key);
//                return aes.encrypt(data);
//            } catch (IllegalStateException ex) {
//                Logger.getLogger(MobileAES.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (InvalidCipherTextException ex) {
//                Logger.getLogger(MobileAES.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return null;
//        }
//    }
//
//    public static String decrypt(@NonNull String data) {
//        if (key == null) {
//            //->//->////System.out.println("Silahkan mendifiniskan key terlebih dahulu");
//            return "";
//        } else {
//            try {
//                AESCipher aes = new AESCipher();
//                aes.setKey(key);
//                return aes.decrypt(data);
//            } catch (InvalidCipherTextException ex) {
//                Logger.getLogger(MobileAES.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalStateException ex) {
//                Logger.getLogger(MobileAES.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return "";
//        }
//    }
//}