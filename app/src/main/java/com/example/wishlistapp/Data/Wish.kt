package com.example.wishlistapp.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="wish_table")

data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0L,
    @ColumnInfo("wish_title")
    val title: String=" ",
    @ColumnInfo(name="wish_desc")
val description:String=""
)

object Dummywish{
    val WishList=listOf(
        Wish(title= "Google Watch",
            description= "An android watch"),

        Wish(title= "A Novel",
            description= "A book by Elif Shafak"),

        Wish(title= "TV",
            description= "An android Tv")

    )
}