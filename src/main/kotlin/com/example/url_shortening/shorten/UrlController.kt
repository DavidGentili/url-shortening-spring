package com.example.url_shortening.shorten

import com.example.url_shortening.shorten.request.UrlRequest
import com.example.url_shortening.shorten.response.UrlResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("shorten")
class UrlController(@Autowired val model: UrlModel) {


    @PostMapping
    fun addUrl(@RequestBody url: UrlRequest): ResponseEntity<UrlResponse> {
        try {
            val current = this.model.createUrl(url.url)
            return ResponseEntity.ok(current)
        } catch (e: RuntimeException) {
            println(e)
            return ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{code}")
    fun updateDomain(@PathVariable code: String, @RequestBody url: UrlRequest): ResponseEntity<UrlResponse> {
        try {
            return ResponseEntity.ok(this.model.updateDomain(code, url.url))
        } catch (e: RuntimeException) {
            return ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{code}")
    fun getUrl(@PathVariable code: String): RedirectView {
        val BASE_URL = "http://localhost:8080"
        try {
            val current = this.model.visitUrl(code)
            val redirect = if(current.url != null) current.url else BASE_URL
            return RedirectView(redirect)

        } catch (e: RuntimeException) {
            return RedirectView(BASE_URL)
        }
    }

    @GetMapping("/{code}/stats")
    fun getUrlWithStats(@PathVariable code: String): ResponseEntity<UrlResponse> {
        try {
            return ResponseEntity.ok(this.model.getByCode(code))
        } catch (e: RuntimeException) {
            return ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{code}")
    fun deleteByCode(@PathVariable code: String): ResponseEntity<UrlResponse> {
        try {
            return ResponseEntity.ok(this.model.deleteUrlByCode(code))
        } catch (e: RuntimeException) {
            return ResponseEntity.notFound().build()
        }
    }
}