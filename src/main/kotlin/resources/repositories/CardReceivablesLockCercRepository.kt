package com.finapp.resources.repositories

import com.finapp.domain.tables.CardReceivablesLockCercEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CardReceivablesLockCercRepository :
        JpaRepository<CardReceivablesLockCercEntity, String>, RepositoryOperations {

  fun findByCardReceivablesLockId(cardReceivablesLockId: String): CardReceivablesLockCercEntity?

  @Modifying
  @Query(
          "UPDATE CardReceivablesLockCercEntity c SET c.creationRetryAttempts = c.creationRetryAttempts + 1, c.updatedAt = CURRENT_TIMESTAMP WHERE c.cardReceivablesLock.id = :id"
  )
  override fun incrementCreationRetryAttempts(@Param("id") id: String)

  @Modifying
  @Query(
          "UPDATE CardReceivablesLockCercEntity c SET c.proactiveSearchAttempts = c.proactiveSearchAttempts + 1, c.updatedAt = CURRENT_TIMESTAMP WHERE c.cardReceivablesLock.id = :id"
  )
  override fun incrementProactiveSearchAttempts(@Param("id") id: String)
}
