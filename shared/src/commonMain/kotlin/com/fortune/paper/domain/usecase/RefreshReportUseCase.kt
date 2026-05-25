package com.fortune.paper.domain.usecase

import com.fortune.paper.domain.model.FortuneReport
import com.fortune.paper.domain.repository.FortuneRepository

class RefreshReportUseCase(private val repository: FortuneRepository) {
    suspend operator fun invoke(): Result<FortuneReport> = repository.refreshReport()
}
