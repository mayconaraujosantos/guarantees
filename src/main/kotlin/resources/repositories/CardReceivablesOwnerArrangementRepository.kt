package com.finapp.resources.repositories

import com.finapp.domain.entities.CardReceivablesOwnerArrangement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardReceivablesOwnerArrangementRepository :
        JpaRepository<CardReceivablesOwnerArrangement, String> {

  fun findByCardReceivablesHolderId(
          cardReceivablesHolderId: String
  ): List<CardReceivablesOwnerArrangement>

  fun findByArrangementCode(arrangementCode: String): List<CardReceivablesOwnerArrangement>
}
