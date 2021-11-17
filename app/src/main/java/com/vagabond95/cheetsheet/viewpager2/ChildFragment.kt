package com.vagabond95.cheetsheet.viewpager2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vagabond95.cheetsheet.databinding.FragmentChildeBinding

class ChildFragment : Fragment() {
    private var position: Int = 0

    private var _binding: FragmentChildeBinding? = null
    private val binding: FragmentChildeBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(POSITION_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChildeBinding.inflate(inflater, container, false)
        binding.position = position.toString()
        return binding.root
    }

    companion object {
       const val POSITION_ARG = "POSITION"

        fun newInstance(position: Int) =
            ChildFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION_ARG, position)
                }
            }
    }
}