package org.peace.gradleandroidtemplate.util;


/**
 * Created by peace_da on 2014/10/29.
 */
public final class LogHelper {

    public static final String DEFAULT_LOG_TAG = "Peace";

    public static final String getNativeSimpleLogTag(Class cls, String tag) {
       return tag + " - " + cls.getSimpleName();
    }
}
