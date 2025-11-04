package com.lopez_jorge.room_t7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.lopez_jorge.room_t7.presentation.viewmodel.TaskViewModel
import com.lopez_jorge.room_t7.ui.theme.Room_t7Theme
import com.lopez_jorge.room_t7.navigation.Navigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskViewModel by viewModels<TaskViewModel>()


        setContent {
            Room_t7Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(taskViewModel)
                }
            }
        }
    }
}
