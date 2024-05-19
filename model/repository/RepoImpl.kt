package com.dev.mahamat.andal_ia.model.repository

import androidx.compose.runtime.MutableState
import com.dev.mahamat.andal_ia.model.local.ChatsDao
import com.dev.mahamat.andal_ia.model.remote.ApiService


class RepoImpl : Repository {
   companion object  {
       private val apiService = ApiService
    }
    override suspend fun getChatGptResponse(
        dao: ChatsDao,
        userPrompt: String,
        conversationName: String,
        system: MutableState<String>,
        isAITypingLabelShowing: MutableState<Boolean>
    ): String {
        return apiService.getChatGptResponse(
            dao = dao,
            userPrompt = userPrompt,
            conversationName = conversationName,
            system = system,
            isAITypingLabelShowing = isAITypingLabelShowing
        )
    }
}