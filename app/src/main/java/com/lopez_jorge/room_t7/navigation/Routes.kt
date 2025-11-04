package com.lopez_jorge.room_t7.navigation

sealed class Routes(val route: String) {
    object SplashScreen: Routes("splash_screen")
    object MainScreen: Routes("main_screen")
    object TaskInfo: Routes("task_info_screen")
}