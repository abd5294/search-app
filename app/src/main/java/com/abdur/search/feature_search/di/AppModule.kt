package com.abdur.search.feature_search.di

import android.content.Context
import androidx.room.Room
import com.abdur.search.feature_search.data.local.Converters
import com.abdur.search.feature_search.data.local.WordDatabase
import com.abdur.search.feature_search.data.remote.WordApi
import com.abdur.search.feature_search.domain.repository.WordRepository
import com.abdur.search.feature_search.data.util.GsonParser
import com.abdur.search.feature_search.data.repository.WordRepositoryImpl
import com.abdur.search.feature_search.domain.use_case.GetWord
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWordDatabase( @ApplicationContext context : Context) : WordDatabase {
        return Room.databaseBuilder(
            context,
            WordDatabase::class.java,
            "words_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideWordApi() : WordApi {
        return Retrofit.Builder()
            .baseUrl(WordApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WordApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWordRepository(api : WordApi, db : WordDatabase) : WordRepository {
        return WordRepositoryImpl(api, db.wordDao)
    }

    @Provides
    @Singleton
    fun provideWordUseCase(repository: WordRepository) : GetWord{
        return GetWord(repository)
    }
}