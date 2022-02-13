package com.example.bengkel.ui.main.pelanggan.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.databinding.FragmentPelangganUpdateBinding
import com.example.bengkel.ui.main.pelanggan.PelangganViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class PelangganUpdateDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentPelangganUpdateBinding? = null
    private val binding get() = _binding!!
    private val viewModel : PelangganViewModel by viewModel()
    private val args: PelangganUpdateDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPelangganUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = args.pelanggan
        with(binding){
            tfName.editText?.setText(user.namaPelanggan)
            tfAddress.editText?.setText(user.alamatPelanggan)
            tfHp.editText?.setText(user.noHpPelanggan)

            btnUpdate.setOnClickListener {
                if (checkAllField()){
                    viewModel.updateUser(
                        user.idPelanggan.toString(),
                        tfName.editText?.text.toString(),
                        tfHp.editText?.text.toString(),
                        tfAddress.editText?.text.toString(),
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
                                    R.id.nav_pelanggan
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