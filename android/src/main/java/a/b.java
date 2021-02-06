package a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class b {//implements Comparable {
    @NonNull
    private String parsebstringa = "";
    @NonNull
    private String parsebstringb = "";
    private String parsebstringc = "";
    private String parsebstringd = "";
    private String parsebstringe = "";
    private String parsebstringf = "";
    private String parsebstringg = "";
    private String parsebstringh = "";
    private String parsebstringi = "";
    @NonNull
    private String parsebstringj = "";
    private String parsebstringk = "";
    @Nullable
    private Date parsebdatel = null;
    private String parsebstringm = "";

    @NonNull
    public String a() {
        return this.parsebstringa;
    }

    public void a(String str) {
        this.parsebstringm = str;
    }

    @NonNull
    public String b() {
        return this.parsebstringb;
    }

    public void b(@NonNull String str) {
        this.parsebstringa = str;
        switch (str) {
            case "04":
                str = "3335313643363536";
                break;
            case "14":
                str = "343546363037303235353037";
                break;
            case "24":
                str = "343546363037303235353037303234353537453631363936";
                break;
            case "34":
                str = "353530373436313634373536303233353136433634364636";
                break;
            default:
                if (str.equals("44")) {
                    this.parsebstringb = i.a(i.b(i.a("3035353632373436313645363136")));
                }
                return;
        }
        this.parsebstringb = i.a(i.b(i.a(str)));
    }

    public String c() {
        return this.parsebstringc;
    }

    public void c(String str) {
        this.parsebstringc = str;
    }

    public int compareTo(@NonNull b nfcparseb) {
        return nfcparseb.m().compareTo(m());
    }

    public String d() {
        return this.parsebstringd;
    }

    public void d(String str) {
        StringBuilder stringBuilder;
        String replace;
        this.parsebstringd = str;
        this.parsebstringe = new DecimalFormat("#,###,###").format((long) g.b(str));
        if (this.parsebstringe.contains(",")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(i.a(i.b(i.a("323530373032"))));
            replace = this.parsebstringe.replace(",", ".");
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(i.a(i.b(i.a("323530373032"))));
            replace = this.parsebstringe;
        }
        stringBuilder.append(replace);
        this.parsebstringe = stringBuilder.toString();
    }

    public String e() {
        return this.parsebstringe;
    }

    public void e(String str) {
        StringBuilder stringBuilder;
        String replace;
        this.parsebstringf = str;
        this.parsebstringg = new DecimalFormat("#,###,###").format((long) g.b(str));
        if (this.parsebstringg.contains(",")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(i.a(i.b(i.a("323530373032"))));
            replace = this.parsebstringg.replace(",", ".");
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(i.a(i.b(i.a("323530373032"))));
            replace = this.parsebstringg;
        }
        stringBuilder.append(replace);
        this.parsebstringg = stringBuilder.toString();
    }

    public String f() {
        return this.parsebstringf;
    }

    public void f(String str) {
        this.parsebstringh = str;
    }

    public String g() {
        return this.parsebstringg;
    }

    public void g(String str) {
        this.parsebstringi = str;
        this.parsebstringj = g.a(str);
    }

    public String h() {
        return this.parsebstringh;
    }

    public void h(String r3) {
        this.parsebstringk = r3;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        try
        {
            this.parsebdatel = localSimpleDateFormat.parse(g.c(r3));
            return;
        }
        catch (ParseException paramString)
        {
           // for (;;) {}
            this.parsebdatel = null;
        }

    }

    public String i() {
        return this.parsebstringi;
    }

    @NonNull
    public String j() {
        return this.parsebstringj;
    }

    public String k() {
        return this.parsebstringk;
    }

    @Nullable
    public Date l() {
        return this.parsebdatel;
    }

    public String m() {
        return this.parsebstringm;
    }




}
