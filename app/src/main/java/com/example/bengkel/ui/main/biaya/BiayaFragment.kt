package com.example.bengkel.ui.main.biaya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.databinding.FragmentBiayaBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class BiayaFragment : Fragment() {

    private val viewModel : BiayaViewModel by viewModel()
    private var _binding: FragmentBiayaBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBiayaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adminAdapter = BiayaAdapter()
        with(binding.rvList) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            setHasFixedSize(true)
            adapter = adminAdapter
        }

        adminAdapter.onUpdateItemClick = {
            val detail = BiayaFragmentDirections.actionNavBiayaAddToNavBiayaAddUpdate(it)
            view.findNavController().navigate(detail)
        }
        adminAdapter.onReadItemClick = {
            val detail = BiayaFragmentDirections.actionNavBiayaAddToNavBiayaAddRead(it)
            view.findNavController().navigate(detail)
        }
        adminAdapter.onDeleteItemClick = {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete")
                .setMessage("are you sure to delete?")
                .setNegativeButton("cancel") { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("delete") { dialog, _ ->
                    //delete action
                    viewModel.deleteBiaya(it.idBiayaTambahan).observe(viewLifecycleOwner) {
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
                                ).navigate(R.id.nav_biaya_add)
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
                                ).navigate(R.id.nav_biaya_add)
                            }
                        }
                    }

                }.show()
        }

        getListUser(adminAdapter)
        binding.btnAddUser.setOnClickListener {
            val detail = BiayaFragmentDirections.actionNavBiayaAddToNavBiayaAddCreate()
            view.findNavController().navigate(detail)
        }
    }

    private fun getListUser(adminAdapter: BiayaAdapter) {
        viewModel.biaya.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbLoading.visibility = View.GONE
                    it.data?.let { user -> adminAdapter.setData(user) }
                }
                is Resource.Error -> {
                    binding.pbLoading.visibility = View.GONE
                }
            }
        }
    }

}