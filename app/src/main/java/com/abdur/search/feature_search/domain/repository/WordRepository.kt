package com.abdur.search.feature_search.domain.repository

import com.abdur.search.core.util.Resource
import com.abdur.search.feature_search.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    fun getWords(word : String) : Flow<Resource<List<Word>>>
}