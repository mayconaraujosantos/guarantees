package com.finapp.web.mappers

import com.finapp.domain.tables.CardReceivablesLockEntity
import com.finapp.web.requests.CardReceivablesLockDto
import tech.mappie.api.ObjectMappie

object CardReceivablesLockMapper :
        ObjectMappie<CardReceivablesLockDto, CardReceivablesLockEntity>()

object CardReceivablesLockEntityMapper :
        ObjectMappie<CardReceivablesLockEntity, CardReceivablesLockDto>()
