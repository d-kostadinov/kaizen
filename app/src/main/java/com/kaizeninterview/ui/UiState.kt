package com.kaizeninterview.ui

data class UiState<T>(
    val data: T? = null,
    var isLoading: Boolean = false,
    val error: String? = null
)