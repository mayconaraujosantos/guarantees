package com.finapp.web.mappers

import com.finapp.domain.tables.CardReceivablesLockNucleaEntity
import com.finapp.web.requests.CardReceivablesLockNucleaDto
import tech.mappie.api.ObjectMappie

object CardReceivablesLockNucleaMapper :
        ObjectMappie<CardReceivablesLockNucleaDto, CardReceivablesLockNucleaEntity>()

object CardReceivablesLockNucleaEntityMapper :
        ObjectMappie<CardReceivablesLockNucleaEntity, CardReceivablesLockNucleaDto>()
