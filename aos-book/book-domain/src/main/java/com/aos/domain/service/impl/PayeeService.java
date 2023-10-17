package com.aos.domain.service.impl;

import com.aos.domain.service.IPayeeService;
import com.aos.repo.entity.Book;
import com.aos.repo.repository.PayeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayeeService implements IPayeeService {

    @Autowired
    private PayeeRepo payeeRepo;

    @Override
    public void deleteByBook(Book book) {
        payeeRepo.deleteByBook(book);
    }
}
