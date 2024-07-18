package com.app.qrscan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.qrscan.ui.theme.AppTypography

@Composable
fun AppRedButton(
    modifier: Modifier = Modifier,
    text: String,
    isOutlined: Boolean,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    val buttonColor =
        if (!isOutlined) ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.Red
        ) else ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            contentColor = Color.Red,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.Red
        )
    Button(
        enabled = isEnabled,
        onClick = onClick,
        border = BorderStroke(2.dp, Color.Red),
        colors = buttonColor,
        modifier = modifier
            .height(46.dp)
            .fillMaxWidth()

    ) {
        Text(
            text = text,
            style = AppTypography.fontStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            color = if (isOutlined) Color.Red else Color.White
        )
    }
}
