package com.example.wishlistapp.Data

import kotlinx.coroutines.flow.Flow

class WishRepo(private val wishDao: WishDao) {

    suspend fun addWish(wish:Wish){
        wishDao.addWish(wish)
    }

    fun getWish(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getAWishbyId(id:Long) :Flow<Wish> {
         return wishDao.getWishbyId(id)
    }
    suspend fun updateWish(wish:Wish){
        wishDao.updateWish(wish)

    }
    suspend fun deleteAWish(wish: Wish){
        wishDao.deleteAWish(wish)
    }
}