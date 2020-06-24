package com.zzq.common.util;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.zzq.common.base.BaseApplication;

public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        return dp2px(context, dpValue);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(float dpValue) {
        return dp2px(BaseApplication.instance, dpValue);
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    public static void getAndroidScreenProperty(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        // 屏幕宽度（像素）
        int width = dm.widthPixels;
        // 屏幕高度（像素）
        int height = dm.heightPixels;
        // 屏幕密度（0.75 / 1.0 / 1.5）
        float density = dm.density;
        // 屏幕密度dpi（120 / 160 / 240）
        int densityDpi = dm.densityDpi;
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        // 屏幕宽度(dp)
        int screenWidth = (int) (width / density);
        // 屏幕高度(dp)
        int screenHeight = (int) (height / density);
    }

    /**
     * 获取屏幕宽度，单位px
     *
     * @param context 上下文
     * @return 屏幕宽度，单位px
     */
    public static float getRealWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        // 屏幕宽度（像素）
        int width = dm.widthPixels;
        // 屏幕高度（像素）
        int height = dm.heightPixels;
        // 屏幕密度（0.75 / 1.0 / 1.5）
        float density = dm.density;
        // 屏幕密度dpi（120 / 160 / 240）
        int densityDpi = dm.densityDpi;
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        // 屏幕宽度(dp)

        int screenWidth = (int) (width / density);
        // 屏幕高度(dp)
        int screenHeight = (int) (height / density);

        return width;
    }

    /**
     * 获取屏幕高度，单位px
     *
     * @param context 上下文
     * @return 屏幕高度，单位px
     */
    public static float getRealHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        // 屏幕宽度（像素）
        int width = dm.widthPixels;
        // 屏幕高度（像素）
        int height = dm.heightPixels;
        // 屏幕密度（0.75 / 1.0 / 1.5）
        float density = dm.density;
        // 屏幕密度dpi（120 / 160 / 240）
        int densityDpi = dm.densityDpi;
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        // 屏幕宽度(dp)

        return height;
    }

    /**
     * point.x和point.y拿到的好像是屏幕分辨率的宽高
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getRealSize(point);
        /**
         * 三星S10打印：
         * 屏幕宽度：pixel：1440
         * 屏幕宽度：pixel：3040
         */
        return point.x;
    }

    public static float getDensity() {
        return BaseApplication.instance.getResources().getDisplayMetrics().density;
    }
}
