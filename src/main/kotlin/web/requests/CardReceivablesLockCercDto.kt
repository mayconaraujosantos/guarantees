package com.finapp.web.requests

import java.time.LocalDateTime

data class CardReceivablesLockCercDto(
        val id: String,
        val creationRetryAttempts: Int,
        val proactiveSearchAttempts: Int,
        val cardReceivablesLock: CardReceivablesLockDto?,
        val protocol: String?,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
)
