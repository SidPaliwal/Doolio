package com.example.doolio.Data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWord(word: Word)

    @Update
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word:Word)

    @Query("SELECT * FROM word_table")
    fun readAllData():LiveData<List<Word>>

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM word_table WHERE Title LIKE :searchquery OR Description LIKE :searchquery")
    suspend fun searchDatabase(searchquery:String):LiveData<List<Word>>
}