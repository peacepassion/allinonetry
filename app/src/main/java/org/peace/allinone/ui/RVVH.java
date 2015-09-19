package org.peace.allinone.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.peace.allinone.R;

/**
 * Created by peacepassion on 15/9/19.
 */
public class RVVH extends RecyclerView.ViewHolder {

  @InjectView(R.id.t_v) public TextView tv;

  public RVVH(View itemView) {
    super(itemView);
    ButterKnife.inject(this, itemView);
  }

}
