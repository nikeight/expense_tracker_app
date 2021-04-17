package com.appchef.expense_tracker_app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appchef.expense_tracker_app.databinding.FragmentAddExpensesBinding

class AddExpensesFragment : Fragment() {

    // Binding Objects.
    private lateinit var addExpensesBinding: FragmentAddExpensesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addExpensesBinding = FragmentAddExpensesBinding.inflate(inflater, container, false)
        return addExpensesBinding.root
    }
}