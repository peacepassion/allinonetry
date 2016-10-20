package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.Arrays;
import org.peace.allinone.R;

public class InfoAndIconView extends RelativeLayout {

  @BindView(R.id.image) protected ImageView imageView;
  @BindView(R.id.weather_info) protected WeatherInfoView weatherInfoView;
  @BindView(R.id.promotion_info) protected PromotionInfoView promotionInfoView;

  public InfoAndIconView(Context context) {
    this(context, null);
  }

  public InfoAndIconView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public InfoAndIconView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.sp_home_info_and_icon_view, this);
    ButterKnife.bind(this, this);
  }

  public void showWeather() {
    weatherInfoView.setVisibility(VISIBLE);
    promotionInfoView.setVisibility(GONE);
  }

  public void showPromotion() {
    weatherInfoView.setVisibility(GONE);
    promotionInfoView.setVisibility(VISIBLE);
    promotionInfoView.setTexts(Arrays.asList("one", "two", "three"));
  }
}
