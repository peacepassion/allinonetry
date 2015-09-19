package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.util.LinkedList;
import java.util.List;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.r_v) RecyclerView rv;

  RVAdapter adapter = new RVAdapter();
  GridLayoutManager manager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override public int getSpanSize(int position) {
        return (position % 3 == 0 ? 2 : 1);
      }
    });
    rv.setLayoutManager(manager);
    rv.setAdapter(adapter);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      List<String> contents = new LinkedList<>();
      for (int i = 0; i < 100; i++) {
        contents.add("item " + i);
      }
      adapter.setData(contents);
    }
  }
}
