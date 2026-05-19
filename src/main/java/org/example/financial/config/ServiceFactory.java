package org.example.financial.config;

import org.example.financial.service.AccountService;
import org.example.financial.service.BranchService;
import org.example.financial.service.CardService;
import org.example.financial.service.CustomerService;
import org.example.financial.service.EmployeeService;
import org.example.financial.service.FinancialInstitutionService;
import org.example.financial.service.TaskService;
import org.example.financial.service.TransactionService;
import org.example.financial.service.impl.AccountServiceImpl;
import org.example.financial.service.impl.BranchServiceImpl;
import org.example.financial.service.impl.CardServiceImpl;
import org.example.financial.service.impl.CustomerServiceImpl;
import org.example.financial.service.impl.EmployeeServiceImpl;
import org.example.financial.service.impl.FinancialInstitutionServiceImpl;
import org.example.financial.service.impl.TaskServiceImpl;
import org.example.financial.service.impl.TransactionServiceImpl;

public final class ServiceFactory {

    private static final FinancialInstitutionService FINANCIAL_INSTITUTION_SERVICE =
            new FinancialInstitutionServiceImpl();
    private static final CustomerService CUSTOMER_SERVICE = new CustomerServiceImpl();
    private static final AccountService ACCOUNT_SERVICE = new AccountServiceImpl();
    private static final EmployeeService EMPLOYEE_SERVICE = new EmployeeServiceImpl();
    private static final BranchService BRANCH_SERVICE = new BranchServiceImpl();
    private static final TransactionService TRANSACTION_SERVICE = new TransactionServiceImpl();
    private static final CardService CARD_SERVICE = new CardServiceImpl();
    private static final TaskService TASK_SERVICE = new TaskServiceImpl();

    private ServiceFactory() {
    }

    public static FinancialInstitutionService financialInstitutionService() {
        return FINANCIAL_INSTITUTION_SERVICE;
    }

    public static CustomerService customerService() {
        return CUSTOMER_SERVICE;
    }

    public static AccountService accountService() {
        return ACCOUNT_SERVICE;
    }

    public static EmployeeService employeeService() {
        return EMPLOYEE_SERVICE;
    }

    public static BranchService branchService() {
        return BRANCH_SERVICE;
    }

    public static TransactionService transactionService() {
        return TRANSACTION_SERVICE;
    }

    public static CardService cardService() {
        return CARD_SERVICE;
    }

    public static TaskService taskService() {
        return TASK_SERVICE;
    }
}
