package com.malexj.service;

import com.malexj.model.bidirectionalOneToOneMapping.Bank;

import java.util.List;

/**
 * Created by malex on 09.02.16.
 */
public interface BankService {

    Bank save(Bank bank);

    Bank update(Bank bank);

    void delete(Long id);

    Bank findById(Long id);

    List<Bank> findAll();

}
