package com.finapp.domain.entities

data class CardReceivablesHolder(
        val id: String,
        val taxId: String,
        val rootTaxIdOperation: String,
        val paymentAccountBranch: String? = null,
        val paymentAccountNumber: String? = null,
        val paymentAccountId: String? = null
)
