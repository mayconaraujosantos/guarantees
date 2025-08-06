# Dicionário de Dados - Sistema de Travas de Recebíveis

## Visão Geral

Este documento descreve o dicionário de dados para o sistema de travas de recebíveis de cartão, incluindo todas as tabelas, campos, tipos de dados e relacionamentos conforme o DER fornecido.

## Tabelas

### 1. card_receivables_lock

Tabela principal que armazena as informações das travas de recebíveis.

| Campo                             | Tipo      | Tamanho | Nullable | Descrição                                                       |
| --------------------------------- | --------- | ------- | -------- | --------------------------------------------------------------- |
| id                                | VARCHAR   | 26      | NOT NULL | Identificador único da trava                                    |
| hub_guarantee_id                  | VARCHAR   | 50      | NOT NULL | ID da garantia do hub                                           |
| contract_number                   | VARCHAR   | 50      | NOT NULL | Número do contrato                                              |
| contract_source                   | VARCHAR   | 50      | NULL     | Origem do contrato                                              |
| ipoc                              | VARCHAR   | 50      | NULL     | Código IPOC                                                     |
| register                          | VARCHAR   | 50      | NULL     | Registro                                                        |
| owner_person_id                   | VARCHAR   | 26      | NULL     | ID da pessoa proprietária                                       |
| amount                            | DECIMAL   | 19,2    | NOT NULL | Valor monetário do recebível                                    |
| recalculation_frequency           | VARCHAR   | 50      | NULL     | Frequência de recálculo                                         |
| consider_balance_on_insufficiency | BOOLEAN   | -       | NOT NULL | Considerar saldo em caso de insuficiência                       |
| start_date                        | TIMESTAMP | -       | NOT NULL | Data de início da trava                                         |
| end_date                          | TIMESTAMP | -       | NULL     | Data de fim da trava                                            |
| status                            | VARCHAR   | 20      | NOT NULL | Status da trava (ACTIVE, INACTIVE, PENDING, CANCELLED, EXPIRED) |
| created_at                        | TIMESTAMP | -       | NOT NULL | Data de criação do registro                                     |
| updated_at                        | TIMESTAMP | -       | NOT NULL | Data de última atualização                                      |

### 2. card_receivables_holder

Tabela que armazena os titulares das travas de recebíveis.

| Campo                    | Tipo      | Tamanho | Nullable | Descrição                      |
| ------------------------ | --------- | ------- | -------- | ------------------------------ |
| id                       | VARCHAR   | 26      | NOT NULL | Identificador único do titular |
| card_receivables_lock_id | VARCHAR   | 26      | NOT NULL | FK para card_receivables_lock  |
| tax_id                   | VARCHAR   | 20      | NOT NULL | CPF/CNPJ do titular            |
| root_tax_id_operation    | BOOLEAN   | -       | NOT NULL | Operação de CPF/CNPJ raiz      |
| payment_account_branch   | VARCHAR   | 10      | NULL     | Agência da conta de pagamento  |
| payment_account_number   | VARCHAR   | 20      | NULL     | Número da conta de pagamento   |
| payment_account_id       | VARCHAR   | 26      | NULL     | ID da conta de pagamento       |
| created_at               | TIMESTAMP | -       | NOT NULL | Data de criação do registro    |
| updated_at               | TIMESTAMP | -       | NOT NULL | Data de última atualização     |

### 3. card_receivables_owner_arrangement

Tabela que armazena os arranjos dos proprietários.

| Campo                           | Tipo      | Tamanho | Nullable | Descrição                       |
| ------------------------------- | --------- | ------- | -------- | ------------------------------- |
| id                              | VARCHAR   | 26      | NOT NULL | Identificador único do arranjo  |
| card_receivables_lock_holder_id | VARCHAR   | 26      | NOT NULL | FK para card_receivables_holder |
| arrangement_code                | VARCHAR   | 50      | NOT NULL | Código do arranjo               |
| created_at                      | TIMESTAMP | -       | NOT NULL | Data de criação do registro     |
| updated_at                      | TIMESTAMP | -       | NOT NULL | Data de última atualização      |

### 4. card_receivables_owner_accreditor

Tabela que armazena os credenciadores dos proprietários.

