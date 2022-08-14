package com.crakac.datastoreexample.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.crakac.datastoreexample.navigation.ProtoDestination
import com.crakac.datastoreexample.navigation.RoomDestination
import com.crakac.datastoreexample.ui.proto.ProtoRoute
import com.crakac.datastoreexample.ui.room.RoomRoute

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    appState: AppState
) {
    NavHost(
        navController = appState.navController,
        startDestination = ProtoDestination.route
    ) {
        composable(ProtoDestination.route) {
            ProtoRoute(
                windowSizeClass = appState.windowSizeClass,
                modifier = modifier
            )
        }
        composable(RoomDestination.route) {
            RoomRoute(
                modifier = modifier
            )
        }
    }
}