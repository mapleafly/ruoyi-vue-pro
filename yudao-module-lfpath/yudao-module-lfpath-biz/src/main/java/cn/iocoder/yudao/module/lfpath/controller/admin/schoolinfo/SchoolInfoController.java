package cn.iocoder.yudao.module.lfpath.controller.admin.schoolinfo;

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

import cn.iocoder.yudao.module.lfpath.controller.admin.schoolinfo.vo.*;
import cn.iocoder.yudao.module.lfpath.dal.dataobject.schoolinfo.SchoolInfoDO;
import cn.iocoder.yudao.module.lfpath.service.schoolinfo.SchoolInfoService;

@Tag(name = "管理后台 - 高校信息")
@RestController
@RequestMapping("/lfpath/school-info")
@Validated
public class SchoolInfoController {

    @Resource
    private SchoolInfoService schoolInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建高校信息")
    @PreAuthorize("@ss.hasPermission('lfpath:school-info:create')")
    public CommonResult<Long> createSchoolInfo(@Valid @RequestBody SchoolInfoSaveReqVO createReqVO) {
        return success(schoolInfoService.createSchoolInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新高校信息")
    @PreAuthorize("@ss.hasPermission('lfpath:school-info:update')")
    public CommonResult<Boolean> updateSchoolInfo(@Valid @RequestBody SchoolInfoSaveReqVO updateReqVO) {
        schoolInfoService.updateSchoolInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除高校信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lfpath:school-info:delete')")
    public CommonResult<Boolean> deleteSchoolInfo(@RequestParam("id") Long id) {
        schoolInfoService.deleteSchoolInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得高校信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lfpath:school-info:query')")
    public CommonResult<SchoolInfoRespVO> getSchoolInfo(@RequestParam("id") Long id) {
        SchoolInfoDO schoolInfo = schoolInfoService.getSchoolInfo(id);
        return success(BeanUtils.toBean(schoolInfo, SchoolInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得高校信息分页")
    @PreAuthorize("@ss.hasPermission('lfpath:school-info:query')")
    public CommonResult<PageResult<SchoolInfoRespVO>> getSchoolInfoPage(@Valid SchoolInfoPageReqVO pageReqVO) {
        PageResult<SchoolInfoDO> pageResult = schoolInfoService.getSchoolInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SchoolInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出高校信息 Excel")
    @PreAuthorize("@ss.hasPermission('lfpath:school-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSchoolInfoExcel(@Valid SchoolInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SchoolInfoDO> list = schoolInfoService.getSchoolInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "高校信息.xls", "普通高等学校", SchoolInfoRespVO.class,
                        BeanUtils.toBean(list, SchoolInfoRespVO.class));
    }

}