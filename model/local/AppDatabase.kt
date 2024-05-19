package com.dev.mahamat.andal_ia.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Chat::class, Conversation::class], version = 7, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): ChatsDao
}