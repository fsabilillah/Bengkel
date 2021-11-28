package com.example.bengkel.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bengkel.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    //user
    @Query("SELECT * FROM user")
    fun getUser(): Flow<UserEntity>

    @Query("SELECT EXISTS (SELECT * FROM user)")
    fun isLogin(): Flow<Int>

    @Query("DELETE FROM user")
    suspend fun logout()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)
}