package com.crakac.datastoreexample.ui.preferences

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.crakac.datastoreexample.ui.Users

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PreferencesRoute(
    modifier: Modifier = Modifier,
    viewModel: PreferencesViewModel = hiltViewModel()
) {
    val state by viewModel.users.collectAsStateWithLifecycle()
    Users(
        modifier = modifier,
        onClickAdd = viewModel::add,
        onClickClear = viewModel::deleteAll,
        onClickItem = viewModel::update,
        data = state
    )
}