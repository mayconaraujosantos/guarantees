package com.finapp.web.requests

import java.time.LocalDateTime

data class CardReceivablesSchedulesDto(
        val id: String,
        val personId: String,
        val register: String?,
        val arrangement: String?,
        val accreditor: String?,
        val source: String?,
        val schedules: String?,
        val createdAt: LocalDateTime
)
