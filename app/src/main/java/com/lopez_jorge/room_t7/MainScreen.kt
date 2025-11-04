// MainScreen.kt
package com.lopez_jorge.room_t7

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun MainScreen(
    navController: NavHostController,
    taskViewModel: TaskViewModel,
    mainScreenViewModel: MainScreenViewModel = viewModel()
) {
    val taskList by taskViewModel.taskList.observeAsState(initial = emptyList())
    val inputTaskName by mainScreenViewModel.taskName.observeAsState(initial = "")

    val showDeleteIcon = remember { derivedStateOf { inputTaskName.isNotEmpty() } }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Lista de tareas:",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = inputTaskName,
                onValueChange = { mainScreenViewModel.onTaskNameChange(it) },
                label = { Text("Tarea a añadir") },
                trailingIcon = {
                    if (showDeleteIcon.value) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar texto",
                            modifier = Modifier.clickable { mainScreenViewModel.onTaskNameDelete() }
                        )
                    }
                }
            )

            Button(
                onClick = {
                    taskViewModel.addTask(inputTaskName)
                    mainScreenViewModel.onTaskNameDelete()
                },
                enabled = showDeleteIcon.value
            ) { Text("Añadir") }
        }

        Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.onPrimary)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1f, fill = true)
        ) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(taskList) { task ->
                    TaskItem(
                        task = task,
                        onUpdate = { taskViewModel.updateTask(task, it) },
                        onDelete = { taskViewModel.deleteTask(task) }
                    )
                }
            }
        }

        AuthorInfo(modifier = Modifier.fillMaxWidth().padding(8.dp))
    }
}

@Composable
fun TaskItem(
    task: TaskEntity,
    onUpdate: (Boolean) -> Unit,
    onDelete: () -> Unit,
) {
    ListItem(
        headlineContent = { Text(task.name) },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        leadingContent = {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = onUpdate
            )
        },
        trailingContent = {
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar tarea"
                )
            }
        },
        modifier = Modifier.padding(4.dp)
    )
}
