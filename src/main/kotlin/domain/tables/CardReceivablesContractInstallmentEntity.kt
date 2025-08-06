package com.finapp.domain.tables

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "card_receivables_contract_installment")
data class CardReceivablesContractInstallmentEntity(
        @Id val id: String,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "card_receivables_lock_id", nullable = false)
        var cardReceivablesLock: CardReceivablesLockEntity?,
        val installmentNumber: Int,
        val date: LocalDate,
        val value: BigDecimal
)
