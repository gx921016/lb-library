package com.lonelybanana.lblibrary.log;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LBLogManager {
    private LBLogConfig config;
    private static LBLogManager instance;
    private List<LBLogPrinter> printers = new ArrayList<>();

    private LBLogManager(LBLogConfig config, LBLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static LBLogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull LBLogConfig config, LBLogPrinter... printers) {
        instance = new LBLogManager(config, printers);
    }

    public LBLogConfig getConfig() {
        return config;
    }

    public List<LBLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(LBLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(LBLogPrinter printer) {
        if (printers != null) {
            printers.remove(printer);
        }
    }
}
