package org.peace.allinone.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class RVBehavior extends CoordinatorLayout.Behavior<RecyclerView> {

  static final String TAG = RVBehavior.class.getSimpleName();

  private View dependencyView;

  public RVBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
    return dependency.getTag().equals("toolbar");
  }

  @Override public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child,
      View dependency) {
    ToolbarBehavior behavior =
        (ToolbarBehavior) ((CoordinatorLayout.LayoutParams) dependency.getLayoutParams()).getBehavior();
    int minH = behavior.getMinH();
    int bottomMargin = ((ViewGroup.MarginLayoutParams) dependency.getLayoutParams()).bottomMargin;
    int y = (int) (dependency.getY() + dependency.getMeasuredHeight() + bottomMargin);
    y = Math.max(minH, y);
    Log.e(TAG, "onDependentViewChanged, Y: " + y);
    child.setY(y);
    return true;
  }

  @Override public boolean onMeasureChild(CoordinatorLayout parent, RecyclerView child,
      int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
    Log.e(TAG, "onMeasure");
    return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed,
        parentHeightMeasureSpec, heightUsed);
  }

  @Override
  public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child, int layoutDirection) {
    Log.e(TAG, "onLayoutChild, dep b: " + getDependency(parent, child).getBottom());
    //child.layout(0, getDependency(parent, child).getBottom(), parent.getMeasuredWidth(),
    //    getDependency(parent, child).getBottom() + child.getMeasuredHeight());
    //parent.onLayoutChild(child, layoutDirection);
    View dependency = getDependency(parent, child);
    int y = (int) (dependency.getY() + dependency.getLayoutParams().height);
    Log.e(TAG, "y: " + y);
    //child.setY(y);
    //return true;
    return false;
  }

  private View getDependency(CoordinatorLayout parent, View child) {
    if (dependencyView == null) {
      dependencyView = parent.getDependencies(child).get(0);
    }
    return dependencyView;
  }

  @Override public boolean onInterceptTouchEvent(CoordinatorLayout parent, RecyclerView child,
      MotionEvent ev) {
    //Log.e(TAG, "onInterceptTouchEvent");
    return super.onInterceptTouchEvent(parent, child, ev);
  }

  @Override
  public boolean onTouchEvent(CoordinatorLayout parent, RecyclerView child, MotionEvent ev) {
    //Log.e(TAG, "onTouchEvent");
    return super.onTouchEvent(parent, child, ev);
  }
}
