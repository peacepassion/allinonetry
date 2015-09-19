package org.peace.allinone.ui;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import java.util.List;
import org.peace.allinone.R;

/**
 * Created by peacepassion on 15/9/19.
 */
public class RVAdapter extends RecyclerView.Adapter<RVVH> {

  public List<String> contents = new LinkedList<>();

  static final int FIRST = 1;
  static final int NORMAL = 2;
  static final int LAST = 3;

  public RVAdapter() {
  }

  public void setData(List<String> data) {
    contents.clear();
    for (int i = 0; i < data.size(); i++) {
      contents.add(data.get(i));
      notifyItemInserted(i);
    }
  }

  @Override public int getItemViewType(int position) {
    if (position == 0) {
      return FIRST;
    }
    if (position == contents.size() - 1) {
      return LAST;
    }
    return NORMAL;
  }

  @Override public void onViewRecycled(RVVH holder) {
    super.onViewRecycled(holder);
  }

  @Override public RVVH onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
    return new RVVH(itemView);
  }

  @Override public void onBindViewHolder(RVVH holder, int position) {
    int type = getItemViewType(position);
    String content = contents.get(position);
    holder.tv.setText(content);
    if (type == FIRST || type == LAST) {
      holder.tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
    }
  }

  @Override public int getItemCount() {
    return contents.size();
  }

}
