package com.abdur.search

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdur.search.feature_search.presentation.WordItem
import com.abdur.search.feature_search.presentation.WordViewModel
import com.abdur.search.ui.theme.SearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchTheme {
                val viewModel: WordViewModel = hiltViewModel()
                val state = viewModel.state.value

                Surface(modifier = Modifier.padding(12.dp)) {

                    Column {
                        Spacer(modifier = Modifier.height(12.dp))
                        TextField(
                            value = viewModel.query.value,
                            onValueChange = { viewModel.onSearch(it) },
                            placeholder = {
                                Text(text = "Search..")
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(24.dp))
                                .border(1.dp, Color.Red, shape = RoundedCornerShape(24.dp))
                                .fillMaxWidth(),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search"
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(state.state.size) { i ->
                                if (i > 0) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                                WordItem(word = state.state[i])
                                if (i < state.state.size - 1) Divider()
                            }
                        }

                    }
                }
            }
        }
    }
}
