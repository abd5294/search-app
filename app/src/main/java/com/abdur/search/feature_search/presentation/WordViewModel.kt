package com.abdur.search.feature_search.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdur.search.core.util.Resource
import com.abdur.search.feature_search.domain.use_case.GetWord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val getWord : GetWord
) : ViewModel() {

    private var _query = mutableStateOf("")
    var query = _query

    private var _state = mutableStateOf(WordState())
    var state = _state

    private var searchJob : Job? = null

    fun onSearch(query : String){
        _query.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWord(_query.value)
                .onEach { result ->
                    when(result){
                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                state = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(
                                state = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                state = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }.launchIn(this)
        }
    }
}