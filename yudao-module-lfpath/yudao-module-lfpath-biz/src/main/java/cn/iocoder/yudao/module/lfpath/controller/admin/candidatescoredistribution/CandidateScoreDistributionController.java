package cn.iocoder.yudao.module.lfpath.controller.admin.candidatescoredistribution;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.lfpath.controller.admin.candidatescoredistribution.vo.*;
import cn.iocoder.yudao.module.lfpath.dal.dataobject.candidatescoredistribution.CandidateScoreDistributionDO;
import cn.iocoder.yudao.module.lfpath.service.candidatescoredistribution.CandidateScoreDistributionService;

@Tag(name = "管理后台 - 考生分数分布")
@RestController
@RequestMapping("/lfpath/candidate-score-distribution")
@Validated
public class CandidateScoreDistributionController {

    @Resource
    private CandidateScoreDistributionService candidateScoreDistributionService;

    @PostMapping("/create")
    @Operation(summary = "创建考生分数分布")
    @PreAuthorize("@ss.hasPermission('lfpath:candidate-score-distribution:create')")
    public CommonResult<Long> createCandidateScoreDistribution(@Valid @RequestBody CandidateScoreDistributionSaveReqVO createReqVO) {
        return success(candidateScoreDistributionService.createCandidateScoreDistribution(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新考生分数分布")
    @PreAuthorize("@ss.hasPermission('lfpath:candidate-score-distribution:update')")
    public CommonResult<Boolean> updateCandidateScoreDistribution(@Valid @RequestBody CandidateScoreDistributionSaveReqVO updateReqVO) {
        candidateScoreDistributionService.updateCandidateScoreDistribution(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除考生分数分布")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lfpath:candidate-score-distribution:delete')")
    public CommonResult<Boolean> deleteCandidateScoreDistribution(@RequestParam("id") Long id) {
        candidateScoreDistributionService.deleteCandidateScoreDistribution(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得考生分数分布")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lfpath:candidate-score-distribution:query')")
    public CommonResult<CandidateScoreDistributionRespVO> getCandidateScoreDistribution(@RequestParam("id") Long id) {
        CandidateScoreDistributionDO candidateScoreDistribution = candidateScoreDistributionService.getCandidateScoreDistribution(id);
        return success(BeanUtils.toBean(candidateScoreDistribution, CandidateScoreDistributionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得考生分数分布分页")
    @PreAuthorize("@ss.hasPermission('lfpath:candidate-score-distribution:query')")
    public CommonResult<PageResult<CandidateScoreDistributionRespVO>> getCandidateScoreDistributionPage(@Valid CandidateScoreDistributionPageReqVO pageReqVO) {
        PageResult<CandidateScoreDistributionDO> pageResult = candidateScoreDistributionService.getCandidateScoreDistributionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CandidateScoreDistributionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出考生分数分布 Excel")
    @PreAuthorize("@ss.hasPermission('lfpath:candidate-score-distribution:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCandidateScoreDistributionExcel(@Valid CandidateScoreDistributionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CandidateScoreDistributionDO> list = candidateScoreDistributionService.getCandidateScoreDistributionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "考生分数分布.xls", "数据", CandidateScoreDistributionRespVO.class,
                        BeanUtils.toBean(list, CandidateScoreDistributionRespVO.class));
    }

}