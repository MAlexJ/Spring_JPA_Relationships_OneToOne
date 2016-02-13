package com.malexj.repository;

import com.malexj.model.bidirectionalOneToOneMapping_02.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 12.02.16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
