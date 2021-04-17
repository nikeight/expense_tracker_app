package com.appchef.expense_tracker_app.model.database

import androidx.annotation.WorkerThread
import com.appchef.expense_tracker_app.model.entitie.ExpenseRecord
import kotlinx.coroutines.flow.Flow

class ExpenseRecordRepository(private val expenseRecordDao: ExpenseRecordDao) {

    @WorkerThread
    suspend fun insertExpenseRecordData(expenseRecord: ExpenseRecord) {
        expenseRecordDao.insertExpenseRecords(expenseRecord)
    }

    // To get the Item from the Room Database.
    val expenseDetails: Flow<List<ExpenseRecord>> = expenseRecordDao.getExpenseList()
}