package com.finapp.domain.tables

import jakarta.persistence.*

@Entity
@Table(name = "card_receivables_owner_accreditor")
data class CardReceivablesOwnerAccreditorEntity(
        @Id val id: String,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "card_receivables_lock_holder_id", nullable = false)
        var cardReceivablesHolder: CardReceivablesHolderEntity?,
        val accreditorTaxId: String,
        val createdAt: java.time.LocalDateTime,
        val updatedAt: java.time.LocalDateTime
)
