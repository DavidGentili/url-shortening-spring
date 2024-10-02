package com.example.url_shortening.shorten.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@Document("url")
data class Url(
    @Id
    val id: ObjectId = ObjectId(),
    var url: String?,
    val shortCode: String?,
    var accessCount: Int = 0,
    @CreatedDate
    val createdAt: LocalDateTime? = null,
    @LastModifiedDate
    val updatedAt: LocalDateTime? = null
)