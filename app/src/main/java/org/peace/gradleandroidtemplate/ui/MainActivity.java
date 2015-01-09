package org.peace.gradleandroidtemplate.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import org.peace.gradleandroidtemplate.app.R;
import org.peace.gradleandroidtemplate.util.LogHelper;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
