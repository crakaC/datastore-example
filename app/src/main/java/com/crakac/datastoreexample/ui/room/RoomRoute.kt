package com.crakac.datastoreexample.ui.room

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.crakac.datastoreexample.ui.Users

@Composable
fun RoomRoute(
    modifier: Modifier,
    viewModel: RoomViewModel = hiltViewModel()
) {
    val state by viewModel.users.collectAsStateWithLifecycle()
    Users(
        modifier = modifier,
        onClickAdd = viewModel::add,
        onClickItem = viewModel::update,
        onClickClear = viewModel::delete,
        data = state
    )
}