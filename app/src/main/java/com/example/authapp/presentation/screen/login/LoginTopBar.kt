package com.example.authapp.presentation.screen.login

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.authapp.ui.theme.AuthAppTheme
import com.example.authapp.ui.theme.topAppBarBackgroundColor
import com.example.authapp.ui.theme.topAppBarContentColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopBar(modifier: Modifier = Modifier) {

    TopAppBar(
        title = {
            Text(
                text = "Sign in",
                color = MaterialTheme.colorScheme.topAppBarContentColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor)
    )
}

@Preview
@Composable
private fun LoginTopBarPreview() {
    AuthAppTheme {
        LoginTopBar()
    }
}