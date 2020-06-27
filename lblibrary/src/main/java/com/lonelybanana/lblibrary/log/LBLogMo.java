package com.lonelybanana.lblibrary.log;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class LBLogMo {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
    public long timeMills;
    public int level;
    public String tag;
    public String log;

    public LBLogMo(long timeMills, int level, String tag, String log) {
        this.timeMills = timeMills;
        this.level = level;
        this.tag = tag;
        this.log = log;
    }

    public String flattenedLog() {
        return getFlattened() + "\n" + log;
    }

    public String getFlattened() {
        return foramt(timeMills) + "|" + level + "|" + tag + "|:";
    }

    private String foramt(long timeMills) {
        return sdf.format(timeMills);
    }


}
