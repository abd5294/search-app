package com.abdur.search.feature_search.data.repository

import com.abdur.search.core.util.Resource
import com.abdur.search.feature_search.data.local.WordDao
import com.abdur.search.feature_search.data.remote.WordApi
import com.abdur.search.feature_search.domain.repository.WordRepository
import com.abdur.search.feature_search.domain.model.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordRepositoryImpl(
    private val api: WordApi,
    private val dao: WordDao,
) : WordRepository {
    override fun getWords(word: String): Flow<Resource<List<Word>>> = flow {
        emit(Resource.Loading())

        val words = dao.getWord(word).map { it.toWord() }
        emit(Resource.Loading(data = words))

        try {
            val remoteWordInfos = api.getWord(word)
            dao.deleteWord(remoteWordInfos.map { it.word })
            dao.insertWord(remoteWordInfos.map { it.toWordEntity() })
        } catch (e : HttpException){
            emit(
                Resource.Error(
                    data = words,
                    message = "Please check your internet connection and try again!"
                )
            )
        } catch (e : IOException){
            emit(
                Resource.Error(
                    data = words,
                    message = "An error occurred. try again later"
                )
            )
        }
        val currentWords = dao.getWord(word).map { it.toWord() }
        emit(Resource.Success(data = currentWords))
    }
}