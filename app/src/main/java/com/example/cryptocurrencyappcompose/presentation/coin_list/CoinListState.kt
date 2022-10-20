package com.example.cryptocurrencyappcompose.presentation.coin_list

import com.example.cryptocurrencyappcompose.common.UiText
import com.example.cryptocurrencyappcompose.domain.model.Coin


data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: UiText = UiText.unknownError()
)
