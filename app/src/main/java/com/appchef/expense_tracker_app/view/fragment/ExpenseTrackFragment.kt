package com.appchef.expense_tracker_app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appchef.expense_tracker_app.databinding.FragmentExpenseTrackBinding

class ExpenseTrackFragment : Fragment() {

    // binding object.
    private lateinit var expenseTrackBinding: FragmentExpenseTrackBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        expenseTrackBinding = FragmentExpenseTrackBinding.inflate(inflater, container, false)
        return expenseTrackBinding.root
    }
}