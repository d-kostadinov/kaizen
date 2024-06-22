package com.kaizeninterview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaizeninterview.domain.cmn.Sport
import com.kaizeninterview.network.repo.KaizenRepo
import com.kaizeninterview.room.dao.CellFavDao
import com.kaizeninterview.room.dao.SectionFavDao
import com.kaizeninterview.room.table.CellFav
import com.kaizeninterview.room.table.SectionFav
import com.kaizeninterview.ui.UiState
import com.kaizeninterview.ui.screen.data.Section
import com.kaizeninterview.util.mapSportListToSectionListImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KaizenViewModel @Inject constructor(
    private val repo: KaizenRepo,
    private val sectionFavDao: SectionFavDao,
    private val cellFavDao: CellFavDao
) : ViewModel() {

    // Mutable state for UI
    private val _sportsData = MutableStateFlow(UiState<List<Section>>())
    val sportsData: StateFlow<UiState<List<Section>>> = _sportsData

    init {
        fetchSportsEvents()
    }

    private fun fetchSportsEvents() {
        viewModelScope.launch {
            try {
                _sportsData.value = UiState(isLoading = true)

                val sportsNetworkLayer = repo.fetchAllSports()

                val sportsUiLayer = mapSportListToSectionList(sportsNetworkLayer)

                _sportsData.value = UiState(data = sportsUiLayer)
            } catch (e: Exception) {
                _sportsData.value = UiState(error = e.message)
            }
        }
    }

    fun insertSectionFav(sectionFav: SectionFav) {
        viewModelScope.launch {
            sectionFavDao.insert(sectionFav)
        }
    }

    fun insertCellFav(cellFav: CellFav) {
        viewModelScope.launch {
            cellFavDao.insert(cellFav)
        }
    }

    suspend fun mapSportListToSectionList(sportList: List<Sport>): List<Section> {

        var sportUiData = mapSportListToSectionListImpl(sportList)

        sportUiData.forEach { section ->
            section.apply {
                isFav = sectionFavDao.getSectionFavById(section.id)?.isFav ?: false

                section.items.forEach { cell ->
                    cell.apply {
                        isFav = cellFavDao.getCellFavById(cell.id)?.isFav ?: false
                    }
                }
            }
        }

        return sportUiData
    }
}