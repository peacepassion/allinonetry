package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import org.peace.allinone.R;

public class SearchView extends LinearLayout {

  public SearchView(Context context) {
    this(context, null);
  }

  public SearchView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.sp_home_search_view, this);
  }
}
