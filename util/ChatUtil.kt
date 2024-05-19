package com.dev.mahamat.andal_ia.util
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Environment
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.sp
import com.dev.mahamat.andal_ia.model.local.Chat
import com.dev.mahamat.andal_ia.model.local.ChatsDao
import com.google.gson.Gson
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.property.TextAlignment
import java.io.File
import java.time.format.DateTimeFormatter
import java.util.*

val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("h:mm a")
val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd.yy")

fun saveConversationToPDF(conversationName: String, chats: List<Chat>) {
    val document = PdfDocument(
        PdfWriter(
            File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "$conversationName.pdf"
            )
        )
    )

    val text = Text(conversationName).apply {
        setFontSize(20f)
        setBold()
    }

    val paragraph = Paragraph().apply {
        add(text)
        setTextAlignment(TextAlignment.CENTER)
        setMarginBottom(20f)
    }

    val doc = Document(document).apply {
        add(paragraph)
    }

    for (chat in chats) {
        val msgText =
            Text("${chat.senderLabel} - ${chat.dateSent}" +
                    " at ${chat.timeSent}\n\n--> ${chat.text}").apply {
                setFontSize(12f)
            }
        val msgPara = Paragraph().apply {
            add(msgText)
            setMarginTop(10f)
            setMarginBottom(10f)
            setMarginLeft(20f)
            setMarginRight(20f)
        }

        doc.add(msgPara)
    }

    doc.close()
}

fun saveConversationToJson(
    list: List<Chat>,
    filename: String
) {
    val gson = Gson()
    val json = gson.toJson(list)

    val downloadsDirectory =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    val file = File(downloadsDirectory, filename)
    file.writeText(json)
}

fun getConvoChats(
    dao: ChatsDao,
    chats: SnapshotStateList<Chat>,
    conversationText: MutableState<String>,
    conversationHeaderName: MutableState<String>,
    isConversationsDialogShowing: MutableState<Boolean>
) {
    val convoChats = dao.getChatsByConvo(conversationHeaderName.value)
    chats.clear()
    convoChats.forEach { chat ->
        chats.add(chat)
    }
    conversationText.value = ""
    isConversationsDialogShowing.value = false
}

fun senderAndTimeStyle(color: Color) = TextStyle(
    fontSize = 15.sp,
    fontWeight = FontWeight.Bold,
    color = color
)

fun getSpeechInputIntent(context: Context): Intent? {
    if (!SpeechRecognizer.isRecognitionAvailable(context)) {
        Toast.makeText(context, "Speech not available", Toast.LENGTH_SHORT).show()
    } else {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, ChatConfig.speakPrompts.random())
        return intent
    }
    return null
}

object SenderLabel {
    var HUMAN_SENDER_LABEL = ""
    const val DEFAULT_HUMAN_LABEL = "Me"
    const val CHATGPT_SENDER_LABEL = "ChatGPT"
}

@Suppress("unused")
object ChatConfig {
    const val GPT_3_5_TURBO = "gpt-3.5-turbo"
    const val GPT_4 = "gpt-4"

    const val SCROLL_ANIMATION_DELAY = 1500L

    const val DEFAULT_CONVO_CONTEXT = "You are my helpful assistant"

    private val aiAdjectives = listOf(
        "Sarcastic",
        "Helpful",
        "Unhelpful",
        "Optimistic",
        "Pessimistic",
        "Excited",
        "Joyful",
        "Charming",
        "Inspiring",
        "Nonchalant",
        "Relaxed",
        "Loud",
        "Annoyed"
    )

    private val randomChatGptAdjective = aiAdjectives.random()

    val conversationalContext = listOf(
        "Be as ${randomChatGptAdjective.lowercase()} as possible.",
        "You are my ${randomChatGptAdjective.lowercase()} assistant",
        "Play the role of the ${randomChatGptAdjective.lowercase()} bot",
        "Act as if you are extremely ${randomChatGptAdjective.lowercase()}",
        "Act as if you are the only ${randomChatGptAdjective.lowercase()} AI"
    )

    val exampleConvoContext = "\"${conversationalContext.random()}\""

    val speakPrompts = listOf(
        "What would you like to ask?",
        "Speak now... please",
        "LOL spit it out already...",
        "* ChatGPT is yawning... *",
        "Speaketh you may",
        "Listening for dat beautiful voice...",
        "Hello Human"
    )
}
public  fun traduire(mot:String):String {
    var mot=mot
    val motsFB = arrayOf(
    "misani",
        " ",
        "awali djam",
        "noy hinde ma",
        "mi done tefa",
        "mi foti mi heba",
        "hinde",
        "hinde",
        "tefi",
        "mine",
        "an",
        "soko",
        "lornoutou"



    )
    val motsFR = arrayOf(
    "salut",
        " ",
        "bonjour",
        "comment tu t'appelles",
        "je cherche",
        "je peux avoir",
        "le nom",
        "nom",
        "cherche",
        "moi",
        "toi",
     "merci",
        "traduit"

    )
    for (i in motsFB.indices) {
        mot = mot.replace(motsFB[i], motsFR[i],true)
    }
    return mot
}

