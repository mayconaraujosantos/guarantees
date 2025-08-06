package com.finapp.resources.repositories

import com.finapp.domain.entities.CardReceivablesLockCercProtocol
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardReceivablesLockCercProtocolRepository :
        JpaRepository<CardReceivablesLockCercProtocol, String> {

  fun findByCardReceivablesLockCercId(
          cardReceivablesLockCercId: String
  ): List<CardReceivablesLockCercProtocol>

  fun findByProtocol(protocol: String): List<CardReceivablesLockCercProtocol>

  fun findByAction(action: String): List<CardReceivablesLockCercProtocol>
}
