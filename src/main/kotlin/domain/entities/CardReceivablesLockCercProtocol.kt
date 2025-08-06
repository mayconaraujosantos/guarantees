package com.finapp.domain.entities

import java.time.LocalDateTime

data class CardReceivablesLockCercProtocol(
        val id: String,
        val action: String,
        val protocol: String,
        val createdAt: LocalDateTime = LocalDateTime.now()
)
