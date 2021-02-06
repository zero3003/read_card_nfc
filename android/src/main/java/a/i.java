package a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class i extends URLClassLoader {
    public i(@Nullable ClassLoader classLoader, @NonNull File file) throws MalformedURLException {
        super(new URL[]{file.toURI().toURL()}, classLoader);
        if (classLoader == null) {
            throw new IllegalArgumentException("MyClassLoader requires a non-null delegation parent");
        }
    }

    private static int a(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        int i1 = 65;
        if (c < 'A' || c > 'F') {
            i1 = 97;
            if (c < 'a' || c > 'f') {
                return 0;
            }
        }
        return (c - i1) + 10;
    }

    @NonNull
    public static String a(@NonNull String str) {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] c = i.c(str);
        for (byte b : c) {
            stringBuilder.append((char) b);
        }
        return stringBuilder.toString();
    }

    public static byte[] a(@NonNull InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
     //   byte[] bArr = new byte[8192];
        byte[] bArr = new byte[' '];
        while (true) {
            int read = 0;
            try {
                read = inputStream.read(bArr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (read <= 0) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    @NonNull
    public static byte[] a(@NonNull byte[] bArr) {
        for (int i = 16; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ 90);
        }
        return bArr;
//        int i = 16;
//        byte[] paramArrayOfByte = new byte[0];
//        while (i < bArr.length)
//        {
//            paramArrayOfByte[i] = ((byte)(bArr[i] ^ 0x5A));
//            i += 1;
//        }
//        return paramArrayOfByte;
    }

    @NonNull
    public static String b(@NonNull String str) {
        StringBuilder stringBuilder;
        if (str.length() % 2 == 1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("0");
            str = stringBuilder.toString();
        }
        stringBuilder = new StringBuilder(str.length());
        for (int i = 0; i < str.length() / 2; i++) {
            StringBuilder stringBuilder2 = new StringBuilder();
            int i2 = i * 2;
            int i3 = i2 + 1;
            stringBuilder2.append(str.substring(i3, i2 + 2));
            stringBuilder2.append(str.substring(i2, i3));
            stringBuilder.append(stringBuilder2.toString());
        }
        return stringBuilder.toString();
    }

    @NonNull
    public static byte[] c(@NonNull String str) {
        int length = str.length();
        byte[] bArr = new byte[((length + 1) / 2)];
        int i1 = 0;
        int i2 = 1;
        if (length % 2 == 1) {
            bArr[0] = (byte) i.a(str.charAt(0));
            i1 = 1;
        } else {
            i2 = 0;
        }
        while (i1 < length) {
            int i3 = i2 + 1;
            int i4 = i1 + 1;
            int i5 = i4 + 1;
            bArr[i2] = (byte) ((i.a(str.charAt(i1)) << 4) | i.a(str.charAt(i4)));
            i2 = i3;
            i1 = i5;
        }
        return bArr;
    }

    @NonNull
    public Class d(@NonNull InputStream paramInputStream, String paramString, boolean paramBoolean) {

        Class<?> localObject2Class = findLoadedClass(paramString);
        Class<?> localObject1 = localObject2Class;
        ClassLoader localClassLoader;
        if (localObject2Class == null) {
           // localObject3 = null;
        }
//        for (;;)
//        {
            try
            {
                localObject1 = getParent().loadClass(paramString);
            }
            catch (@NonNull ClassNotFoundException|ClassFormatError localClassNotFoundException1)
            {
               //
              //  continue;
            }
            try
            {
                ClassLoader localObject3 = ((Class) localObject1).getClassLoader();
                localClassLoader = getParent();
                if (localObject3 != localClassLoader) {
                    localObject2Class = localObject1;
                }
            }
            catch (ClassFormatError localClassNotFoundException2) {}
      //  }
        Object localObject3 = localObject1;
        //localObject1 = localObject2Class;
        //if (localObject2Class == null) {}
        localObject1 = e(paramInputStream, paramString);
       // localObject1 = localObject3;
        if (localObject1 == null) {
            try {
                throw new ClassNotFoundException(paramString);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (paramBoolean) {
            resolveClass((Class)localObject1);
        }
        return (Class)localObject1;

    }


    @NonNull
    public Class d(String paramString1, String paramString2, boolean paramBoolean) {

        Class<?> localObject2Class = findLoadedClass(paramString2);
        Class<?> localObject1 = localObject2Class;
        ClassLoader localClassLoader;
        if (localObject2Class == null) {
           // localObject3 = null;
        }
//        for (;;)
//        {
            try
            {
                localObject1 = getParent().loadClass(paramString2);
            }
            catch (@NonNull ClassNotFoundException|ClassFormatError localClassNotFoundException1)
            {
//                ClassLoader localClassLoader;
//                continue;
            }
            try
            {
                ClassLoader localObject3 = ((Class) localObject1).getClassLoader();
                localClassLoader = getParent();
                if (localObject3 != localClassLoader) {
                    localObject2Class = localObject1;
                }
            }
            catch (ClassFormatError localClassNotFoundException2) {}
       // }
        Class<?> localObject3 = localObject1;
        localObject1 = localObject2Class;
        //if (localObject2 == null) {}
        localObject1 = e(paramString1, paramString2);
        localObject1 = localObject3;
        if (localObject1 == null) {
            try {
                throw new ClassNotFoundException(paramString2);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (paramBoolean) {
            resolveClass((Class)localObject1);
        }
        return (Class)localObject1;

    }


    protected Class e(@NonNull InputStream paramInputStream, String paramString) {

        byte[] paramInputStreamByte = a(a(paramInputStream));
       // Class<?> paramInputStreamClass = defineClass(paramString, paramInputStreamByte, 0, paramInputStreamByte.length);
        return defineClass(paramString, paramInputStreamByte, 0, paramInputStreamByte.length);
        // throw new ClassNotFoundException(paramString);

    }

    @Nullable
    protected Class e(String param1, String param2) {
        URL url = getResource(param1);
        InputStream inputStream = null;
        Class<?> aClass = null;
        if(url!=null){
            try {
                 inputStream = url.openStream();
                byte[] im49aByte = a(inputStream);
                byte[] m50aByte = a(im49aByte);
                int m50aInt = m50aByte.length;
                aClass = defineClass(param2, m50aByte, 0, m50aInt);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return aClass;

        }else{
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return aClass;
        }

       // return null;
    }

}
