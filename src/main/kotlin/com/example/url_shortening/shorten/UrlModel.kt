package com.example.url_shortening.shorten

import com.example.url_shortening.shorten.entity.Url
import com.example.url_shortening.shorten.response.UrlResponse
import org.springframework.beans.factory.annotation.Autowired
import kotlin.random.Random

@org.springframework.stereotype.Component
class UrlModel(@Autowired val service: UrlService) {

    fun createUrl(url: String): UrlResponse {
        val urlObject = Url(url=url, shortCode =this.createCode())
        val created = this.service.addUrl(urlObject)
        println("Esta linea de codigo la tenemos")
        return UrlResponse(
            id = created?.id.toString() ?: "Id-falso",
            url = created.url,
            shortCode = created.shortCode,
            accessCount = created.accessCount,
            createdAt = created.createdAt,
            updatedAt = created.updatedAt
        )
    }

    fun getByCode(code: String): UrlResponse {
        val url = this.service.getByCode(code)
        return UrlResponse(
            id = url.id.toString(),
            url = url.url,
            shortCode = url.shortCode,
            accessCount = url.accessCount,
            createdAt = url.createdAt,
            updatedAt = url.updatedAt
        )
    }

    fun visitUrl(code: String): UrlResponse {
        this.service.addAccess(code)
        val url = this.service.getByCode(code)
        return UrlResponse(
            id = url.id.toString(),
            url = url.url,
            shortCode = url.shortCode,
            createdAt = url.createdAt,
            updatedAt = url.updatedAt
        )
    }

    fun updateDomain(code: String, url: String): UrlResponse {
        val current = this.service.updateUrlByCode(code, url)

        return UrlResponse(
            id = current.id.toString(),
            url = current.url,
            shortCode = current.shortCode,
            createdAt = current.createdAt,
            updatedAt = current.updatedAt
        )
    }

    fun deleteUrl(urlId: String) {
        this.service.deleteById(urlId)
    }

    fun deleteUrlByCode(code: String): UrlResponse {
        val current = this.service.getByCode(code)
        this.deleteUrl(current.id.toString())
        return UrlResponse(
            id = current.id.toString(),
            url = current.url,
            shortCode = current.shortCode,
            createdAt = current.createdAt,
            updatedAt = current.updatedAt
        )
    }

    private fun createCode(): String {
        var code = ""
        do {
            code = this.generateCode()
            var isUnique: Boolean = false
            try {
                val searched = this.service.getByCode(code)
            } catch (e: RuntimeException) {
                isUnique = true
            }
        } while (!isUnique)

        return code
    }

    private fun generateCode(): String {
        val list = List(6) { Random.nextInt(65, 90) }
        var code = ""
        for (num in list) {
            code += num.toChar()
        }
        return code
    }
}