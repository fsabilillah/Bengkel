package com.example.bengkel.data.source.local

import com.example.bengkel.data.source.local.entity.UserEntity
import com.example.bengkel.data.source.local.room.RoomDao

class LocalDataSource(private val roomDao: RoomDao) {

    fun getUser() = roomDao.getUser()

    fun isLogin() = roomDao.isLogin()

    suspend fun logout() = roomDao.logout()

    suspend fun insertUser(userEntity: UserEntity) = roomDao.insertUser(userEntity)
}