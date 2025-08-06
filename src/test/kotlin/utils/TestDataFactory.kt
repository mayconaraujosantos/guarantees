package utils

import com.finapp.domain.enums.CardReceivablesLockStatus
import com.finapp.domain.enums.RecalculationPeriodIndicator
import com.finapp.domain.enums.RegisterType
import com.finapp.domain.tables.*
import com.finapp.web.requests.CardReceivablesContractInstallmentDto
import com.finapp.web.requests.CardReceivablesHolderDto
import com.finapp.web.requests.CardReceivablesLockCercDto
import com.finapp.web.requests.CardReceivablesLockDto
import com.finapp.web.requests.CardReceivablesLockNucleaDto
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

object TestDataFactory {

  // Constantes para IDs
  const val TEST_LOCK_ID = "test-id-123456789012345678901234"
  const val TEST_HOLDER_ID = "holder-id-123456789012345678901234"
  const val TEST_INSTALLMENT_ID = "installment-id-123456789012345678901234"
  const val TEST_NUCLEA_ID = "nuclea-id-123456789012345678901234"
  const val TEST_CERC_ID = "cerc-id-123456789012345678901234"

  // Constantes para dados básicos
  const val TEST_HUB_GUARANTEE_ID = "HUB123"
  const val TEST_CONTRACT_NUMBER = "CONTRACT123"
  const val TEST_CONTRACT_SOURCE = "SOURCE"
  const val TEST_IPOC = "IPOC123"
  const val TEST_OWNER_PERSON_ID = "OWNER123"
  const val TEST_TAX_ID = "12345678901"
  const val TEST_PROTOCOL = "PROTOCOL-001"

  // Constantes para valores
  val TEST_AMOUNT = BigDecimal("1000.00")
  val TEST_INSTALLMENT_VALUE = BigDecimal("500.00")

  // Constantes para datas
  val TEST_START_DATE = LocalDate.now()
  val TEST_END_DATE = LocalDate.now().plusMonths(12)
  val TEST_NOW = LocalDateTime.now()

  // Factory methods para CardReceivablesLockEntity
  fun createCardReceivablesLockEntity(
          id: String = TEST_LOCK_ID,
          hubGuaranteeId: String = TEST_HUB_GUARANTEE_ID,
          contractNumber: String = TEST_CONTRACT_NUMBER,
          status: CardReceivablesLockStatus = CardReceivablesLockStatus.ACTIVE,
          register: RegisterType = RegisterType.CERC,
          nucleaEntry: CardReceivablesLockNucleaEntity? = null,
          cercEntry: CardReceivablesLockCercEntity? = null
  ): CardReceivablesLockEntity {
    return CardReceivablesLockEntity(
            id = id,
            hubGuaranteeId = hubGuaranteeId,
            contractNumber = contractNumber,
            contractSource = TEST_CONTRACT_SOURCE,
            ipoc = TEST_IPOC,
            register = register,
            ownerPersonId = TEST_OWNER_PERSON_ID,
            amount = TEST_AMOUNT,
            recalculationFrequency = RecalculationPeriodIndicator.MONTHLY,
            considerBalanceOnInsufficiency = true,
            startDate = TEST_START_DATE,
            endDate = TEST_END_DATE,
            status = status,
            createdAt = TEST_NOW,
            updatedAt = TEST_NOW,
            holders = emptyList(),
            contractInstallments = emptyList(),
            nucleaEntry = nucleaEntry,
            cercEntry = cercEntry
    )
  }

  // Factory methods para CardReceivablesLockDto
  fun createCardReceivablesLockDto(
          id: String = TEST_LOCK_ID,
          hubGuaranteeId: String = TEST_HUB_GUARANTEE_ID,
          contractNumber: String = TEST_CONTRACT_NUMBER,
          status: CardReceivablesLockStatus = CardReceivablesLockStatus.ACTIVE,
          register: RegisterType = RegisterType.CERC
  ): CardReceivablesLockDto {
    return CardReceivablesLockDto(
            id = id,
            hubGuaranteeId = hubGuaranteeId,
            contractNumber = contractNumber,
            contractSource = TEST_CONTRACT_SOURCE,
            ipoc = TEST_IPOC,
            register = register,
            ownerPersonId = TEST_OWNER_PERSON_ID,
            amount = TEST_AMOUNT,
            recalculationFrequency = RecalculationPeriodIndicator.MONTHLY,
            considerBalanceOnInsufficiency = true,
            startDate = TEST_START_DATE,
            endDate = TEST_END_DATE,
            status = status,
            createdAt = TEST_NOW,
            updatedAt = TEST_NOW,
            holders = emptyList(),
            nucleaEntry = null,
            cercEntry = null,
            contractInstallments = emptyList()
    )
  }

  // Factory methods para CardReceivablesHolderEntity
  fun createCardReceivablesHolderEntity(
          id: String = TEST_HOLDER_ID,
          cardReceivablesLock: CardReceivablesLockEntity? = null
  ): CardReceivablesHolderEntity {
    return CardReceivablesHolderEntity(
            id = id,
            taxId = TEST_TAX_ID,
            rootTaxIdOperation = "false",
            paymentAccountBranch = "0001",
            paymentAccountNumber = "123456",
            paymentAccountId = "account-123456789012345678901234",
            cardReceivablesLock = cardReceivablesLock,
            arrangements = emptyList(),
            accreditors = emptyList()
    )
  }

  // Factory methods para CardReceivablesHolderDto
  fun createCardReceivablesHolderDto(id: String = TEST_HOLDER_ID): CardReceivablesHolderDto {
    return CardReceivablesHolderDto(
            id = id,
            taxId = TEST_TAX_ID,
            rootTaxIdOperation = "false",
            paymentAccountBranch = "0001",
            paymentAccountNumber = "123456",
            paymentAccountId = "account-123456789012345678901234",
            cardReceivablesLock = null
    )
  }

