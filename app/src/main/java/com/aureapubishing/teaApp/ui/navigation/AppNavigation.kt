package com.aureapubishing.teaApp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aureapubishing.teaApp.R
import com.aureapubishing.teaApp.ui.layout.AppLayout
import com.aureapubishing.teaApp.ui.screens.home.HomeScreen
import com.aureapubishing.teaApp.ui.screens.infusions.InfusionsScreen
import com.aureapubishing.teaApp.ui.screens.login.LoginScreen
import com.aureapubishing.teaApp.ui.screens.login.RegisterScreen
import com.aureapubishing.teaApp.ui.screens.profile.EditProfileScreen
import com.aureapubishing.teaApp.ui.screens.profile.ProfileScreen
import com.aureapubishing.teaApp.ui.screens.teas.TeasScreen

//Mapa de rutas
sealed class Screen(
    val route: String,
    val label: String,
    @DrawableRes val iconRes: Int = 0
) {
    data object Login : Screen("login", "Iniciar sesión")
    data object Register : Screen("register", "Registrarse")

    data object Home : Screen("home", "Inicio", R.drawable.home)
    data object Infusions : Screen("infusiones", "Infusiones", R.drawable.infusions)
    data object Teas : Screen("tes", "Tés", R.drawable.tea_leafs)
    data object Profile : Screen("perfil", "Perfil", R.drawable.profile)
    // data object CreateTea : Screen("createTea", "Crear té")
    data object EditProfile : Screen("editProfile", "Editar perfil")
}

//Páginas en la barra de Navegación
val bottomBarScreens = listOf(Screen.Infusions, Screen.Home, Screen.Teas)

//Navegación sin ningún tipo de animación
fun NavHostController.navigateToTab(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

//Función que actúa como un contenedor donde cambia el contenido
// según la ruta inicial y según a donde vayamos
@Composable
fun AppNavHost(
    navController: NavHostController,
    onOpenProfile: () -> Unit,
    onLogin: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        //Los composables registran una ruta de navegación
        composable(Screen.Login.route) {
            LoginScreen(
                onBack = { navController.popBackStack() },
                onLoggedIn = {
                    onLogin()
                    navController.popBackStack(Screen.Login.route, inclusive = true)
                    navController.navigateToTab(Screen.Profile.route)
                },
                onRegister = { navController.navigate(Screen.Register.route) }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onBack = { navController.popBackStack() },
                onLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onNext = {
                    navController.navigateToTab(Screen.Infusions.route)
                })
        }

        composable(Screen.Infusions.route) {
            InfusionsScreen(
                onNext = {
                    navController.navigateToTab(Screen.Teas.route)
                }
            )
        }

        composable(Screen.Teas.route) {
            TeasScreen(onNext = onOpenProfile)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onEditProfileClick = { navController.navigate(Screen.EditProfile.route) },
                onCerrarSesionClick = {
                    // TODO(API): borrar el token guardado (y opcionalmente POST /auth/logout).
                    onLogout()
                    navController.navigate(Screen.Home.route) { popUpTo(0) { inclusive = true } }
                }
            )
        }

        composable(Screen.EditProfile.route) {
            EditProfileScreen(
                onConfirm = {
                    navController.popBackStack()
                }
            )
        }
    }
}


//Main es el centro de la app, lleva el control y observa las rutas
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val currentRoute = navController
        .currentBackStackEntryAsState()
        .value?.destination?.route

    // Detecta si estás en Login o en Register
    val isAccessScreen =
        currentRoute == Screen.Login.route ||
                currentRoute == Screen.Register.route

    //Guarda en memoria con remember Saveable si estas logueado o no
    var isLoggedIn by rememberSaveable { mutableStateOf(false) }

    // Único punto de entrada a Perfil: si no hay sesión, pide iniciarla
    val openProfile = {
        if (isLoggedIn) navController.navigateToTab(Screen.Profile.route)
        else navController.navigate(Screen.Login.route)
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (!isAccessScreen) {
                TeaBottomBar(
                    currentRoute = currentRoute,
                    onTabClick = { navController.navigateToTab(it) }
                )
            }
        }
    ) { innerPadding ->

        AppLayout(
            modifier = Modifier.padding(innerPadding),
            showHeader = currentRoute != Screen.Login.route,
            isProfile = currentRoute == Screen.Profile.route ||
                    currentRoute == Screen.EditProfile.route,
            onProfileClick = openProfile
        ) {
            AppNavHost(
                navController = navController,
                onOpenProfile = openProfile,
                onLogin = { isLoggedIn = true },
                onLogout = { isLoggedIn = false },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

//Es una barra personalizada y hecha a mano
@Composable
fun TeaBottomBar(
    currentRoute: String?,
    onTabClick: (String) -> Unit
) {
    val iconColor = MaterialTheme.colorScheme.onSecondary
    val selectorColor = MaterialTheme.colorScheme.outline

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(selectorColor)
            .navigationBarsPadding()
            .height(72.dp)
    ) {

        //Esto controla la el movimiento y tamaño de los elementos
        bottomBarScreens.forEach { screen ->
            val selected = currentRoute == screen.route

            val iconSize by animateDpAsState(
                targetValue = if (selected) 65.dp else 40.dp,
                animationSpec = tween(200),
                label = "iconSize"
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .drawBehind {
                        if (selected) {
                            drawCircle(
                                color = selectorColor,
                                radius = 50.dp.toPx(),
                                center = Offset(size.width / 2f, 25.dp.toPx())
                            )
                        }
                    }
                    .clickable(
                        indication = null,
                        interactionSource = null,
                        role = Role.Tab,
                        onClick = { onTabClick(screen.route) }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(screen.iconRes),
                    contentDescription = screen.label,
                    colorFilter = ColorFilter.tint(iconColor),
                    modifier = Modifier.size(iconSize)
                )
            }
        }
    }
}