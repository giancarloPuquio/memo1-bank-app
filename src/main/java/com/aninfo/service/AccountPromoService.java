package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountPromoService extends AccountService{

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        Account account = accountRepository.findAccountByCbu(cbu);

        if (sum>=2000) {
            account.setBalance(promo(account.getBalance(), sum));
        }else{
            account.setBalance(account.getBalance() + sum);
        }
        accountRepository.save(account);
        return account;
    }

    private Double promo(Double balance, Double sum){

        Double promo = (sum*10/100);
        if(promo > 500){
            promo = 500.00;
        }
        return balance + sum + promo;
    }

}
