package com.example.nisa_blossom.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nisa_blossom.Data.dao.NoteDao
import com.example.nisa_blossom.Data.dao.WishlistDao
import com.example.nisa_blossom.Data.entity.NoteEntity
import com.example.nisa_blossom.Data.entity.WishlistEntity

@Database(
    entities = [
        NoteEntity::class,
        WishlistEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    abstract fun wishlistDao(): WishlistDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nisa_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}