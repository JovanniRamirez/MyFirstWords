package com.example.myfirstwords

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordListDAO {
    @Insert
    fun insertWord(word: WordList)

    @Query("SELECT * FROM word_list WHERE wordSaved = :word")
    fun findWord(word: String): List<WordList>

    @Query("DELETE FROM word_list WHERE wordSaved = :word")
    fun deleteWord(word: String)

    @Query("SELECT * FROM word_list ORDER BY wordSaved DESC")
    fun getAllWords(): LiveData<List<WordList>>
}