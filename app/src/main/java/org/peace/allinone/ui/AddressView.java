package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import org.peace.allinone.R;

import static android.view.View.MeasureSpec.AT_MOST;
import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import static me.ele.base.utils.DimenUtil.dip2px;

public class AddressView extends LinearLayout {

  private final int maxWidth = dip2px(200);

  @BindView(R.id.address_text) protected TextView textView;

  public AddressView(Context context) {
    this(context, null);
  }

  public AddressView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public AddressView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setGravity(Gravity.CENTER_VERTICAL);
    inflate(context, R.layout.sp_home_address_view, this);
    ButterKnife.bind(this, this);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (getMeasuredWidth() > maxWidth) {
      int width = textView.getMeasuredWidth() - (getMeasuredWidth() - maxWidth);
      textView.measure(makeMeasureSpec(width, AT_MOST),
          makeMeasureSpec(getMeasuredHeight(), EXACTLY));
      textView.setHorizontalFadingEdgeEnabled(true);
      setMeasuredDimension(maxWidth, getMeasuredHeight());
    } else {
      textView.setHorizontalFadingEdgeEnabled(false);
    }
  }

  public void setAddress(String address) {
    textView.setText(address);
  }
}
