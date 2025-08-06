package com.finapp.domain.entities

data class CardReceivablesLockNuclea(
        val id: String,
        val creationRetryAttempts: Int = 0,
        val proactiveSearchAttempts: Int = 0,
        val protocol: String? = null
)
