package com.example.keeppy.presentation.screen.task.add_task

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.keeppy.presentation.screen.home.MyTaskIcon
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTaskScreen(navController: NavController, viewModel: AddTaskViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val uiState = rememberAddTaskScreenState(addTaskViewModel = viewModel)
    val formState = rememberAddTaskFormState(addTaskViewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
        uiState.uiEvent.collectLatest { uiEvent ->
            when (uiEvent) {
                is AddTaskScreenEvent.SaveTaskSuccess -> {
                    Toast.makeText(context, "saved ~", Toast.LENGTH_SHORT).show()
                    navController.navigateUp()
                }
                is AddTaskScreenEvent.SaveTaskFailed -> {
                    uiState.showSnackbar(uiEvent.message)
                }

            }
        }
    }

    Scaffold(
        scaffoldState = uiState.scaffoldState,
        topBar = {
            MyTaskTopAppBar(
                title = "Add Task",
                navigationIcon = {

                    MyTaskIcon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        onClick = {
                            navController.navigateUp()
                            formState.onSaveTask()
                        }
                    )

                },
                actions = {
                    MyTaskIcon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = "",
                        onClick = {
                            keyboardController?.hide()
                            formState.onSaveTask()
                        }
                    )
                }
            )


        },
        content = {
            Column(Modifier.padding(it)) {
                AddTaskForm(
                    addTaskFormState = formState,
                    keyboardController = keyboardController,
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
    )


}

@Composable
fun MyTaskTopAppBar(
    title: String,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {

    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = navigationIcon,
        actions = actions
    )

}

































