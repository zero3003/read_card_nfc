package utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by dian on 04/04/16.
 */
public final class Var {

    @NonNull
    public static <T> T trimNull(@Nullable Object obj, @NonNull T def) {
        return obj != null ? (T) obj : def;
    }

    public static int toInt(Object _int, int def) {
        try {
            return Integer.parseInt(trimNull(_int, ""));
        } catch (NumberFormatException ex) {
            return def;
        }
    }

    public static double toDouble(Object _d, double def) {
        try {
            return Double.parseDouble(trimNull(_d, ""));
        } catch (NumberFormatException ex) {
            return def;
        }
    }

    public static float toFloat(Object _d, float def) {
        try {
            return Float.parseFloat(trimNull(_d, ""));
        } catch (NumberFormatException ex) {
            return def;
        }
    }

    public static <T> T[] arrayFilter(@NonNull T[] arr) {
        ArrayList<T> list = new ArrayList<T>();

        for (T s : arr) {
            boolean condition_met = true;
            if (s instanceof String) {
                condition_met = ((String) s).length() > 0;
            }
            if (s != null && condition_met) {
                list.add(s);
            }
        }

        return list.toArray(arr);
    }

    public static <T> T getIgnoreBound(@NonNull T[] arr, int index, T def) {
        if (index >= arr.length) {
            return def;
        } else {
            return arr[index];
        }
    }

    /**
     * Checks whether any of the given parameters are null
     *
     * @param object the object/objects/array of objects to check
     * @return true if any give parameter is null
     */
    public static boolean isNull(@NonNull Object... object) {
        for (Object object1 : object) {
            if (object1 == null) {
                return true;
            }
        }
        return false;
    }

}
