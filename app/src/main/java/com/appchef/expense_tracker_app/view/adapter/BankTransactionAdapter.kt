package com.appchef.expense_tracker_app.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.appchef.expense_tracker_app.databinding.BankCardViewHomeBinding
import com.appchef.expense_tracker_app.model.entitie.ExpenseRecord

class BankTransactionAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<BankTransactionAdapter.ViewHolder>() {

    private var bankList: List<ExpenseRecord> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BankCardViewHomeBinding.inflate(
            LayoutInflater.from(fragment.context), parent, false
        )

        Toast.makeText(fragment.context,"Yaha tk aya kya?",Toast.LENGTH_SHORT).show()

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val list = bankList[position]

        holder.bankNameTv.text = list.title
        holder.totalAmountTv.text = list.amount
        holder.updatedDateTv.text = list.date
    }

    override fun getItemCount(): Int {
        return bankList.size
    }

    // Create a function that will have the updated list of dishes that we will bind it to the adapter class.
    // Todo Change the Models as well.
    fun bankList(list: List<ExpenseRecord>) {
        bankList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: BankCardViewHomeBinding) : RecyclerView.ViewHolder(view.root) {
        // holding the views.
        val bankNameTv = view.bankNameTv
        val totalAmountTv = view.amountTv
        val updatedDateTv = view.updatedDateTv
    }
}