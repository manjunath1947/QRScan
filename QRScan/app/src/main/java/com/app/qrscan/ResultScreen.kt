package com.app.qrscan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(qrText: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Scanned QR Code",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = qrText,
                    onValueChange = { /* Read-only field */ },
                    label = { Text("QR Code Value") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )
            }
        }
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Next")

                }
        }

    }
}