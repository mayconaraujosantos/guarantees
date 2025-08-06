package com.finapp.resources.repositories

import com.finapp.domain.enums.CardReceivablesLockStatus
import com.finapp.domain.tables.CardReceivablesLockEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CardReceivablesLockRepository : JpaRepository<CardReceivablesLockEntity, String> {

  @Query(
          """
        SELECT c FROM CardReceivablesLockEntity c
        LEFT JOIN FETCH c.holders
        LEFT JOIN FETCH c.nucleaEntry
        LEFT JOIN FETCH c.cercEntry
        LEFT JOIN FETCH c.contractInstallments
        WHERE c.id = :id
    """
  )
  fun findByIdWithRelationships(@Param("id") id: String): CardReceivablesLockEntity?

  // Buscar por contract_number simples
  fun findByContractNumber(contractNumber: String): CardReceivablesLockEntity

  // Buscar por contract_number com relacionamentos
  @Query(
          """
        SELECT c FROM CardReceivablesLockEntity c
        LEFT JOIN FETCH c.holders
        LEFT JOIN FETCH c.nucleaEntry
        LEFT JOIN FETCH c.cercEntry
        LEFT JOIN FETCH c.contractInstallments
        WHERE c.contractNumber = :contractNumber
    """
  )
  fun findByContractNumberWithRelationships(
          @Param("contractNumber") contractNumber: String
  ): CardReceivablesLockEntity?

  // Buscar por contract_number apenas se estiver ativo
  fun findByContractNumberAndStatus(
          contractNumber: String,
          status: CardReceivablesLockStatus
  ): CardReceivablesLockEntity?

  // Buscar por contract_number com relacionamentos e status ativo
  @Query(
          """
        SELECT c FROM CardReceivablesLockEntity c
        LEFT JOIN FETCH c.holders
        LEFT JOIN FETCH c.nucleaEntry
        LEFT JOIN FETCH c.cercEntry
        LEFT JOIN FETCH c.contractInstallments
        WHERE c.contractNumber = :contractNumber AND c.status = :status
    """
  )
  fun findByContractNumberAndStatusWithRelationships(
          @Param("contractNumber") contractNumber: String,
          @Param("status") status: CardReceivablesLockStatus
  ): CardReceivablesLockEntity?

  // Buscar apenas registros ativos
  fun findByStatus(status: CardReceivablesLockStatus): List<CardReceivablesLockEntity>

  // Buscar por ID apenas se estiver ativo
  fun findByIdAndStatus(id: String, status: CardReceivablesLockStatus): CardReceivablesLockEntity?

  // Buscar todos os ativos
  fun findByStatusOrderByCreatedAtDesc(
          status: CardReceivablesLockStatus
  ): List<CardReceivablesLockEntity>

  // Buscar por hub guarantee id apenas se estiver ativo
  fun findByHubGuaranteeIdAndStatus(
          hubGuaranteeId: String,
          status: CardReceivablesLockStatus
  ): List<CardReceivablesLockEntity>
}
