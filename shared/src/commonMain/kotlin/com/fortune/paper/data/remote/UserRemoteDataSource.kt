package com.fortune.paper.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val kakao_id: String,
    val birth_date: String,
    val gender: String,
    val notify_time: String? = null,
    val fcm_token: String? = null,
    val created_at: String
)

@Serializable
data class UserUpsert(
    val id: String,
    val kakao_id: String,
    val birth_date: String,
    val gender: String
)

class UserRemoteDataSource(private val client: SupabaseClient) {

    fun currentUserId(): String? = client.auth.currentUserOrNull()?.id

    suspend fun getUser(userId: String): UserDto? {
        return client.postgrest["users"]
            .select(Columns.ALL) {
                filter { eq("id", userId) }
            }
            .decodeSingleOrNull<UserDto>()
    }

    suspend fun upsertUser(kakaoId: String, birthDate: String, gender: String): UserDto {
        val userId = requireNotNull(currentUserId()) { "인증된 사용자가 없음" }
        client.postgrest["users"].upsert(
            UserUpsert(id = userId, kakao_id = kakaoId, birth_date = birthDate, gender = gender)
        )
        return requireNotNull(getUser(userId))
    }

    suspend fun updateNotifyTime(userId: String, time: String?) {
        client.postgrest["users"].update(
            { set("notify_time", time) }
        ) {
            filter { eq("id", userId) }
        }
    }

    suspend fun updateFcmToken(userId: String, token: String) {
        client.postgrest["users"].update(
            { set("fcm_token", token) }
        ) {
            filter { eq("id", userId) }
        }
    }

    suspend fun signOut() {
        client.auth.signOut()
    }
}
