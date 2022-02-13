package com.example.bengkel.ui.main.biaya.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.bengkel.databinding.FragmentBiayaReadBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BiayaReadDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBiayaReadBinding? = null
    private val binding get() = _binding!!
    private val args: BiayaReadDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBiayaReadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = args.biaya
        with(binding){
            tfBiaya.editText?.setText(user.biayaTambahan)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}