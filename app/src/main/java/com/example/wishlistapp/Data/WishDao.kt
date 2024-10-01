package com.example.wishlistapp.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(wishEntity:Wish)

    @Query("Select * from 'wish_table'")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract fun updateWish(wishEntry: Wish)

    @Delete
    abstract suspend fun deleteAWish(wishEntity: Wish)

    @Query("Select * from 'wish_table'where id=:id")
    abstract fun getWishbyId(id:Long): Flow<Wish>




}