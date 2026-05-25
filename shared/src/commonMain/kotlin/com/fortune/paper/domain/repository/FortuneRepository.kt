package com.fortune.paper.domain.repository

import com.fortune.paper.domain.model.FortuneReport

interface FortuneRepository {
    suspend fun getTodayReport(): Result<FortuneReport>
    suspend fun refreshReport(): Result<FortuneReport>
}
