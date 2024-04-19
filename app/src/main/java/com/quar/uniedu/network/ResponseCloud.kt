package com.quar.uniedu.network

import com.quar.uniedu.model.University

data class ResponseCloud(
    val message: String,
    val userId: String?,
    val universities: List<University>
)
