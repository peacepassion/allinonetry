package org.peace.allinone.ui;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.peace.allinone.R;

public class RVVH extends RecyclerView.ViewHolder {

  @InjectView(R.id.t_v) public TextView tv;
  public int type;
  private Resources res;

  public RVVH(View itemView, int type) {
    super(itemView);
    res = itemView.getResources();
    this.type = type;
    ButterKnife.inject(this, itemView);
  }

  public void bind(String str) {
    if (type == FoodListAdapter.CATEGORY) {
      tv.setTextColor(color(android.R.color.holo_red_light));
    } else {
      tv.setTextColor(Color.BLACK);
    }
    tv.setText(str);
  }

  public int color(int rid) {
    return res.getColor(rid);
  }
}
