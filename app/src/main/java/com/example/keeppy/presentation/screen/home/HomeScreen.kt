package com.example.keeppy.presentation.screen.home

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.keeppy.presentation.common.model.PriorityView
import com.example.keeppy.presentation.common.model.StatusView
import com.example.keeppy.presentation.common.model.TaskView
import com.example.keeppy.presentation.common.navigation.Screen
import com.example.keeppy.presentation.common.theme.KEEPPYTheme
import com.example.keeppy.presentation.screen.components.SimpleAlertDialog
import com.example.keeppy.presentation.screen.components.SimpleSearchBar
import com.example.keeppy.presentation.screen.components.SimpleTopBar
import com.example.keeppy.presentation.screen.home.utils.TaskCardAction


enum class TopBarState {
    IDLE, SEARCH
}

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    var dialogDeleteState by remember { mutableStateOf(false) }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navHostController.navigate(Screen.AddTaskScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        },
        topBar = {
            var topBarState by remember { mutableStateOf(TopBarState.IDLE) }
            Crossfade(
                targetState = topBarState,
                animationSpec = tween(durationMillis = 500)
            ) { state ->
                when (state) {

                    TopBarState.IDLE -> {
                        SimpleTopBar(
                            modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp),
                            onSearchClicked = { topBarState = TopBarState.SEARCH }
                        )
                    }

                    TopBarState.SEARCH -> {
                        SimpleSearchBar(
                            modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp),
                            onPerformQuery = { },
                            onCancelClicked = { topBarState = TopBarState.IDLE }
                        )
                    }
                }

            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            TaskListItems(
                tasks = uiState.tasks,

                onClickTask = { taskClicked ->

                },
                onCLickEdit = { id ->
                    navHostController.navigate(Screen.EditTaskScreen.route + "?id=$id")
                },
                onClickDelete = { task ->
                    dialogDeleteState = true
                    viewModel.onSetTaskDeleted(task)
                }
            )
        }






        SimpleAlertDialog(
            openDialog = dialogDeleteState,
            title = "Confirm",
            text = "This action is not reversible. Are you sure you wish to delete?",
            positiveButtonText = "YES",
            negativeButtonText = "NO",
            onClick = {
                viewModel.onDeleteTask()
                dialogDeleteState = false
            },
            onDismiss = {
                dialogDeleteState = false
            }
        )

        /* if (dialogDeleteState) {

             MyTaskAlertDialog(
                 title = "Delete Task",
                 textDescription = "Do You Really Want To Delete This Task?",
                 onConfirmClick = {
                     viewModel.onDeleteTask()
                     dialogDeleteState = false
                 },
                 onDismissClick = {
                     dialogDeleteState = false
                 }
             )
         }*/
    }


}

@Composable
private fun TaskListItems(
    tasks: List<TaskView>,
    onClickTask: (TaskView) -> Unit,
    onCLickEdit: (Int) -> Unit,
    onClickDelete: (TaskView) -> Unit
) {
    LazyVerticalGrid(
        state = rememberLazyGridState(),
        columns = GridCells.Fixed(2),
        content = {

            items(tasks) { task ->
                TaskCard(
                    task = task,
                    onEditClicked = { onCLickEdit(task.id) },
                    onDeleteClicked = { onClickDelete(task) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable { onClickTask(task) }
                )
            }

        }
    )

    /*
        LazyColumn {
            items(tasks) { task ->
                TaskCard(
                    task = task,
                    onEditClicked = {
                        onCLickEdit(task.id)
                    },
                    onDeleteClicked = {
                        onClickDelete(task)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable { onClickTask(task) }
                )


            }
        }

     */

}

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    task: TaskView,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    var menuExpandedState by remember { mutableStateOf(false) }

    val colorPriority = if (isSystemInDarkTheme()) {
        task.priority.colorDark
    } else {
        task.priority.colorLight
    }


    MyTaskCard(
        modifier = modifier.wrapContentHeight(Alignment.CenterVertically),
        color = colorPriority
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
//            PriorityIndicator(color = colorPriority)


            Column(modifier = Modifier.wrapContentHeight()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Box(modifier = Modifier.wrapContentSize()) {
                        MyTaskIcon(
                            imageVector = Icons.Filled.MoreVert,
                            onClick = {
                                menuExpandedState = true
                            },
                            modifier = Modifier.wrapContentSize()
                        )
                        TaskCardDropDownMenu(
                            onActionClicked = { actionSelected ->
                                when (actionSelected) {
                                    TaskCardAction.Edit -> onEditClicked()
                                    TaskCardAction.Delete -> onDeleteClicked()
                                }
                            },
                            menuExpandedState = menuExpandedState,
                            menuExpandedStateChange = { menuExpandedState = false }
                        )


                    }

                }
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                    maxLines = 5,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                )

                TaskCardFooter(
                    date = task.date,
                    time = task.time,
                    status = task.status
                )

            }


        }


    }

}


