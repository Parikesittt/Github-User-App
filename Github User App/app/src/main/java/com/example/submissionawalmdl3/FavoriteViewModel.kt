package com.example.submissionawalmdl3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.submissionawalmdl3.database.FavoriteUser
import com.example.submissionawalmdl3.database.FavoriteUserDao
import com.example.submissionawalmdl3.database.FavoriteUserDatabase

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private var userDao: FavoriteUserDao?
    private var userDB: FavoriteUserDatabase?

    init {
        userDB = FavoriteUserDatabase.getDatabase(application)
        userDao = userDB?. favUserDao()
    }

    fun getFavoriteUser(): LiveData<List<FavoriteUser>>?{
        return userDao?.getFavoriteUser()
    }
}