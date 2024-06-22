package com.kaizeninterview.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaizeninterview.ui.screen.data.Cell
import com.kaizeninterview.ui.screen.data.Section
import com.kaizeninterview.ui.screen.util.LoadingDialog
import com.kaizeninterview.ui.screen.util.showErrorDialog
import com.kaizeninterview.ui.theme.KaizenBlue
import com.kaizeninterview.ui.theme.KaizenPrimaryColor
import com.kaizeninterview.util.mapSportListToSectionList
import com.kaizeninterview.util.mapSportToSection
import com.kaizeninterview.viewmodel.KaizenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    titleName: String,
    modifier: Modifier = Modifier,
    viewModel: KaizenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = titleName, style = MaterialTheme.typography.titleLarge)
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = KaizenBlue,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->

        MainScreenContent(innerPadding, viewModel = viewModel)

    }
}

@Composable
fun MainScreenContent(innerPadding: PaddingValues, viewModel: KaizenViewModel) {

    val salesOrderLinesState by viewModel.sportsData.collectAsState()

    when {
        salesOrderLinesState.isLoading -> {
            LoadingDialog()
        }

        salesOrderLinesState.error != null -> {
            showErrorDialog(
                error = salesOrderLinesState.error!!
            )
        }

        salesOrderLinesState.data != null -> {
            SectionedGrid(
                Modifier.padding(innerPadding),
                sections = mapSportListToSectionList(salesOrderLinesState.data!!)
            )
        }
    }

//    var cell = Cell(10, false, "asd", "asd")
//    var cellFav = Cell(10, true, "asd", "asd")
//
//    val sections = listOf(
//        Section(
//            "Section 1",
//            listOf(
//                cell,
//                cell,
//                cell,
//                cell,
//                cell,
//                cellFav,
//                cell,
//                cell,
//                cellFav,
//                cell,
//                cell,
//                cell,
//                cell
//            ),
//            true,
//            true
//        ),
//        Section("Section 2", listOf(cell, cell, cell, cell), false, true),
//        Section("Section 3", listOf(cell, cell, cell, cell), true, true),
//    )


//    val numbers = (1..10).toList()
//    LazyColumn(contentPadding = innerPadding) {
//        items(numbers) { number ->
//            Text(text = number.toString())
//        }
//    }
}

@Composable
fun CollapsableSection(
    section: Section,
    onToggleExpand: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(section.isExpanded) }
    var isFav by remember { mutableStateOf(section.isFav) }

    Column(modifier = modifier) {
        // Section header
        MainScreenSection(section, isFav,
            onToggleExpand = {
                isExpanded = !isExpanded
                onToggleExpand()
            },
            onFavClicked = { isFav = !isFav })
        // Items grid
        if (isExpanded) {
            Box(
                modifier = Modifier
                    .height(section.calculateSectionHeight())
                    .background(KaizenPrimaryColor)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(section.items.size) { index ->
                        val item = section.items[index]
                        MainScreenCell(cellData = item)
                    }
                }
            }

        }
    }
}

@Composable
fun SectionedGrid(modifier: Modifier, sections: List<Section>) {
    // State to track expanded sections
    val sectionStates = remember { mutableStateListOf<Section>() }
    sectionStates.clear()
    sectionStates.addAll(sections)

    LazyColumn(modifier = modifier) {
        items(sectionStates.size) { index ->
            val section by remember { mutableStateOf(sectionStates[index]) }

//            val section = sectionStates[index]
            CollapsableSection(
                section = section,
                onToggleExpand = {
                    section.isExpanded = !section.isExpanded
                    // Update state to reflect changes
                    sectionStates[index] = section
                }
            )
        }
    }
}



