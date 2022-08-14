package com.crakac.datastoreexample.ui.proto

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.crakac.datastoreexample.ui.Users

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ProtoRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    viewModel: ProtoViewModel = hiltViewModel()
) {
    val state by viewModel.userPreferences.collectAsStateWithLifecycle()
    Users(
        modifier = modifier,
        onClickAdd = viewModel::addItem,
        onClickClear = viewModel::clearItems,
        onClickItem = viewModel::updateItem,
        data = state
    )
}