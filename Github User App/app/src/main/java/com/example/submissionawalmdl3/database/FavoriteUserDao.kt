package com.example.submissionawalmdl3.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.submissionawalmdl3.database.FavoriteUser

@Dao
interface FavoriteUserDao {
    @Insert
    suspend fun addFavorite(favoriteUser: FavoriteUser)

    @Query("SELECT * FROM favoriteUser")
    fun getFavoriteUser(): LiveData<List<FavoriteUser>>

    @Query("SELECT count(*) FROM favoriteUser WHERE favoriteUser.id = :id")
    suspend fun checkUser(id: Int): Int

    @Query("DELETE FROM favoriteUser WHERE favoriteUser.id = :id")
    suspend fun removeFavorite(id:Int): Int

}