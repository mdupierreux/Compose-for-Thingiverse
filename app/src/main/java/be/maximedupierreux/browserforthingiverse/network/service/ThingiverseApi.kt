package be.maximedupierreux.browserforthingiverse.network.service

import be.maximedupierreux.browserforthingiverse.BuildConfig
import be.maximedupierreux.browserforthingiverse.network.model.Things
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class ThingiverseApi(private val client: HttpClient) {
    suspend fun getNewest(): Things = client.request("https://api.thingiverse.com/search?sort=popular") {
        method = HttpMethod.Get
        headers {
            append(HttpHeaders.Accept, "application/json")
            append("Authorization", "Bearer ${BuildConfig.API_KEY}")
        }
    }
}