CREATE TABLE `jy_customer_follow` (
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '删除标识',
  `remarks` varchar(255) DEFAULT NULL COMMENT ' 备注',
  `id` varchar(64) NOT NULL COMMENT 'id',
  `dat` datetime NOT NULL COMMENT '时间',
  `follow_content` varchar(400) DEFAULT NULL COMMENT '跟进内容',
  `follower_id` varchar(64) DEFAULT NULL COMMENT '跟进人id',
  `hid` varchar(64) DEFAULT NULL COMMENT 'hid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='京远客户跟进';

CREATE TABLE `jy_customer` (
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '删除标识',
  `remarks` varchar(255) DEFAULT NULL COMMENT ' 备注',
  `id` varchar(64) NOT NULL COMMENT 'id',
  `typ` varchar(32) NOT NULL COMMENT '客户类型',
  `agent` varchar(32) DEFAULT NULL COMMENT '代理项目',
  `level` varchar(32) DEFAULT NULL COMMENT '客户级别',
  `current_follower_id` varchar(64) DEFAULT NULL COMMENT '当前跟进人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='京远客户';

CREATE TABLE `jy_contacter` (
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '删除标识',
  `remarks` varchar(255) DEFAULT NULL COMMENT ' 备注',
  `id` varchar(64) NOT NULL COMMENT 'id',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `hid` varchar(64) DEFAULT NULL COMMENT 'hid',
  `qqowx` varchar(32) DEFAULT NULL COMMENT 'QQ或微信',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='京远联系人';

