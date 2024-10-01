package com.example.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.Data.Wish
import com.example.wishlistapp.Data.WishRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(

    private val wishRepository: WishRepo=Graph.wishRepository
): ViewModel(
) {

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")


    fun onWishTitleChanged(newString: String){
        wishTitleState =newString
    }
    fun onWishDescriptionState(newString: String){
        wishDescriptionState = newString
    }

    lateinit var getAWish: Flow<List<Wish>>

    init{
        viewModelScope.launch{
            getAWish = wishRepository.getWish()
        }
    }

    fun addWish(wish: Wish){
        viewModelScope.launch( Dispatchers.IO){
            wishRepository.addWish(wish=wish)
        }
    }
    fun updateWish(wish: Wish){
        viewModelScope.launch( Dispatchers.IO){
            wishRepository.updateWish(wish=wish)
        }
    }
    fun getAWishbyId(id:Long): Flow<Wish>{
        return wishRepository.getAWishbyId(id)
    }
    fun deleteWish(wish: Wish){
        viewModelScope.launch( Dispatchers.IO){
            wishRepository.deleteAWish(wish=wish)
    }
}
}