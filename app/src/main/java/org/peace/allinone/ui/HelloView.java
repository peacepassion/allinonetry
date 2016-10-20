package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import org.peace.allinone.R;

public class HelloView extends FrameLayout {

  @BindView(R.id.text) protected TextView textView;
  // todo change to SimpleDraweeView for gif
  @BindView(R.id.image) protected ImageView imageView;

  public HelloView(Context context) {
    this(context, null);
  }

  public HelloView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public HelloView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.sp_home_hello_view, this);
    ButterKnife.bind(this, this);
  }
}
