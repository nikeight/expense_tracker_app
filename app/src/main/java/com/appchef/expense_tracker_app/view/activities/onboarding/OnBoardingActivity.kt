package com.appchef.expense_tracker_app.view.activities.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.appchef.expense_tracker_app.R
import com.appchef.expense_tracker_app.databinding.ActivityOnBoardingBinding
import com.appchef.expense_tracker_app.view.activities.MainActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private var currentPage: Int = 0
    private val maxPages: Int = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 4
            override fun createFragment(position: Int): Fragment {
                //TODO change these to show different features in different fragments
                return when (position) {
                    0 -> OnBoardingFragment(
                        R.drawable.sample_onboarding,
                        "Header", "Quick Text description to brag the feature"
                    )
                    1 -> OnBoardingFragment(
                        R.drawable.sample_onboarding,
                        "Header", "Quick Text description to brag the feature"
                    )
                    2 -> OnBoardingFragment(
                        R.drawable.sample_onboarding,
                        "Header", "Quick Text description to brag the feature"
                    )
                    3 -> OnBoardingFragment(
                        R.drawable.sample_onboarding,
                        "Header", "Quick Text description to brag the feature"
                    )
                    else -> OnBoardingFragment(
                        R.drawable.sample_onboarding,
                        "Expense Tracker", ""
                    )
                }
            }
        }

        binding.backBtn.setOnClickListener {
            if (currentPage != 0)
                binding.viewPager.currentItem = currentPage - 1
        }

        binding.skipBtn.setOnClickListener {
            goToNextActivity()
        }

        binding.nextBtn.setOnClickListener {
            if (currentPage == maxPages - 1)
                goToNextActivity()
            else
                binding.viewPager.currentItem = currentPage + 1
        }

        //this method shows proper progress in the dot progress bar on bottom left of layout
        //& sets the value of currentPage var
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position

                //setting all to default color
                binding.dot1.setColorFilter(
                    ContextCompat.getColor(
                        baseContext,
                        R.color.colorVariantOnPrimary
                    )
                )
                binding.dot2.setColorFilter(
                    ContextCompat.getColor(
                        baseContext,
                        R.color.colorVariantOnPrimary
                    )
                )
                binding.dot3.setColorFilter(
                    ContextCompat.getColor(
                        baseContext,
                        R.color.colorVariantOnPrimary
                    )
                )
                binding.dot4.setColorFilter(
                    ContextCompat.getColor(
                        baseContext,
                        R.color.colorVariantOnPrimary
                    )
                )

                //setting the active dot's color according to active page
                when (position) {
                    0 -> binding.dot1.setColorFilter(
                        ContextCompat.getColor(
                            baseContext,
                            R.color.colorOnPrimary
                        )
                    )
                    1 -> binding.dot2.setColorFilter(
                        ContextCompat.getColor(
                            baseContext,
                            R.color.colorOnPrimary
                        )
                    )
                    2 -> binding.dot3.setColorFilter(
                        ContextCompat.getColor(
                            baseContext,
                            R.color.colorOnPrimary
                        )
                    )
                    3 -> binding.dot4.setColorFilter(
                        ContextCompat.getColor(
                            baseContext,
                            R.color.colorOnPrimary
                        )
                    )
                }
            }
        })
    }

    private fun goToNextActivity() {
        //TODO goto next activity : Auth Activity
        startActivity(Intent(this@OnBoardingActivity, MainActivity::class.java))
    }
}