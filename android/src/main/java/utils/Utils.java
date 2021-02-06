package utils;//package utils;
//
//import android.app.ActivityManager;
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.graphics.Typeface;
//import android.graphics.drawable.Drawable;
//import android.text.SpannableString;
//import android.text.style.StyleSpan;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.Display;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.content.ContextCompat;
//
//import com.bm.main.fpl.templates.taptargetview.TapTarget;
//import com.bm.main.fpl.templates.taptargetview.TapTargetSequence;
//import com.bm.main.scm.R;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * Created by kalmath_v on 8/29/16.
// */
//
//public class Utils {
//    public static int getColor(@NonNull Context context, int color) {
//        TypedValue tv = new TypedValue();
//        context.getTheme().resolveAttribute(color, tv, true);
//        return tv.data;
//    }
//
//    /**
//     * Converts dps to pixels nicely.
//     *
//     * @param context the Context for getting the resources
//     * @param dp      dimension in dps
//     * @return dimension in pixels
//     */
//    public static int dpToPixel(@NonNull Context context, float dp) {
//        Resources resources = context.getResources();
//        DisplayMetrics metrics = resources.getDisplayMetrics();
//
//        try {
//            return (int) (dp * (metrics.densityDpi / 160f));
//        } catch (NoSuchFieldError ignored) {
//            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
//        }
//    }
//
//    /**
//     * Returns screen width.
//     *
//     * @param context Context to get resources and device specific display metrics
//     * @return screen width
//     */
//    public static int getScreenWidth(@NonNull Context context) {
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        return (int) (displayMetrics.widthPixels / displayMetrics.density);
//    }
//
//    /**
//     * Returns screen height.
//     *
//     * @param context Context to get resources and device specific display metrics
//     * @return screen height
//     */
//    public static int getScreenHeight(@NonNull Context context) {
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        return (int) (displayMetrics.heightPixels / displayMetrics.density);
//    }
//
//
//    public static Bitmap getRoundedTopLeftCornerBitmap(@NonNull Bitmap bitmap, int round) {
//        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
//                bitmap.getHeight(), Bitmap.Config.ARGB_4444);
//        Canvas canvas = new Canvas(output);
//
//        final int color = 0xff424242;
//        final Paint paint = new Paint();
//        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//        final RectF rectF = new RectF(rect);
//        final float Px = round;
//
//        final Rect bottomRect = new Rect(0, bitmap.getHeight() / 2,
//                bitmap.getWidth(), bitmap.getHeight());
//
//        paint.setAntiAlias(true);
//        canvas.drawARGB(0, 0, 0, 0);
//        paint.setColor(color);
//        canvas.drawRoundRect(rectF, Px, Px, paint);
//        // Fill in upper right corner
//        // canvas.drawRect(topRightRect, paint);
//        // Fill in bottom corners
//        canvas.drawRect(bottomRect, paint);
//
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(bitmap, rect, rect, paint);
//        if (bitmap != output) {
//            bitmap.recycle();
//        }
//        return output;
//    }
//
//    public static TapTargetSequence sequence;
//
//    public static void tapView(@NonNull AppCompatActivity activity, Toolbar toolbar, int menuItem, CharSequence title, CharSequence subTitle, int step) {
//
//
//        final Display display = activity.getWindowManager().getDefaultDisplay();
//
//        final Drawable droid = ContextCompat.getDrawable(activity.getApplicationContext(), R.drawable.srikandi);
//
//        final Rect droidTarget = new Rect(0, 0, droid.getIntrinsicWidth() * 2, droid.getIntrinsicHeight() * 2);
//
//        droidTarget.offset(display.getWidth() / 2, display.getHeight() / 2);
//
//        final SpannableString sassyDesc = new SpannableString("It allows you to go back, sometimes");
//        sassyDesc.setSpan(new StyleSpan(Typeface.ITALIC), sassyDesc.length() - "sometimes".length(), sassyDesc.length(), 0);
//
//
//        sequence = new TapTargetSequence(activity)
//                .targets(
//
//                        TapTarget.forToolbarNavigationIcon(toolbar, "This is the back button", sassyDesc).id(step),
//
//                        TapTarget.forToolbarMenuItem(toolbar, menuItem, "This is da search icon", "As you can see, it has gotten pretty dark around here...")
//                                .dimColor(android.R.color.black)
//                                .outerCircleColor(R.color.colorAccent)
//                                .targetCircleColor(android.R.color.black)
//                                .transparentTarget(true)
//                                .textColor(android.R.color.black)
//                                .id(step),
//
//                        TapTarget.forToolbarOverflow(toolbar, "This will show more options", "But they're not useful :(").id(step),
//
//                        TapTarget.forBounds(droidTarget, "Oh look!", "You can point to any part of the screen. You also can't cancel this one!")
//                                .cancelable(false)
//                                .icon(droid)
//                                .id(step)
//                )
//                .listener(new TapTargetSequence.Listener() {
//
//                    @Override
//                    public void onSequenceFinish() {
//                        // ((TextView) findViewById(R.id.educated)).setText("Congratulations! You're educated now!");
//                    }
//
//                    @Override
//                    public void onSequenceStep(@NonNull TapTarget lastTarget, boolean targetClicked) {
//                        Log.d("TapTargetView", "Clicked on " + lastTarget.id());
//                    }
//
//                    @Override
//                    public void onSequenceCanceled(TapTarget lastTarget) {
////
//                    }
//                });
//
//    }
//
//
//    public static void tapViewX(@NonNull AppCompatActivity activity, View view, CharSequence title, CharSequence subTitle) {
//
//
//        final Display display = activity.getWindowManager().getDefaultDisplay();
//
//        final Drawable droid = ContextCompat.getDrawable(activity.getApplicationContext(), R.drawable.srikandi);
//
//        final Rect droidTarget = new Rect(0, 0, droid.getIntrinsicWidth() * 2, droid.getIntrinsicHeight() * 2);
//
//        droidTarget.offset(display.getWidth() / 2, display.getHeight() / 2);
//
//        final SpannableString sassyDesc = new SpannableString("It allows you to go back, sometimes");
//        sassyDesc.setSpan(new StyleSpan(Typeface.ITALIC), sassyDesc.length() - "sometimes".length(), sassyDesc.length(), 0);
//
//
//        sequence = new TapTargetSequence(activity)
//                .targets(TapTarget.forView(view, title, subTitle))
//                .listener(new TapTargetSequence.Listener() {
//
//                    @Override
//                    public void onSequenceFinish() {
//                    }
//
//                    @Override
//                    public void onSequenceStep(@NonNull TapTarget lastTarget, boolean targetClicked) {
//                        Log.d("TapTargetView", "Clicked on " + lastTarget.id());
//                    }
//
//                    @Override
//                    public void onSequenceCanceled(TapTarget lastTarget) {
//                    }
//                });
//
//    }
//
//    // UNICODE 0x23 = #
//    public static final byte[] UNICODE_TEXT = new byte[]{0x23, 0x23, 0x23,
//            0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23,
//            0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23,
//            0x23, 0x23, 0x23};
//
//    @NonNull
//    private static String hexStr = "0123456789ABCDEF";
//    @NonNull
//    private static String[] binaryArray = {"0000", "0001", "0010", "0011",
//            "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011",
//            "1100", "1101", "1110", "1111"};
//
//    @Nullable
//    public static byte[] decodeBitmap(@NonNull Bitmap bmp) {
//        int bmpWidth = bmp.getWidth();
//        int bmpHeight = bmp.getHeight();
//
//        List<String> list = new ArrayList<String>(); //binaryString list
//        StringBuffer sb;
//
//
//        int bitLen = bmpWidth / 8;
//        int zeroCount = bmpWidth % 8;
//
//        String zeroStr = "";
//        if (zeroCount > 0) {
//            bitLen = bmpWidth / 8 + 1;
//            for (int i = 0; i < (8 - zeroCount); i++) {
//                zeroStr = zeroStr + "0";
//            }
//        }
//
//        for (int i = 0; i < bmpHeight; i++) {
//            sb = new StringBuffer();
//            for (int j = 0; j < bmpWidth; j++) {
//                int color = bmp.getPixel(j, i);
//
//                int r = (color >> 16) & 0xff;
//                int g = (color >> 8) & 0xff;
//                int b = color & 0xff;
//
//                // if color close to white，bit='0', else bit='1'
//                if (r > 160 && g > 160 && b > 160)
//                    sb.append("0");
//                else
//                    sb.append("1");
//            }
//            if (zeroCount > 0) {
//                sb.append(zeroStr);
//            }
//            list.add(sb.toString());
//        }
//
//        List<String> bmpHexList = binaryListToHexStringList(list);
//        String commandHexString = "1D763000";
//        String widthHexString = Integer
//                .toHexString(bmpWidth % 8 == 0 ? bmpWidth / 8
//                        : (bmpWidth / 8 + 1));
//        if (widthHexString.length() > 2) {
//            Log.e("decodeBitmap error", " width is too large");
//            return null;
//        } else if (widthHexString.length() == 1) {
//            widthHexString = "0" + widthHexString;
//        }
//        widthHexString = widthHexString + "00";
//
//        String heightHexString = Integer.toHexString(bmpHeight);
//        if (heightHexString.length() > 2) {
//            Log.e("decodeBitmap error", " height is too large");
//            return null;
//        } else if (heightHexString.length() == 1) {
//            heightHexString = "0" + heightHexString;
//        }
//        heightHexString = heightHexString + "00";
//
//        List<String> commandList = new ArrayList<String>();
//        commandList.add(commandHexString + widthHexString + heightHexString);
//        commandList.addAll(bmpHexList);
//
//        return hexList2Byte(commandList);
//    }
//
//    @NonNull
//    public static List<String> binaryListToHexStringList(@NonNull List<String> list) {
//        List<String> hexList = new ArrayList<String>();
//        for (String binaryStr : list) {
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < binaryStr.length(); i += 8) {
//                String str = binaryStr.substring(i, i + 8);
//
//                String hexString = myBinaryStrToHexString(str);
//                sb.append(hexString);
//            }
//            hexList.add(sb.toString());
//        }
//        return hexList;
//
//    }
//
//    @NonNull
//    public static String myBinaryStrToHexString(@NonNull String binaryStr) {
//        String hex = "";
//        String f4 = binaryStr.substring(0, 4);
//        String b4 = binaryStr.substring(4, 8);
//        for (int i = 0; i < binaryArray.length; i++) {
//            if (f4.equals(binaryArray[i]))
//                hex += hexStr.substring(i, i + 1);
//        }
//        for (int i = 0; i < binaryArray.length; i++) {
//            if (b4.equals(binaryArray[i]))
//                hex += hexStr.substring(i, i + 1);
//        }
//
//        return hex;
//    }
//
//    @NonNull
//    public static byte[] hexList2Byte(@NonNull List<String> list) {
//        List<byte[]> commandList = new ArrayList<byte[]>();
//
//        for (String hexStr : list) {
//            commandList.add(hexStringToBytes(hexStr));
//        }
//        byte[] bytes = sysCopy(commandList);
//        return bytes;
//    }
//
//    @Nullable
//    public static byte[] hexStringToBytes(@Nullable String hexString) {
//        if (hexString == null || hexString.equals("")) {
//            return null;
//        }
//        hexString = hexString.toUpperCase();
//        int length = hexString.length() / 2;
//        char[] hexChars = hexString.toCharArray();
//        byte[] d = new byte[length];
//        for (int i = 0; i < length; i++) {
//            int pos = i * 2;
//            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
//        }
//        return d;
//    }
//
//    @NonNull
//    public static byte[] sysCopy(@NonNull List<byte[]> srcArrays) {
//        int len = 0;
//        for (byte[] srcArray : srcArrays) {
//            len += srcArray.length;
//        }
//        byte[] destArray = new byte[len];
//        int destLen = 0;
//        for (byte[] srcArray : srcArrays) {
//            System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
//            destLen += srcArray.length;
//        }
//        return destArray;
//    }
//
//    private static byte charToByte(char c) {
//        return (byte) "0123456789ABCDEF".indexOf(c);
//    }
//
//    public static String getDateNow() {
//        Date c = Calendar.getInstance().getTime();
//        System.out.println("Current time => " + c);
//
//        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
//        return df.format(c);
//    }
//
//    public static boolean isThisDateWithin3DaysRange(String dateToValidate,
//                                                     String dateFromat) {
//
//        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
//        sdf.setLenient(false);
//        try {
//
//            // if not valid, it will throw ParseException
//            Date date = sdf.parse(dateToValidate);
//
//            // current date after 3 months
//            Calendar currentDateAfter3Day = Calendar.getInstance();
//            currentDateAfter3Day.add(Calendar.DAY_OF_YEAR, 3);
//
//            // current date before 3 months
//            Calendar currentDateBefore3Day = Calendar.getInstance();
//            currentDateBefore3Day.add(Calendar.DAY_OF_YEAR, -3);
//
//            /*************** verbose ***********************/
//            System.out.println("\n\ncurrentDate : "
//                    + Calendar.getInstance().getTime());
//            System.out.println("currentDateAfter3Day : "
//                    + currentDateAfter3Day.getTime());
//            System.out.println("currentDateBefore3Day : "
//                    + currentDateBefore3Day.getTime());
//            System.out.println("dateToValidate : " + dateToValidate);
//            /************************************************/
//
//            if (date.before(currentDateAfter3Day.getTime())
//                    && date.after(currentDateBefore3Day.getTime())) {
//
//                //ok everything is fine, date in range
//                return true;
//
//            } else {
//
//                return false;
//
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @NonNull
//    public static Set<String> findDuplicates(@NonNull List<String> listContainingDuplicates) {
//
//        final Set<String> setToReturn = new HashSet<String>();
//        final Set<String> set1 = new HashSet<String>();
//
//        for (String yourInt : listContainingDuplicates) {
//            if (!set1.add(yourInt)) {
//                setToReturn.add(yourInt);
//            }
//        }
//        return setToReturn;
//    }
//
//    public static boolean isMyServiceRunning(@NonNull Class<?> serviceClass, @NonNull Context mContext) {
//        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//            if (serviceClass.getName().equals(service.service.getClassName())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    public static int getToolbarHeight(@NonNull Context context) {
//        int height = (int) context.getResources().getDimension(R.dimen.abc_action_bar_default_height_material);
//        return height;
//    }
//
//    public static int getStatusBarHeight(@NonNull Context context) {
//        int height = (int) context.getResources().getDimension(R.dimen.statusbar_size);
//        return height;
//    }
//
//    public static int getColorWithAlpha(int baseColor, float alpha) {
//        int a = Math.min(255, Math.max((int) (alpha * 255), 0)) << 24;
//        int rgb = baseColor & 0x00ffffff;
//        return rgb + a;
//    }
//}
