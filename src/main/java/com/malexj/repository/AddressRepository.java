package com.malexj.repository;

import com.malexj.model.retrieveOneToOneMappedEntity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 08.02.16.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
