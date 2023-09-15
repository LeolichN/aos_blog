package com.aos.repo.repository;

import com.aos.core.repository.BaseRepository;
import com.aos.repo.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends BaseRepository<Book> {}
