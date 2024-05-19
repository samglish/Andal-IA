package com.dev.mahamat.andal_ia.model.repository

import androidx.compose.runtime.MutableState
import com.dev.mahamat.andal_ia.model.local.ChatsDao


interface Repository {
    suspend fun getChatGptResponse(
        dao: ChatsDao,
        userPrompt: String,
        conversationName: String,
        system: MutableState<String>,
        isAITypingLabelShowing: MutableState<Boolean>
    ): String
}