package com.abdur.search.feature_search.data.remote.dto

import com.abdur.search.feature_search.domain.model.Definition
import com.abdur.search.feature_search.domain.model.Word

data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
){
    fun toDefinition() : Definition {
        return Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}