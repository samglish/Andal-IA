package com.dev.mahamat.andal_ia.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversations")
data class Conversation(@PrimaryKey val conversationName: String)