package com.crakac.datastoreexample.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.crakac.datastoreexample.R
import com.crakac.datastoreexample.design.Icon.DrawableResourceIcon
import com.crakac.datastoreexample.navigation.NavigationDestination
import com.crakac.datastoreexample.navigation.ProtoDestination
import com.crakac.datastoreexample.navigation.RoomDestination
import com.crakac.datastoreexample.navigation.TopLevelDestination

@Composable
fun rememberAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController()
): AppState {
    return remember(navController, windowSizeClass) {
        AppState(navController, windowSizeClass)
    }
}

@Stable
class AppState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val topLevelDestination: List<TopLevelDestination> = listOf(
        TopLevelDestination(
            route = ProtoDestination.route,
            destination = ProtoDestination.destination,
            selectedIcon = DrawableResourceIcon(R.drawable.ic_hive),
            unselectedIcon = DrawableResourceIcon(R.drawable.ic_hive_outlined),
            iconTextId = R.string.proto
        ),
        TopLevelDestination(
            route = RoomDestination.route,
            destination = RoomDestination.destination,
            selectedIcon = DrawableResourceIcon(R.drawable.ic_room),
            unselectedIcon = DrawableResourceIcon(R.drawable.ic_room_off),
            iconTextId = R.string.room
        )
    )

    fun navigate(destination: NavigationDestination, route: String? = null) {
        if (destination is TopLevelDestination) {
            navController.navigate(route ?: destination.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        } else {
            navController.navigate(route ?: destination.route)
        }
    }
}