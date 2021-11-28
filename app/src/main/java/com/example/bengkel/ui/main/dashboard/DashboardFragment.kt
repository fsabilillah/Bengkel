package com.example.bengkel.ui.main.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bengkel.databinding.FragmentDashboardBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val viewModel : DashboardViewModel by viewModel()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

}