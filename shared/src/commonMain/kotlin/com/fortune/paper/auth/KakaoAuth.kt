package com.fortune.paper.auth

expect class KakaoAuth {
    suspend fun login(): Result<KakaoToken>
    suspend fun logout()
}

data class KakaoToken(
    val accessToken: String,
    val userId: Long
)
