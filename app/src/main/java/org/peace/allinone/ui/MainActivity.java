package org.peace.allinone.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import me.ele.base.utils.DimenUtil;
import me.ele.components.pullrefresh.PullToRefresh;
import me.ele.components.recyclerview.EMRecyclerView;
import org.peace.allinone.R;

import static org.peace.allinone.ui.Util.createHead;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.rv) EMRecyclerView rv;
  @BindView(R.id.toolbar) HomeFragmentToolbar toolbar;
  @BindView(R.id.emotion) protected EmotionView emotionView;
  @BindView(R.id.address) protected AddressView addressView;
  @BindView(R.id.search_key_words) protected SearchKeyWordsView searchKeyWordsView;


  int H = DimenUtil.dip2px(60);

  @Override protected void onCreate(Bundle savedInstanceState) {
    getWindow().getDecorView()
        .setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    setupRV();
    setupToolbar();
  }

  private void setupToolbar() {
    CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) toolbar.getLayoutParams();
    ToolbarBehavior behavior = new ToolbarBehavior();
    behavior.addHeightChangeListener(new ToolbarBehavior.HeightChangeListener() {
      @Override public void onHeightChange(int height) {

      }
    });
    lp.setBehavior(behavior);

    emotionView.showOrderStatus();
    addressView.setAddress("近铁城市广场");

    List<String> keyWords = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      keyWords.add("麦当劳");
      keyWords.add("粥");
      keyWords.add("KFC");
    }
    searchKeyWordsView.setKeyWords(keyWords);
  }

  public void setupRV() {
    rv.setLayoutManager(new LinearLayoutManager(this));
    Util.insertItem(this, rv);
    rv.addHeaderView(createHead(this, H, Color.TRANSPARENT, "Head 1"));
    rv.addHeaderView(createHead(this, H, Color.TRANSPARENT, "Head 2"));
    rv.setRefreshListener(new PullToRefresh.OnRefreshListener() {
      @Override public void onRefresh() {
        rv.postDelayed(new Runnable() {
          @Override public void run() {
            rv.finishRefresh();
          }
        }, 30000);
      }
    });
  }
}
