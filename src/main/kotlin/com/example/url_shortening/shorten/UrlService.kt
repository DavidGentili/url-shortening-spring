package com.example.url_shortening.shorten

import com.example.url_shortening.shorten.entity.Url
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UrlService( @Autowired val repository: IUrlRepository) {

    fun getByCode(code: String): Url {
        if(code == null || code.length == 0)
            throw RuntimeException("Debe ingresar una url Valida")
        return repository
            .findByShortCode(code)
            .orElseThrow { throw RuntimeException("No se encontro una url con esa Id") }
    }

    fun addUrl(url: Url): Url {
        return repository.insert(url)
    }

    fun updateUrlByCode(code: String, url: String): Url {
        if(url == null)
            throw RuntimeException("Debe ingresar una url Valida")
        val current : Url = repository
            .findByShortCode(code)
            .orElseThrow { throw RuntimeException("No se encontro una url con ese codigo") }
        current.url = url
        repository.save(current)
        return current
    }

    fun addAccess(code: String) {
        if(code == null)
            throw RuntimeException("Debe ingresar un codigo valido")
        val current : Url = repository
            .findByShortCode(code)
            .orElseThrow{ throw  RuntimeException("No se encontro una url con ese codigo")}
        current.accessCount++
        repository.save(current)
    }

    fun deleteById(urlId: String) { repository.deleteById(urlId)}
}