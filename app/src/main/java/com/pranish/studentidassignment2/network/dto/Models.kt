package com.pranish.s8069126assignment2.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val keypass: String)

@JsonClass(generateAdapter = true)
data class EntityDto(
    @Json(name = "property1") val property1: String? = null,
    @Json(name = "property2") val property2: String? = null,
    // Also accept legacy names from older payloads:
    @Json(name = "artworkTitle") val artworkTitle: String? = null,
    @Json(name = "artist") val artist: String? = null,
    val description: String? = null
) {
    fun title(): String = property1 ?: artworkTitle ?: ""
    fun subtitle(): String = property2 ?: artist ?: ""
}

@JsonClass(generateAdapter = true)
data class DashboardResponse(
    val entities: List<EntityDto>,
    val entityTotal: Int
)
