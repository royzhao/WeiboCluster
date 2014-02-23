/*
Navicat MySQL Data Transfer

Source Server         : zpl
Source Server Version : 50523
Source Host           : localhost:3306
Source Database       : weiboinfo

Target Server Type    : MYSQL
Target Server Version : 50523
File Encoding         : 65001

Date: 2014-02-23 17:19:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `doc`
-- ----------------------------
DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc` (
  `DocText` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `DocID` int(20) NOT NULL,
  PRIMARY KEY (`DocID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of doc
-- ----------------------------

-- ----------------------------
-- Table structure for `lexcion`
-- ----------------------------
DROP TABLE IF EXISTS `lexcion`;
CREATE TABLE `lexcion` (
  `Lexicon` tinytext NOT NULL,
  `LexiconID` int(20) NOT NULL,
  `IDF` double(20,0) NOT NULL,
  PRIMARY KEY (`LexiconID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of lexcion
-- ----------------------------

-- ----------------------------
-- Table structure for `postingfile`
-- ----------------------------
DROP TABLE IF EXISTS `postingfile`;
CREATE TABLE `postingfile` (
  `ListID` int(20) NOT NULL AUTO_INCREMENT,
  `LexiconID` int(20) NOT NULL,
  PRIMARY KEY (`ListID`),
  KEY `F_L_ID` (`LexiconID`),
  CONSTRAINT `F_L_ID` FOREIGN KEY (`LexiconID`) REFERENCES `lexcion` (`LexiconID`)
) ENGINE=InnoDB AUTO_INCREMENT=14934 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of postingfile
-- ----------------------------

-- ----------------------------
-- Table structure for `postingitem`
-- ----------------------------
DROP TABLE IF EXISTS `postingitem`;
CREATE TABLE `postingitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tf` int(20) DEFAULT NULL,
  `lexiconID` int(20) DEFAULT NULL,
  `docID` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `LexID_F` (`lexiconID`),
  KEY `DocID_F` (`docID`),
  CONSTRAINT `DocID_F` FOREIGN KEY (`docID`) REFERENCES `doc` (`DocID`),
  CONSTRAINT `LexID_F` FOREIGN KEY (`lexiconID`) REFERENCES `postingfile` (`LexiconID`)
) ENGINE=InnoDB AUTO_INCREMENT=38662 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of postingitem
-- ----------------------------
