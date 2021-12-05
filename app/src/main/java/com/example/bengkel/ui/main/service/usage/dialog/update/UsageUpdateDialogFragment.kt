package com.example.bengkel.ui.main.service.usage.dialog.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.databinding.FragmentUsageUpdateBinding
import com.example.bengkel.ui.main.service.usage.UsageViewModel
import com.example.bengkel.ui.main.service.usage.dialog.create.UsageCreateDialogFragmentDirections
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class UsageUpdateDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentUsageUpdateBinding? = null
    private val args: UsageUpdateDialogFragmentArgs by navArgs()
    private val viewModel: UsageViewModel by viewModel()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsageUpdateBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tfStock.editText?.setText(args.usage.jumlahSukuCadang)

            btnAddSukuCadang.setOnClickListener {
                viewModel.updateUsage(
                    args.idService,
                    args.usage.idPemakaian,
                    args.usage.idSukuCadang,
                    tfStock.editText?.text.toString()
                ).observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> {
                            btnAddSukuCadang.isEnabled = false
                            btnAddSukuCadang.text = "Loading..."
                        }
                        is Resource.Success -> {
                            btnAddSukuCadang.isEnabled = false
                            btnAddSukuCadang.text = "Loading..."
                            FancyToast.makeText(
                                context,
                                "update usage has successful",
                                FancyToast.LENGTH_SHORT,
                                FancyToast.SUCCESS,
                                false
                            ).show()

                            val data =
                                UsageUpdateDialogFragmentDirections.actionNavUsageUpdateToNavUsage(
                                    args.idService
                                )
                            Navigation.findNavController(
                                requireActivity(),
                                R.id.nav_host_fragment_content_main
                            ).navigate(data)
                        }
                        is Resource.Error -> {
                            btnAddSukuCadang.isEnabled = true
                            btnAddSukuCadang.text = "Update Suku Cadang"
                            FancyToast.makeText(
                                context,
                                "update usage has failed",
                                FancyToast.LENGTH_SHORT,
                                FancyToast.ERROR,
                                false
                            ).show()

                            val data =
                                UsageUpdateDialogFragmentDirections.actionNavUsageUpdateToNavUsage(
                                    args.idService
                                )
                            Navigation.findNavController(
                                requireActivity(),
                                R.id.nav_host_fragment_content_main
                            ).navigate(data)
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