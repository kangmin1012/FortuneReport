package com.fortune.paper.auth

// TODO: 카카오 Android SDK 연동 (Task 3)
// 의존성: com.kakao.sdk:v2-user
actual class KakaoAuth {
    actual suspend fun login(): Result<KakaoToken> =
        Result.failure(NotImplementedError("카카오 Android SDK 미구현"))

    actual suspend fun logout() {
        // TODO
    }
}
