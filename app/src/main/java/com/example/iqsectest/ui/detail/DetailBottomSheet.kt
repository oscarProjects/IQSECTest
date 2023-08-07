package com.example.iqsectest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iqsectest.data.model.Estado
import com.example.iqsectest.data.model.Pais
import com.example.iqsectest.databinding.BottomSheetDetailBinding
import com.example.iqsectest.util.ConstantsIQSEC
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetDetailBinding

    private lateinit var paisArg: Pais
    private lateinit var stateArg: Estado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null){
            paisArg = requireArguments().getSerializable(ConstantsIQSEC.ARG_PAIS) as Pais
            stateArg = requireArguments().getSerializable(ConstantsIQSEC.ARG_ESTADO) as Estado
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BottomSheetDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        initListener()
    }

    private fun initListener() {
        binding.imageViewClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setData(){
        binding.item = stateArg
        binding.itemPais = paisArg
    }
}