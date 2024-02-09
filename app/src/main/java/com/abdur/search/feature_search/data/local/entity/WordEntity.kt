package com.abdur.search.feature_search.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdur.search.feature_search.domain.model.Meaning
import com.abdur.search.feature_search.domain.model.Word

@Entity
data class WordEntity(
    val meanings : List<Meaning>,
    val phonetic : String,
    val word : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
) {
    fun toWord() : Word {
        return Word(
            meanings = meanings,
            phonetic = phonetic,
            word = word
        )
    }
}
