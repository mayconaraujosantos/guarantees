package resources.repositories

import com.finapp.FinappGuaranteeCardReceivablesServiceApplication
import com.finapp.domain.enums.CardReceivablesLockStatus
import com.finapp.domain.tables.CardReceivablesLockEntity
import com.finapp.resources.repositories.CardReceivablesLockRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import utils.TestDataFactory

@SpringBootTest(classes = [FinappGuaranteeCardReceivablesServiceApplication::class])
@ActiveProfiles("test")
class CardReceivablesLockRepositoryTest {

  @Autowired private lateinit var repository: CardReceivablesLockRepository

  @Test
  fun `should find by contract number when exists`() {
    // Given
    val entity = createTestEntity()
    val savedEntity = repository.save(entity)

    // When
    val result = repository.findByContractNumber(savedEntity.contractNumber)

    // Then
    assertNotNull(result)
    assertEquals(savedEntity.contractNumber, result.contractNumber)
  }

  @Test
  fun `should find by contract number with relationships when exists`() {
    // Given
    val entity = createTestEntity()
    val savedEntity = repository.save(entity)

    // When
    val result = repository.findByContractNumberWithRelationships(savedEntity.contractNumber)

    // Then
    assertNotNull(result)
    assertEquals(savedEntity.contractNumber, result!!.contractNumber)
  }

  @Test
  fun `should find by contract number with installments when exists`() {
    // Given
    val entity = createTestEntity()
    val savedEntity = repository.save(entity)

    // When
    val result = repository.findByContractNumberWithInstallments(savedEntity.contractNumber)

    // Then
    assertNotNull(result)
    assertEquals(savedEntity.contractNumber, result!!.contractNumber)
  }

  @Test
  fun `should find by contract number and status when exists`() {
    // Given
    val entity = createTestEntity()
    val savedEntity = repository.save(entity)

    // When
    val result =
            repository.findByContractNumberAndStatus(
                    savedEntity.contractNumber,
                    CardReceivablesLockStatus.ACTIVE
            )

    // Then
    assertNotNull(result)
    assertEquals(savedEntity.contractNumber, result!!.contractNumber)
    assertEquals(CardReceivablesLockStatus.ACTIVE, result.status)
  }

  @Test
  fun `should find by status when entities exist`() {
    // Given
    val entity1 = createTestEntity()
    val entity2 = createTestEntity()
    repository.save(entity1)
    repository.save(entity2)

    // When
    val result = repository.findByStatus(CardReceivablesLockStatus.ACTIVE)

    // Then
    assertNotNull(result)
    assertTrue(result.isNotEmpty())
    assertTrue(result.all { it.status == CardReceivablesLockStatus.ACTIVE })
  }

  @Test
  fun `should find by id and status when exists`() {
    // Given
    val entity = createTestEntity()
    val savedEntity = repository.save(entity)

    // When
    val result = repository.findByIdAndStatus(savedEntity.id, CardReceivablesLockStatus.ACTIVE)

    // Then
    assertNotNull(result)
    assertEquals(savedEntity.id, result!!.id)
    assertEquals(CardReceivablesLockStatus.ACTIVE, result.status)
  }

  @Test
  fun `should find by status ordered by created at desc`() {
    // Given
    val entity1 = createTestEntity()
    val entity2 = createTestEntity()
    repository.save(entity1)
    repository.save(entity2)

    // When
    val result = repository.findByStatusOrderByCreatedAtDesc(CardReceivablesLockStatus.ACTIVE)

    // Then
    assertNotNull(result)
    assertTrue(result.isNotEmpty())
    assertTrue(result.all { it.status == CardReceivablesLockStatus.ACTIVE })
  }

  @Test
  fun `should find by hub guarantee id and status when exists`() {
    // Given
    val entity = createTestEntity()
    val savedEntity = repository.save(entity)

    // When
    val result =
            repository.findByHubGuaranteeIdAndStatus(
                    savedEntity.hubGuaranteeId,
                    CardReceivablesLockStatus.ACTIVE
            )

    // Then
    assertNotNull(result)
    assertTrue(result.isNotEmpty())
    assertTrue(result.all { it.hubGuaranteeId == savedEntity.hubGuaranteeId })
    assertTrue(result.all { it.status == CardReceivablesLockStatus.ACTIVE })
  }

  @Test
  fun `should find by id with relationships when exists`() {
    // Given
    val entity = createTestEntity()
    val savedEntity = repository.save(entity)

    // When
    val result = repository.findByIdWithRelationships(savedEntity.id)

    // Then
    assertNotNull(result)
    assertEquals(savedEntity.id, result!!.id)
  }

  @Test
  fun `should find by id with installments when exists`() {
    // Given
    val entity = createTestEntity()
    val savedEntity = repository.save(entity)

    // When
    val result = repository.findByIdWithInstallments(savedEntity.id)

    // Then
    assertNotNull(result)
    assertEquals(savedEntity.id, result!!.id)
  }

  private fun createTestEntity(): CardReceivablesLockEntity {
    return TestDataFactory.createCardReceivablesLockEntity(
            id = "test-id-${System.currentTimeMillis()}",
            hubGuaranteeId = "hub-guarantee-${System.currentTimeMillis()}",
            contractNumber = "CONTRACT-${System.currentTimeMillis()}"
    )
  }
}
