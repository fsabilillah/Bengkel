package com.example.bengkel.ui.main.cadang

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.databinding.FragmentDashboardBinding
import com.example.bengkel.databinding.FragmentSukuCadangBinding
import com.example.bengkel.ui.main.admin.AdminFragmentDirections
import com.example.bengkel.ui.main.dashboard.DashboardViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class SukuCadangFragment : Fragment() {

    private val viewModel : SukuCadangViewModel by viewModel()
    private var _binding: FragmentSukuCadangBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSukuCadangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sukuCadangAdapter = SukuCadangAdapter()
        with(binding.rvList){
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            setHasFixedSize(true)
            adapter = sukuCadangAdapter
        }

        sukuCadangAdapter.onUpdateItemClick = {
            val detail = SukuCadangFragmentDirections.actionNavCadangToNavCadangUpdate(it)
            view.findNavController().navigate(detail)
        }

        sukuCadangAdapter.onDeleteItemClick = {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete")
                .setMessage("are you sure to delete?")
                .setNegativeButton("cancel") { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("delete") { dialog, _ ->
                    //delete action
                    viewModel.deleteSukuCadang(it.idSukuCadang.toInt()).observe(viewLifecycleOwner, {
                        dialog.dismiss()
                        when(it){
                            is Resource.Loading -> {
                                binding.pbLoading.visibility = View.VISIBLE
                            }
                            is Resource.Success -> {
                                binding.pbLoading.visibility = View.GONE
                                FancyToast.makeText(context, "delete has successful", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()

                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.nav_cadang)
                            }
                            is Resource.Error -> {
                                binding.pbLoading.visibility = View.GONE
                                FancyToast.makeText(context, "delete has failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()

                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.nav_cadang)
                            }
                        }
                    })

                }.show()
        }

        binding.btnAddCadang.setOnClickListener {
            val detail = SukuCadangFragmentDirections.actionNavCadangToNavCadangCreate()
            view.findNavController().navigate(detail)
        }

        getListSukuCadang(sukuCadangAdapter)
    }

    private fun getListSukuCadang(sukuCadangAdapter: SukuCadangAdapter) {
        viewModel.getSukuCadang.observe(viewLifecycleOwner, {
            when(it){
                is Resource.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbLoading.visibility = View.GONE
                    it.data?.let { sukuCadang -> sukuCadangAdapter.setData(sukuCadang) }
                }
                is Resource.Error -> {
                    binding.pbLoading.visibility = View.GONE
                }
            }
        })
    }

}