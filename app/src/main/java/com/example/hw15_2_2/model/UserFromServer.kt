package com.example.hw15_2_2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserFromServer(
    @PrimaryKey val _id: String,
    val firstName: String,
    val lastName: String,
    val nationalCode: String
)
