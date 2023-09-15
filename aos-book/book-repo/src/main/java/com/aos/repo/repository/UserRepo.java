package com.aos.repo.repository;

import com.aos.core.repository.BaseRepository;
import com.aos.repo.entity.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends BaseRepository<User> {
  Optional<User> findOneByUsername(String username);
}
