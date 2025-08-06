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

  // Factory methods para CardReceivablesOwnerArrangementEntity
  fun createCardReceivablesOwnerArrangementEntity(
          id: String = "arrangement-id-123456789012345678901234",
          arrangementCode: String = "ARRANGEMENT001",
          cardReceivablesHolder: CardReceivablesHolderEntity? = null
  ): CardReceivablesOwnerArrangementEntity {
    return CardReceivablesOwnerArrangementEntity(
            id = id,
            arrangementCode = arrangementCode,
            createdAt = TEST_NOW,
            updatedAt = TEST_NOW,
            cardReceivablesHolder = cardReceivablesHolder
    )
  }

  // Factory methods para CardReceivablesOwnerAccreditorEntity
  fun createCardReceivablesOwnerAccreditorEntity(
          id: String = "accreditor-id-123456789012345678901234",
          accreditorTaxId: String = "98765432109",
          cardReceivablesHolder: CardReceivablesHolderEntity? = null
  ): CardReceivablesOwnerAccreditorEntity {
    return CardReceivablesOwnerAccreditorEntity(
            id = id,
            accreditorTaxId = accreditorTaxId,
            createdAt = TEST_NOW,
            updatedAt = TEST_NOW,
            cardReceivablesHolder = cardReceivablesHolder
    )
  }

  // Factory methods para CardReceivablesLockCercProtocolEntity
  fun createCardReceivablesLockCercProtocolEntity(
          id: String = "protocol-id-123456789012345678901234",
          action: String = "CREATE",
          protocol: String = TEST_PROTOCOL,
          cardReceivablesLockCerc: CardReceivablesLockCercEntity? = null
  ): CardReceivablesLockCercProtocolEntity {
    return CardReceivablesLockCercProtocolEntity(
            id = id,
            action = action,
            protocol = protocol,
            createdAt = TEST_NOW,
            cardReceivablesLockCerc = cardReceivablesLockCerc
    )
  }

  // Factory methods para CardReceivablesSchedulesEntity
  fun createCardReceivablesSchedulesEntity(
          id: String = "schedules-id-123456789012345678901234",
          personId: String = TEST_OWNER_PERSON_ID,
          register: String = "CERC",
          arrangement: String = "ARRANGEMENT001",
          accreditor: String = "98765432109",
          source: String = "SOURCE",
          schedules: String = "{\"schedule\": \"data\"}"
  ): CardReceivablesSchedulesEntity {
    return CardReceivablesSchedulesEntity(
            id = id,
            personId = personId,
            register = register,
            arrangement = arrangement,
            accreditor = accreditor,
            source = source,
            schedules = schedules,
            createdAt = TEST_NOW
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

  // Métodos para criar entidades completas com todas as tabelas filhas
  fun createCompleteCardReceivablesLockEntity(
          lockId: String = TEST_LOCK_ID,
          register: RegisterType = RegisterType.CERC,
          includeHolders: Boolean = true,
          includeInstallments: Boolean = true,
          includeNuclea: Boolean = false,
          includeCerc: Boolean = false
  ): CardReceivablesLockEntity {
    val lockEntity = createCardReceivablesLockEntity(lockId, register = register)

    // Adicionar holders com arrangements e accreditors
    if (includeHolders) {
      val holder1 = createCardReceivablesHolderEntity("holder-1-123456789012345678901234")
      val holder2 = createCardReceivablesHolderEntity("holder-2-123456789012345678901234")

      // Adicionar arrangements para holder1
      val arrangement1 =
              createCardReceivablesOwnerArrangementEntity(
                      "arrangement-1-123456789012345678901234",
                      "ARRANGEMENT001",
                      holder1
              )
      val arrangement2 =
              createCardReceivablesOwnerArrangementEntity(
                      "arrangement-2-123456789012345678901234",
                      "ARRANGEMENT002",
                      holder1
              )
      holder1.arrangements = listOf(arrangement1, arrangement2)

      // Adicionar accreditors para holder1
      val accreditor1 =
              createCardReceivablesOwnerAccreditorEntity(
                      "accreditor-1-123456789012345678901234",
                      "11111111111",
                      holder1
              )
      val accreditor2 =
              createCardReceivablesOwnerAccreditorEntity(
                      "accreditor-2-123456789012345678901234",
                      "22222222222",
                      holder1
              )
      holder1.accreditors = listOf(accreditor1, accreditor2)

      // Adicionar arrangements para holder2
      val arrangement3 =
              createCardReceivablesOwnerArrangementEntity(
                      "arrangement-3-123456789012345678901234",
                      "ARRANGEMENT003",
                      holder2
              )
      holder2.arrangements = listOf(arrangement3)

      lockEntity.addHolder(holder1)
      lockEntity.addHolder(holder2)
    }

    // Adicionar contract installments
    if (includeInstallments) {
      val installment1 =
              createCardReceivablesContractInstallmentEntity(
                      "installment-1-123456789012345678901234",
                      1
              )
      val installment2 =
              createCardReceivablesContractInstallmentEntity(
                      "installment-2-123456789012345678901234",
                      2
              )
      val installment3 =
              createCardReceivablesContractInstallmentEntity(
                      "installment-3-123456789012345678901234",
                      3
              )

      lockEntity.addContractInstallment(installment1)
      lockEntity.addContractInstallment(installment2)
      lockEntity.addContractInstallment(installment3)
    }

    // Adicionar nuclea entry se necessário
    if (includeNuclea && register == RegisterType.NUCLEA) {
      val nucleaEntity =
              createCardReceivablesLockNucleaEntity(
                      "nuclea-123456789012345678901234",
                      "NUCLEA-PROTOCOL-001"
              )
      lockEntity.assignNucleaEntry(nucleaEntity)
    }

    // Adicionar cerc entry se necessário
    if (includeCerc && register == RegisterType.CERC) {
      val cercEntity =
              createCardReceivablesLockCercEntity(
                      "cerc-123456789012345678901234",
                      "CERC-PROTOCOL-001"
              )

      // Adicionar protocols para CERC
      val protocol1 =
              createCardReceivablesLockCercProtocolEntity(
                      "protocol-1-123456789012345678901234",
                      "CREATE",
                      "CERC-PROTOCOL-001",
                      cercEntity
              )
      val protocol2 =
              createCardReceivablesLockCercProtocolEntity(
                      "protocol-2-123456789012345678901234",
                      "UPDATE",
                      "CERC-PROTOCOL-002",
                      cercEntity
              )
      cercEntity.protocols = listOf(protocol1, protocol2)

      lockEntity.assignCercEntry(cercEntity)
    }

    return lockEntity
  }

  // Método para criar CardReceivablesLockEntity com NUCLEA completo
  fun createCompleteNucleaCardReceivablesLockEntity(
          lockId: String = TEST_LOCK_ID
  ): CardReceivablesLockEntity {
    return createCompleteCardReceivablesLockEntity(
            lockId = lockId,
            register = RegisterType.NUCLEA,
            includeHolders = true,
            includeInstallments = true,
            includeNuclea = true,
            includeCerc = false
    )
  }

  // Método para criar CardReceivablesLockEntity com CERC completo
  fun createCompleteCercCardReceivablesLockEntity(
          lockId: String = TEST_LOCK_ID
  ): CardReceivablesLockEntity {
    return createCompleteCardReceivablesLockEntity(
            lockId = lockId,
            register = RegisterType.CERC,
            includeHolders = true,
            includeInstallments = true,
            includeNuclea = false,
            includeCerc = true
    )
  }

  // Método para criar CardReceivablesLockEntity mínima (apenas dados básicos)
  fun createMinimalCardReceivablesLockEntity(
          lockId: String = TEST_LOCK_ID,
          register: RegisterType = RegisterType.CERC
  ): CardReceivablesLockEntity {
    return createCompleteCardReceivablesLockEntity(
            lockId = lockId,
            register = register,
            includeHolders = false,
            includeInstallments = false,
            includeNuclea = false,
            includeCerc = false
    )
  }
}
