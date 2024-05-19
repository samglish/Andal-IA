package com.dev.mahamat.andal_ia.screen

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dev.mahamat.andal_ia.R
import com.dev.mahamat.andal_ia.model.BottomNavItem
import com.dev.mahamat.andal_ia.ui.theme.color1
import com.dev.mahamat.andal_ia.ui.theme.color3
import kotlinx.coroutines.launch

@Composable
fun profile(navController: NavHostController)
     {
        var imageURI by remember {
            mutableStateOf<Uri?>(null)
        }
        val context = LocalContext.current
        val bitmap = remember {
            mutableStateOf<Bitmap?>(null)
        }
        val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){ uri: Uri? ->
            imageURI = uri
        }
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()


        val bottomNaveItem = listOf(
            BottomNavItem(
                name = "Modifier le passe",
                route = "home",
                icon = Icons.Rounded.Password
            ),
            BottomNavItem(
                name = "Theme",
                route = "add",
                icon = Icons.Rounded.Colorize
            ),
            BottomNavItem(
                name = "Acheter",
                route = "Setting",
                icon = Icons.Rounded.Wallet
            ),
            BottomNavItem(
                name = "Apropos",
                route = "Setting",
                icon = Icons.Rounded.Info
            ),
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Divider(modifier = Modifier.padding(end = 15.dp), color = color3)
        Text(text = "Profile", fontSize = MaterialTheme.typography.overline.fontSize, color = color3)
        Divider(modifier = Modifier.padding(end = 15.dp), color = color3)
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(126.dp)
                    .clip(CircleShape)
                    .background(color1)
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if(imageURI==null){
                    Image(painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(800.dp,800.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }

                bitmap.value?.let { btm ->
                    Image(
                        bitmap = btm.asImageBitmap(),
                        contentDescription = "avar",
                        modifier = Modifier
                            .size(800.dp,800.dp),
                        contentScale = ContentScale.FillBounds
                    )

                }

            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "MAHAMAT ", fontSize = MaterialTheme.typography.h6.fontSize,fontWeight = FontWeight.Bold, color = color3)
            }

        }

        imageURI?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(
                    context.contentResolver,
                    it
                )
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        Divider(modifier = Modifier.padding(end = 15.dp), color = color3)
        Text(text = "Paramétre", fontSize = MaterialTheme.typography.overline.fontSize, color = color3)
        Divider(modifier = Modifier.padding(end = 15.dp), color = color3)

        bottomNaveItem.forEach{item ->
            val selected = false

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickable {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                        navController.navigate(item.route)
                    },
                backgroundColor = Color.White,
                elevation = 10.dp,
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(imageVector = item.icon, contentDescription = "${item.name} Icon")
                    Text(
                        modifier = Modifier.padding(start = 24.dp),
                        text = item.name)
                }
            }

        }
        Divider(modifier = Modifier.padding(end = 15.dp), color = color3)
        Text(text = "Nous conctacter", fontSize = MaterialTheme.typography.overline.fontSize, color = color3)
        Divider(modifier = Modifier.padding(end = 15.dp), color = color3)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clickable {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate("main_scren")
                },
            backgroundColor = Color.White,
            elevation = 10.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook_mod),
                    contentDescription = "facebook Icon",
                    contentScale = ContentScale.Crop

                )
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    text = "Facebook")
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clickable {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate("main_scren")
                },
            backgroundColor = Color.White,
            elevation = 10.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.watsapp_mod),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    text = "Whatsapp")
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clickable {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate("main_scren")
                },
            backgroundColor = Color.White,
            elevation = 10.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(painter = painterResource(id = R.drawable.telegrame_mod
                ), contentDescription = "Telegram Icon" )
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    text = "Telegram")
            }
        }
        Divider(modifier = Modifier.padding(end = 15.dp), color = color3)
        Text(text = "A Propos", fontSize = MaterialTheme.typography.overline.fontSize, color = color3)
        Divider(modifier = Modifier.padding(end = 15.dp), color = color3)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clickable {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate("main_scren")
                },
            backgroundColor = Color.White,
            elevation = 10.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(Icons.Rounded.Info, contentDescription = "About Icon")
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    text = "A Propos")
            }
        }
    }
            }
