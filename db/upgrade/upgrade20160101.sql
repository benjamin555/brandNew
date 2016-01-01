CREATE TABLE `bg_daily_contacter` (
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '删除标识',
  `remarks` varchar(255) DEFAULT NULL COMMENT ' 备注',
  `id` varchar(64) NOT NULL COMMENT 'id : id',
  `name` varchar(64) DEFAULT NULL COMMENT '名称 : 名称',
  `mobile_number` varchar(20) DEFAULT NULL COMMENT '手机号码 : 手机号码',
  `brand` varchar(20) DEFAULT NULL COMMENT '商标',
  `typ` varchar(20) DEFAULT NULL COMMENT '类别',
  `hid` varchar(64) NOT NULL COMMENT '日报id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日报联系人';

