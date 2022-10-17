package com.rest.api.dorado.repository;

import com.rest.api.dorado.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
