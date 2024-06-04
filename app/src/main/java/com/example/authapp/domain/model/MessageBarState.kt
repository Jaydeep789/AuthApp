package com.example.authapp.domain.model

data class MessageBarState(
    val errorMessage: Exception? = null,
    val message: String? = null
)
