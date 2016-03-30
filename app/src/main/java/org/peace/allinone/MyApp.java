package org.peace.allinone;

import android.app.Application;
import net.wequick.small.Small;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

  @Override public void onCreate() {
    super.onCreate();

    Small.preSetUp(this);
  }
}
