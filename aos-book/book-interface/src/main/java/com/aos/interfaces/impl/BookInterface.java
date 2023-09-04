package com.aos.interfaces.impl;

import com.aos.application.dto.BookAddDTO;
import com.aos.application.service.IBookApplication;
import com.aos.core.response.BaseResponse;
import com.aos.interfaces.IBookInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/book")
@RestController
public class BookInterface implements IBookInterface {
    @Autowired
    private IBookApplication bookApplication;

    @Override
    public BaseResponse handleAdd(BookAddDTO bookAddDTO) {
        bookApplication.addBook(bookAddDTO);
        return new BaseResponse();
    }
}
