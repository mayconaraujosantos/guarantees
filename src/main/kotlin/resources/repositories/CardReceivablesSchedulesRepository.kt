package com.finapp.resources.repositories

import com.finapp.domain.entities.CardReceivablesSchedules
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface CardReceivablesSchedulesRepository  {

  fun findByPersonId(personId: String): List<CardReceivablesSchedules>

  fun findByRegister(register: String): List<CardReceivablesSchedules>

  fun findByArrangement(arrangement: String): List<CardReceivablesSchedules>

  fun findByAccreditor(accreditor: String): List<CardReceivablesSchedules>

  fun findBySource(source: String): List<CardReceivablesSchedules>
}
