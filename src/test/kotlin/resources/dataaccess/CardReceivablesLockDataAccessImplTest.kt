package resources.dataaccess

import com.finapp.domain.enums.CardReceivablesLockStatus
import com.finapp.domain.enums.RegisterType
import com.finapp.domain.tables.CardReceivablesLockEntity
import com.finapp.resources.repositories.CardReceivablesLockCercRepository
import com.finapp.resources.repositories.CardReceivablesLockNucleaRepository
import com.finapp.resources.repositories.CardReceivablesLockRepository
import com.finapp.web.mappers.CardReceivablesLockEntityMapper
import com.finapp.web.requests.CardReceivablesLockDto
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import utils.TestDataFactory

class CardReceivablesLockDataAccessImplTest {

  private lateinit var cardReceivablesLockRepository: CardReceivablesLockRepository
  private lateinit var cardReceivablesLockNucleaRepository: CardReceivablesLockNucleaRepository
  private lateinit var cardReceivablesLockCercRepository: CardReceivablesLockCercRepository
  private lateinit var dataAccess: CardReceivablesLockDataAccessImpl

  @BeforeEach
  fun setUp() {
    cardReceivablesLockRepository = mockk()
    cardReceivablesLockNucleaRepository = mockk()
    cardReceivablesLockCercRepository = mockk()

    dataAccess =
            CardReceivablesLockDataAccessImpl(
                    cardReceivablesLockRepository,
                    cardReceivablesLockNucleaRepository,
                    cardReceivablesLockCercRepository
            )
  }

  @Test
  fun `should create card receivables lock successfully`() {
    // Given
    val dto = createTestDto()
    val savedEntity = createTestEntity()

    every { cardReceivablesLockRepository.save(any()) } returns savedEntity

    // When
    val result = dataAccess.create(dto)

    // Then
    verify { cardReceivablesLockRepository.save(any()) }
  }

  @Test
  fun `should update card receivables lock successfully`() {
    // Given
    val dto = createTestDto()
    val updatedEntity = createTestEntity()

    every { cardReceivablesLockRepository.save(any()) } returns updatedEntity

    // When
    val result = dataAccess.update(dto)

    // Then
    verify { cardReceivablesLockRepository.save(any()) }
  }

  @Test
  fun `should get by id when entity exists`() {
    // Given
    val id = "test-id-123456789012345678901234"
    val entity = createTestEntity()

    every { cardReceivablesLockRepository.findById(id) } returns java.util.Optional.of(entity)

    // When
    val result = dataAccess.getById(id)

    // Then
    verify { cardReceivablesLockRepository.findById(id) }
    assert(result != null)
  }

  @Test
  fun `should return null when getting by id and entity does not exist`() {
    // Given
    val id = "non-existent-id"

    every { cardReceivablesLockRepository.findById(id) } returns java.util.Optional.empty()

    // When
    val result = dataAccess.getById(id)

    // Then
    verify { cardReceivablesLockRepository.findById(id) }
    assert(result == null)
  }

  @Test
  fun `should get by id with relationships when entity exists`() {
    // Given
    val id = "test-id-123456789012345678901234"
    val entity = createTestEntity()

    every { cardReceivablesLockRepository.findByIdWithRelationships(id) } returns entity

    // When
    val result = dataAccess.getByIdWithRelationships(id)

    // Then
    verify { cardReceivablesLockRepository.findByIdWithRelationships(id) }
    assert(result != null)
  }

  @Test
  fun `should return null when getting by id with relationships and entity does not exist`() {
    // Given
    val id = "non-existent-id"

    every { cardReceivablesLockRepository.findByIdWithRelationships(id) } returns null

    // When
    val result = dataAccess.getByIdWithRelationships(id)

    // Then
    verify { cardReceivablesLockRepository.findByIdWithRelationships(id) }
    assert(result == null)
  }

  @Test
  fun `should get by contract number when entity exists`() {
    // Given
    val contractNumber = "CONTRACT123"
    val entity = createTestEntity()

    every { cardReceivablesLockRepository.findByContractNumber(contractNumber) } returns entity

    // When
    val result = dataAccess.getByContractNumber(contractNumber)

    // Then
    verify { cardReceivablesLockRepository.findByContractNumber(contractNumber) }
    assert(result.contractNumber == contractNumber)
  }

