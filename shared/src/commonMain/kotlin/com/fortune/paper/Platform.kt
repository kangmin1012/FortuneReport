package com.fortune.paper

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform