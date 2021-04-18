package com.appchef.expense_tracker_app.view.fragment

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.appchef.expense_tracker_app.R
import com.appchef.expense_tracker_app.databinding.FragmentAccountBinding
import com.appchef.expense_tracker_app.view.activities.SplashScreen
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        var user=FirebaseAuth.getInstance().currentUser

        setupDarkModeControls()
        setupIncomeBudget()

        activity?.let {
            Glide.with(it)
                .load(user.photoUrl)
                .placeholder(context?.let { it1 -> ContextCompat.getDrawable(it1,R.drawable.ic_account_vector) })
                .into(binding.userImage)
        }
        binding.userName.text=user?.displayName ?: "User"
        binding.userPhone.text=user?.phoneNumber ?: "1234567890"

        binding.logOutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(activity,SplashScreen::class.java))
        }

        return binding.root
    }

    private fun setupIncomeBudget(){
        val incomePref = activity?.getSharedPreferences(getString(R.string.income_pref_key),Context.MODE_PRIVATE)
        val budgetPref=activity?.getSharedPreferences(getString(R.string.budget_pref_key),Context.MODE_PRIVATE)

        val income = incomePref?.getFloat(getString(R.string.income_pref_key),0.0f)
        val budget = incomePref?.getFloat(getString(R.string.budget_pref_key),0.0f)

        binding.incomeText.text=income.toString()
        binding.budgetText.text=budget.toString()

        //TODO handle app crash for alphabets inputed
        binding.incomeEditBtn.setOnClickListener {
            val et=EditText(context)
            et.inputType=InputType.TYPE_NUMBER_FLAG_SIGNED
            et.setText(income.toString())

            MaterialAlertDialogBuilder(requireContext(),R.style.AlertDialogTheme)
                .setTitle("Change income")
                .setView(et)
                .setPositiveButton("Set",DialogInterface.OnClickListener{dialogInterface, i ->
                    incomePref?.edit()
                        ?.putFloat(getString(R.string.income_pref_key),et.text.toString().toFloat())
                        ?.apply()
                    setupIncomeBudget()
                })
                .setNegativeButton("Cancel",DialogInterface.OnClickListener{dialogInterface, i ->
                    dialogInterface.dismiss()
                }).show()
        }

        binding.budgetEditBtn.setOnClickListener {
            val et=EditText(context)
            et.inputType=InputType.TYPE_NUMBER_FLAG_SIGNED
            et.setText(budget.toString())

            MaterialAlertDialogBuilder(requireContext(),R.style.AlertDialogTheme)
                .setTitle("Change budget")
                .setView(et)
                .setPositiveButton("Set",DialogInterface.OnClickListener{dialogInterface, i ->
                    incomePref?.edit()
                        ?.putFloat(getString(R.string.budget_pref_key),et.text.toString().toFloat())
                        ?.apply()
                    setupIncomeBudget()
                })
                .setNegativeButton("Cancel",DialogInterface.OnClickListener{dialogInterface, i ->
                    dialogInterface.dismiss()
                }).show()
        }
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