package nfclib;

import android.nfc.tech.IsoDep;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import a.d;
import a.e;

public class FlazzCardReader implements e {
    private static final String TAG = "CardReader";
    private IsoDep mIsoDep;

    public FlazzCardReader(IsoDep isoDep) {
        this.mIsoDep = isoDep;
    }

    @NonNull
    public d a
        (@NonNull String paramString)
        {
            Log.d(TAG, "d a flazzCardreader: "+paramString);
            d locald = new d();
            String paramStringx="";
            if ((this.mIsoDep != null) && (this.mIsoDep.isConnected())) {}
            try
            {
                byte[] paramStringByte = SConverter.Hexchar2Bytes(paramString);
                paramStringx = SConverter.Bytes2Hexchar(this.mIsoDep.transceive(paramStringByte));
            }
            catch (IOException ioe)
            {
//                for (;;) {}

            }
            // String paramStringss = "";
            if (paramStringx.length() > 0)
            {
                locald.a(true);
                locald.b(paramStringx);
                return locald;
            }
            locald.a(false);
            locald.b("");
            return locald;
        }

}
