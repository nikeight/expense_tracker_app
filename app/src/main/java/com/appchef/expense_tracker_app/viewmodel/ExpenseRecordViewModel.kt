package com.appchef.expense_tracker_app.viewmodel

import androidx.lifecycle.*
import com.appchef.expense_tracker_app.model.database.ExpenseRecordRepository
import com.appchef.expense_tracker_app.model.entitie.ExpenseRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ExpenseRecordViewModel(private val repository: ExpenseRecordRepository) : ViewModel() {

    fun insert(expenseRecord: ExpenseRecord) = viewModelScope.launch {
        repository.insertExpenseRecordData(expenseRecord)
    }

    // To get the list.
    val expenseDetails: LiveData<List<ExpenseRecord>> = repository.expenseDetails.asLiveData()
}

class ExpenseTrackerViewModelFactory(private val repository: ExpenseRecordRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseRecordViewModel::class.java)) {
            return ExpenseRecordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkown ViewModel Class")
    }
}