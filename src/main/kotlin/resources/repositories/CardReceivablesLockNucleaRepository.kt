package com.finapp.resources.repositories

import com.finapp.domain.tables.CardReceivablesLockNucleaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CardReceivablesLockNucleaRepository :
        JpaRepository<CardReceivablesLockNucleaEntity, String>, RepositoryOperations {

  fun findByCardReceivablesLockId(cardReceivablesLockId: String): CardReceivablesLockNucleaEntity?

  fun findByProtocol(protocol: String): List<CardReceivablesLockNucleaEntity>

  @Modifying
  @Query(
          "UPDATE CardReceivablesLockNucleaEntity c SET c.creationRetryAttempts = c.creationRetryAttempts + 1, c.updatedAt = CURRENT_TIMESTAMP WHERE c.cardReceivablesLock.id = :id"
  )
  override fun incrementCreationRetryAttempts(@Param("id") id: String)

  @Modifying
  @Query(
          "UPDATE CardReceivablesLockNucleaEntity c SET c.proactiveSearchAttempts = c.proactiveSearchAttempts + 1, c.updatedAt = CURRENT_TIMESTAMP WHERE c.cardReceivablesLock.id = :id"
  )
  override fun incrementProactiveSearchAttempts(@Param("id") id: String)

  @Modifying
  @Query(
          "UPDATE CardReceivablesLockNucleaEntity c SET c.protocol = :protocol, c.updatedAt = CURRENT_TIMESTAMP WHERE c.id = :id"
  )
  fun updateProtocol(@Param("id") id: String, @Param("protocol") protocol: String)
}
