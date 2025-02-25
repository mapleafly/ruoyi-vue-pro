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

CREATE TABLE lfpath_major_directory (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
    parent_id BIGINT COMMENT '父级编号，用于标识层级关系',
    level VARCHAR(50) NOT NULL COMMENT '层级：1-门类，2-专业类，3-专业',
    major_name VARCHAR(255) NOT NULL COMMENT '名称（门类、专业类、专业名称）',
    major_code VARCHAR(50) COMMENT '专业代码，仅专业层级有',
    degree_type VARCHAR(100) COMMENT '学位授予门类，仅专业层级有',
    study_duration VARCHAR(50) COMMENT '修业年限，仅专业层级有',
    establishment_year VARCHAR(50) COMMENT '增设年度，仅专业层级有',
    directory_type VARCHAR(50) NOT NULL COMMENT '目录类型：本科（普通教育）、本科（职业教育）、高职（专科）',
    creator VARCHAR(64) COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除'
) COMMENT='高校专业目录表';

CREATE TABLE lfpath_candidate_score_distribution (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
    score VARCHAR(50) NOT NULL COMMENT '分数',
    segment_count INT NOT NULL COMMENT '本段人数',
    cumulative_count INT NOT NULL COMMENT '累计人数',
    province VARCHAR(100) NOT NULL COMMENT '省份',
    year VARCHAR(100) NOT NULL COMMENT '年份',
    creator VARCHAR(64) COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除'
) COMMENT='考生分数分布表';