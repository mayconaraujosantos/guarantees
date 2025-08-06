package com.finapp.domain.entities

data class CardReceivablesLockCerc(
        val id: String,
        val creationRetryAttempts: Int = 0,
        val proactiveSearchAttempts: Int = 0
)
