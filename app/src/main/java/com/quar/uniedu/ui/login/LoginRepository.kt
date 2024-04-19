package com.quar.uniedu.ui.login

import com.quar.uniedu.network.ApiClient
import com.quar.uniedu.network.RetrofitInjector
import com.quar.uniedu.network.SafeApiCall

class LoginRepository(private val api: ApiClient) : SafeApiCall {
    constructor() : this(RetrofitInjector.injectApiService())

    suspend fun login(username: String, password: String) =
        safeResponse { api.login("login", username, password) }


    suspend fun signup(
        firstname: String,
        lastname: String,
        email: String,
        username: String,
        password: String
    ) = safeResponse { api.signup("signup", firstname, lastname, email, username, password) }

}