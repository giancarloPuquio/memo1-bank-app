package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction, Double monto, String tipo, Long cbu,
                                         AccountService accountService) {
        transaction.setMonto(monto);
        transaction.setTipo(tipo);
        transaction.setCuenta(cbu);

        if(tipo.equals("deposito")){
            accountService.deposit(cbu, monto);
        }
        if(tipo.equals("extraccion")){
            accountService.withdraw(cbu, monto);
        }

        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public void deleteById(Long id, AccountService accountService) {
        Transaction transaction = transactionRepository.findTransaccionById(id);

        if(transaction.getTipo().equals("deposito")){
            accountService.withdraw(transaction.getCuenta(),transaction.getMonto());
        }
        if(transaction.getTipo().equals("extraccion")){
            accountService.deposit(transaction.getCuenta(),transaction.getMonto());
        }

        transactionRepository.deleteById(id);
    }


}
