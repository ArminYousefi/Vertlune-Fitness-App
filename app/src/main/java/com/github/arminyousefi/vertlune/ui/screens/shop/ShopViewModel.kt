package com.github.arminyousefi.vertlune.ui.screens.shop

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.arminyousefi.vertlune.domain.use_case.products.GetAllProductsUseCase
import com.github.arminyousefi.vertlune.domain.util.Resource
import com.github.arminyousefi.vertlune.ui.components.snackbar.SnackbarType
import com.github.arminyousefi.vertlune.ui.screens.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShopUiState(isLoading = true))
    val uiState: StateFlow<ShopUiState> = _uiState.asStateFlow()

    private val _eventFlow = Channel<UiEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    init {
        loadProducts()
    }

    private fun <T> executeTask(
        task: suspend () -> Flow<Resource<T>>,
        onSuccess: (T?) -> Unit,
        showSuccessMessage: String? = null
    ) = viewModelScope.launch {
        task().collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    onSuccess(resource.data)
                    _uiState.update { it.copy(isLoading = false) }

                    showSuccessMessage?.let {
                        _eventFlow.send(
                            UiEvent.ShowSnackbar(it, SnackbarType.SUCCESS)
                        )
                    }
                }

                is Resource.Error -> {
                    Log.e("ShopViewModel", resource.devMessage ?: "Unknown error")

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = resource.uiMessage
                        )
                    }

                    _eventFlow.send(
                        UiEvent.ShowSnackbar(
                            resource.uiMessage ?: "Unexpected error",
                            SnackbarType.ERROR
                        )
                    )
                }
            }
        }
    }

    private fun loadProducts() {
        executeTask(
            task = { getAllProductsUseCase() },
            onSuccess = { data ->
                val products = data ?: emptyList()
                _uiState.update {
                    it.copy(
                        products = products,
                        originalProducts = products
                    )
                }
            }
        )
    }

    fun applyFilter(filter: ProductFilter) {
        _uiState.update { it.copy(currentFilter = filter) }
        filterAndSort("Filter applied: ${filter.name}")
    }

    fun applySort(sort: ProductSort) {
        _uiState.update { it.copy(currentSort = sort) }
        filterAndSort("Sorting updated")
    }

    private fun filterAndSort(message: String) = viewModelScope.launch {
        val state = _uiState.value

        var filtered = state.originalProducts

        if (state.currentFilter != ProductFilter.ALL) {
            filtered = filtered.filter {
                it.category.equals(state.currentFilter.name, ignoreCase = true)
            }
        }

        filtered = when (state.currentSort) {
            ProductSort.NONE -> filtered
            ProductSort.PRICE_LOW_TO_HIGH -> filtered.sortedBy { it.price }
            ProductSort.PRICE_HIGH_TO_LOW -> filtered.sortedByDescending { it.price }
            ProductSort.RATING_HIGH_TO_LOW -> filtered.sortedByDescending { it.rating }
        }

        _uiState.update { it.copy(products = filtered) }

        if (filtered.isEmpty()) {
            _eventFlow.send(
                UiEvent.ShowSnackbar(
                    "No products found",
                    SnackbarType.ERROR
                )
            )
        } else {
            _eventFlow.send(
                UiEvent.ShowSnackbar(
                    message,
                    SnackbarType.SUCCESS
                )
            )
        }
    }

    fun resetFilter() {
        applyFilter(ProductFilter.ALL)
    }

    fun resetSort() {
        applySort(ProductSort.NONE)
    }

    fun searchProduct(query: String) {

        val filtered = _uiState.value.originalProducts.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.category.contains(query, ignoreCase = true)

        }

        _uiState.update {
            it.copy(
                products = filtered,
                currentFilter = ProductFilter.ALL,

                currentSort = ProductSort.NONE

            )
        }
    }
}

