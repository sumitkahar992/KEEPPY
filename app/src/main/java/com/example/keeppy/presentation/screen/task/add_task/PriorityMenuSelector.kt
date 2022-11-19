package com.example.keeppy.presentation.screen.task.add_task

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.keeppy.presentation.common.model.PriorityView
import com.example.keeppy.presentation.common.model.StatusView
import com.example.keeppy.presentation.common.theme.KEEPPYTheme
import com.example.keeppy.presentation.screen.home.MyTaskDropDownMenu
import com.example.keeppy.presentation.screen.home.MyTaskIcon


@Composable
fun PriorityMenuSelector(
    priority: PriorityView,
    onPriorityChange: (PriorityView) -> Unit,
    modifier: Modifier = Modifier
) {
    var menuExpandedState by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(TextFieldDefaults.MinHeight / 2)
            .clickable {
                menuExpandedState = true
            }
    ) {
        TaskPriorityIndicator(priority = priority)

        PriorityDropDownMenu(
            onPriorityChange = { prioritySelected ->
                onPriorityChange(prioritySelected)
            },
            menuExpandedState = menuExpandedState,
            menuExpandedStateChange = {
                menuExpandedState = false
            }
        )
    }
}

@Composable
@Preview(name = "Light")
private fun Previewr2() {
    KEEPPYTheme {

        var priority by remember { mutableStateOf(PriorityView.NONE) }

        PriorityMenuSelector(
            priority = priority,
            onPriorityChange = {
                priority = it
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TaskPriorityIndicator(
    priority: PriorityView,
    modifier: Modifier = Modifier
) {
    val colorPriorityIndicator = if (isSystemInDarkTheme()) {
        priority.colorDark
    } else {
        priority.colorLight
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(TextFieldDefaults.MinHeight / 2)
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .background(colorPriorityIndicator)
        )
        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = getPriorityName(priority),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
        )

    }

}

private fun getPriorityName(priority: PriorityView) = when (priority) {
    PriorityView.NONE -> "Sem"
    PriorityView.LOW -> "Baixa"
    PriorityView.MEDIUM -> "Média"
    PriorityView.HIGH -> "Alta"
}

@Composable
@Preview(name = "Light")
private fun Preview() {
    KEEPPYTheme {
        TaskPriorityIndicator(
            priority = PriorityView.HIGH,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PriorityDropDownMenu(
    onPriorityChange: (PriorityView) -> Unit,
    menuExpandedState: Boolean,
    menuExpandedStateChange: () -> Unit,
) {

    val priorityItems = listOf(
        "None",
        "Low",
        "Medium",
        "High"
    )

    val priorityItemsModels = listOf(
        PriorityView.NONE,
        PriorityView.LOW,
        PriorityView.MEDIUM,
        PriorityView.HIGH,
    )

    MyTaskDropDownMenu(
        items = priorityItems,
        onItemIndexChange = { indexOptionSelected ->
            onPriorityChange(
                priorityItemsModels[indexOptionSelected]
            )
        },
        expandedState = menuExpandedState,
        onExpandedStateChange = menuExpandedStateChange
    )
}


@Composable
fun StatusMenuSelector(
    status: StatusView,
    onStatusChange: (StatusView) -> Unit,
    modifier: Modifier = Modifier
) {
    var menuExpandedState by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(TextFieldDefaults.MinHeight / 2)
            .clickable {
                menuExpandedState = true
            }
    ) {
        TaskStatusIndicator(status = status)

        StatusDropDownMenu(
            onStatusChange = { statusSelected ->
                onStatusChange(statusSelected)
            },
            menuExpandedState = menuExpandedState,
            menuExpandedStateChange = {
                menuExpandedState = false
            }
        )
    }

}


@Composable
fun TaskStatusIndicator(
    status: StatusView,
    modifier: Modifier = Modifier
) {
    val nameStatus = when (status) {
        StatusView.TODO -> "Scheduled"
        StatusView.PROGRESS -> "In Progress"
        StatusView.DONE -> "Completed"
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(TextFieldDefaults.MinHeight / 2)
    ) {
        MyTaskIcon(imageVector = status.icon)

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = nameStatus,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
        )

    }

}


@Composable
@Preview(name = "Light", showBackground = true)
private fun Preview2323() {
    KEEPPYTheme {
        TaskStatusIndicator(
            status = StatusView.TODO,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun StatusDropDownMenu(
    onStatusChange: (StatusView) -> Unit,
    menuExpandedState: Boolean,
    menuExpandedStateChange: () -> Unit
) {
    val statusItem = listOf(
        "Scheduled",
        "In progress",
        "Completed"
    )

    val statusItemsModels = listOf(
        StatusView.TODO,
        StatusView.PROGRESS,
        StatusView.DONE
    )

    MyTaskDropDownMenu(
        items = statusItem,
        onItemIndexChange = { indexOptionSelected ->
            onStatusChange(
                statusItemsModels[indexOptionSelected]
            )
        },
        expandedState = menuExpandedState,
        onExpandedStateChange = menuExpandedStateChange
    )
}





































