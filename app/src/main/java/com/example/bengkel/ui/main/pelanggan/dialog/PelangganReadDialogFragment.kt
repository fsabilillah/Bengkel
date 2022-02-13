package com.example.bengkel.ui.main.pelanggan.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.bengkel.databinding.FragmentPelangganReadBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PelangganReadDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentPelangganReadBinding? = null
    private val binding get() = _binding!!
    private val args: PelangganReadDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPelangganReadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = args.pelanggan
        with(binding){
            tfName.editText?.setText(user.namaPelanggan)
            tfAddress.editText?.setText(user.alamatPelanggan)
            tfHp.editText?.setText(user.noHpPelanggan)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}