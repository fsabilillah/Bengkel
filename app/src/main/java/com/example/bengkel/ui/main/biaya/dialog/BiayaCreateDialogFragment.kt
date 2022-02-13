package com.example.bengkel.ui.main.biaya.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.databinding.FragmentBiayaCreateBinding
import com.example.bengkel.databinding.FragmentPelangganCreateBinding
import com.example.bengkel.ui.main.biaya.BiayaViewModel
import com.example.bengkel.ui.main.pelanggan.PelangganViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class BiayaCreateDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBiayaCreateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BiayaViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBiayaCreateBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            btnBiaya.setOnClickListener {
                if (checkAllField()){
                    viewModel.createBiaya(
                        tfBiaya.editText?.text.toString()
                    ).observe(viewLifecycleOwner) {
                        when (it) {
                            is Resource.Loading -> {
                                binding.btnBiaya.isEnabled = false
                                binding.btnBiaya.text = "Loading..."
                            }
                            is Resource.Success -> {
                                binding.btnBiaya.isEnabled = false
                                binding.btnBiaya.text = "Loading..."
                                FancyToast.makeText(
                                    context,
                                    "create user has successful",
                                    FancyToast.LENGTH_SHORT,
                                    FancyToast.SUCCESS,
                                    false
                                ).show()

                                Navigation.findNavController(
                                    requireActivity(),
                                    R.id.nav_host_fragment_content_main
                                ).navigate(
                                    R.id.nav_biaya_add
                                )
                            }
                            is Resource.Error -> {
                                binding.btnBiaya.isEnabled = true
                                binding.btnBiaya.text = "Tambah User"
                                FancyToast.makeText(
                                    context,
                                    "create user has failed",
                                    FancyToast.LENGTH_SHORT,
                                    FancyToast.ERROR,
                                    false
                                ).show()

                                Navigation.findNavController(
                                    requireActivity(),
                                    R.id.nav_host_fragment_content_main
                                ).navigate(
                                    R.id.nav_biaya_add
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun checkAllField() : Boolean {
        if (binding.tfBiaya.editText?.text?.isEmpty() == true){
            binding.tfBiaya.error = "This field is required"
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}