package com.jasson.example.s5
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "table_footage")
data class Footage(
    @ColumnInfo(name="footage_title")
    val title: String,
    @ColumnInfo(name="footage_genre")
    val genre: String,
    @ColumnInfo(name="footage_abstract")
    val abs: String,
    @ColumnInfo(name="footage_poster")
    val poster: String,
    @ColumnInfo(name="footage_type")
    val type: String,
    @ColumnInfo(name="footage_url")
    val url: String,
    @PrimaryKey(autoGenerate=true)
    val footageId: Long = 0L): Serializable