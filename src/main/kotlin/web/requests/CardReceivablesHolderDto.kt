package com.finapp.web.requests

data class CardReceivablesHolderDto(
        val id: String,
        val taxId: String,
        val rootTaxIdOperation: String,
        val paymentAccountBranch: String?,
        val paymentAccountNumber: String?,
        val paymentAccountId: String?,
        val cardReceivablesLock: CardReceivablesLockDto?
)
