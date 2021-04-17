package com.appchef.expense_tracker_app.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.appchef.expense_tracker_app.R
import com.appchef.expense_tracker_app.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    // Binding Objects.
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        setupDarkModeControls()

        return binding.root
    }

    private fun setupDarkModeControls() {
        //checking the current mode & marking it on ui
        when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> binding.darkYesChip.isChecked = true
            AppCompatDelegate.MODE_NIGHT_NO -> binding.darkNoChip.isChecked = true
            else -> binding.darkAutoChip.isChecked = true
        }

        //setting click listeners for dark mode buttons
        binding.darkNoChip.setOnCheckedChangeListener { compoundButton, b ->
            if (b) setDarkModePref(AppCompatDelegate.MODE_NIGHT_NO)
        }
        binding.darkYesChip.setOnCheckedChangeListener { compoundButton, b ->
            if (b) setDarkModePref(AppCompatDelegate.MODE_NIGHT_YES)
        }
        binding.darkAutoChip.setOnCheckedChangeListener { compoundButton, b ->
            if (b) setDarkModePref(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    private fun setDarkModePref(pref: Int) {
        //setting the mode
        AppCompatDelegate.setDefaultNightMode(pref)

        //saving to shared preference
        activity?.getSharedPreferences(getString(R.string.dark_mode_pref_key), Context.MODE_PRIVATE)
            ?.edit()
            ?.putInt(getString(R.string.dark_mode_pref_key), pref)
            ?.apply()
    }
}