package com.heril.music.ui.theme

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heril.music.R

@Composable
fun LibraryView(isDarkMode: Boolean) {
    val color = if(isDarkMode) colorResource(id = R.color.white) else colorResource(id = R.color.black)

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_playlist),
                    contentDescription = "Playlist",
                    modifier = Modifier.padding(end = 10.dp),
                    tint = colorResource(id = R.color.yellow)
                )
                Text("Playlist", fontSize = 20.sp, color = color)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = colorResource(id = R.color.yellow)
            )
        }
        Divider(color = colorResource(id = R.color.grey))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_artists),
                    contentDescription = "Artists",
                    modifier = Modifier.padding(end = 10.dp),
                    tint = colorResource(id = R.color.yellow)
                )
                Text("Artists", fontSize = 20.sp, color = color)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = colorResource(id = R.color.yellow)
            )
        }
        Divider(color = colorResource(id = R.color.grey))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_album),
                    contentDescription = "Album",
                    modifier = Modifier.padding(end = 10.dp),
                    tint = colorResource(id = R.color.yellow)
                )
                Text("Album", fontSize = 20.sp, color = color)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = colorResource(id = R.color.yellow)
            )
        }
        Divider(color = colorResource(id = R.color.grey))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_music),
                    contentDescription = "Music",
                    modifier = Modifier.padding(end = 10.dp),
                    tint = colorResource(id = R.color.yellow)
                )
                Text("Music", fontSize = 20.sp, color = color)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = colorResource(id = R.color.yellow)
            )
        }
        Divider(color = colorResource(id = R.color.grey))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_genre),
                    contentDescription = "Genre",
                    modifier = Modifier.padding(end = 10.dp),
                    tint = colorResource(id = R.color.yellow)
                )
                Text("Genre", fontSize = 20.sp, color = color)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = colorResource(id = R.color.yellow)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = Color.BLACK.toLong())
@Composable
fun LibraryViewPreview() {
    LibraryView(true)
}