package cn.iocoder.yudao.module.lfpath.service.candidatescoredistribution;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.lfpath.controller.admin.candidatescoredistribution.vo.*;
import cn.iocoder.yudao.module.lfpath.dal.dataobject.candidatescoredistribution.CandidateScoreDistributionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.lfpath.dal.mysql.candidatescoredistribution.CandidateScoreDistributionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lfpath.enums.ErrorCodeConstants.*;

/**
 * 考生分数分布 Service 实现类
 *
 * @author lfpath
 */
@Service
@Validated
public class CandidateScoreDistributionServiceImpl implements CandidateScoreDistributionService {

    @Resource
    private CandidateScoreDistributionMapper candidateScoreDistributionMapper;

    @Override
    public Long createCandidateScoreDistribution(CandidateScoreDistributionSaveReqVO createReqVO) {
        // 插入
        CandidateScoreDistributionDO candidateScoreDistribution = BeanUtils.toBean(createReqVO, CandidateScoreDistributionDO.class);
        candidateScoreDistributionMapper.insert(candidateScoreDistribution);
        // 返回
        return candidateScoreDistribution.getId();
    }

    @Override
    public void updateCandidateScoreDistribution(CandidateScoreDistributionSaveReqVO updateReqVO) {
        // 校验存在
        validateCandidateScoreDistributionExists(updateReqVO.getId());
        // 更新
        CandidateScoreDistributionDO updateObj = BeanUtils.toBean(updateReqVO, CandidateScoreDistributionDO.class);
        candidateScoreDistributionMapper.updateById(updateObj);
    }

    @Override
    public void deleteCandidateScoreDistribution(Long id) {
        // 校验存在
        validateCandidateScoreDistributionExists(id);
        // 删除
        candidateScoreDistributionMapper.deleteById(id);
    }

    private void validateCandidateScoreDistributionExists(Long id) {
        if (candidateScoreDistributionMapper.selectById(id) == null) {
            throw exception(CANDIDATE_SCORE_DISTRIBUTION_NOT_EXISTS);
        }
    }

    @Override
    public CandidateScoreDistributionDO getCandidateScoreDistribution(Long id) {
        return candidateScoreDistributionMapper.selectById(id);
    }

    @Override
    public PageResult<CandidateScoreDistributionDO> getCandidateScoreDistributionPage(CandidateScoreDistributionPageReqVO pageReqVO) {
        return candidateScoreDistributionMapper.selectPage(pageReqVO);
    }

}