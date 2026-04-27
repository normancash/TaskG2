package com.example.taskg2.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskg2.ui.model.Task
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Content(tab: Int) {
    val scope = rememberCoroutineScope()
    var loading by remember { mutableStateOf(false) }

    var tasks by remember { mutableStateOf(listOf(
        Task("Estudiar compose", false),
        Task("Estudiar fisica", false),
        Task("Estudiar finanzas", false),
        Task("Hacer el proyecto de POO", false),
        Task("Hacer el examen de Android", false),
        ))
    }

    val grouped = tasks.groupBy { it.completed }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
    {
        Button(onClick = {
            scope.launch {
                loading = true
                delay(3000)
                tasks = tasks.shuffled()
                loading = false
            }
           },
            modifier = Modifier.fillMaxWidth())
        {
            if (loading) CircularProgressIndicator(color = Color.White)
            else Text(text = "Reordenar tareas")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn()
        {
            val listToShow = if (tab == 0) grouped[false] else grouped[true]
            items(listToShow ?: emptyList()) {
                task -> TaskItem(task) {
                    tasks = tasks.map {
                        if (it.name == task.name) {
                            it.copy(completed = !it.completed)
                        } else {
                            it
                        }
                    }
                }
            }

        }

    }


}