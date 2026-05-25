package com.fortune.paper.data.repository

import com.fortune.paper.data.remote.FortuneDto
import com.fortune.paper.data.remote.FortuneRemoteDataSource
import com.fortune.paper.data.remote.UserRemoteDataSource
import com.fortune.paper.domain.model.FortuneGrade
import com.fortune.paper.domain.model.FortuneReport
import com.fortune.paper.domain.repository.FortuneRepository
import com.fortune.paper.core.util.getTodayDateString

class FortuneRepositoryImpl(
    private val fortuneRemote: FortuneRemoteDataSource,
    private val userRemote: UserRemoteDataSource
) : FortuneRepository {

    override suspend fun getTodayReport(): Result<FortuneReport> = runCatching {
        val userId = requireNotNull(userRemote.currentUserId()) { "로그인이 필요합니다" }
        val today = getTodayDateString()

        val cached = fortuneRemote.getTodayReport(userId, today)
        if (cached != null) return@runCatching cached.toDomain()

        val user = requireNotNull(userRemote.getUser(userId)) { "사용자 정보를 찾을 수 없습니다" }
        fortuneRemote.generateReport(userId, user.birth_date, user.gender).toDomain()
    }

    override suspend fun refreshReport(): Result<FortuneReport> = runCatching {
        val userId = requireNotNull(userRemote.currentUserId()) { "로그인이 필요합니다" }
        val today = getTodayDateString()

        val cached = fortuneRemote.getTodayReport(userId, today)
        if (cached != null) return@runCatching cached.toDomain()

        val user = requireNotNull(userRemote.getUser(userId)) { "사용자 정보를 찾을 수 없습니다" }
        fortuneRemote.generateReport(userId, user.birth_date, user.gender).toDomain()
    }

    private fun FortuneDto.toDomain() = FortuneReport(
        id = id,
        grade = FortuneGrade.fromString(grade),
        summary = summary,
        advice = advice,
        date = date
    )
}
