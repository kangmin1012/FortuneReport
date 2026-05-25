package com.fortune.paper.domain.usecase

import com.fortune.paper.domain.repository.UserRepository

class UpdateNotifyTimeUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(time: String?): Result<Unit> = repository.updateNotifyTime(time)
}
