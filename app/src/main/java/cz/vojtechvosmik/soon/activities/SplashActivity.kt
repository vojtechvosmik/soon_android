package cz.vojtechvosmik.soon.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import cz.vojtechvosmik.soon.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_LENGTH = 800

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startCloseTimer()
    }

    private fun startCloseTimer() {
        Handler().postDelayed({
            finish()
        }, SPLASH_LENGTH.toLong())
    }
}
