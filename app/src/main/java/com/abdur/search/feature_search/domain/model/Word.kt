package com.abdur.search.feature_search.domain.model

import com.abdur.search.feature_search.data.remote.dto.MeaningDto

data class Word(
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String
)