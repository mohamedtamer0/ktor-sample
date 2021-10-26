package com.example.models

import kotlinx.serialization.Serializable


@Serializable
data class NoteResponse<T>(
    val data:T,
    val success:Boolean
)
