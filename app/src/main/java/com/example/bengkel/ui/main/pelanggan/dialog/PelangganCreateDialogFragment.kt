package com.example.bengkel.ui.main.pelanggan.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.databinding.FragmentPelangganCreateBinding
import com.example.bengkel.ui.main.pelanggan.PelangganViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class PelangganCreateDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentPelangganCreateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PelangganViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPelangganCreateBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            btnAddUser.setOnClickListener {
                if (checkAllField()){
                    viewModel.createUser(
                        tfName.editText?.text.toString(),
                        tfHp.editText?.text.toString(),
                        tfAddress.editText?.text.toString(),
                    ).observe(viewLifecycleOwner) {
                        when (it) {
                            is Resource.Loading -> {
                                binding.btnAddUser.isEnabled = false
                                binding.btnAddUser.text = "Loading..."
                            }
                            is Resource.Success -> {
                                binding.btnAddUser.isEnabled = false
                                binding.btnAddUser.text = "Loading..."
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
                                    R.id.nav_pelanggan
                                )
                            }
                            is Resource.Error -> {
                                binding.btnAddUser.isEnabled = true
                                binding.btnAddUser.text = "Tambah User"
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
                                    R.id.nav_pelanggan
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun checkAllField() : Boolean {
        if (binding.tfName.editText?.text?.isEmpty() == true){
            binding.tfName.error = "This field is required"
            return false
        }
        if (binding.tfAddress.editText?.text?.isEmpty() == true) {
            binding.tfAddress.error = "This field is required"
            return false
        }
        if (binding.tfHp.editText?.text?.isEmpty() == true) {
            binding.tfHp.error = "This field is required"
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}