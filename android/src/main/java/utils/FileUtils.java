package utils;//package utils;
//
//import android.content.ActivityNotFoundException;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.pdf.PdfDocument;
//import android.net.Uri;
//import android.os.Environment;
//import android.print.PrintAttributes;
//import android.print.pdf.PrintedPdfDocument;
//import android.widget.LinearLayout;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//
//import com.bm.main.fpl.activities.BaseActivity;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.text.DecimalFormat;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import timber.log.Timber;
//
//public class FileUtils {
//
//    public static String getFileSize(long size) {
//        if (size <= 0)
//            return "0";
//
//        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
//        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
//
//        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
//    }
//
//    public static void doCekPDF(@NonNull String nama_pdf, String url_pdf) {
//        Timber.d("doCekPDF: " + nama_pdf + " " + url_pdf);
//        try {
//            String path = Environment.getExternalStorageDirectory().toString();
//            File dir = new File(path, "/Profit/struk/pdfs");
//            if (!dir.isDirectory()) {
//                dir.mkdirs();
//            }
//            File file = new File(dir, nama_pdf);
//
//            if (!file.exists()) {
//                Timber.d("doCekLogo: ga exist");
//                downloadpdf(nama_pdf.replace(".pdf", ""), dir.getAbsolutePath(), url_pdf);
//            } else {
//                file.delete();
//                downloadpdf(nama_pdf.replace(".pdf", ""), dir.getAbsolutePath(), url_pdf);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void downloadpdf(String imageFile, String saveDir, String url_pdf) {
//        Timber.d("download pdf: %s", url_pdf);
//        try {
//            URL verifiedUrl = UrlUtils.verifyUrl(url_pdf);
//            if (verifiedUrl != null) {
//                UrlUtils.trustEveryone();
//
//                downloadFilePDF(url_pdf, saveDir, imageFile);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(BaseActivity.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//        }
//    }
//
//    private static final int BUFFER_SIZE = 4096;
//
//    private static void downloadFilePDF(String fileURL, String saveDir, String fileName) throws IOException {
//        URL url = new URL(fileURL);
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        urlConnection.connect();
//        urlConnection.setInstanceFollowRedirects(false);
//        int responseCode = urlConnection.getResponseCode();
//        Timber.d("downloadFile: " + responseCode + " " + fileName);
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            String disposition = urlConnection.getHeaderField("Content-Disposition");
//            if (disposition != null) {
//                String depo = urlConnection.getHeaderField("Content-Disposition");
//
//                String depoSplit[] = depo.split("filename=");
//                fileName = depoSplit[1].replace("filename=", "").replace("\"", "").trim();
//            }
//
//            // opens input stream from the HTTP connection
//            InputStream inputStream = urlConnection.getInputStream();
//            String saveFilePath = saveDir + File.separator + fileName;
//            File fx = new File(saveFilePath);
//            String saveFilePathnnew = fx.getCanonicalPath();
//            FileOutputStream outputStream = new FileOutputStream(new File(saveFilePathnnew));
//            int bytesRead = -1;
//            byte[] buffer = new byte[BUFFER_SIZE];
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//            outputStream.close();
//            inputStream.close();
//            urlConnection.disconnect();
//        }
//    }
//
//    public static void saveToPdf(@NonNull Context context, @NonNull LinearLayout webView) {
//        PrintAttributes printAttrs = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//            String path;
//            File dir;
//            File file;printAttrs = new PrintAttributes.Builder().
//                    setColorMode(PrintAttributes.COLOR_MODE_COLOR).
//                    setMediaSize(PrintAttributes.MediaSize.NA_LETTER).
//                    setResolution(new PrintAttributes.Resolution("zooey", Context.PRINT_SERVICE, 300, 300)).
//                    setMinMargins(PrintAttributes.Margins.NO_MARGINS).
//                    build();
//
//            PdfDocument document = new PrintedPdfDocument(context, printAttrs);
//
//            // crate da page description
//            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(webView.getMeasuredWidth(), webView.getMeasuredHeight(), 1).create();
//
//            // create da new page from the PageInfo
//            PdfDocument.Page page = document.startPage(pageInfo);
//
//            // repaint the user's text into the page
//            //View content = findViewById(R.id.textArea);
//            webView.draw(page.getCanvas());
//
//            // do final processing of the page
//            document.finishPage(page);
//
//            // Here you could add more pages in da longer doc app, but you'd have
//            // to handle page-breaking yourself in e.g., write your own word processor...
//
//            // Now write the PDF document to da file; it actually needs to be da file
//            // since the Share mechanism can't accept da byte[]. though it can
//            // accept da String/CharSequence. Meh.
//            try {
//                path = Environment.getExternalStorageDirectory().toString();
//                dir = new File(path, "/FastPay/struk/pdfs");
//                if (!dir.isDirectory()) {
//                    dir.mkdirs();
//                }
//                String arquivo = "produk_" + System.currentTimeMillis() + ".pdf";
//                file = new File(dir, arquivo);
//
//                OutputStream os = new FileOutputStream(file);
//                document.writeTo(os);
//                document.close();
//                os.close();
//
//                openPdf(context, file);
//            } catch (IOException e) {
//                throw new RuntimeException("Error generating file", e);
//            }
//        }
//    }
//
//    public static void openPdf(@NonNull Context context, File imageFile) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(imageFile);
//        intent.setDataAndType(uri, "application/pdf");
//        try {
//            context.startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setTitle("Aplikasi PDF Reader Tidak Ditemukan");
//            builder.setMessage("Apakah bersedia download dari playstore ?");
//            builder.setPositiveButton("Ya, Silahkan",
//                    (dialog, which) -> {
//                        Intent marketIntent = new Intent(Intent.ACTION_VIEW);
//                        marketIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.adobe.reader"));
//                        context.startActivity(marketIntent);
//                    });
//            builder.setNegativeButton("Tidak, Terimakasih", null);
//            builder.create().show();
//        }
//    }
//
//    public static void openImage(Context context, File imageFile) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(imageFile);
//        //    intent.setDataAndType(uri, "pdf/*");
//        intent.setDataAndType(uri, "image/*");
//
//        context.startActivity(intent);
//    }
//}
