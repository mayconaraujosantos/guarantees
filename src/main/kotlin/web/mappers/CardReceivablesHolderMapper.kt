package com.finapp.web.mappers

import com.finapp.domain.tables.CardReceivablesHolderEntity
import com.finapp.web.requests.CardReceivablesHolderDto
import tech.mappie.api.ObjectMappie

object CardReceivablesHolderMapper :
        ObjectMappie<CardReceivablesHolderDto, CardReceivablesHolderEntity>()

object CardReceivablesHolderEntityMapper :
        ObjectMappie<CardReceivablesHolderEntity, CardReceivablesHolderDto>()