CREATE TABLE lfpath_school_info (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
    school_name VARCHAR(255) NOT NULL COMMENT '学校名称',
    school_code VARCHAR(50) NOT NULL COMMENT '学校标识码',
    administrative_department VARCHAR(255) NOT NULL COMMENT '管理部门',
    location VARCHAR(255) NOT NULL COMMENT '所在地',
    education_level VARCHAR(100) NOT NULL COMMENT '办学层次',
    education_system VARCHAR(100) NOT NULL COMMENT '办学体制',
    creator VARCHAR(64) COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除'
) COMMENT='学校信息表';

CREATE TABLE lfpath_candidate_score_distribution (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
    score VARCHAR(50) NOT NULL COMMENT '分数',
    segment_count INT NOT NULL COMMENT '本段人数',
    cumulative_count INT NOT NULL COMMENT '累计人数',
    province VARCHAR(100) NOT NULL COMMENT '省份',
    year DATETIME NOT NULL COMMENT '年份',
    creator VARCHAR(64) COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除'
) COMMENT='考生分数分布表';