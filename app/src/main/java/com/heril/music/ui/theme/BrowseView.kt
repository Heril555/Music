package com.heril.music.ui.theme

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.heril.music.R

@Composable
fun BrowseView(isDarkMode: Boolean) {
    val color = if(isDarkMode) colorResource(id = R.color.white) else colorResource(id = R.color.black)

    LazyVerticalGrid(columns = GridCells.Fixed(2)){
        items(categories){
            category ->
            BrowserItem(category = category, drawable = R.drawable.ic_browse)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BrowseViewPreview() {
    BrowseView(isDarkMode = false)
}