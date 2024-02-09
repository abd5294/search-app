package com.abdur.search.feature_search.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdur.search.feature_search.data.local.entity.WordEntity

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(infos : List<WordEntity>)

    @Query("DELETE FROM wordentity where word in (:words)")
    suspend fun deleteWord(words : List<String>)

    @Query("SELECT * FROM wordentity where word like '%' || :word || '%'")
    suspend fun getWord(word : String) : List<WordEntity>
}