package cn.iocoder.yudao.module.lfpath.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * lfpath 错误码枚举类
 * <p>
 * lfpath 系统，使用 1-111-000-000 段
 */
public interface ErrorCodeConstants {
    //==========  通用流程处理 模块 1-111-000-000 ==========
    // ========== 高校信息 1-111-001-000 ==========
    ErrorCode SCHOOL_INFO_NOT_EXISTS = new ErrorCode(1_111_001_001, "高校信息不存在");
    ErrorCode SCHOOL_IMPORT_LIST_IS_EMPTY = new ErrorCode(1_111_001_002, "导入高校数据不能为空！");
    ErrorCode SCHOOL_SCHOOLNAME_EXISTS = new ErrorCode(1_111_001_003, "高校名称已经存在");
}