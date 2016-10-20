package me.ele.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Field;

public class DimenUtil {

  private static final float PADDING = 0.5f;
  private static Context CONTEXT;

  public static void init(Context context) {
    CONTEXT = context;
  }

  private DimenUtil() {
  }

  /**
   * dip转px
   */
  public static int dip2px(float dpValue) {
    float scale = CONTEXT.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + PADDING);
  }

  public static int sp2px(float sp) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
        CONTEXT.getResources().getDisplayMetrics());
  }

  /**
   * px转dip
   */
  public static int px2dip(float pxValue) {
    final float scale = CONTEXT.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + PADDING);
  }

  public static int getScreenWidth() {
    return CONTEXT.getResources().getDisplayMetrics().widthPixels;
  }

  public static int getScreenWidthMinusOf(int dpValue) {
    return getScreenWidth() - dip2px(dpValue);
  }

  public static int getScreenHeight() {
    return CONTEXT.getResources().getDisplayMetrics().heightPixels;
  }

  public static int getScreenHeightMinusOf(int dpValue) {
    return getScreenHeight() - dip2px(dpValue);
  }

  public static int getStatusBarHeight() {
    Resources resources = CONTEXT.getResources();
    int resId = resources.getIdentifier("status_bar_height", "dimen", "android");
    return resId > 0 ? resources.getDimensionPixelSize(resId) : 0;
  }

  public static int getNavigationBarHeight() {
    Resources resources = CONTEXT.getResources();
    int resId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
    return resId > 0 ? resources.getDimensionPixelSize(resId) : 0;
  }

  public static int getContentHeight() {
    Context context = CONTEXT;
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = windowManager.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size.y - getStatusBarHeight();
  }

  // must use context with theme
  public static int getToolBarHeight(Activity activity) {
    TypedArray styledAttributes = null;
    try {
      styledAttributes = activity.getTheme().obtainStyledAttributes(new int[] { getActionbarSize() });
      return (int) styledAttributes.getDimension(0, DimenUtil.dip2px(56));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (styledAttributes != null) {
        styledAttributes.recycle();
      }
    }
    return DimenUtil.dip2px(56);
  }

  private static int getActionbarSize() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
    Class appcompatAttrClazz = Class.forName("android.support.v7.appcompat.R$attr");
    Field actionBarSizeField = appcompatAttrClazz.getField("actionBarSize");
    return (int) actionBarSizeField.get(null);
  }
}
