package com.example.doolio.Data

import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: WordDao) {

    val readAllWords:LiveData<List<Word>> = wordDao.readAllData()

    suspend fun addWord(word: Word){
        wordDao.addWord(word)
    }

    suspend fun updateWord(word:Word){
        wordDao.update(word)
    }

    suspend fun deleteAll(){
        wordDao.deleteAll()
    }
    suspend fun deleteuser(word: Word){
        wordDao.delete(word)
    }

    suspend fun searchQuery(searchquery:String):LiveData<List<Word>>{
        return wordDao.searchDatabase(searchquery)

    }




}