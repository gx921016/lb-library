package com.lonelybanana.lblibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * Tips:
 * 1 打印堆栈信息
 * 2 File输出
 * 3 模拟控制台
 */
public class LBLog {
    private static final String LB_LOG_PACKAGE;

    static {
        String className = LBLog.class.getName();
        LB_LOG_PACKAGE = className.substring(0,className.lastIndexOf('.')+1);
    }
    public void v(Object... contents) {
        log(LBLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(LBLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(LBLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(LBLogType.D, tag, contents);
    }

    public static void w(Object... contents) {
        log(LBLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(LBLogType.W, tag, contents);
    }

    public static void i(Object... contents) {
        log(LBLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(LBLogType.I, tag, contents);
    }

    public static void a(Object... contents) {
        log(LBLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(LBLogType.A, tag, contents);
    }

    public static void e(Object... contents) {
        log(LBLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(LBLogType.E, tag, contents);
    }

    public static void log(@LBLogType.TYPE int type, Object... contents) {
        log(type, LBLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@LBLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(LBLogManager.getInstance().getConfig(), type, tag, contents);
    }

    public static void log(@NonNull LBLogConfig config, @LBLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if (!config.enable()) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        if (config.includeTread()) {
            String threadInfo = LBLogConfig.LB_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }
        if (config.stackTraceDepth() > 0) {
            String stackTrace = LBLogConfig.LB_STACK_TRACE_FORMATTER.format(LBStackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(),LB_LOG_PACKAGE,config.stackTraceDepth()));
            sb.append(stackTrace).append("\n");
        }
        String body = parseBody(contents,config);
        sb.append(body);
        List<LBLogPrinter> printers = config.printers() != null ? Arrays.asList(config.printers()) : LBLogManager.getInstance().getPrinters();
        if (printers == null) {
            return;
        }
        //打印log
        for (LBLogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }
    }

    private static String parseBody(@NonNull Object[] contents, @NonNull LBLogConfig config) {
        if (config.injectJsonParser() != null) {
            return config.injectJsonParser().toJson(contents);
        }
        StringBuffer sb = new StringBuffer();
        for (Object o : contents) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
