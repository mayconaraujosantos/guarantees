package domain.tables

import com.finapp.domain.enums.CardReceivablesLockStatus
import com.finapp.domain.enums.RegisterType
import com.finapp.domain.tables.CardReceivablesContractInstallmentEntity
import com.finapp.domain.tables.CardReceivablesHolderEntity
import com.finapp.domain.tables.CardReceivablesLockCercEntity
import com.finapp.domain.tables.CardReceivablesLockEntity
import com.finapp.domain.tables.CardReceivablesLockNucleaEntity
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import utils.TestDataFactory

class CardReceivablesLockEntityTest {

  @Test
  fun `should maintain bidirectional relationship when adding holder`() {
    // Given
    val lock = createTestCardReceivablesLock()
    val holder = createTestCardReceivablesHolder()

    // When
    lock.addHolder(holder)

    // Then
    assertAll(
            { assert(lock.holders.contains(holder)) },
            { assert(holder.cardReceivablesLock == lock) }
    )
  }

  @Test
  fun `should maintain bidirectional relationship when removing holder`() {
    // Given
    val lock = createTestCardReceivablesLock()
    val holder = createTestCardReceivablesHolder()
    lock.addHolder(holder)

    // When
    lock.removeHolder(holder)

    // Then
    assertAll(
            { assert(!lock.holders.contains(holder)) },
            { assert(holder.cardReceivablesLock == null) }
    )
  }

  @Test
  fun `should maintain bidirectional relationship when adding contract installment`() {
    // Given
    val lock = createTestCardReceivablesLock()
    val installment = createTestContractInstallment()

    // When
    lock.addContractInstallment(installment)

    // Then
    assertAll(
            { assert(lock.contractInstallments.contains(installment)) },
            { assert(installment.cardReceivablesLock == lock) }
    )
  }

  @Test
  fun `should maintain bidirectional relationship when removing contract installment`() {
    // Given
    val lock = createTestCardReceivablesLock()
    val installment = createTestContractInstallment()
    lock.addContractInstallment(installment)

    // When
    lock.removeContractInstallment(installment)

    // Then
    assertAll(
            { assert(!lock.contractInstallments.contains(installment)) },
            { assert(installment.cardReceivablesLock == null) }
    )
  }

  @Test
  fun `should maintain bidirectional relationship when setting nuclea entry`() {
    // Given
    val lock = createTestCardReceivablesLock()
    val nuclea = createTestNucleaEntity()

    // When
    lock.assignNucleaEntry(nuclea)

    // Then
    assertAll(
            { assert(lock.nucleaEntry == nuclea) },
            { assert(nuclea.cardReceivablesLock == lock) }
    )
  }

  @Test
  fun `should maintain bidirectional relationship when setting cerc entry`() {
    // Given
    val lock = createTestCardReceivablesLock()
    val cerc = createTestCercEntity()

    // When
    lock.assignCercEntry(cerc)

    // Then
    assertAll({ assert(lock.cercEntry == cerc) }, { assert(cerc.cardReceivablesLock == lock) })
  }

  @Test
  fun `should clear bidirectional relationship when setting nuclea entry to null`() {
    // Given
    val lock = createTestCardReceivablesLock()
    val nuclea = createTestNucleaEntity()
    lock.assignNucleaEntry(nuclea)

    // When
    lock.assignNucleaEntry(null)

    // Then
    assertAll({ assert(lock.nucleaEntry == null) }, { assert(nuclea.cardReceivablesLock == null) })
  }

  @Test
  fun `should require cerc entry to be set`() {
    // Given
    val lock = createTestCardReceivablesLock()
    val cerc = createTestCercEntity()

    // When
    lock.assignCercEntry(cerc)

    // Then
    assert(lock.cercEntry == cerc)
    assert(cerc.cardReceivablesLock == lock)
    assert(lock.requireCercEntry() == cerc)
  }

  @Test
  fun `should throw exception when cerc entry is not set`() {
    // Given
    val lock = createTestCardReceivablesLock()

    // When & Then
    assertThrows<IllegalStateException> { lock.requireCercEntry() }
  }

