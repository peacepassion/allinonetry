package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import org.peace.allinone.R;

public class PromotionInfoView extends LinearLayout {

  public PromotionInfoView(Context context) {
    this(context, null);
  }

  public PromotionInfoView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PromotionInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.sp_home_promotion_info, this);
  }
}
