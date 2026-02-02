package com.github.arminyousefi.vertlune.ui.screens

import com.github.arminyousefi.vertlune.ui.components.snackbar.SnackbarType

sealed class UiEvent {
    data class ShowSnackbar(
        val message: String,
        val type: SnackbarType
    ) : UiEvent()
}