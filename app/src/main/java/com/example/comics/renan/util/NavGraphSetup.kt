package com.example.comics.renan.util

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.comics.renan.presentation.comics_screen.ComicsScreen
import com.example.comics.renan.presentation.comics_screen.state.ComicsScreenViewModel

@Composable
fun NavGraphSetup(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = "comics_screen"){
        composable(route = "comics_screen"){
            val viewModel: ComicsScreenViewModel = hiltViewModel()
            ComicsScreen(state = viewModel.state, onEvent = viewModel::onEvent, viewModel = viewModel)
        }
    }
}