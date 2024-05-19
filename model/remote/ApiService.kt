package com.dev.mahamat.andal_ia.model.remote

import androidx.compose.runtime.MutableState
import com.dev.mahamat.andal_ia.model.local.ChatsDao
import com.dev.mahamat.andal_ia.util.ChatConfig
import com.dev.mahamat.andal_ia.util.traduire
import com.dev.mahamat.andal_ia.util.translate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface ApiService {
    object ApiKey {
        var userApiKey = "OpenAI key"
    }

    companion object {
        suspend fun getChatGptResponse(
            dao: ChatsDao,
            userPrompt: String,
            conversationName:String,
            system: MutableState<String>,
            isAITypingLabelShowing: MutableState<Boolean>
        ): String {
            var aiResponse: String
            isAITypingLabelShowing.value = true
            try {
                withContext(Dispatchers.IO) {
                    val key = ApiKey.userApiKey
                    val request = ChatBot.ChatCompletionRequest(
                        model = ChatConfig.GPT_3_5_TURBO,
                        systemContent = system.value
                    )
                    val bot = CachedChatBot(
                        key,
                        request,
                        dao.getLastSixChats(conversationName)
                    )
                    aiResponse = bot.generateResponse(traduire(userPrompt))
                    isAITypingLabelShowing.value = false
                }
            } catch (e: SocketTimeoutException) {
                aiResponse = "Connection timed out. Please try again."
                isAITypingLabelShowing.value = false
            } catch (e: java.lang.IllegalArgumentException) {
                aiResponse = "ERROR: ${e.message}"
                isAITypingLabelShowing.value = false
            } catch (e: UnknownHostException) {
                aiResponse = "ERROR: ${e.message}.\n\n" +
                        "This could indicate no/very poor internet connection. " +
                        "Please check your connection and try again."
                isAITypingLabelShowing.value = false
            }
            return translate(aiResponse)
        }
    }
}
