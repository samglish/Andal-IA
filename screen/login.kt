package com.dev.mahamat.andal_ia.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.unit.sp
import com.dev.mahamat.andal_ia.NavControl.HOME_GRAPH
import com.dev.mahamat.andal_ia.NavControl.REGISTRATION_GRAPH
import com.dev.mahamat.andal_ia.ui.theme.color1
import com.dev.mahamat.andal_ia.ui.theme.color3
import com.dev.mahamat.andal_ia.R.drawable

@Composable
fun login(navController: NavHostController, context: Context) {
    var nom by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    var image = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = drawable.login1),
            contentDescription = "Logo clinique",
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = "SE CONNECTER",
            color = color1,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = nom,
            singleLine = true,
            onValueChange = { nom = it },
            placeholder = { Text(text = "Nom d'utilisateur") },
            label = { Text(text = "Nom d'utilisateur") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color1,
                unfocusedBorderColor = color1,
                focusedLabelColor = color1,
                unfocusedLabelColor = color1,
                cursorColor = color1,
            )
        )
        OutlinedTextField(
            value = password,
            singleLine = true,
            onValueChange = { password = it },
            placeholder = {
                Text(
                    text = "Mot de passe"
                )
            },
            label = { Text(text = "Mot de passe") },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = image,
                        contentDescription = "password visibility",
                        tint = color1

                    )
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color1,
                unfocusedBorderColor = color1,
                focusedLabelColor = color1,
                unfocusedLabelColor = color1,
                cursorColor = color1,
            )
        )
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Mot de passe oublié ?", color = color1)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if(nom.isEmpty() || password.isEmpty()){
                    Toast.makeText(context,"Veillez remplire tout les champs !", Toast.LENGTH_SHORT).show()
                }else{
                    navController.popBackStack()
                    navController.navigate(HOME_GRAPH){
                        popUpTo(HOME_GRAPH)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = color1,
                contentColor = color3
            )

        ) {
            Text(text = "SE CONNECTER")
        }
        TextButton(onClick = {
            navController.navigate(REGISTRATION_GRAPH) }) {
            Text(text = "S'enregistrer", color = color1)
        }

    }

}