package com.abdur.search.feature_search.data.util

import com.abdur.search.feature_search.domain.model.Meaning
import com.google.gson.Gson
import java.lang.reflect.Type

class GsonParser(
    private val gson : Gson
) : JsonParser {
    override fun fromJson(json: String, type: Type) : List<Meaning>? {
        return gson.fromJson(json, type)
    }

    override fun <T> toJson(obj: T?, type: Type): String? {
        return gson.toJson(obj, type)
    }
}