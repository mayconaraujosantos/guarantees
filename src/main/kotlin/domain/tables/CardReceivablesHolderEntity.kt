package com.finapp.domain.tables

import jakarta.persistence.*

@Entity
@Table(name = "card_receivables_holder")
data class CardReceivablesHolderEntity(
        @Id val id: String,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "card_receivables_lock_id", nullable = false)
        var cardReceivablesLock: CardReceivablesLockEntity?,
        val taxId: String,
        val rootTaxIdOperation: String,
        val paymentAccountBranch: String?,
        val paymentAccountNumber: String?,
        val paymentAccountId: String?,
        @OneToMany(
                mappedBy = "cardReceivablesHolder",
                cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY
        )
        var arrangements: List<CardReceivablesOwnerArrangementEntity> = emptyList(),
        @OneToMany(
                mappedBy = "cardReceivablesHolder",
                cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY
        )
        var accreditors: List<CardReceivablesOwnerAccreditorEntity> = emptyList()
)
