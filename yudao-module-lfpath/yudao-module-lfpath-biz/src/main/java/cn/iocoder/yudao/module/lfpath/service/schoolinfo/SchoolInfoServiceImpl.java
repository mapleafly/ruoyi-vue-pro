package cn.iocoder.yudao.module.lfpath.service.schoolinfo;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.lfpath.controller.admin.schoolinfo.vo.*;
import cn.iocoder.yudao.module.lfpath.dal.dataobject.schoolinfo.SchoolInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.lfpath.dal.mysql.schoolinfo.SchoolInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lfpath.enums.ErrorCodeConstants.*;

/**
 * 高校信息 Service 实现类
 *
 * @author LFPath
 */
@Service
@Validated
public class SchoolInfoServiceImpl implements SchoolInfoService {

    @Resource
    private SchoolInfoMapper schoolInfoMapper;

    @Override
    public Long createSchoolInfo(SchoolInfoSaveReqVO createReqVO) {
        // 插入
        SchoolInfoDO schoolInfo = BeanUtils.toBean(createReqVO, SchoolInfoDO.class);
        schoolInfoMapper.insert(schoolInfo);
        // 返回
        return schoolInfo.getId();
    }

    @Override
    public void updateSchoolInfo(SchoolInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateSchoolInfoExists(updateReqVO.getId());
        // 更新
        SchoolInfoDO updateObj = BeanUtils.toBean(updateReqVO, SchoolInfoDO.class);
        schoolInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteSchoolInfo(Long id) {
        // 校验存在
        validateSchoolInfoExists(id);
        // 删除
        schoolInfoMapper.deleteById(id);
    }

    private void validateSchoolInfoExists(Long id) {
        if (schoolInfoMapper.selectById(id) == null) {
            throw exception(SCHOOL_INFO_NOT_EXISTS);
        }
    }

    @Override
    public SchoolInfoDO getSchoolInfo(Long id) {
        return schoolInfoMapper.selectById(id);
    }

    @Override
    public PageResult<SchoolInfoDO> getSchoolInfoPage(SchoolInfoPageReqVO pageReqVO) {
        return schoolInfoMapper.selectPage(pageReqVO);
    }

}