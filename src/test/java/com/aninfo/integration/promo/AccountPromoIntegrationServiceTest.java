package com.aninfo.integration.promo;

import com.aninfo.Memo1BankApp;
import com.aninfo.model.Account;
import com.aninfo.service.AccountPromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = Memo1BankApp.class)
@WebAppConfiguration
public class AccountPromoIntegrationServiceTest {
    @Autowired
    AccountPromoService accountPromoService;

    Account createAccount(Double balance) {
        return accountPromoService.createAccount(new Account(balance));
    }

    Account deposit(Account account, Double sum) {
        return accountPromoService.deposit(account.getCbu(), sum);
    }
}
