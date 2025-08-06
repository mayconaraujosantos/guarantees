package com.finapp.resources.repositories

import com.finapp.domain.tables.CardReceivablesHolderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardReceivablesHolderRepository : JpaRepository<CardReceivablesHolderEntity, String> {

  fun findByCardReceivablesLockId(cardReceivablesLockId: String): List<CardReceivablesHolderEntity>

  fun findByTaxId(taxId: String): List<CardReceivablesHolderEntity>

  fun findByCardReceivablesLockIdAndTaxId(
          cardReceivablesLockId: String,
          taxId: String
  ): CardReceivablesHolderEntity?

  fun findByPaymentAccountId(paymentAccountId: String): List<CardReceivablesHolderEntity>
}
