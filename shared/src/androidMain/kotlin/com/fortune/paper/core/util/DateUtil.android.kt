package com.fortune.paper.core.util

import java.time.LocalDate
import java.time.ZoneId

actual fun getTodayDateString(): String =
    LocalDate.now(ZoneId.of("Asia/Seoul")).toString()
