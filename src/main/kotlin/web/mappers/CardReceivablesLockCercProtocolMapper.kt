package com.finapp.web.mappers

import com.finapp.domain.tables.CardReceivablesLockCercProtocolEntity
import com.finapp.web.requests.CardReceivablesLockCercProtocolDto
import tech.mappie.api.ObjectMappie

object CardReceivablesLockCercProtocolMapper :
        ObjectMappie<CardReceivablesLockCercProtocolDto, CardReceivablesLockCercProtocolEntity>()

object CardReceivablesLockCercProtocolEntityMapper :
        ObjectMappie<CardReceivablesLockCercProtocolEntity, CardReceivablesLockCercProtocolDto>()
