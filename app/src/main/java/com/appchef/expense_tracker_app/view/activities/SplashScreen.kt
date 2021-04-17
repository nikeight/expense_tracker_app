package com.appchef.expense_tracker_app.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.appchef.expense_tracker_app.R
import com.appchef.expense_tracker_app.view.activities.onboarding.OnBoardingActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //getting the pref of dark mode
        val darkModePref =
            getSharedPreferences(getString(R.string.dark_mode_pref_key), Context.MODE_PRIVATE)
                .getInt(
                    getString(R.string.dark_mode_pref_key),
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                )

        //setting the dark mode accordingly
        AppCompatDelegate.setDefaultNightMode(darkModePref)

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