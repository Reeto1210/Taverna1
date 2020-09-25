package com.mudryakov.taverna.models

data class Users(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullName: String = "",
    var status: String = "",
    var phoneNumber: String = "",
    var photoUrl: String = ""
)