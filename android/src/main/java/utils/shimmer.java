package utils;//package utils;
//
//import androidx.annotation.NonNull;
//
//import com.bm.main.fpl.templates.shimmer.ShimmerFrameLayout;
//
///**
// * Created by Sarif Hidayat on 12/05/2017.
// */
//
//public class shimmer {
//
//
//    /**
//     * Select one of the shimmer animation presets.
//     *
//     * @param preset index of the shimmer animation preset
//     */
//    public static void selectPreset(int preset, @NonNull ShimmerFrameLayout mShimmerViewContainer) {
//        // Save the state of the animation
//        boolean isPlaying = mShimmerViewContainer.isAnimationStarted();
//        mShimmerViewContainer.setBaseAlpha(0.7f);
//        mShimmerViewContainer.setDropoff(0.5f);
//        if (isPlaying) {
//            mShimmerViewContainer.stopShimmerAnimation();
//        }
//        mShimmerViewContainer.startShimmerAnimation();
//    }
//}
