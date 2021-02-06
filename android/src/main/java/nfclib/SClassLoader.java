package nfclib;

import androidx.annotation.Nullable;

import java.io.PrintStream;

import a.j;
import dalvik.system.DexClassLoader;

public class SClassLoader implements j {
    private DexClassLoader dcl;

    public SClassLoader(DexClassLoader dexClassLoader) {
        this.dcl = dexClassLoader;
    }

    @Nullable
    public Class<?> a(String paramString) {
//        try {
//            PrintStream printStream = System.out;
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("ClassName=");
//            stringBuilder.append(str);
//            printStream.println(stringBuilder.toString());
//            return this.dcl.loadClass(str);
//        } catch (Exception str2) {
//            System.out.println("exception2");
//            str2.printStackTrace();
//            return null;
//        }
        try
        {
            PrintStream localPrintStream = System.out;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("ClassName=");
            localStringBuilder.append(paramString);
            localPrintStream.println(localStringBuilder.toString());
            Class<?> paramStringx = this.dcl.loadClass(paramString);
            return paramStringx;
        }
        catch (Exception e)
        {
            System.out.println("exception2 "+e.toString());
            e.printStackTrace();
            return null;
        }

    }
}
