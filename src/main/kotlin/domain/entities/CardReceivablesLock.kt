package com.finapp.domain.entities

import com.finapp.domain.enums.CardReceivablesLockStatus
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class CardReceivablesLock(
        val id: String,
        val hubGuaranteeId: String,
        val contractNumber: String,
        val contractSource: String,
        val ipoc: String,
        val register: String,
        val ownerPersonId: String,
        val amount: BigDecimal,
        val recalculationFrequency: String,
        val considerBalanceOnInsufficiency: Boolean,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val status: CardReceivablesLockStatus,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
        val holders: List<CardReceivablesHolder> = emptyList(),
        val nucleaEntries: List<CardReceivablesLockNuclea> = emptyList(),
        val cercEntries: List<CardReceivablesLockCerc> = emptyList(),
        val contractInstallments: List<CardReceivablesContractInstallment> = emptyList()
)
