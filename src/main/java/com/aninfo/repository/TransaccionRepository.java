package com.aninfo.repository;


import java.util.List;

import com.aninfo.model.Transaccion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransaccionRepository extends CrudRepository<Transaccion, Long> {
    Transaccion findTransaccionById(Long id);
    @Override
    List<Transaccion> findAll();
}