package com.finapp.domain.tables

import jakarta.persistence.*

@Entity
@Table(name = "card_receivables_lock_cerc_protocols")
data class CardReceivablesLockCercProtocolEntity(
        @Id val id: String,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "card_receivables_lock_cerc_id", nullable = false)
        var cardReceivablesLockCerc: CardReceivablesLockCercEntity?,
        val action: String,
        val protocol: String,
        val createdAt: java.time.LocalDateTime
)
