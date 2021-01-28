package com.example.doolio.Data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application): AndroidViewModel(application) {

    val readAllData:LiveData<List<Word>>
    private val repository:WordRepository

    init{
        val userDao = WordDatabase.getDatabase(application).userDao()

        repository = WordRepository(userDao)
        readAllData = repository.readAllWords
    }


    fun addWord(word: Word){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWord(word)
        }
    }
    fun updateWord(word: Word){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWord(word)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun DeleteNote(word: Word){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteuser(word)
        }
    }

    


}