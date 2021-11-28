package com.example.bengkel.ui.main.cadang.dialog.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.data.source.remote.request.UpdateSukuCadangRequest
import com.example.bengkel.databinding.FragmentSukuCadangUpdateBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class SukuCadangUpdateDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSukuCadangUpdateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SukuCadangUpdateViewModel by viewModel()
    private val args: SukuCadangUpdateDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSukuCadangUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sukuCadang = args.sukucadang
        with(binding){
            tfName.editText?.setText(sukuCadang.namaSukuCadang)
            tfStock.editText?.setText(sukuCadang.jumlahStok)
            tfPrice.editText?.setText(sukuCadang.harga)

            btnUpdateSukuCadang.setOnClickListener {
                if (checkAllField()){
                    viewModel.updateSukuCadang(
                        sukuCadang.idSukuCadang.toInt(),
                        UpdateSukuCadangRequest(
                            tfName.editText?.text.toString(),
                            tfStock.editText?.text.toString().toInt(),
                            tfPrice.editText?.text.toString().toDouble(),
                            tfAddition.editText?.text.toString().toInt()
                        )
                    ).observe(viewLifecycleOwner, {
                        when(it){
                            is Resource.Loading -> {
                                btnUpdateSukuCadang.isEnabled = false
                                btnUpdateSukuCadang.text = "Loading..."
                            }
                            is Resource.Success -> {
                                btnUpdateSukuCadang.isEnabled = false
                                btnUpdateSukuCadang.text = "Loading..."
                                FancyToast.makeText(context, "update has successful", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()


                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(
                                    R.id.nav_cadang)
                            }
                            is Resource.Error -> {
                                btnUpdateSukuCadang.isEnabled = true
                                btnUpdateSukuCadang.text = "Update Suku Cadang"
                                FancyToast.makeText(context, "update has failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()


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
        if (binding.tfAddition.editText?.text?.isEmpty() == true){
            binding.tfAddition.error = "This field is required"
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}