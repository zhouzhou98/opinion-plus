-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: rm-bp1282ovup9z9k9js.mysql.rds.aliyuncs.com    Database: email_manager_db
-- ------------------------------------------------------
-- Server version	5.7.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(120) NOT NULL COMMENT '邮件组邮箱或者用户组组名',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `uid` bigint(20) NOT NULL COMMENT '创建邮件组的用户id',
  `type` int(2) NOT NULL COMMENT '组类类别0代表邮件组 1代表用户组',
  `isDeleted` int(2) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `dataVersion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `group_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (31,'support-group@heiniugo.com','黑牛购support组',68,0,0,'2021-02-07 16:10:44','2021-02-07 16:10:44',0),(32,'黑牛开发者&&支付组',NULL,68,1,0,'2021-02-07 16:38:40','2021-02-07 17:04:29',2),(33,'kuan-test',NULL,68,1,0,'2021-02-07 17:19:57','2021-02-07 17:19:57',0);
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leader`
--

DROP TABLE IF EXISTS `leader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leader` (
  `name` varchar(50) NOT NULL DEFAULT '',
  `dataVersion` int(10) NOT NULL,
  `heartbeat` datetime NOT NULL,
  `leaderName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `leaderName` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leader`
--

LOCK TABLES `leader` WRITE;
/*!40000 ALTER TABLE `leader` DISABLE KEYS */;
INSERT INTO `leader` VALUES ('TIMER_UPDATE',2950,'2021-02-07 17:40:24','email-manager-0-deployment-688b866cdc-2mv2w');
/*!40000 ALTER TABLE `leader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `mid` bigint(20) DEFAULT NULL COMMENT '邮件id',
  `operation` varchar(60) DEFAULT NULL COMMENT '操作内容',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `LOG_DB` (`uid`,`mid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail`
--

DROP TABLE IF EXISTS `mail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mid` bigint(20) DEFAULT NULL COMMENT '邮箱id',
  `theme` varchar(600) DEFAULT NULL COMMENT '邮箱主题',
  `content` mediumtext COMMENT '邮箱内容',
  `sendMail` varchar(150) DEFAULT NULL,
  `sender` varchar(100) DEFAULT NULL COMMENT '发送者',
  `receiver` text COMMENT '收件人',
  `sendTime` datetime DEFAULT NULL COMMENT '发送时间',
  `state` int(2) DEFAULT NULL COMMENT '处理状态',
  `isAttach` int(2) DEFAULT NULL,
  `isDeleted` int(2) DEFAULT NULL COMMENT '逻辑删除',
  `dataVersion` int(2) DEFAULT NULL,
  `isLoading` tinyint(1) DEFAULT NULL,
  `outStream` longtext,
  `uid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `db_email` (`sender`,`sendMail`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1385 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail`
--

LOCK TABLES `mail` WRITE;
/*!40000 ALTER TABLE `mail` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mailbox`
--

DROP TABLE IF EXISTS `mailbox`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mailbox` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `description` varchar(500) DEFAULT NULL COMMENT '邮箱描绘',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '创建时间',
  `dataVersion` int(11) NOT NULL COMMENT '版本号',
  `isDeleted` int(2) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL COMMENT '邮箱密码',
  `uid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `db_mailbox` (`email`,`password`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mailbox`
--

LOCK TABLES `mailbox` WRITE;
/*!40000 ALTER TABLE `mailbox` DISABLE KEYS */;
INSERT INTO `mailbox` VALUES (106,'tech@jodoinc.com','jodoinc开发者邮箱','2021-02-07 16:00:29','2021-02-07 16:00:29',0,0,'OEpXM2ozMzMyWjZheTRkNA==',68),(107,'tech@smaltblue.com','天津巨蓝开发者邮箱','2021-02-07 16:01:21','2021-02-07 16:01:21',0,0,'N2YxOWFlZjUySEsmYjRjMjQ4MTE=',68),(108,'tech@tappile.com','寰宇开发者邮箱','2021-02-07 16:01:53','2021-02-07 16:01:53',0,0,'RE9ETzF4c3plUWZlSEsxYVd4WFRyTTNicw==',68),(109,'tech@gamigames.com','gami开发者邮箱','2021-02-07 16:02:23','2021-02-07 16:02:23',0,0,'YWE0YzYzMDNISyY1ZTEwNDUyNzU0',68),(110,'support@heiniugo.com','support 黑牛购','2021-02-07 16:10:19','2021-02-07 16:10:19',0,0,'Sm9kbzAwMDA=',68),(111,'tech_pay@tappile.com','寰宇支付邮箱','2021-02-07 16:40:22','2021-02-07 16:40:22',0,0,'RE9ET3plUWZlSEsxYVd4WFRyTTNic2Rvbmc=',68),(112,'tech_pay@smaltblue.com','天津巨蓝支付邮箱','2021-02-07 16:40:45','2021-02-07 16:40:45',0,0,'M1RFcGp5akZab2N5U0pENQ==',68),(113,'tech_pay@gamigames.com','gami支付邮箱','2021-02-07 16:41:07','2021-02-07 16:41:07',0,0,'OTZ0NmlVNDJ6MzI5NDZpZA==',68),(114,'tech_pay@jodoinc.com','jodoinc支付邮箱','2021-02-07 16:41:33','2021-02-07 16:41:33',0,0,'UDg1WjRaQ29IdDIxNjZUMw==',68);
/*!40000 ALTER TABLE `mailbox` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `part` varchar(60) NOT NULL COMMENT '角色名称',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  `dataVersion` int(11) NOT NULL COMMENT '版本号',
  `isDeleted` int(2) NOT NULL COMMENT '逻辑删除，判断是否删除',
  PRIMARY KEY (`id`),
  KEY `db_part` (`part`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rule`
--

DROP TABLE IF EXISTS `rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mid` bigint(20) DEFAULT NULL,
  `uid` bigint(20) DEFAULT NULL COMMENT '创建者用户id',
  `theme` varchar(100) DEFAULT NULL COMMENT '主题',
  `content` varchar(100) DEFAULT NULL COMMENT '内容',
  `sendMail` varchar(80) DEFAULT NULL COMMENT '发送邮箱',
  `receiver` varchar(100) DEFAULT NULL COMMENT '接受者',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `dataVersion` int(11) DEFAULT NULL COMMENT '版本号',
  `isDeleted` int(2) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `RULE_DB` (`theme`,`content`,`sendMail`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule`
--

LOCK TABLES `rule` WRITE;
/*!40000 ALTER TABLE `rule` DISABLE KEYS */;
INSERT INTO `rule` VALUES (34,114,68,'黑牛','黑牛',NULL,NULL,'2021-02-07 16:42:27','2021-02-07 17:02:44',2,0),(35,113,68,'黑牛','黑牛',NULL,NULL,'2021-02-07 16:43:03','2021-02-07 17:02:37',1,0),(36,111,68,'黑牛','黑牛',NULL,NULL,'2021-02-07 16:43:24','2021-02-07 17:02:30',1,0),(37,109,68,'黑牛','黑牛',NULL,NULL,'2021-02-07 16:45:22','2021-02-07 17:02:22',1,0),(38,108,68,'黑牛','黑牛',NULL,NULL,'2021-02-07 16:45:39','2021-02-07 17:02:13',1,0),(39,107,68,'黑牛','黑牛',NULL,NULL,'2021-02-07 16:45:54','2021-02-07 17:02:05',1,0),(40,106,68,'黑牛','黑牛',NULL,NULL,'2021-02-07 16:46:09','2021-02-07 17:01:45',1,0);
/*!40000 ALTER TABLE `rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rule_group`
--

DROP TABLE IF EXISTS `rule_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) DEFAULT NULL COMMENT '规则id',
  `gid` bigint(20) DEFAULT NULL COMMENT '用户组id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `MAILBOX_GROUP_DB` (`rid`,`gid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule_group`
--

LOCK TABLES `rule_group` WRITE;
/*!40000 ALTER TABLE `rule_group` DISABLE KEYS */;
INSERT INTO `rule_group` VALUES (158,34,32),(157,35,32),(156,36,32),(155,37,32),(154,38,32),(153,39,32),(152,40,32);
/*!40000 ALTER TABLE `rule_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` int(11) NOT NULL DEFAULT '0',
  `accessToken` text NOT NULL,
  `dataVersion` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (0,'7zyEBMzALe6aD2Z1fpj7yrm5QtGkl3PdJXUPGXPKhj-rR0fYhHEc_w79xMZZa5T81FTAiALDsypV5lyKiFojUONZg2qTU4dHaO1ehMnGkBBOWBKAK0gULQF6SF5aMga40XEtQin-7JwX1HVEAGFDvp3i33eqQgZJsPZhQ0NJtq5vAkQIUx2cCoktj5rvwxKU8Ic7bWMR9WNXQCjWxBzyUw',1,'2021-02-05 16:21:21','2021-02-07 16:17:47');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleId` bigint(20) NOT NULL COMMENT '角色id',
  `realName` varchar(60) DEFAULT NULL,
  `wechat` varchar(100) DEFAULT NULL COMMENT '企业微信id',
  `username` varchar(60) NOT NULL COMMENT '用户名',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  `dataVersion` int(11) NOT NULL COMMENT '版本号',
  `isDeleted` int(2) NOT NULL COMMENT '逻辑删除，判断是否删除',
  `uid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `db_username` (`realName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'苏毓洲','jack.su@jodoinc.com','suyuzhou','jack.su@jodoinc.com','2021-02-02 09:35:51','2021-02-02 16:53:08',2,0,0),(68,1,'王海宽','qy01d5acba9f01950028fa2f762a','wanghaikuan','cris.wang@jodoinc.com','2021-02-05 16:21:45','2021-02-05 16:21:45',0,0,1),(69,1,'张穗文','ZhangSuiWen','zhangsuiwen','treeman@jodoinc.com','2021-02-05 16:22:05','2021-02-05 16:22:05',0,0,1),(70,1,'陈超荣','qy01d1acfb9f2d9500280f2c6f13','chenchaorong','rokie.chen@jodoinc.com','2021-02-05 16:23:14','2021-02-05 16:23:14',0,0,1),(71,2,'董嘉茵','qy01ecac969f2e9500286bd87046','dongjiayin','dongua@jodoinc.com','2021-02-05 16:23:46','2021-02-05 16:23:46',0,0,1),(72,0,'黄剑基','duncan.huang@jodoinc.com','huangjianji','duncan@jodoinc.com','2021-02-05 16:24:46','2021-02-07 17:01:44',2,0,68),(73,2,'黄艺丹','edan.huang@jodoinc.com','huangyidan','edan.huang@jodoinc.com','2021-02-05 16:25:11','2021-02-05 16:25:11',0,0,1),(74,2,'纪伊均','evelyn.ji@jodoinc.com','jiyijun','evelyn.ji@jodoinc.com','2021-02-05 16:25:44','2021-02-05 16:25:44',0,0,1),(75,1,'梁嘉俊','jaron.liang@jodoinc.com','liangjiajun','jaron.liang@jodoinc.com','2021-02-05 16:26:31','2021-02-05 16:26:31',0,0,1),(76,2,'梁露琪','qy01b4acef9f13950228c3d44962','liangluqi','adelle.liang@jodoinc.com','2021-02-05 16:27:01','2021-02-05 16:27:01',0,0,1),(77,2,'梁振言','walking.liang@jodoinc.com','liangzhenyan','walking.liang@jodoinc.com','2021-02-05 16:27:36','2021-02-05 16:27:36',0,0,1),(78,2,'李坤捷','qy014fac739fc29502284b512adc','likunjie','queenie.li@jodoinc.com','2021-02-05 16:28:07','2021-02-05 16:28:07',0,0,1),(79,1,'林秋明','qy01c6acb19f0b950028d91637d5','linqiuming','kim@jodoinc.com','2021-02-05 16:28:34','2021-02-05 16:28:34',0,0,1),(80,2,'林湧彬','qy01bcac9b9f19950028cf8597c2','linyongbin','blackey.lin@jodoinc.com','2021-02-05 16:29:15','2021-02-05 16:29:15',0,0,1),(81,2,'罗晓文','kitty.luo@jodoinc.com','luoxiaowen','kitty.luo@jodoinc.com','2021-02-05 16:29:43','2021-02-05 16:29:43',0,0,1),(82,1,'罗忠铭','qy01baacac9f97950128db64753a','luozhongming','jomin.luo@jodoinc.com','2021-02-05 16:30:34','2021-02-05 16:30:34',0,0,1),(83,2,'潘佳柳','dora.pan@jodoinc.com','panjialiu','dora.pan@jodoinc.com','2021-02-05 16:30:57','2021-02-06 13:00:35',2,0,1),(84,2,'吴晓亚','yogi.wu@jodoinc.com','wuxiaoya','yogi.wu@hbgame.net','2021-02-05 16:31:51','2021-02-05 16:31:51',0,0,1),(85,2,'巫燕菲','qy0108ac3b9f049500281645a044','wuyanfei','candy.wu@jodoinc.com','2021-02-05 16:32:34','2021-02-05 16:32:34',0,0,1),(86,1,'张相睿','qy01acac729f0f950028d1260fa1','zhangxiangrui','luke.zhang@jodoinc.com','2021-02-05 16:33:03','2021-02-05 16:33:03',0,0,1),(87,1,'仇家伟','qy015dac269f3a95002830bf4fc7','qiujiawei1','jiawei.qiu@jodoinc.com','2021-02-05 16:35:19','2021-02-05 16:36:33',2,0,1),(88,0,'lifucheng','姓名有误，请重新填写','lifucheng','lifucheng@jodogame.com','2021-02-07 16:19:03','2021-02-07 16:59:46',3,1,68),(89,0,'huangjianji','姓名有误，请重新填写','huangjianji','duncan.huang@jodoinc.com','2021-02-07 16:21:12','2021-02-07 16:59:44',1,1,68),(90,0,'wuxiaoya','姓名有误，请重新填写','wuxiaoya','yogi.wu@jodoinc.com','2021-02-07 16:35:29','2021-02-07 16:38:53',1,1,68),(91,0,'zhongbishu','姓名有误，请重新填写','zhongbishu','nicole.zhong@hbgame.net','2021-02-07 16:37:51','2021-02-07 16:59:41',1,1,68),(92,0,'李富铖','qy01beac869f08950028cd37bf51','lifucheng','lee.li@jodoinc.com','2021-02-07 17:00:57','2021-02-07 17:00:57',0,0,68),(93,0,'钟碧舒','nicole.zhong@hbgame.net','zhongbishu','nicole.zhong@hbgame.net','2021-02-07 17:03:51','2021-02-07 17:03:51',0,0,68);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `gid` bigint(20) NOT NULL COMMENT '用户组id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_GROUP` (`uid`,`gid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (105,68,33),(102,72,32),(101,84,32),(103,92,32),(104,93,32);
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_mail`
--

DROP TABLE IF EXISTS `user_mail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_mail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `mid` bigint(20) DEFAULT NULL COMMENT '邮件id',
  `type` int(2) DEFAULT NULL COMMENT '0代表普通 1代表负责人 2代表业务管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1750 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_mail`
--

LOCK TABLES `user_mail` WRITE;
/*!40000 ALTER TABLE `user_mail` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_mail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_rule`
--

DROP TABLE IF EXISTS `user_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `rid` bigint(20) DEFAULT NULL COMMENT '规则id',
  `type` int(2) DEFAULT NULL COMMENT '类型 1代表转发 0代表标记负责人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_rule`
--

LOCK TABLES `user_rule` WRITE;
/*!40000 ALTER TABLE `user_rule` DISABLE KEYS */;
INSERT INTO `user_rule` VALUES (217,92,40,1),(218,92,39,1),(219,92,38,1),(220,92,37,1),(221,92,36,1),(222,92,35,1),(223,92,34,1);
/*!40000 ALTER TABLE `user_rule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-07 17:40:33
