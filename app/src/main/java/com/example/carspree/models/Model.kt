package com.example.carspree.models

data class Model(
    val id: Int,
    val imageUrl: String,
    val make: MakeX,
    val modelFeatures: List<Any>,
    val name: String,
    val popular: Boolean,
    val wheelType: String
)