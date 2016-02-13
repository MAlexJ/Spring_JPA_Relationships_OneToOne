package com.malexj.service;

import com.malexj.model.bidirectionalOneToOneMapping_02.Account;

import java.util.List;

/**
 * Created by malex on 12.02.16.
 */
public interface AccountService {

    Account save(Account account);

    Account update(Account account);

    void delete(Long id);

    Account findById(Long id);

    List<Account> findAll();
}
