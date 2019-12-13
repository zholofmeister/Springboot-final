/*
Navicat MySQL Data Transfer

Source Server         : DB
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : tsgl

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-12-13 14:12:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin_info`
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `admin_id` varchar(20) COLLATE utf8_bin NOT NULL,
  `admin_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `admin_pwd` varchar(20) COLLATE utf8_bin NOT NULL,
  `admin_phone` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES ('admin', 'tourist', 'asd', '123');

-- ----------------------------
-- Table structure for `book_info`
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info` (
  `book_id` varchar(20) COLLATE utf8_bin NOT NULL,
  `book_writer` varchar(30) COLLATE utf8_bin NOT NULL,
  `book_presum` int(11) NOT NULL,
  `book_boss` varchar(20) COLLATE utf8_bin NOT NULL,
  `book_nowsum` int(11) NOT NULL,
  `book_name` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES ('2016000004', '杨坚华', '19', '人民出版社', '19', '遇见德国');
INSERT INTO `book_info` VALUES ('2016000054', '夏可君', '23', ' 中国青年出版社', '23', '生命是最好的礼物');
INSERT INTO `book_info` VALUES ('2016000070', '奥布赖恩', '20', '人民邮电出版社', '20', '管理信息系统');
INSERT INTO `book_info` VALUES ('2016001142', '人民日报社评论部', '11', '人民出版社', '11', '任仲平十年精选');
INSERT INTO `book_info` VALUES ('2016001457', '赵志新', '13', '人民邮电出版社', '12', '线性代数同步精讲及练习');
INSERT INTO `book_info` VALUES ('2016002315', '庄恩岳', '6', '浙江人民出版社', '5', '认识你自己');
INSERT INTO `book_info` VALUES ('2016002465', '平克', '5', '浙江人民出版社', '3', '心智探奇');
INSERT INTO `book_info` VALUES ('2016002469', '张吾渝', '8', '中国建材工业出版社', '8', '黄土工程');
INSERT INTO `book_info` VALUES ('2016003551', '《建筑工程管理与实务》 编委会', '4', '中国建材工业出版社', '4', '建筑工程管理与实务');
INSERT INTO `book_info` VALUES ('2016011152', '张加强', '12', '浙江人民出版社', '12', '太湖传');
INSERT INTO `book_info` VALUES ('2016016599', '刘醒龙', '9', '中国青年出版社', '9', '刘醒龙墨迹');

-- ----------------------------
-- Table structure for `borrow`
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `stu_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `pre_time` date NOT NULL,
  `stu_id` varchar(20) COLLATE utf8_bin NOT NULL,
  `book_id` varchar(20) COLLATE utf8_bin NOT NULL,
  `status` int(11) NOT NULL,
  `unique_id` int(11) NOT NULL AUTO_INCREMENT,
  `nxt_time` date DEFAULT NULL,
  `allowed_days` int(11) NOT NULL,
  `book_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `stu_sex` varchar(10) COLLATE utf8_bin NOT NULL,
  `more_time` int(11) NOT NULL,
  PRIMARY KEY (`unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('zholof', '2019-11-11', '123', '2016001457', '0', '36', null, '10', '线性代数同步精讲及练习', '女', '0');
INSERT INTO `borrow` VALUES ('zholof', '2019-12-11', '123', '2016002465', '0', '37', null, '134', '心智探奇', '女', '0');
INSERT INTO `borrow` VALUES ('zzy', '2019-08-25', '189050741', '2016002465', '0', '38', null, '127', '心智探奇', '男', '0');
INSERT INTO `borrow` VALUES ('zzy', '2019-12-11', '189050741', '2016002315', '0', '39', null, '100', '认识你自己', '男', '0');

-- ----------------------------
-- Table structure for `stu_info`
-- ----------------------------
DROP TABLE IF EXISTS `stu_info`;
CREATE TABLE `stu_info` (
  `stu_name` varchar(10) COLLATE utf8_bin NOT NULL,
  `stu_id` varchar(20) COLLATE utf8_bin NOT NULL,
  `stu_pro` varchar(50) COLLATE utf8_bin NOT NULL,
  `stu_sex` varchar(2) COLLATE utf8_bin NOT NULL,
  `stu_age` int(11) NOT NULL,
  `stu_phone` varchar(11) COLLATE utf8_bin NOT NULL,
  `stu_pwd` varchar(8) COLLATE utf8_bin NOT NULL,
  `stu_sum` int(11) NOT NULL,
  `stu_limit` int(11) NOT NULL,
  `stu_allowed` int(11) NOT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of stu_info
-- ----------------------------
INSERT INTO `stu_info` VALUES ('zholof', '123', '软工', '女', '19', '1234567', '123', '2', '10', '120');
INSERT INTO `stu_info` VALUES ('zzy', '189050741', '计科', '男', '19', '123456', '123456', '2', '5', '100');
