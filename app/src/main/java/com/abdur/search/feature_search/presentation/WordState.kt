package com.abdur.search.feature_search.presentation

import com.abdur.search.core.util.Resource
import com.abdur.search.feature_search.domain.model.Word
import kotlinx.coroutines.flow.Flow

data class WordState(
    val state : List<Word> = emptyList(),
    val isLoading : Boolean = false
) {
}