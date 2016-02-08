package com.malexj.service.impl;

import com.malexj.model.retrieveOneToOneMappedEntity.Address;
import com.malexj.repository.AddressRepository;
import com.malexj.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 08.02.16.
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository repository;

    @Override
    public Address save(Address address) {
        return repository.saveAndFlush(address);
    }

    @Override
    public Address update(Address address) {
        return repository.saveAndFlush(address);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Address findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Address> findAll() {
        return repository.findAll();
    }
}
