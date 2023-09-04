package com.aos.interfaces;

import com.aos.application.dto.BookAddDTO;
import com.aos.core.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface IBookInterface {
    BaseResponse handleAdd(@Valid @RequestBody BookAddDTO bookAddDTO);
}
