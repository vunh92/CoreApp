package com.vunh.coreapp

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.vunh.coreapp.common.AppSharePresfs
import com.vunh.coreapp.common.CURRENT_LANGUAGE
import com.vunh.coreapp.common.LANGUAGE
import com.vunh.coreapp.common.TIME_DELAY
import java.util.*

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        findViewById<TextView>(R.id.tv_version_name).text = BuildConfig.VERSION_NAME
        Thread {
            startProgressBar()
            getLanguage()
            direction()
            finish()
        }.start()
    }

    private fun getLanguage() {
        val locale = AppSharePresfs(this).newInstance().getString(CURRENT_LANGUAGE)
        if (locale.isNullOrEmpty()) {
            AppSharePresfs(this).newInstance().setString(CURRENT_LANGUAGE, LANGUAGE.ENGLISH)
            Locale.setDefault(Locale(LANGUAGE.ENGLISH))
        } else {
            Locale.setDefault(Locale(locale))
        }
        resources?.updateConfiguration(Configuration(), DisplayMetrics())
    }

    private fun startProgressBar() {
        var progress = 0
        while (progress < 100) {
            try {
                Thread.sleep(TIME_DELAY.TIME_SPLASH.toLong())
                findViewById<ProgressBar>(R.id.progress_bar).progress = progress
            } catch (e: Exception) {
                e.printStackTrace()
            }
            progress += 1
        }
    }

    private fun direction() {
        when (getToken() != null) {
            true -> {
                gotoHome()
            }
            else -> {
                gotoLogin()
            }
        }
        finish()
    }
}