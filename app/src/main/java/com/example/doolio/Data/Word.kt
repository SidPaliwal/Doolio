package com.example.doolio.Data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val Title:String,
    val Description:String
):Parcelable