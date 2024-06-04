package com.example.authapp.navigation

sealed class Screen(val route: String){
    data object Login: Screen(route = "login_screen")
    data object Profile: Screen(route = "profile_screen")
}
