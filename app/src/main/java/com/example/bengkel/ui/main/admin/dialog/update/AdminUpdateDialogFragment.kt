package com.example.bengkel.ui.main.admin.dialog.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.data.source.remote.request.UserRequest
import com.example.bengkel.databinding.FragmentAdminCreateBinding
import com.example.bengkel.databinding.FragmentAdminUpdateBinding
import com.example.bengkel.ui.main.admin.AdminFragmentDirections
import com.example.bengkel.ui.main.admin.dialog.read.AdminReadDialogFragmentArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class AdminUpdateDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAdminUpdateBinding? = null
    private val binding get() = _binding!!
    private val viewModel : AdminUpdateDialogViewModel by viewModel()
    private val args: AdminUpdateDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = args.user
        with(binding){
            tfUsername.editText?.setText(user.username)
            tfPassword.editText?.setText(user.password)
            tfName.editText?.setText(user.nama)
            tfEmail.editText?.setText(user.email)
            tfAddress.editText?.setText(user.alamat)
            tfHakAkses.editText?.setText(user.hakAkses)
            tfHp.editText?.setText(user.noHp)

            btnUpdate.setOnClickListener {
                if (checkAllField()){
                    viewModel.updateUser(
                        user.idUsers.toInt(),
                        UserRequest(
                           tfName.editText?.text.toString(),
                           tfUsername.editText?.text.toString(),
                           tfPassword.editText?.text.toString(),
                           tfEmail.editText?.text.toString(),
                           tfAddress.editText?.text.toString(),
                           tfHp.editText?.text.toString()
                        )
                    ).observe(viewLifecycleOwner, {
                        when(it){
                            is Resource.Loading -> {
                                binding.btnUpdate.isEnabled = false
                                binding.btnUpdate.text = "Loading..."
                            }
                            is Resource.Success -> {
                                binding.btnUpdate.isEnabled = false
                                binding.btnUpdate.text = "Loading..."
                                FancyToast.makeText(context, "update has successful", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()


                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(
                                    R.id.nav_admin)
                            }
                            is Resource.Error -> {
                                binding.btnUpdate.isEnabled = true
                                binding.btnUpdate.text = "Update"
                                FancyToast.makeText(context, "update has failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()


                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(
                                    R.id.nav_admin)
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
        if (binding.tfUsername.editText?.text?.isEmpty() == true){
            binding.tfUsername.error = "This field is required"
            return false
        }
        if (binding.tfPassword.editText?.text?.isEmpty() == true) {
            binding.tfPassword.error = "This field is required"
            return false
        }
        if (binding.tfAddress.editText?.text?.isEmpty() == true) {
            binding.tfAddress.error = "This field is required"
            return false
        }
        if (binding.tfEmail.editText?.text?.isEmpty() == true) {
            binding.tfEmail.error = "This field is required"
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