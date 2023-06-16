package com.hitechtalents.assessment.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.hitechtalents.assessment.presentation.ui.profile_details.ProfileScreen

import com.hitechtalents.assessment.presentation.ui.sign_up.SignUpScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.SignUp.route
    ) {
        composable(
            route = Screen.SignUp.route
        ) {
            SignUpScreen(navController)
        }
        composable(route = Screen.ProfileDetails.route) {
            ProfileScreen()
        }
    }
}