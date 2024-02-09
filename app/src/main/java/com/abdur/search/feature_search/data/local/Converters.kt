package com.abdur.search.feature_search.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.abdur.search.feature_search.data.util.JsonParser
import com.abdur.search.feature_search.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters (
    private val jsonParser : JsonParser
){
    @TypeConverter
    fun fromMeaningsJson(json : String) : List<Meaning>{
        return jsonParser.fromJson(
            json,
            object : TypeToken<List<Meaning>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings : List<Meaning>) : String{
        return jsonParser.toJson(
            meanings,
            object : TypeToken<List<Meaning>>(){}.type
        ) ?: "[]"
    }
}
