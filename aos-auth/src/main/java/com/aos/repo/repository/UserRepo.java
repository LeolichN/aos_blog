package com.aos.repo.repository;


import com.aos.repo.entity.User;
import com.aos.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends BaseRepository<User> {
  Optional<User> findOneByUsername(String username);
}
