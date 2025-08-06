package com.finapp.web.mappers

import com.finapp.domain.tables.CardReceivablesLockCercEntity
import com.finapp.web.requests.CardReceivablesLockCercDto
import tech.mappie.api.ObjectMappie

object CardReceivablesLockCercMapper :
        ObjectMappie<CardReceivablesLockCercDto, CardReceivablesLockCercEntity>()

object CardReceivablesLockCercEntityMapper :
        ObjectMappie<CardReceivablesLockCercEntity, CardReceivablesLockCercDto>()