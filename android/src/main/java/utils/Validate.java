package utils;//package utils;
//
//import android.text.TextUtils;
//
//import androidx.annotation.NonNull;
//
//import com.bm.main.fpl.templates.PasswordEditText;
//import com.bm.main.materialedittext.MaterialEditText;
//
///**
// * Created by Sarif Hidayat on 19/08/2017.
// */
//
//public class Validate {
//
//    public Validate() {
//
//    }
//
//    private static boolean isValidEmail(String email) {
//        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
//    }
//
//    public static boolean checkValidEmailEditText(@NonNull MaterialEditText editText, String error) {
//        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editText.getText()).matches()) {
//
//
//            editText.setError(error);
//            return false;
//        }
//
//        return true;
//    }
//
//    public static boolean checkEmptyEditText(@NonNull MaterialEditText editText, String error) {
//        if (editText.getText().toString().trim().isEmpty()) {
//
//
//            editText.setError(error);
//            return false;
//        }
//
//        return true;
//
//    }
//
//    public static boolean checkZerroEditText(@NonNull MaterialEditText editText, String error) {
//        if (editText.getText().toString().equalsIgnoreCase("0")) {
//
//
//            editText.setError(error);
//            return false;
//        }
//
//        return true;
//
//    }
//
//    public static boolean checkEmptyEditTextPassword(@NonNull PasswordEditText editText, String error) {
//        if (editText.getText().toString().trim().isEmpty()) {
//
//
//            editText.setError(error);
//            return false;
//        }
//
//        return true;
//    }
//}
