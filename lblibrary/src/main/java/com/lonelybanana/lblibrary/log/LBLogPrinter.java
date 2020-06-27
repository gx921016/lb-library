package com.lonelybanana.lblibrary.log;

import androidx.annotation.NonNull;

public interface LBLogPrinter {
    void print(@NonNull LBLogConfig config,int level,String tag, @NonNull String printString);
}
