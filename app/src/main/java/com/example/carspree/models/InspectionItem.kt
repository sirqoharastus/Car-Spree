package com.example.carspree.models

data class InspectionItem(
    val comment: String,
    val condition: String,
    val medias: List<Media>,
    val name: String,
    val response: String
)