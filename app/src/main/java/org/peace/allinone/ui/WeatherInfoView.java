package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import org.peace.allinone.R;

public class WeatherInfoView extends LinearLayout {

  public WeatherInfoView(Context context) {
    this(context, null);
  }

  public WeatherInfoView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public WeatherInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setOrientation(VERTICAL);
    inflate(context, R.layout.sp_home_weather_info, this);
  }
}
