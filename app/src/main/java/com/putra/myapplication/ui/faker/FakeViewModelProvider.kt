package com.hanif.detectionperson.ui.faker

import com.google.firebase.auth.FirebaseUser
import com.hanif.detectionperson.data.Resource
import com.hanif.detectionperson.data.auth.AuthRepository
import com.putra.myapplication.ui.auth.AuthViewModel

object FakeViewModelProvider {
    fun provideAuthViewModel() = AuthViewModel(authRepo)

    private val authRepo = object : AuthRepository {
        override val currentUser: FirebaseUser?
            get() = null

        override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
            TODO("Not yet implemented")
        }

        override suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser> {
            TODO("Not yet implemented")
        }

        override fun logout() {
            TODO("Not yet implemented")
        }

    }
}