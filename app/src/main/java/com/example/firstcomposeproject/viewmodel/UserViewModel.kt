package com.example.firstcomposeproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.models.User

class UserViewModel : ViewModel() {
    val users = listOf(
        User(1,"John Doe 1"),
        User(2,"John Doe 2"),
        User(3,"John Doe 3"),
        User(4,"John Doe 4"),
        User(5,"John Doe 5"),
        User(6,"John Doe 6"),
        User(7,"John Doe 7"),
        User(8,"John Doe 8"),
        User(9,"John Doe 9"),
        User(10,"John Doe 10"),
        User(11,"John Doe 11"),
        User(12,"John Doe 12"),
        User(13,"John Doe 13"),
        User(14,"John Doe 14"),
        User(15,"John Doe 15"),
        User(16,"John Doe 16"),
        User(17,"John Doe 17"),
        User(18,"John Doe 18"),
        User(19,"John Doe 19"),
        User(20,"John Doe 20"),
    )

    fun getUsersList(): List<User> {
        return users
    }

}