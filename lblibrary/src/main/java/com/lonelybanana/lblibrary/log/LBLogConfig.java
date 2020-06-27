package com.lonelybanana.lblibrary.log;

public abstract class LBLogConfig {
    public static  int MAX_LEN = 512;
    static  LBStackTraceFormatter LB_STACK_TRACE_FORMATTER = new LBStackTraceFormatter();
    static  LBThreadFormatter LB_THREAD_FORMATTER = new LBThreadFormatter();
    public JsonParser injectJsonParser(){
        return null;
    }
    public String getGlobalTag(){
        return "BLLog";
    }
    public boolean enable(){
        return true;
    }
    public boolean includeTread(){
        return false;
    }
    public int stackTraceDepth(){
        return 5;
    }
    public LBLogPrinter[] printers(){
        return null;
    }

    public interface JsonParser{
        String toJson(Object src);
    }
}
