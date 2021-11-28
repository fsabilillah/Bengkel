package com.example.bengkel.ui.main.cadang.dialog.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.data.source.remote.request.SukuCadangRequest
import com.example.bengkel.databinding.FragmentSukuCadangCreateBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class SukuCadangCreateDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSukuCadangCreateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SukuCadangCreateViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSukuCadangCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            btnAddSukuCadang.setOnClickListener {
                if (checkAllField()){
                    viewModel.createSukuCadang(SukuCadangRequest(
                        tfName.editText?.text.toString(),
                        tfStock.editText?.text.toString().toInt(),
                        tfPrice.editText?.text.toString().toDouble()
                    )).observe(viewLifecycleOwner, {
                        when(it){
                            is Resource.Loading -> {
                                btnAddSukuCadang.isEnabled = false
                                btnAddSukuCadang.text = "Loading..."
                            }
                            is Resource.Success -> {
                                btnAddSukuCadang.isEnabled = false
                                btnAddSukuCadang.text = "Loading..."
                                FancyToast.makeText(context, "create parts has successful", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()

                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(
                                    R.id.nav_cadang)
                            }
                            is Resource.Error -> {
                                btnAddSukuCadang.isEnabled = true
                                btnAddSukuCadang.text = "Tambah Suku Cadang"
                                FancyToast.makeText(context, "create parts has failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()

                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(
                                    R.id.nav_cadang)
                            }
                        }
                    })
                }
            }
        }
    }

    private fun checkAllField() : Boolean {
        if (binding.tfName.editText?.text?.isEmpty() == true){
            binding.tfName.error = "This field is required"
            return false
        }
        if (binding.tfStock.editText?.text?.isEmpty() == true){
            binding.tfStock.error = "This field is required"
            return false
        }
        if (binding.tfPrice.editText?.text?.isEmpty() == true) {
            binding.tfPrice.error = "This field is required"
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}