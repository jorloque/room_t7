package com.lopez_jorge.room_t7.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lopez_jorge.room_t7.presentation.viewmodel.TaskViewModel
import com.lopez_jorge.room_t7.screens.MainScreen
import com.lopez_jorge.room_t7.screens.SplashScreen

@Composable
fun Navigation(taskViewModel: TaskViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen.route,
    ) {
        composable(Routes.SplashScreen.route) {
            SplashScreen(navController, taskViewModel)
        }

        composable(Routes.MainScreen.route) {
            MainScreen(navController, taskViewModel)
        }
    }
}