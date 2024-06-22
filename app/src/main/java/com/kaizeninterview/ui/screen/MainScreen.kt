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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaizeninterview.R
import com.kaizeninterview.room.table.CellFav
import com.kaizeninterview.room.table.SectionFav
import com.kaizeninterview.ui.screen.data.Cell
import com.kaizeninterview.ui.screen.data.Section
import com.kaizeninterview.ui.screen.util.LoadingDialog
import com.kaizeninterview.ui.screen.util.showErrorDialog
import com.kaizeninterview.ui.theme.KaizenBlue
import com.kaizeninterview.ui.theme.KaizenPrimaryColor
import com.kaizeninterview.viewmodel.KaizenViewModel
import java.util.Locale

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

    val sportDataState by viewModel.sportsData.collectAsState()

    when {
        sportDataState.isLoading -> {
            LoadingDialog()
        }

        sportDataState.error != null -> {
            showErrorDialog(
                error = sportDataState.error!!
            )
        }

        sportDataState.data != null -> {
            SectionedGrid(
                Modifier.padding(top = innerPadding.calculateTopPadding()),
                sections = sportDataState.data!!,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun CollapsableSection(
    section: Section,
    onToggleExpand: () -> Unit,
    onToggleFavSection: () -> Unit,
    onToggleFavCell: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(section.isExpanded) }
    var isFav by remember { mutableStateOf(section.isFav) }

    Column() {
        // Section header
        MainScreenSection(section, isFav,
            onToggleExpand = {
                isExpanded = !isExpanded
                onToggleExpand()
            },
            onToggleFav = {
                isFav = !isFav
                onToggleFavSection()
            }
        )
        // Items grid
        if (isExpanded) {

            var data by remember { mutableStateOf(listOf<Cell>()) }
            data = section.itemsFiltered

            if (data.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .height(section.calculateSectionHeight())
                        .background(KaizenPrimaryColor)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(data.size) { index ->
                            val item = data[index]
                            MainScreenCell(
                                cellData = item,
                                onToggleFav = { cellId ->
                                    onToggleFavCell(cellId)
                                }
                            )
                        }
                    }
                }
            } else {
                Text(
                    text = stringResource(R.string.no_fav_games).uppercase(Locale.ROOT),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .background(KaizenPrimaryColor)
                )
            }
        }
    }
}

@Composable
fun SectionedGrid(modifier: Modifier, sections: List<Section>, viewModel: KaizenViewModel) {
    // State to track expanded sections
    var sectionStates = remember { mutableStateListOf<Section>() }
    sectionStates.clear()
    sectionStates.addAll(sections)

    LazyColumn(modifier = modifier.background(KaizenPrimaryColor)) {
        items(sectionStates.size) { index ->
            var section by remember { mutableStateOf(sectionStates[index]) }

            CollapsableSection(
                section = section,
                onToggleFavSection = {
                    section.isFav = !section.isFav
                    // Update state to reflect changes
                    sectionStates[index] = section

                    viewModel.insertSectionFav(SectionFav(section.id, section.isFav))
                },
                onToggleExpand = {
                    section.isExpanded = !section.isExpanded
                    // Update state to reflect changes
                    sectionStates[index] = section
                },
                onToggleFavCell = { cellId ->
                    var cell = section.items.first { it.id == cellId }
                    cell.isFav = !cell.isFav

                    viewModel.insertCellFav(CellFav(cell.id, cell.isFav))

                    var findedSection = sections.first { sc -> sc.id == section.id }
                    var findedCell = findedSection.items.first { cl -> cl.id == cell.id }
                    findedCell.isFav = cell.isFav

                    // Update state to reflect changes
                    sectionStates.clear()
                    sectionStates.addAll(sections)
                }
            )
        }
    }
}



