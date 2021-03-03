/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.model.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Dog Go",
                                )
                            },
                            backgroundColor = Color.Transparent, elevation = 0.dp
                        )
                    }
                ) {
                    MyApp()
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val context = LocalContext.current
    val dogs = mutableListOf(
        Dog("Pyredoodle", "pyredoodle",
        "The Pyredoodle is a mixed breed dog–a cross between the Great Pyrenees and Standard Poodle dog breeds."),
        Dog("Rat Terrier", "rat_terrier",
            "Members of the Rat Terrier dog breed are adorable, little, digging escape artists who are true terriers."),
        Dog("Sheepadoodle", "sheepadoodle",
            "The Sheepadoodle is a mixed breed dog — a cross between the Old English Sheepdog and Poodle dog breeds."),
        Dog("Terripoo", "terripoo",
            "The Terripoo is a cross between the Australian Terrier and the miniature Poodle."),
        Dog("Westiepoo", "westiepoo",
            "The Westiepoo is a mixed breed dog — a cross between the West Highland White Terrier and Poodle dog breeds."),
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        LazyColumn(Modifier.fillMaxWidth()) {
            itemsIndexed(dogs) { position, dog ->
                DisplayDogItem(position, dog) { position, dog ->
//                    Intent(context, DogDetailActivity::class.java).apply {
//                        putExtra(SELECTED_POSITION, position)
//                        putExtra(SELECTED_DOG, dog)
//                        startForResult.launch(this)
//                    }
                }
            }
        }
    }
}

@Composable
fun DisplayDogItem(position: Int, dog: Dog, onClick: (position: Int, dog: Dog) -> Unit) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .requiredHeight(220.dp)
            .clickable { onClick(position, dog) }
    ) {
        CardItem(name = dog.name, avatar = dog.avatarFilename, introduction = dog.introduction)
    }
}

@Composable
fun CardItem(name: String, avatar: String, introduction: String) {
    val context = LocalContext.current
    val imageIdentity = context.resources.getIdentifier(
        avatar, "drawable",
        context.packageName
    )
    val image: Painter = painterResource(imageIdentity)
    Image(
        painter = image,
        contentDescription = name,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Surface(
            color = Color(0x99000000),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = name,
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Surface(
            color = Color(0x99000000),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = introduction,
                color = Color.White,
                fontSize = 10.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
