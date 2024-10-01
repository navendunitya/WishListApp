package com.example.wishlistapp

import android.content.Context
import androidx.room.Room
import com.example.wishlistapp.Data.WishDatabase
import com.example.wishlistapp.Data.WishRepo

object Graph {
    lateinit var database: WishDatabase
    val wishRepository by lazy{  // by lazy ensures variable is initialised only when it is used
       WishRepo(wishDao=database.wishDao()
       )
    }
    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase:: class.java, "wishlist.db").build()
    }
}