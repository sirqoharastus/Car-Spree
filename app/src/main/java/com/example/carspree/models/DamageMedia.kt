package com.example.carspree.models

data class DamageMedia(
    val comment: String,
    val inspectionItems: List<InspectionItem>,
    val name: String
)