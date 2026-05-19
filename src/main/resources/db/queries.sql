USE financial_institutions;

-- Updates 10

UPDATE financial_institutions
SET active = 0
WHERE id = 2;

UPDATE branches
SET address = '101 Main Street, Kutaisi, KU'
WHERE id = 1;

UPDATE employees
SET salary = salary * 1.05
WHERE financial_institution_id = 1;

UPDATE customers
SET active = 1
WHERE id = 4;

UPDATE passports
SET expiry_date = '2030-12-31'
WHERE passport_number = 'P300004H';

UPDATE accounts
SET balance = balance + 100.0000
WHERE account_number = 'ACC-10001';

UPDATE transactions
SET description = 'Adjusted grocery payment'
WHERE id = 2;

UPDATE loans
SET active = 0
WHERE account_id = 3;

UPDATE cards
SET blocked = 0
WHERE id = 3;

UPDATE tasks
SET completed = 1
WHERE due_date < '2024-06-01' AND completed = 0;

-- Deletions 10

DELETE FROM tasks
WHERE id = 5;

DELETE FROM cards
WHERE blocked = 1 AND id <> 3;

DELETE FROM loans
WHERE active = 0;

DELETE FROM transactions
WHERE amount < 0 AND id = 6;

DELETE FROM accounts
WHERE balance < 1000.0000 AND id = 5;

DELETE FROM passports
WHERE expiry_date < '2021-01-01';

DELETE FROM customers
WHERE active = 0 AND id = 4;

DELETE FROM employees
WHERE salary < 65000.00;

DELETE FROM branches
WHERE name = 'Bayview Office';

DELETE FROM financial_institutions
WHERE active = 0;

-- Join all tables

SELECT fi.id                    AS financial_institution_id,
       fi.name                  AS financial_institution_name,
       b.id                     AS branch_id,
       b.name                   AS branch_name,
       e.id                     AS employee_id,
       e.full_name              AS employee_name,
       tk.id                    AS task_id,
       tk.title                 AS task_title,
       pep.id                   AS employee_passport_id,
       pep.passport_number      AS employee_passport_number,
       cu.id                    AS customer_id,
       cu.full_name             AS customer_name,
       ppc.id                   AS customer_passport_id,
       ppc.passport_number      AS customer_passport_number,
       a.id                     AS account_id,
       a.account_number,
       a.balance,
       tr.id                    AS transaction_id,
       tr.amount                AS transaction_amount,
       lo.id                    AS loan_id,
       lo.principal,
       cd.id                    AS card_id,
       cd.card_number
FROM financial_institutions fi
         INNER JOIN branches b ON b.financial_institution_id = fi.id
         INNER JOIN employees e ON e.financial_institution_id = fi.id
         INNER JOIN tasks tk ON tk.employee_id = e.id
         LEFT JOIN passports pep ON pep.employee_id = e.id
         INNER JOIN customers cu ON cu.financial_institution_id = fi.id
         LEFT JOIN passports ppc ON ppc.customer_id = cu.id
         INNER JOIN accounts a ON a.customer_id = cu.id
         INNER JOIN transactions tr ON tr.account_id = a.id
         INNER JOIN loans lo ON lo.account_id = a.id
         INNER JOIN cards cd ON cd.account_id = a.id
WHERE fi.id = 1
  AND b.id = 1
  AND e.id = 1
  AND cu.id = 1
  AND a.id = 1
  AND tr.id = 1;

-- Join types 5

SELECT c.id,
       c.full_name,
       a.id   AS account_id,
       a.balance
FROM customers c
         INNER JOIN accounts a ON a.customer_id = c.id;

SELECT c.id,
       c.full_name,
       l.id   AS loan_id,
       l.principal
FROM customers c
         LEFT JOIN accounts a ON a.customer_id = c.id
         LEFT JOIN loans l ON l.account_id = a.id;

SELECT e.id,
       e.full_name,
       t.id   AS task_id,
       t.title
FROM employees e
         RIGHT JOIN tasks t ON t.employee_id = e.id;

SELECT b.id,
       b.name,
       fi.id  AS institution_id,
       fi.name AS institution_name
FROM branches b
         LEFT JOIN financial_institutions fi ON fi.id = b.financial_institution_id
UNION
SELECT b.id,
       b.name,
       fi.id,
       fi.name
FROM branches b
         RIGHT JOIN financial_institutions fi ON fi.id = b.financial_institution_id
WHERE b.id IS NULL;

SELECT a.id,
       a.account_number,
       tr.id  AS transaction_id,
       tr.amount
FROM accounts a
         LEFT JOIN transactions tr ON tr.account_id = a.id
WHERE a.customer_id = 1
UNION
SELECT a.id,
       a.account_number,
       tr.id,
       tr.amount
FROM accounts a
         RIGHT JOIN transactions tr ON tr.account_id = a.id
WHERE a.id IS NULL;

-- Aggregates without HAVING 7

SELECT financial_institution_id,
       COUNT(*) AS employee_count
FROM employees
GROUP BY financial_institution_id;

SELECT customer_id,
       SUM(balance) AS total_balance
FROM accounts
GROUP BY customer_id;

SELECT account_id,
       AVG(amount) AS average_transaction_amount
FROM transactions
GROUP BY account_id;

SELECT employee_id,
       MIN(due_date) AS earliest_due_date
FROM tasks
GROUP BY employee_id;

SELECT account_id,
       MAX(principal) AS largest_loan_principal
FROM loans
GROUP BY account_id;

SELECT active,
       COUNT(DISTINCT id) AS institution_count
FROM financial_institutions
GROUP BY active;

SELECT financial_institution_id,
       hire_date,
       COUNT(*) AS hires_on_date
FROM employees
GROUP BY financial_institution_id, hire_date;

-- Aggregates with HAVING 7

SELECT customer_id,
       SUM(balance) AS total_balance
FROM accounts
GROUP BY customer_id
HAVING SUM(balance) > 5000.0000;

SELECT account_id,
       COUNT(*) AS transaction_count
FROM transactions
GROUP BY account_id
HAVING COUNT(*) >= 2;

SELECT financial_institution_id,
       AVG(salary) AS average_salary
FROM employees
GROUP BY financial_institution_id
HAVING AVG(salary) > 70000.00;

SELECT employee_id,
       COUNT(*) AS open_task_count
FROM tasks
GROUP BY employee_id
HAVING SUM(completed = 0) >= 1;

SELECT active,
       COUNT(*) AS customer_count
FROM customers
GROUP BY active
HAVING COUNT(*) >= 2;

SELECT account_id,
       MAX(issued_at) AS last_card_issued_at
FROM cards
GROUP BY account_id
HAVING MAX(issued_at) >= '2023-01-01';

SELECT financial_institution_id,
       COUNT(*) AS branch_count
FROM branches
GROUP BY financial_institution_id
HAVING COUNT(*) > 1;
