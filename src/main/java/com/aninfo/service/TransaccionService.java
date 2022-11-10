package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaccion;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    public Transaccion createTransaccion(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public Collection<Transaccion> getTransaccions() {
        return transaccionRepository.findAll();
    }

    public Optional<Transaccion> findById(Long id) {
        return transaccionRepository.findById(id);
    }

    public void deleteById(Long cbu) {
        transaccionRepository.deleteById(cbu);
    }

}
