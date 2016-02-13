package com.malexj.service;

import com.malexj.model.bidirectionalOneToOneMapping_02.User;

import java.util.List;

/**
 * Created by malex on 12.02.16.
 */
public interface UserService {

    User save(User user);

    User update(User user);

    void delete(Long id);

    User findById(Long id);

    List<User> findAll();

}
