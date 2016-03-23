package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

import net.wequick.small.Small;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @Override protected void onStart() {
    super.onStart();

    Small.setBaseUri("http://m.wequick.net/demo/");
    Small.setUp(this, new Small.OnCompleteListener() {
      @Override public void onComplete() {
        Toast.makeText(MainActivity.this, "set up OK", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      Small.openUri("mine", this);
    }
  }
}
