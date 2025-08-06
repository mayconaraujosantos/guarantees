package com.finapp.domain.entities

import java.time.LocalDateTime

data class CardReceivablesOwnerArrangement(
        val id: String,
        val arrangementCode: String,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now()
)
