package com.example.bengkel.ui.main.admin.dialog.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.Navigation
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.data.source.remote.request.UserRequest
import com.example.bengkel.databinding.FragmentAdminCreateBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class AdminCreateDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAdminCreateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AdminCreateDialogViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdminCreateBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            btnAddUser.setOnClickListener {
                if (checkAllField()){
                    viewModel.createUser(
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
                                binding.btnAddUser.isEnabled = false
                                binding.btnAddUser.text = "Loading..."
                            }
                            is Resource.Success -> {
                                binding.btnAddUser.isEnabled = false
                                binding.btnAddUser.text = "Loading..."
                                FancyToast.makeText(context, "create user has successful", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()

                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(
                                    R.id.nav_admin)
                            }
                            is Resource.Error -> {
                                binding.btnAddUser.isEnabled = true
                                binding.btnAddUser.text = "Tambah User"
                                FancyToast.makeText(context, "create user has failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()

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