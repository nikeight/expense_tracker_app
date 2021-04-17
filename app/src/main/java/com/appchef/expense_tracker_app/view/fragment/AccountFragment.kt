package com.appchef.expense_tracker_app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appchef.expense_tracker_app.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    // Binding Objects.
    private lateinit var accountBinding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountBinding = FragmentAccountBinding.inflate(inflater,container,false)
        return accountBinding.root
    }
}