package org.peace.allinone.ui;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashSet;
import java.util.Set;

import static me.ele.base.utils.DimenUtil.dip2px;

public class ToolbarBehavior extends CoordinatorLayout.Behavior<View> {

  static final String TAG = ToolbarBehavior.class.getSimpleName();

  private NestedScrollingChildHelper scrollingChildHelper;
  private ScrollerCompat mScroller;
  private FlingRunnable mFlingRunnable;
  private int maxH;

  private Set<HeightChangeListener> listeners = new HashSet<>();

  public ToolbarBehavior() {
  }

  @Override
  public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec,
      int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
    if (scrollingChildHelper == null) {
      scrollingChildHelper = new NestedScrollingChildHelper(parent);
      scrollingChildHelper.setNestedScrollingEnabled(true);
      parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec,
          heightUsed);
      maxH = child.getMeasuredHeight();
    }
    return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed,
        parentHeightMeasureSpec, heightUsed);
  }

  @Override public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
      View directTargetChild, View target, int nestedScrollAxes) {
    if (scrollingChildHelper.startNestedScroll(nestedScrollAxes)) {
      return true;
    }
    return target instanceof RecyclerView && nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dx, int dy, int[] consumed) {
    Log.e(TAG, "onNestedPreScroll, dy: " + dy);

    int parentDy = dy;
    if (dy > 0) {
      parentDy = Math.min(coordinatorLayout.getTop(), dy);
    }
    Log.e(TAG, "parentDy: " + parentDy);
    scrollingChildHelper.dispatchNestedPreScroll(dx, parentDy, consumed, new int[2]);

    Log.e(TAG, "consumed[1]: " + consumed[1]);
    if (consumed[1] == dy) {
      return;
    }

    dy -= consumed[1];

    // up scroll
    if (dy > 0) {
      ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
      int h = child.getMeasuredHeight();
      setHeight(child, h - dy);

      Log.e(TAG, "onNestedPreScroll, consume dy: " + (h - lp.height));
      consumed[1] = h - lp.height;
    }
  }

  @Override public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    Log.e(TAG, "onNestedScroll, dyUnconsumed: " + dyUnconsumed);

    // down scroll
    if (dyUnconsumed < 0) {
      ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
      int h = child.getMeasuredHeight();
      setHeight(child, lp.height - dyUnconsumed);
      dyUnconsumed += lp.height - h;
      Log.e(TAG, "onNestedScroll to parent, dyUn: " + dyUnconsumed);
      scrollingChildHelper.dispatchNestedScroll(0, 0, 0, dyUnconsumed, new int[2]);
    }
  }

  @Override
  public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target,
      float velocityX, float velocityY) {
    Log.e(TAG, "onNestedPreFling, vy: " + velocityY + ", cor top: " + coordinatorLayout.getTop());
    if (mScroller == null) {
      mScroller = ScrollerCompat.create(child.getContext());
    }
    if (coordinatorLayout.getTop() > 0 && scrollingChildHelper.dispatchNestedPreFling(velocityX,
        velocityY)) {
      return true;
    }
    if (velocityY > 0 && child.getLayoutParams().height > dip2px(40)) {
      mScroller.fling(0, (int) target.getY(), 0, (int) (-velocityY), 0, 0,
          dip2px(40), maxH);
      if (mScroller.computeScrollOffset()) {
        mFlingRunnable = new FlingRunnable(child, target);
        ViewCompat.postOnAnimation(child, mFlingRunnable);
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target,
      float velocityX, float velocityY, boolean consumed) {
    Log.e(TAG, "onNestedFling, vy: " + velocityY);
    if (mScroller == null) {
      mScroller = ScrollerCompat.create(child.getContext());
    }

    // down fling
    RecyclerView rv = (RecyclerView) target;
    LinearLayoutManager llm = (LinearLayoutManager) rv.getLayoutManager();
    if (velocityY < 0
        && llm.findFirstVisibleItemPosition() == 0
        && child.getLayoutParams().height < maxH) {
      mScroller.fling(0, (int) target.getY(), 0, (int) (-velocityY), 0, 0,
          dip2px(40), maxH);
      if (mScroller.computeScrollOffset()) {
        mFlingRunnable = new FlingRunnable(child, target);
        ViewCompat.postOnAnimation(child, mFlingRunnable);
        return true;
      }
    }

    return false;
  }

  @Override
  public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
    scrollingChildHelper.stopNestedScroll();
  }

  private void setHeight(View child, int height) {
    ViewGroup.LayoutParams lp = child.getLayoutParams();
    int h = lp.height;
    lp.height = Math.min(maxH, Math.max(dip2px(40), height));
    child.setLayoutParams(lp);
    if (h != lp.height) {
      notifyHeightChange(lp.height);
    }
  }

  private void notifyHeightChange(int height) {
    for (HeightChangeListener listener : listeners) {
      listener.onHeightChange(height);
    }
  }

  public void addHeightChangeListener(HeightChangeListener listener) {
    listeners.add(listener);
  }

  public void removeHeightChangeListener(HeightChangeListener listener) {
    listeners.remove(listener);
  }

  private class FlingRunnable implements Runnable {

    View child;
    View target;

    public FlingRunnable(View child, View target) {
      this.child = child;
      this.target = target;
    }

    @Override public void run() {
      if (mScroller.computeScrollOffset()) {
        int value = mScroller.getCurrY();
        setHeight(child, value);
        ViewCompat.postOnAnimation(child, this);
      }
    }
  }

  public interface HeightChangeListener {
    void onHeightChange(int height);
  }
}
