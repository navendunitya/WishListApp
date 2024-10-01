package com.example.wishlistapp
import android.graphics.Paint
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.FloatingActionButton
import com.example.wishlistapp.AppBarView
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapp.Data.Dummywish
import com.example.wishlistapp.Data.Wish
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment


@OptIn(ExperimentalMaterialApi::class)
@Composable

fun HomeView(
    navController: NavController,
    viewModel:WishViewModel
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBarView(title = "Wish List", {
                //what should happen when we click back button

                Toast.makeText(context, "ButtonClicked", Toast.LENGTH_LONG).show()
            })
        },
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                containerColor = Color.Red,

                //container color is same as backgroundcolor, its just because containercolor is used in material3
                onClick = {
                    navController.navigate(Screen.AddScreen.route + "/0L")
                }) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = null, tint=Color.White)
            }
        }
    ) {
        val wishList = viewModel.getAWish.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishList.value, key={ wish-> wish.id}) { wish ->
                val dismissState = rememberDismissState(
                    confirmStateChange = { dismissValue ->
                        if (dismissValue == DismissValue.DismissedToEnd || dismissValue == DismissValue.DismissedToStart) {
                            // Here you can call the delete function on the wish
                            viewModel.deleteWish(wish)

                            // Example: viewModel.deleteWish(wish)
                            true // Return true to confirm the dismissal
                        } else {
                            false // Return false for any other dismiss state
                        }
                    }
                )
                SwipeToDismiss(state = dismissState,
                    background = {
                                 val color by animateColorAsState(
                                     if(dismissState.dismissDirection == DismissDirection.EndToStart)
                                     Color.Red
                                     else Color.Transparent,
                                     label = " "
                                 )
                        val alignment= Alignment.CenterEnd
                        Box(
                            Modifier.fillMaxSize().background(color).padding(horizontal = 20.dp),
                            contentAlignment= alignment
                        ){
                            Icon(Icons.Default.Delete, contentDescription= "Delete Icon",
                                tint= Color.White)
                        }

                    },
                    directions = setOf(
                        DismissDirection.EndToStart
                    ),
                    dismissThresholds = {
                        FractionalThreshold(0.25f)
                    },
                    dismissContent = {
                        WishItem(wish = wish) {
                            val id = wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    }
                )
            }
        }
    }
}








@Composable
fun WishItem(wish: Wish, onClick: ()-> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable {
            onClick()
        },
        elevation = 10.dp,
        backgroundColor= Color.White
        ){
            Column(modifier = Modifier.padding(16.dp)){
                Text(text =wish.title, fontWeight= FontWeight.ExtraBold )
                Text(text =wish.description)
            }
    }

}