package com.github.arminyousefi.vertlune.ui.screens.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.arminyousefi.vertlune.domain.use_case.DiscoveryUseCases
import com.github.arminyousefi.vertlune.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val discoveryUseCases: DiscoveryUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(DiscoverUiState(isLoading = true))
    val uiState: StateFlow<DiscoverUiState> = _uiState

    init {
        loadDiscoveryItems()
    }

    private fun loadDiscoveryItems() {
        viewModelScope.launch {
            discoveryUseCases.getDiscoveryItems()
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = true)
                        }

                        is Resource.Success -> {
                            _uiState.value = DiscoverUiState(
                                items = resource.data ?: emptyList()
                            )
                        }

                        is Resource.Error -> {
                            _uiState.value = DiscoverUiState(
                                errorMessage = resource.uiMessage
                            )
                        }
                    }
                }
        }
    }
}
