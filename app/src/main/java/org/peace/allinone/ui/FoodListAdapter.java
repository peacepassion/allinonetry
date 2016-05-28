package org.peace.allinone.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import me.ele.commons.AppLogger;
import me.ele.commons.CollectionsUtils;
import org.peace.allinone.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

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
      vh.itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          //Toast.makeText(v.getContext(), "category: " + ((FoodCategory) obj).name, Toast.LENGTH_SHORT).show();
          popup(vh.itemView.getContext(), v);
        }
      });
      return;
    }
    vh.bind((String) obj);
  }

  private void popup(Context context, View anchor) {
    View contentView = LayoutInflater.from(context).inflate(R.layout.popup_content, null);
    TextView titleView = (TextView) contentView.findViewById(R.id.title);
    TextView content = (TextView) contentView.findViewById(R.id.content);
    titleView.setText("Title");
    content.setText("content");
    PopupWindow popupWindow = new PopupWindow(contentView, 600, WRAP_CONTENT);
    popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    popupWindow.setOutsideTouchable(true);
    popupWindow.setFocusable(true);
    popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
    int[] loc = new int[2];
    anchor.getLocationInWindow(loc);
    AppLogger.e("x: " + loc[0] + ", y: " + loc[1]);
    popupWindow.showAtLocation(anchor, Gravity.TOP | Gravity.RIGHT, 00, loc[1]);
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
