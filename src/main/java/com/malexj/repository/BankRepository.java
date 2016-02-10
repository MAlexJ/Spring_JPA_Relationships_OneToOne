package com.malexj.repository;

import com.malexj.model.bidirectionalOneToOneMapping.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 09.02.16.
 */
public interface BankRepository extends JpaRepository<Bank, Long> {
}
