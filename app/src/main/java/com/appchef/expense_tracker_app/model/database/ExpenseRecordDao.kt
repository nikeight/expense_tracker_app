package com.appchef.expense_tracker_app.model.database

import androidx.room.Dao
import androidx.room.Insert
import com.appchef.expense_tracker_app.model.entitie.ExpenseRecord

@Dao
interface ExpenseRecordDao {

    @Insert
    suspend fun insertExpenseRecords(expenseRecord: ExpenseRecord)

}