  @Test
  fun `should require nuclea entry to be set`() {
    // Given
    val lock = createTestCardReceivablesLock()
    val nuclea = createTestNucleaEntity()

    // When
    lock.assignNucleaEntry(nuclea)

    // Then
    assert(lock.nucleaEntry == nuclea)
    assert(nuclea.cardReceivablesLock == lock)
    assert(lock.requireNucleaEntry() == nuclea)
  }

  @Test
  fun `should throw exception when nuclea entry is not set`() {
    // Given
    val lock = createTestCardReceivablesLock()

    // When & Then
    assertThrows<IllegalStateException> { lock.requireNucleaEntry() }
  }

  @Test
  fun `should validate nuclea entry for NUCLEA register type`() {
    // Given
    val lock = createTestCardReceivablesLock(RegisterType.NUCLEA)
    lock.assignNucleaEntry(createTestNucleaEntity())

    // When
    val isValid = lock.validateNucleaEntryForRegister()

    // Then
    assert(isValid)
  }

  @Test
  fun `should not validate nuclea entry for NUCLEA register type when null`() {
    // Given
    val lock = createTestCardReceivablesLock(RegisterType.NUCLEA)

    // When
    val isValid = lock.validateNucleaEntryForRegister()

    // Then
    assert(!isValid)
  }

  @Test
  fun `should validate nuclea entry for CERC register type even when null`() {
    // Given
    val lock = createTestCardReceivablesLock(RegisterType.CERC)
    // CERC não requer nucleaEntry, então deve ser válido mesmo quando null

    // When
    val isValid = lock.validateNucleaEntryForRegister()

    // Then
    assert(isValid)
  }

  @Test
  fun `should require nuclea entry for NUCLEA register type when set`() {
    // Given
    val lock = createTestCardReceivablesLock(RegisterType.NUCLEA)
    val nuclea = createTestNucleaEntity()
    lock.assignNucleaEntry(nuclea)

    // When
    val result = lock.requireNucleaEntryForRegister()

    // Then
    assert(result == nuclea)
  }

  @Test
  fun `should throw exception when requiring nuclea entry for NUCLEA register type but not set`() {
    // Given
    val lock = createTestCardReceivablesLock(RegisterType.NUCLEA)

    // When & Then
    assertThrows<IllegalStateException> { lock.requireNucleaEntryForRegister() }
  }

  @Test
  fun `should activate card receivables lock`() {
    // Given
    val lock = createTestCardReceivablesLock()

    // When
    lock.activate()

    // Then
    assertAll(
            { assert(lock.status == CardReceivablesLockStatus.ACTIVE) },
            { assert(lock.isActive()) },
            { assert(!lock.isInactive()) }
    )
  }

  @Test
  fun `should deactivate card receivables lock`() {
    // Given
    val lock = createTestCardReceivablesLock()

    // When
    lock.deactivate()

    // Then
    assertAll(
            { assert(lock.status == CardReceivablesLockStatus.INACTIVE) },
            { assert(lock.isInactive()) },
            { assert(!lock.isActive()) }
    )
  }

  private fun createTestCardReceivablesLock(
          register: RegisterType = RegisterType.CERC
  ): CardReceivablesLockEntity {
    return TestDataFactory.createCardReceivablesLockEntity(
            status = CardReceivablesLockStatus.INACTIVE,
            register = register
    )
  }

  private fun createTestCardReceivablesHolder(): CardReceivablesHolderEntity {
    return TestDataFactory.createCardReceivablesHolderEntity()
  }

  private fun createTestContractInstallment(): CardReceivablesContractInstallmentEntity {
    return TestDataFactory.createCardReceivablesContractInstallmentEntity()
  }

  private fun createTestNucleaEntity(): CardReceivablesLockNucleaEntity {
    return TestDataFactory.createCardReceivablesLockNucleaEntity()
  }

  private fun createTestCercEntity(): CardReceivablesLockCercEntity {
    return TestDataFactory.createCardReceivablesLockCercEntity()
  }
}
