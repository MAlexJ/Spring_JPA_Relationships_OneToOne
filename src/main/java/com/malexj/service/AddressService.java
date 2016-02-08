package com.malexj.service;

import com.malexj.model.retrieveOneToOneMappedEntity.Address;

import java.util.List;

/**
 * Created by malex on 08.02.16.
 */
public interface AddressService {

    Address save(Address address);

    Address update(Address address);

    void delete(Long id);

    Address findById(Long id);

    List<Address> findAll();

}
