package com.appchef.expense_tracker_app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appchef.expense_tracker_app.databinding.FragmentGroupChatBinding

class GroupChatFragment : Fragment() {

    // Binding Object.
    private lateinit var groupChatBinding: FragmentGroupChatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupChatBinding = FragmentGroupChatBinding.inflate(inflater,container,false)
        return groupChatBinding.root
    }
}