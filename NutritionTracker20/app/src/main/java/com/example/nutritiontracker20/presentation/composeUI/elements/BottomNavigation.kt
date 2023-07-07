package com.example.nutritiontracker20.presentation.composeUI.elements

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.nutritiontracker20.R
import com.example.nutritiontracker20.utils.CREATE_PLAN_SCREEN
import com.example.nutritiontracker20.utils.HOME_PAGE
import com.example.nutritiontracker20.utils.PROFILE_SCREEN

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyBottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavigationItemData("Create Plan", ImageVector.vectorResource(id = R.drawable.restaurant_24px), CREATE_PLAN_SCREEN),
        BottomNavigationItemData("Home Page", Icons.Default.Home, HOME_PAGE),
        BottomNavigationItemData("Profile", Icons.Default.Person, PROFILE_SCREEN)
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) },
                selected = currentRoute == item.route,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.route) {
                        // Optional: Add additional navigation options
                        navController.graph.startDestinationRoute?.let{
                            screen_route ->
                            popUpTo(screen_route) {
                                saveState=true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
//                                popUpTo(item.route)
                    }
                }
            )
        }
    }
}

@Composable
fun NavController.shouldShowBottomNavigationAsState(shouldHideRoutes: List<String>): State<Boolean> {
    val shouldShowBottomNavigation = remember { mutableStateOf(false) }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            shouldShowBottomNavigation.value = shouldHideRoutes.any { it == destination.route }
        }
        addOnDestinationChangedListener(listener)
        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return shouldShowBottomNavigation
}

data class BottomNavigationItemData(
    val label: String,
    val icon: ImageVector,
    val route: String
)