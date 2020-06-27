package com.lonelybanana.lb_library

import android.app.Application
import com.google.gson.Gson
import com.lonelybanana.lblibrary.log.LBConsolePrinter
import com.lonelybanana.lblibrary.log.LBLogConfig
import com.lonelybanana.lblibrary.log.LBLogManager

class MyApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        LBLogManager.init(object :LBLogConfig(){
            override fun injectJsonParser(): JsonParser {
                return JsonParser { src -> Gson().toJson(src)}
            }
            override fun getGlobalTag(): String {
                return "MyApplication"
            }

            override fun enable(): Boolean {
                return true
            }
        },LBConsolePrinter())


    }
}