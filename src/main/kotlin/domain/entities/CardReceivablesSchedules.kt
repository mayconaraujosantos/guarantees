package com.finapp.domain.entities

import java.time.LocalDateTime

data class CardReceivablesSchedules(
        val id: String,
        val personId: String,
        val register: String?,
        val arrangement: String?,
        val accreditor: String?,
        val source: String?,
        val schedules: String?,
        val createdAt: LocalDateTime = LocalDateTime.now()
)
