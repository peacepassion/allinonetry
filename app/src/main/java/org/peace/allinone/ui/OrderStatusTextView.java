package org.peace.allinone.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import me.ele.base.utils.CollectionsUtils;

import static android.animation.ValueAnimator.RESTART;
import static android.view.Gravity.CENTER_VERTICAL;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class OrderStatusTextView extends FrameLayout {

  private TextView frontView;
  private TextView bgView;

  private List<String> texts = new ArrayList<>();
  private ValueAnimator valueAnimator;
  private final int ANIM_DURATION = 500;
  private final int ANIM_INT = 1000;
  private int bgTextIndex = 1;

  public OrderStatusTextView(Context context) {
    this(context, null);
  }

  public OrderStatusTextView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public OrderStatusTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    frontView = createTextView();
    bgView = createTextView();
    valueAnimator = ValueAnimator.ofFloat(0, 1);
    valueAnimator.setDuration(ANIM_DURATION);
    valueAnimator.setStartDelay(ANIM_INT);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        int parentH = ((View) getParent()).getMeasuredHeight();
        int dy = (int) (parentH * ((float) animation.getAnimatedValue()));
        frontView.setY(-dy);
        bgView.setY(parentH - dy);
      }
    });
    valueAnimator.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationEnd(Animator animation) {
        if (frontView == getChildAt(0)) {
          frontView = (TextView) getChildAt(1);
          bgView = (TextView) getChildAt(0);
        } else {
          frontView = (TextView) getChildAt(0);
          bgView = (TextView) getChildAt(1);
        }
        bgTextIndex = (bgTextIndex + 1) % texts.size();
        bgView.setText(texts.get(bgTextIndex));
        valueAnimator.start();
      }

      @Override public void onAnimationCancel(Animator animation) {
        bgTextIndex = 1;
      }
    });
  }

  private TextView createTextView() {
    TextView tv = new TextView(getContext());
    tv.setMaxLines(2);
    tv.setTextColor(Color.WHITE);
    tv.setTextSize(10);
    tv.setGravity(CENTER_VERTICAL);
    LayoutParams lp = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
    lp.gravity = CENTER_VERTICAL;
    tv.setLayoutParams(lp);
    return tv;
  }

  public void setTexts(List<String> texts) {
    if (CollectionsUtils.isEmpty(texts)) {
      valueAnimator.cancel();
      removeAllViews();
      return;
    }
    this.texts = texts;
    if (texts.size() == 1) {
      showOne();
    } else {
      showMany();
    }
  }

  private void showOne() {
    valueAnimator.cancel();
    removeAllViews();
    frontView.setText(texts.get(0));
    addView(frontView);
  }

  private void showMany() {
    removeAllViews();
    bgView.setText(texts.get(bgTextIndex));
    bgView.setY(getMeasuredHeight());
    addView(bgView);
    frontView.setText(texts.get(0));
    addView(frontView);
    valueAnimator.start();
  }
}
