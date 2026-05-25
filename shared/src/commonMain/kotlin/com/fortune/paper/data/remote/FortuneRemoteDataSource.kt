package com.fortune.paper.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.functions.functions
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.ktor.client.call.body
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@Serializable
data class FortuneDto(
    val id: String,
    val user_id: String,
    val date: String,
    val grade: String,
    val summary: String,
    val advice: String,
    val created_at: String
)

class FortuneRemoteDataSource(private val client: SupabaseClient) {

    suspend fun getTodayReport(userId: String, today: String): FortuneDto? {
        return client.postgrest["fortunes"]
            .select(Columns.ALL) {
                filter {
                    eq("user_id", userId)
                    eq("date", today)
                }
            }
            .decodeSingleOrNull<FortuneDto>()
    }

    suspend fun generateReport(userId: String, birthDate: String, gender: String): FortuneDto {
        val response = client.functions.invoke(
            function = "fortune",
            body = buildJsonObject {
                put("user_id", userId)
                put("birth_date", birthDate)
                put("gender", gender)
            }
        )
        return response.body<FortuneDto>()
    }
}
