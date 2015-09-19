package org.peace.allinone.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import me.ele.commons.DimenUtil;

/**
 * Created by peacepassion on 15/9/19.
 */
public class RVItemDecorator extends RecyclerView.ItemDecoration {

  @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDraw(c, parent, state);
  }

  @Override public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDrawOver(c, parent, state);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(parent.getResources().getColor(android.R.color.holo_red_dark));
    //paint.setTextSize(40);
    //c.drawText("onDrawOver", 20, 20, paint);
    for (int i = 0, size = parent.getChildCount(); i < size; ++i) {
      View item = parent.getChildAt(i);
      int l = item.getLeft(),
          r = item.getRight(),
          t = item.getMeasuredHeight() - DimenUtil.dip2px(item.getContext(), 8),
          b = item.getMeasuredHeight();
    }
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    int type = parent.getAdapter().getItemViewType(parent.getChildAdapterPosition(view));
    if (type == RVAdapter.FIRST) {
      outRect.set(20, 100, 40, 200);
    }
  }
}
