package com.finapp.domain.entities

import java.math.BigDecimal
import java.time.LocalDate

data class CardReceivablesContractInstallment(
        val id: String,
        val installmentNumber: Int,
        val date: LocalDate,
        val value: BigDecimal
)
