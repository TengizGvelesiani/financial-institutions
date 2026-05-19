package org.example.financial.config;

import org.example.financial.persistence.AccountRepository;
import org.example.financial.persistence.BranchRepository;
import org.example.financial.persistence.CardRepository;
import org.example.financial.persistence.CustomerRepository;
import org.example.financial.persistence.EmployeeRepository;
import org.example.financial.persistence.FinancialInstitutionRepository;
import org.example.financial.persistence.TaskRepository;
import org.example.financial.persistence.TransactionRepository;
import org.example.financial.persistence.jdbc.JdbcAccountRepository;
import org.example.financial.persistence.jdbc.JdbcBranchRepository;
import org.example.financial.persistence.jdbc.JdbcCardRepository;
import org.example.financial.persistence.jdbc.JdbcCustomerRepository;
import org.example.financial.persistence.jdbc.JdbcEmployeeRepository;
import org.example.financial.persistence.jdbc.JdbcFinancialInstitutionRepository;
import org.example.financial.persistence.jdbc.JdbcTaskRepository;
import org.example.financial.persistence.jdbc.JdbcTransactionRepository;

public final class RepositoryFactory {

    private static final FinancialInstitutionRepository FINANCIAL_INSTITUTION_REPOSITORY =
            new JdbcFinancialInstitutionRepository();
    private static final CustomerRepository CUSTOMER_REPOSITORY = new JdbcCustomerRepository();
    private static final AccountRepository ACCOUNT_REPOSITORY = new JdbcAccountRepository();
    private static final EmployeeRepository EMPLOYEE_REPOSITORY = new JdbcEmployeeRepository();
    private static final BranchRepository BRANCH_REPOSITORY = new JdbcBranchRepository();
    private static final TransactionRepository TRANSACTION_REPOSITORY = new JdbcTransactionRepository();
    private static final CardRepository CARD_REPOSITORY = new JdbcCardRepository();
    private static final TaskRepository TASK_REPOSITORY = new JdbcTaskRepository();

    private RepositoryFactory() {
    }

    public static FinancialInstitutionRepository financialInstitutionRepository() {
        return FINANCIAL_INSTITUTION_REPOSITORY;
    }

    public static CustomerRepository customerRepository() {
        return CUSTOMER_REPOSITORY;
    }

    public static AccountRepository accountRepository() {
        return ACCOUNT_REPOSITORY;
    }

    public static EmployeeRepository employeeRepository() {
        return EMPLOYEE_REPOSITORY;
    }

    public static BranchRepository branchRepository() {
        return BRANCH_REPOSITORY;
    }

    public static TransactionRepository transactionRepository() {
        return TRANSACTION_REPOSITORY;
    }

    public static CardRepository cardRepository() {
        return CARD_REPOSITORY;
    }

    public static TaskRepository taskRepository() {
        return TASK_REPOSITORY;
    }
}
