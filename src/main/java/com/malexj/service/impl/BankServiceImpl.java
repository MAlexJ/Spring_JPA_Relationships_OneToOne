package com.malexj.service.impl;

import com.malexj.model.bidirectionalOneToOneMapping.Bank;
import com.malexj.repository.BankRepository;
import com.malexj.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 09.02.16.
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository repository;

    @Override
    public Bank save(Bank bank) {
        return repository.saveAndFlush(bank);
    }

    @Override
    public Bank update(Bank bank) {
        return repository.saveAndFlush(bank);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Bank findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Bank> findAll() {
        return repository.findAll();
    }
}
