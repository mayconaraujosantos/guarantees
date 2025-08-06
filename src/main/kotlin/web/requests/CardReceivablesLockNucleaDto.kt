package com.finapp.web.requests

import java.time.LocalDateTime

data class CardReceivablesLockNucleaDto(
        val id: String,
        val creationRetryAttempts: Int,
        val proactiveSearchAttempts: Int,
        val protocol: String?,
        val cardReceivablesLock: CardReceivablesLockDto?,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
)
