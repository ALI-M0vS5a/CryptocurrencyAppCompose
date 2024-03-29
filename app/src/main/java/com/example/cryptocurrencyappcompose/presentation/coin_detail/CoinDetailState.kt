package com.example.cryptocurrencyappcompose.presentation.coin_detail

import com.example.cryptocurrencyappcompose.R
import com.example.cryptocurrencyappcompose.common.UiText
import com.example.cryptocurrencyappcompose.domain.model.CoinDetail


data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: UiText = UiText.StringResource(R.string.empty)
)
