/*
MySQL Backup
Source Server Version: 5.6.21
Source Database: druid
Date: 2016/3/4 16:56:26
*/

CREATE DATABASE /*!32312 IF NOT EXISTS*/`druid` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `druid`;
-- ----------------------------
--  Table structure for `baby`
-- ----------------------------
DROP TABLE IF EXISTS `baby`;
CREATE TABLE `baby` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `height` float(200,2) DEFAULT NULL,
  `weight` float(200,2) DEFAULT NULL,
  `head` float(200,2) DEFAULT NULL,
  `recode_date` datetime DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `operator` varchar(200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modifitor` varchar(200) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(200) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) DEFAULT NULL,
  `sex` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `menu_id` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='权限关联表';

-- ----------------------------
--  Table structure for `standard`
-- ----------------------------
DROP TABLE IF EXISTS `standard`;
CREATE TABLE `standard` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `recode_date` datetime DEFAULT NULL,
  `height` float(200,0) DEFAULT NULL,
  `weight` float(200,0) DEFAULT NULL,
  `head` float(200,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sysmenu`
-- ----------------------------
DROP TABLE IF EXISTS `sysmenu`;
CREATE TABLE `sysmenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(20) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `parentId` int(11) NOT NULL DEFAULT '0' COMMENT '父级菜单ID 0表示根节点',
  `sequence` int(6) NOT NULL DEFAULT '0' COMMENT '菜单顺序',
  `iconCls` varchar(20) NOT NULL DEFAULT '' COMMENT '菜单图标样式',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '菜单链接地址 总是以‘/’开头，相对于项目根路径',
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`),
  KEY `sequence` (`sequence`)
) ENGINE=InnoDB AUTO_INCREMENT=3031 DEFAULT CHARSET=utf8 COMMENT='后台菜单表';

-- ----------------------------
--  Table structure for `sysrole`
-- ----------------------------
DROP TABLE IF EXISTS `sysrole`;
CREATE TABLE `sysrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL DEFAULT '' COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='后台角色表';

-- ----------------------------
--  Table structure for `sysuser`
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '登录密码',
  `email` varchar(30) DEFAULT NULL COMMENT '邮件',
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
--  Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `roleId` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='后台角色与用户关联表';

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `baby` VALUES ('1','50.00','6.10','30.00','2015-11-10 11:15:00','出生','daddy','2016-03-03 13:58:00','daddy','2016-03-03 13:58:00'),  ('2','51.00','6.20','31.00','2016-03-03 21:47:24','1周','daddy','2016-03-03 13:58:04',NULL,'2016-03-03 22:13:22'),  ('3','64.00','7.00','33.00','2016-03-03 21:38:10','22','momy',NULL,NULL,'2016-03-03 22:14:01');
INSERT INTO `person` VALUES ('1','张三','男'),  ('2','李四','男');
INSERT INTO `role_menu` VALUES ('1','1','10'),  ('2','1','20'),  ('3','1','1010'),  ('4','1','1020'),  ('5','1','2010'),  ('6','2','10'),  ('7','2','1010'),  ('8','2','1020'),  ('9','1','30'),  ('10','1','3010'),  ('11','1','3020'),  ('12','1','3030');
INSERT INTO `sysmenu` VALUES ('10','系统管理','0','10','','http://www.baidu.com'),  ('20','用户管理','0','20','','/user/list'),  ('30','宝贝成长管理','0','30','','/baby/list'),  ('1010','系统管理1','10','1010','','http://www.baidu.com'),  ('1020','系统管理2','10','1020','','http://www.baidu.com'),  ('2010','用户管理1','20','2010','','http://www.baidu.com'),  ('3010','身高管理','30','3010','','/baby/height'),  ('3020','体重管理','30','3020','','/baby/weight'),  ('3030','头围管理','30','3030','','/baby/head');
INSERT INTO `sysrole` VALUES ('1','超级管理员'),  ('2','员工');
INSERT INTO `sysuser` VALUES ('1','admin','admin',NULL),  ('2','tony','tony',NULL),  ('3','test1','test1',NULL),  ('4','test2','test2',NULL),  ('5','test3','test3','sdasda@163.com'),  ('6','test4','test4','322@qq.com'),  ('7','test5','test5',NULL),  ('8','test6','test6',NULL),  ('9','test7','test7',NULL),  ('10','test8','test8','11@qq.com'),  ('11','test9','test9',NULL),  ('12','qqqq','','11@qq.com'),  ('13','asdasdsa','','11@qq.com'),  ('14','test9','',NULL),  ('15','test11','',NULL),  ('16','test12','',NULL);
INSERT INTO `user_role` VALUES ('1','1','1'),  ('2','2','2');
