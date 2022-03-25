package com.example.hw15_2_2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class User(
     val _id: String,
    val firstName: String,
    val hobbies: List<String>,
    val image: String,
    val lastName: String,
    val nationalCode: String
)