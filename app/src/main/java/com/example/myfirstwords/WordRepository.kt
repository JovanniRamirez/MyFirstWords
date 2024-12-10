package com.example.myfirstwords

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.async
import kotlinx.coroutines.Dispatchers

class WordRepository(application: Application) {
    val searchResults = MutableLiveData<List<WordList>>()

    private var wordDao: WordListDAO?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allWords: LiveData<List<WordList>>?
    init {
        val db: WordDatabase? =
            WordDatabase.getDatabase(application)
        wordDao = db?.wordDao()
        allWords = wordDao?.getAllWords()
    }

    fun insertWord(newWord: WordList) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newWord)
        }
    }

    private fun asyncInsert(word: WordList) {
        wordDao?.insertWord(word)
    }

    fun deleteWord(word: String) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(word)

        }
    }

    private fun asyncDelete(word: String) {
        wordDao?.deleteWord(word)
    }

    fun findWord(word: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(word).await()
        }
    }

    private fun asyncFind(word: String): Deferred<List<WordList>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async wordDao?.findWord(word)
    }
}