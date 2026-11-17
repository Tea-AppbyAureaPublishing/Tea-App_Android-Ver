
package com.aureapubishing.teaApp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aureapubishing.teaApp.ui.layout.AppLayout
import com.aureapubishing.teaApp.ui.screens.home.HomeScreen
import com.aureapubishing.teaApp.ui.screens.infusions.InfusionsScreen
import com.aureapubishing.teaApp.ui.screens.profile.ProfileScreen
import com.aureapubishing.teaApp.ui.screens.profile.CreateTeaScreen
import com.aureapubishing.teaApp.ui.screens.teas.TeasScreen
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.aureapubishing.teaApp.R

sealed class Screen(
    val route: String,
    val label: String,
    @DrawableRes val iconRes: Int
) {
    data object Home : Screen("home", "Inicio", R.drawable.home)
    data object Infusions : Screen("infusiones", "Infusiones", R.drawable.infusions)
    data object Teas : Screen("tes", "Tés", R.drawable.tea_leafs)
    data object Profile : Screen("perfil", "Perfil", R.drawable.profile)
    data object CreateTea : Screen("createTea", "Crear té", R.drawable.tea_leafs)
}

val bottomBarScreens = listOf(
    Screen.Home,
    Screen.Infusions,
    Screen.Teas
)

fun NavHostController.navigateToTab(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNext = {
                    navController.navigateToTab(Screen.Infusions.route)
                }
            )
        }

        composable(Screen.Infusions.route) {
            InfusionsScreen(
                onNext = {
                    navController.navigateToTab(Screen.Teas.route)
                }
            )
        }

        composable(Screen.Teas.route) {
            TeasScreen(
                onNext = {
                    navController.navigateToTab(Screen.Profile.route)
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onNext = {
                    navController.navigateToTab(Screen.Home.route)
                },
                onCreateTeaClick = {
                    navController.navigate(Screen.CreateTea.route)
                }
            )
        }

        composable(Screen.CreateTea.route) {
            CreateTeaScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val currentRoute = navController
        .currentBackStackEntryAsState()
        .value?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomBarScreens.forEach { screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigateToTab(screen.route)
                        },
                        icon = {
                            InsertImage(imageResId = screen.iconRes)
                        },
                        label = {
                            Text(screen.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        AppLayout(
            modifier = Modifier.padding(innerPadding),
            showHeader = currentRoute != "login",
            onProfileClick = {
                navController.navigateToTab(Screen.Profile.route)
            }
        ) {
            AppNavHost(
                navController = navController,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun InsertImage(
    @DrawableRes imageResId: Int
) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = "Icono de navegación"
    )
}
