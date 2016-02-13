package com.malexj.service.impl;

import com.malexj.model.bidirectionalOneToOneMapping_02.Account;
import com.malexj.repository.AccountRepository;
import com.malexj.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 12.02.16.
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public Account save(Account account) {
        return repository.saveAndFlush(account);
    }

    @Override
    public Account update(Account account) {
        return repository.saveAndFlush(account);
    }

    @Override
    public void delete(Long id) {
repository.delete(id);
    }

    @Override
    public Account findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }
}
