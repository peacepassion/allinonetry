package org.peace.allinone.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import me.ele.commons.CollectionsUtils;
import org.peace.allinone.R;

public class FoodListAdapter extends RecyclerView.Adapter<RVVH>
    implements PinnedItemDecoration.PinnedHeaderAdapter {

  public static final int FOOD = 2;
  public static final int CATEGORY = 1;

  private List<Object> dataSet;

  public FoodListAdapter(List<FoodCategory> foodCategories) {
    initDataSet(foodCategories);
  }

  private void initDataSet(List<FoodCategory> foodCategories) {
    if (dataSet != null) {
      dataSet.clear();
    } else {
      dataSet = new ArrayList<>();
    }

    if (CollectionsUtils.isEmpty(foodCategories)) {
      return;
    }

    for (FoodCategory foodCategory : foodCategories) {
      dataSet.add(foodCategory);
      dataSet.addAll(foodCategory.foods);
    }
  }

  @Override public RVVH onCreateViewHolder(ViewGroup parent, int viewType) {
    View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
    return new RVVH(item, viewType);
  }

  @Override public void onBindViewHolder(RVVH vh, int position) {
    Object obj = getItem(position);
    vh.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Toast.makeText(v.getContext(), "position: " + position, Toast.LENGTH_SHORT).show();
      }
    });
    if (obj instanceof FoodCategory) {
      vh.bind(((FoodCategory) obj).name);
      return;
    }
    vh.bind((String) obj);
  }

  @Override public int getItemViewType(int position) {
    return getItem(position) instanceof FoodCategory ? CATEGORY : FOOD;
  }

  public int getItemPosition(Object item) {
    return dataSet.indexOf(item);
  }

  public void notifyDataSetChanged(List<FoodCategory> foodCategories) {
    initDataSet(foodCategories);
    notifyDataSetChanged();
  }

  public Object getItem(int position) {
    if (position < 0 || position >= CollectionsUtils.size(dataSet)) {
      return null;
    }
    return dataSet.get(position);
  }

  @Override public int getItemCount() {
    return CollectionsUtils.size(dataSet);
  }

  @Override public boolean isPinnedViewType(int viewType) {
    return viewType == CATEGORY;
  }
}
