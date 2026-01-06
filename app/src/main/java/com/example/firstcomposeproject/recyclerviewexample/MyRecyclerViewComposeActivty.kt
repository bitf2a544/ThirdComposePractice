package com.example.firstcomposeproject.recyclerviewexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstcomposeproject.ConversionViewModel
import com.example.firstcomposeproject.models.User
import com.example.firstcomposeproject.viewmodel.UserViewModel
import com.example.thirdcomposeproject.R

//video link https://www.youtube.com/watch?v=YhD2VLP7xWM&t=339s
class MyRecyclerViewComposeActivty : ComponentActivity() {

    val userViewModel: UserViewModel = UserViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           // UserList()
            AppNavGraph()
        }
    }

    @Composable
    fun AppNavGraph() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "list"
        ) {
            composable("list") {
                UserListScreen(navController)
            }
            composable("details/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                DetailsScreen(id)
            }
        }
    }
    @Preview
    @Composable
    fun UserListScreen(navController: NavController) {
        //below listing will not re-use list elements
     /*   Column(modifier = Modifier.verticalScroll(rememberScrollState())
            .background(color = colorResource(R.color.teal_200),)) {
            for (i in 1..10) {
                userCard()
            }
        }*/

        //below listing will re-use list elements and its recommended
        var usersList = userViewModel.getUsersList()
        //alternative to Recycler view
        LazyColumn (
            modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)){
            items(usersList) { user ->
                UserCard(user, navController)
            }
        }
    }

    @Composable
    fun UserCard( userItem: User, navController: NavController) {
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .padding(top = 8.dp, start = 12.dp, end = 12.dp)//margin
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
                Image(painter = painterResource(id = R.drawable.abc2), contentDescription = "",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(90.dp)
                        .clip(CircleShape))
                Column {
                   // Text(text = stringResource(id = R.string.dummy_text),
                    Text(text = userItem.name + " " + stringResource(id = R.string.dummy_text),
                        modifier = Modifier
                            .padding(top = 15.dp, start = 5.dp, end = 5.dp, bottom = 10.dp)
                            .wrapContentHeight()
                            .wrapContentWidth())
                    Button(
                        modifier = Modifier
                            .width(120.dp)
                            .height(45.dp)
                            .padding(bottom =  10.dp)
                            //  .background(color = colorResource(id = R.color.purple_200)) => Wrong
                            .clip(RoundedCornerShape(10.dp))
                        , colors = ButtonDefaults.buttonColors(
                                         backgroundColor = Color.Red,
                                         contentColor = Color.White)
                        , onClick = {
                            Log.e("ButtonClick", "View Profile clicked for user id="+ userItem.id)
                            navController.navigate("details/${userItem.id}")

                        }) {
                             Text(
                                 fontSize = 10.sp,
                                 text = stringResource(id = R.string.view_profile) +" "+ userItem.id)
                       }
                }
            }
        }
    }

    @Composable
    fun DetailsScreen(id: String?) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.Blue)
        ) {
            Text(
                text = "User Details",
                fontSize = 24.sp,
                color = colorResource(id = R.color.purple_700)
            )
            Text(text = "User ID: $id", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
            // Add more user details
            Text(text = "Clicked item: $id")
        }
    }

}