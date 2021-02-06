package utils;//package utils;
//
//import android.content.Context;
//import android.os.Build;
//import android.text.Editable;
//import android.text.InputFilter;
//import android.text.TextWatcher;
//import android.widget.EditText;
//
//import com.bm.main.single.ftl.utils.FormatUtil;
//
//import java.lang.ref.WeakReference;
//
///**
// * Created by sarif on 03/03/16.
// */
//public class MoneyTextWatcher implements TextWatcher {
//    private final WeakReference<EditText> editTextWeakReference;
//    Context mContext;
//
//    public MoneyTextWatcher(Context context, EditText editText) {
//        editTextWeakReference = new WeakReference<EditText>(editText);
//        mContext = context;
//    }
//
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//    }
//
//    @Override
//    public void afterTextChanged(Editable editable) {
//        EditText editText = editTextWeakReference.get();
//        if (editText == null || editText.equals("")) return;
//        String s = editable.toString();
//        editText.removeTextChangedListener(this);
//        String cleanString = s.replaceAll("[%sRp,.\\\\s]", "");
//
//        try {
//            PreferenceClass.putString("uang", cleanString.trim());
//        } catch (NumberFormatException nfe) {
//            PreferenceClass.putString("uang", "0");
//        }
//
//        String formatted;
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
//            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(17)});
//            formatted = cleanString.trim();
//        } else {
//            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(17)});
//            formatted = FormatUtil.FormatMoneySymbol(cleanString.trim());
//        }
//
//        editText.setText(formatted);
//        editText.setSelection(formatted.length());
//        editText.addTextChangedListener(this);
//    }
//}
