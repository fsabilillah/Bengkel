package com.example.bengkel.ui.main.service.dialog.update

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.data.source.remote.request.ServiceUpdateRequest
import com.example.bengkel.databinding.FragmentServiceUpdateBinding
import com.example.bengkel.ui.main.service.ServiceViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class ServiceUpdateDialogFragment : BottomSheetDialogFragment() {

    private val viewModel : ServiceViewModel by viewModel()
    private val args : ServiceUpdateDialogFragmentArgs by navArgs()

    private var _binding: FragmentServiceUpdateBinding? = null
    private val binding get() = _binding!!
    private var idUser = mutableListOf<String>()
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServiceUpdateBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val service = args.service
        with(binding){

            tfNamaPel.editText?.setText(service.namaPelanggan)
            tfNamaBar.editText?.setText(service.namaBarang)
            tfTgSel.editText?.setText(service.tanggalSelesai)
            tfHarga.editText?.setText(service.hargaJasa)
            tfKeterangan.editText?.setText(service.keterangan)
            tfBiaya.editText?.setText(service.biayaTambahan)
            tfStatus.editText?.setText(service.status)


            val adapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, listOf("masuk", "proses", "selesai"))
            (binding.tfStatus.editText as? AutoCompleteTextView)?.setAdapter(adapter)

            tfTgSel.editText?.setOnClickListener {
                DatePickerDialog(
                    requireActivity(),
                    dateListener(tfTgSel),
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }


            viewModel.user.observe(viewLifecycleOwner) {
                idUser.add(it.idTeknisi.toString())
            }

            viewModel.getCustomer.observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        binding.tfNamaPel.visibility = View.VISIBLE
                        for (data in it.data!!){
                            viewModel.listCustomer[data.namaPelanggan] = data.idPelanggan
                        }
                        val adapterPel = ArrayAdapter(requireContext(), R.layout.item_dropdown, ArrayList(viewModel.listCustomer.keys))
                        (binding.tfNamaPel.editText as? AutoCompleteTextView)?.setAdapter(adapterPel)
                    }
                    is Resource.Error -> {}
                }
            }

            viewModel.getBiaya.observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        binding.tfNamaPel.visibility = View.VISIBLE
                        for (data in it.data!!){
                            viewModel.listBiaya[data.biayaTambahan] = data.idBiayaTambahan
                        }
                        val adapterPel = ArrayAdapter(requireContext(), R.layout.item_dropdown, ArrayList(viewModel.listBiaya.keys))
                        (binding.tfBiaya.editText as? AutoCompleteTextView)?.setAdapter(adapterPel)
                    }
                    is Resource.Error -> {}
                }
            }

            btnAddService.setOnClickListener {
                if (checkAllField()){
                    viewModel.update(
                        service.idNota,
                        ServiceUpdateRequest(
                            idUser[0],
                            viewModel.listCustomer[tfNamaPel.editText?.text.toString()].toString(),
                            tfNamaBar.editText?.text.toString(),
                            tfTgSel.editText?.text.toString(),
                            tfHarga.editText?.text.toString(),
                            tfKeterangan.editText?.text.toString(),
                            viewModel.listBiaya[tfBiaya.editText?.text.toString()].toString(),
                            tfStatus.editText?.text.toString()
                        )
                    ).observe(viewLifecycleOwner) {
                        when (it) {
                            is Resource.Loading -> {
                                btnAddService.isEnabled = false
                                btnAddService.text = "Loading..."
                            }
                            is Resource.Success -> {
                                btnAddService.isEnabled = false
                                btnAddService.text = "Loading..."
                                FancyToast.makeText(
                                    context,
                                    "update service has successful",
                                    FancyToast.LENGTH_SHORT,
                                    FancyToast.SUCCESS,
                                    false
                                ).show()

                                Navigation.findNavController(
                                    requireActivity(),
                                    R.id.nav_host_fragment_content_main
                                ).navigate(
                                    R.id.nav_service
                                )
                            }
                            is Resource.Error -> {
                                btnAddService.isEnabled = true
                                btnAddService.text = "Update Service"
                                FancyToast.makeText(
                                    context,
                                    "update service has failed",
                                    FancyToast.LENGTH_SHORT,
                                    FancyToast.ERROR,
                                    false
                                ).show()

                                Navigation.findNavController(
                                    requireActivity(),
                                    R.id.nav_host_fragment_content_main
                                ).navigate(
                                    R.id.nav_service
                                )
                            }
                        }
                    }
                }
            }

            btnSukuCadang.setOnClickListener {
                val sukuCadang = ServiceUpdateDialogFragmentDirections.actionNavServiceUpdateToNavUsage(service.idService)
                findNavController().navigate(sukuCadang)
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