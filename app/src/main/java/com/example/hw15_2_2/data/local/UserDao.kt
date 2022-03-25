package com.example.hw15_2_2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import com.example.hw15_2_2.model.User
import com.example.hw15_2_2.model.UserFromServer

@Dao
interface UserDao {
    @Insert(onConflict = IGNORE)
    fun insertUserList(vararg userList:UserFromServer)

    @Insert
    fun insertUser(user: UserFromServer)
}