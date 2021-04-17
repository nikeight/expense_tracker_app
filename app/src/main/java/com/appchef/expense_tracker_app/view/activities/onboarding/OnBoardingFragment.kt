package com.appchef.expense_tracker_app.view.activities.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.appchef.expense_tracker_app.databinding.FragmentOnBoardingBinding

/**
 * @param imgDrawableId : the drawable of image in the fragment
 * @param header : a heading string to be shown
 * @param description : a quick description about the feature
 */
class OnBoardingFragment(
    private val imgDrawableId: Int,
    private val header: String,
    private val description: String
) : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null

    // This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //view binding
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)

        //setting details in layout
        binding.image.setImageDrawable(ContextCompat.getDrawable(requireContext(), imgDrawableId))
        binding.header.text = header
        binding.description.text = description

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}