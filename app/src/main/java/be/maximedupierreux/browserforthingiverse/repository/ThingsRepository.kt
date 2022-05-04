package be.maximedupierreux.browserforthingiverse.repository

import be.maximedupierreux.browserforthingiverse.network.service.ThingiverseApi
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.flow.flow

class ThingsRepository {
    suspend fun getNewestThings() = flow {
        val api = ThingiverseApi(HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                })
            }
        })
        while (true) {
            val things = api.getNewest()
            emit(things.hits.toMutableList())
        }

    }
}