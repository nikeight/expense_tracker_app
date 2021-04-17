package com.appchef.expense_tracker_app.model.database

import androidx.annotation.WorkerThread
import com.appchef.expense_tracker_app.model.entitie.ExpenseRecord

class ExpenseRecordRepository(private val expenseRecordDao: ExpenseRecordDao) {

    @WorkerThread
    suspend fun insertExpenseRecordData(expenseRecord: ExpenseRecord){
        expenseRecordDao.insertExpenseRecords(expenseRecord)
    }


}