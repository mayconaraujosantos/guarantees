package com.finapp.web.mappers

import com.finapp.domain.tables.CardReceivablesOwnerAccreditorEntity
import com.finapp.web.requests.CardReceivablesOwnerAccreditorDto
import tech.mappie.api.ObjectMappie

object CardReceivablesOwnerAccreditorMapper :
        ObjectMappie<CardReceivablesOwnerAccreditorDto, CardReceivablesOwnerAccreditorEntity>()

object CardReceivablesOwnerAccreditorEntityMapper :
        ObjectMappie<CardReceivablesOwnerAccreditorEntity, CardReceivablesOwnerAccreditorDto>()
