package com.appchef.expense_tracker_app.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appchef.expense_tracker_app.model.entitie.ExpenseRecord

@Database(entities = [ExpenseRecord::class], version = 1)
abstract class ExpenseRecordRoomDatabase : RoomDatabase() {

    abstract fun expenseRecordDao() : ExpenseRecordDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseRecordRoomDatabase? = null

        fun getDatabase(context: Context): ExpenseRecordRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseRecordRoomDatabase::class.java,
                    "exp_record_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}