@Composable
fun MyTaskCard(
    modifier: Modifier = Modifier,
    color: Color,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.height(IntrinsicSize.Min),
        elevation = 8.dp,
        content = content,
        backgroundColor = color
    )

}


@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun MyTaskCardPrev() {
    KEEPPYTheme {
        MyTaskCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colors.onPrimary),
            content = { },
            color = Color.Blue
        )
    }
}


@Composable
private fun PriorityIndicator(color: Color) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(22.dp)
            .background(color)

    )
}

@Composable
fun TaskCardDropDownMenu(
    onActionClicked: (TaskCardAction) -> Unit,
    menuExpandedState: Boolean,
    menuExpandedStateChange: () -> Unit
) {
    val actionOptionsLabels = listOf("Edit", "Delete")

    val actionOptions = listOf(
        TaskCardAction.Edit,
        TaskCardAction.Delete
    )

    MyTaskDropDownMenu(
        items = actionOptionsLabels,
        onItemIndexChange = { indexOptionSelected ->
            onActionClicked(actionOptions[indexOptionSelected])
        },
        expandedState = menuExpandedState,
        onExpandedStateChange = menuExpandedStateChange
    )


}

@Composable
fun MyTaskDropDownMenu(
    items: List<String>,
    onItemIndexChange: (Int) -> Unit,
    expandedState: Boolean,
    onExpandedStateChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        expanded = expandedState,
        onDismissRequest = { onExpandedStateChange() },
        modifier = modifier
    ) {
        items.forEachIndexed { index, label ->
            DropdownMenuItem(
                onClick = {
                    onItemIndexChange(index)
                    onExpandedStateChange()
                },
                content = {
                    Text(text = label)
                }
            )
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun MyTaskDropDownMenuPrev() {
    KEEPPYTheme {
        val items = listOf("Item 1", "Item 2", "Item3")
        var itemSelected by remember { mutableStateOf(items.first()) }
        var expandedState by remember { mutableStateOf(false) }

        Row(Modifier.fillMaxWidth()) {
            Text(
                text = itemSelected,
                modifier = Modifier.clickable { expandedState = true }
            )
            MyTaskDropDownMenu(
                items = items,
                onItemIndexChange = { itemIndexSelected ->
                    itemSelected = items[itemIndexSelected]
                },
                expandedState = expandedState,
                onExpandedStateChange = { expandedState = false })

        }
    }
}

@Composable
fun TaskCardFooter(
    date: String,
    time: String,
    status: StatusView
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        ScheduleIndicator(
            date = date,
            time = time,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        StatusIndicator(
            status = status,
            modifier = Modifier.align(Alignment.CenterEnd)
        )


    }

}


@Composable
fun ScheduleIndicator(
    date: String,
    time: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        MyTaskIcon(
            imageVector = Icons.Filled.DateRange,
            modifier = Modifier
                .alpha(0.5f)
                .size(12.dp)
        )
        Text(
            text = date,
            style = MaterialTheme.typography.caption,
            fontSize = 9.sp
        )

        Spacer(modifier = Modifier.width(8.dp))

        MyTaskIcon(
            imageVector = Icons.Filled.Alarm,
            modifier = Modifier
                .alpha(0.8f)
                .size(12.dp)
        )
        Text(
            text = time,
            style = MaterialTheme.typography.caption,
            fontSize = 9.sp
        )
    }
}

@Composable
fun MyTaskIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String? = null,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = MaterialTheme.colors.onPrimary,
        )
    }


}

@Preview
@Composable
fun MyTaskIconPrev() {
    MyTaskIcon(imageVector = Icons.Filled.Search)
}


@Composable
fun StatusIndicator(
    status: StatusView,
    modifier: Modifier = Modifier
) {
    val statusName = when (status) {
        StatusView.TODO -> "todo"
        StatusView.PROGRESS -> "progress"
        StatusView.DONE -> "done"
    }

    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onPrimary.copy(0.5f),
                shape = RoundedCornerShape(100.dp)
            )
    ) {
        Text(
            text = statusName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 8.dp)
        )
    }

}


@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Preview() {
    val task = TaskView(
        1,
        "Android Jetpack Kotlin",
        "27/09/1997",
        "1800",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged",
        PriorityView.MEDIUM,
        StatusView.TODO
    )
    KEEPPYTheme {
        TaskCard(
            task = task,
            onEditClicked = { },
            onDeleteClicked = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }

}

















































