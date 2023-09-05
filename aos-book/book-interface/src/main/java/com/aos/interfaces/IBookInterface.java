package com.aos.interfaces;

import com.aos.repo.dto.BookAddDTO;
import com.aos.core.response.DataResponse;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/book")
@RestController
public interface IBookInterface {

    @PostMapping("/add")
    DataResponse<Book> handleAdd(@Valid @RequestBody BookAddDTO bookAddDTO);

    @GetMapping("/{id}")
    DataResponse<Book> handleGet(@PathVariable("id") Integer id);

    @PostMapping("/query")
    public DataResponse<Book> handleAdd(BookQueryDTO bookQueryDTO, Pageable page);


}
