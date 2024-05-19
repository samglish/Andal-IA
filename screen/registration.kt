package com.dev.mahamat.andal_ia.screen


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import com.dev.mahamat.andal_ia.NavControl.AUTH_GRAPH
import com.dev.mahamat.andal_ia.NavControl.Screen
import com.dev.mahamat.andal_ia.ui.theme.color1
import com.dev.mahamat.andal_ia.ui.theme.color3
import com.dev.mahamat.andal_ia.R.drawable

@ExperimentalMaterialApi
@Composable
fun regist(navController: NavHostController, context: Context) {

    var name by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var mail by remember {
        mutableStateOf("")
    }

    var passwordVisibility1 by remember {
        mutableStateOf(false)
    }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    var image1 = if (passwordVisibility1) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
    var image = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

    var mdp by remember {
        mutableStateOf("")
    }
    var mdpConf by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id =com.dev.mahamat.andal_ia.R.drawable.login1),
            contentDescription = "Logo clinique",
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = "ENREGISTREMENT",
            color = color1,
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = name,
            singleLine = true,
            onValueChange = { name = it },
            placeholder = { Text(text = "Entré votre nom") },
            label = { Text(text = "Nom") },
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
            value = lastName,
            singleLine = true,
            onValueChange = { lastName = it },
            placeholder = { Text(text = "Entré votre prénom") },
            label = { Text(text = "Prénom") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color1,
                unfocusedBorderColor = color1,
                focusedLabelColor = color1,
                unfocusedLabelColor = color1,
                cursorColor = color1,
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = mail,
            singleLine = true,
            onValueChange = { mail = it },
            placeholder = { Text(text = "Entré votre E-mail") },
            label = { Text(text = "E-mail") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color1,
                unfocusedBorderColor = color1,
                focusedLabelColor = color1,
                unfocusedLabelColor = color1,
                cursorColor = color1,
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = mdp,
            singleLine = true,
            onValueChange = { mdp = it },
            placeholder = {
                Text(
                    text = "Entré votre Mot de passe"
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
        OutlinedTextField(
            value = mdpConf,
            singleLine = true,
            onValueChange = { mdpConf = it },
            placeholder = {
                Text(
                    text = "Confirmez votre mot de passe"
                )
            },
            label = { Text(text = "Confirmez votre mot de passe") },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility1 = !passwordVisibility1 }) {
                    Icon(
                        imageVector = image1,
                        contentDescription = "password visibility",
                        tint = color1

                    )
                }
            },
            visualTransformation = if (passwordVisibility1) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color1,
                unfocusedBorderColor = color1,
                focusedLabelColor = color1,
                unfocusedLabelColor = color1,
                cursorColor = color1,
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                if(name.isEmpty() || lastName.isEmpty() || mail.isEmpty() || mdp.isEmpty() || mdpConf.isEmpty()){
                    Toast.makeText(context,"Veillez remplire toutes les champs !", Toast.LENGTH_SHORT).show()
                }else if(!mdp.equals(mdpConf, ignoreCase = false)){
                    Toast.makeText(context,"Les deux mot de passe ne corresponds pas !", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = color1,
                contentColor = color3
            )

        ) {
            Text(text = "Enregistrer")
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(
            onClick = {
                navController.navigate(AUTH_GRAPH){
                    popUpTo(AUTH_GRAPH)
                }
            },
        ) {
            Text(text = "Déjà un compte ?", color = color1)
        }
    }
}
