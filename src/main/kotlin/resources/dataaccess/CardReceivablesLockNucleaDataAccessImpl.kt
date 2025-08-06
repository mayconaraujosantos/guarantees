package com.finapp.resources.dataaccess

import com.finapp.domain.dataaccess.CardReceivablesLockNucleaDataAccess
import com.finapp.domain.tables.CardReceivablesLockNucleaEntity
import com.finapp.resources.repositories.CardReceivablesLockNucleaRepository
import org.springframework.stereotype.Repository

@Repository
class CardReceivablesLockNucleaDataAccessImpl(
        private val cardReceivablesLockNucleaRepository: CardReceivablesLockNucleaRepository
) : CardReceivablesLockNucleaDataAccess {

  override fun create(
          cardReceivablesLockNuclea: CardReceivablesLockNucleaEntity
  ): CardReceivablesLockNucleaEntity {
    return cardReceivablesLockNucleaRepository.save(cardReceivablesLockNuclea)
  }

  override fun update(
          cardReceivablesLockNuclea: CardReceivablesLockNucleaEntity
  ): CardReceivablesLockNucleaEntity {
    return cardReceivablesLockNucleaRepository.save(cardReceivablesLockNuclea)
  }

  override fun getById(id: String): CardReceivablesLockNucleaEntity? {
    return cardReceivablesLockNucleaRepository.findById(id).orElse(null)
  }

  override fun findByCardReceivablesLockId(
          cardReceivablesLockId: String
  ): CardReceivablesLockNucleaEntity? {
    return cardReceivablesLockNucleaRepository.findByCardReceivablesLockId(cardReceivablesLockId)
  }

  override fun findByProtocol(protocol: String): List<CardReceivablesLockNucleaEntity> {
    return cardReceivablesLockNucleaRepository.findByProtocol(protocol)
  }

  override fun incrementCreationRetryAttempts(id: String) {
    cardReceivablesLockNucleaRepository.incrementCreationRetryAttempts(id)
  }

  override fun incrementProactiveSearchAttempts(id: String) {
    cardReceivablesLockNucleaRepository.incrementProactiveSearchAttempts(id)
  }

  override fun updateProtocol(id: String, protocol: String) {
    cardReceivablesLockNucleaRepository.updateProtocol(id, protocol)
  }
}
