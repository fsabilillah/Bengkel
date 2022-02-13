package com.example.bengkel.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bengkel.data.Resource
import com.example.bengkel.databinding.FragmentHistoryBinding
import com.example.bengkel.ui.main.service.ServiceAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {


    private val viewModel: HistoryViewModel by viewModel()
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serviceAdapter = ServiceAdapter("history")
        with(binding.rvList){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = serviceAdapter
        }

        viewModel.history.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                    binding.lyEmpty.visibility = View.GONE
                    binding.rvList.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.lyEmpty.visibility = View.GONE
                    binding.rvList.visibility = View.VISIBLE
                    it.data?.let { service -> serviceAdapter.setData(service) }
                }
                is Resource.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.rvList.visibility = View.GONE
                    binding.lyEmpty.visibility = View.VISIBLE
                }
            }
        }

        binding.tfSearch.setEndIconOnClickListener { _ ->
            viewModel.searchService(binding.tfSearch.editText?.text.toString().uppercase()).observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        binding.pbLoading.visibility = View.VISIBLE
                        binding.lyEmpty.visibility = View.GONE
                        binding.rvList.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.pbLoading.visibility = View.GONE
                        binding.lyEmpty.visibility = View.GONE
                        binding.rvList.visibility = View.VISIBLE
                        it.data?.let { s -> serviceAdapter.setData(s) }
                    }
                    is Resource.Error -> {
                        binding.pbLoading.visibility = View.GONE
                        binding.rvList.visibility = View.GONE
                        binding.lyEmpty.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

}