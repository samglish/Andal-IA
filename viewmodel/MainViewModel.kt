package com.dev.mahamat.andal_ia.viewmodel
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import android.speech.tts.TextToSpeech
import com.dev.mahamat.andal_ia.model.local.ChatsDao
import com.dev.mahamat.andal_ia.model.repository.Repository
import java.util.*

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _response = MutableStateFlow<String?>(null)
    val response = _response.asStateFlow()

    companion object {
        var textToSpeech: TextToSpeech? = null
        var autoSpeak = false
    }

    suspend fun getChatGptResponse(
        context: Context,
        dao: ChatsDao,
        userPrompt: String,
        conversationName: String,
        system: MutableState<String>,
        isAITypingLabelShowing: MutableState<Boolean>
    ) {
        val aiResponse = repository.getChatGptResponse(
            dao = dao,
            userPrompt = userPrompt,
            conversationName = conversationName,
            system = system,
            isAITypingLabelShowing = isAITypingLabelShowing
        )
        _response.emit(aiResponse)
        if (autoSpeak) {
            textToSpeech(context = context, text = aiResponse)
        }
    }

    fun textToSpeech(context: Context, text: String) {
        textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    if (!txtToSpeech.isSpeaking) {
                        txtToSpeech.language = Locale.getDefault()
                        txtToSpeech.setSpeechRate(1.0f)
                        txtToSpeech.stop()
                        txtToSpeech.speak(
                            text,
                            TextToSpeech.QUEUE_ADD,
                            null,
                            null
                        )
                    }
                }
            }
        }
    }

    fun stopSpeech() {
        textToSpeech?.stop()
    }
}