package com.example.keeppy.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.keeppy.presentation.screen.home.HomeScreen
import com.example.keeppy.presentation.screen.task.add_task.AddTaskScreen
import com.example.keeppy.presentation.screen.task.edit_task.EditTaskScreen


@Composable
fun NavigationComponent(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ){


        composable(Screen.HomeScreen.route){
            HomeScreen(navHostController = navController)
        }

         composable(Screen.AddTaskScreen.route){
             AddTaskScreen(navController = navController)
        }

         composable(
            route =  Screen.EditTaskScreen.route + "?id={id}",
             arguments = listOf(
                 navArgument(name = "id") {
                     type = NavType.IntType
                     defaultValue = 0
                 }
             )
         ){
             EditTaskScreen(navController = navController)
        }




    }


}
























