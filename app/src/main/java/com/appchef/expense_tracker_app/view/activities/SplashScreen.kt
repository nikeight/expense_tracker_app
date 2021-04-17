package com.appchef.expense_tracker_app.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.appchef.expense_tracker_app.R
import com.appchef.expense_tracker_app.view.activities.onboarding.OnBoardingActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Changing the Activity.
        // Todo using Handler is not the best practice.
        //TODO start the onboarding activity only if user is opening for first time else go to main activity


        Handler(Looper.getMainLooper()).postDelayed({
            if(FirebaseAuth.getInstance().currentUser ==null)
                startActivity(Intent(this@SplashScreen, OnBoardingActivity::class.java))
            else
                startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }, 2000)
    }
}