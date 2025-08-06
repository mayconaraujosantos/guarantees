package com.finapp.resources.repositories

import com.finapp.domain.entities.CardReceivablesOwnerArrangement
import com.finapp.domain.tables.CardReceivablesOwnerArrangementEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardReceivablesOwnerArrangementRepository :
        JpaRepository<CardReceivablesOwnerArrangementEntity, String> {

  fun findByCardReceivablesHolderId(
          cardReceivablesHolderId: String
  ): List<CardReceivablesOwnerArrangement>

  fun findByArrangementCode(arrangementCode: String): List<CardReceivablesOwnerArrangement>
}
