package com.appchef.expense_tracker_app.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.appchef.expense_tracker_app.databinding.TopTransactionLayoutBinding
import com.appchef.expense_tracker_app.model.entitie.ExpenseRecord

class TransactionAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    private var transactionList: List<ExpenseRecord> = listOf()

    inner class ViewHolder(view: TopTransactionLayoutBinding) : RecyclerView.ViewHolder(view.root) {

        // Views
        val transactionStatusIv = view.transactionStatusIv
        val transactionStatusTv = view.transactionStatusTv
        val transactionAmount = view.transactionStatusAmountTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TopTransactionLayoutBinding.inflate(
            LayoutInflater.from(fragment.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    // Create a function that will have the updated list of dishes that we will bind it to the adapter class.
    // Todo Change the Models as well.
    fun transactionList(list: List<ExpenseRecord>) {
        transactionList = list
        notifyDataSetChanged()
    }
}