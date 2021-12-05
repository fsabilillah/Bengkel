package com.example.bengkel.ui.main.service.usage.dialog.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.databinding.FragmentUsageCreateBinding
import com.example.bengkel.ui.main.cadang.SukuCadangViewModel
import com.example.bengkel.ui.main.service.usage.UsageViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class UsageCreateDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentUsageCreateBinding? = null
    private val viewModel: UsageViewModel by viewModel()
    private val sukuCadangViewModel : SukuCadangViewModel by viewModel()
    private val args : UsageCreateDialogFragmentArgs by navArgs()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsageCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sukuCadangViewModel.getSukuCadang.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> { }
                is Resource.Success -> {
                    for (data in it.data!!){
                        viewModel.listSukuCadang[data.namaSukuCadang!!] = data.idSukuCadang!!
                    }
                    val adapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, ArrayList(viewModel.listSukuCadang.keys))
                    (binding.tfName.editText as? AutoCompleteTextView)?.setAdapter(adapter)
                }
                is Resource.Error -> { }
            }
        }

        with(binding){
            btnAddSukuCadang.setOnClickListener {
                val idSukuCadang = viewModel.listSukuCadang[binding.tfName.editText?.text.toString()]
                val jumlah = binding.tfStock.editText?.text.toString()

                viewModel.createUsage(args.idService, idSukuCadang.toString(), jumlah).observe(viewLifecycleOwner) {
                    when(it){
                        is Resource.Loading -> {
                            btnAddSukuCadang.isEnabled = false
                            btnAddSukuCadang.text = "Loading..."
                        }
                        is Resource.Success -> {
                            btnAddSukuCadang.isEnabled = false
                            btnAddSukuCadang.text = "Loading..."
                            FancyToast.makeText(context, "create usage has successful", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()

                            val data = UsageCreateDialogFragmentDirections.actionNavUsageCreateToNavUsage(args.idService)
                            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(data)
                        }
                        is Resource.Error -> {
                            btnAddSukuCadang.isEnabled = true
                            btnAddSukuCadang.text = "Tambah Suku Cadang"
                            FancyToast.makeText(context, "create usage has failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()

                            val data = UsageCreateDialogFragmentDirections.actionNavUsageCreateToNavUsage(args.idService)
                            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(data)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}