fun translate(phrase: String): String {
    var phrase = phrase
    val motsFR = arrayOf(
        "et toi",
        "je n\'ai pas",
        "Comment puis-je vous aider",
        "aujourd'hui",
        "ne pas aimer",
        "n\'aime pas",
        "les problèmes",
        "et",
        "aime",
        "aimer",
        "je vais à",
        "je vais a",
        "école",
        "l\'école",
        "ecole",
        "l\'ecole",
        "élève",
        "eleve",
        "je pars",
        "au",
        "marché",
        "oui",
        "non",
        "écris",
        "écrit",
        "écrivons",
        "écrivez",
        "écrivont",
        "je suis malade",
        "la maladie",
        "maladie",
        "malade",
        "Les malades",
        "aller quelques part",
        "quelques part",
        "aller",
        "partir",
        "chapitre",
        "verset",
        "versets",
        "bible",
        "bibles",
        "famille",
        "tout d\'abord",
        "je remercie",
        "je te remercie",
        "Dieu",
        "travail ",
        "cours",
        "j\'adresse",
        "depuis",
        "Adam",
        "promesse",
        "le nouveau testament",
        "ancien testament",
        "allié ",
        "politique",
        "ouvrir sa bouche",
        "écarter",
        "semence",
        "graine",
        "semis",
        "avocat",
        "Abraham",
        "céder à",
        "céder",
        "annuler",
        "prendre",
        "veux",
        "veut",
        "voulons",
        "voulez",
        "veulent",
        "voulais",
        "voulais",
        "voulait",
        "voulions",
        "vouliez",
        "voulaient",
        "voulu",
        "prends",
        "prend",
        "prenons",
        "prenez",
        "prennent",
        "prendrai",
        "prendras",
        "prendra",
        "prendrons",
        "prendrez",
        "prendront",
        "bouger quelque chose ",
        "bouger quelqu\'un",
        "quelqu\'un",
        "introduction",
        "La parole",
        "problème",
        "gens",
        "les gens du monde",
        "monde",
        "terre",
        "parole",
        "afrique",
        "le premier fils",
        "la première fille",
        "première fille",
        "premier fils",
        "examen",
        "coutume",
        "tradition",
        "pratique",
        "signe clinique",
        "dimanche",
        "mercredi",
        "avec",
        "ail",
        "Porte-monnaie",
        "oignon",
        "notre",
        "amen",
        "intelligence",
        "intelligent",
        "il est intelligent",
        "elle est intelligente",
        "l\'homme blanc",
        "ambassade",
        "l\'ambassade",
        "ambassadeur",
        "année",
        "populations",
        "populations",
        "les gens",
        "connaissance",
        "la connaissance",
        "le savoir",
        "expérience",
        "la science",
        "science",
        "l\'expérience",
        "aujourd\'hui",
        "demain",
        "hier",
        "avant-hier",
        "après-demain",
        "enseigner",
        "la langue",
        "mouton",
        "grand",
        "petit",
        "baiser",
        "baise",
        "faire l\'amour",
        "coucher",
        "chevre",
        "chévre",
        "chèvre",
        "devenir",
        "repos",
        "je mange",
        "mange",
        "Nom",
        "salut",
        "bonjour",
        "bonne nuit",
        "comment tu vas",
        " je vais bien",
        "je vais bien et toi",
        " ",
        "la joie",
        "joie",
        "le bonheur",
        "bonheur",
        "famine",
        "la famine",
        "la nourriture",
        "nourriture",
        "l'eau",
        "boire de l'eau",
        "blanc",
        "blanche",
        "noir",
        "noire",
        "rouge",
        "vendre",
        "manger",
        "l'esprit",
        "voleur",
        "natte",
        "sol",
        "terre",
        "pays",
        "habit",
        "maison",
        "mouches",
        "mouche",
        "arraignée",
        "arraignées",
        "montagne",
        "doucement",
        "paresseux",
        "un fou",
        "fou",
        "villageois",
        "enfants",
        "enfants",
        "femme",
        "homme",
        "maman",
        "papa",
        "grand frère",
        "grande soeur",
        "oncle",
        "Oncle",
        "tante",
        "Tante",
        "chat",
        "chambre",
        "brulé",
        "cuisiner",
        "moi",
        "je",
        "toi",
        "tu",
        "lui",
        "il",
        "elle",
        "nous",
        "nous",
        "vous",
        "vous",
        "eux",
        "ils",
        "elles",
        "j'ai",
        "tu as",
        "il a",
        "elle a",
        "nous avons",
        "vous avez",
        "ils ont",
        "elles ont",
        "je suis",
        "tu es",
        "il est",
        "elle est",
        "nous sommes",
        "vous êtes",
        "vous etes",
        "ils sont",
        "elles sont",
        "sa maison",
        "ma maison",
        "son mari",
        "mon mari",
        "ma femme",
        "sa femme"
    )
    val motsFB = arrayOf(
        "an bo",
        "mi maraille",
        "noy mi foti mi walla one",
        "handé",
        "na yihigo",
        "yida",
        "haala",
        "be",
        "yidi",
        "hidigo",
        "mi done yah",
        "mi done yah",
        "djanguirdé",
        "djanguirdé",
        "djanguirdé",
        "djanguirdé",
        "binguel djanduirdé",
        "binguel djanduirdé",
        "mi done dilla",
        "a",
        "loumo",
        "ohoo",
        "kaÏ",
        "wildi",
        "wildi",
        "wilden",
        "wildéh",
        "wildan",
        "mi nyaoudo",
        "nyaou",
        "nyaou",
        "nyaoudo",
        "nyaoubéh",
        "yahogo bébelle",
        "bébelle",
        "yahogo",
        "dinlégo",
        "sourate",
        "aya",
        "aya",
        "deftéré",
        "deftéré",
        "légnol",
        "tawon",
        "mi yotti ",
        "mi yotti ma",
        "Allah",
        "cougal",
        "darséré",
        "mi done wiya",
        "tigame",
        "Aadama ",
        "aadi",
        "Aadi Keyri ",
        "Aadi Kiinndi",
        "aadondiraaɗo ",
        "politikki",
        "maboutougo  hounndouko",
        "ndassou",
        "aawdiiri",
        "aawdiiri",
        "aawdiiri",
        "aboka",
        "Abraama",
        "atchounou",
        "atchounougo",
        "facitougo",
        "hossogo",
        "hidi",
        "hidi",
        "hidi",
        "hidi",
        "hidi",
        "hidi noo",
        "hidi noo",
        "hidi noo",
        "hidi noo",
        "hidi noo",
        "hidi go",
        "hidi",
        "hossou",
        "hossou",
        "hossou",
        "hossou",
        "hossou",
        "hossann",
        "hossann",
        "hossann",
        "hossann",
        "hossann",
        "hossann",
        "dimbinogo coudjé",
        "dimbinogo goddo",
        "goddo",
        "fouddogo",
        "hala",
        "haala",
        "yimbeh",
        "yimbeh douniya",
        "douniya",
        "lesdi",
        "hala",
        "afiriki",
        "afo",
        "afo",
        "afo",
        "afo",
        "agijame",
        "al\'aada ",
        "al\'aada ",
        "al\'aada",
        "nyaou",
        "alat",
        "alarba",
        "bé",
        "albatché",
        "possa",
        "tiniereh",
        "notre",
        "amen",
        "andal",
        "faamou",
        "ho mari faamou",
        "ho mari faamou",
        "nassara",
        "ammbasad",
        "ammbasad",
        "maoudo ammbasad",
        "doubi",
        "yimbeh",
        "yimbeh",
        "yimbeh",
        "faamu",
        "faamu",
        "hakkillo",
        "paamu",
        "andal",
        "andal",
        "paamu",
        "handeh",
        "djangoo",
        "kennya",
        "hesisi kennya",
        "fappi django",
        "janngindeh",
        "woondeh",
        "baala",
        "maanga",
        "petelle",
        "baaldal",
        "baaldal",
        "baaldal",
        "waalougo",
        "mbewa",
        "mbewa",
        "mbewa",
        "wartogo",
        "sioutogo",
        "mi done gnama",
        "gnama",
        "Indé",
        "misani",
        " awali djam ",
        "djam wala",
        "noy",
        "wala ",
        "wala an bo",
        " ",
        "hayrou",
        "hayrou",
        "seyo",
        "seyo",
        "welo",
        "welo",
        "gnamdou",
        "gnamdou",
        "ndiyam",
        "yarogo ndiyam",
        "danédjoum",
        "danédjoum",
        "balédjoum",
        "balédjoum",
        "bodédjoum",
        "sippougo",
        "gnamougo",
        "hakkilo",
        "goudjo",
        "dago",
        "lesdi",
        "lesdi",
        "wouro",
        "loumché",
        "saaré",
        "boubii",
        "boubii",
        "tchaytchay",
        "tchaytchay",
        "hosséré",
        "be hakkilo",
        "mbassedjo",
        "guinnado",
        "guinnado",
        "kaywédjo",
        "bikkone",
        "binguel",
        "debbo",
        "gorgo",
        "dada",
        "baba",
        "hamma",
        "adda",
        "kawou",
        "bappa",
        "yapendo",
        "goggo",
        "patourou",
        "soudou",
        "wouli",
        "defougo",
        "mine",
        "mi",
        "ane",
        "a",
        "kanko",
        "o",
        "o",
        "ene",
        "enen",
        "one",
        "onon",
        "kambé",
        "bé",
        "bé",
        "mi mari",
        "a mari",
        "o mari",
        "o mari",
        "ene mari",
        "one mari",
        "bé mari",
        "bé mari",
        "mi done",
        "a done",
        "o done",
        "o done",
        "ene done",
        "one done",
        "one done",
        "bé done",
        "bé done",
        "saaré mako",
        "saaré am",
        "gorgo mako",
        "gorgo am",
        "debbo am",
        "debbo mako"
    )
    for (i in motsFR.indices) {
        phrase = phrase.replace(motsFR[i], motsFB[i],true)
    }
    return phrase
}
