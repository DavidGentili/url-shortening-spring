package com.example.url_shortening.shorten

import com.example.url_shortening.shorten.entity.Url
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface IUrlRepository : MongoRepository<Url, String> {

     @Query("{'shortCode' :?0}")
     fun findByShortCode(shortCode: String): Optional<Url>

     @Query("{'url' :?0}")
     fun findByUrl(url: String): Optional<Url>


}