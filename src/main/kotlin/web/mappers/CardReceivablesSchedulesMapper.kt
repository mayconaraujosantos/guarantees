package com.finapp.web.mappers

import com.finapp.domain.tables.CardReceivablesSchedulesEntity
import com.finapp.web.requests.CardReceivablesSchedulesDto
import tech.mappie.api.ObjectMappie

object CardReceivablesSchedulesMapper :
        ObjectMappie<CardReceivablesSchedulesDto, CardReceivablesSchedulesEntity>()

object CardReceivablesSchedulesEntityMapper :
        ObjectMappie<CardReceivablesSchedulesEntity, CardReceivablesSchedulesDto>()
