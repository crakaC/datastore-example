package com.crakac.datastoreexample.ui.proto

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.crakac.datastoreexample.ui.Users

@Composable
fun ProtoRoute(
    modifier: Modifier = Modifier,
    viewModel: ProtoViewModel = hiltViewModel()
) {
    val state by viewModel.users.collectAsStateWithLifecycle()
    Users(
        modifier = modifier,
        onClickAdd = viewModel::addItem,
        onClickClear = viewModel::clearItems,
        onClickItem = viewModel::updateItem,
        data = state
    )
}