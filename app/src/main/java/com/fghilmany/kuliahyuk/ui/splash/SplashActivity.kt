package com.fghilmany.kuliahyuk.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.fghilmany.kuliahyuk.R
import com.fghilmany.kuliahyuk.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val animSplash = AnimationUtils.loadAnimation(this, R.anim.anim_splash)

        iv_splash.startAnimation(animSplash)

        val timerThread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(this@SplashActivity, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        timerThread.start()

    }
}
