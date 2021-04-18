package com.appchef.expense_tracker_app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.appchef.expense_tracker_app.databinding.FragmentHomeBinding
import com.appchef.expense_tracker_app.model.entitie.ExpenseRecord
import com.appchef.expense_tracker_app.view.adapter.BankTransactionAdapter
import com.appchef.expense_tracker_app.view.adapter.TransactionAdapter

class HomeFragment : Fragment() {

    // Bindings.
    private lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding =
            FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpDetailCard()
//        setUpBankAdapter()
        setUpTransactionAdapter()
        // Todo @Anubhav sending the List item here thus changing the models.
    }

    private fun setUpDetailCard() {
        homeBinding.statusTv.text = "Low on Money."
        homeBinding.amountTv.text = "432/-"

    }

    private fun setUpTransactionAdapter() {
        // setting the layout.
        homeBinding.transactionRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        // Setting the adapter
        val transactionAdapter : TransactionAdapter = TransactionAdapter(this@HomeFragment)

        // Setting the adapter
//        homeBinding.banKRecyclerView.adapter = transactionAdapter
    }

//    private fun setUpBankAdapter() {
//        // setting the layout.
//        homeBinding.banKRecyclerView.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
//
//        // Setting the adapter
//        val bankAdapter = BankTransactionAdapter(this@HomeFragment)
//
//        // Mock list
//        val list = ArrayList<ExpenseRecord>()
//        val obj1 = ExpenseRecord("555","31/04/21,","Bills","Google Pay",10)
//        val obj2 = ExpenseRecord("444","30/04/21","Bills","Phone Pe",11)
//
//        list.add(obj1)
//        list.add(obj2)
//
//        // Setting the adapter
//        homeBinding.banKRecyclerView.adapter = bankAdapter
//
//        // Sending the list
//        bankAdapter.bankList(list)
//        bankAdapter.notifyDataSetChanged()
//
//        Toast.makeText(requireContext(),"Yaha tk aya kya?", Toast.LENGTH_SHORT).show()
//    }
}