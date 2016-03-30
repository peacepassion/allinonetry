package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import net.wequick.small.Small;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.start_btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Small.openUri("mine", MainActivity.this);
      }
    });
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
}
