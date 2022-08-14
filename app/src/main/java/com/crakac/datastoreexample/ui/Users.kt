package com.crakac.datastoreexample.ui

import android.text.format.DateUtils
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crakac.datastoreexample.ui.theme.DataStoreSampleTheme
import java.util.*


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Users(
    modifier: Modifier = Modifier,
    onClickAdd: () -> Unit = {},
    onClickClear: () -> Unit = {},
    onClickItem: (id: String) -> Unit = {},
    data: List<Pair<String, Long>> = emptyList()
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        stickyHeader {
            Row(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
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