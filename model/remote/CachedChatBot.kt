package com.dev.mahamat.andal_ia.model.remote

import com.dev.mahamat.andal_ia.model.local.Chat
import timber.log.Timber


class CachedChatBot(
    apiKey: String,
    private val request: ChatCompletionRequest,
    prevChats: List<Chat>
) : ChatBot(apiKey) {
    private val prev = prevChats
    fun generateResponse(content: String, role: String = "user"): String {
        prev.forEach {
            request.messages.add(ChatMessage(role, it.text))
        }
        request.messages.add(ChatMessage(role, content))
        val response = super.generateResponse(request)
        val temp = response.choices[0].message
        request.messages.add(temp)
        Timber.tag("chats").i(request.messages.toString())
        return temp.content
    }
}