| Campo                           | Tipo      | Tamanho | Nullable | Descrição                           |
| ------------------------------- | --------- | ------- | -------- | ----------------------------------- |
| id                              | VARCHAR   | 26      | NOT NULL | Identificador único do credenciador |
| card_receivables_lock_holder_id | VARCHAR   | 26      | NOT NULL | FK para card_receivables_holder     |
| accreditor_tax_id               | VARCHAR   | 20      | NOT NULL | CPF/CNPJ do credenciador            |
| created_at                      | TIMESTAMP | -       | NOT NULL | Data de criação do registro         |
| updated_at                      | TIMESTAMP | -       | NOT NULL | Data de última atualização          |

### 5. card_receivables_lock_nuclea

Tabela que armazena as informações de processamento Nuclea.

| Campo                     | Tipo      | Tamanho | Nullable | Descrição                              |
| ------------------------- | --------- | ------- | -------- | -------------------------------------- |
| id                        | VARCHAR   | 26      | NOT NULL | Identificador único do registro Nuclea |
| card_receivables_lock_id  | VARCHAR   | 26      | NOT NULL | FK para card_receivables_lock          |
| creation_retry_attempts   | INTEGER   | -       | NOT NULL | Número de tentativas de criação        |
| proactive_search_attempts | INTEGER   | -       | NOT NULL | Número de tentativas de busca proativa |
| protocol                  | VARCHAR   | 100     | NULL     | Protocolo do processamento             |
| created_at                | TIMESTAMP | -       | NOT NULL | Data de criação do registro            |
| updated_at                | TIMESTAMP | -       | NOT NULL | Data de última atualização             |

### 6. card_receivables_lock_cerc

Tabela que armazena as informações de processamento CERC.

| Campo                     | Tipo      | Tamanho | Nullable | Descrição                              |
| ------------------------- | --------- | ------- | -------- | -------------------------------------- |
| id                        | VARCHAR   | 26      | NOT NULL | Identificador único do registro CERC   |
| card_receivables_lock_id  | VARCHAR   | 26      | NOT NULL | FK para card_receivables_lock          |
| creation_retry_attempts   | INTEGER   | -       | NOT NULL | Número de tentativas de criação        |
| proactive_search_attempts | INTEGER   | -       | NOT NULL | Número de tentativas de busca proativa |
| created_at                | TIMESTAMP | -       | NOT NULL | Data de criação do registro            |
| updated_at                | TIMESTAMP | -       | NOT NULL | Data de última atualização             |

### 7. card_receivables_lock_cerc_protocols

Tabela que armazena os protocolos do CERC.

| Campo                         | Tipo      | Tamanho | Nullable | Descrição                          |
| ----------------------------- | --------- | ------- | -------- | ---------------------------------- |
| id                            | VARCHAR   | 26      | NOT NULL | Identificador único do protocolo   |
| card_receivables_lock_cerc_id | VARCHAR   | 26      | NOT NULL | FK para card_receivables_lock_cerc |
| action                        | VARCHAR   | 50      | NOT NULL | Ação realizada                     |
| protocol                      | VARCHAR   | 100     | NOT NULL | Detalhes do protocolo              |
| created_at                    | TIMESTAMP | -       | NOT NULL | Data de criação do registro        |

### 8. card_receivables_contract_installments

Tabela que armazena as parcelas dos contratos.

| Campo                    | Tipo      | Tamanho | Nullable | Descrição                      |
| ------------------------ | --------- | ------- | -------- | ------------------------------ |
| id                       | VARCHAR   | 26      | NOT NULL | Identificador único da parcela |
| card_receivables_lock_id | VARCHAR   | 26      | NOT NULL | FK para card_receivables_lock  |
| installment_number       | INTEGER   | -       | NOT NULL | Número da parcela              |
| date                     | DATE      | -       | NOT NULL | Data da parcela                |
| value                    | DECIMAL   | 19,2    | NOT NULL | Valor da parcela               |
| created_at               | TIMESTAMP | -       | NOT NULL | Data de criação do registro    |

### 9. card_receivables_schedules

Tabela que armazena os agendamentos de recebíveis.

