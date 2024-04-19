package com.quar.uniedu.network

import com.quar.uniedu.utils.Constants
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiClient {
    @FormUrlEncoded
    @POST(Constants.LOGIN)
    suspend fun login(
        @Field("param") param: String = "login",
        @Field("username") username: String,
        @Field("password") password: String
    ): ResponseCloud

    @FormUrlEncoded
    @POST(Constants.LOGIN)
    suspend fun signup(
        @Field("param") param: String = "signup",
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
    ): ResponseCloud

    @FormUrlEncoded
    @POST(Constants.LOGIN)
    suspend fun getUniversities( @Field("param") param: String = "getUniversity"):ResponseCloud
}