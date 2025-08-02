package com.kottland.ozusnews.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Dark Mode", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(16.dp))
            Switch(
                checked = isDarkTheme,
                onCheckedChange = onThemeToggle
            )
        }
    }
}