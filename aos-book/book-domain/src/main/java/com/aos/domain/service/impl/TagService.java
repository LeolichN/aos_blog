package com.aos.domain.service.impl;

import com.aos.domain.service.ITagService;
import com.aos.repo.entity.Book;
import com.aos.repo.entity.Tag;
import com.aos.repo.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService implements ITagService {
    @Autowired
    private TagRepo tagRepo;
    @Override
    public void deleteByBook(Book book) {
        tagRepo.deleteByBook(book);
    }
}
