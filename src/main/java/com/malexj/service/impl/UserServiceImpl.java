package com.malexj.service.impl;

import com.malexj.model.bidirectionalOneToOneMapping_02.User;
import com.malexj.repository.UserRepository;
import com.malexj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 12.02.16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.saveAndFlush(user);
    }

    @Override
    public User update(User user) {
        return repository.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public User findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
