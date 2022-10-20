package com.example.cryptocurrencyappcompose.domain.use_case.get_coins

import com.example.cryptocurrencyappcompose.R
import com.example.cryptocurrencyappcompose.common.Resource
import com.example.cryptocurrencyappcompose.common.UiText
import com.example.cryptocurrencyappcompose.domain.model.Coin
import com.example.cryptocurrencyappcompose.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject


class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))

        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = UiText.StringResource(
                        resId = R.string.please_check_your_connection
                    )
                )
            )
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = (e.localizedMessage ?: UiText.StringResource(
                        resId = R.string.Oops_something_went_wrong
                    )) as UiText
                )
            )
        }
    }
}