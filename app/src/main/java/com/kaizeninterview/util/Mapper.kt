package com.kaizeninterview.util

import com.kaizeninterview.domain.cmn.Event
import com.kaizeninterview.domain.cmn.Sport
import com.kaizeninterview.ui.screen.data.Cell
import com.kaizeninterview.ui.screen.data.Section

fun mapSportListToSectionList(sportList: List<Sport>): List<Section> {
    return sportList.map { mapSportToSection(it) }
}

fun mapSportToSection(sport: Sport): Section {
    return Section(
        title = sport.description,
        items = sport.events.map { mapEventToCell(it) }
    )
}

fun mapEventToCell(event: Event): Cell {
    // Assuming the short description contains the competitors' information
    val competitors = event.shortDescription.split(" - ")
    return Cell(
        timeUntilStart = event.startTime,
        competitor1 = competitors.getOrNull(0) ?: "Unknown",
        competitor2 = competitors.getOrNull(1) ?: "Unknown"
    )
}
