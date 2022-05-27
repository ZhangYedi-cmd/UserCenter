/*
 Navicat Premium Data Transfer

 Source Server         : Mydata
 Source Server Type    : MySQL
 Source Server Version : 50650
 Source Host           : localhost:3306
 Source Schema         : usercenter

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 27/05/2022 09:57:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户id\n',
  `username` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名\n',
  `nickName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称\n',
  `userAccount` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号\n',
  `userPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码\n',
  `gender` tinyint(4) NULL DEFAULT 1 COMMENT '性别\n',
  `avatarUrl` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址\n',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码\n',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话\n',
  `userStatus` int(11) NULL DEFAULT 0 COMMENT '用户状态\n',
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间\n',
  `updateTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据更新时间\n',
  `isDelete` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `salt` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐\n',
  `role` int(255) NOT NULL DEFAULT 1 COMMENT '用户类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
