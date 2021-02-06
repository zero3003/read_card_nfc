package a;

import androidx.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;

public final class f {
//    public static Object a(j c0009j, String str, String str2, Class[] clsArr, Object[] objArr) {
//        Class a = c0009j.a(str);
//        return a.getDeclaredMethod(str2, clsArr).invoke(a.newInstance(), objArr);
//    }
    public static Object a(@NonNull j paramj, String paramString1, @NonNull String paramString2, Class[] clsArr, Object[] paramArrayOfObject) {

        System.out.println(paramj+" "+paramString1+" "+paramString2+" "+clsArr);

        Class a = paramj.a(paramString1);
        try {
            return a.getDeclaredMethod(paramString2, clsArr).invoke(a.newInstance(), paramArrayOfObject);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return a;
    }

}
