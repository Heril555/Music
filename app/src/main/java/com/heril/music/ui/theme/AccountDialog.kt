package com.heril.music.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.heril.music.R

@Composable
fun AccountDialog(dialogOpen: MutableState<Boolean>,isDarkMode:Boolean) {
    if (dialogOpen.value) {
        AlertDialog(
            backgroundColor = if (isDarkMode) colorResource(id = R.color.black) else colorResource(id = R.color.white),
            onDismissRequest = { dialogOpen.value = false },
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        content = { Text("Dismiss", fontSize = 20.sp, color = colorResource(id = R.color.yellow)) },
                        onClick = {
                            dialogOpen.value = false
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(
                        content = { Text("Confirm", fontSize = 20.sp, color = colorResource(id = R.color.yellow)) },
                        onClick = {
                            dialogOpen.value = false
                        }
                    )
                }
            },
            title = {
                Column(modifier = Modifier.wrapContentHeight()) {
                    Text(
                        "Add Account",
                        color = colorResource(id = R.color.yellow),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(" ")
                }
            },
            text = {
                val email = remember { mutableStateOf("") }
                val password = remember { mutableStateOf("") }
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = email.value,
                        label = { Text("Email", color = colorResource(id = R.color.yellow),fontSize = 18.sp) },
                        onValueChange = {
                            email.value = it
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = colorResource(id = R.color.yellow),
                            cursorColor = colorResource(id = R.color.yellow),
                            focusedBorderColor = colorResource(id = R.color.yellow),
                            unfocusedBorderColor = colorResource(id = R.color.yellow),
                            backgroundColor = colorResource(id = R.color.grey),
                            focusedLabelColor = colorResource(id = R.color.yellow),
                            unfocusedLabelColor = colorResource(id = R.color.yellow)
                        ),
                        modifier = Modifier.padding(top=16.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = password.value,
                        label = { Text("Password", color = colorResource(id = R.color.yellow),fontSize = 18.sp) },
                        onValueChange = {
                            password.value = it
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = colorResource(id = R.color.yellow),
                            cursorColor = colorResource(id = R.color.yellow),
                            focusedBorderColor = colorResource(id = R.color.yellow),
                            unfocusedBorderColor = colorResource(id = R.color.yellow),
                            backgroundColor = colorResource(id = R.color.grey),
                            focusedLabelColor = colorResource(id = R.color.yellow),
                            unfocusedLabelColor = colorResource(id = R.color.yellow)
                        )
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.yellow))
                .padding(start = 2.dp, end = 3.dp, top = 2.dp, bottom = 2.dp),
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun AddAccountPreview() {
    AccountDialog(dialogOpen = mutableStateOf(true),false)
}