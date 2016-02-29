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

INSERT INTO `sys_area` VALUES ('86b582840f964abfbb6f77e709914330', '9bd01dc186674aa9a46fd0f2d90870b1', '0,1,9bd01dc186674aa9a46fd0f2d90870b1,', '深圳市', 30, '120755', '3', '1', '2016-2-28 00:02:41', '1', '2016-2-28 00:02:41', '', '0');

INSERT INTO `sys_dict` VALUES ('48cbee22226243f18dff34c95e02a5b2', 'C', 'C', 'jy_follwer_level', 'C', 30, '0', '1', '2016-2-27 21:40:12', '1', '2016-2-27 21:40:12', '', '0');
INSERT INTO `sys_dict` VALUES ('7a5f728d015c41fdb7f98b5ce4e0a79c', 'B', 'B', 'jy_follwer_level', 'B', 20, '0', '1', '2016-2-27 21:39:58', '1', '2016-2-27 21:39:58', '', '0');
INSERT INTO `sys_dict` VALUES ('4e8e977a5a5640269092f60b9f687c9a', 'A', 'A', 'jy_follwer_level', 'A', 10, '0', '1', '2016-2-27 21:39:43', '1', '2016-2-27 21:39:43', '', '0');
INSERT INTO `sys_dict` VALUES ('c69dbc53a19c47d2b2dbdd094c4fdba5', '1', '直接', 'jy_follwer_type', '直接', 20, '0', '1', '2016-2-27 21:37:43', '1', '2016-2-27 21:37:43', '', '0');
INSERT INTO `sys_dict` VALUES ('2ea6d29573374379babb06a8774f20b9', '0', '代理', 'jy_follwer_type', '代理', 10, '0', '1', '2016-2-27 21:37:18', '1', '2016-2-27 21:37:18', '', '0');

INSERT INTO `sys_menu` VALUES ('d76e6ce94fe141339eb5af61f47d3a61', '41e70673167f4a5da8af5bff911aa08d', '0,1,62,63,41e70673167f4a5da8af5bff911aa08d,', '超级查看', 90, '', '', '', '0', 'jy:jyCustomer:superView', '1', '2016-2-29 16:04:52', '1', '2016-2-29 16:04:52', '', '0');
INSERT INTO `sys_menu` VALUES ('22ece6dfab764fd894228f065fccc4ad', '41e70673167f4a5da8af5bff911aa08d', '0,1,62,63,41e70673167f4a5da8af5bff911aa08d,', '编辑', 60, '', '', '', '0', 'jy:jyCustomer:edit', '1', '2016-2-25 10:37:16', '1', '2016-2-25 10:37:58', '', '0');
INSERT INTO `sys_menu` VALUES ('f29f71fca8b9444d9aff5d232a36d15e', '41e70673167f4a5da8af5bff911aa08d', '0,1,62,63,41e70673167f4a5da8af5bff911aa08d,', '查看', 30, '', '', '', '0', 'jy:jyCustomer:view', '1', '2016-2-25 10:36:56', '1', '2016-2-25 10:36:56', '', '0');
INSERT INTO `sys_menu` VALUES ('41e70673167f4a5da8af5bff911aa08d', '63', '0,1,62,63,', '客户进度', 360, '/jy/jyCustomer', '', '', '1', '', '1', '2016-2-25 10:36:14', '1', '2016-2-25 10:36:14', '', '0');


INSERT INTO `sys_office` VALUES ('0115f4cf59ea4ef782387f275b79cda7', '0', '0,', '深圳京远', 30, '86b582840f964abfbb6f77e709914330', '12075501', '1', '1', '', '', '', '', '', '', '1', '', '', '1', '2016-2-28 00:03:36', '1', '2016-2-28 00:03:36', '', '0');
INSERT INTO `sys_office` VALUES ('963558482f074aa8ab54f9edd2c7f65e', '0115f4cf59ea4ef782387f275b79cda7', '0,0115f4cf59ea4ef782387f275b79cda7,', '综合部', 30, '86b582840f964abfbb6f77e709914330', '1207550101', '2', '2', '', '', '', '', '', '', '1', '', '', '1', '2016-2-28 00:03:36', '1', '2016-2-28 00:03:59', '', '0');

INSERT INTO `sys_role` VALUES ('387ddb34fb69410f86c891b3ac74141c', '0115f4cf59ea4ef782387f275b79cda7', '京远管理员', 'jy_a', 'assignment', '3', '1', '1', '1', '2016-2-29 16:08:34', '1', '2016-2-29 16:41:35', '', '0');
INSERT INTO `sys_role` VALUES ('f71b5f970ea24b6db8662523cb213011', '0115f4cf59ea4ef782387f275b79cda7', '京远用户', 'jy_d', 'assignment', '3', '1', '1', '1', '2016-2-29 16:09:22', '1', '2016-2-29 16:41:50', '', '0');



INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '1');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '13');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '2');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '20');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '21');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '22');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '22ece6dfab764fd894228f065fccc4ad');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '27');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '28');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '29');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '30');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '41e70673167f4a5da8af5bff911aa08d');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '62');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', '63');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', 'd76e6ce94fe141339eb5af61f47d3a61');
INSERT INTO `sys_role_menu` VALUES ('387ddb34fb69410f86c891b3ac74141c', 'f29f71fca8b9444d9aff5d232a36d15e');
INSERT INTO `sys_role_menu` VALUES ('f71b5f970ea24b6db8662523cb213011', '1');
INSERT INTO `sys_role_menu` VALUES ('f71b5f970ea24b6db8662523cb213011', '22ece6dfab764fd894228f065fccc4ad');
INSERT INTO `sys_role_menu` VALUES ('f71b5f970ea24b6db8662523cb213011', '27');
INSERT INTO `sys_role_menu` VALUES ('f71b5f970ea24b6db8662523cb213011', '28');
INSERT INTO `sys_role_menu` VALUES ('f71b5f970ea24b6db8662523cb213011', '29');
INSERT INTO `sys_role_menu` VALUES ('f71b5f970ea24b6db8662523cb213011', '30');
INSERT INTO `sys_role_menu` VALUES ('f71b5f970ea24b6db8662523cb213011', '41e70673167f4a5da8af5bff911aa08d');
INSERT INTO `sys_role_menu` VALUES ('f71b5f970ea24b6db8662523cb213011', '62');
INSERT INTO `sys_role_menu` VALUES ('f71b5f970ea24b6db8662523cb213011', '63');
INSERT INTO `sys_role_menu` VALUES ('f71b5f970ea24b6db8662523cb213011', 'f29f71fca8b9444d9aff5d232a36d15e');
