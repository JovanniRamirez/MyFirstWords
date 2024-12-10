package com.example.myfirstwords

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WordRepository = WordRepository(application)
    private val allWords: LiveData<List<WordList>>?
    private val searchResults: MutableLiveData<List<WordList>>

    init {
        allWords = repository.allWords
        searchResults = repository.searchResults
    }

    fun insertWord(word: WordList) {
        repository.insertWord(word)
    }

    fun findWord(word: String) {
        repository.findWord(word)
    }

    fun deleteWord(word: String) {
        repository.deleteWord(word)
    }

    fun getSearchResults(): MutableLiveData<List<WordList>> {
        return searchResults
    }

    fun getAllWords(): LiveData<List<WordList>>? {
        return allWords
    }
}