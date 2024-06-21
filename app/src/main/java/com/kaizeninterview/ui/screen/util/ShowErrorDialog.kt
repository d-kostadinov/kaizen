package com.kaizeninterview.ui.screen.util

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.kaizeninterview.R

@Composable
fun showErrorDialog(error: String) {

    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = stringResource(R.string.general_error),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Text(
                    text = error,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            confirmButton = {

                TextButton(onClick = {
                    showDialog = false
                }) {

                    Text(
                        stringResource(R.string.close),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        )
    }

}