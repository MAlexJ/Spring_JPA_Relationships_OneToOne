package com.malexj.repository;

import com.malexj.model.bidirectionalOneToOneMapping_02.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 12.02.16.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
