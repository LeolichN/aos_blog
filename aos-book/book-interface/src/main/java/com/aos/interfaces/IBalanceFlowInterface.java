package com.aos.interfaces;

import com.aos.core.response.BaseResponse;
import com.aos.repo.dto.BalanceFlowOperateDTO;
import com.aos.repo.dto.BalanceFlowQueryDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance-flows")
public interface IBalanceFlowInterface {
    @PostMapping("")
    BaseResponse handleAdd(@Valid @RequestBody BalanceFlowOperateDTO form);

    @GetMapping("")
    BaseResponse handleQuery(BalanceFlowQueryDTO form, @PageableDefault(sort = "createTime", direction = Sort.Direction.DESC) Pageable page);

    @GetMapping("/{id}")
    BaseResponse handleGet(@PathVariable("id") Integer id);

    @PutMapping("/{id}")
    BaseResponse handleUpdate(@PathVariable("id") Integer id, @Valid @RequestBody BalanceFlowOperateDTO form);

    @DeleteMapping("/{id}")
    BaseResponse handleDelete(@PathVariable("id") Integer id);

    @GetMapping("/statistics")
    BaseResponse handleStatistics(BalanceFlowQueryDTO form);

    @PatchMapping("/{id}/confirm")
    BaseResponse handleConfirm(@PathVariable("id") Integer id);
}
