package com.fortune.paper.auth

// TODO: 카카오 iOS SDK 연동 (Task 3)
// 의존성: KakaoOpenSDK (SPM)
actual class KakaoAuth {
    actual suspend fun login(): Result<KakaoToken> =
        Result.failure(NotImplementedError("카카오 iOS SDK 미구현"))

    actual suspend fun logout() {
        // TODO
    }
}
