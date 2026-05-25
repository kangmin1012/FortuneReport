package com.fortune.paper.domain.model

data class FortuneReport(
    val id: String,
    val grade: FortuneGrade,
    val summary: String,
    val advice: String,
    val date: String
)

enum class FortuneGrade {
    SUNNY, CLEAR, CLOUDY, RAINY, STORM;

    val displayName: String get() = when (this) {
        SUNNY -> "화창"
        CLEAR -> "맑음"
        CLOUDY -> "구름"
        RAINY -> "비"
        STORM -> "폭풍번개"
    }

    val icon: String get() = when (this) {
        SUNNY -> "☀️"
        CLEAR -> "🌤️"
        CLOUDY -> "☁️"
        RAINY -> "🌧️"
        STORM -> "⛈️"
    }

    companion object {
        fun fromString(value: String): FortuneGrade =
            entries.find { it.name == value.uppercase() } ?: CLOUDY
    }
}
