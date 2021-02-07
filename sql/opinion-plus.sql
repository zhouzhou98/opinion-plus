/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : opinion-plus

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2021-02-07 08:02:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kid` bigint(20) NOT NULL COMMENT '舆情关键字id',
  `content` longtext COMMENT '舆情内容',
  `posOrNeg` int(11) DEFAULT NULL COMMENT '舆情正负面标志 1代表证明 0代表负面',
  `reason` varchar(100) DEFAULT NULL COMMENT '文本检测结果，有内容的把结果显示 ，没有的就写个 无',
  `href` text COMMENT '地址链接',
  `grade` int(11) DEFAULT NULL COMMENT '预警等级 1代表低 2代表中低 3代表中 4代表中高 5代表高',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `area` varchar(20) DEFAULT NULL COMMENT '地域 如广东',
  `author` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `B_key` (`area`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of blog
-- ----------------------------

-- ----------------------------
-- Table structure for `forward`
-- ----------------------------
DROP TABLE IF EXISTS `forward`;
CREATE TABLE `forward` (
  `id` bigint(20) NOT NULL,
  `bid` bigint(20) NOT NULL COMMENT '微博id',
  `forwarding` int(11) NOT NULL DEFAULT '0' COMMENT '转发数量',
  `praise` int(11) NOT NULL DEFAULT '0' COMMENT '点赞',
  `comment` int(11) NOT NULL COMMENT '评论',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of forward
-- ----------------------------

-- ----------------------------
-- Table structure for `keyword`
-- ----------------------------
DROP TABLE IF EXISTS `keyword`;
CREATE TABLE `keyword` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `kname` varchar(100) NOT NULL COMMENT '舆情关键字',
  `description` varchar(200) DEFAULT NULL COMMENT '舆情关键字描述',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `dataVersion` int(2) DEFAULT '0' COMMENT '版本号',
  `isOpen` int(2) DEFAULT '0' COMMENT '是否开启舆情预警',
  PRIMARY KEY (`id`),
  KEY `K_key` (`kname`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of keyword
-- ----------------------------

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `url` varchar(600) DEFAULT NULL COMMENT '请求响应路径',
  `params` varchar(4095) DEFAULT NULL COMMENT '请求参数',
  `staus` int(2) DEFAULT NULL COMMENT '访问状态',
  `message` mediumtext COMMENT '异常信息',
  `method` varchar(20) DEFAULT NULL COMMENT '请求方法',
  `time` bigint(20) DEFAULT NULL COMMENT '响应时间',
  `ip` varchar(60) DEFAULT NULL COMMENT '请求ip',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `L_KEY` (`url`(191)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) NOT NULL COMMENT '用户名',
  `email` varchar(100) NOT NULL COMMENT '用户邮箱',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '用户状态 1代表管理员 0代表普通用户',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  `dataVersion` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `U_KEY` (`username`,`password`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
