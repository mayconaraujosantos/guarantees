package com.finapp.domain.dataaccess

import com.finapp.domain.enums.CardReceivablesLockStatus
import com.finapp.domain.enums.RegisterType
import com.finapp.web.requests.CardReceivablesLockDto

interface CardReceivablesLockDataAccess {
  fun create(cardReceivablesLockDto: CardReceivablesLockDto): CardReceivablesLockDto
  fun update(cardReceivablesLockDto: CardReceivablesLockDto): CardReceivablesLockDto
  fun getById(id: String): CardReceivablesLockDto?
  fun getByIdWithRelationships(id: String): CardReceivablesLockDto?
  fun getByContractNumber(contractNumber: String): CardReceivablesLockDto
  fun getByStatus(status: CardReceivablesLockStatus): List<CardReceivablesLockDto>
  fun getByContractNumberAndStatus(
          contractNumber: String,
          status: CardReceivablesLockStatus
  ): CardReceivablesLockDto?
  fun getByHubGuaranteeIdAndStatus(
          hubGuaranteeId: String,
          status: CardReceivablesLockStatus
  ): List<CardReceivablesLockDto>
  fun incrementCreationRetryAttempts(id: String, registerType: RegisterType)
  fun incrementProactiveSearchAttempts(id: String, registerType: RegisterType)
}
