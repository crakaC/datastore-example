package com.crakac.datastoreexample.ui

import android.os.Bundle
import android.text.format.DateUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crakac.datastoreexample.ui.theme.DataStoreSampleTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Users(
                        onClickAdd = viewModel::addItem,
                        onClickClear = viewModel::clearItems,
                        onClickItem = viewModel::updateItem,
                        data = viewModel.userPreferences.dataMap.toList()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Users(
    onClickAdd: () -> Unit = {},
    onClickClear: () -> Unit = {},
    onClickItem: (id: String) -> Unit = {},
    data: List<Pair<String, Long>> = emptyList()
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        stickyHeader {
            Row(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                ,
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(onClick = onClickAdd) {
                    Text(text = "Add")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onClickClear) {
                    Text(text = "Clear")
                }
            }
        }
        items(data) { (id, updatedAt) ->
            User(id = id, updatedAt = updatedAt, onClick = onClickItem)
        }
    }
}

@Composable
fun User(
    modifier: Modifier = Modifier,
    id: String,
    updatedAt: Long,
    onClick: (id: String) -> Unit
) {
    val relativeTime = DateUtils.getRelativeTimeSpanString(updatedAt)
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick(id) }
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "$id: $relativeTime"
        )
    }
}

@Preview(showBackground = true, heightDp = 200)
@Composable
fun DefaultPreview() {
    DataStoreSampleTheme {
        Surface {
            Users(
                data = (1..10).map {
                    it.toString() to Date().time
                }
            )
        }
    }
}