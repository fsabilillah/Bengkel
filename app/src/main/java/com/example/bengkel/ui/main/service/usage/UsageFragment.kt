package com.example.bengkel.ui.main.service.usage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bengkel.databinding.FragmentUsageBinding
import org.koin.android.viewmodel.ext.android.viewModel

class UsageFragment : Fragment() {

    private val viewModel: UsageViewModel by viewModel()

    private var _binding: FragmentUsageBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsageBinding.inflate(inflater, container, false)
        return binding.root
    }

}