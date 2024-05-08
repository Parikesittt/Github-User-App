package com.example.submissionawalmdl3.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favoriteUser")
@Parcelize
data class FavoriteUser(
    @ColumnInfo(name = "username")
    val login: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String
):Parcelable

