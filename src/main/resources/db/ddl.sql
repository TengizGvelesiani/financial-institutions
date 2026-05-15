CREATE DATABASE IF NOT EXISTS financial_institutions
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE financial_institutions;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS loans;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS passports;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS branches;
DROP TABLE IF EXISTS financial_institutions;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE financial_institutions (
    id               BIGINT       NOT NULL AUTO_INCREMENT,
    name             VARCHAR(255) NOT NULL,
    license_number   DECIMAL(20, 2) NULL,
    active           TINYINT(1)   NULL,
    established_date DATE         NULL,
    created_at       DATETIME     NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE branches (
    id                        BIGINT       NOT NULL AUTO_INCREMENT,
    financial_institution_id  BIGINT       NOT NULL,
    name                      VARCHAR(255) NULL,
    address                   VARCHAR(500) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_branches_financial_institution
        FOREIGN KEY (financial_institution_id)
            REFERENCES financial_institutions (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    INDEX idx_branches_financial_institution_id (financial_institution_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE employees (
    id                        BIGINT         NOT NULL AUTO_INCREMENT,
    financial_institution_id  BIGINT         NOT NULL,
    full_name                 VARCHAR(255)   NULL,
    hire_date                 DATE           NULL,
    salary                    DECIMAL(15, 2) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_employees_financial_institution
        FOREIGN KEY (financial_institution_id)
            REFERENCES financial_institutions (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    INDEX idx_employees_financial_institution_id (financial_institution_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE customers (
    id                        BIGINT       NOT NULL AUTO_INCREMENT,
    financial_institution_id  BIGINT       NOT NULL,
    full_name                 VARCHAR(255) NULL,
    birth_date                DATE         NULL,
    registered_at             DATETIME     NULL,
    active                    TINYINT(1)   NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_customers_financial_institution
        FOREIGN KEY (financial_institution_id)
            REFERENCES financial_institutions (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    INDEX idx_customers_financial_institution_id (financial_institution_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE passports (
    id              BIGINT      NOT NULL AUTO_INCREMENT,
    employee_id     BIGINT      NULL,
    customer_id     BIGINT      NULL,
    passport_number VARCHAR(50) NULL,
    issue_date      DATE        NULL,
    expiry_date     DATE        NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_passports_employee
        FOREIGN KEY (employee_id)
            REFERENCES employees (id)
            ON DELETE SET NULL
            ON UPDATE CASCADE,
    CONSTRAINT fk_passports_customer
        FOREIGN KEY (customer_id)
            REFERENCES customers (id)
            ON DELETE SET NULL
            ON UPDATE CASCADE,
    INDEX idx_passports_employee_id (employee_id),
    INDEX idx_passports_customer_id (customer_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE accounts (
    id             BIGINT         NOT NULL AUTO_INCREMENT,
    customer_id    BIGINT         NOT NULL,
    account_number VARCHAR(50)    NULL,
    balance        DECIMAL(19, 4) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_accounts_customer
        FOREIGN KEY (customer_id)
            REFERENCES customers (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    INDEX idx_accounts_customer_id (customer_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE transactions (
    id               BIGINT         NOT NULL AUTO_INCREMENT,
    account_id       BIGINT         NOT NULL,
    amount           DECIMAL(19, 4) NULL,
    transaction_time DATETIME       NULL,
    description      VARCHAR(500)   NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_transactions_account
        FOREIGN KEY (account_id)
            REFERENCES accounts (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    INDEX idx_transactions_account_id (account_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE loans (
    id         BIGINT         NOT NULL AUTO_INCREMENT,
    account_id BIGINT         NOT NULL,
    principal  DECIMAL(19, 4) NULL,
    start_date DATE           NULL,
    active     TINYINT(1)     NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_loans_account
        FOREIGN KEY (account_id)
            REFERENCES accounts (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    UNIQUE KEY uq_loans_account_id (account_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE cards (
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    account_id  BIGINT      NOT NULL,
    card_number VARCHAR(20) NULL,
    issued_at   DATETIME    NULL,
    blocked     TINYINT(1)  NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_cards_account
        FOREIGN KEY (account_id)
            REFERENCES accounts (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    UNIQUE KEY uq_cards_account_id (account_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE tasks (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    employee_id BIGINT       NOT NULL,
    title       VARCHAR(255) NULL,
    due_date    DATE         NULL,
    completed   TINYINT(1)   NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_tasks_employee
        FOREIGN KEY (employee_id)
            REFERENCES employees (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    INDEX idx_tasks_employee_id (employee_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
