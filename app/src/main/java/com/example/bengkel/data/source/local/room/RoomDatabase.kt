package com.example.bengkel.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bengkel.data.source.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao
}