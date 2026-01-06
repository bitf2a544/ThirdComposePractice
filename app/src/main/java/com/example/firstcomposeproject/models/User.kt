package com.example.firstcomposeproject.models

data class User(
    val id: Int,
    val name: String = "John Doe",
    val profileImageUrl: String = "Dummy URL",
)