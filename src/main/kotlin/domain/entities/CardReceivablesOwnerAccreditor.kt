package com.finapp.domain.entities

import java.time.LocalDateTime

data class CardReceivablesOwnerAccreditor(
        val id: String,
        val accreditorTaxId: String,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now()
)
