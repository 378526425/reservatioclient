/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80100
 Source Host           : 124.220.46.104:3306
 Source Schema         : fast-reservation

 Target Server Type    : MySQL
 Target Server Version : 80100
 File Encoding         : 65001

 Date: 15/11/2023 10:42:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '分类' ROW_FORMAT = DYNAMIC;

INSERT INTO `fast-reservation`.`category` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `name`, `sort`) VALUES (NULL, now(), NULL, now(), 0, 0, '皮肤管理', 1);
INSERT INTO `fast-reservation`.`category` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `name`, `sort`) VALUES (NULL, now(), NULL, now(), 0, 0, 'SPA按摩', 2);
INSERT INTO `fast-reservation`.`category` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `name`, `sort`) VALUES (NULL, now(), NULL, now(), 0, 0, '养生调理', 3);


-- ----------------------------
-- Table structure for fr_user
-- ----------------------------
DROP TABLE IF EXISTS `fr_user`;
CREATE TABLE `fr_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登陆凭证',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户状态 启用/停用/注销',
  `head_portrait` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '昵称',
  `session_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会话密钥',
  `union_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户在开放平台的唯一标识符',
  `lately_time` datetime NULL DEFAULT NULL COMMENT '最近时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '前台用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for msf_config
-- ----------------------------
DROP TABLE IF EXISTS `msf_config`;
CREATE TABLE `msf_config`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设置编号',
  `value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '系统值',
  `descr` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述信息',
  `access` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '访问类型 访问类型 PUBLIC/PRIVATE/INNER',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统配置' ROW_FORMAT = DYNAMIC;

INSERT INTO `fast-reservation`.`msf_config` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `code`, `value`, `descr`, `access`) VALUES (NULL, now(), NULL, NULL, 0, 0, 'HOME_INFO', '{\r\n  \"mainImg\":\"https://www.wxmblog.com/devminio/reservation/2023/09/26/50fb89b6-070f-4328-bf64-a5db5147261e.png\",\r\n  \"preImg\":\"https://www.wxmblog.com/devminio/reservation/2023/09/26/e01ef6b2-f52b-4c0e-bf44-e90cdf2d5974.png\",\r\n  \"title\":\"莱特妮丝\",\r\n  \"time\":\"18182206033、023-65087388\",\r\n  \"location\":\"沙坪坝汉渝路13号附10号\"\r\n}', '首页预览信息', 'INNER');
INSERT INTO `fast-reservation`.`msf_config` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `code`, `value`, `descr`, `access`) VALUES (NULL, now(), NULL, NULL, 0, 0, 'Wx_Template', '[\"Jjvu5i4MqZGtRu09mSA1jlwL1f6U9wGKz0uNu8kJcBs\"]', '首页预览信息', 'PUBLIC');


