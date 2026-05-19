package org.example.financial;

import org.example.financial.controller.AccountController;
import org.example.financial.model.dto.AccountOverviewDto;

public class Application {

    public static void main(String[] args) {
        AccountController accountController = new AccountController();
        var overview = accountController.getAccountOverview(1L);
        for (AccountOverviewDto row : overview) {
            System.out.println(row.getFinancialInstitutionName() + " / "
                    + row.getCustomerName() + " / " + row.getAccountNumber());
        }
    }
}
