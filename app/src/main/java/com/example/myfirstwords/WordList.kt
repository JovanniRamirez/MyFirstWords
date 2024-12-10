package com.example.myfirstwords

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_list")
class WordList {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    @ColumnInfo(name = "wordSaved")
    var wordSaved: String? = null

    constructor() {}

    constructor(word: String?) {
        this.wordSaved = word
    }
}