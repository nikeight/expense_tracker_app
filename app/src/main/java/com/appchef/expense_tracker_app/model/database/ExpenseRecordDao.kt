package com.appchef.expense_tracker_app.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.appchef.expense_tracker_app.model.entitie.ExpenseRecord

@Dao
interface ExpenseRecordDao {

    @Insert
    suspend fun insertExpenseRecords(expenseRecord: ExpenseRecord)

    // TO get the Data from the Room Database.
    @Query("SELECT * FROM EXPENSE_RECORD_TABLE ORDER BY ID")
    fun getExpenseList(): kotlinx.coroutines.flow.Flow<List<ExpenseRecord>>

}