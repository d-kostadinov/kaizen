package com.kaizeninterview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaizeninterview.domain.cmn.Sport
import com.kaizeninterview.network.KaizenApi
import com.kaizeninterview.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KaizenViewModel @Inject constructor (val apiService: KaizenApi) : ViewModel() {

    // Mutable state for UI
    private val _sportsData = MutableStateFlow(UiState<List<Sport>>())
    val sportsData: StateFlow<UiState<List<Sport>>> = _sportsData

    init {
        fetchSportsEvents()
    }

    private fun fetchSportsEvents() {
        viewModelScope.launch {
            try {
                _sportsData.value = UiState(isLoading = true)

                val response = apiService.fetchAllSports()

                _sportsData.value = UiState(data = response)
            } catch (e: Exception) {
                _sportsData.value = UiState(error = e.message)
            }
        }
    }
}