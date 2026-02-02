package com.github.arminyousefi.vertlune.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.arminyousefi.vertlune.domain.model.CartItem
import com.github.arminyousefi.vertlune.domain.use_case.CartUseCases
import com.github.arminyousefi.vertlune.domain.util.Resource
import com.github.arminyousefi.vertlune.ui.components.snackbar.SnackbarType
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCases: CartUseCases
) : ViewModel() {

    private val _eventFlow = Channel<UiEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    val cartItems: StateFlow<List<CartItem>> =
        cartUseCases.getCartItems()
            .map { resource ->
                if (resource is Resource.Error) {
                    sendEvent(UiEvent.ShowSnackbar(resource.uiMessage ?: "Error loading items", SnackbarType.ERROR))
                }
                resource.data ?: emptyList()
            }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val totalPrice: StateFlow<Double> = cartItems
        .map { it.sumOf { item -> item.price * item.quantity } }
        .stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    private fun <T> executeTask(
        task: suspend () -> Resource<T>,
        successMessage: String? = null
    ) = viewModelScope.launch {
        when (val result = task()) {
            is Resource.Success -> {
                successMessage?.let {
                    _eventFlow.send(UiEvent.ShowSnackbar(it, SnackbarType.SUCCESS))
                }
            }
            is Resource.Error -> {

                Log.e("CartViewModel", "Dev Error: ${result.devMessage}")

                _eventFlow.send(UiEvent.ShowSnackbar(result.uiMessage ?: "Something went wrong", SnackbarType.ERROR))
            }
            is Resource.Loading -> {  }
        }
    }

    fun addToCart(item: CartItem) = executeTask(
        task = { cartUseCases.addToCart(item) },
        successMessage = "${item.title} added to cart!"
    )

    fun removeFromCart(productId: String) = executeTask(
        task = { cartUseCases.removeFromCart(productId) }

    )

    fun updateQuantity(productId: String, quantity: Int) = executeTask(
        task = { cartUseCases.updateQuantity(productId, quantity) }
    )

    private fun sendEvent(event: UiEvent) = viewModelScope.launch {
        _eventFlow.send(event)
    }
}