package com.example.bengkel.ui.main.service.usage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.databinding.FragmentUsageBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class UsageFragment : Fragment() {

    private val viewModel: UsageViewModel by viewModel()
    private val args: UsageFragmentArgs by navArgs()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usageAdapter = UsageAdapter()
        with(binding.rvList){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = usageAdapter
        }

        usageAdapter.onUpdateItemClick = {
            val detail = UsageFragmentDirections.actionNavUsageToNavUsageUpdate(it, args.idservice)
            view.findNavController().navigate(detail)
        }

        usageAdapter.onDeleteItemClick = {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete")
                .setMessage("are you sure to delete?")
                .setNegativeButton("cancel") { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("delete") { dialog, _ ->
                    //delete action
                    viewModel.deleteUsage(args.idservice, it.idPemakaian).observe(viewLifecycleOwner) {
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
            val add = UsageFragmentDirections.actionNavUsageToNavUsageCreate(args.idservice)
            it.findNavController().navigate(add)
        }

        getListUsage(usageAdapter)
    }

    private fun getListUsage(usageAdapter: UsageAdapter) {
        viewModel.usage(args.idservice).observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbLoading.visibility = View.GONE
                    it.data?.let { service -> usageAdapter.setData(service) }
                }
                is Resource.Error -> {
                    binding.pbLoading.visibility = View.GONE
                }
            }
        }
    }
}