package com.abdur.search.feature_search.data.util

import com.abdur.search.feature_search.domain.model.Meaning
import java.lang.reflect.Type

interface JsonParser {
    fun fromJson (json : String, type : Type) : List<Meaning>?
    fun <T> toJson(obj : T?, type : Type) : String?
}