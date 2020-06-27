package com.lonelybanana.lblibrary.log;

public class LBThreadFormatter implements LBLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread:"+data.getName();
    }
}
