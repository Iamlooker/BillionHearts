package com.looker.billionhearts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.looker.billionhearts.ui.AppState
import com.looker.billionhearts.ui.navigation.BillionHeartsNavHost
import com.looker.billionhearts.ui.rememberAppState
import com.looker.billionhearts.ui.theme.BillionHeartsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberAppState()
            BillionHearts(appState)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun BillionHearts(appState: AppState) {
    BillionHeartsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { BillionHeartsAppBar(appState) },
        ) { _ ->
            BillionHeartsNavHost(appState)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun BillionHeartsAppBar(appState: AppState) {
    TopAppBar(
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            if (!appState.isHome) {
                IconButton(
                    onClick = { appState.controller.popBackStack() },
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = {}) {
                val icon = if (appState.isHome) {
                    Icons.Default.Search
                } else {
                    Icons.Default.FavoriteBorder
                }
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                )
            }
        },
    )
}
