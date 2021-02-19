package com.jasson.example.s5.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jasson.example.s5.Footage

@Dao
interface FootageDao {

    @Insert
    fun insertFootage(newFootage: Footage)
    @Delete
    fun deleteFootage(footage: Footage)
    @Query("SELECT * FROM table_footage")
    fun getAllFootage(): List<Footage>
    @Query("SELECT * FROM table_footage WHERE footageId = :key")
    fun getFootage(key:Long): Footage?

}