| Campo       | Tipo      | Tamanho | Nullable | Descrição                          |
| ----------- | --------- | ------- | -------- | ---------------------------------- |
| id          | VARCHAR   | 26      | NOT NULL | Identificador único do agendamento |
| person_id   | VARCHAR   | 26      | NOT NULL | ID da pessoa                       |
| register    | VARCHAR   | 50      | NULL     | Registro                           |
| arrangement | VARCHAR   | 50      | NULL     | Arranjo                            |
| accreditor  | VARCHAR   | 50      | NULL     | Credenciador                       |
| source      | VARCHAR   | 50      | NULL     | Origem                             |
| schedules   | TEXT      | -       | NULL     | Dados dos agendamentos (JSON)      |
| created_at  | TIMESTAMP | -       | NOT NULL | Data de criação do registro        |

## Relacionamentos

### Relacionamentos Principais:

1. **card_receivables_lock** → **card_receivables_holder** (1:N)

   - Uma trava pode ter múltiplos titulares

2. **card_receivables_holder** → **card_receivables_owner_arrangement** (1:N)

   - Um titular pode ter múltiplos arranjos

3. **card_receivables_holder** → **card_receivables_owner_accreditor** (1:N)

   - Um titular pode ter múltiplos credenciadores

4. **card_receivables_lock** → **card_receivables_lock_nuclea** (1:N)

   - Uma trava pode ter múltiplos registros Nuclea

5. **card_receivables_lock** → **card_receivables_lock_cerc** (1:N)

   - Uma trava pode ter múltiplos registros CERC

6. **card_receivables_lock_cerc** → **card_receivables_lock_cerc_protocols** (1:N)

   - Um registro CERC pode ter múltiplos protocolos

7. **card_receivables_lock** → **card_receivables_contract_installments** (1:N)
   - Uma trava pode ter múltiplas parcelas de contrato

## Observações Importantes

1. **IDs**: Todos os campos de ID utilizam VARCHAR(26) conforme padrão do projeto
2. **Timestamps**: Campos de data/hora utilizam TIMESTAMP para precisão
3. **Valores Monetários**: Utilizam DECIMAL(19,2) para precisão decimal
4. **Múltiplos Titulares**: O sistema suporta múltiplos titulares por trava conforme nota do DER
5. **Status**: Enum com valores predefinidos para controle de estado
6. **Tentativas**: Contadores para controle de retry em processamentos Nuclea e CERC

## Métodos de DataAccess Implementados

### CardReceivablesLockDataAccess:

- `create()` - Criar nova trava
- `update()` - Atualizar trava existente
- `getById()` - Buscar por ID

### CardReceivablesLockNucleaDataAccess:

- `create()` - Criar registro Nuclea
- `update()` - Atualizar registro Nuclea
- `getById()` - Buscar por ID
- `incrementCreationRetryAttempts()` - Incrementar tentativas de criação
- `incrementProactiveSearchAttempts()` - Incrementar tentativas de busca proativa

### CardReceivablesLockCercDataAccess:

- `create()` - Criar registro CERC
- `update()` - Atualizar registro CERC
- `getById()` - Buscar por ID
- `incrementCreationRetryAttempts()` - Incrementar tentativas de criação
- `incrementProactiveSearchAttempts()` - Incrementar tentativas de busca proativa

## Índices Criados

### card_receivables_lock:

- `idx_card_receivables_lock_contract_number` - Busca por número do contrato
- `idx_card_receivables_lock_hub_guarantee_id` - Busca por ID da garantia
- `idx_card_receivables_lock_status` - Busca por status
- `idx_card_receivables_lock_start_date` - Busca por data de início
- `idx_card_receivables_lock_end_date` - Busca por data de fim

### card_receivables_holder:

- `idx_card_receivables_holder_lock_id` - Busca por ID da trava
- `idx_card_receivables_holder_tax_id` - Busca por CPF/CNPJ

### card_receivables_lock_nuclea:

- `idx_card_receivables_lock_nuclea_lock_id` - Busca por ID da trava
- `idx_card_receivables_lock_nuclea_protocol` - Busca por protocolo

### card_receivables_lock_cerc:

- `idx_card_receivables_lock_cerc_lock_id` - Busca por ID da trava

### card_receivables_contract_installments:

- `idx_card_receivables_contract_installments_lock_id` - Busca por ID da trava
- `idx_card_receivables_contract_installments_date` - Busca por data da parcela
