package utils;

import android.text.InputFilter;
import android.text.Spanned;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sarif on 29/02/16.
 */
public class saring_karakter {

    public saring_karakter() {
    }


    @NonNull
    public InputFilter[] getOnlyLetter() {

        InputFilter[] filterLetter= new InputFilter[] {
                new InputFilter() {
                    @NonNull
                    @Override
                    public CharSequence filter(@NonNull CharSequence cs, int start,
                                               int end, Spanned spanned, int dStart, int dEnd) {
                        // TODO Auto-generated method stub
                        if(cs.equals("")){ // for backspace
                            return cs;
                        }
                        if(cs.toString().matches("[a-zA-Z \'.]")){
                            return cs;
                        }
                        return "";
                    }
                }
        };
        return filterLetter;
        }

    @NonNull
    public InputFilter[] getOnlyLetterAndDigit() {

        InputFilter[] filterLetter= new InputFilter[] {
                new InputFilter() {
                    @NonNull
                    @Override
                    public CharSequence filter(@NonNull CharSequence cs, int start,
                                               int end, Spanned spanned, int dStart, int dEnd) {
                        // TODO Auto-generated method stub
                        if(cs.equals("")){ // for backspace
                            return cs;
                        }
                        if(cs.toString().matches("[a-zA-Z]")){
                            return cs;
                        }
                        return "";
                    }
                }
        };
        return filterLetter;
    }

    public boolean isEmailValid(String email)
    {
//        String regExpn =
//                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
//                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
//                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
//                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
//                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
//                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
//
//       /// CharSequence inputStr = email;
//
//        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
       // Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }


    @NonNull
    public InputFilter[] DisableSpecialCharactersAllCaps(int length) {
        InputFilter filter = new InputFilter() {
            @Nullable
            @Override
            public CharSequence filter(@NonNull CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        InputFilter[] FilterArraym = new InputFilter[3];
        FilterArraym[0] = filter;

        FilterArraym[1] = new InputFilter.LengthFilter(length);
        FilterArraym[2] = new InputFilter.AllCaps();
        return FilterArraym;
    }

}
