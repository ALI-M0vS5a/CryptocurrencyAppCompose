package com.example.cryptocurrencyappcompose.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyappcompose.common.Resource
import com.example.cryptocurrencyappcompose.common.UiEvent
import com.example.cryptocurrencyappcompose.common.UiText
import com.example.cryptocurrencyappcompose.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {


    private val _eventFLow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFLow.asSharedFlow()

    private val _uiState = mutableStateOf(CoinListState())
    val uiState: State<CoinListState> = _uiState


    init {
        getCoins()
    }


    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.value = uiState.value.copy(coins = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _uiState.value = uiState.value.copy(isLoading = true)
                }
                is Resource.Error -> {
                    _uiState.value =
                        uiState.value.copy(error = result.message ?: UiText.unknownError())
                }
            }
        }.launchIn(viewModelScope)
    }
}