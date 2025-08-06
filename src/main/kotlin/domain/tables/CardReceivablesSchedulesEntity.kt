package com.finapp.domain.tables

import jakarta.persistence.*

@Entity
@Table(name = "card_receivables_schedules")
data class CardReceivablesSchedulesEntity(
        @Id val id: String,
        val personId: String,
        val register: String?,
        val arrangement: String?,
        val accreditor: String?,
        val source: String?,
        val schedules: String?,
        val createdAt: java.time.LocalDateTime
)
