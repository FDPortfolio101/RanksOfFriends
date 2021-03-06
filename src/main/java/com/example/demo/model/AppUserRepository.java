package com.example.demo.model;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    boolean existsByUsername(String username);
}
