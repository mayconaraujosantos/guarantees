package com.finapp.domain.tables

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "card_receivables_lock_cerc")
data class CardReceivablesLockCercEntity(
        @Id val id: String,
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "card_receivables_lock_id")
        var cardReceivablesLock: CardReceivablesLockEntity?,
        var protocol: String?,
        var creationRetryAttempts: Int,
        var proactiveSearchAttempts: Int,
        var createdAt: LocalDateTime,
        var updatedAt: LocalDateTime,
        @OneToMany(
                mappedBy = "cardReceivablesLockCerc",
                cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY
        )
        var protocols: List<CardReceivablesLockCercProtocolEntity> = emptyList()
)
