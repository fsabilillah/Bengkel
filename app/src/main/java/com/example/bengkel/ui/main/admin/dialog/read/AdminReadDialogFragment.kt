package com.example.bengkel.ui.main.admin.dialog.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.bengkel.databinding.FragmentAdminCreateBinding
import com.example.bengkel.databinding.FragmentAdminReadBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AdminReadDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAdminReadBinding? = null
    private val binding get() = _binding!!
    private val args: AdminReadDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminReadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = args.user
        with(binding){
            tfUsername.editText?.setText(user.username)
            tfName.editText?.setText(user.nama)
            tfEmail.editText?.setText(user.email)
            tfAddress.editText?.setText(user.alamat)
            tfHakAkses.editText?.setText(user.hakAkses)
            tfHp.editText?.setText(user.noHp)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}