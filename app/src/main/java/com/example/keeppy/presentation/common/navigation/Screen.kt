package com.example.keeppy.presentation.common.navigation

sealed class Screen(val route : String){
    object HomeScreen : Screen("home_screen")
    object AddTaskScreen : Screen("add_task_screen")
    object EditTaskScreen : Screen("edit_task_screen")
}
