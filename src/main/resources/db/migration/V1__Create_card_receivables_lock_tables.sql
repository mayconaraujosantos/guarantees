-- Tabela principal de trava de recebíveis
CREATE TABLE card_receivables_lock (
    id VARCHAR(26) NOT NULL,
    hub_guarantee_id VARCHAR(26) NOT NULL,
    contract_number VARCHAR(50) NOT NULL,
    contract_source VARCHAR(50) NOT NULL,
    ipoc VARCHAR(50) NOT NULL,
    register VARCHAR(20) NOT NULL,
    owner_person_id VARCHAR(26) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    recalculation_frequency VARCHAR(20) NOT NULL,
    consider_balance_on_insufficiency BOOLEAN NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

-- Tabela de titulares
CREATE TABLE card_receivables_holder (
    id VARCHAR(26) NOT NULL,
    card_receivables_lock_id VARCHAR(26) NOT NULL,
    tax_id VARCHAR(14) NOT NULL,
    root_tax_id_operation VARCHAR(10) NOT NULL,
    payment_account_branch VARCHAR(10),
    payment_account_number VARCHAR(20),
    payment_account_id VARCHAR(26),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (card_receivables_lock_id) REFERENCES card_receivables_lock (id)
);

-- Tabela de arranjos do titular
CREATE TABLE card_receivables_owner_arrangement (
    id VARCHAR(26) NOT NULL,
    card_receivables_lock_holder_id VARCHAR(26) NOT NULL,
    arrangement_code VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (
        card_receivables_lock_holder_id
    ) REFERENCES card_receivables_holder (id)
);

-- Tabela de credenciadores do titular
CREATE TABLE card_receivables_owner_accreditor (
    id VARCHAR(26) NOT NULL,
    card_receivables_lock_holder_id VARCHAR(26) NOT NULL,
    accreditor_tax_id VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (
        card_receivables_lock_holder_id
    ) REFERENCES card_receivables_holder (id)
);

-- Tabela de entrada Nuclea (1:1)
CREATE TABLE card_receivables_lock_nuclea (
    id VARCHAR(26) NOT NULL,
    card_receivables_lock_id VARCHAR(26) NOT NULL,
    protocol VARCHAR(50),
    creation_retry_attempts INTEGER NOT NULL DEFAULT 0,
    proactive_search_attempts INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (card_receivables_lock_id) REFERENCES card_receivables_lock (id),
    UNIQUE (card_receivables_lock_id)
);

-- Tabela de entrada Cerc (1:1)
CREATE TABLE card_receivables_lock_cerc (
    id VARCHAR(26) NOT NULL,
    card_receivables_lock_id VARCHAR(26) NOT NULL,
    protocol VARCHAR(50),
    creation_retry_attempts INTEGER NOT NULL DEFAULT 0,
    proactive_search_attempts INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (card_receivables_lock_id) REFERENCES card_receivables_lock (id),
    UNIQUE (card_receivables_lock_id)
);

-- Tabela de protocolos Cerc (1:N)
CREATE TABLE card_receivables_lock_cerc_protocols (
    id VARCHAR(26) NOT NULL,
    card_receivables_lock_cerc_id VARCHAR(26) NOT NULL,
    action VARCHAR(50) NOT NULL,
    protocol VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (card_receivables_lock_cerc_id) REFERENCES card_receivables_lock_cerc (id)
);

-- Tabela de parcelas do contrato
CREATE TABLE card_receivables_contract_installment (
    id VARCHAR(26) NOT NULL,
    card_receivables_lock_id VARCHAR(26) NOT NULL,
    installment_number INTEGER NOT NULL,
    due_date DATE NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (card_receivables_lock_id) REFERENCES card_receivables_lock (id)
);

-- Tabela de agendamentos
CREATE TABLE card_receivables_schedules (
    id VARCHAR(26) NOT NULL,
    person_id VARCHAR(26) NOT NULL,
    register VARCHAR(50),
    arrangement VARCHAR(50),
    accreditor VARCHAR(50),
    source VARCHAR(50),
    schedules TEXT,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

-- Índices para melhorar performance
CREATE INDEX idx_card_receivables_lock_hub_guarantee_id ON card_receivables_lock (hub_guarantee_id);

CREATE INDEX idx_card_receivables_lock_contract_number ON card_receivables_lock (contract_number);

CREATE INDEX idx_card_receivables_lock_status ON card_receivables_lock (status);

CREATE INDEX idx_card_receivables_lock_cerc_protocols_cerc_id ON card_receivables_lock_cerc_protocols (card_receivables_lock_cerc_id);

CREATE INDEX idx_card_receivables_owner_arrangement_holder_id ON card_receivables_owner_arrangement (
    card_receivables_lock_holder_id
);

CREATE INDEX idx_card_receivables_owner_accreditor_holder_id ON card_receivables_owner_accreditor (
    card_receivables_lock_holder_id
);

CREATE INDEX idx_card_receivables_schedules_person_id ON card_receivables_schedules (person_id);