package com.fortune.paper.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.postgrest.Postgrest

// SUPABASE_URL과 SUPABASE_ANON_KEY는 local.properties 또는 환경 변수에서 주입
// 실제 값은 SupabaseConfig.kt(gitignore)에서 관리
object SupabaseClientProvider {
    lateinit var client: SupabaseClient
        private set

    fun initialize(url: String, anonKey: String) {
        client = createSupabaseClient(
            supabaseUrl = url,
            supabaseKey = anonKey
        ) {
            install(Postgrest)
            install(Auth)
            install(Functions)
        }
    }
}
