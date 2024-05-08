package com.example.submissionawalmdl3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteUser::class],
    version = 2
)
abstract class FavoriteUserDatabase: RoomDatabase() {
    abstract fun favUserDao(): FavoriteUserDao
    companion object{
        @Volatile
        var INSTANCE: FavoriteUserDatabase? = null

        fun getDatabase(context: Context): FavoriteUserDatabase?{
            if (INSTANCE == null){
                synchronized(FavoriteUserDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, FavoriteUserDatabase::class.java, "favUser_database")
                        .fallbackToDestructiveMigration()
                        .build()

                }
            }
            return INSTANCE
        }
    }

}