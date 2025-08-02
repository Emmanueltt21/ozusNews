package com.kottland.ozusnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.kottland.ozusnews.presentation.viewmodel.NewsViewModel
import com.kottland.ozusnews.presentation.navigation.OzusNewsNavHost
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.kottland.ozusnews.ui.theme.OzusNewsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: NewsViewModel by viewModels()
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }
            MaterialTheme(
                colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
            ) {
                OzusNewsNavHostWithTheme(isDarkTheme = isDarkTheme, onThemeToggle = { isDarkTheme = it })
            }
        }
        viewModel.fetchTopHeadlines()
    }
}

@Composable
fun OzusNewsNavHostWithTheme(isDarkTheme: Boolean, onThemeToggle: (Boolean) -> Unit) {
    com.kottland.ozusnews.presentation.navigation.OzusNewsNavHost(
        isDarkTheme = isDarkTheme,
        onThemeToggle = onThemeToggle
    )
}

