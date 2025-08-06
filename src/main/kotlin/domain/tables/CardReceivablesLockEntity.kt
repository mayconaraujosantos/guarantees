package com.finapp.domain.tables

import com.finapp.domain.enums.CardReceivablesLockStatus
import com.finapp.domain.enums.RecalculationPeriodIndicator
import com.finapp.domain.enums.RegisterType
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "card_receivables_lock")
data class CardReceivablesLockEntity(
        @Id val id: String,
        val hubGuaranteeId: String,
        val contractNumber: String,
        val contractSource: String,
        val ipoc: String,
        @Enumerated(EnumType.STRING) val register: RegisterType,
        val ownerPersonId: String,
        val amount: java.math.BigDecimal,
        @Enumerated(EnumType.STRING) val recalculationFrequency: RecalculationPeriodIndicator,
        val considerBalanceOnInsufficiency: Boolean,
        val startDate: LocalDate,
        val endDate: LocalDate,
        @Enumerated(EnumType.STRING) var status: CardReceivablesLockStatus,
        var createdAt: LocalDateTime,
        var updatedAt: LocalDateTime,
        @OneToMany(
                mappedBy = "cardReceivablesLock",
                cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY
        )
        var holders: List<CardReceivablesHolderEntity>,
        @OneToOne(mappedBy = "cardReceivablesLock", cascade = [CascadeType.ALL])
        var nucleaEntry: CardReceivablesLockNucleaEntity?,
        @OneToOne(mappedBy = "cardReceivablesLock", cascade = [CascadeType.ALL])
        var cercEntry: CardReceivablesLockCercEntity?,
        @OneToMany(
                mappedBy = "cardReceivablesLock",
                cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY
        )
        var contractInstallments: List<CardReceivablesContractInstallmentEntity>
) {

  // Helper methods for bidirectional relationships
  fun addHolder(holder: CardReceivablesHolderEntity) {
    holders = holders + holder
    holder.cardReceivablesLock = this
  }

  fun removeHolder(holder: CardReceivablesHolderEntity) {
    holders = holders - holder
    holder.cardReceivablesLock = null
  }

  fun addContractInstallment(installment: CardReceivablesContractInstallmentEntity) {
    contractInstallments = contractInstallments + installment
    installment.cardReceivablesLock = this
  }

  fun removeContractInstallment(installment: CardReceivablesContractInstallmentEntity) {
    contractInstallments = contractInstallments - installment
    installment.cardReceivablesLock = null
  }

  // Helper methods for OneToOne relationships
  fun assignNucleaEntry(nuclea: CardReceivablesLockNucleaEntity?) {
    // Clear previous bidirectional relationship
    this.nucleaEntry?.cardReceivablesLock = null
    this.nucleaEntry = nuclea
    nuclea?.cardReceivablesLock = this
  }

  fun assignCercEntry(cerc: CardReceivablesLockCercEntity) {
    this.cercEntry = cerc
    cerc.cardReceivablesLock = this
  }

  fun requireCercEntry(): CardReceivablesLockCercEntity {
    return cercEntry ?: throw IllegalStateException("CercEntry is required but not set")
  }

  // Helper methods for status control (Soft Delete)
  fun activate() {
    this.status = CardReceivablesLockStatus.ACTIVE
    this.updatedAt = LocalDateTime.now()
  }

  fun deactivate() {
    this.status = CardReceivablesLockStatus.INACTIVE
    this.updatedAt = LocalDateTime.now()
  }

  fun isActive(): Boolean = this.status == CardReceivablesLockStatus.ACTIVE

  fun isInactive(): Boolean = this.status == CardReceivablesLockStatus.INACTIVE
}
