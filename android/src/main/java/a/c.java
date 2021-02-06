package a;

import java.util.ArrayList;
import java.util.List;

public class c {
    List parseclista = new ArrayList(10);
    List parseclistb = new ArrayList(2);
    private boolean parsecbooleanc = false;

    public void a(List list) {
        this.parseclista = list;
    }

    public boolean a() {
        return this.parsecbooleanc;
    }

    public List b() {
        return this.parseclista;
    }

    public void b(List list) {
        this.parseclistb = list;
    }

    public List c() {
        return this.parseclistb;
    }

    public void c(boolean z) {
        this.parsecbooleanc = z;
    }
}
