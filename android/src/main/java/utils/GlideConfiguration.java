package utils;//package utils;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.GlideBuilder;
//import com.bumptech.glide.Registry;
//import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
//import com.bumptech.glide.load.DecodeFormat;
//import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
//import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
//import com.bumptech.glide.load.engine.cache.LruResourceCache;
//import com.bumptech.glide.load.model.GlideUrl;
//import com.bumptech.glide.module.AppGlideModule;
//import com.bumptech.glide.request.RequestOptions;
//
//import java.io.InputStream;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.ConnectionPool;
//import okhttp3.OkHttpClient;
//
////import android.support.annotation.NonNull;
//
//
///**
// * Created by sarifhidayat on 8/14/18.
// **/
//public class GlideConfiguration extends AppGlideModule {
//    @Override
//    public void applyOptions(Context context, @NonNull GlideBuilder builder) {
//       // client.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
////        client.setDecodeFormat(DecodeFormat.ALWAYS_ARGB_8888);
//        builder.setDefaultRequestOptions(
//                new RequestOptions()
//                        .format(DecodeFormat.PREFER_RGB_565)
//                        .disallowHardwareConfig());
//
//        int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
//        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, memoryCacheSizeBytes));
//        builder.setBitmapPool(new LruBitmapPool(memoryCacheSizeBytes));
////        client.setDefaultRequestOptions(requestOptions(context));
////        client.build(context);
////        int diskCacheSizeBytes = 1024 * 1024 * 100; // 100 MB
////        client.setDiskCache(
////                new InternalCacheDiskCacheFactory(context, "cacheFolderName", diskCacheSizeBytes));
////        client.setBitmapPool(new LruBitmapPool(diskCacheSizeBytes));
////        client.setMemoryCache(new LruResourceCache(diskCacheSizeBytes));
//    }
//
////    @Override
////    public void registerComponents(Context context, @NonNull Glide glide) {
////        OkHttpClient.Builder client = new OkHttpClient.Builder();
////        client.connectTimeout(5, TimeUnit.MINUTES);
////        client.readTimeout(5,TimeUnit.MINUTES);
////        client.writeTimeout(5,TimeUnit.MINUTES);
////
////        client.retryOnConnectionFailure(true);
////        client.connectionPool(new ConnectionPool(100, 20, TimeUnit.SECONDS));
////        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(client.build());
////        glide.register(GlideUrl.class, InputStream.class, factory);
////    }
//
//
////    @Override
//    public void registerComponents(Context context, Glide glide, Registry registry) {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectionPool(new ConnectionPool(100, 5, TimeUnit.SECONDS));
//        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(builder.build());
////        glide.getRegistry();
//        registry.replace(GlideUrl.class, InputStream.class, factory);
//    }
//}
