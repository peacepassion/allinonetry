package net.wequick.example.small.app.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by galen on 15/11/11.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_main);

        handleToolbar();

        ButterKnife.inject(this);
    }

    private void handleToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.mine_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Great Small Plugin");
    }

    @OnClick(R.id.bundle_start) public void onClick() {
        Toast.makeText(this, "Hello from bundle", Toast.LENGTH_SHORT).show();
    }
}
