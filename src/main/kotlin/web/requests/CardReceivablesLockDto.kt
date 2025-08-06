package com.finapp.web.requests

import com.finapp.domain.enums.CardReceivablesLockStatus
import com.finapp.domain.enums.RecalculationPeriodIndicator
import com.finapp.domain.enums.RegisterType
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class CardReceivablesLockDto(
        val id: String,
        val hubGuaranteeId: String,
        val contractNumber: String,
        val contractSource: String,
        val ipoc: String,
        val register: RegisterType,
        val ownerPersonId: String,
        val amount: BigDecimal,
        val recalculationFrequency: RecalculationPeriodIndicator,
        val considerBalanceOnInsufficiency: Boolean,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val status: CardReceivablesLockStatus,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
        val holders: List<CardReceivablesHolderDto>,
        val nucleaEntry: CardReceivablesLockNucleaDto?,
        val cercEntry: CardReceivablesLockCercDto?,
        val contractInstallments: List<CardReceivablesContractInstallmentDto>
)
