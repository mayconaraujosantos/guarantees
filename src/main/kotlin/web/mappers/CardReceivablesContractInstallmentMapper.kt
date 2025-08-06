package com.finapp.web.mappers

import com.finapp.domain.tables.CardReceivablesContractInstallmentEntity
import com.finapp.web.requests.CardReceivablesContractInstallmentDto
import tech.mappie.api.ObjectMappie

object CardReceivablesContractInstallmentMapper :
        ObjectMappie<CardReceivablesContractInstallmentDto, CardReceivablesContractInstallmentEntity>()

object CardReceivablesContractInstallmentEntityMapper :
        ObjectMappie<CardReceivablesContractInstallmentEntity, CardReceivablesContractInstallmentDto>()