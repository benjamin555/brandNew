ALTER TABLE `bg_daily`
ADD COLUMN `agent_job`  varchar(1000) NULL DEFAULT '代理事项' AFTER `tomorrow_plan`;
CREATE TABLE `bg_daily_customer` (
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '删除标识',
  `remarks` varchar(255) DEFAULT NULL COMMENT ' 备注',
  `id` varchar(64) NOT NULL COMMENT 'id : id',
 `customer_remark` varchar(20) DEFAULT NULL COMMENT '意向客户情况',
  `hid` varchar(64) NOT NULL COMMENT '日报id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='意向客户';



