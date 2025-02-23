package cn.iocoder.yudao.module.lfpath.controller.admin.candidatescoredistribution.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 考生分数分布 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CandidateScoreDistributionRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "5471")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "分数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分数")
    private String score;

    @Schema(description = "本段人数", requiredMode = Schema.RequiredMode.REQUIRED, example = "28410")
    @ExcelProperty("本段人数")
    private Integer segmentCount;

    @Schema(description = "累计人数", requiredMode = Schema.RequiredMode.REQUIRED, example = "27256")
    @ExcelProperty("累计人数")
    private Integer cumulativeCount;

    @Schema(description = "省份", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("省份")
    private String province;

    @Schema(description = "年份", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("年份")
    private LocalDateTime year;

}