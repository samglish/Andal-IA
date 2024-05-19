package com.dev.mahamat.andal_ia.model

data class ReponseBot(
    var bot: String,
    var version : String,
)
data class UserModel(
    var reponses: ReponseBot
)