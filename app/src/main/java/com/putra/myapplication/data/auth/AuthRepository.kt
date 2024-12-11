package com.hanif.detectionperson.data.auth

import com.google.firebase.auth.FirebaseUser
import com.hanif.detectionperson.data.Resource

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}