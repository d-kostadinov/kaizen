package com.kaizeninterview.util

import com.kaizeninterview.domain.cmn.Event
import com.kaizeninterview.domain.cmn.Sport
import com.kaizeninterview.ui.screen.data.Cell
import com.kaizeninterview.ui.screen.data.Section

fun mapSportListToSectionListImpl(sportList: List<Sport>): List<Section> {
    return sportList.map { mapSportToSection(it) }
}

fun mapSportToSection(sport: Sport): Section {
    return Section(
        id = sport.id,
        title = sport.description,
        items = sport.events.map { mapEventToCell(it) }
    )
}

fun mapEventToCell(event: Event): Cell {
    // Assuming the short description contains the competitors' information
    val competitors = event.shortDescription.split(" - ")
    return Cell(
        id = event.id,
        timeUntilStart = event.startTime,
        competitor1 = competitors.getOrNull(0) ?: "Unknown",
        competitor2 = competitors.getOrNull(1) ?: "Unknown"
    )
}
