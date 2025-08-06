package resources.repositories

import com.finapp.FinappGuaranteeCardReceivablesServiceApplication
import com.finapp.domain.enums.RegisterType
import com.finapp.domain.tables.CardReceivablesLockCercEntity
import com.finapp.domain.tables.CardReceivablesLockEntity
import com.finapp.resources.repositories.CardReceivablesLockCercRepository
import com.finapp.resources.repositories.CardReceivablesLockRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import utils.TestDataFactory

@SpringBootTest(classes = [FinappGuaranteeCardReceivablesServiceApplication::class])
@ActiveProfiles("test")
class CardReceivablesLockCercRepositoryTest {

  @Autowired private lateinit var cercRepository: CardReceivablesLockCercRepository

  @Autowired private lateinit var lockRepository: CardReceivablesLockRepository

  @Test
  fun `should increment creation retry attempts`() {
    // Given
    val lockEntity = createTestLockEntity()
    val savedLock = lockRepository.save(lockEntity)
    val cercEntity = createTestCercEntity(savedLock)
    val savedCerc = cercRepository.save(cercEntity)

    // When
    cercRepository.incrementCreationRetryAttempts(savedCerc.id)

    // Then
    val updatedCerc = cercRepository.findById(savedCerc.id).orElse(null)
    assertNotNull(updatedCerc)
    assertEquals(1, updatedCerc!!.creationRetryAttempts)
  }

  @Test
  fun `should increment proactive search attempts`() {
    // Given
    val lockEntity = createTestLockEntity()
    val savedLock = lockRepository.save(lockEntity)
    val cercEntity = createTestCercEntity(savedLock)
    val savedCerc = cercRepository.save(cercEntity)

    // When
    cercRepository.incrementProactiveSearchAttempts(savedCerc.id)

    // Then
    val updatedCerc = cercRepository.findById(savedCerc.id).orElse(null)
    assertNotNull(updatedCerc)
    assertEquals(1, updatedCerc!!.proactiveSearchAttempts)
  }

  @Test
  fun `should save and find cerc entity`() {
    // Given
    val lockEntity = createTestLockEntity()
    val savedLock = lockRepository.save(lockEntity)
    val cercEntity = createTestCercEntity(savedLock)

    // When
    val savedCerc = cercRepository.save(cercEntity)
    val foundCerc = cercRepository.findById(savedCerc.id)

    // Then
    assertTrue(foundCerc.isPresent)
    assertEquals(savedCerc.id, foundCerc.get().id)
    assertEquals(savedCerc.protocol, foundCerc.get().protocol)
    assertEquals(savedCerc.creationRetryAttempts, foundCerc.get().creationRetryAttempts)
    assertEquals(savedCerc.proactiveSearchAttempts, foundCerc.get().proactiveSearchAttempts)
  }

  private fun createTestLockEntity(): CardReceivablesLockEntity {
    return TestDataFactory.createCardReceivablesLockEntity(
            id = "test-lock-id-${System.currentTimeMillis()}",
            hubGuaranteeId = "hub-guarantee-${System.currentTimeMillis()}",
            contractNumber = "CONTRACT-${System.currentTimeMillis()}",
            register = RegisterType.CERC
    )
  }

  private fun createTestCercEntity(
          lockEntity: CardReceivablesLockEntity
  ): CardReceivablesLockCercEntity {
    return TestDataFactory.createCardReceivablesLockCercEntity(
            id = "test-cerc-id-${System.currentTimeMillis()}",
            protocol = "PROTOCOL-${System.currentTimeMillis()}",
            cardReceivablesLock = lockEntity
    )
  }
}
