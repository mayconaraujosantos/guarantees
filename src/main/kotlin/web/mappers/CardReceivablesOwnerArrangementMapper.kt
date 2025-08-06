package com.finapp.web.mappers

import com.finapp.domain.tables.CardReceivablesOwnerArrangementEntity
import com.finapp.web.requests.CardReceivablesOwnerArrangementDto
import tech.mappie.api.ObjectMappie

object CardReceivablesOwnerArrangementMapper :
        ObjectMappie<CardReceivablesOwnerArrangementDto, CardReceivablesOwnerArrangementEntity>()

object CardReceivablesOwnerArrangementEntityMapper :
        ObjectMappie<CardReceivablesOwnerArrangementEntity, CardReceivablesOwnerArrangementDto>()
