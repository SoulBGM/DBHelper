/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-08-21 15:08:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `grade_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(30) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_no` varchar(40) COLLATE utf8_bin NOT NULL,
  `login_pwd` varchar(30) COLLATE utf8_bin NOT NULL,
  `grade_id` int(1) NOT NULL,
  `student_name` varchar(40) COLLATE utf8_bin NOT NULL DEFAULT '',
  `sex` varchar(4) COLLATE utf8_bin NOT NULL,
  `phone` varchar(13) COLLATE utf8_bin NOT NULL DEFAULT '',
  `address` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `born_date` datetime DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`student_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
