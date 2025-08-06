package com.finapp.web.requests

import java.time.LocalDateTime

data class CardReceivablesOwnerAccreditorDto(
        val id: String,
        val cardReceivablesHolder: CardReceivablesHolderDto?,
        val accreditorTaxId: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
)
