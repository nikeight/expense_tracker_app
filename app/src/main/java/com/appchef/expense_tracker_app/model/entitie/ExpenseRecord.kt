package com.appchef.expense_tracker_app.model.entitie

import android.icu.text.CaseMap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "expense_record_table")
data class ExpenseRecord(
    @ColumnInfo val amount: String,
    @ColumnInfo val date: String,
    @ColumnInfo val category: String,
    @ColumnInfo val title: String,

    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {

}