package com.finapp.web.requests

import java.time.LocalDateTime

data class CardReceivablesLockCercProtocolDto(
        val id: String,
        val cardReceivablesLockCerc: CardReceivablesLockCercDto?,
        val action: String,
        val protocol: String,
        val createdAt: LocalDateTime
)
