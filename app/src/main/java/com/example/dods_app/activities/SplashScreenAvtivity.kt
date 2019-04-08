package com.example.dods_app.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.dods_app.R

class SplashScreenActivity : AppCompatActivity() {
    lateinit var countDownTimer: CountDownTimer
    var timerState: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        timerState = savedInstanceState?.getLong("timerState") ?: timerState
        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)

        countDownTimer = object : CountDownTimer(MILLISTILLFINISH - timerState, SECOND) {
            override fun onFinish() {
                this@SplashScreenActivity.startActivity(intent)
                this@SplashScreenActivity.finish()
            }

            override fun onTick(millisUntilFinished: Long) {
                timerState = MILLISTILLFINISH - millisUntilFinished
            }
        }
        countDownTimer.start()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putLong("timerState", timerState)
    }

    override fun onStop() {
        super.onStop()
        countDownTimer.cancel()
    }

    companion object {
        const val MILLISTILLFINISH: Long = 2000
        const val SECOND: Long = 1000
    }
}
