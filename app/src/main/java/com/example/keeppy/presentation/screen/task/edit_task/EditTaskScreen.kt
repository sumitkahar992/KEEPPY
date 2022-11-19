package com.example.keeppy.presentation.screen.task.edit_task

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.keeppy.presentation.screen.home.MyTaskIcon
import com.example.keeppy.presentation.screen.task.add_task.MyTaskTopAppBar
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditTaskScreen(navController: NavController, viewModel: EditTaskViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val uiState = rememberEditTaskScreenState(editTaskViewModel = viewModel)
    val formState = rememberEditTaskFormState(editTaskViewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
        uiState.uiEvent.collectLatest { uiEvent ->
            when (uiEvent) {
                is EditTaskScreenEvent.EditTaskSuccess -> {
                    Toast.makeText(context, "Edited Successfully", Toast.LENGTH_SHORT).show()
                    navController.navigateUp()
                }

                is EditTaskScreenEvent.EditTaskFailed -> {
                    uiState.showSnackbar(uiEvent.message)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = uiState.scaffoldState,
        topBar = {
            MyTaskTopAppBar(
                title = "Edit task",
                navigationIcon = {
                    MyTaskIcon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        onClick = { navController.popBackStack() }
                    )
                },
                actions = {
                    MyTaskIcon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = "",
                        onClick = {
                            keyboardController?.hide()
                            formState.onEditTask()
                        }
                    )
                }
            )
        },
        content = {
            Column(Modifier.padding(it)) {
                EditTaskForm(
                    editTaskFormState = formState,
                    keyboardController = keyboardController,
                    modifier = Modifier.fillMaxSize()
                )
            }

        }

    )


}






































