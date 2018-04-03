package com.soul.substantial.util.scan;

import android.content.Context;
import android.content.SharedPreferences;

import com.soul.substantial.config.Constant;


/**
 * @author Jayce
 * @date 2015/6/10
 */
public class Environment extends Constant {

    public static final String ENV = "env";

    /**
     * 获取是否过滤文件大小
     *
     * @param context
     * @return
     */
    public static boolean isFilterSize(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ENV, Context.MODE_PRIVATE);
        return preferences.getBoolean(ENV_IS_FILTER_SIZE, true);
    }

    /**
     * 设置是否过滤文件大小
     *
     * @param context
     * @param flag
     */
    public static void setIsFilterSize(Context context, boolean flag) {
        SharedPreferences preferences = context.getSharedPreferences(ENV, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(ENV_IS_FILTER_SIZE, flag);
        editor.apply();
    }

    /**
     * 获取是否过滤文件时长
     *
     * @param context
     * @return
     */
    public static boolean isFilterDuration(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ENV, Context.MODE_PRIVATE);
        return preferences.getBoolean(ENV_IS_FILTER_DURATION, true);
    }

    /**
     * 设置是否过滤文件时长
     *
     * @param context
     * @param flag
     */
    public static void setIsFilterDuration(Context context, boolean flag) {
        SharedPreferences preferences = context.getSharedPreferences(ENV, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(ENV_IS_FILTER_DURATION, flag);
        editor.apply();
    }

    /**
     * 获取已经启动过的次数
     *
     * @param context
     * @return
     */
    public static int getHasRunCount(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ENV, Context.MODE_PRIVATE);
        return preferences.getInt(ENV_HAS_RUN_COUNT, 0);
    }

    public static void setHasRunCount(Context context, int  hasRunCount) {
        SharedPreferences preferences = context.getSharedPreferences(ENV, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ENV_HAS_RUN_COUNT, hasRunCount);
        editor.apply();
    }

}
