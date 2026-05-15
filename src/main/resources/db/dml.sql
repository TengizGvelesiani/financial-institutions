USE financial_institutions;

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE tasks;
TRUNCATE TABLE cards;
TRUNCATE TABLE loans;
TRUNCATE TABLE transactions;
TRUNCATE TABLE accounts;
TRUNCATE TABLE passports;
TRUNCATE TABLE customers;
TRUNCATE TABLE employees;
TRUNCATE TABLE branches;
TRUNCATE TABLE financial_institutions;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO financial_institutions (id, name, license_number, active, established_date, created_at)
VALUES (1, 'Bank Of Georgia', 10001234567890.00, 1, '1920-03-15', '2020-01-10 09:00:00'),
       (2, 'TBC', 20009876543210.50, 1, '1985-07-22', '2021-06-01 14:30:00');

INSERT INTO branches (id, financial_institution_id, name, address)
VALUES (1, 1, 'Kutaisi Branch', '10 Main Street, Kutaisi, KU'),
       (2, 1, 'Batumi Branch', '250 Lake Shore Dr, Chicago, BT'),
       (3, 2, 'Freedom Square Office', '44 Freedom Square, Tbilisi, TB'),
       (4, 2, 'Tbilisi Office', '90 Akaki Street, Tbilisi, TB');

INSERT INTO employees (id, financial_institution_id, full_name, hire_date, salary)
VALUES (1, 1, 'David James', '2018-04-02', 72000.00),
       (2, 1, 'Brian Smith', '2019-11-18', 68500.00),
       (3, 2, 'Carla Ruiz', '2017-09-05', 81000.00),
       (4, 2, 'David Kim', '2022-02-14', 64000.00);

INSERT INTO customers (id, financial_institution_id, full_name, birth_date, registered_at, active)
VALUES (1, 1, 'Giorgio James', '1990-05-12', '2023-03-01 10:15:00', 1),
       (2, 1, 'Frank Smith', '1982-12-30', '2023-03-02 11:20:00', 1),
       (3, 2, 'Grace Kim', '1995-08-07', '2024-01-15 08:45:00', 1),
       (4, 2, 'Henry James', '1978-01-25', '2024-01-16 16:00:00', 0);

INSERT INTO passports (id, employee_id, customer_id, passport_number, issue_date, expiry_date)
VALUES (1, 1, NULL, 'P100001A', '2015-06-01', '2025-06-01'),
       (2, 2, NULL, 'P100002B', '2016-03-10', '2026-03-10'),
       (3, 3, NULL, 'P200001C', '2014-11-20', '2024-11-20'),
       (4, 4, NULL, 'P200002D', '2018-07-05', '2028-07-05'),
       (5, NULL, 1, 'P300001E', '2019-02-14', '2029-02-14'),
       (6, NULL, 2, 'P300002F', '2020-09-01', '2030-09-01'),
       (7, NULL, 3, 'P300003G', '2021-04-18', '2031-04-18'),
       (8, NULL, 4, 'P300004H', '2010-12-12', '2020-12-12');

INSERT INTO accounts (id, customer_id, account_number, balance)
VALUES (1, 1, 'ACC-10001', 15420.7500),
       (2, 1, 'ACC-10002', 3200.0000),
       (3, 2, 'ACC-20001', 8750.5000),
       (4, 3, 'ACC-30001', 22100.0000),
       (5, 4, 'ACC-40001', 450.2500);

INSERT INTO transactions (id, account_id, amount, transaction_time, description)
VALUES (1, 1, 500.0000, '2024-05-01 09:12:00', 'Salary deposit'),
       (2, 1, -45.9900, '2024-05-02 18:40:00', 'Grocery payment'),
       (3, 2, 200.0000, '2024-05-03 12:00:00', 'Transfer in'),
       (4, 3, -1200.0000, '2024-05-04 10:05:00', 'Rent payment'),
       (5, 4, 15000.0000, '2024-05-05 08:30:00', 'Business income'),
       (6, 5, -25.5000, '2024-05-06 14:22:00', 'ATM withdrawal');

INSERT INTO loans (id, account_id, principal, start_date, active)
VALUES (1, 1, 10000.0000, '2023-01-15', 1),
       (2, 3, 25000.0000, '2022-08-01', 1),
       (3, 4, 50000.0000, '2024-02-10', 1);

INSERT INTO cards (id, account_id, card_number, issued_at, blocked)
VALUES (1, 1, '4111111111111001', '2023-06-01 00:00:00', 0),
       (2, 2, '4111111111111002', '2023-07-15 00:00:00', 0),
       (3, 3, '4111111111112001', '2022-11-20 00:00:00', 1),
       (4, 4, '4111111111113001', '2024-03-01 00:00:00', 0);

INSERT INTO tasks (id, employee_id, title, due_date, completed)
VALUES (1, 1, 'Quarterly compliance review', '2024-06-30', 0),
       (2, 1, 'Onboard new teller', '2024-05-20', 1),
       (3, 2, 'Update branch security policy', '2024-07-01', 0),
       (4, 3, 'Audit customer accounts', '2024-06-15', 0),
       (5, 4, 'Prepare annual report', '2024-08-01', 0);
