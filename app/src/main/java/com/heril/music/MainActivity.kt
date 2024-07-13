package com.heril.music

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.heril.music.ui.theme.MainView
import com.heril.music.ui.theme.MusicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode = remember { mutableStateOf(true) }
            MusicTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = if (isDarkMode.value) Color.Black else MaterialTheme.colors.background
                ) {
//                    Navigation(
//                        isDarkMode = isDarkMode.value,
//                        onToggleDarkMode = { isDarkMode.value = it }
//                    )
                    MainView(
                        isDarkMode = isDarkMode.value,
                        onToggleDarkMode = { isDarkMode.value = it }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {

}