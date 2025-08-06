package com.finapp.resources.repositories

import com.finapp.domain.tables.CardReceivablesContractInstallmentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardReceivablesContractInstallmentRepository :
        JpaRepository<CardReceivablesContractInstallmentEntity, String> {

  fun findByCardReceivablesLockId(
          cardReceivablesLockId: String
  ): List<CardReceivablesContractInstallmentEntity>

  fun findByCardReceivablesLockIdOrderByInstallmentNumber(
          cardReceivablesLockId: String
  ): List<CardReceivablesContractInstallmentEntity>

  fun findByInstallmentNumber(
          installmentNumber: Int
  ): List<CardReceivablesContractInstallmentEntity>

  fun findByDate(date: java.time.LocalDate): List<CardReceivablesContractInstallmentEntity>
}
