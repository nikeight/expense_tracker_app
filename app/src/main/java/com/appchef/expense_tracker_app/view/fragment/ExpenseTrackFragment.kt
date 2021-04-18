package com.appchef.expense_tracker_app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.appchef.expense_tracker_app.application.ExpenseTrackerApplication
import com.appchef.expense_tracker_app.databinding.FragmentExpenseTrackBinding
import com.appchef.expense_tracker_app.viewmodel.ExpenseRecordViewModel
import com.appchef.expense_tracker_app.viewmodel.ExpenseTrackerViewModelFactory

class ExpenseTrackFragment : Fragment() {

    // binding object.
    private lateinit var expenseTrackBinding: FragmentExpenseTrackBinding

    // View Model Object.
    private val expenseDetailViewModel: ExpenseRecordViewModel by viewModels {
        ExpenseTrackerViewModelFactory((requireActivity().application as ExpenseTrackerApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        expenseTrackBinding = FragmentExpenseTrackBinding.inflate(inflater, container, false)
        return expenseTrackBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

