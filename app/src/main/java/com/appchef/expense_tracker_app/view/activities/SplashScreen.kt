package com.appchef.expense_tracker_app.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.appchef.expense_tracker_app.R
import com.appchef.expense_tracker_app.view.activities.onboarding.OnBoardingActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Changing the Activity.
        // Todo using Handler is not the best practice.
        //TODO start the onboarding activity only if user is opening for first time else go to main activity

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreen, OnBoardingActivity::class.java))
            finish()
        }, 700)
    }
}