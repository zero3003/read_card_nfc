package utils;

/*
  Created by Sarif Hidayat on 05/05/2017.
 */

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@SuppressLint({"SdCardPath"})
public class PrintPic {
    @Nullable
    public Canvas canvas = null;
    @Nullable
    public Paint paint = null;
    @Nullable
    public Bitmap bm = null;
    public int width;
    public float length = 0.0F;
    @Nullable
    private byte[] bitbuf = null;

    public PrintPic() {
    }

    @NonNull
    private static PrintPic instance = new PrintPic();

    @NonNull
    public static PrintPic getInstance() {
        return instance;
    }

    public void init(@Nullable Bitmap bitmap) {
        if (null != bitmap) {
            initCanvas(bitmap.getWidth());
//            initCanvas(300);
        }
        if (null == paint) {
            initPaint();
        }
        if (null != bitmap) {
            // drawImage((float)(bitmap.getWidth()/2), 0, bitmap);
            drawImage(0, 0, bitmap);
            //  drawImage(60, 0, bitmap);
        }
    }


    public int getWidth() {
        return this.width;
    }

    public int getLength() {
        return (int) this.length + 20;
    }

    public void initCanvas(int w) {
        int h = 10 * w;
        this.bm = Bitmap.createBitmap(w, h, Config.RGB_565);
//        this.bm = Bitmap.createBitmap(w, h, Config.ARGB_8888);

        this.canvas = new Canvas(this.bm);
        this.canvas.drawColor(-1);
        this.width = w;
        this.bitbuf = new byte[this.width / 8];
        //  this.bitbuf = new byte[this.width / 4];

    }

    public void initPaint() {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        // this.paint.setColor(-16777216);
//        this.paint.setStyle(Style.STROKE);
        this.paint.setStyle(Style.FILL);
        this.paint.setTextAlign(Paint.Align.CENTER);
    }

    public void drawImage(float x, float y, String path) {
        Bitmap e = null;
        try {
            e = BitmapFactory.decodeFile(path);
            this.canvas.drawBitmap(e, x, y, (Paint) null);
            //    this.canvas.drawBitmap(e, e.getWidth()/2, y, (Paint)null);
            if (this.length < y + (float) e.getHeight()) {
                this.length = y + (float) e.getHeight();
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        } finally {
            if (null != e) {
                e.recycle();
            }
        }

    }

    /**
     * draw bitmap
     */
    public void drawImage(float x, float y, @NonNull Bitmap btm) {

        try {
            // Bitmap btm = BitmapFactory.decodeFile(path);
            this.canvas.drawBitmap(btm, x, y, null);
            if (this.length < y + btm.getHeight())
                this.length = (y + btm.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (null != btm) {
//                btm.recycle();
//            }
            if (!btm.isRecycled()) {
                btm.recycle();
                // btm = null;
            }
        }
    }

    public void printPng(String file) {
        // File a = new File("/mnt/sdcard/0.png");
        File f = new File(file);
        FileOutputStream fos = null;
        Bitmap nbm = Bitmap.createBitmap(this.bm, 0, 0, this.width, this.getLength());

        try {
            fos = new FileOutputStream(f);
            nbm.compress(CompressFormat.PNG, 50, fos);
        } catch (FileNotFoundException var5) {
            var5.printStackTrace();
        }
    }

    @NonNull
    public byte[] printDraw() {
        Bitmap nbm = Bitmap.createBitmap(this.bm, 0, 0, this.width, this.getLength());
        byte[] imgbuf = new byte[this.width / 8 * this.getLength() + 8];
        boolean s = false;
        imgbuf[0] = 29;
        imgbuf[1] = 118;
        imgbuf[2] = 48;
        imgbuf[3] = 0;
        imgbuf[4] = (byte) (this.width / 8);
        imgbuf[5] = 0;
        imgbuf[6] = (byte) (this.getLength() % 256);
        imgbuf[7] = (byte) (this.getLength() / 256);
        int var23 = 7;

        for (int i = 0; i < this.getLength(); ++i) {
            int t;
            for (t = 0; t < this.width / 8; ++t) {
                int c0 = nbm.getPixel(t * 8 + 0, i);
                byte p0;
                if (c0 == -1) {
                    p0 = 0;
                } else {
                    p0 = 1;
                }

                int c1 = nbm.getPixel(t * 8 + 1, i);
                byte p1;
                if (c1 == -1) {
                    p1 = 0;
                } else {
                    p1 = 1;
                }

                int c2 = nbm.getPixel(t * 8 + 2, i);
                byte p2;
                if (c2 == -1) {
                    p2 = 0;
                } else {
                    p2 = 1;
                }

                int c3 = nbm.getPixel(t * 8 + 3, i);
                byte p3;
                if (c3 == -1) {
                    p3 = 0;
                } else {
                    p3 = 1;
                }

                int c4 = nbm.getPixel(t * 8 + 4, i);
                byte p4;
                if (c4 == -1) {
                    p4 = 0;
                } else {
                    p4 = 1;
                }

                int c5 = nbm.getPixel(t * 8 + 5, i);
                byte p5;
                if (c5 == -1) {
                    p5 = 0;
                } else {
                    p5 = 1;
                }

                int c6 = nbm.getPixel(t * 8 + 6, i);
                byte p6;
                if (c6 == -1) {
                    p6 = 0;
                } else {
                    p6 = 1;
                }

                int c7 = nbm.getPixel(t * 8 + 7, i);
                byte p7;
                if (c7 == -1) {
                    p7 = 0;
                } else {
                    p7 = 1;
                }

                int value = p0 * 128 + p1 * 64 + p2 * 32 + p3 * 16 + p4 * 8 + p5 * 4 + p6 * 2 + p7;
                this.bitbuf[t] = (byte) value;
            }

            for (t = 0; t < this.width / 8; ++t) {
                ++var23;
                imgbuf[var23] = this.bitbuf[t];
            }
        }

        if (bm != null && !bm.isRecycled()) {
            bm.recycle();
            bm = null;
        }

        return imgbuf;
    }
}

