package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import java.util.ArrayList;
import java.util.List;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.r_v) RecyclerView rv;
  @InjectView(R.id.container) FrameLayout container;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);

    setupRV();
  }

  public void setupRV() {
    List<FoodCategory> foodCategories = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      FoodCategory data = new FoodCategory();
      data.name = "Category: " + i;
      List<String> lst = new ArrayList<>(10);
      for (int j = 0; j < 10; j++) {
        lst.add("Food: " + i + " - " + j);
      }
      data.foods = lst;
      foodCategories.add(data);
    }

    FoodListAdapter adapter = new FoodListAdapter(foodCategories);
    rv.setLayoutManager(new LinearLayoutManager(this));
    rv.setAdapter(adapter);
    rv.addItemDecoration(new PinnedItemDecoration(container));
  }

}
