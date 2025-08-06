package com.finapp.resources.repositories

import com.finapp.domain.entities.CardReceivablesOwnerAccreditor
import com.finapp.domain.tables.CardReceivablesOwnerAccreditorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardReceivablesOwnerAccreditorRepository :
        JpaRepository<CardReceivablesOwnerAccreditorEntity, String> {

  fun findByCardReceivablesHolderId(
          cardReceivablesHolderId: String
  ): List<CardReceivablesOwnerAccreditor>

  fun findByAccreditorTaxId(accreditorTaxId: String): List<CardReceivablesOwnerAccreditor>
}
