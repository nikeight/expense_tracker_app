package com.appchef.expense_tracker_app.model.database

import androidx.room.Insert
import com.appchef.expense_tracker_app.model.entitie.ExpenseRecord

interface ExpenseRecordDao {

    @Insert
    suspend fun insertExpenseRecords(expenseRecord: ExpenseRecord)

}