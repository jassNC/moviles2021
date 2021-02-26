package com.jasson.example.s5.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jasson.example.s5.Footage
import com.jasson.example.s5.dao.FootageDao


@Database(entities = [Footage::class],version = 2, exportSchema = false)
abstract class LocalDataBase : RoomDatabase(){
    abstract val footageDao: FootageDao

    companion object{
        @Volatile
        private var dbInstance : LocalDataBase? = null

        fun getInstance(context: Context): LocalDataBase{
            synchronized(this){
                var instance = dbInstance
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                                                    LocalDataBase::class.java,
                                                "local_database").fallbackToDestructiveMigration().build()

                    dbInstance = instance
                }
                return instance
            }
        }


    }

}