package com.example.taskg2.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskg2.ui.model.Task

@Composable
fun TaskItem(task: Task, onCheck: () -> Unit)  {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(vertical = 8.dp).
                fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    )
    {
       Row(
           modifier = Modifier.fillMaxWidth().padding(16.dp),
           verticalAlignment = Alignment.CenterVertically
       )
       {
           Checkbox(checked = task.completed
               , onCheckedChange = {onCheck()})
           Spacer(modifier = Modifier.width(16.dp))
           Text(text = task.name,fontSize = 16.sp)
       }
    }
}