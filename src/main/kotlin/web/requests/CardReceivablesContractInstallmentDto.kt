package com.finapp.web.requests

import java.math.BigDecimal
import java.time.LocalDate

data class CardReceivablesContractInstallmentDto(
        val id: String,
        val installmentNumber: Int,
        val date: LocalDate,
        val value: BigDecimal,
        val cardReceivablesLock: CardReceivablesLockDto?
)
