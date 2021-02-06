package a;

import androidx.annotation.NonNull;

public final class g {
    public static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static int a(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        int i = 65;
        if (c < 'A' || c > 'F') {
            i = 97;
            if (c < 'a' || c > 'f') {
                return 0;
            }
        }
        return (c - i) + 10;
    }

    @NonNull
    public static String a(@NonNull String str) {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] d = g.d(str);
        for (byte b : d) {
            stringBuilder.append((char) b);
        }
        return stringBuilder.toString();
    }

    public static int b(@NonNull String str) {
        return Integer.parseInt(str, 16);
    }

    @NonNull
    public static String c(@NonNull String str) {
//        StringBuilder stringBuilder = new StringBuilder();
//        int parseInt = Integer.parseInt(str, 16);
//        int i = parseInt / 86400;
//        StringBuilder stringBuilder2 = new StringBuilder();
//        i = (i + 2444240) + 68569;
//        i -= ((146097 * ((4 * i) / 146097)) + 3) / 4;
//        i = (i - ((1461 * ((4000 * (i + 1)) / 1461001)) / 4)) + 31;
//        int i2 = (80 * i) / 2447;
//        i -= (2447 * i2) / 80;
//        i2 = (i2 + 2) - (12 * (i2 / 11));
//        stringBuilder2.append(String.format("%2s", new Object[]{Integer.valueOf(((((r5 - 49) * 100) + r7) + r4) % 100)}).replace(' ', '0'));
//        stringBuilder2.append(String.format("%2s", new Object[]{Integer.valueOf(i2)}).replace(' ', '0'));
//        stringBuilder2.append(String.format("%2s", new Object[]{Integer.valueOf(i)}).replace(' ', '0'));
//        stringBuilder.append(stringBuilder2.toString());
//        parseInt %= 86400;
//        int i3 = parseInt / 3600;
//        parseInt %= 3600;
//        i = parseInt / 60;
//        parseInt %= 60;
//        stringBuilder.append(String.format("%2s", new Object[]{Integer.valueOf(i3)}).replace(' ', '0'));
//        stringBuilder.append(String.format("%2s", new Object[]{Integer.valueOf(i)}).replace(' ', '0'));
//        stringBuilder.append(String.format("%2s", new Object[]{Integer.valueOf(parseInt)}).replace(' ', '0'));
//        return stringBuilder.toString();
        StringBuilder localStringBuilder = new StringBuilder();
        int i = Integer.parseInt(str, 16);
        int j = i / 86400;
        StringBuilder paramString = new StringBuilder();
        int k = j + 2444240 + 68569;
        j = 4 * k / 146097;
        int m = k - (146097 * j + 3) / 4;
        k = 4000 * (m + 1) / 1461001;
        m = m - 1461 * k / 4 + 31;
        int n = 80 * m / 2447;
        int i1 = 2447 * n / 80;
        int i2 = n / 11;
        paramString.append(String.format("%2s", ((j - 49) * 100 + k + i2) % 100).replace(' ', '0'));
        paramString.append(String.format("%2s", n + 2 - 12 * i2).replace(' ', '0'));
        paramString.append(String.format("%2s", m - i1).replace(' ', '0'));
        localStringBuilder.append(paramString.toString());
        j = i % 86400;
        i = j / 3600;
        j %= 3600;
        k = j / 60;
        localStringBuilder.append(String.format("%2s", i).replace(' ', '0'));
        localStringBuilder.append(String.format("%2s", k).replace(' ', '0'));
        localStringBuilder.append(String.format("%2s", j % 60).replace(' ', '0'));
        return localStringBuilder.toString();
    }

    @NonNull
    private static byte[] d(@NonNull String str) {
        int length = str.length();
        byte[] bArr = new byte[((length + 1) / 2)];
        int i = 0;
        int i2 = 1;
        if (length % 2 == 1) {
            bArr[0] = (byte) g.a(str.charAt(0));
            i = 1;
        } else {
            i2 = 0;
        }
        while (i < length) {
            int i3 = i2 + 1;
            int i4 = i + 1;
            int i5 = i4 + 1;
            bArr[i2] = (byte) ((g.a(str.charAt(i)) << 4) | g.a(str.charAt(i4)));
            i2 = i3;
            i = i5;
        }
        return bArr;
    }
}
