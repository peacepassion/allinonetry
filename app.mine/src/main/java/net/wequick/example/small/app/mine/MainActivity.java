package net.wequick.example.small.app.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import javax.inject.Inject;
import me.ele.omniknight.OKInjector;

/**
 * Created by galen on 15/11/11.
 */
public class MainActivity extends AppCompatActivity {

    @Inject MySin mySin;

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
        OKInjector.inject(this);
        Toast.makeText(this, mySin.foo(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Fuck", Toast.LENGTH_SHORT).show();
    }
}
