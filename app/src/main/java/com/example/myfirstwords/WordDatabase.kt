package com.example.myfirstwords
import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room

@Database(entities = [(WordList::class)], version = 1)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordListDAO

    companion object {
        private var INSTANCE: WordDatabase? = null
        internal fun getDatabase(context: Context): WordDatabase? {
            if (INSTANCE == null) {
                synchronized(WordDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            WordDatabase::class.java,
                            "word_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}