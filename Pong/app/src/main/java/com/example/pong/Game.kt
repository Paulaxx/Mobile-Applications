package com.example.pong

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Game")
data class Game (

    @ColumnInfo(name = "level") var level: String = "",
    @ColumnInfo(name = "points") var points: Int = 0,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id : Long = 0
)