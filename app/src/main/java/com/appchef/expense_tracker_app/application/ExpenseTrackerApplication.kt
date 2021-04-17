package com.appchef.expense_tracker_app.application

import android.app.Application
import com.appchef.expense_tracker_app.model.database.ExpenseRecordRepository
import com.appchef.expense_tracker_app.model.database.ExpenseRecordRoomDatabase

class ExpenseTrackerApplication : Application() {

    // To get the context
    private val database by lazy {
        // Setting up the database.
        ExpenseRecordRoomDatabase.getDatabase((this@ExpenseTrackerApplication))
    }

    val repository by lazy {
        ExpenseRecordRepository(database.expenseRecordDao())
    }
}