package com.finapp.application.resources.dataaccess

import com.finapp.domain.dataaccess.CardReceivablesLockCercDataAccess
import com.finapp.domain.tables.CardReceivablesLockCercEntity
import com.finapp.resources.repositories.CardReceivablesLockCercRepository
import org.springframework.stereotype.Component

@Component
class CardReceivablesLockCercDataAccessImpl(
        private val cardReceivablesLockCercRepository: CardReceivablesLockCercRepository
) : CardReceivablesLockCercDataAccess {

  override fun create(
          cardReceivablesLockCerc: CardReceivablesLockCercEntity
  ): CardReceivablesLockCercEntity {
    return cardReceivablesLockCercRepository.save(cardReceivablesLockCerc)
  }

  override fun update(
          cardReceivablesLockCerc: CardReceivablesLockCercEntity
  ): CardReceivablesLockCercEntity {
    return cardReceivablesLockCercRepository.save(cardReceivablesLockCerc)
  }

  override fun getById(id: String): CardReceivablesLockCercEntity? {
    return cardReceivablesLockCercRepository.findById(id).orElse(null)
  }

  override fun findByCardReceivablesLockId(
          cardReceivablesLockId: String
  ): CardReceivablesLockCercEntity? {
    return cardReceivablesLockCercRepository.findByCardReceivablesLockId(cardReceivablesLockId)
  }

  override fun incrementCreationRetryAttempts(id: String) {
    cardReceivablesLockCercRepository.incrementCreationRetryAttempts(id)
  }

  override fun incrementProactiveSearchAttempts(id: String) {
    cardReceivablesLockCercRepository.incrementProactiveSearchAttempts(id)
  }
}
