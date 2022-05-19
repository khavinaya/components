package com.example.onboardingscreencomponent.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun ButtonText(
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.TopEnd
    ) {
        Text(
            text = text,
            color = Color.Red,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Normal
        )
    }

}