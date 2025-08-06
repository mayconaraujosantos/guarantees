# Finapp Guarantee Card Receivables Service

Serviço para gerenciamento de recebíveis de cartão de garantia da Finapp.

## 📋 Histórico de Commits

### 🏗️ Configuração Inicial do Projeto

#### `ad11eb8` - chore: add project configuration files

- Adicionados arquivos de configuração do projeto
- Configuração do Gradle, Docker Compose e propriedades do projeto

#### `584c336` - feat: add Spring Boot application and configuration

- Criada aplicação Spring Boot principal
- Configuração de recursos e propriedades da aplicação

#### `baec18d` - feat: add domain enums and existing entities

- Adicionados enums do domínio (`CardReceivablesLockStatus`, `RegisterType`)
- Entidades de domínio existentes

#### `a0321bd` - feat: add existing table entities

- Entidades de tabela existentes com anotações JPA
- `CardReceivablesLockEntity`, `CardReceivablesLockCercEntity`, `CardReceivablesHolderEntity`

#### `c989560` - fix: update Detekt version for Kotlin compatibility

- Atualizada versão do Detekt de `1.23.4` para `1.24.0`
- Corrigida incompatibilidade com Kotlin 2.1.10

### 🔧 Correções e Melhorias nas Entidades

#### `c94e420` - feat: add missing table entities for card receivables lock

- Criadas entidades de tabela faltantes:
  - `CardReceivablesLockCercProtocolEntity`
  - `CardReceivablesOwnerArrangementEntity`
  - `CardReceivablesOwnerAccreditorEntity`
  - `CardReceivablesSchedulesEntity`

#### `8185aea` - fix: correct domain entities by removing JPA annotations

- Removidas anotações JPA das entidades de domínio
- Separada responsabilidade entre domínio e tabelas
- Entidades de domínio agora são classes de dados puras

#### `8ab7343` - feat: update existing entities with new relationships

- Adicionados relacionamentos `@OneToMany` nas entidades existentes
- `CardReceivablesLockCercEntity` com lista de protocolos
- `CardReceivablesHolderEntity` com listas de arranjos e credenciadores

### 📊 Camada de Dados

#### `063833c` - feat: update database migration with all required tables

- Migração única consolidada (`V1__Create_card_receivables_lock_tables.sql`)
- Criação de todas as tabelas necessárias
- Adição de colunas faltantes em `card_receivables_holder`
- Remoção de colunas desnecessárias

#### `552eaff` - fix: update TestDataFactory to support new entity relationships

- Atualizado `TestDataFactory` para inicializar listas vazias
- Corrigida inicialização de relacionamentos `@OneToMany`

### 🎯 Camada Web (DTOs e Mappers)

#### `33ad0a2` - feat: add DTOs for new entities

- Criados DTOs para todas as novas entidades:
  - `CardReceivablesLockCercProtocolDto`
  - `CardReceivablesOwnerArrangementDto`
  - `CardReceivablesOwnerAccreditorDto`
  - `CardReceivablesSchedulesDto`

#### `b6981fe` - feat: add mappers for new entities

- Criados mappers usando Mappie para todas as novas entidades
- Mapeamento bidirecional entre DTOs e entidades

#### `4c31679` - feat: add existing DTOs

- DTOs existentes para entidades principais

#### `6971250` - feat: add existing mappers

- Mappers existentes para entidades principais

### 🧪 Testes

#### `96904d6` - test: add comprehensive test suite

- Suíte completa de testes
- Testes de domínio, data access e recursos

### 🔌 Camada de Acesso a Dados

#### `fb12d15` - feat: add data access layer and repositories

- Camada de acesso a dados
- Repositórios JPA para todas as entidades

#### `6b14051` - feat: add data access interfaces

- Interfaces de data access:
  - `CardReceivablesLockDataAccess`
  - `CardReceivablesLockCercDataAccess`
  - `CardReceivablesLockNucleaDataAccess`

### 📚 Documentação

#### `78f8d8f` - docs: add data dictionary documentation

- Documentação do dicionário de dados
- Especificações técnicas das entidades

### 🛠️ Configuração Final

#### `eaeea88` - chore: add .gitignore file

- Arquivo `.gitignore` completo
- Exclusão de arquivos de cache e configurações locais
- Inclusão de arquivos importantes como `gradle-wrapper.jar`

## 🏗️ Arquitetura

### Camadas do Projeto

1. **Domain Layer**

   - Entidades de domínio (sem JPA)
   - Enums de negócio
   - Interfaces de data access

2. **Tables Layer**

   - Entidades de tabela (com JPA)
   - Mapeamento objeto-relacional

3. **Data Access Layer**

   - Implementações dos repositórios
   - Interfaces de data access

4. **Web Layer**
   - DTOs para transferência de dados
   - Mappers para conversão entre camadas

## 🛠️ Tecnologias Utilizadas

- **Kotlin** - Linguagem de programação
- **Spring Boot** - Framework da aplicação
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM
- **PostgreSQL** - Banco de dados principal
- **H2** - Banco de dados para testes
- **Flyway** - Migração de banco de dados
- **Mappie** - Mapeamento de objetos
- **Detekt** - Análise estática de código
- **JUnit 5** - Framework de testes
- **MockK** - Biblioteca de mocks

## 📁 Estrutura do Projeto

```
src/main/kotlin/
├── domain/
│   ├── entities/          # Entidades de domínio (sem JPA)
│   ├── tables/           # Entidades de tabela (com JPA)
│   ├── enums/            # Enums de negócio
│   └── dataaccess/       # Interfaces de data access
├── web/
│   ├── requests/         # DTOs
│   └── mappers/          # Mappers
└── dataaccess/           # Implementações dos repositórios

src/test/kotlin/
├── domain/              # Testes de domínio
├── dataaccess/          # Testes de data access
└── utils/               # Utilitários de teste

src/main/resources/
└── db/migration/        # Migrações do Flyway
```

## 🚀 Como Executar

1. **Clone o repositório**

   ```bash
   git clone <repository-url>
   cd finapp-guarantee-card-receivables-service
   ```

2. **Execute com Docker**

   ```bash
   docker-compose up -d
   ```

3. **Execute a aplicação**

   ```bash
   ./gradlew bootRun
   ```

4. **Execute os testes**
   ```bash
   ./gradlew test
   ```

## 📝 Convenções de Commit

O projeto segue as convenções do **Conventional Commits**:

- `feat:` - Novas funcionalidades
- `fix:` - Correções de bugs
- `docs:` - Documentação
- `style:` - Formatação de código
- `refactor:` - Refatoração de código
- `test:` - Adição ou correção de testes
- `chore:` - Tarefas de manutenção

## 🔍 Análise de Código

```bash
# Executar Detekt
./gradlew detekt

# Executar testes
./gradlew test

# Build do projeto
./gradlew build
```
