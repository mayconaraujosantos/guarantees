package com.finapp.domain.tables

import jakarta.persistence.*

@Entity
@Table(name = "card_receivables_owner_arrangement")
data class CardReceivablesOwnerArrangementEntity(
        @Id val id: String,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "card_receivables_lock_holder_id", nullable = false)
        var cardReceivablesHolder: CardReceivablesHolderEntity?,
        val arrangementCode: String,
        val createdAt: java.time.LocalDateTime,
        val updatedAt: java.time.LocalDateTime
)
