package com.dev.mahamat.andal_ia.model

import android.util.Log
import androidx.compose.runtime.MutableState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//const val token ="CTAPI-320AXEPJQHJWdcotSJhGZcTJq"
//const val question = ""

interface ApiService {
    @GET("posts")
    abstract fun getReponse(): Call<UserModel?>?
}


fun sendRequest(
token: String,
question: String,
reponse: MutableState<ReponseBot>
) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://codingllama.codingteamapi.workers.dev/?token=$token&question=$question")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ApiService::class.java)

    val call: Call<UserModel?>? = api.getReponse();

    call!!.enqueue(object: Callback<UserModel?> {
        override fun onResponse(call: Call<UserModel?>, response: Response<UserModel?>) {
            if(response.isSuccessful) {
                Log.d("Main", "success!" + response.body().toString())
                reponse.value = response.body()!!.reponses
                print("success :"+reponse.value)
            }
        }

        override fun onFailure(call: Call<UserModel?>, t: Throwable) {
            Log.e("Main", "Failed mate " + t.message.toString())
            print("error"+t.message.toString())
        }
    })


    }

