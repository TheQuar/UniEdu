package com.quar.uniedu.model

import java.io.Serializable


data class University(
    val id: Int,
    val full_name: String,
    val description: String,
    val img: String,
    val location: String,
    val acceptance_rate: String,
    val rank: String,
    val requerments: String,
    val website: String,
) : Serializable
