package com.example.bengkel.ui.main.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.databinding.FragmentServiceBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class ServiceFragment : Fragment() {

    private val viewModel : ServiceViewModel by viewModel()
    private var _binding: FragmentServiceBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serviceAdapter = ServiceAdapter("service")
        with(binding.rvList){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = serviceAdapter
        }

        serviceAdapter.onUpdateItemClick = {
            val detail = ServiceFragmentDirections.actionNavServiceToNavServiceUpdate(it)
            view.findNavController().navigate(detail)
        }

        serviceAdapter.onDeleteItemClick = {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete")
                .setMessage("are you sure to delete?")
                .setNegativeButton("cancel") { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("delete") { dialog, _ ->
                    //delete action
                    viewModel.delete(it.idService).observe(viewLifecycleOwner) {
                        dialog.dismiss()
                        when (it) {
                            is Resource.Loading -> {
                                binding.pbLoading.visibility = View.VISIBLE
                            }
                            is Resource.Success -> {
                                binding.pbLoading.visibility = View.GONE
                                FancyToast.makeText(
                                    context,
                                    "delete has successful",
                                    FancyToast.LENGTH_SHORT,
                                    FancyToast.SUCCESS,
                                    false
                                ).show()

                                Navigation.findNavController(
                                    requireActivity(),
                                    R.id.nav_host_fragment_content_main
                                ).navigate(
                                    R.id.nav_service
                                )
                            }
                            is Resource.Error -> {
                                binding.pbLoading.visibility = View.GONE
                                FancyToast.makeText(
                                    context,
                                    "delete has failed",
                                    FancyToast.LENGTH_SHORT,
                                    FancyToast.ERROR,
                                    false
                                ).show()

                                Navigation.findNavController(
                                    requireActivity(),
                                    R.id.nav_host_fragment_content_main
                                ).navigate(
                                    R.id.nav_service
                                )
                            }
                        }
                    }

                }.show()
        }

        binding.btnAddService.setOnClickListener {
            val detail = ServiceFragmentDirections.actionNavServiceToNavServiceCreate()
            view.findNavController().navigate(detail)
        }
        getListService(serviceAdapter)
        getSearchData(serviceAdapter)
    }

    private fun getSearchData(serviceAdapter: ServiceAdapter) {
        binding.tfSearch.setEndIconOnClickListener { _ ->
            viewModel.searchService(binding.tfSearch.editText.toString()).observe(viewLifecycleOwner, {
                when(it){
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
            })
        }
    }

    private fun getListService(serviceAdapter: ServiceAdapter) {
        viewModel.service.observe(viewLifecycleOwner, {
            when(it){
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
        })
    }

}