package com.aos.interfaces;

import com.aos.core.response.BaseResponse;
import com.aos.core.response.DataResponse;
import com.aos.core.response.PageResponse;
import com.aos.repo.dto.BookOperateDTO;
import com.aos.repo.dto.BookQueryDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.vo.BookVO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/books")
@RestController
public interface IBookInterface {

  @PostMapping("")
  BaseResponse handleAdd(@Valid @RequestBody BookOperateDTO bookAddDTO);

  @GetMapping("/{id}")
  DataResponse<BookVO> handleGet(@PathVariable("id") Integer id);

  @GetMapping("")
  PageResponse queryBookList(BookQueryDTO bookQueryDTO, Pageable page);

  @GetMapping("/all")
  BaseResponse handleAll(BookQueryDTO form);
  
  @PutMapping("/{id}")
  BaseResponse handleUpdate(@PathVariable("id") Integer id, @Valid @RequestBody BookOperateDTO form);

  @DeleteMapping("/{id}")
  BaseResponse handleDelete(@PathVariable("id") Integer id);

  @PatchMapping("/{id}/toggle")
  BaseResponse handleToggle(@PathVariable("id") Integer id);

//  @PostMapping("/template")
//  BaseResponse handleAddByTemplate(@Valid @RequestBody BookAddByTemplateForm form);
}
