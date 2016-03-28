package net.wequick.example.small.app.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        ButterKnife.inject(this);
    }

    @OnClick(R.id.bundle_start) public void onClick() {
        Toast.makeText(this, "Hello from bundle", Toast.LENGTH_SHORT).show();
    }
}