  @Test
  fun `should throw exception when getting by contract number and entity does not exist`() {
    // Given
    val contractNumber = "NON-EXISTENT"

    every { cardReceivablesLockRepository.findByContractNumber(contractNumber) } throws
            org.springframework.dao.EmptyResultDataAccessException(1)

    // When & Then
    org.junit.jupiter.api.assertThrows<org.springframework.dao.EmptyResultDataAccessException> {
      dataAccess.getByContractNumber(contractNumber)
    }
    verify { cardReceivablesLockRepository.findByContractNumber(contractNumber) }
  }

  @Test
  fun `should increment creation retry attempts for CERC`() {
    // Given
    val id = "test-id"
    val registerType = RegisterType.CERC

    every { cardReceivablesLockCercRepository.incrementCreationRetryAttempts(id) } returns Unit

    // When
    dataAccess.incrementCreationRetryAttempts(id, registerType)

    // Then
    verify { cardReceivablesLockCercRepository.incrementCreationRetryAttempts(id) }
  }

  @Test
  fun `should increment creation retry attempts for NUCLEA`() {
    // Given
    val id = "test-id"
    val registerType = RegisterType.NUCLEA

    every { cardReceivablesLockNucleaRepository.incrementCreationRetryAttempts(id) } returns Unit

    // When
    dataAccess.incrementCreationRetryAttempts(id, registerType)

    // Then
    verify { cardReceivablesLockNucleaRepository.incrementCreationRetryAttempts(id) }
  }

  @Test
  fun `should increment proactive search attempts for CERC`() {
    // Given
    val id = "test-id"
    val registerType = RegisterType.CERC

    every { cardReceivablesLockCercRepository.incrementProactiveSearchAttempts(id) } returns Unit

    // When
    dataAccess.incrementProactiveSearchAttempts(id, registerType)

    // Then
    verify { cardReceivablesLockCercRepository.incrementProactiveSearchAttempts(id) }
  }

  @Test
  fun `should increment proactive search attempts for NUCLEA`() {
    // Given
    val id = "test-id"
    val registerType = RegisterType.NUCLEA

    every { cardReceivablesLockNucleaRepository.incrementProactiveSearchAttempts(id) } returns Unit

    // When
    dataAccess.incrementProactiveSearchAttempts(id, registerType)

    // Then
    verify { cardReceivablesLockNucleaRepository.incrementProactiveSearchAttempts(id) }
  }

  @Test
  fun `should get by status when entities exist`() {
    // Given
    val status = CardReceivablesLockStatus.ACTIVE
    val entities = listOf(createTestEntity(), createTestEntity())

    every { cardReceivablesLockRepository.findByStatus(status) } returns entities

    // When
    val result = dataAccess.getByStatus(status)

    // Then
    verify { cardReceivablesLockRepository.findByStatus(status) }
    assert(result.size == 2)
    assert(result.all { it.status == status })
  }

  @Test
  fun `should return empty list when getting by status and no entities exist`() {
    // Given
    val status = CardReceivablesLockStatus.INACTIVE

    every { cardReceivablesLockRepository.findByStatus(status) } returns emptyList()

    // When
    val result = dataAccess.getByStatus(status)

    // Then
    verify { cardReceivablesLockRepository.findByStatus(status) }
    assert(result.isEmpty())
  }

  @Test
  fun `should get by contract number and status when entity exists`() {
    // Given
    val contractNumber = "CONTRACT123"
    val status = CardReceivablesLockStatus.ACTIVE
    val entity = createTestEntity()

    every {
      cardReceivablesLockRepository.findByContractNumberAndStatus(contractNumber, status)
    } returns entity

    // When
    val result = dataAccess.getByContractNumberAndStatus(contractNumber, status)

    // Then
    verify { cardReceivablesLockRepository.findByContractNumberAndStatus(contractNumber, status) }
    assert(result != null)
    assert(result!!.contractNumber == contractNumber)
    assert(result.status == status)
  }

  @Test
  fun `should return null when getting by contract number and status and entity does not exist`() {
    // Given
    val contractNumber = "NON-EXISTENT"
    val status = CardReceivablesLockStatus.ACTIVE

    every {
      cardReceivablesLockRepository.findByContractNumberAndStatus(contractNumber, status)
    } returns null

    // When
    val result = dataAccess.getByContractNumberAndStatus(contractNumber, status)

    // Then
    verify { cardReceivablesLockRepository.findByContractNumberAndStatus(contractNumber, status) }
    assert(result == null)
  }

