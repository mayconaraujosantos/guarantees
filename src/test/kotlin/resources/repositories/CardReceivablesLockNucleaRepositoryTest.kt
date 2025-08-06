package resources.repositories

import com.finapp.FinappGuaranteeCardReceivablesServiceApplication
import com.finapp.domain.enums.RegisterType
import com.finapp.domain.tables.CardReceivablesLockEntity
import com.finapp.domain.tables.CardReceivablesLockNucleaEntity
import com.finapp.resources.repositories.CardReceivablesLockNucleaRepository
import com.finapp.resources.repositories.CardReceivablesLockRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import utils.TestDataFactory

@SpringBootTest(classes = [FinappGuaranteeCardReceivablesServiceApplication::class])
@ActiveProfiles("test")
class CardReceivablesLockNucleaRepositoryTest {

  @Autowired private lateinit var nucleaRepository: CardReceivablesLockNucleaRepository

  @Autowired private lateinit var lockRepository: CardReceivablesLockRepository

  @Test
  fun `should increment creation retry attempts`() {
    // Given
    val lockEntity = createTestLockEntity()
    val savedLock = lockRepository.save(lockEntity)
    val nucleaEntity = createTestNucleaEntity(savedLock)
    val savedNuclea = nucleaRepository.save(nucleaEntity)

    // When
    nucleaRepository.incrementCreationRetryAttempts(savedNuclea.id)

    // Then
    val updatedNuclea = nucleaRepository.findById(savedNuclea.id).orElse(null)
    assertNotNull(updatedNuclea)
    assertEquals(1, updatedNuclea!!.creationRetryAttempts)
  }

  @Test
  fun `should increment proactive search attempts`() {
    // Given
    val lockEntity = createTestLockEntity()
    val savedLock = lockRepository.save(lockEntity)
    val nucleaEntity = createTestNucleaEntity(savedLock)
    val savedNuclea = nucleaRepository.save(nucleaEntity)

    // When
    nucleaRepository.incrementProactiveSearchAttempts(savedNuclea.id)

    // Then
    val updatedNuclea = nucleaRepository.findById(savedNuclea.id).orElse(null)
    assertNotNull(updatedNuclea)
    assertEquals(1, updatedNuclea!!.proactiveSearchAttempts)
  }

  @Test
  fun `should save and find nuclea entity`() {
    // Given
    val lockEntity = createTestLockEntity()
    val savedLock = lockRepository.save(lockEntity)
    val nucleaEntity = createTestNucleaEntity(savedLock)

    // When
    val savedNuclea = nucleaRepository.save(nucleaEntity)
    val foundNuclea = nucleaRepository.findById(savedNuclea.id)

    // Then
    assertTrue(foundNuclea.isPresent)
    assertEquals(savedNuclea.id, foundNuclea.get().id)
    assertEquals(savedNuclea.protocol, foundNuclea.get().protocol)
    assertEquals(savedNuclea.creationRetryAttempts, foundNuclea.get().creationRetryAttempts)
    assertEquals(savedNuclea.proactiveSearchAttempts, foundNuclea.get().proactiveSearchAttempts)
  }

  private fun createTestLockEntity(): CardReceivablesLockEntity {
    return TestDataFactory.createCardReceivablesLockEntity(
            id = "test-lock-id-${System.currentTimeMillis()}",
            hubGuaranteeId = "hub-guarantee-${System.currentTimeMillis()}",
            contractNumber = "CONTRACT-${System.currentTimeMillis()}",
            register = RegisterType.NUCLEA
    )
  }

  private fun createTestNucleaEntity(
          lockEntity: CardReceivablesLockEntity
  ): CardReceivablesLockNucleaEntity {
    return TestDataFactory.createCardReceivablesLockNucleaEntity(
            id = "test-nuclea-id-${System.currentTimeMillis()}",
            protocol = "PROTOCOL-${System.currentTimeMillis()}",
            cardReceivablesLock = lockEntity
    )
  }
}
