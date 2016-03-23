package org.peace.allinone;

import android.app.Application;
import me.ele.commons.AppLogger;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

  @Override public void onCreate() {
    super.onCreate();
    AppLogger.debug = true;
  }
}
