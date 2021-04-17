package com.appchef.expense_tracker_app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.appchef.expense_tracker_app.databinding.FragmentHomeBinding
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

        setUpBankAdapter()
        setUpTransactionAdapter()
        // Todo @Anubhav sending the List item here thus changing the models.
    }

    private fun setUpTransactionAdapter() {
        // setting the layout.
        homeBinding.transactionRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        // Setting the adapter
        val transactionAdapter : TransactionAdapter = TransactionAdapter(this@HomeFragment)

        // Setting the adapter
        homeBinding.banKRecyclerView.adapter = transactionAdapter
    }

    private fun setUpBankAdapter() {
        // setting the layout.
        homeBinding.banKRecyclerView.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        // Setting the adapter
        val bankAdapter : BankTransactionAdapter =BankTransactionAdapter(this@HomeFragment)

        // Setting the adapter
        homeBinding.banKRecyclerView.adapter = bankAdapter
    }
}