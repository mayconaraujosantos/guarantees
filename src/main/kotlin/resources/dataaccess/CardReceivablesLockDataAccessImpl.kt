package resources.dataaccess

import com.finapp.domain.dataaccess.CardReceivablesLockDataAccess
import com.finapp.domain.enums.CardReceivablesLockStatus
import com.finapp.domain.enums.RegisterType
import com.finapp.resources.repositories.CardReceivablesLockCercRepository
import com.finapp.resources.repositories.CardReceivablesLockNucleaRepository
import com.finapp.resources.repositories.CardReceivablesLockRepository
import com.finapp.resources.repositories.RepositoryOperations
import com.finapp.web.mappers.CardReceivablesLockEntityMapper
import com.finapp.web.mappers.CardReceivablesLockMapper
import com.finapp.web.requests.CardReceivablesLockDto
import org.springframework.stereotype.Component

@Component
class CardReceivablesLockDataAccessImpl(
        private val cardReceivablesLockRepository: CardReceivablesLockRepository,
        private val cardReceivablesLockNucleaRepository: CardReceivablesLockNucleaRepository,
        private val cardReceivablesLockCercRepository: CardReceivablesLockCercRepository
) : CardReceivablesLockDataAccess {

  override fun create(cardReceivablesLockDto: CardReceivablesLockDto): CardReceivablesLockDto {
    val cardReceivablesLock = CardReceivablesLockMapper.map(cardReceivablesLockDto)
    val savedCardReceivablesLock = cardReceivablesLockRepository.save(cardReceivablesLock)
    return CardReceivablesLockEntityMapper.map(savedCardReceivablesLock)
  }

  override fun update(cardReceivablesLockDto: CardReceivablesLockDto): CardReceivablesLockDto {
    val cardReceivablesLock = CardReceivablesLockMapper.map(cardReceivablesLockDto)
    val updatedCardReceivablesLock = cardReceivablesLockRepository.save(cardReceivablesLock)
    return CardReceivablesLockEntityMapper.map(updatedCardReceivablesLock)
  }

  override fun getById(id: String): CardReceivablesLockDto? {
    val cardReceivablesLock = cardReceivablesLockRepository.findById(id).orElse(null)
    return cardReceivablesLock?.let { CardReceivablesLockEntityMapper.map(it) }
  }

  override fun getByIdWithRelationships(id: String): CardReceivablesLockDto? {
    val cardReceivablesLock = cardReceivablesLockRepository.findByIdWithRelationships(id)
    return cardReceivablesLock?.let { CardReceivablesLockEntityMapper.map(it) }
  }

  override fun getByContractNumber(contractNumber: String): CardReceivablesLockDto {
    val cardReceivablesLock = cardReceivablesLockRepository.findByContractNumber(contractNumber)
    return CardReceivablesLockEntityMapper.map(cardReceivablesLock)
  }

  override fun getByStatus(status: CardReceivablesLockStatus): List<CardReceivablesLockDto> {
    val cardReceivablesLocks = cardReceivablesLockRepository.findByStatus(status)
    return cardReceivablesLocks.map { CardReceivablesLockEntityMapper.map(it) }
  }

  override fun getByContractNumberAndStatus(
          contractNumber: String,
          status: CardReceivablesLockStatus
  ): CardReceivablesLockDto? {
    val cardReceivablesLock =
            cardReceivablesLockRepository.findByContractNumberAndStatus(contractNumber, status)
    return cardReceivablesLock?.let { CardReceivablesLockEntityMapper.map(it) }
  }

  override fun getByHubGuaranteeIdAndStatus(
          hubGuaranteeId: String,
          status: CardReceivablesLockStatus
  ): List<CardReceivablesLockDto> {
    val cardReceivablesLocks =
            cardReceivablesLockRepository.findByHubGuaranteeIdAndStatus(hubGuaranteeId, status)
    return cardReceivablesLocks.map { CardReceivablesLockEntityMapper.map(it) }
  }

  override fun incrementCreationRetryAttempts(id: String, registerType: RegisterType) {
    executeRepositoryOperation(registerType) { repository ->
      repository.incrementCreationRetryAttempts(id)
    }
  }

  override fun incrementProactiveSearchAttempts(id: String, registerType: RegisterType) {
    executeRepositoryOperation(registerType) { repository ->
      repository.incrementProactiveSearchAttempts(id)
    }
  }

  private fun executeRepositoryOperation(
          registerType: RegisterType,
          operation: (RepositoryOperations) -> Unit
  ) {
    when (registerType) {
      RegisterType.CERC -> operation(cardReceivablesLockCercRepository)
      RegisterType.NUCLEA -> operation(cardReceivablesLockNucleaRepository)
    }
  }
}
