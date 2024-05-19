package com.dev.mahamat.andal_ia

import android.graphics.Color
import android.graphics.fonts.*
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.util.Log
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.mahamat.andal_ia.NavControl.NavigationGraph
import com.dev.mahamat.andal_ia.NavControl.navigation
import com.dev.mahamat.andal_ia.screen.ChatScreen
import com.dev.mahamat.andal_ia.screen.*
import com.dev.mahamat.andal_ia.model.ChatUiModel
import com.dev.mahamat.andal_ia.model.ReponseBot
import com.dev.mahamat.andal_ia.model.sendRequest
import com.dev.mahamat.andal_ia.ui.theme.ChatzTheme
import com.dev.mahamat.andal_ia.ui.theme.Purple700
import com.dev.mahamat.andal_ia.ui.theme.SetStatusBarColor
import com.dev.mahamat.andal_ia.ui.theme.color1

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var navController: NavHostController

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val conversation = viewModel.conversation.collectAsState()
            navController = rememberNavController()

            ChatzTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetStatusBarColor(color1)
                    val navController: NavHostController = rememberNavController()
                    val bottomBarHeight = 40.dp
                    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }

                    var buttonsVisible = remember { mutableStateOf(true) }

                    Scaffold(
                        bottomBar = {
                            BottomBar(
                                navController = navController,
                                state = buttonsVisible,
                                modifier = Modifier
                            )
                        }) { paddingValues ->
                        Box(
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            NavigationGraph(navController = navController)
                        }
                        // MainScreen()
                        /*ChatScreen(
                        model = ChatUiModel(
                            messages = conversation.value,
                            addressee = ChatUiModel.Author.bot
                        ),
                        onSendChatClickListener = { msg -> viewModel.sendChat(msg) },
                        modifier = Modifier
                    )*/
                        //navigation(navControleur = navController, context =this )
                        //MainScreen()
                        //VRScreen()
                        //VR()
                        //profile(navController)
                        //splashScreen(navController = navController)
                        //login(navController = navController, context =this)
                        //regist(navController = navController, context = this)
                    }
                }
            }
        }
    }
}

    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val token = remember {
                mutableStateOf("CTAPI-320AXEPJQHJWdcotSJhGZcTJq")
            }

            val question = remember {
                mutableStateOf(TextFieldValue())
            }
            val reponses = remember {
                mutableStateOf(
                    ReponseBot(
                        bot = "",
                        version = ""
                    )
                )
            }


            Text(
                text = "API Sample",
                style = TextStyle(
                    fontSize = 40.sp,
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                label = { Text(text = "your question") },
                value = question.value,
                onValueChange = { question.value = it }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        val data = sendRequest(
                            token = token.value,
                            question = question.value.text,
                            reponse = reponses
                        )
                        Log.d("Main Activity", question.toString())
                    }
                ) {
                    Text(text = "Get Data")
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = reponses.component1().toString(), fontSize = 40.sp)
        }
    }


