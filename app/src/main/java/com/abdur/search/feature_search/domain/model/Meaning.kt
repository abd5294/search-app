package com.abdur.search.feature_search.domain.model

import com.abdur.search.feature_search.data.remote.dto.DefinitionDto

data class Meaning(
    val antonyms: List<Any>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<Any>
)