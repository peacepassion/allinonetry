package org.peace.gradleandroidtemplate.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.gradleandroidtemplate.app.R;
import org.peace.gradleandroidtemplate.util.LogHelper;


public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = LogHelper.getNativeSimpleLogTag(MainActivity.class, LogHelper.DEFAULT_LOG_TAG);

    @InjectView(R.id.start_btn)
    Button mStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
    }

    @OnClick({R.id.start_btn})
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.start_btn) {

        }
    }
}
