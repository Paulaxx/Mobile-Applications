package com.example.pong

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDAO {

    @Query("select * from Game")
    fun getAll(): List<Game>

    @Insert
    fun insertAll(vararg game: Game)

    @Query("delete from Game")
    fun deleteAll()
}