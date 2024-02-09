package com.abdur.search.feature_search.data.remote.dto

import com.abdur.search.feature_search.data.local.entity.WordEntity
import com.abdur.search.feature_search.domain.model.Word

data class WordDto(
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val word: String
){
    fun toWordEntity() : WordEntity {
        return WordEntity(
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            word = word
        )
    }
}
