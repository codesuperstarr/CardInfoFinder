package com.mayokunadeniyi.cardinfofinder.ui.inputfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mayokunadeniyi.cardinfofinder.R
import com.mayokunadeniyi.cardinfofinder.databinding.InputCardDetailsBinding
import com.mayokunadeniyi.cardinfofinder.ui.inputfragment.InputFragmentDirections.Companion.actionInputFragmentToResultFragment

/**
 * Created by Mayokun Adeniyi on 23/07/2020.
 */

class InputFragment : Fragment() {

    private lateinit var binding: InputCardDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InputCardDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputFragSearchBtn.setOnClickListener {
            if (binding.cardNumberEditText.text.isNullOrBlank()) {
                val shake = AnimationUtils.loadAnimation(requireContext(), R.anim.shake)
                binding.cardNumberEditText.startAnimation(shake)
            } else {
                binding.cardNumberEditText.text?.toString()?.let {
                    val cardNumber = it.toInt()
                    val action = actionInputFragmentToResultFragment(cardNumber)
                    findNavController().navigate(action)
                }
            }
        }
    }
}