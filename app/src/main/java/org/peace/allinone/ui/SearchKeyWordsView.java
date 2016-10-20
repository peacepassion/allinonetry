package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import me.ele.base.utils.CollectionsUtils;
import me.ele.base.utils.DimenUtil;

import static android.graphics.Color.WHITE;
import static android.view.View.MeasureSpec.AT_MOST;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class SearchKeyWordsView extends LinearLayout {

  private List<String> keyWords = new ArrayList<>();

  public SearchKeyWordsView(Context context) {
    this(context, null);
  }

  public SearchKeyWordsView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SearchKeyWordsView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setOrientation(HORIZONTAL);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
    int maxHeight = MeasureSpec.getSize(heightMeasureSpec);

    List<View> deletedViews = new ArrayList<>();
    int count = getChildCount();
    int rightPos = 0;
    for (int i = 0; i < count; ++i) {
      View child = getChildAt(i);
      if (rightPos > maxWidth) {
        deletedViews.add(child);
      } else {
        child.measure(makeMeasureSpec(maxWidth, AT_MOST), makeMeasureSpec(maxHeight, AT_MOST));
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        rightPos += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
        if (rightPos > maxWidth + lp.rightMargin) {
          deletedViews.add(child);
        }
      }
    }
    for (View view : deletedViews) {
      removeView(view);
    }
    if (getChildCount() > 0) {
      ((LayoutParams) getChildAt(getChildCount() - 1).getLayoutParams()).rightMargin = 0;
    }
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  public void setKeyWords(List<String> keyWords) {
    this.keyWords = keyWords;
    if (CollectionsUtils.isEmpty(keyWords)) {
      setVisibility(GONE);
      return;
    }

    removeAllViews();
    for (String keyWord : keyWords) {
      TextView cellView = new TextView(getContext());
      cellView.setTextColor(WHITE);
      cellView.setTextSize(12);
      cellView.setSingleLine();
      cellView.setText(keyWord);
      LayoutParams lp = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
      lp.rightMargin = DimenUtil.dip2px(16);
      addView(cellView, lp);
    }
  }
}

