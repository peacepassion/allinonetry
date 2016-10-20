package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import org.peace.allinone.R;

public class OrderStatusView extends FrameLayout {

  public OrderStatusView(Context context) {
    this(context, null);
  }

  public OrderStatusView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public OrderStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.sp_home_order_status, this);
  }
}
