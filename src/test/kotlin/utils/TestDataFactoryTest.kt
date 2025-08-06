package utils

import com.finapp.domain.enums.RegisterType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class TestDataFactoryTest {

  @Test
  fun `should create complete CERC card receivables lock entity with all child tables`() {
    // When
    val lockEntity = TestDataFactory.createCompleteCercCardReceivablesLockEntity()

    // Then
    assertAll(
            { assertNotNull(lockEntity) },
            { assertEquals(RegisterType.CERC, lockEntity.register) },
            { assertEquals(2, lockEntity.holders.size) },
            { assertEquals(3, lockEntity.contractInstallments.size) },
            { assertNotNull(lockEntity.cercEntry) },
            { assertEquals(2, lockEntity.cercEntry!!.protocols.size) },
            { assertEquals(null, lockEntity.nucleaEntry) }
    )

    // Verificar holders
    val holder1 = lockEntity.holders[0]
    val holder2 = lockEntity.holders[1]
    assertAll(
            { assertEquals(2, holder1.arrangements.size) },
            { assertEquals(2, holder1.accreditors.size) },
            { assertEquals(1, holder2.arrangements.size) },
            { assertEquals(0, holder2.accreditors.size) }
    )

    // Verificar contract installments
    assertAll(
            { assertEquals(1, lockEntity.contractInstallments[0].installmentNumber) },
            { assertEquals(2, lockEntity.contractInstallments[1].installmentNumber) },
            { assertEquals(3, lockEntity.contractInstallments[2].installmentNumber) }
    )

    // Verificar CERC protocols
    val cercEntry = lockEntity.cercEntry!!
    assertAll(
            { assertEquals("CREATE", cercEntry.protocols[0].action) },
            { assertEquals("UPDATE", cercEntry.protocols[1].action) }
    )
  }

  @Test
  fun `should create complete NUCLEA card receivables lock entity with all child tables`() {
    // When
    val lockEntity = TestDataFactory.createCompleteNucleaCardReceivablesLockEntity()

    // Then
    assertAll(
            { assertNotNull(lockEntity) },
            { assertEquals(RegisterType.NUCLEA, lockEntity.register) },
            { assertEquals(2, lockEntity.holders.size) },
            { assertEquals(3, lockEntity.contractInstallments.size) },
            { assertNotNull(lockEntity.nucleaEntry) },
            { assertEquals(null, lockEntity.cercEntry) }
    )

    // Verificar NUCLEA entry
    val nucleaEntry = lockEntity.nucleaEntry!!
    assertAll(
            { assertEquals("NUCLEA-PROTOCOL-001", nucleaEntry.protocol) },
            { assertEquals(0, nucleaEntry.creationRetryAttempts) },
            { assertEquals(0, nucleaEntry.proactiveSearchAttempts) }
    )
  }

  @Test
  fun `should create minimal card receivables lock entity`() {
    // When
    val lockEntity = TestDataFactory.createMinimalCardReceivablesLockEntity()

    // Then
    assertAll(
            { assertNotNull(lockEntity) },
            { assertEquals(RegisterType.CERC, lockEntity.register) },
            { assertEquals(0, lockEntity.holders.size) },
            { assertEquals(0, lockEntity.contractInstallments.size) },
            { assertEquals(null, lockEntity.nucleaEntry) },
            { assertEquals(null, lockEntity.cercEntry) }
    )
  }

  @Test
  fun `should create complete card receivables lock entity with custom parameters`() {
    // When
    val lockEntity =
            TestDataFactory.createCompleteCardReceivablesLockEntity(
                    lockId = "custom-lock-id",
                    register = RegisterType.NUCLEA,
                    includeHolders = true,
                    includeInstallments = false,
                    includeNuclea = true,
                    includeCerc = false
            )

    // Then
    assertAll(
            { assertNotNull(lockEntity) },
            { assertEquals("custom-lock-id", lockEntity.id) },
            { assertEquals(RegisterType.NUCLEA, lockEntity.register) },
            { assertEquals(2, lockEntity.holders.size) },
            { assertEquals(0, lockEntity.contractInstallments.size) },
            { assertNotNull(lockEntity.nucleaEntry) },
            { assertEquals(null, lockEntity.cercEntry) }
    )
  }

  @Test
  fun `should create individual child entities correctly`() {
    // When
    val arrangementEntity = TestDataFactory.createCardReceivablesOwnerArrangementEntity()
    val accreditorEntity = TestDataFactory.createCardReceivablesOwnerAccreditorEntity()
    val protocolEntity = TestDataFactory.createCardReceivablesLockCercProtocolEntity()
    val schedulesEntity = TestDataFactory.createCardReceivablesSchedulesEntity()

    // Then
    assertAll(
            { assertNotNull(arrangementEntity) },
            { assertEquals("ARRANGEMENT001", arrangementEntity.arrangementCode) },
            { assertNotNull(accreditorEntity) },
            { assertEquals("98765432109", accreditorEntity.accreditorTaxId) },
            { assertNotNull(protocolEntity) },
            { assertEquals("CREATE", protocolEntity.action) },
            { assertNotNull(schedulesEntity) },
            { assertEquals("CERC", schedulesEntity.register) }
    )
  }
}
