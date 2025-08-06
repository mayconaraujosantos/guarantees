package com.finapp.domain.dataaccess

import com.finapp.domain.tables.CardReceivablesLockCercEntity

interface CardReceivablesLockCercDataAccess {

  fun create(cardReceivablesLockCerc: CardReceivablesLockCercEntity): CardReceivablesLockCercEntity

  fun update(cardReceivablesLockCerc: CardReceivablesLockCercEntity): CardReceivablesLockCercEntity

  fun getById(id: String): CardReceivablesLockCercEntity?

  fun findByCardReceivablesLockId(cardReceivablesLockId: String): CardReceivablesLockCercEntity?

  fun incrementCreationRetryAttempts(id: String): Unit

  fun incrementProactiveSearchAttempts(id: String): Unit
}
