package com.lonelybanana.lb_library.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lonelybanana.lb_library.R
import com.lonelybanana.lblibrary.log.*

class LBLogDemoActivity : AppCompatActivity() {
    var  viewPrinter:LBViewPrinter ? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l_b_log_demo)
        viewPrinter = LBViewPrinter(this)
        findViewById<View>(R.id.button).setOnClickListener {
            printLog()
        }
        viewPrinter!!.viewProvider.showFloatingView()

    }
    private fun printLog(){
        LBLogManager.getInstance().addPrinter(viewPrinter)

        LBLog.log(object :LBLogConfig(){
            override fun includeTread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
        },LBLogType.E,"----","555666")
        LBLog.a("1233453")
    }

}
