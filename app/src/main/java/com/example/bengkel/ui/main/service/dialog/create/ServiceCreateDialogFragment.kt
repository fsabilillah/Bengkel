package com.example.bengkel.ui.main.service.dialog.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bengkel.databinding.FragmentServiceCreateBinding
import com.example.bengkel.ui.main.service.ServiceViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

import android.app.DatePickerDialog
import androidx.navigation.Navigation
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.data.source.remote.request.ServiceCreateRequest
import com.google.android.material.textfield.TextInputLayout
import com.shashank.sony.fancytoastlib.FancyToast
import java.text.SimpleDateFormat


class ServiceCreateDialogFragment : BottomSheetDialogFragment() {

    private val viewModel : ServiceViewModel by viewModel()

    private var _binding: FragmentServiceCreateBinding? = null
    private val binding get() = _binding!!
    private var idUser = mutableListOf<String>()
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServiceCreateBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            tfTglServ.editText?.setOnClickListener {
                DatePickerDialog(
                    requireActivity(),
                    dateListener(tfTglServ),
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            tfTgSel.editText?.setOnClickListener {
                DatePickerDialog(
                    requireActivity(),
                    dateListener(tfTgSel),
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }


            viewModel.user.observe(viewLifecycleOwner, {
                idUser.add(it.id_users.toString())
            })

            btnAddService.setOnClickListener {
                if (checkAllField()){
                    viewModel.create(ServiceCreateRequest(
                        idUser[0],
                        tfNamaPel.editText?.text.toString(),
                        tfNamaBar.editText?.text.toString(),
                        tfNoTelp.editText?.text.toString(),
                        tfTglServ.editText?.text.toString(),
                        tfTgSel.editText?.text.toString(),
                        tfHarga.editText?.text.toString()
                    )).observe(viewLifecycleOwner, {
                        when(it){
                            is Resource.Loading -> {
                                btnAddService.isEnabled = false
                                btnAddService.text = "Loading..."
                            }
                            is Resource.Success -> {
                                btnAddService.isEnabled = false
                                btnAddService.text = "Loading..."
                                FancyToast.makeText(context, "create service has successful", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()

                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(
                                    R.id.nav_cadang)
                            }
                            is Resource.Error -> {
                                btnAddService.isEnabled = true
                                btnAddService.text = "Tambah Service"
                                FancyToast.makeText(context, "create service has failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()

                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(
                                    R.id.nav_cadang)
                            }
                        }
                    })
                }
            }
        }
    }

    private fun dateListener(tfTglServ: TextInputLayout): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateinView(tfTglServ)
        }
    }

    private fun updateDateinView(tfTglServ: TextInputLayout) {
        val newFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(newFormat, Locale.US)
        tfTglServ.editText?.setText(sdf.format(calendar.time))
    }

    private fun checkAllField() : Boolean {
        if (binding.tfNamaPel.editText?.text?.isEmpty() == true){
            binding.tfNamaPel.error = "This field is required"
            return false
        }
        if (binding.tfNamaBar.editText?.text?.isEmpty() == true){
            binding.tfNamaBar.error = "This field is required"
            return false
        }
        if (binding.tfNoTelp.editText?.text?.isEmpty() == true) {
            binding.tfNoTelp.error = "This field is required"
            return false
        }
        if (binding.tfTglServ.editText?.text?.isEmpty() == true) {
            binding.tfTglServ.error = "This field is required"
            return false
        }
        if (binding.tfTgSel.editText?.text?.isEmpty() == true) {
            binding.tfTgSel.error = "This field is required"
            return false
        }
        if (binding.tfHarga.editText?.text?.isEmpty() == true) {
            binding.tfHarga.error = "This field is required"
            return false
        }

        return true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}