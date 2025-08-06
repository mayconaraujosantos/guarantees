package com.finapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan("com.finapp.domain.*")
@EnableJpaRepositories("com.finapp.resources.repositories")
class FinappGuaranteeCardReceivablesServiceApplication

fun main(args: Array<String>) {
  runApplication<FinappGuaranteeCardReceivablesServiceApplication>(*args)
}
