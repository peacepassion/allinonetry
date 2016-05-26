package org.peace.allinone.ui;

import android.graphics.Canvas;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import me.ele.commons.AppLogger;

//TODO support horizontal layout
public class PinnedItemDecoration extends RecyclerView.ItemDecoration {
  public interface PinnedHeaderAdapter {
    boolean isPinnedViewType(int viewType);
  }

  private RecyclerView.Adapter mAdapter = null;
  private View mPinnedHeaderView = null;
  private int mPinnedViewPosition = -1;
  private SparseBooleanArray mCachedPinnedViewTypes = new SparseBooleanArray();
  private SparseArray<View> mCachedPinnedHeaders = new SparseArray<>();
  private int mPinnedHeaderTop;

  private FrameLayout container;

  public PinnedItemDecoration(FrameLayout container) {
    this.container = container;
  }

  @Override
  public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    createPinnedHeader(parent);

    if (mPinnedHeaderView != null) {
      final int bottom = mPinnedHeaderView.getTop() + mPinnedHeaderView.getHeight();
      View nextView = parent.findChildViewUnder(c.getWidth() / 2, bottom + 1);
      mPinnedHeaderTop = 0;
      int pos = parent.indexOfChild(nextView);
      for (int i = pos; i >= 0; i--) {
        nextView = parent.getChildAt(i);
        if (!isPinnedView(parent, nextView)) {
          continue;
        }
        int top = nextView.getTop();
        if (top <= 0) {
          continue;
        }
        mPinnedHeaderTop -= (mPinnedHeaderView.getHeight() - top);
      }
    }
  }

  @Override
  public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
    if (mPinnedHeaderView != null) {
      container.setTranslationY(mPinnedHeaderTop);
      if (container.getChildCount() > 0) {
        return;
      }
      ViewGroup p = (ViewGroup) mPinnedHeaderView.getParent();
      if (p != null) {
        p.removeView(mPinnedHeaderView);
      }
      container.addView(mPinnedHeaderView);
    }
  }

  private void createPinnedHeader(RecyclerView parent) {
    mAdapter = parent.getAdapter();
    if (!(mAdapter instanceof PinnedHeaderAdapter)) {
      throw new RuntimeException("RecyclerView's Adapter must implement PinnedHeaderAdapter");
    }

    RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
    if (!(layoutManager instanceof LinearLayoutManager)) {
      return;
    }

    final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
    final int firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
    final int pinnedViewPosition = findPinneViewPosition(firstVisiblePosition);

    if (pinnedViewPosition < 0 || mPinnedViewPosition == pinnedViewPosition) {
      return;
    }

    mPinnedViewPosition = pinnedViewPosition;
    int viewType = mAdapter.getItemViewType(pinnedViewPosition);
    mPinnedHeaderView = mCachedPinnedHeaders.get(viewType);
    RecyclerView.ViewHolder pinnedViewHolder;
    if (mPinnedHeaderView == null) {
      pinnedViewHolder =
          mAdapter.createViewHolder(parent, viewType);
      mPinnedHeaderView = pinnedViewHolder.itemView;
      mPinnedHeaderView.setTag(pinnedViewHolder);
      mCachedPinnedHeaders.put(viewType, mPinnedHeaderView);
    } else {
      pinnedViewHolder = (RecyclerView.ViewHolder) mPinnedHeaderView.getTag();
    }

    mAdapter.bindViewHolder(pinnedViewHolder, pinnedViewPosition);
    // read layout parameters
    ViewGroup.LayoutParams layoutParams = mPinnedHeaderView.getLayoutParams();
    if (layoutParams == null) {
      layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.WRAP_CONTENT);
      mPinnedHeaderView.setLayoutParams(layoutParams);
    }

    int heightMode = View.MeasureSpec.getMode(layoutParams.height);
    int heightSize = View.MeasureSpec.getSize(layoutParams.height);

    if (heightMode == View.MeasureSpec.UNSPECIFIED) {
      heightMode = View.MeasureSpec.EXACTLY;
    }

    final int maxHeight =
        parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom();
    if (heightSize > maxHeight) {
      heightSize = maxHeight;
    }

    final int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(
        parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(),
        View.MeasureSpec.EXACTLY);
    final int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(heightSize, heightMode);
    mPinnedHeaderView.measure(widthMeasureSpec, heightMeasureSpec);
    //mPinnedHeaderView.layout(0, 0, mPinnedHeaderView.getMeasuredWidth(),
    //    mPinnedHeaderView.getMeasuredHeight());
  }

  private int findPinneViewPosition(int fromPosition) {
    if (fromPosition > mAdapter.getItemCount()) {
      return -1;
    }

    for (int position = fromPosition; position >= 0; position--) {
      final int viewType = mAdapter.getItemViewType(position);
      if (isPinnedViewType(viewType)) {
        return position;
      }
    }

    return -1;
  }

  private boolean isPinnedViewType(int viewType) {
    if (mCachedPinnedViewTypes.indexOfKey(viewType) > 0) {
      return mCachedPinnedViewTypes.get(viewType);
    }

    boolean isPinned = ((PinnedHeaderAdapter) mAdapter).isPinnedViewType(viewType);
    mCachedPinnedViewTypes.put(viewType, isPinned);
    return isPinned;
  }

  private boolean isPinnedView(RecyclerView parent, View v) {
    final int position = parent.getChildAdapterPosition(v);
    if (position == RecyclerView.NO_POSITION) {
      return false;
    }
    final int viewType = mAdapter.getItemViewType(position);

    return isPinnedViewType(viewType);
  }

  public void invalidate() {
    mPinnedHeaderView = null;
    mPinnedViewPosition = -1;
  }
}
