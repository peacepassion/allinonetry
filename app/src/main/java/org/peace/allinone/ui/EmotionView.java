package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import org.peace.allinone.R;

public class EmotionView extends FrameLayout {

  @BindView(R.id.info_and_icon) protected InfoAndIconView infoAndIconView;
  @BindView(R.id.order_status) protected OrderStatusView orderStatusView;

  public EmotionView(Context context) {
    this(context, null);
  }

  public EmotionView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public EmotionView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.sp_home_emotion_view, this);
    ButterKnife.bind(this, this);
  }

  public void showWeather() {
    infoAndIconView.setVisibility(VISIBLE);
    orderStatusView.setVisibility(GONE);
    infoAndIconView.showWeather();
  }

  public void showPromotion() {
    infoAndIconView.setVisibility(VISIBLE);
    orderStatusView.setVisibility(GONE);
    infoAndIconView.showPromotion();
  }

  public void showOrderStatus() {
    infoAndIconView.setVisibility(GONE);
    orderStatusView.setVisibility(VISIBLE);
    orderStatusView.setStatusTexts();
  }
}
