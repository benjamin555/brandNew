-- 案件进度
CREATE TABLE bg_case_follow
(
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	del_flag char NOT NULL COMMENT '删除标识',
	remarks varchar(255) COMMENT ' 备注',
	id varchar(64) NOT NULL COMMENT 'id',
	-- 描述
	descrip varchar(4000) COMMENT 'descrip : 描述',
	case_id varchar(64) NOT NULL COMMENT '案件id',
	PRIMARY KEY (id)
) COMMENT = '案件进度';