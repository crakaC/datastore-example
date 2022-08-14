package com.crakac.datastoreexample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.crakac.datastoreexample.design.Icon.DrawableResourceIcon
import com.crakac.datastoreexample.design.Icon.ImageVectorIcon
import com.crakac.datastoreexample.navigation.ProtoDestination
import com.crakac.datastoreexample.navigation.RoomDestination
import com.crakac.datastoreexample.navigation.TopLevelDestination
import com.crakac.datastoreexample.ui.proto.ProtoRoute
import com.crakac.datastoreexample.ui.room.RoomRoute
import com.crakac.datastoreexample.ui.theme.DataStoreSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState =
                rememberAppState(windowSizeClass = calculateWindowSizeClass(activity = this))
            DataStoreSampleTheme {
                Scaffold(
                    bottomBar = {
                        AppBottomBar(
                            destinations = appState.topLevelDestination,
                            onNavigateToDestination = appState::navigate,
                            currentDestination = appState.currentDestination
                        )
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = appState.navController,
                        startDestination = ProtoDestination.route
                    ) {
                        composable(ProtoDestination.route) {
                            ProtoRoute(
                                windowSizeClass = appState.windowSizeClass,
                                modifier = Modifier.padding(paddingValues)
                            )
                        }
                        composable(RoomDestination.route) {
                            RoomRoute(
                                modifier = Modifier.padding(paddingValues)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AppBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    NavigationBar {
        destinations.forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    when (icon) {
                        is DrawableResourceIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null
                        )
                        is ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null
                        )
                    }
                },
                alwaysShowLabel = true,
                label = { Text(text = stringResource(id = destination.iconTextId)) }
            )
        }
    }
}