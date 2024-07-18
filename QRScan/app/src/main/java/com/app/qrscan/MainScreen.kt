package com.app.qrscan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    var barcodeValue by remember { mutableStateOf("") }

    CameraPreview { detectedValue ->
        barcodeValue = detectedValue
        navController.navigate("resultScreen/$detectedValue")
    }

    if (barcodeValue.isNotEmpty()) {
        Text(
            text = "Detected QR Code: $barcodeValue",
            color = Color.Black,
            style = MaterialTheme.typography.bodySmall
        )
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center) {
            Text(text = "Point the camera at qr code", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = barcodeValue, onValueChange = {},
            label = { Text(text = "Qr Code Scanner")},
            modifier = Modifier.fillMaxSize(),
            readOnly = true)
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}
*/
