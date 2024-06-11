package com.example.sounds.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name ="id")
    val id: Int = 0,
    @ColumnInfo(name ="fullname")
    val fullName: String,
    @ColumnInfo(name ="email")
    val email: String,
    @ColumnInfo(name ="phone")
    val phone: String,
    @ColumnInfo(name ="password")
    val password: String
)
