package com.finapp.resources.repositories

import com.finapp.domain.entities.CardReceivablesOwnerAccreditor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardReceivablesOwnerAccreditorRepository :
        JpaRepository<CardReceivablesOwnerAccreditor, String> {

  fun findByCardReceivablesHolderId(
          cardReceivablesHolderId: String
  ): List<CardReceivablesOwnerAccreditor>

  fun findByAccreditorTaxId(accreditorTaxId: String): List<CardReceivablesOwnerAccreditor>
}
