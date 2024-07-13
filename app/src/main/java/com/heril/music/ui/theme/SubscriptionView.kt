package com.heril.music.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heril.music.R

@Composable
fun SubscriptionView(isDarkMode: Boolean){
    val color = if(isDarkMode) colorResource(id = R.color.white) else colorResource(id = R.color.black)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Manage Subscription",
            modifier = Modifier.padding(top=4.dp),
            color = color,
            fontSize = 17.sp
        )
        Card(
            modifier = Modifier.padding(10.dp),
            elevation = 4.dp
        ){
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.padding(start=3.dp)) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Musical", color=colorResource(id = R.color.black))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Free Tier",color=colorResource(id = R.color.black))
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("See All Plans",color = colorResource(id = R.color.yellow))
                        IconButton(onClick = { /*TODO*/ },modifier = Modifier.width(24.dp)) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                                tint = colorResource(id = R.color.yellow)
                            )
                        }
                    }
                }
                Divider()
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_account),
                        contentDescription = "Account Icon",
                        modifier = Modifier.padding(end = 8.dp),
                        tint = colorResource(id = R.color.yellow)
                    )
                    Text("Get a Plan",color = colorResource(id = R.color.yellow))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SubscriptionView(true)
}