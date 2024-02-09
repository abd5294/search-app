package com.abdur.search.feature_search.domain.use_case

import android.util.Log
import com.abdur.search.core.util.Resource
import com.abdur.search.feature_search.data.repository.WordRepositoryImpl
import com.abdur.search.feature_search.domain.model.Word
import com.abdur.search.feature_search.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWord(private val repository : WordRepository) {
    operator fun invoke(word : String) : Flow<Resource<List<Word>>> {
        if (word.isEmpty()){
            return flow {  }
        }
        Log.d("jazila", word)
        return repository.getWords(word)
    }
}