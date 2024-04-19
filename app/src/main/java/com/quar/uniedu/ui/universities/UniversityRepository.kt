package com.quar.uniedu.ui.universities

import com.quar.uniedu.network.ApiClient
import com.quar.uniedu.network.RetrofitInjector
import com.quar.uniedu.network.SafeApiCall

class UniversityRepository(private val api: ApiClient) : SafeApiCall {
    constructor() : this(RetrofitInjector.injectApiService())

    suspend fun getUniversities() = safeResponse { api.getUniversities() }
}