package com.finapp.web.requests

import java.time.LocalDateTime

data class CardReceivablesOwnerArrangementDto(
        val id: String,
        val cardReceivablesHolder: CardReceivablesHolderDto?,
        val arrangementCode: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
)
