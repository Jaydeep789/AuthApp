package com.example.authapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authapp.navigation.Screen
import com.example.authapp.presentation.screen.login.LoginScreen
import com.example.authapp.presentation.screen.login.LoginViewModel
import com.example.authapp.ui.theme.AuthAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthAppTheme {
                val navigationController = rememberNavController()
                NavigationGraph(navHostController = navigationController)
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(
                loginViewModel = loginViewModel
            )
        }
        composable(route = Screen.Profile.route) {

        }
    }
}