-- ----------------------------
-- Table structure for msf_file
-- ----------------------------
DROP TABLE IF EXISTS `msf_file`;
CREATE TABLE `msf_file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `original` bit(1) NULL DEFAULT b'1' COMMENT '是否是原图',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件路径',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件状态 TEMP-临时文件 SAVED-已保存的文件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `pre_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '预览图片',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '简介',
  `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号 多个空格隔开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '人员' ROW_FORMAT = DYNAMIC;

INSERT INTO `fast-reservation`.`person` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `name`, `pre_img`, `introduction`, `phone`) VALUES (NULL, now(), NULL, now(), 0, 0, '到店安排', 'https://www.wxmblog.com/devminio/reservation/2023/09/26/a76789d4-4a09-4706-b1db-40ab4f99edab.png', '店铺推荐合适人员', '15902393423');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `pre_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '预览图片',
  `duration` int NOT NULL DEFAULT 90 COMMENT '时长',
  `time_unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '时长单位',
  `info` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件地址',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '简介',
  `category_id` int NOT NULL COMMENT '分类',
  `sort` int NOT NULL DEFAULT 0,
  `price` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '价格',
  `ori_price` decimal(10, 0) NULL DEFAULT NULL COMMENT '原来的价格',
  `main_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主图',
  `status` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_FK`(`category_id` ASC) USING BTREE,
  CONSTRAINT `product_FK` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品' ROW_FORMAT = DYNAMIC;

INSERT INTO `fast-reservation`.`product` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `title`, `pre_img`, `duration`, `time_unit`, `info`, `introduction`, `category_id`, `sort`, `price`, `ori_price`, `main_img`, `status`) VALUES (NULL, now(), NULL, now(), 0, 0, '60分钟面部清洁', 'https://www.wxmblog.com/devminio/reservation/2023/09/26/9ad0a068-9e79-40f5-ab46-2907d81732be.png', 1, 'HOUR', '[\"https://www.wxmblog.com/devminio/reservation/2023/09/26/1ba45c39-46b0-4261-9073-f7e80ae26fed.png\"]', '小气泡清洁', 16, 0, 80, 300, 'https://www.wxmblog.com/devminio/reservation/2023/09/26/b2aad885-7dac-4a8a-887d-aadbd7866e31.png', 'UP');
INSERT INTO `fast-reservation`.`product` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `title`, `pre_img`, `duration`, `time_unit`, `info`, `introduction`, `category_id`, `sort`, `price`, `ori_price`, `main_img`, `status`) VALUES (NULL, now(), NULL, now(), 0, 0, 'A4小腰精', 'https://www.wxmblog.com/devminio/reservation/2023/09/26/1170f563-56af-4972-a01b-87ad6976b1c8.png', 1, 'HOUR', '[\"https://www.wxmblog.com/devminio/reservation/2023/09/26/bbb295d2-482a-4289-851a-09567e0b6d1e.png\"]', '疏通筋络 放松肌肉', 18, 0, 138, 598, 'https://www.wxmblog.com/devminio/reservation/2023/09/26/61e1fc18-fa56-4b2b-875d-94addd758ada.png', 'UP');
INSERT INTO `fast-reservation`.`product` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `title`, `pre_img`, `duration`, `time_unit`, `info`, `introduction`, `category_id`, `sort`, `price`, `ori_price`, `main_img`, `status`) VALUES (NULL, now(), NULL, now(), 0, 0, '超值三次身体塑型', 'https://www.wxmblog.com/devminio/reservation/2023/09/26/50d54137-f8aa-4c38-a8cc-87b8305115e9.png', 1, 'HOUR', '[\"https://www.wxmblog.com/devminio/reservation/2023/09/26/cb9e62c1-c2c3-4441-a8d4-89bdc520b5ab.png\"]', '超值三次身体塑型', 17, 0, 99, 300, 'https://www.wxmblog.com/devminio/reservation/2023/09/26/03bdb560-3790-4f82-a651-ec4f5cfa2689.png', 'UP');
INSERT INTO `fast-reservation`.`product` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `title`, `pre_img`, `duration`, `time_unit`, `info`, `introduction`, `category_id`, `sort`, `price`, `ori_price`, `main_img`, `status`) VALUES (NULL, now(), NULL, now(), 0, 0, '红宝石美眼奢宠SAP', 'https://www.wxmblog.com/devminio/reservation/2023/09/26/92f56558-7f78-4c9e-8642-c0369abb8641.png', 1, 'HOUR', '[\"https://www.wxmblog.com/devminio/reservation/2023/09/26/6283be82-8cb7-481d-9400-43bad37b1a5e.png\"]', '眼部紧致 抗氧化', 17, 0, 99, 398, 'https://www.wxmblog.com/devminio/reservation/2023/09/26/36015482-615a-4351-81c9-78beb794c218.png', 'UP');
INSERT INTO `fast-reservation`.`product` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `title`, `pre_img`, `duration`, `time_unit`, `info`, `introduction`, `category_id`, `sort`, `price`, `ori_price`, `main_img`, `status`) VALUES (NULL, now(), NULL, now(), 0, 0, '肩颈按摩SPA', 'https://www.wxmblog.com/devminio/reservation/2023/09/26/813562ac-e808-4fdc-966b-9cf4981e8d8e.png', 45, 'MINUTE', '[\"https://www.wxmblog.com/devminio/reservation/2023/09/26/7ccb7aeb-02cb-49cb-a57c-2613a2f5089b.png\"]', '按摩部位任选', 17, 0, 48, 300, 'https://www.wxmblog.com/devminio/reservation/2023/09/26/76a9979b-821e-470b-9d48-d6d8a86f9dcc.png', 'UP');
INSERT INTO `fast-reservation`.`product` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `title`, `pre_img`, `duration`, `time_unit`, `info`, `introduction`, `category_id`, `sort`, `price`, `ori_price`, `main_img`, `status`) VALUES (NULL, now(), NULL, now(), 0, 0, '蜜桃臀', 'https://www.wxmblog.com/devminio/reservation/2023/09/26/4ba45645-ed61-41c2-b891-f8909f32a3ef.jpg', 1, 'HOUR', '[\"https://www.wxmblog.com/devminio/reservation/2023/09/26/f3fd0f2f-13fa-4165-90c0-60cb1960839b.png\"]', '蜜桃臀', 18, 0, 199, 400, 'https://www.wxmblog.com/devminio/reservation/2023/09/26/c3b66f71-02d5-41a5-9555-30b3a8edc07e.png', 'UP');
INSERT INTO `fast-reservation`.`product` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `title`, `pre_img`, `duration`, `time_unit`, `info`, `introduction`, `category_id`, `sort`, `price`, `ori_price`, `main_img`, `status`) VALUES (NULL, now(), NULL, now(), 0, 0, '女神三部曲', 'https://www.wxmblog.com/devminio/reservation/2023/09/26/ea765337-d85f-46e1-8ec0-dd4a44eb4d36.png', 60, 'MINUTE', '[\"https://www.wxmblog.com/devminio/reservation/2023/09/26/421be1b2-dd2a-4070-8d26-eed0bc9cf26f.png\"]', '生深层清洁 美白嫩肤', 16, 0, 148, 598, 'https://www.wxmblog.com/devminio/reservation/2023/09/26/43a8df28-5b2b-41a7-a0b2-6ac53e30c267.png', 'UP');
INSERT INTO `fast-reservation`.`product` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `title`, `pre_img`, `duration`, `time_unit`, `info`, `introduction`, `category_id`, `sort`, `price`, `ori_price`, `main_img`, `status`) VALUES (NULL, now(), NULL, now(), 0, 0, '深层清洁', 'https://www.wxmblog.com/devminio/reservation/2023/09/26/49561faa-bde4-45ad-ac66-935841c654eb.png', 60, 'MINUTE', '[\"https://www.wxmblog.com/devminio/reservation/2023/09/26/93416882-487c-46c8-947d-a1593b1371ef.png\"]', '深层清洁', 16, 0, 199, 399, 'https://www.wxmblog.com/devminio/reservation/2023/09/26/b2b83091-7b7d-4d4a-a068-0289699ef43a.png', 'UP');
INSERT INTO `fast-reservation`.`product` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `title`, `pre_img`, `duration`, `time_unit`, `info`, `introduction`, `category_id`, `sort`, `price`, `ori_price`, `main_img`, `status`) VALUES (NULL, now(), NULL, now(), 0, 0, '躺平瘦', 'https://www.wxmblog.com/devminio/reservation/2023/09/26/ade1eb99-34de-4078-9c8b-135184c40739.png', 40, 'MINUTE', '[\"https://www.wxmblog.com/devminio/reservation/2023/09/26/e178f25c-9b9f-4776-82f9-68c0c8137702.png\"]', '除湿 祛寒', 17, 0, 68, 498, 'https://www.wxmblog.com/devminio/reservation/2023/09/26/fbbe6837-b867-4da2-9c85-1252cda1705b.png', 'UP');
INSERT INTO `fast-reservation`.`product` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `title`, `pre_img`, `duration`, `time_unit`, `info`, `introduction`, `category_id`, `sort`, `price`, `ori_price`, `main_img`, `status`) VALUES (NULL, now(), NULL, now(), 0, 0, '童颜水光嫩肤', 'https://www.wxmblog.com/devminio/reservation/2023/09/26/a91c7f88-f8d1-48d9-9a39-e1894ae91656.png', 1, 'HOUR', '[\"https://www.wxmblog.com/devminio/reservation/2023/09/26/acbe1bfa-b61b-4c2c-a7ff-7bd51af7eb40.png\"]', '美白嫩肤', 16, 0, 299, 599, 'https://www.wxmblog.com/devminio/reservation/2023/09/26/b76bd6e7-6864-4e12-a593-23417ed76adc.png', 'UP');
INSERT INTO `fast-reservation`.`product` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `title`, `pre_img`, `duration`, `time_unit`, `info`, `introduction`, `category_id`, `sort`, `price`, `ori_price`, `main_img`, `status`) VALUES (NULL, now(), NULL, now(), 0, 0, '养肤提亮', 'https://www.wxmblog.com/devminio/reservation/2023/09/26/9cc73a7e-a071-4e43-a7ac-6c0889656b9a.png', 1, 'HOUR', '[\"https://www.wxmblog.com/devminio/reservation/2023/09/26/3e43b5b5-b0f6-4660-8579-9c2238bf94b7.png\"]', '提亮肤色 美白调理', 16, 0, 199, 399, 'https://www.wxmblog.com/devminio/reservation/2023/09/26/dc1414bb-a27c-45cf-8ace-edc4a5731f88.png', 'UP');


-- ----------------------------
-- Table structure for product_order
-- ----------------------------
DROP TABLE IF EXISTS `product_order`;
CREATE TABLE `product_order`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `pre_img` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '预览图片base64',
  `duration` int NOT NULL DEFAULT 90 COMMENT '时长',
  `time_unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '时长单位',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '简介',
  `price` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '价格',
  `user_id` int NOT NULL COMMENT '用户id',
  `product_id` int NOT NULL COMMENT '产品id',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `contacts` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '预约人姓名',
  `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系手机号',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `person_id` int NULL DEFAULT NULL COMMENT '人员id',
  `person_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '人员姓名',
  `person_pre_img` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '人员预览图片 base64',
  `ori_price` decimal(10, 0) NULL DEFAULT NULL COMMENT '原价',
  `main_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品预览图',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_order_FK_1`(`product_id` ASC) USING BTREE,
  INDEX `product_order_FK`(`user_id` ASC) USING BTREE,
  CONSTRAINT `product_order_FK` FOREIGN KEY (`user_id`) REFERENCES `fr_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for product_to_person
-- ----------------------------
DROP TABLE IF EXISTS `product_to_person`;
CREATE TABLE `product_to_person`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `person_id` int NOT NULL COMMENT '人员id',
  `product_id` int NOT NULL COMMENT '产品id',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_to_person_FK`(`person_id` ASC) USING BTREE,
  INDEX `product_to_person_FK_1`(`product_id` ASC) USING BTREE,
  CONSTRAINT `product_to_person_FK` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_to_person_FK_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品人员关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user_to_person
-- ----------------------------
DROP TABLE IF EXISTS `user_to_person`;
CREATE TABLE `user_to_person`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `user_id` int NOT NULL COMMENT '用户id',
  `person_id` int NOT NULL COMMENT '人员id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_to_person_FK`(`user_id` ASC) USING BTREE,
  INDEX `user_to_person_FK_1`(`person_id` ASC) USING BTREE,
  CONSTRAINT `user_to_person_FK` FOREIGN KEY (`user_id`) REFERENCES `fr_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_to_person_FK_1` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户关联人员' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
