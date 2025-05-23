package ie.setu.donationx.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.donationx.ui.screens.about.AboutScreen
import ie.setu.donationx.ui.screens.details.DetailsScreen
import ie.setu.donationx.ui.screens.donate.ReviewScreen
import ie.setu.donationx.ui.screens.home.HomeScreen
import ie.setu.donationx.ui.screens.login.LoginScreen
import ie.setu.donationx.ui.screens.profile.ProfileScreen
import ie.setu.donationx.ui.screens.register.RegisterScreen
import ie.setu.donationx.ui.screens.report.ListScreen

@Composable
fun NavHostProvider(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: AppDestination,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

        composable(route = Donate.route) {
            //call our 'review' Screen Here
            ReviewScreen(modifier = modifier)
        }

        composable(route = Home.route) {
            //call our 'Home' Screen Here
            HomeScreen(modifier = modifier)
        }

        composable(route = Report.route) {
            //call our 'list' Screen Here
            ListScreen(modifier = modifier,
                onClickReviewDetails = {
                        reviewId : String ->
                    navController.navigateToReviewDetails(reviewId)
                },
            )
        }
        composable(route = About.route) {
            //call our 'About' Screen Here
            AboutScreen(modifier = modifier)
        }
        composable(route = Login.route) {
            //call our 'Login' Screen Here
            LoginScreen(
                navController = navController,
                onLogin = { navController.popBackStack() }
            )
        }

        composable(route = Register.route) {
            //call our 'Register' Screen Here
            RegisterScreen(
                navController = navController,
                onRegister = { navController.popBackStack() }
            )
        }


        composable(
            route = Details.route,
            arguments = Details.arguments
        )
        { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(Details.idArg)
            if (id != null) {
                DetailsScreen()
            }
        }

        composable(route = Profile.route) {
            ProfileScreen(
                onSignOut = {
                    navController.popBackStack()
                    navController.navigate(Login.route) {
                        popUpTo(Home.route) { inclusive = true }
                    }
                }
            )


        }
    }
}

private fun NavHostController.navigateToReviewDetails(reviewId: String) {
    this.navigate("details/$reviewId")
}

