package com.aos.interfaces;

import com.aos.core.response.BaseResponse;
import com.aos.core.response.PageResponse;
import com.aos.repo.dto.AccountOperateDTO;
import com.aos.repo.dto.AccountQueryDTO;
import com.aos.repo.dto.AdjustBalanceAddDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public interface IAccountInterface {
    @GetMapping("/overview")
    BaseResponse handleOverview();

    @GetMapping("/statistics")
    BaseResponse handleStatistics(AccountQueryDTO accountQueryDTO);

    @PostMapping
    BaseResponse handleAdd(@Valid @RequestBody AccountOperateDTO accountAddDTO);

    @GetMapping("")
    PageResponse handleQuery(
            AccountQueryDTO form,
            @PageableDefault(sort = "balance", direction = Sort.Direction.DESC) Pageable page);

    @PutMapping("/{id}")
    BaseResponse handleUpdate(@PathVariable("id") Integer id, @Valid @RequestBody AccountOperateDTO form);

    @PutMapping("/{id}/delete")
    BaseResponse handleDelete(@PathVariable("id") Integer id);

    @PostMapping("/{id}/adjust")
    BaseResponse handleAdjust(@PathVariable("id") Integer id, @Valid @RequestBody AdjustBalanceAddDTO form);

    @PutMapping("/{id}/adjust")
    BaseResponse handleUpdateAdjust(@PathVariable("id") Integer id, @Valid @RequestBody AdjustBalanceAddDTO form);
}
