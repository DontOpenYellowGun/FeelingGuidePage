package com.sven.feelingguidepage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Sven on 2016/12/8.
 */

public class DensityUtil {
    private static float mPixels = 0.0F;
    private static float density = -1.0F;

    public DensityUtil() {
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getDisplayMetrics(Context context, float pixels) {
        if(mPixels == 0.0F) {
            mPixels = context.getResources().getDisplayMetrics().density;
        }

        return (int)(0.5F + pixels * mPixels);
    }

    public static int getImageWeidth(Context context, float pixels) {
        return context.getResources().getDisplayMetrics().widthPixels - 66 - getDisplayMetrics(context, pixels);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static int dip2px(float dipValue) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

    public static int getMetricsDensity(Context context, float height) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
        return Math.round(height * (float)localDisplayMetrics.densityDpi / 160.0F);
    }

    public static int fromDPToPix(Context context, int dp) {
        return Math.round(getDensity(context) * (float)dp);
    }

    public static float getDensity(Context context) {
        if(density < 0.0F) {
            density = context.getResources().getDisplayMetrics().density;
        }

        return density;
    }

    public static int round(Context context, int paramInt) {
        return Math.round((float)paramInt / getDensity(context));
    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / fontScale + 0.5F);
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * fontScale + 0.5F);
    }
}
