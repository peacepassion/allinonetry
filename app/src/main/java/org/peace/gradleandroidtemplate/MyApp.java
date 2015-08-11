package org.peace.gradleandroidtemplate;

import android.app.Application;
import android.content.Context;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher refWatcher(Context context) {
        MyApp app = (MyApp) context.getApplicationContext();
        return app.refWatcher;
    }
}
