package utils;//package utils;
//
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothSocket;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//
//import androidx.annotation.NonNull;
//import androidx.localbroadcastmanager.content.LocalBroadcastManager;
//
//import com.bm.main.fpl.activities.BaseActivity;
//import com.bm.main.fpl.activities.SettingPrinterActivity;
//import com.bm.main.fpl.constants.ResponseCode;
//import com.bm.main.scm.R;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.lang.reflect.Method;
//import java.util.Set;
//import java.util.UUID;
//
//import timber.log.Timber;
//
//public class PrintUtils {
//    private static BluetoothSocket socket;
//    public static BluetoothAdapter bluetoothAdapter = null;
//    public static BluetoothDevice mmDevice;
//    private static OutputStream os;
//    public static String strukTercetak;
//    public static Bitmap bitmapQRCode;
//    public static int isMatrix = 0;
//
//    public static synchronized void cetak(@NonNull BaseActivity mContext) {
//        new Thread(() -> {
//            try {
//                print(mContext);
//            } catch (Exception e) {
//                Timber.e(e);
//            } finally {
//                try {
//                    if (socket != null) {
//                        try {
//                            Thread.sleep(4000);
//                        } catch (InterruptedException e) {
//                            Timber.e(e);
//                        }
//                        socket.close();
//                    }
//                } catch (IOException e) {
//                    mContext.showToast("Tidak dapat mencetak struk ke printer bluetooth");
//                }
//            }
//        }).start();
//    }
//
//    public static void print(@NonNull BaseActivity mContext) {
//        String deviceName;
//
//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//
//        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
//        if (pairedDevices.size() > 0) {
//            Timber.d("print: pairedDevices.size() " + pairedDevices.size());
//            try {
//                if (!bluetoothAdapter.isEnabled()) {
//                    if (!bluetoothAdapter.enable()) {
//                        mContext.showToast("pastikan bluetooth handphone anda menyala");
//                    }
//                }
//
//                for (BluetoothDevice device : pairedDevices) {
//                    deviceName = device.getName();
//                    String a = device.getAddress();
//                    mmDevice = bluetoothAdapter.getRemoteDevice(device.getAddress());
//                    UUID BPP = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
//                    try {
//                        socket = mmDevice.createRfcommSocketToServiceRecord(BPP);
//                    } catch (IOException e) {
//                        mContext.showToast("Tidak dapat melakukan koneksi awal dengan printer bluetooth"); // Unable
//                    }
//                    Method m = mmDevice.getClass().getMethod("createRfcommSocket", int.class);
//                    try {
//                        socket = (BluetoothSocket) m.invoke(mmDevice, 1);
//                        if (socket != null) {
//                            socket.connect();
//                            Timber.d("Connected 1 " + socket.isConnected());
//                        }
//
//                    } catch (Exception e) {
//                        try {
//                            Timber.d("Trying fallback..." + e.toString());
//                            Timber.d("mencoba menghubungkan kembali ke printer");
//                            socket = mmDevice.createRfcommSocketToServiceRecord(BPP);
//                            socket = (BluetoothSocket) m.invoke(mmDevice, 1);
//                            if (socket != null) {
//                                socket.connect();
//                                Timber.d("Connected 2 " + socket.isConnected());
//                            }
//                        } catch (Exception e2) {
//                            Timber.e(e2);
//                            mContext.snackBarCustomAction(mContext.findViewById(R.id.btn_test_print), 0, "Tidak dapat terhubung ke printer bluetooth " + deviceName + "\n Pastikan printer bluetooth " + deviceName + "anda telah menyala", 2);
//                        }
//                    }
//
//                    Timber.d("print socket: " + socket.isConnected());
//                    if (socket.isConnected()) {
//                        try {
//                            os = socket.getOutputStream();
//                            try {
//                                os.write(ResponseCode.InitializePrinter.getBytes());
//                                printLogo(mContext);
//                                os.write(strukTercetak.getBytes("UTF-8"), 0, strukTercetak.getBytes("UTF-8").length);
//                                if (PreferenceClass.getBoolean("switchQRCodeStruk", true) == true) {
//                                    if (bitmapQRCode != null) {
//                                        printQRCode();
//                                    }
//                                }
//                                os.write(0xA);
//                                os.write(0xA);
//                                os.flush();
//                            } catch (Exception e) {
//                                mContext.showToast("Tidak dapat mencetak struk ke printer bluetooth" + e.toString()); //
//                            } finally {
//                                Timber.d("print: finnaly");
//                                if (os != null) {
//                                    try {
//                                        Thread.sleep(4000);
//                                    } catch (InterruptedException e) {
//                                        Timber.e(e);
//                                    }
//                                    os.close();
//
//                                }
//                            }
//                        } catch (Exception e) {
//                            mContext.showToast("Tidak dapat mencetak struk ke printer bluetooth");
//                        }
//                    } else {
//                        Timber.d("print: ga konek");
//                    }
//                }
//
//                Intent intent = new Intent("BROADCAST_PRINTING");
//                // Put the random number to intent to broadcast it
//                intent.putExtra("finish", true);
//
//                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
//                Timber.d("print: finish");
//                Thread.currentThread().interrupt();
//            } catch (Exception e) {
//                Timber.d("print exception this: " + e.toString());
//                Thread.currentThread().interrupt();
//            }
//        } else {
//            Intent intentBrod = new Intent("BROADCAST_PRINTING");
//            // Put the random number to intent to broadcast it
//            intentBrod.putExtra("finish", true);
//
//            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intentBrod);
//            mContext.snackBarCustomAction(mContext.findViewById(R.id.bottom_toolbar), 0, "Pastikan printer bluetooth anda On dan telah melakukan pairing dengan printer bluetooth", 2);
//            mContext.snackbar.dismiss();
//            Thread.currentThread().interrupt();
//            Intent intent = new Intent(mContext, SettingPrinterActivity.class);
//            mContext.startActivity(intent);
//        }
//    }
//
//    private static void printLogo(Context context) throws IOException {
//        Drawable d = Drawable.createFromStream(context.getAssets().open("logo_fp.bmp"), null);
//
//        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
//        PrintPic pg = PrintPic.getInstance();
//        pg.initCanvas(350);
//        pg.initPaint();
//        pg.drawImage(58, 0, bitmap);
//        byte[] bitmapdata = pg.printDraw();
//        os.write(bitmapdata, 0, bitmapdata.length);
//    }
//
//    private static void printQRCode() throws IOException {
//        Bitmap bitmap = bitmapQRCode;
//        PrintPic pgQR = PrintPic.getInstance();
//        byte[] sendDataQR;
//        pgQR.initCanvas(380);
//        pgQR.initPaint();
//        if (isMatrix == 1) {
//            pgQR.drawImage(0, 0, bitmap);
//        } else {
//            pgQR.drawImage(125, 0, bitmap);
//        }
//        sendDataQR = pgQR.printDraw();
//        if (sendDataQR != null) {
//            os.write(sendDataQR, 0, sendDataQR.length);
//        }
//    }
//}