  @Test
  fun `should get by hub guarantee id and status when entities exist`() {
    // Given
    val hubGuaranteeId = "HUB123"
    val status = CardReceivablesLockStatus.ACTIVE
    val entities = listOf(createTestEntity(), createTestEntity())

    every {
      cardReceivablesLockRepository.findByHubGuaranteeIdAndStatus(hubGuaranteeId, status)
    } returns entities

    // When
    val result = dataAccess.getByHubGuaranteeIdAndStatus(hubGuaranteeId, status)

    // Then
    verify { cardReceivablesLockRepository.findByHubGuaranteeIdAndStatus(hubGuaranteeId, status) }
    assert(result.size == 2)
    assert(result.all { it.status == status })
  }

  @Test
  fun `should return empty list when getting by hub guarantee id and status and no entities exist`() {
    // Given
    val hubGuaranteeId = "HUB456"
    val status = CardReceivablesLockStatus.INACTIVE

    every {
      cardReceivablesLockRepository.findByHubGuaranteeIdAndStatus(hubGuaranteeId, status)
    } returns emptyList()

    // When
    val result = dataAccess.getByHubGuaranteeIdAndStatus(hubGuaranteeId, status)

    // Then
    verify { cardReceivablesLockRepository.findByHubGuaranteeIdAndStatus(hubGuaranteeId, status) }
    assert(result.isEmpty())
  }

  @Test
  fun `should handle null result from repository when getting by id`() {
    // Given
    val id = "non-existent-id"

    every { cardReceivablesLockRepository.findById(id) } returns java.util.Optional.empty()

    // When
    val result = dataAccess.getById(id)

    // Then
    verify { cardReceivablesLockRepository.findById(id) }
    assert(result == null)
  }

  @Test
  fun `should handle null result from repository when getting by id with relationships`() {
    // Given
    val id = "non-existent-id"

    every { cardReceivablesLockRepository.findByIdWithRelationships(id) } returns null

    // When
    val result = dataAccess.getByIdWithRelationships(id)

    // Then
    verify { cardReceivablesLockRepository.findByIdWithRelationships(id) }
    assert(result == null)
  }

  @Test
  fun `should map entity to dto correctly when creating`() {
    // Given
    val dto = createTestDto()
    val savedEntity = createTestEntity()

    every { cardReceivablesLockRepository.save(any()) } returns savedEntity

    // When
    val result = dataAccess.create(dto)

    // Then
    verify { cardReceivablesLockRepository.save(any()) }
    assertNotNull(result)
    assertEquals(savedEntity.id, result.id)
    assertEquals(savedEntity.contractNumber, result.contractNumber)
    assertEquals(savedEntity.status, result.status)
  }

  @Test
  fun `should map entity to dto correctly when updating`() {
    // Given
    val dto = createTestDto()
    val updatedEntity = createTestEntity()

    every { cardReceivablesLockRepository.save(any()) } returns updatedEntity

    // When
    val result = dataAccess.update(dto)

    // Then
    verify { cardReceivablesLockRepository.save(any()) }
    assertNotNull(result)
    assertEquals(updatedEntity.id, result.id)
    assertEquals(updatedEntity.contractNumber, result.contractNumber)
    assertEquals(updatedEntity.status, result.status)
  }

  @Test
  fun `should handle empty list when getting by status`() {
    // Given
    val status = CardReceivablesLockStatus.INACTIVE

    every { cardReceivablesLockRepository.findByStatus(status) } returns emptyList()

    // When
    val result = dataAccess.getByStatus(status)

    // Then
    verify { cardReceivablesLockRepository.findByStatus(status) }
    assert(result.isEmpty())
  }

  @Test
  fun `should handle multiple entities when getting by status`() {
    // Given
    val status = CardReceivablesLockStatus.ACTIVE
    val entities = listOf(createTestEntity(), createTestEntity(), createTestEntity())

    every { cardReceivablesLockRepository.findByStatus(status) } returns entities

    // When
    val result = dataAccess.getByStatus(status)

    // Then
    verify { cardReceivablesLockRepository.findByStatus(status) }
    assertEquals(3, result.size)
    assert(result.all { it.status == status })
  }

  private fun createTestDto(): CardReceivablesLockDto {
    val entity = TestDataFactory.createCardReceivablesLockEntity()
    return CardReceivablesLockEntityMapper.map(entity)
  }

  private fun createTestEntity(): CardReceivablesLockEntity {
    return TestDataFactory.createCardReceivablesLockEntity()
  }
}
