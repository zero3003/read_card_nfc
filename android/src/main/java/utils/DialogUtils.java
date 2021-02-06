package utils;//package utils;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.Paint;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.text.Html;
//import android.text.SpannableString;
//import android.text.Spanned;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.widget.AppCompatButton;
//import androidx.cardview.widget.CardView;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bm.main.fpl.activities.BaseActivity;
//import com.bm.main.fpl.activities.CaraDepositActivity;
//import com.bm.main.fpl.activities.CekDepositActivity;
//import com.bm.main.fpl.activities.NotificationTopSingleActivity;
//import com.bm.main.fpl.adapters.ListPaketOutletAdapter;
//import com.bm.main.fpl.constants.EventParam;
//import com.bm.main.fpl.models.PaketModelModelResponse_valueTipe_loket_detail;
//import com.bm.main.fpl.models.PaketModelModelResponse_valueTipe_loket_footer;
//import com.bm.main.fpl.models.PromoProdukModel;
//import com.bm.main.fpl.templates.indicators.AVLoadingIndicatorView;
//import com.bm.main.fpl.templates.shimmer.ShimmerFrameLayout;
//import com.bm.main.scm.R;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.request.target.BitmapImageViewTarget;
//import com.bumptech.glide.request.transition.Transition;
//import com.crowdfire.cfalertdialog.CFAlertDialog;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import timber.log.Timber;
//
//public class DialogUtils {
//
//    private static Dialog mPromoDialog, mBottomSheetDialog;
//
//    public static void openPromoDialog(@NonNull final Context context, @NonNull View v, @NonNull List<PromoProdukModel.Response_value> response) {
//        final PromoProdukModel.Response_value promo = response.get(0);
//        final String judul = promo.getJudul();
//        final String content = promo.getContent();
//        String url = promo.getUrl_gambar();
//        final String url_landscape = promo.getUrl_gambar_landscape();
//        LinearLayout linPromo = v.findViewById(R.id.linPromo);
//        CardView card_view = v.findViewById(R.id.card_view);
//
//        final AVLoadingIndicatorView avi = v.findViewById(R.id.avi);
//        AppCompatButton button_baca = v.findViewById(R.id.button_baca);
//        TextView button_tidak = v.findViewById(R.id.button_tidak);
//        TextView textViewPlusJudul = v.findViewById(R.id.textViewPlusJudul);
//        final ImageView imageViewPromo = v.findViewById(R.id.imageViewPromo);
//        float heightDp = (context.getResources().getDisplayMetrics().heightPixels * 2) / 3;
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) card_view.getLayoutParams();
//
//        lp.height = (int) heightDp;
//        card_view.setMinimumHeight(lp.height);
//        textViewPlusJudul.setText(judul);
//
//        Glide.with(context).asBitmap().load(url).centerCrop().encodeFormat(Bitmap.CompressFormat.WEBP).encodeQuality(50).diskCacheStrategy(DiskCacheStrategy.NONE).into(new BitmapImageViewTarget(imageViewPromo) {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> animation) {
//                // here it's similar to RequestListener, but with less information (e.g. no model available)
//                super.onResourceReady(resource, animation);
//                avi.setVisibility(View.GONE);
//                // here you can be sure it's already set
//            }
//
//            // +++++ OR +++++
//            @Override
//            protected void setResource(Bitmap resource) {
//                // this.getView().setImageDrawable(resource); is about to be called
//                super.setResource(resource);
//                avi.setVisibility(View.GONE);
//
//                // here you can be sure it's already set
//            }
//
//            @Override
//            public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                super.onLoadFailed(errorDrawable);
//                avi.setVisibility(View.GONE);
//                imageViewPromo.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.srikandi));
//            }
//        });
//
//        button_baca.setOnClickListener(view -> {
//            Timber.d("onClick: " + url_landscape);
//            Intent intent = new Intent(context, NotificationTopSingleActivity.class);  //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            intent.putExtra("url", url_landscape);
//            intent.putExtra("title", judul);
//            intent.putExtra("conten", content);
//            context.startActivity(intent);
//
//            mPromoDialog.dismiss();
//        });
//
//        button_tidak.setOnClickListener(view -> mPromoDialog.dismiss());
//
//        mPromoDialog = new Dialog(context,
//                R.style.MaterialDialogSheet);
//        mPromoDialog.setContentView(v);
//        mPromoDialog.setCancelable(false);
//        mPromoDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        mPromoDialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
//        mPromoDialog.show();
//    }
//
//    public static void openPaketDetailDialog(@NonNull final Context context, @NonNull View v, @NonNull ArrayList<PaketModelModelResponse_valueTipe_loket_detail> response, @NonNull ArrayList<PaketModelModelResponse_valueTipe_loket_footer> response_footer, @NonNull String paketAlias, String paketUrl, String harga, ListPaketOutletAdapter listPaketOutletAdapter) {
//        PaketModelModelResponse_valueTipe_loket_detail promo = response.get(0);
//        final String avail = promo.getAvail();
//        final String content = promo.getDesc_name();
//
//        LinearLayout linBasic = v.findViewById(R.id.linBasic);
//        final ImageView imageViewBasic = v.findViewById(R.id.imageViewBasic);
//        LinearLayout linPro = v.findViewById(R.id.linPro);
//        final ImageView imageViewPro = v.findViewById(R.id.imageViewPro);
//        LinearLayout linEnterprise = v.findViewById(R.id.linEnterprise);
//        final ImageView imageViewEnterprise = v.findViewById(R.id.imageViewEnterprise);
//        TextView textViewHargaCoret = v.findViewById(R.id.textViewHargaCoret);
//        TextView textViewHargaHemat = v.findViewById(R.id.textViewHargaHemat);
//        TextView textViewHargaPaket = v.findViewById(R.id.textViewHargaPaket);
//
//        RecyclerView recycler_paket_detail = v.findViewById(R.id.recycler_paket_detail);
//        RelativeLayout relBottomPaket = v.findViewById(R.id.relBottomPaket);
//        AppCompatButton appButtonTutupPaket = v.findViewById(R.id.appButtonTutupPaket);
//
//        ShimmerFrameLayout frame_ribbon_termurah = v.findViewById(R.id.frame_ribbon_termurah);
//        ShimmerFrameLayout frame_ribbon_terlaris = v.findViewById(R.id.frame_ribbon_terlaris);
//
//        recycler_paket_detail.setAdapter(listPaketOutletAdapter);
//        LinearLayoutManager linearLayoutManagery = new LinearLayoutManager(context);
//        recycler_paket_detail.setHasFixedSize(false);
//        recycler_paket_detail.setLayoutManager(linearLayoutManagery);
//        recycler_paket_detail.setAdapter(listPaketOutletAdapter);
//        if (response_footer.get(0).getAvail().equals("1") && response_footer.get(0).getType_footer().equals("coret")) {
//            textViewHargaCoret.setVisibility(View.VISIBLE);
//            SpannableString ss = new SpannableString(FormatString.htmlString(response_footer.get(0).getDesc_name_footer()));
//            textViewHargaCoret.setPaintFlags(textViewHargaCoret.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//            textViewHargaCoret.setText(ss);
//        } else {
//            textViewHargaCoret.setVisibility(View.GONE);
//        }
//
//        if (response_footer.get(1).getAvail().equals("1") && response_footer.get(1).getType_footer().equals("hemat")) {
//            textViewHargaHemat.setVisibility(View.VISIBLE);
//            textViewHargaHemat.setText(Html.fromHtml(response_footer.get(1).getDesc_name_footer()));
//        } else {
//            textViewHargaHemat.setVisibility(View.GONE);
//        }
//        textViewHargaPaket.setText(harga);
//        switch (paketAlias) {
//            case "BASIC":
//
//                linBasic.setVisibility(View.VISIBLE);
//                linPro.setVisibility(View.GONE);
//                linEnterprise.setVisibility(View.GONE);
//
//                frame_ribbon_termurah.setVisibility(View.VISIBLE);
//                frame_ribbon_terlaris.setVisibility(View.GONE);
//                relBottomPaket.setBackgroundColor(ContextCompat.getColor(context, R.color.header_basic));
//
//                Glide.with(context).asBitmap().load(paketUrl).encodeFormat(Bitmap.CompressFormat.WEBP).encodeQuality(50).diskCacheStrategy(DiskCacheStrategy.NONE).into(new BitmapImageViewTarget(imageViewBasic) {
//                    @Override
//                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                        super.onLoadFailed(errorDrawable);
//                        imageViewBasic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.paket_basic_sbf));
//                    }
//                });
//                break;
//            case "ENTERPRISE":
//                linBasic.setVisibility(View.GONE);
//                linPro.setVisibility(View.GONE);
//                linEnterprise.setVisibility(View.VISIBLE);
//
//                frame_ribbon_termurah.setVisibility(View.GONE);
//                frame_ribbon_terlaris.setVisibility(View.VISIBLE);
//                relBottomPaket.setBackgroundColor(ContextCompat.getColor(context, R.color.header_enterprise));
//                Glide.with(context).asBitmap().load(paketUrl).encodeFormat(Bitmap.CompressFormat.WEBP).encodeQuality(50).diskCacheStrategy(DiskCacheStrategy.NONE).into(new BitmapImageViewTarget(imageViewEnterprise) {
//                    @Override
//                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                        super.onLoadFailed(errorDrawable);
//                        imageViewEnterprise.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.paket_enterprise_sbf));
//                    }
//                });
//                break;
//            case "PROFESSIONAL":
//                linBasic.setVisibility(View.GONE);
//                linPro.setVisibility(View.VISIBLE);
//                linEnterprise.setVisibility(View.GONE);
//                relBottomPaket.setBackgroundColor(ContextCompat.getColor(context, R.color.header_pro));
//                Glide.with(context).asBitmap().load(paketUrl).encodeFormat(Bitmap.CompressFormat.WEBP).encodeQuality(50).diskCacheStrategy(DiskCacheStrategy.NONE).into(new BitmapImageViewTarget(imageViewPro) {
//                    @Override
//                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                        super.onLoadFailed(errorDrawable);
//                        imageViewPro.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.paket_pro_sbf));
//                    }
//                });
//                break;
//        }
//
//        appButtonTutupPaket.setOnClickListener(view -> mPromoDialog.dismiss());
//
//        mPromoDialog = new Dialog(context,
//                R.style.MaterialDialogSheet);
//        mPromoDialog.setContentView(v);
//        mPromoDialog.setCancelable(false);
//        mPromoDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        mPromoDialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
//        mPromoDialog.show();
//    }
//
//    public static void openBottomSheetDialog(@NonNull Context context, @NonNull View v) {
//        mBottomSheetDialog = new Dialog(context, R.style.MaterialDialogSheet);
//        mBottomSheetDialog.setContentView(v);
//        mBottomSheetDialog.setCancelable(true);
//        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
//        mBottomSheetDialog.show();
//    }
//
//    public static void closeBootomSheetDialog() {
//        if (mBottomSheetDialog != null && mBottomSheetDialog.isShowing()) {
//            mBottomSheetDialog.dismiss();
//        }
//    }
//
//    public static void openSendVia(Context context, @NonNull View v, final Uri parse, @NonNull final String finalproduk, final String finalnamaProduk, final String finalkode, final String finalnama, @NonNull final File file) {
//        final int id = v.getId();
//        final View view = View.inflate(context, R.layout.travel_layout_action_share, null);
//        DialogUtils.openBottomSheetDialog(context, view);
//        String pesanBody = "";
//        switch (finalproduk) {
//            case "Pesawat":
//                pesanBody = "Silakan download eTicket " + finalproduk + " " + finalnamaProduk + " Anda dalam format PDF. Mohon diperhatikan Anda akan diminta untuk membawa hasil cetak eTicket Anda untuk masuk ke bandara dan untuk check-in bersama dengan membawa kartu identitas yang masih berlaku dan sesuai dengan data Anda.<br />Kami ucapkan terima kasih telah menggunakan layanan travel di Fastpay.<br /><br />";
//                break;
//            case "Kereta":
//                pesanBody = "Silakan download eTicket Anda dalam format PDF. Diharapkan Anda tiba 1 (satu) jam sebelum keberangkatan dengan membawa eTicket ini untuk mencetak Tiket Penumpang Kereta Api di mesin cetak yang ada di stasiun. Anda WAJIB membawa kartu identitas yang masih berlaku dan sesuai dengan data Anda untuk melakukan check in.<br />Kami ucapkan terima kasih telah menggunakan layanan travel di Fastpay.<br /><br />";
//                break;
//            case "Pelni":
//                pesanBody = "Silakan download eTicket Anda dalam format PDF. Bawalah eTicket ini untuk mencetak Tiket Penumpang PELNI di kantor perwakilan Pelni terdekat. Anda juga WAJIB membawa kartu identitas yang masih berlaku dan sesuai dengan data Anda untuk melakukan check in.<br />Kami ucapkan terima kasih telah menggunakan layanan travel di Fastpay.<br /><br />";
//                break;
//            case "Paket Wisata":
//                pesanBody = "Silakan download eTicket Anda dalam format PDF. Bawalah eTicket ini untuk ditunjukkan pada tim wisata kami saat Anda tiba di lokasi. Anda juga WAJIB membawa kartu identitas yang masih berlaku dan sesuai dengan data Anda untuk verifikasi.<br />Kami ucapkan terima kasih telah menggunakan layanan travel di Fastpay.<br /><br />";
//                break;
//            case "Hotel":
//                pesanBody = "Silakan download eTicket Anda dalam format PDF. Bawalah eTicket ini saat melakukan reservasi hotel. Anda juga WAJIB membawa kartu identitas yang masih berlaku dan sesuai dengan data Anda untuk melakukan check in.<br />Kami ucapkan terima kasih telah menggunakan layanan travel di Fastpay.<br /><br />";
//                break;
//        }
//
//        final String finalPesanBody = pesanBody;
//        ImageView imageViewWa = view.findViewById(R.id.imageViewWa);
//
//        imageViewWa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String apps = "com.whatsapp";
//
//                boolean installed = Device.checkAppInstall(context, apps);
//                if (!installed) {
//                    apps = "com.whatsapp.w4b";
//                }
//                installed = Device.checkAppInstall(context, apps);
//                if (installed) {
//                    Device.openWhatsApp(context, apps, parse, file);
//                } else {
//                    Toast.makeText(context,
//                            "Aplikasi Whatsapp tidak ditemukan, Silahkan Install aplikasi Whatsapp terlebih dahulu", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//        ImageView imageViewGmail = view.findViewById(R.id.imageViewGmail);
//
//        imageViewGmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String apps = "com.google.android.gm";
//
//                boolean installed = Device.checkAppInstall(context, apps);
//                if (installed) {
//                    String subject = "E-ticket " + finalproduk + " " + finalnamaProduk + " (" + finalkode + ") - " + finalnama;
//                    String pesanHeader = "Dengan hormat " + finalnama + ",<br /><br />";
//
//                    String pesanFooter = "Hormat kami";
//                    Spanned pesan = FormatString.fromHtml("<html>" + pesanHeader + finalPesanBody + pesanFooter + "</html>");
//                    Device.sendGmail(context, apps, subject, pesan, parse);
//                } else {
//                    Toast.makeText(context,
//                            "Aplikasi Gmail tidak ditemukan, Silahkan Install aplikasi Gmail terlebih dahulu", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        ImageView imageViewYmail = view.findViewById(R.id.imageViewYmail);
//        imageViewYmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String apps = "com.yahoo.mobile.client.android.mail";
//                boolean installed = Device.checkAppInstall(context, apps);
//                if (installed) {
//                    String subject = "E-ticket " + finalproduk + " " + finalnamaProduk + " (" + finalkode + ") - " + finalnama;
//                    String pesanHeader = "Dengan hormat " + finalnama + ",<br /><br />";
//
//                    String pesanFooter = "Hormat kami";
//                    Spanned pesan = FormatString.fromHtml("<html>" + pesanHeader + finalPesanBody + pesanFooter + "</html>");
//                    Device.sendYmail(context, apps, subject, pesan, parse);
//                } else {
//                    Toast.makeText(context,
//                            "Aplikasi Yahoo Mail tidak ditemukan, Silahkan Install aplikasi Yahoo Mail terlebih dahulu", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        ImageView imageViewEmail = view.findViewById(R.id.imageViewEmail);
//        imageViewEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String apps = "com.android.email";
//                boolean installed = Device.checkAppInstall(context, apps);
//                if (!installed) {
//                    apps = "com.lge.email";
//                }
//
//                if (installed) {
//                    String subject = "E-ticket " + finalproduk + " " + finalnamaProduk + " (" + finalkode + ") - " + finalnama;
//                    String pesanHeader = "Dengan hormat " + finalnama + ",<br /><br />";
//
//                    String pesanFooter = "Hormat kami";
//                    Spanned pesan = FormatString.fromHtml("<html>" + pesanHeader + finalPesanBody + pesanFooter + "</html>");
//                    Device.sendEmail(context, apps, subject, pesan, parse);
//                } else {
//                    Toast.makeText(context,
//                            "Aplikasi EMail tidak ditemukan, Silahkan Install aplikasi EMail terlebih dahulu", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }
//
//    public static void new_popup_bantuan(@NonNull final Context context) {
//        if (context instanceof BaseActivity) {
//            BaseActivity activity = (BaseActivity) context;
//            activity.logEventFireBase("Bantuan", "Kategori Bantuan", EventParam.EVENT_ACTION_VISIT, EventParam.EVENT_SUCCESS, activity.getClass().getSimpleName());
//
//            ViewGroup parent = activity.findViewById(R.id.contentHost);
//            final RelativeLayout v = (RelativeLayout) View.inflate(context, R.layout.dialog_header_cs_layout, parent);
//
//
//            CFAlertDialog.Builder builder = new CFAlertDialog.Builder(context);
//            builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
//                    .setBackgroundColor(ContextCompat.getColor(context, R.color.black_overlay_dark))
//                    .setCornerRadius(10)
//                    .setMessage("Hai juragan, Silahkan pilih layanan live chat yang Anda butuhkan.")
//                    .setTextGravity(Gravity.CENTER)
//                    .setHeaderView(v)
//                    .setCancelable(true);
//
//            builder.addButton("Layanan", -1, ContextCompat.getColor(context, R.color.colorPrimary), CFAlertDialog.CFAlertActionStyle.POSITIVE, activity.getButtonGravity(3), new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(@NonNull DialogInterface dialog, int which) {
//                    activity.liveChat();
//                    dialog.cancel();
//                }
//            });
//
//            builder.addButton("Deposit", -1, ContextCompat.getColor(context, R.color.colorPrimary), CFAlertDialog.CFAlertActionStyle.POSITIVE, activity.getButtonGravity(3), new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(@NonNull DialogInterface dialog, int which) {
//                    new_popup_subbantuan(activity, "Pilih Sub Category Bantuan");
//                    dialog.cancel();
//                }
//            });
//
//            if (!activity.isFinishing()) {
//                builder.show();
//            }
//        }
//    }
//
//    private static void new_popup_subbantuan(@NonNull final BaseActivity context, String title) {
//        context.logEventFireBase("Bantuan", "Sub Kategori Bantuan", EventParam.EVENT_ACTION_VISIT, EventParam.EVENT_SUCCESS, context.getClass().getSimpleName());
//
//        ViewGroup parent = context.findViewById(R.id.contentHost);
//        final RelativeLayout v = (RelativeLayout) View.inflate(context, R.layout.dialog_header_cs_layout, parent);
//
//
//        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(context);
//        builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
//                .setBackgroundColor(ContextCompat.getColor(context, R.color.black_overlay_dark))
//                .setCornerRadius(10)
//                .setMessage("Silahkan pilih kategori deposit Anda.")
//                .setTextGravity(Gravity.CENTER)
//                .setHeaderView(v)
//                .setCancelable(true);
//
//        builder.addButton("Lacak Deposit", -1, ContextCompat.getColor(context, R.color.colorPrimary), CFAlertDialog.CFAlertActionStyle.POSITIVE, context.getButtonGravity(3), (dialog, which) -> {
//            Intent intent = new Intent(context, CekDepositActivity.class);
//            context.startActivity(intent);
//            dialog.cancel();
//        });
//
//        builder.addButton("Cara Deposit", -1, ContextCompat.getColor(context, R.color.colorPrimary), CFAlertDialog.CFAlertActionStyle.POSITIVE, context.getButtonGravity(3), (dialog, which) -> {
//            Intent intent = new Intent(context, CaraDepositActivity.class);
//            context.startActivity(intent);
//            dialog.cancel();
//        });
//
//        if (!context.isFinishing()) {
//            builder.show();
//        }
//    }
//}
