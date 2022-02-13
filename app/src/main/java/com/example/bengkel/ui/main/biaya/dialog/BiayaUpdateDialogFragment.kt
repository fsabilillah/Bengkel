package com.example.bengkel.ui.main.biaya.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.databinding.FragmentBiayaUpdateBinding
import com.example.bengkel.databinding.FragmentPelangganUpdateBinding
import com.example.bengkel.ui.main.biaya.BiayaViewModel
import com.example.bengkel.ui.main.pelanggan.PelangganViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class BiayaUpdateDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBiayaUpdateBinding? = null
    private val binding get() = _binding!!
    private val viewModel : BiayaViewModel by viewModel()
    private val args: BiayaUpdateDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBiayaUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = args.biaya
        with(binding){
            tfBiaya.editText?.setText(user.biayaTambahan)

            btnUpdate.setOnClickListener {
                if (checkAllField()){
                    viewModel.updateBiaya(
                        user.idBiayaTambahan,
                        tfBiaya.editText?.text.toString()
                    ).observe(viewLifecycleOwner) {
                        when (it) {
                            is Resource.Loading -> {
                                binding.btnUpdate.isEnabled = false
                                binding.btnUpdate.text = "Loading..."
                            }
                            is Resource.Success -> {
                                binding.btnUpdate.isEnabled = false
                                binding.btnUpdate.text = "Loading..."
                                FancyToast.makeText(
                                    context,
                                    "update has successful",
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
                                binding.btnUpdate.isEnabled = true
                                binding.btnUpdate.text = "Update"
                                FancyToast.makeText(
                                    context,
                                    "update has failed",
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