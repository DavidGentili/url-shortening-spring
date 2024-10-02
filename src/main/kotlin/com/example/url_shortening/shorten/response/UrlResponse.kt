package com.example.url_shortening.shorten.response

import java.time.LocalDateTime

data class UrlResponse(
    val id: String?,
    val url: String?,
    val shortCode: String?,
    val accessCount: Int?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {
    constructor(
        id: String?,
        url: String?,
        shortCode: String?,
        createdAt: LocalDateTime?,
        updatedAt: LocalDateTime?
    ) : this(id, url, shortCode, null, createdAt,updatedAt)

    constructor(): this(null, null, null, null, null)
}
