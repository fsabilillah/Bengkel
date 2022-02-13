package com.example.bengkel.ui.main.pelanggan

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
import com.example.bengkel.databinding.FragmentPelangganBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class PelangganFragment : Fragment() {

    private val viewModel: PelangganViewModel by viewModel()
    private var _binding: FragmentPelangganBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPelangganBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pelangganAdapter = PelangganAdapter()
        with(binding.rvList) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            setHasFixedSize(true)
            adapter = pelangganAdapter
        }

        pelangganAdapter.onUpdateItemClick = {
            val detail = PelangganFragmentDirections.actionNavPelangganToNavPelangganUpdate(it)
            view.findNavController().navigate(detail)
        }
        pelangganAdapter.onReadItemClick = {
            val detail = PelangganFragmentDirections.actionNavPelangganToNavPelangganRead(it)
            view.findNavController().navigate(detail)
        }

        pelangganAdapter.onDeleteItemClick = {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete")
                .setMessage("are you sure to delete?")
                .setNegativeButton("cancel") { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("delete") { dialog, _ ->
                    //delete action
                    viewModel.deleteUser(it.idPelanggan.toString()).observe(viewLifecycleOwner) {
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
                                ).navigate(R.id.nav_pelanggan)
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
                                ).navigate(R.id.nav_pelanggan)
                            }
                        }
                    }

                }.show()
        }

        getListUser(pelangganAdapter)
        binding.btnAddUser.setOnClickListener {
            val detail = PelangganFragmentDirections.actionNavPelangganToNavPelangganCreate()
            view.findNavController().navigate(detail)
        }
    }

    private fun getListUser(pelangganAdapter: PelangganAdapter) {
        viewModel.user.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbLoading.visibility = View.GONE
                    it.data?.let { user -> pelangganAdapter.setData(user) }
                }
                is Resource.Error -> {
                    binding.pbLoading.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}