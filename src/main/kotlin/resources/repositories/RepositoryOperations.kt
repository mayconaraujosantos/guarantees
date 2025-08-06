package com.finapp.resources.repositories

interface RepositoryOperations {
  fun incrementCreationRetryAttempts(id: String)
  fun incrementProactiveSearchAttempts(id: String)
}