  // Factory methods para CardReceivablesContractInstallmentEntity
  fun createCardReceivablesContractInstallmentEntity(
          id: String = TEST_INSTALLMENT_ID,
          installmentNumber: Int = 1,
          cardReceivablesLock: CardReceivablesLockEntity? = null
  ): CardReceivablesContractInstallmentEntity {
    return CardReceivablesContractInstallmentEntity(
            id = id,
            installmentNumber = installmentNumber,
            date = TEST_START_DATE,
            value = TEST_INSTALLMENT_VALUE,
            cardReceivablesLock = cardReceivablesLock
    )
  }

  // Factory methods para CardReceivablesContractInstallmentDto
  fun createCardReceivablesContractInstallmentDto(
          id: String = TEST_INSTALLMENT_ID,
          installmentNumber: Int = 1
  ): CardReceivablesContractInstallmentDto {
    return CardReceivablesContractInstallmentDto(
            id = id,
            installmentNumber = installmentNumber,
            date = TEST_START_DATE,
            value = TEST_INSTALLMENT_VALUE,
            cardReceivablesLock = null
    )
  }

  // Factory methods para CardReceivablesLockNucleaEntity
  fun createCardReceivablesLockNucleaEntity(
          id: String = TEST_NUCLEA_ID,
          protocol: String = TEST_PROTOCOL,
          creationRetryAttempts: Int = 0,
          proactiveSearchAttempts: Int = 0,
          cardReceivablesLock: CardReceivablesLockEntity? = null
  ): CardReceivablesLockNucleaEntity {
    return CardReceivablesLockNucleaEntity(
            id = id,
            protocol = protocol,
            creationRetryAttempts = creationRetryAttempts,
            proactiveSearchAttempts = proactiveSearchAttempts,
            createdAt = TEST_NOW,
            updatedAt = TEST_NOW,
            cardReceivablesLock = cardReceivablesLock
    )
  }

  // Factory methods para CardReceivablesLockNucleaDto
  fun createCardReceivablesLockNucleaDto(
          id: String = TEST_NUCLEA_ID,
          protocol: String = TEST_PROTOCOL
  ): CardReceivablesLockNucleaDto {
    return CardReceivablesLockNucleaDto(
            id = id,
            creationRetryAttempts = 0,
            proactiveSearchAttempts = 0,
            protocol = protocol,
            cardReceivablesLock = null,
            createdAt = TEST_NOW,
            updatedAt = TEST_NOW
    )
  }

  // Factory methods para CardReceivablesLockCercEntity
  fun createCardReceivablesLockCercEntity(
          id: String = TEST_CERC_ID,
          protocol: String = TEST_PROTOCOL,
          creationRetryAttempts: Int = 0,
          proactiveSearchAttempts: Int = 0,
          cardReceivablesLock: CardReceivablesLockEntity? = null
  ): CardReceivablesLockCercEntity {
    return CardReceivablesLockCercEntity(
            id = id,
            protocol = protocol,
            creationRetryAttempts = creationRetryAttempts,
            proactiveSearchAttempts = proactiveSearchAttempts,
            createdAt = TEST_NOW,
            updatedAt = TEST_NOW,
            cardReceivablesLock = cardReceivablesLock,
            protocols = emptyList()
    )
  }

  // Factory methods para CardReceivablesLockCercDto
  fun createCardReceivablesLockCercDto(id: String = TEST_CERC_ID): CardReceivablesLockCercDto {
    return CardReceivablesLockCercDto(
            id = id,
            creationRetryAttempts = 0,
            proactiveSearchAttempts = 0,
            cardReceivablesLock = null,
            protocol = TEST_PROTOCOL,
            createdAt = TEST_NOW,
            updatedAt = TEST_NOW
    )
  }

  // Métodos utilitários para criar entidades com relacionamentos
  fun createCardReceivablesLockWithNuclea(
          lockId: String = TEST_LOCK_ID,
          nucleaId: String = TEST_NUCLEA_ID
  ): CardReceivablesLockEntity {
    val nucleaEntity = createCardReceivablesLockNucleaEntity(nucleaId)
    val lockEntity = createCardReceivablesLockEntity(lockId, register = RegisterType.NUCLEA)
    lockEntity.assignNucleaEntry(nucleaEntity)
    return lockEntity
  }

  fun createCardReceivablesLockWithCerc(
          lockId: String = TEST_LOCK_ID,
          cercId: String = TEST_CERC_ID
  ): CardReceivablesLockEntity {
    val cercEntity = createCardReceivablesLockCercEntity(cercId)
    val lockEntity = createCardReceivablesLockEntity(lockId, register = RegisterType.CERC)
    lockEntity.assignCercEntry(cercEntity)
    return lockEntity
  }

  fun createCardReceivablesLockWithHolder(
          lockId: String = TEST_LOCK_ID,
          holderId: String = TEST_HOLDER_ID
  ): CardReceivablesLockEntity {
    val holderEntity = createCardReceivablesHolderEntity(holderId)
    val lockEntity = createCardReceivablesLockEntity(lockId)
    lockEntity.addHolder(holderEntity)
    return lockEntity
  }

  fun createCardReceivablesLockWithInstallment(
          lockId: String = TEST_LOCK_ID,
          installmentId: String = TEST_INSTALLMENT_ID
  ): CardReceivablesLockEntity {
    val installmentEntity = createCardReceivablesContractInstallmentEntity(installmentId)
    val lockEntity = createCardReceivablesLockEntity(lockId)
    lockEntity.addContractInstallment(installmentEntity)
    return lockEntity
  }
}
