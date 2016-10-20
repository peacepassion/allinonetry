package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.emotion) protected EmotionView emotionView;
  @BindView(R.id.address) protected AddressView addressView;
  @BindView(R.id.search_key_words) protected SearchKeyWordsView searchKeyWordsView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    getWindow().getDecorView()
        .setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
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
}
