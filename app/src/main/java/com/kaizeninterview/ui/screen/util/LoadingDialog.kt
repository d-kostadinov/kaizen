package com.kaizeninterview.ui.screen.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kaizeninterview.R

@Composable
fun LoadingDialog() {

    Dialog(onDismissRequest = { /* Dialog is not dismissible */ }) {
        // Use a Box to center the Card inside the Dialog
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Use Card for rounded corners and elevation
            Card(
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth() // Adjust to make the card width relative to the screen width
                    .padding(16.dp)
            ) {
                // Use padding around the content inside the Card
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Optional: Adding a CircularProgressIndicator
                    CircularProgressIndicator()
                    // Add a little space between the progress bar and the text
                    Spacer(modifier = Modifier.height(16.dp))
                    // Text indicating loading
                    Text(stringResource(R.string.please_wait), style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
