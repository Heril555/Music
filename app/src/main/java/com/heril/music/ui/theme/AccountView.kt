package com.heril.music.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.heril.music.R

@Composable
fun AccountView(
isDarkMode: Boolean
){
    val color = if(isDarkMode) colorResource(id = R.color.white) else colorResource(id = R.color.black)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Account",
                    modifier = Modifier.padding(end = 8.dp),
                    tint = colorResource(id = R.color.yellow)
                )
                Column {
                    Text("Heril Chahwala",color = color)
                    Text("herilchahwala@gmail.com",color = colorResource(id = R.color.yellow))
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = colorResource(id = R.color.yellow)
                )
            }
        }
        Row(modifier = Modifier.padding(top=16.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_library_music_24),
                contentDescription = "Music Icon",
                modifier = Modifier.padding(end = 8.dp),
                tint = colorResource(id = R.color.yellow)
            )
            Text("My Music",color = color)
        }
        Divider(color = colorResource(id = R.color.grey))
    }
}