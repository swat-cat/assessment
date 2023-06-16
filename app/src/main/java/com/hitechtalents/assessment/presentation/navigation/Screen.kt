package com.hitechtalents.assessment.presentation.navigation

sealed class Screen(val route: String) {
    object SignUp : Screen(route = "sign_up")
    object ProfileDetails : Screen(route = "profile_details")
}
