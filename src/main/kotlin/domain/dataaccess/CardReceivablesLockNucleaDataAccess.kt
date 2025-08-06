package com.finapp.domain.dataaccess

import com.finapp.domain.tables.CardReceivablesLockNucleaEntity

interface CardReceivablesLockNucleaDataAccess {

  fun create(
          cardReceivablesLockNuclea: CardReceivablesLockNucleaEntity
  ): CardReceivablesLockNucleaEntity

  fun update(
          cardReceivablesLockNuclea: CardReceivablesLockNucleaEntity
  ): CardReceivablesLockNucleaEntity

  fun getById(id: String): CardReceivablesLockNucleaEntity?

  fun findByCardReceivablesLockId(cardReceivablesLockId: String): CardReceivablesLockNucleaEntity?

  fun findByProtocol(protocol: String): List<CardReceivablesLockNucleaEntity>

  fun incrementCreationRetryAttempts(id: String): Unit

  fun incrementProactiveSearchAttempts(id: String): Unit

  fun updateProtocol(id: String, protocol: String): Unit
}
