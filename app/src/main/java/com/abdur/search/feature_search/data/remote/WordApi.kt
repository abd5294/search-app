package com.abdur.search.feature_search.data.remote

import com.abdur.search.feature_search.data.remote.dto.WordDto
import retrofit2.http.GET
import retrofit2.http.Path

interface WordApi {
    @GET("{word}")
    suspend fun getWord(
        @Path("word") query : String
    ) : List<WordDto>

    companion object{
        const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/"
    }
}