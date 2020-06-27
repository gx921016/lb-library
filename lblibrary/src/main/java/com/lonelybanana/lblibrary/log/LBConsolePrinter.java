package com.lonelybanana.lblibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;

import static com.lonelybanana.lblibrary.log.LBLogConfig.MAX_LEN;

public class LBConsolePrinter implements LBLogPrinter {
    @Override
    public void print(@NonNull LBLogConfig config, int level, String tag, @NonNull String printString) {
        int len = printString.length();
        int coutOfSub = len / MAX_LEN;
        if (coutOfSub>0){
            int index = 0;
            for (int i = 0;i<coutOfSub;i++){

                Log.println(level,tag,printString.substring(index,index+MAX_LEN));
                index += MAX_LEN;
            }
            if (index!=len){
                Log.println(level,tag,printString.substring(index,len));
            }
        }else {
            Log.println(level,tag,printString);
        }


    }
}
