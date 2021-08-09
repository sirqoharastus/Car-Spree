package com.example.carspree.models

data class Pagination(
    val currentPage: Int,
    val pageSize: Int,
    val total: Int
)