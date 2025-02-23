package cn.iocoder.yudao.module.lfpath.dal.mysql.candidatescoredistribution;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lfpath.dal.dataobject.candidatescoredistribution.CandidateScoreDistributionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.lfpath.controller.admin.candidatescoredistribution.vo.*;

/**
 * 考生分数分布 Mapper
 *
 * @author lfpath
 */
@Mapper
public interface CandidateScoreDistributionMapper extends BaseMapperX<CandidateScoreDistributionDO> {

    default PageResult<CandidateScoreDistributionDO> selectPage(CandidateScoreDistributionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CandidateScoreDistributionDO>()
                .betweenIfPresent(CandidateScoreDistributionDO::getScore, reqVO.getScore())
                .eqIfPresent(CandidateScoreDistributionDO::getProvince, reqVO.getProvince())
                .eqIfPresent(CandidateScoreDistributionDO::getYear, reqVO.getYear())
                .orderByDesc(CandidateScoreDistributionDO::getId));
    }

}