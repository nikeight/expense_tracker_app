package com.appchef.expense_tracker_app.view.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.appchef.expense_tracker_app.R
import com.appchef.expense_tracker_app.application.ExpenseTrackerApplication
import com.appchef.expense_tracker_app.databinding.FragmentAddExpensesBinding
import com.appchef.expense_tracker_app.model.entitie.ExpenseRecord
import com.appchef.expense_tracker_app.viewmodel.ExpenseRecordViewModel
import com.appchef.expense_tracker_app.viewmodel.ExpenseTrackerViewModelFactory

class AddExpensesFragment : Fragment(), View.OnClickListener {

    // Binding Objects.
    private lateinit var addExpensesBinding: FragmentAddExpensesBinding

    // Spinner View
    var selectedCategory: String = "Default"

    // View Model Object.
    private val addExpenseViewModel: ExpenseRecordViewModel by viewModels {
        ExpenseTrackerViewModelFactory((requireActivity().application as ExpenseTrackerApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addExpensesBinding = FragmentAddExpensesBinding.inflate(inflater, container, false)
        return addExpensesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSpinner()

        // OnClick Events
        addExpensesBinding.addExpenseBtn.setOnClickListener(this)
    }

    private fun setSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            addExpensesBinding.categorySpinner.adapter = adapter
        }

        addExpensesBinding.categorySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedCategory = parent?.getItemAtPosition(position).toString()
                    Log.i(
                        "Selected Item",
                        "$selectedCategory"
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
    }

    override fun onClick(v: View?) {

        val expenseAmount = addExpensesBinding.etExpenseAmount.text.toString()
        val title = addExpensesBinding.etTitle.text.toString().trim() { it <= ' ' }
        val time = System.currentTimeMillis().toString()

        Log.i(
            "Information Tag", "Amount: $expenseAmount " +
                    "Title: $title" + " time:  $time"
        )

        when {
            TextUtils.isEmpty(expenseAmount) -> {
                Toast.makeText(requireContext(), "Enter the amount first", Toast.LENGTH_SHORT)
                    .show()
            }
            // Check for the title as well
            TextUtils.isEmpty(title) -> {
                Toast.makeText(requireContext(), "Enter the Title also.", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                // We can add the data to the database.
                val expenseRecord = ExpenseRecord(
                    expenseAmount,
                    time,
                    selectedCategory,
                    title
                )

                // Inserting the data here.
                addExpenseViewModel.insert(expenseRecord)
                Toast.makeText(
                    requireContext(),
                    "Expense added successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

