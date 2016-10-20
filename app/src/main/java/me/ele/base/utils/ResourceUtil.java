package me.ele.base.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;

public class ResourceUtil {

  private static Context CONTEXT;
  private static Resources RES;

  public static void init(Context context) {
    CONTEXT = context;
    RES = context.getResources();
  }

  private ResourceUtil() {
  }

  public static Resources getRes() {
    return RES;
  }

  @ColorInt public static int getColor(@ColorRes int colorId) {
    return getColor(colorId, null);
  }

  @ColorInt public static int getColor(@ColorRes int colorId, @Nullable Resources.Theme theme) {
    return ResourcesCompat.getColor(RES, colorId, theme);
  }

  public static String getString(@StringRes int id) {
    return RES.getString(id);
  }

  public static String getString(@StringRes int id, Object... formatArgs) {
    return RES.getString(id, formatArgs);
  }

  public static Drawable getDrawable(@DrawableRes int id) {
    return getDrawable(id, null);
  }

  public static Drawable getDrawable(@DrawableRes int id, @Nullable Resources.Theme theme) {
    return ResourcesCompat.getDrawable(RES, id, theme);
  }

  public static int[] getIntArray(@ArrayRes int id) {
    return RES.getIntArray(id);
  }

  public static ColorStateList getColorStateList(@ColorRes int id) {
    return getColorStateList(id, null);
  }

  public static ColorStateList getColorStateList(@ColorRes int id,
      @Nullable Resources.Theme theme) {
    return ResourcesCompat.getColorStateList(RES, id, theme);
  }

  public static int getDimension(@DimenRes int id) {
    return (int) RES.getDimension(id);
  }
}
