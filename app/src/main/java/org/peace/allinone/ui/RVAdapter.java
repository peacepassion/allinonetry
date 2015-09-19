package org.peace.allinone.ui;

import android.support.v7.widget.RecyclerView;
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

  List<String> contents = new LinkedList<>();

  public RVAdapter() {
    for (int i = 0; i < 100; ++i) {
      contents.add("Item " + i);
    }
  }

  @Override public int getItemViewType(int position) {
    return super.getItemViewType(position);
  }

  @Override public void onViewRecycled(RVVH holder) {
    super.onViewRecycled(holder);
  }

  @Override public RVVH onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
    return new RVVH(itemView);
  }

  @Override public void onBindViewHolder(RVVH holder, int position) {
    String content = contents.get(position);
    holder.tv.setText(content);
  }

  @Override public int getItemCount() {
    return contents.size();
  }

}
