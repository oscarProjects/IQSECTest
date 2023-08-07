package com.example.iqsectest.listener

import com.example.iqsectest.data.model.Pais

interface ClickListener {
    fun onClickCard(pais: Pais?)
}