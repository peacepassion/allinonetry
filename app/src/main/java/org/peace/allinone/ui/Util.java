package org.peace.allinone.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import me.ele.base.utils.DimenUtil;
import me.ele.components.recyclerview.EMRecyclerView;
import me.ele.components.recyclerview.HeaderViewRecyclerAdapter;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static me.ele.base.utils.DimenUtil.dip2px;

public class Util {

  public static TextView createHead(Context context, int h, int bg, String text) {
    TextView tv = new TextView(context);
    tv.setLayoutParams(new MarginLayoutParams(MATCH_PARENT, h));
    tv.setText(text);
    tv.setBackgroundColor(bg);
    tv.setGravity(Gravity.CENTER);
    tv.setTextSize(30);
    tv.setClickable(true);
    return tv;
  }

  public static void insertItem(Context context, EMRecyclerView recyclerView) {
    recyclerView.setAdapter(new HeaderViewRecyclerAdapter(new MyAdapter()) {
      @Override public boolean isEmpty() {
        return false;
      }
    });
  }

  public static class MyVH extends RecyclerView.ViewHolder {

    TextView tv;

    public MyVH(View itemView) {
      super(itemView);
      this.tv = (TextView) itemView;
    }
  }

  public static class MyAdapter extends RecyclerView.Adapter<MyVH> {

    @Override public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
      TextView textView = new TextView(parent.getContext());
      textView.setLayoutParams(
          new MarginLayoutParams(MATCH_PARENT, dip2px(40)));
      textView.setGravity(Gravity.CENTER);
      textView.setTextSize(20);
      return new MyVH(textView);
    }

    @Override public void onBindViewHolder(MyVH holder, int position) {
      holder.tv.setText("Item " + position);
      holder.tv.setClickable(true);
    }

    @Override public int getItemCount() {
      return 40;
    }
  }
}
