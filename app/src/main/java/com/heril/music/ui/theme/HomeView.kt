package com.heril.music.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heril.music.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView(isDarkMode: Boolean) {
    val color = if(isDarkMode) colorResource(id = R.color.white) else colorResource(id = R.color.black)

    LazyColumn{
        grouped.forEach {
            stickyHeader {
                Text(
                    text = it,
                    modifier = Modifier.padding(16.dp),
                    color = color,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                LazyRow {
                    items(categories){
                        category ->
                        BrowserItem(category = category, drawable = R.drawable.ic_browse)
                    }
                }
            }
        }
    }
}

@Composable
fun BrowserItem(category:String,drawable:Int) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(200.dp),
        elevation = 4.dp,
        border = BorderStroke(3.dp,color = colorResource(id = R.color.yellow)
        )
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(category,fontSize = 18.sp)
            Icon(
                painter = painterResource(id = drawable),
                contentDescription = category
            )
        }
    }
}