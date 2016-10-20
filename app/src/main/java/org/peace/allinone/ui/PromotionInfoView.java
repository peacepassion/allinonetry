package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.animation.Animation.ABSOLUTE;
import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class PromotionInfoView extends TextSwitcher {

  private List<String> texts;
  private final int ANIM_DURATION = 500;
  private final int ANIM_DELAY = 2000;

  private int index;

  private Handler handler = new Handler();

  private Runnable task = new Runnable() {
    @Override public void run() {
      setText(texts.get(index++ % texts.size()));
      handler.postDelayed(task, ANIM_DELAY);
    }
  };

  public PromotionInfoView(Context context) {
    this(context, null);
  }

  public PromotionInfoView(Context context, AttributeSet attrs) {
    super(context, attrs);
    addView(createTextView());
    addView(createTextView());
    setInAnimation(createInAnim());
    setOutAnimation(createOutAnim());
  }

  private TextView createTextView() {
    TextView tv = new TextView(getContext());
    tv.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
    tv.setTextColor(Color.WHITE);
    tv.setTextSize(11);
    tv.setLayoutParams(new LayoutParams(MATCH_PARENT, MATCH_PARENT));
    return tv;
  }

  public void setTexts(List<String> texts) {
    this.texts = texts;
    resetAnim();
    handler.post(task);
  }

  private void resetAnim() {
    handler.removeCallbacks(task);
    index = 0;
  }

  private Animation createOutAnim() {
    TranslateAnimation anim =
        new TranslateAnimation(ABSOLUTE, 0, 0, 0, RELATIVE_TO_SELF, 0, RELATIVE_TO_SELF, -1.0f);
    anim.setDuration(ANIM_DURATION);
    return anim;
  }

  private Animation createInAnim() {
    TranslateAnimation anim =
        new TranslateAnimation(ABSOLUTE, 0, 0, 0, RELATIVE_TO_SELF, 1.0f, RELATIVE_TO_SELF, 0);
    anim.setDuration(ANIM_DURATION);
    return anim;
  }
}
