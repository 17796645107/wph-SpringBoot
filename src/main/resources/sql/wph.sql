/*
Navicat MySQL Data Transfer

Source Server         : dn
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : wph

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2019-06-19 23:16:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `username` varchar(255) NOT NULL COMMENT '管理员用户名',
  `password` varchar(255) NOT NULL COMMENT '管理员密码',
  `create` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of tb_admin
-- ----------------------------

-- ----------------------------
-- Table structure for tb_brand
-- ----------------------------
DROP TABLE IF EXISTS `tb_brand`;
CREATE TABLE `tb_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `brand_name` varchar(255) DEFAULT NULL COMMENT '品牌名称',
  `brand_icon` varchar(255) DEFAULT NULL COMMENT '品牌图标',
  `create` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='品牌表';

-- ----------------------------
-- Records of tb_brand
-- ----------------------------
INSERT INTO `tb_brand` VALUES ('1', '百图', 'primary.png', null, null);
INSERT INTO `tb_brand` VALUES ('2', '白鹿语', 'primary_160x80_90.png', null, null);
INSERT INTO `tb_brand` VALUES ('3', '索菲丝尔', 'primary1.png', null, null);
INSERT INTO `tb_brand` VALUES ('4', '玖姿', 'primary2.png', null, null);
INSERT INTO `tb_brand` VALUES ('5', '洛诗琳', 'primary3.png', null, null);
INSERT INTO `tb_brand` VALUES ('6', '裂帛', 'primary4.png', null, null);
INSERT INTO `tb_brand` VALUES ('7', '拉谷谷', 'primary5.png', null, null);
INSERT INTO `tb_brand` VALUES ('8', '欧时力', 'ochirly.png', null, null);
INSERT INTO `tb_brand` VALUES ('9', '七品狼', '63f581b5ab5a2b5c6111a29de5f140a6.jpg', null, null);
INSERT INTO `tb_brand` VALUES ('10', '\r\n丝柏舍', 'primary6.png', null, null);
INSERT INTO `tb_brand` VALUES ('11', '韩都衣舍', 'primary7.png', null, null);

-- ----------------------------
-- Table structure for tb_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `productId` int(11) DEFAULT NULL COMMENT '商品编号',
  `productNumber` int(11) DEFAULT NULL COMMENT '商品数量',
  `productColor` varchar(255) DEFAULT NULL COMMENT '商品颜色',
  `productSize` varchar(255) DEFAULT NULL COMMENT '商品尺寸',
  `status` tinyint(4) DEFAULT '1' COMMENT '购物车状态',
  `create` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='购物车表';

-- ----------------------------
-- Records of tb_cart
-- ----------------------------
INSERT INTO `tb_cart` VALUES ('3', '1', '2019040901', '3', '漂白', 'M', '0', '2019-04-16 20:08:03', null);
INSERT INTO `tb_cart` VALUES ('6', '1', '2019040901', '1', '漂白', 'L', '0', '2019-04-16 20:37:01', null);
INSERT INTO `tb_cart` VALUES ('7', '1', '2019040901', '1', '灰粉红', 'L', '0', '2019-04-16 20:55:24', null);
INSERT INTO `tb_cart` VALUES ('8', '1', '2019040901', '1', '灰粉红', 'XS', '0', '2019-04-16 20:56:20', null);
INSERT INTO `tb_cart` VALUES ('10', '1', '2019040901', '1', '灰粉红', 'M', '0', '2019-04-16 20:58:14', null);
INSERT INTO `tb_cart` VALUES ('12', '1', '2019040901', '1', '灰粉红', 'XL', '0', '2019-04-16 20:58:45', null);
INSERT INTO `tb_cart` VALUES ('22', '1', '2019050504', '1', '默认', 'L', '1', '2019-05-26 23:04:56', null);

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL COMMENT '类目名称',
  `category_sort` tinyint(4) DEFAULT NULL COMMENT '排序',
  `parent_id` int(11) NOT NULL COMMENT '父ID',
  `admin_id` int(11) NOT NULL COMMENT '管理员ID',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8 COMMENT='商品类目表';

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('1', '人气美衣', '1', '0', '1', '1', '2015-05-14 00:00:00', null);
INSERT INTO `tb_category` VALUES ('2', '女下装', '2', '0', '1', '1', '2015-05-14 00:00:00', null);
INSERT INTO `tb_category` VALUES ('3', '特色女装', '3', '0', '1', '1', '2015-05-14 00:00:00', null);
INSERT INTO `tb_category` VALUES ('4', '男上装', '4', '0', '1', '1', '2015-05-14 00:00:00', null);
INSERT INTO `tb_category` VALUES ('5', '男下装', '5', '0', '1', '1', '2015-05-14 00:00:00', null);
INSERT INTO `tb_category` VALUES ('6', '特色男装', '6', '0', '1', '1', '2015-05-14 00:00:00', null);
INSERT INTO `tb_category` VALUES ('7', '女士内衣', '7', '0', '1', '1', '2015-05-14 00:00:00', null);
INSERT INTO `tb_category` VALUES ('8', '男士内衣', '8', '0', '1', '1', '2015-05-14 00:00:00', null);
INSERT INTO `tb_category` VALUES ('9', '秋季上新 ', '9', '0', '1', '1', '2015-05-14 00:00:00', null);
INSERT INTO `tb_category` VALUES ('40', 'T恤', '1', '1', '1', '1', '2019-05-14 18:43:39', null);
INSERT INTO `tb_category` VALUES ('41', '衬衫', '2', '1', '1', '1', '2019-05-14 18:58:03', null);
INSERT INTO `tb_category` VALUES ('42', '连衣裙', '3', '1', '1', '1', '2019-05-14 19:05:39', null);
INSERT INTO `tb_category` VALUES ('43', '外套', '4', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('44', '短外套', '5', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('45', '卫衣', '6', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('46', '防晒衣', '7', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('47', '雪纺衫', '8', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('48', '针织衫', '9', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('49', '小西装', '10', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('50', '风衣', '11', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('51', '马甲', '12', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('52', '羽绒', '13', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('53', '棉衣', '14', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('54', '毛衣', '15', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('55', '大衣', '16', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('56', '羊绒/羊毛衫', '17', '1', '1', '1', '2019-05-14 19:05:43', null);
INSERT INTO `tb_category` VALUES ('57', '皮衣/皮草', '18', '1', '1', '1', '2019-05-14 19:05:44', null);
INSERT INTO `tb_category` VALUES ('58', '套装', '19', '1', '1', '1', '2019-05-14 19:05:44', null);
INSERT INTO `tb_category` VALUES ('59', '休闲裤', '1', '2', '1', '1', '2019-05-14 20:00:47', null);
INSERT INTO `tb_category` VALUES ('60', '半身裙', '2', '2', '1', '1', '2019-05-14 20:00:49', null);
INSERT INTO `tb_category` VALUES ('61', '打底裤', '3', '2', '1', '1', '2019-05-14 20:00:49', null);
INSERT INTO `tb_category` VALUES ('62', '西装裤', '4', '2', '1', '1', '2019-05-14 20:00:49', null);
INSERT INTO `tb_category` VALUES ('63', '牛仔裤', '5', '2', '1', '1', '2019-05-14 20:00:49', null);
INSERT INTO `tb_category` VALUES ('64', '阔腿裤', '6', '2', '1', '1', '2019-05-14 20:00:49', null);
INSERT INTO `tb_category` VALUES ('65', '短裤', '7', '2', '1', '1', '2019-05-14 20:00:50', null);
INSERT INTO `tb_category` VALUES ('66', '连体/背带裤', '8', '2', '1', '1', '2019-05-14 20:00:50', null);
INSERT INTO `tb_category` VALUES ('67', '哈伦裤', '9', '2', '1', '1', '2019-05-14 20:00:50', null);
INSERT INTO `tb_category` VALUES ('68', '牛仔裙', '10', '2', '1', '1', '2019-05-14 20:00:50', null);
INSERT INTO `tb_category` VALUES ('69', '设计师', '1', '3', '1', '1', '2019-05-14 20:04:03', null);
INSERT INTO `tb_category` VALUES ('70', '民族风', '2', '3', '1', '1', '2019-05-14 20:04:05', null);
INSERT INTO `tb_category` VALUES ('71', '礼服/旗袍', '3', '3', '1', '1', '2019-05-14 20:04:05', null);
INSERT INTO `tb_category` VALUES ('72', '中老年女装', '4', '3', '1', '1', '2019-05-14 20:04:05', null);
INSERT INTO `tb_category` VALUES ('73', '时尚套装', '5', '3', '1', '1', '2019-05-14 20:04:05', null);
INSERT INTO `tb_category` VALUES ('74', '短袖T恤', '0', '4', '1', '1', '2019-05-14 20:06:05', null);
INSERT INTO `tb_category` VALUES ('75', '棉衣', '0', '4', '1', '1', '2019-05-14 20:06:05', null);
INSERT INTO `tb_category` VALUES ('76', '大衣', '0', '4', '1', '1', '2019-05-14 20:06:05', null);
INSERT INTO `tb_category` VALUES ('77', '棒球服', '0', '4', '1', '1', '2019-05-14 20:06:05', null);
INSERT INTO `tb_category` VALUES ('78', '毛衣', '0', '4', '1', '1', '2019-05-14 20:06:05', null);
INSERT INTO `tb_category` VALUES ('79', '羽绒服', '0', '4', '1', '1', '2019-05-14 20:06:05', null);
INSERT INTO `tb_category` VALUES ('80', '皮衣', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('81', '马甲/背心', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('82', '风衣', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('83', '长袖T恤', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('84', '外套', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('85', '皮肤衣', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('86', '针织衫', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('87', 'Polo衫', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('88', '夹克', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('89', '西装', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('90', '卫衣', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('91', '衬衫', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('92', '羊绒/羊毛衫', '0', '4', '1', '1', '2019-05-14 20:06:06', null);
INSERT INTO `tb_category` VALUES ('93', '短裤', '0', '5', '1', '1', '2019-05-14 20:31:43', null);
INSERT INTO `tb_category` VALUES ('94', '运动裤', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('95', '西裤', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('96', '牛仔短裤', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('97', '牛仔裤', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('98', '哈伦裤', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('99', '夏季裤装', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('100', '工装裤', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('101', '亚麻裤', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('102', '冬季裤装', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('103', '加绒裤', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('104', '小脚裤', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('105', '休闲裤', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('106', '九分裤', '0', '5', '1', '1', '2019-05-14 20:31:45', null);
INSERT INTO `tb_category` VALUES ('107', '中老年', '0', '6', '1', '1', '2019-05-14 20:33:17', null);
INSERT INTO `tb_category` VALUES ('108', '职场精英', '0', '6', '1', '1', '2019-05-14 20:33:19', null);
INSERT INTO `tb_category` VALUES ('109', '大码', '0', '6', '1', '1', '2019-05-14 20:33:19', null);
INSERT INTO `tb_category` VALUES ('110', '运动着装', '0', '6', '1', '1', '2019-05-14 20:33:19', null);
INSERT INTO `tb_category` VALUES ('111', '街头潮流', '0', '6', '1', '1', '2019-05-14 20:33:19', null);
INSERT INTO `tb_category` VALUES ('112', '中国风', '0', '6', '1', '1', '2019-05-14 20:33:19', null);
INSERT INTO `tb_category` VALUES ('113', '时尚休闲', '0', '6', '1', '1', '2019-05-14 20:33:19', null);
INSERT INTO `tb_category` VALUES ('114', '情侣装', '0', '6', '1', '1', '2019-05-14 20:33:19', null);
INSERT INTO `tb_category` VALUES ('117', '聚拢文胸', '0', '7', '1', '1', '2019-05-14 20:34:49', null);
INSERT INTO `tb_category` VALUES ('118', '无钢圈文胸', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('119', '文胸套装', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('120', '薄杯文胸', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('121', '运动文胸', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('122', '调整型文胸', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('123', '美背文胸', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('124', '睡衣/家居服', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('125', '睡裙/睡袍', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('126', '纯棉睡衣', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('127', '女士内裤', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('128', '纯棉内裤', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('129', '女袜', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('130', '丝袜/裤袜', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('131', '塑身内衣', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('132', '抹胸/吊带', '0', '7', '1', '1', '2019-05-14 20:34:52', null);
INSERT INTO `tb_category` VALUES ('133', '男士内裤', '0', '8', '1', '1', '2019-05-14 20:38:44', null);
INSERT INTO `tb_category` VALUES ('134', '组合内裤', '0', '8', '1', '1', '2019-05-14 20:38:45', null);
INSERT INTO `tb_category` VALUES ('135', '平角裤', '0', '8', '1', '1', '2019-05-14 20:38:45', null);
INSERT INTO `tb_category` VALUES ('136', '男袜', '0', '8', '1', '1', '2019-05-14 20:38:46', null);
INSERT INTO `tb_category` VALUES ('137', '睡衣/家居服', '0', '8', '1', '1', '2019-05-14 20:38:46', null);
INSERT INTO `tb_category` VALUES ('138', '背心/T恤', '0', '8', '1', '1', '2019-05-14 20:38:46', null);
INSERT INTO `tb_category` VALUES ('139', '女士保暖', '0', '9', '1', '1', '2019-05-14 20:39:25', null);
INSERT INTO `tb_category` VALUES ('140', '男士保暖', '0', '9', '1', '1', '2019-05-14 20:39:26', null);
INSERT INTO `tb_category` VALUES ('141', '女士羊绒衫', '0', '9', '1', '1', '2019-05-14 20:39:26', null);
INSERT INTO `tb_category` VALUES ('142', '男士羊绒衫', '0', '9', '1', '1', '2019-05-14 20:39:26', null);
INSERT INTO `tb_category` VALUES ('143', '羊绒羊毛大衣', '0', '9', '1', '1', '2019-05-14 20:39:26', null);
INSERT INTO `tb_category` VALUES ('144', '羊绒裙', '0', '9', '1', '1', '2019-05-14 20:39:26', null);

-- ----------------------------
-- Table structure for tb_category_attribute
-- ----------------------------
DROP TABLE IF EXISTS `tb_category_attribute`;
CREATE TABLE `tb_category_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `attribute_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_category_attribute
-- ----------------------------
INSERT INTO `tb_category_attribute` VALUES ('1', '40', '1');
INSERT INTO `tb_category_attribute` VALUES ('2', '40', '2');
INSERT INTO `tb_category_attribute` VALUES ('3', '40', '5');
INSERT INTO `tb_category_attribute` VALUES ('4', '40', '6');
INSERT INTO `tb_category_attribute` VALUES ('5', '40', '31');
INSERT INTO `tb_category_attribute` VALUES ('6', '40', '30');
INSERT INTO `tb_category_attribute` VALUES ('7', '40', '32');
INSERT INTO `tb_category_attribute` VALUES ('8', '40', '23');
INSERT INTO `tb_category_attribute` VALUES ('9', '40', '19');
INSERT INTO `tb_category_attribute` VALUES ('10', '40', '12');
INSERT INTO `tb_category_attribute` VALUES ('11', '42', '4');
INSERT INTO `tb_category_attribute` VALUES ('12', '41', '3');
INSERT INTO `tb_category_attribute` VALUES ('13', '41', '5');
INSERT INTO `tb_category_attribute` VALUES ('14', '42', '6');
INSERT INTO `tb_category_attribute` VALUES ('15', '42', '31');
INSERT INTO `tb_category_attribute` VALUES ('16', '42', '34');
INSERT INTO `tb_category_attribute` VALUES ('17', '41', '30');
INSERT INTO `tb_category_attribute` VALUES ('18', '41', '19');
INSERT INTO `tb_category_attribute` VALUES ('19', '41', '4');
INSERT INTO `tb_category_attribute` VALUES ('20', '42', '7');
INSERT INTO `tb_category_attribute` VALUES ('21', '42', '8');
INSERT INTO `tb_category_attribute` VALUES ('22', '42', '2');
INSERT INTO `tb_category_attribute` VALUES ('23', '42', '42');
INSERT INTO `tb_category_attribute` VALUES ('24', '41', '34');
INSERT INTO `tb_category_attribute` VALUES ('25', '42', '11');
INSERT INTO `tb_category_attribute` VALUES ('26', '42', '10');
INSERT INTO `tb_category_attribute` VALUES ('27', '42', '23');
INSERT INTO `tb_category_attribute` VALUES ('28', '41', '31');
INSERT INTO `tb_category_attribute` VALUES ('29', '41', '6');
INSERT INTO `tb_category_attribute` VALUES ('30', '43', '4');
INSERT INTO `tb_category_attribute` VALUES ('31', '43', '3');
INSERT INTO `tb_category_attribute` VALUES ('32', '43', '12');
INSERT INTO `tb_category_attribute` VALUES ('33', '43', '13');
INSERT INTO `tb_category_attribute` VALUES ('34', '43', '11');
INSERT INTO `tb_category_attribute` VALUES ('35', '43', '6');
INSERT INTO `tb_category_attribute` VALUES ('36', '43', '19');
INSERT INTO `tb_category_attribute` VALUES ('37', '44', '10');
INSERT INTO `tb_category_attribute` VALUES ('38', '44', '12');
INSERT INTO `tb_category_attribute` VALUES ('39', '44', '6');
INSERT INTO `tb_category_attribute` VALUES ('40', '45', '4');
INSERT INTO `tb_category_attribute` VALUES ('41', '45', '3');
INSERT INTO `tb_category_attribute` VALUES ('42', '45', '12');
INSERT INTO `tb_category_attribute` VALUES ('43', '45', '13');
INSERT INTO `tb_category_attribute` VALUES ('44', '45', '11');
INSERT INTO `tb_category_attribute` VALUES ('45', '45', '34');
INSERT INTO `tb_category_attribute` VALUES ('46', '45', '19');
INSERT INTO `tb_category_attribute` VALUES ('47', '45', '10');
INSERT INTO `tb_category_attribute` VALUES ('48', '46', '14');
INSERT INTO `tb_category_attribute` VALUES ('49', '46', '15');
INSERT INTO `tb_category_attribute` VALUES ('50', '46', '1');
INSERT INTO `tb_category_attribute` VALUES ('51', '46', '26');
INSERT INTO `tb_category_attribute` VALUES ('52', '47', '4');
INSERT INTO `tb_category_attribute` VALUES ('53', '47', '3');
INSERT INTO `tb_category_attribute` VALUES ('54', '47', '5');
INSERT INTO `tb_category_attribute` VALUES ('55', '47', '6');
INSERT INTO `tb_category_attribute` VALUES ('56', '47', '31');
INSERT INTO `tb_category_attribute` VALUES ('57', '47', '34');
INSERT INTO `tb_category_attribute` VALUES ('58', '47', '30');
INSERT INTO `tb_category_attribute` VALUES ('59', '47', '19');
INSERT INTO `tb_category_attribute` VALUES ('60', '48', '6');
INSERT INTO `tb_category_attribute` VALUES ('61', '48', '11');
INSERT INTO `tb_category_attribute` VALUES ('62', '48', '4');
INSERT INTO `tb_category_attribute` VALUES ('63', '48', '3');
INSERT INTO `tb_category_attribute` VALUES ('64', '48', '27');
INSERT INTO `tb_category_attribute` VALUES ('65', '48', '12');
INSERT INTO `tb_category_attribute` VALUES ('66', '48', '10');
INSERT INTO `tb_category_attribute` VALUES ('67', '59', '43');
INSERT INTO `tb_category_attribute` VALUES ('68', '59', '42');
INSERT INTO `tb_category_attribute` VALUES ('69', '59', '4');
INSERT INTO `tb_category_attribute` VALUES ('70', '59', '11');
INSERT INTO `tb_category_attribute` VALUES ('71', '59', '41');
INSERT INTO `tb_category_attribute` VALUES ('72', '59', '12');
INSERT INTO `tb_category_attribute` VALUES ('73', '59', '23');
INSERT INTO `tb_category_attribute` VALUES ('74', '59', '15');
INSERT INTO `tb_category_attribute` VALUES ('75', '60', '8');
INSERT INTO `tb_category_attribute` VALUES ('76', '60', '7');
INSERT INTO `tb_category_attribute` VALUES ('77', '60', '4');
INSERT INTO `tb_category_attribute` VALUES ('78', '60', '15');
INSERT INTO `tb_category_attribute` VALUES ('79', '60', '23');
INSERT INTO `tb_category_attribute` VALUES ('80', '60', '20');
INSERT INTO `tb_category_attribute` VALUES ('81', '60', '19');

-- ----------------------------
-- Table structure for tb_courier
-- ----------------------------
DROP TABLE IF EXISTS `tb_courier`;
CREATE TABLE `tb_courier` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '快递点ID',
  `name` varchar(255) DEFAULT NULL COMMENT '快递名称',
  `telephone` varchar(255) DEFAULT NULL COMMENT '手机',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `create` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流-快递点表';

-- ----------------------------
-- Records of tb_courier
-- ----------------------------

-- ----------------------------
-- Table structure for tb_log_base
-- ----------------------------
DROP TABLE IF EXISTS `tb_log_base`;
CREATE TABLE `tb_log_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '物流单号',
  `courier_id` varchar(255) DEFAULT NULL COMMENT '快递点ID',
  `orderId` int(11) DEFAULT NULL COMMENT '订单ID',
  `status` varchar(255) DEFAULT NULL,
  `create` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流基本信息表';

-- ----------------------------
-- Records of tb_log_base
-- ----------------------------

-- ----------------------------
-- Table structure for tb_log_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_log_message`;
CREATE TABLE `tb_log_message` (
  `id` int(11) NOT NULL,
  `log_id` int(11) NOT NULL COMMENT '物流单号',
  `send_place` varchar(255) DEFAULT NULL COMMENT '发出地',
  `arrive_place` varchar(255) DEFAULT NULL COMMENT '到达地',
  `send_date` date DEFAULT NULL COMMENT '发出时间',
  `arrive_date` date DEFAULT NULL COMMENT '到达时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流信息表';

-- ----------------------------
-- Records of tb_log_message
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单号',
  `order_no` varchar(27) NOT NULL COMMENT '自动编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `address_id` int(11) DEFAULT NULL COMMENT '用户地址ID',
  `product_total` double(255,0) DEFAULT NULL COMMENT '商品总价',
  `order_count` int(11) DEFAULT NULL COMMENT '订单商品数量',
  `pay_channel` tinyint(255) DEFAULT NULL COMMENT '订单支付渠道',
  `pay_no` varchar(255) DEFAULT NULL COMMENT '第三方支付流水号',
  `status` tinyint(4) DEFAULT NULL COMMENT '订单状态',
  `create` varchar(255) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of tb_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_channer
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_channer`;
CREATE TABLE `tb_order_channer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_channer` int(11) DEFAULT NULL COMMENT '支付渠道',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付渠道表';

-- ----------------------------
-- Records of tb_order_channer
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_product`;
CREATE TABLE `tb_order_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(255) NOT NULL COMMENT '商品id',
  `orderId` int(11) NOT NULL COMMENT '订单id',
  `productNumber` int(11) DEFAULT NULL COMMENT '商品数量',
  `productSize` varchar(255) DEFAULT NULL COMMENT '商品尺寸',
  `productColor` varchar(255) DEFAULT NULL COMMENT '商品颜色',
  `create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='订单-商品详情表';

-- ----------------------------
-- Records of tb_order_product
-- ----------------------------
INSERT INTO `tb_order_product` VALUES ('8', '2019040901', '15', '3', 'M', '漂白', null);
INSERT INTO `tb_order_product` VALUES ('9', '2019040901', '15', '1', 'L', '漂白', null);
INSERT INTO `tb_order_product` VALUES ('10', '2019040901', '15', '1', 'L', '灰粉红', null);
INSERT INTO `tb_order_product` VALUES ('11', '2019040901', '15', '1', 'XS', '灰粉红', null);
INSERT INTO `tb_order_product` VALUES ('12', '2019040901', '15', '1', 'M', '灰粉红', null);
INSERT INTO `tb_order_product` VALUES ('13', '2019040901', '15', '1', 'XL', '灰粉红', null);
INSERT INTO `tb_order_product` VALUES ('14', '2019040901', '16', '1', 'XL', '漂白', null);
INSERT INTO `tb_order_product` VALUES ('15', '2019040901', '17', '10', 'L', '灰粉红', null);
INSERT INTO `tb_order_product` VALUES ('16', '2019040901', '18', '5', 'S', '漂白', null);
INSERT INTO `tb_order_product` VALUES ('17', '2019040901', '19', '20', 'XS', '漂白', null);
INSERT INTO `tb_order_product` VALUES ('18', '2019050503', '20', '1', 'S', '默认', null);
INSERT INTO `tb_order_product` VALUES ('19', '2019050505', '21', '1', 'M', '默认', null);
INSERT INTO `tb_order_product` VALUES ('20', '2019050507', '22', '1', '54A', '深绿色', null);
INSERT INTO `tb_order_product` VALUES ('21', '2019050507', '23', '1', '54A', '深绿色', null);
INSERT INTO `tb_order_product` VALUES ('22', '2019050507', '24', '1', '54A', '深绿色', null);
INSERT INTO `tb_order_product` VALUES ('23', '2019040801', '25', '1', 'L', '蓝色', null);
INSERT INTO `tb_order_product` VALUES ('24', '2019041001', '26', '1', 'XS', '浅粉', null);
INSERT INTO `tb_order_product` VALUES ('25', '2019050503', '27', '2', 'M', '默认', null);
INSERT INTO `tb_order_product` VALUES ('26', '2019041001', '28', '1', 'XS', '浅黄', null);
INSERT INTO `tb_order_product` VALUES ('27', '2019050503', '29', '1', 'S', '默认', null);
INSERT INTO `tb_order_product` VALUES ('28', '2019050503', '30', '8', 'M', '默认', null);

-- ----------------------------
-- Table structure for tb_order_state
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_state`;
CREATE TABLE `tb_order_state` (
  `id` int(11) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单状态枚举表';

-- ----------------------------
-- Records of tb_order_state
-- ----------------------------
INSERT INTO `tb_order_state` VALUES ('1', '已取消', null);
INSERT INTO `tb_order_state` VALUES ('2', '待处理', null);
INSERT INTO `tb_order_state` VALUES ('3', '待发货', null);
INSERT INTO `tb_order_state` VALUES ('4', '已发货', null);
INSERT INTO `tb_order_state` VALUES ('5', '已收货', null);
INSERT INTO `tb_order_state` VALUES ('6', null, null);

-- ----------------------------
-- Table structure for tb_pay_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_pay_info`;
CREATE TABLE `tb_pay_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `orderId` varchar(255) DEFAULT NULL COMMENT '订单ID',
  `pay_platform` varchar(255) DEFAULT NULL COMMENT '支付平台',
  `platform_number` varchar(255) DEFAULT NULL COMMENT '第三方流水号',
  `platform_status` varchar(255) DEFAULT NULL COMMENT '支付状态',
  `create` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='支付信息表';

-- ----------------------------
-- Records of tb_pay_info
-- ----------------------------
INSERT INTO `tb_pay_info` VALUES ('1', '1', '1201812060001', '支付宝', '201812060001', '已支付', '2018-12-06 10:31:20', null);
INSERT INTO `tb_pay_info` VALUES ('2', '2', '1201812060002', '微信', '1201812060002', '已支付', '2018-12-06 10:31:27', null);
INSERT INTO `tb_pay_info` VALUES ('3', '1', '1201812060003', '支付宝', '1201812060002', '未付款', '2018-12-06 10:31:27', null);
INSERT INTO `tb_pay_info` VALUES ('4', '1', '1201812060004', '支付宝', '1201812060002', '未付款', '2018-12-06 10:31:27', null);
INSERT INTO `tb_pay_info` VALUES ('5', '2', '1201812060005', '微信', '1201812060004', '未付款', '2018-12-06 10:31:27', null);
INSERT INTO `tb_pay_info` VALUES ('6', '2', '1201812060006', '微信', '1201812060004', '已支付', '2018-12-06 10:31:27', null);

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_no` varchar(27) NOT NULL COMMENT '编号',
  `seller_id` int(11) NOT NULL COMMENT '商户ID',
  `category_id` int(11) NOT NULL COMMENT '分类ID',
  `brand_id` int(11) NOT NULL COMMENT '品牌ID',
  `title` varchar(255) NOT NULL COMMENT '商品名称',
  `detail` varchar(255) NOT NULL DEFAULT '' COMMENT '商品卖点，商品描述',
  `price` int(11) NOT NULL COMMENT '商品价格，x100',
  `collect` int(11) DEFAULT '0' COMMENT '商品收藏量',
  `is_hot` tinyint(4) DEFAULT '1' COMMENT '是否热门',
  `is_new` tinyint(4) DEFAULT '0' COMMENT '是否新品',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态：0-删除，1-正常',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2019052503 DEFAULT CHARSET=utf8 COMMENT='商品基本信息表';

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES ('1', '2019040701', '8', '40', '8', 'ochirly欧时力春秋装纯色立领印花棉质短袖t恤女', '字母印花低调吸睛/立领设计时髦大方/纯色修身版型', '100', '999', '1', '0', '1', '2019-04-07 23:57:03', null);
INSERT INTO `tb_product` VALUES ('2', '2019040702', '8', '42', '8', 'ochirly欧时力新女装拼接荷叶边刺绣网纱无袖连衣裙', 'ochirly欧时力新女装拼接荷叶边刺绣网纱无袖连衣裙', '99', '0', '1', '0', '1', '2019-04-08 09:36:39', null);
INSERT INTO `tb_product` VALUES ('3', '2019040801', '8', '48', '8', 'ochirly欧时力春夏装两件套纯色半裙套头针织衫女', '针织衫+半裙两件套，打造低调都会女郎印象，时髦优雅；后幅单排扣设计，婉约淑雅轻松演绎；A字轮廓半裙，强调纤腰长腿，魅力显瘦。', '100', '999', '1', '0', '1', '2019-04-08 13:21:07', null);
INSERT INTO `tb_product` VALUES ('4', '2019040901', '8', '40', '8', 'ochirly欧时力春夏装V型挖空撞色印花短袖t恤女', 'V型领口+挖空设计展露出部分肌肤，新潮个性，街头感十足；仙人掌+字母印花，带来几分休闲度假风，经典潮流；选用棉质面料制作，兼具亲肤性与透气性，穿着舒适怡人。', '68', '0', '1', '0', '1', '2019-04-09 17:50:09', null);
INSERT INTO `tb_product` VALUES ('5', '2019041001', '8', '40', '8', 'ochirly欧时力春装圆领心形印花棉质短袖T恤女', '领口点缀时髦印花/吸睛心形亮片贴布/采用柔软高含棉', '193', '0', '1', '0', '1', '2019-04-10 11:52:48', null);
INSERT INTO `tb_product` VALUES ('6', '2019050501', '6', '40', '6', '刺绣圆领针织短袖休闲合体显瘦T恤', '大气圆领 花枝刺绣 落肩设计 舒适面料', '59', '0', '1', '0', '1', '2019-05-05 14:49:45', null);
INSERT INTO `tb_product` VALUES ('7', '2019050502', '6', '48', '6', '2019春季新款文艺甜美百搭圆领清新提花长袖女士外套针织开衫', '简约圆领 清新提花 实用长袖 舒适开衫', '129', '0', '1', '0', '1', '2019-05-05 15:01:43', null);
INSERT INTO `tb_product` VALUES ('8', '2019050503', '1', '43', '1', 'Betu/百图学院风西装领中长款外套', '', '229', '0', '1', '0', '1', '2019-05-05 15:55:59', null);
INSERT INTO `tb_product` VALUES ('9', '2019050504', '1', '43', '1', 'betu百图时尚百搭字母刺绣短款女装外套学院风流苏牛仔外套', '字母刺绣短款牛仔外套', '199', '0', '1', '0', '1', '2019-05-05 16:01:02', null);
INSERT INTO `tb_product` VALUES ('10', '2019050505', '1', '42', '1', 'betu百图新款裙子撞色字母强织带肩带吊带连衣裙荷叶袖仙女裙', '趣味撞色织带肩带 缤纷几何图形印花图案', '269', '0', '1', '0', '1', '2019-05-05 16:05:39', null);
INSERT INTO `tb_product` VALUES ('11', '2019050506', '2', '41', '2', '白鹿语19春季新款纯色V领拼接网纱灯笼袖收腰荷叶边衬衫女', '细腻柔软的网纱与泡泡袖的结合 让整体look加分 腰部的收腰处理 让下半身比例更加纤长', '113', '0', '1', '0', '1', '2019-05-05 16:10:53', null);
INSERT INTO `tb_product` VALUES ('12', '2019050507', '9', '40', '9', '七匹狼夏装 新款时尚休闲青年男士条纹短袖T恤', '2018夏季新品 舒适纯棉', '199', '0', '1', '0', '1', '2019-05-22 12:38:53', null);
INSERT INTO `tb_product` VALUES ('13', '2019052401', '3', '59', '3', '【夏装新款】薄款宽松脚口开叉休闲裤女小脚裤子女夏', '百搭舒适 简洁纯色 开叉设计', '109', '0', '0', '1', '1', '2019-05-24 12:02:21', null);
INSERT INTO `tb_product` VALUES ('14', '2019052501', '4', '42', '4', 'JUZUI/玖姿新款碎花短袖V领气质修身连衣裙女', '', '329', '0', '0', '1', '1', '2019-05-25 18:23:01', null);
INSERT INTO `tb_product` VALUES ('15', '2019052502', '4', '42', '4', 'JUZUI-玖姿新品优雅气质拼接荷叶袖花朵刺绣连衣裙', '', '364', '0', '0', '1', '1', '2019-05-25 19:07:32', null);

-- ----------------------------
-- Table structure for tb_product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_attribute`;
CREATE TABLE `tb_product_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品属性ID',
  `attr_name` varchar(255) DEFAULT NULL COMMENT '属性名称',
  `is_search` tinyint(4) DEFAULT '1' COMMENT '是否搜索',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `attr_name` (`attr_name`) USING BTREE COMMENT '唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='商品属性表';

-- ----------------------------
-- Records of tb_product_attribute
-- ----------------------------
INSERT INTO `tb_product_attribute` VALUES ('1', '尺码', '1', '1', '2019-05-14 21:08:33', null);
INSERT INTO `tb_product_attribute` VALUES ('2', '颜色', '1', '1', '2019-05-14 21:08:36', null);
INSERT INTO `tb_product_attribute` VALUES ('3', '衣长', '1', '1', '2019-05-14 21:08:39', null);
INSERT INTO `tb_product_attribute` VALUES ('4', '版型', '1', '1', '2019-05-14 21:08:41', null);
INSERT INTO `tb_product_attribute` VALUES ('5', '袖长', '1', '1', '2019-05-14 21:08:43', null);
INSERT INTO `tb_product_attribute` VALUES ('6', '领型', '1', '1', '2019-05-14 21:08:45', null);
INSERT INTO `tb_product_attribute` VALUES ('7', '裙长', '1', '1', '2019-05-14 22:23:27', null);
INSERT INTO `tb_product_attribute` VALUES ('8', '裙型', '1', '1', '2019-05-14 22:23:29', null);
INSERT INTO `tb_product_attribute` VALUES ('10', '风格', '1', '1', '2019-05-14 22:23:47', null);
INSERT INTO `tb_product_attribute` VALUES ('11', '款式', '1', '1', '2019-05-14 22:23:48', null);
INSERT INTO `tb_product_attribute` VALUES ('12', '厚薄', '1', '1', '2019-05-14 22:23:48', null);
INSERT INTO `tb_product_attribute` VALUES ('13', '门襟', '1', '1', '2019-05-14 22:23:48', null);
INSERT INTO `tb_product_attribute` VALUES ('14', '适用项目', '0', '1', '2019-05-14 22:32:57', null);
INSERT INTO `tb_product_attribute` VALUES ('15', '适用季节', '0', '1', '2019-05-14 22:33:04', null);
INSERT INTO `tb_product_attribute` VALUES ('16', '填充物', '0', '1', '2019-05-14 22:33:04', null);
INSERT INTO `tb_product_attribute` VALUES ('17', '充绒量', '0', '1', '2019-05-14 22:33:04', null);
INSERT INTO `tb_product_attribute` VALUES ('18', '填充物含量', '0', '1', '2019-05-14 22:33:04', null);
INSERT INTO `tb_product_attribute` VALUES ('19', '选购热点', '1', '1', '2019-05-14 22:33:04', null);
INSERT INTO `tb_product_attribute` VALUES ('20', '裙/裤门襟', '0', '1', '2019-05-14 22:33:04', null);
INSERT INTO `tb_product_attribute` VALUES ('21', '流行元素', '0', '1', '2019-05-14 22:35:07', null);
INSERT INTO `tb_product_attribute` VALUES ('22', '适用场合', '0', '1', '2019-05-14 22:35:08', null);
INSERT INTO `tb_product_attribute` VALUES ('23', '弹性', '1', '1', '2019-05-14 22:35:08', null);
INSERT INTO `tb_product_attribute` VALUES ('24', '适用人群', '0', '1', '2019-05-14 22:35:08', null);
INSERT INTO `tb_product_attribute` VALUES ('25', '面料', '1', '1', '2019-05-14 22:35:08', null);
INSERT INTO `tb_product_attribute` VALUES ('26', '功能', '0', '1', '2019-05-14 22:35:08', null);
INSERT INTO `tb_product_attribute` VALUES ('27', '柔软度', '0', '1', '2019-05-14 22:35:08', null);
INSERT INTO `tb_product_attribute` VALUES ('29', '规格数量', '0', '1', '2019-05-14 22:37:25', null);
INSERT INTO `tb_product_attribute` VALUES ('30', '图案', '1', '1', '2019-05-16 00:23:46', null);
INSERT INTO `tb_product_attribute` VALUES ('31', '肩型', '1', '1', '2019-05-16 00:23:48', null);
INSERT INTO `tb_product_attribute` VALUES ('32', '袖形', '1', '1', '2019-05-16 00:23:52', null);
INSERT INTO `tb_product_attribute` VALUES ('34', '袖型', '1', '1', '2019-05-16 21:20:22', null);
INSERT INTO `tb_product_attribute` VALUES ('35', '适用性别', '0', '1', '2019-05-16 21:24:02', null);
INSERT INTO `tb_product_attribute` VALUES ('36', '洗涤说明', '0', '1', '2019-05-16 22:21:39', null);
INSERT INTO `tb_product_attribute` VALUES ('37', '上市时间', '0', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute` VALUES ('38', '重量', '0', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute` VALUES ('39', '产地', '0', '1', '2019-05-16 22:30:30', null);
INSERT INTO `tb_product_attribute` VALUES ('40', '材质', '0', '1', '2019-05-16 22:30:30', null);
INSERT INTO `tb_product_attribute` VALUES ('41', '裤门襟', '1', '1', '2019-05-24 19:00:16', null);
INSERT INTO `tb_product_attribute` VALUES ('42', '腰型', '1', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute` VALUES ('43', '裤长', '1', '1', '2019-05-25 14:17:09', null);
INSERT INTO `tb_product_attribute` VALUES ('44', '衣门襟', '1', '1', '2019-05-27 11:36:38', null);

-- ----------------------------
-- Table structure for tb_product_attribute_relation
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_attribute_relation`;
CREATE TABLE `tb_product_attribute_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) NOT NULL COMMENT '商品ID',
  `attribute_id` int(11) DEFAULT NULL COMMENT '属性ID',
  `value_id` int(11) DEFAULT NULL COMMENT '属性值ID',
  `status` int(11) DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='商品属性关系表';

-- ----------------------------
-- Records of tb_product_attribute_relation
-- ----------------------------
INSERT INTO `tb_product_attribute_relation` VALUES ('1', '2019050507', '15', '37', '1', '2019-05-16 20:53:50', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('2', '2019050507', '34', '43', '1', '2019-05-16 21:20:23', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('3', '2019050507', '22', '44', '1', '2019-05-16 21:20:23', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('4', '2019050507', '3', '43', '1', '2019-05-16 21:20:23', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('5', '2019050507', '23', '45', '1', '2019-05-16 21:20:23', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('6', '2019050507', '11', '46', '1', '2019-05-16 21:24:02', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('7', '2019050507', '24', '47', '1', '2019-05-16 21:24:02', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('8', '2019050507', '19', '48', '1', '2019-05-16 21:24:02', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('9', '2019050507', '10', '49', '1', '2019-05-16 21:24:02', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('10', '2019050507', '12', '43', '1', '2019-05-16 21:24:02', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('11', '2019050507', '35', '50', '1', '2019-05-16 21:24:03', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('12', '2019050507', '26', '51', '1', '2019-05-16 21:24:03', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('13', '2019050507', '6', '52', '1', '2019-05-16 21:24:03', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('14', '2019050507', '36', '53', '1', '2019-05-16 22:21:39', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('15', '2019050507', '30', '54', '1', '2019-05-16 22:21:39', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('16', '2019040701', '21', '55', '1', '2019-05-16 22:30:28', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('17', '2019040701', '37', '56', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('18', '2019040701', '34', '43', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('19', '2019040701', '22', '44', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('20', '2019040701', '4', '43', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('21', '2019040701', '3', '43', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('22', '2019040701', '30', '57', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('23', '2019040701', '11', '58', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('24', '2019040701', '36', '59', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('25', '2019040701', '38', '60', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('26', '2019040701', '25', '61', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('27', '2019040701', '6', '62', '1', '2019-05-16 22:30:29', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('28', '2019040701', '26', '51', '1', '2019-05-16 22:30:30', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('29', '2019040701', '39', '63', '1', '2019-05-16 22:30:30', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('30', '2019040701', '23', '45', '1', '2019-05-16 22:30:30', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('31', '2019040701', '24', '47', '1', '2019-05-16 22:30:30', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('32', '2019040701', '15', '64', '1', '2019-05-16 22:30:30', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('33', '2019040701', '40', '65', '1', '2019-05-16 22:30:30', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('34', '2019040701', '19', '48', '1', '2019-05-16 22:30:30', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('35', '2019040701', '12', '43', '1', '2019-05-16 22:30:30', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('36', '2019040701', '10', '44', '1', '2019-05-16 22:30:30', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('37', '2019052401', '41', '66', '1', '2019-05-24 19:00:16', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('38', '2019052401', '21', '67', '1', '2019-05-24 19:00:16', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('39', '2019052401', '24', '68', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('40', '2019052401', '22', '69', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('41', '2019052401', '16', '70', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('42', '2019052401', '26', '71', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('43', '2019052401', '25', '72', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('44', '2019052401', '23', '73', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('45', '2019052401', '36', '74', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('46', '2019052401', '42', '75', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('47', '2019052401', '10', '69', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('48', '2019052401', '12', '76', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('49', '2019052401', '15', '77', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('50', '2019052401', '11', '78', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('51', '2019052401', '4', '43', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('52', '2019052401', '39', '79', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('53', '2019052401', '30', '80', '1', '2019-05-24 19:00:17', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('54', '2019052501', '7', '81', '1', '2019-05-25 18:39:26', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('55', '2019052501', '24', '47', '1', '2019-05-25 18:39:26', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('56', '2019052501', '25', '72', '1', '2019-05-25 18:39:26', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('57', '2019052501', '23', '73', '1', '2019-05-25 18:39:26', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('58', '2019052501', '36', '82', '1', '2019-05-25 18:39:27', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('59', '2019052501', '12', '76', '1', '2019-05-25 18:39:27', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('60', '2019052501', '15', '77', '1', '2019-05-25 18:39:27', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('61', '2019052501', '40', '83', '1', '2019-05-25 18:39:27', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('62', '2019052501', '4', '84', '1', '2019-05-25 18:39:27', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('63', '2019052501', '39', '63', '1', '2019-05-25 18:39:27', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('64', '2019052501', '30', '85', '1', '2019-05-25 18:39:27', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('65', '2019052502', '7', '81', '1', '2019-05-25 19:13:24', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('66', '2019052502', '24', '47', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('67', '2019052502', '22', '86', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('68', '2019052502', '25', '87', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('69', '2019052502', '5', '88', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('70', '2019052502', '36', '89', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('71', '2019052502', '12', '43', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('72', '2019052502', '15', '77', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('73', '2019052502', '11', '90', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('74', '2019052502', '40', '91', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('75', '2019052502', '4', '43', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('76', '2019052502', '39', '63', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('77', '2019052502', '30', '57', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('78', '2019050506', '31', '92', '1', '2019-05-27 11:36:36', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('79', '2019050506', '21', '93', '1', '2019-05-27 11:36:36', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('80', '2019050506', '24', '68', '1', '2019-05-27 11:36:36', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('81', '2019050506', '22', '44', '1', '2019-05-27 11:36:36', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('82', '2019050506', '25', '87', '1', '2019-05-27 11:36:36', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('83', '2019050506', '3', '43', '1', '2019-05-27 11:36:36', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('84', '2019050506', '23', '73', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('85', '2019050506', '10', '44', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('86', '2019050506', '25', '61', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('87', '2019050506', '36', '94', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('88', '2019050506', '6', '95', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('89', '2019050506', '10', '69', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('90', '2019050506', '34', '96', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('91', '2019050506', '12', '76', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('92', '2019050506', '15', '97', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('93', '2019050506', '24', '98', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('94', '2019050506', '15', '77', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('95', '2019050506', '11', '58', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('96', '2019050506', '40', '99', '1', '2019-05-27 11:36:38', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('97', '2019050506', '4', '43', '1', '2019-05-27 11:36:38', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('98', '2019050506', '39', '100', '1', '2019-05-27 11:36:38', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('99', '2019050506', '44', '101', '1', '2019-05-27 11:36:38', null);
INSERT INTO `tb_product_attribute_relation` VALUES ('100', '2019050506', '30', '80', '1', '2019-05-27 11:36:38', null);

-- ----------------------------
-- Table structure for tb_product_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_attribute_value`;
CREATE TABLE `tb_product_attribute_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute_id` int(11) DEFAULT NULL COMMENT '属性ID',
  `value` varchar(255) DEFAULT NULL COMMENT '属性值',
  `status` int(11) DEFAULT '1',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `value` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_attribute_value
-- ----------------------------
INSERT INTO `tb_product_attribute_value` VALUES ('1', '1', 'XXXS', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('2', '1', 'XXS', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('3', '1', 'XS', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('4', '1', 'S', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('5', '1', 'M', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('6', '1', 'L', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('7', '1', 'XL', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('8', '1', 'XXL', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('9', '1', 'XXXL', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('10', '1', 'XXXXL', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('11', '1', 'XXXXXL', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('12', '1', 'XXXXXXL', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('13', '2', '\r\n银色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('14', '2', '青色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('15', '2', '咖色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('16', '2', '驼色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('17', '2', '杏色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('18', '2', '米色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('19', '2', '裸色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('20', '2', '炫色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('21', '2', '褐色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('22', '2', '花色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('23', '2', '黄色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('24', '2', '棕色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('25', '2', '橙色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('26', '2', '白色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('27', '2', '绿色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('28', '2', '粉色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('29', '2', '紫色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('30', '2', '蓝色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('31', '2', '黑色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('32', '2', '红色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('33', '2', '灰色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('34', '2', '肤色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('35', '2', '卡其', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('36', '2', '肉色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('37', '15', '春,夏', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('43', '34', '常规', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('44', '22', '休闲', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('45', '23', '微弹', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('46', '11', '日常便服', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('47', '24', '通用', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('48', '19', '线上专供款', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('49', '10', '休闲,时尚,潮流,商务', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('50', '35', '男', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('51', '26', '透气', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('52', '6', 'POLO领', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('53', '36', '手洗，不可漂白，阴凉处悬挂晾干，低温熨烫，不可干洗', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('54', '30', '条纹', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('55', '21', '贴图', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('56', '37', '2018秋', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('57', '30', '字母', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('58', '11', '套头', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('59', '36', '30℃以下网袋机洗 不可漂白 悬挂晾干 低温熨烫 不可干洗 不可曝晒 反面洗涤 装饰工艺部位不可揉搓,不可熨烫 请勿浸泡 请与同类颜色衣物洗涤', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('60', '38', '200g', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('61', '25', '棉', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('62', '6', '圆领', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('63', '39', '中国', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('64', '15', '秋', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('65', '40', '面料-棉54% 莫代尔40.5% 氨纶5.5%', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('66', '41', '拉链', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('67', '21', '车缝线', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('68', '24', '青年', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('69', '22', '通勤', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('70', '16', '无填充', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('71', '26', '便携', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('72', '25', '聚酯纤维', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('73', '23', '无弹', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('74', '36', '最高洗涤温度30℃ 缓和程序 不可漂白 悬挂晾干 烫斗底板最高温度110℃ 不可干洗', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('75', '42', '中腰', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('76', '12', '薄', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('77', '15', '夏', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('78', '11', '小脚裤', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('79', '39', '中国浙江', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('80', '30', '纯色', '1', null, null);
INSERT INTO `tb_product_attribute_value` VALUES ('81', '7', '中长裙', '1', '2019-05-25 18:39:26', null);
INSERT INTO `tb_product_attribute_value` VALUES ('82', '36', '手洗，不可漂白，在阴凉处悬挂晾干，低温熨烫，缓和干洗', '1', '2019-05-25 18:39:27', null);
INSERT INTO `tb_product_attribute_value` VALUES ('83', '40', '面料-聚酯纤维100%,里料-聚酯纤维100%', '1', '2019-05-25 18:39:27', null);
INSERT INTO `tb_product_attribute_value` VALUES ('84', '4', '修身', '1', '2019-05-25 18:39:27', null);
INSERT INTO `tb_product_attribute_value` VALUES ('85', '30', '几何图案', '1', '2019-05-25 18:39:27', null);
INSERT INTO `tb_product_attribute_value` VALUES ('86', '22', '约会,度假,运动,休闲,逛街', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_value` VALUES ('87', '25', '锦纶(尼龙)', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_value` VALUES ('88', '5', '短袖', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_value` VALUES ('89', '36', '手洗，不可漂白，在阴凉处平摊晾干，低温熨烫，不可干洗', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_value` VALUES ('90', '11', '背心', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_value` VALUES ('91', '40', '面料-锦纶45.8%;棉33.4%再生纤维素纤维20.2%;其他纤维0.6%;(装饰、配料除外) 里料-聚酯纤维100%', '1', '2019-05-25 19:13:25', null);
INSERT INTO `tb_product_attribute_value` VALUES ('92', '31', '常规肩', '1', '2019-05-27 11:36:36', null);
INSERT INTO `tb_product_attribute_value` VALUES ('93', '21', '蕾丝', '1', '2019-05-27 11:36:36', null);
INSERT INTO `tb_product_attribute_value` VALUES ('94', '36', '手洗，不可漂白，悬挂晾干，熨斗底板最高温度110℃，不可干洗', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_value` VALUES ('95', '6', 'V领', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_value` VALUES ('96', '34', '泡泡袖', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_value` VALUES ('97', '15', '春', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_value` VALUES ('98', '24', '中年', '1', '2019-05-27 11:36:37', null);
INSERT INTO `tb_product_attribute_value` VALUES ('99', '40', '面料成分-棉100% 网纱成分-聚酯纤维100% 花边成分-聚酯纤维100%', '1', '2019-05-27 11:36:38', null);
INSERT INTO `tb_product_attribute_value` VALUES ('100', '39', '宁波', '1', '2019-05-27 11:36:38', null);
INSERT INTO `tb_product_attribute_value` VALUES ('101', '44', '后拉链', '1', '2019-05-27 11:36:38', null);

-- ----------------------------
-- Table structure for tb_product_color
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_color`;
CREATE TABLE `tb_product_color` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '颜色id',
  `productId` int(11) DEFAULT NULL COMMENT '商品ID',
  `color` varchar(255) NOT NULL COMMENT '颜色',
  `status` int(255) DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `color` (`color`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COMMENT='商品颜色表';

-- ----------------------------
-- Records of tb_product_color
-- ----------------------------
INSERT INTO `tb_product_color` VALUES ('59', '2019040701', '漂白', '1', '2019-04-08 09:28:14', null);
INSERT INTO `tb_product_color` VALUES ('60', '2019040701', '黑色', '1', '2019-04-08 09:28:17', null);
INSERT INTO `tb_product_color` VALUES ('61', '2019040702', '黑色', '1', '2019-04-08 13:06:35', null);
INSERT INTO `tb_product_color` VALUES ('62', '2019040702', '卡其色', '1', '2019-04-08 13:06:37', null);
INSERT INTO `tb_product_color` VALUES ('63', '2019040901', '漂白', '1', '2019-04-09 17:51:35', null);
INSERT INTO `tb_product_color` VALUES ('64', '2019040901', '灰粉红', '1', '2019-04-09 17:51:38', null);
INSERT INTO `tb_product_color` VALUES ('65', '2019041001', '浅黄', '1', '2019-04-10 11:53:40', null);
INSERT INTO `tb_product_color` VALUES ('66', '2019041001', '浅粉', '1', '2019-04-10 11:53:40', null);
INSERT INTO `tb_product_color` VALUES ('67', '2019041001', '蓝色', '1', '2019-04-10 11:53:40', null);
INSERT INTO `tb_product_color` VALUES ('68', '2019041001', '黑色', '1', '2019-04-10 11:53:40', null);
INSERT INTO `tb_product_color` VALUES ('69', '2019050501', '默认', '1', '2019-05-05 14:53:51', null);
INSERT INTO `tb_product_color` VALUES ('70', '2019050502', '默认', '1', '2019-05-05 15:04:53', null);
INSERT INTO `tb_product_color` VALUES ('71', '2019050503', '默认', '1', '2019-05-05 15:57:33', null);
INSERT INTO `tb_product_color` VALUES ('72', '2019050504', '默认', '1', '2019-05-05 16:01:30', null);
INSERT INTO `tb_product_color` VALUES ('73', '2019050505', '默认', '1', '2019-05-05 16:06:01', null);
INSERT INTO `tb_product_color` VALUES ('74', '2019050506', '默认', '1', '2019-05-05 16:14:44', null);
INSERT INTO `tb_product_color` VALUES ('75', '2019040801', '蓝色', '1', '2019-05-05 16:22:58', null);
INSERT INTO `tb_product_color` VALUES ('76', '2019040801', '黑色', '1', '2019-05-05 16:22:58', null);
INSERT INTO `tb_product_color` VALUES ('77', '2019050507', '深绿色', '1', '2019-05-05 17:43:24', null);
INSERT INTO `tb_product_color` VALUES ('78', '2019050507', '藏青色', '1', '2019-05-05 17:43:24', null);
INSERT INTO `tb_product_color` VALUES ('82', '2019052401', '白色', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_color` VALUES ('83', '2019052401', '黑色', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_color` VALUES ('84', '2019052401', '灰色', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_color` VALUES ('85', '2019052501', '默认', '1', '2019-05-25 18:30:53', null);
INSERT INTO `tb_product_color` VALUES ('86', '2019052502', '丈青', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_color` VALUES ('87', '2019052502', '本白', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_color` VALUES ('88', '2019052502', '粉红', '1', '2019-05-25 19:07:34', null);

-- ----------------------------
-- Table structure for tb_product_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_evaluation`;
CREATE TABLE `tb_product_evaluation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品评价',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `productId` int(11) DEFAULT NULL COMMENT '商品ID',
  `context` varchar(255) DEFAULT NULL COMMENT '内容',
  `status` varchar(255) DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '发表时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评价表';

-- ----------------------------
-- Records of tb_product_evaluation
-- ----------------------------

-- ----------------------------
-- Table structure for tb_product_image
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_image`;
CREATE TABLE `tb_product_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `productId` int(11) NOT NULL COMMENT '商品ID',
  `image` varchar(255) NOT NULL COMMENT '图片路径',
  `color_id` int(11) NOT NULL,
  `status` tinyint(255) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `tb_product_images_ibfk_2` (`color_id`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8 COMMENT='商品图片表';

-- ----------------------------
-- Records of tb_product_image
-- ----------------------------
INSERT INTO `tb_product_image` VALUES ('111', '2019040701', '783867336-28505610736574511-28505610736578917-2.jpg', '59', '1', '2019-04-08 00:05:13', null);
INSERT INTO `tb_product_image` VALUES ('112', '2019040701', '490688674-28505610736574511-28505610736578917-3.jpg', '59', '1', '2019-04-08 00:05:15', null);
INSERT INTO `tb_product_image` VALUES ('113', '2019040701', '521083837-28505610736574511-28505610736578917-15.jpg', '59', '1', '2019-04-08 00:05:18', null);
INSERT INTO `tb_product_image` VALUES ('114', '2019040701', '783867336-28505610736574511-28505610736578917-1.jpg', '59', '1', '2019-04-08 00:06:14', null);
INSERT INTO `tb_product_image` VALUES ('115', '2019040701', '338682210-28505610736574511-28505610736578670-15.jpg', '60', '1', '2019-04-08 00:06:14', null);
INSERT INTO `tb_product_image` VALUES ('116', '2019040701', '530671911-28505610736574511-28505610736578670-1.jpg', '60', '1', '2019-04-08 00:06:14', null);
INSERT INTO `tb_product_image` VALUES ('117', '2019040701', '530671911-28505610736574511-28505610736578670-2.jpg', '60', '1', '2019-04-08 00:06:14', null);
INSERT INTO `tb_product_image` VALUES ('118', '2019040701', '633399868-28505610736574511-28505610736578670-3.jpg', '60', '1', '2019-04-08 00:06:14', null);
INSERT INTO `tb_product_image` VALUES ('120', '2019040702', '9638578-438051563145240605-438051563145240694-1_64x64_90.jpg', '62', '1', '2019-04-08 13:16:26', null);
INSERT INTO `tb_product_image` VALUES ('121', '2019040702', '9638578-438051563145240605-438051563145240694-2_64x64_90.jpg', '62', '1', '2019-04-08 13:16:26', null);
INSERT INTO `tb_product_image` VALUES ('122', '2019040702', '18872552-438051563145240605-438051563145240694-15_64x64_90.jpg', '62', '1', '2019-04-08 13:16:26', null);
INSERT INTO `tb_product_image` VALUES ('123', '2019040702', '46574473-438051563145240605-438051563145240694-4_64x64_90.jpg', '62', '1', '2019-04-08 13:16:26', null);
INSERT INTO `tb_product_image` VALUES ('124', '2019040702', '885711824-438051563145240605-438051563145240694-3_64x64_90.jpg', '62', '1', '2019-04-08 13:16:26', null);
INSERT INTO `tb_product_image` VALUES ('125', '2019040702', '362398284-438051563145240605-438051563145240902-3_64x64_90.jpg', '61', '1', '2019-04-08 13:16:26', null);
INSERT INTO `tb_product_image` VALUES ('126', '2019040702', '1139591062-438051563145240605-438051563145240902-15_64x64_90.jpg', '61', '1', '2019-04-08 13:16:26', null);
INSERT INTO `tb_product_image` VALUES ('127', '2019040702', '1201535635-438051563145240605-438051563145240902-4_64x64_90.jpg', '61', '1', '2019-04-08 13:16:26', null);
INSERT INTO `tb_product_image` VALUES ('128', '2019040702', '1231161300-438051563145240605-438051563145240902-1_64x64_90.jpg', '61', '1', '2019-04-08 13:16:26', null);
INSERT INTO `tb_product_image` VALUES ('129', '2019040702', '1231161300-438051563145240605-438051563145240902-2_64x64_90.jpg', '61', '1', '2019-04-08 13:16:26', null);
INSERT INTO `tb_product_image` VALUES ('130', '2019040801', '560199143-221597417577037834-221597417577041937-1.jpg', '75', '1', '2019-04-08 13:23:28', null);
INSERT INTO `tb_product_image` VALUES ('131', '2019040801', '226445436-221597417577037834-221597417577041937-3.jpg', '75', '1', '2019-04-08 13:23:28', null);
INSERT INTO `tb_product_image` VALUES ('132', '2019040801', '637148923-221597417577037834-221597417577041937-2.jpg', '75', '1', '2019-04-08 13:23:28', null);
INSERT INTO `tb_product_image` VALUES ('133', '2019040801', '678701804-221597417577037834-221597417577041937-4.jpg', '75', '1', '2019-04-08 13:23:28', null);
INSERT INTO `tb_product_image` VALUES ('134', '2019040801', '1919901756-221597417577037834-221597417577041937-15.jpg', '75', '1', '2019-04-08 13:23:28', null);
INSERT INTO `tb_product_image` VALUES ('135', '2019040901', '318142210-104785299368529935-104785299368529961-1.jpg', '63', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('136', '2019040901', '318142210-104785299368529935-104785299368529961-2.jpg', '63', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('137', '2019040901', '1306562135-104785299368529935-104785299368529961-3.jpg', '63', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('138', '2019040901', '352384862-104785299368529935-104785299368529961-4.jpg', '63', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('139', '2019040901', '333147417-104785299368529935-104785299368529961-15.jpg', '63', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('140', '2019040901', '1105554643-104785299368529935-104785299368534060-1.jpg', '64', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('141', '2019040901', '1105554643-104785299368529935-104785299368534060-2.jpg', '64', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('142', '2019040901', '237973192-104785299368529935-104785299368534060-3.jpg', '64', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('143', '2019040901', '1230982784-104785299368529935-104785299368534060-4.jpg', '64', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('144', '2019040901', '877013796-104785299368529935-104785299368534060-15.jpg', '64', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('145', '2019041001', '1647471020-155732371651792896-155732371651801091-1.jpg', '68', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('146', '2019041001', '1372390881-155732371651792896-155732371651801091-2.jpg', '68', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('147', '2019041001', '1263539158-155732371651792896-155732371651801091-3.jpg', '68', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('148', '2019041001', '1372390881-155732371651792896-155732371651801091-4.jpg', '68', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('149', '2019041001', '1687100157-155732371651792896-155732371651801091-15.jpg', '68', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('150', '2019041001', '223023323-155732371651792896-155732371651805188-1.jpg', '65', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('151', '2019041001', '419107268-155732371651792896-155732371651805188-2.jpg', '65', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('152', '2019041001', '1207611082-155732371651792896-155732371651805188-3.jpg', '65', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('153', '2019041001', '419107268-155732371651792896-155732371651805188-4.jpg', '65', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('154', '2019041001', '120942194-155732371651792896-155732371651805188-15.jpg', '65', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('155', '2019041001', '1469563786-155732371651792896-155732371651792905-1.jpg', '66', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('156', '2019041001', '1268709460-155732371651792896-155732371651792905-2.jpg', '66', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('157', '2019041001', '1964704896-155732371651792896-155732371651792905-3.jpg', '66', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('158', '2019041001', '1268709460-155732371651792896-155732371651792905-4.jpg', '66', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('159', '2019041001', '1510347170-155732371651792896-155732371651792905-15.jpg', '66', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('160', '2019041001', '1149406652-155732371651792896-155732371651796995-1.jpg', '67', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('161', '2019041001', '1208719356-155732371651792896-155732371651796995-2.jpg', '67', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('162', '2019041001', '2125514612-155732371651792896-155732371651796995-3.jpg', '67', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('163', '2019041001', '1208719356-155732371651792896-155732371651796995-4.jpg', '67', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('164', '2019041001', '1166335604-155732371651792896-155732371651796995-15.jpg', '67', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('165', '2019050501', '7515e3f8-7ff8-48d0-8195-37bbff51b12c.jpg', '69', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('166', '2019050501', '764dd525-5280-417d-ae75-08f9fda0c961.jpg', '69', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('167', '2019050501', 'c238a3db-6477-42a2-af35-7a33b42b82c9.jpg', '69', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('168', '2019050501', '0a635061-1875-4869-a873-b8ecd560a4b1.jpg', '69', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('169', '2019050501', 'b190c914-6e83-446b-b7f7-ce3eecdc3ad3.jpg', '69', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('170', '2019050501', 'e6a97830-bed5-4be7-8c49-b129a2eaa3ce.jpg', '69', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('171', '2019050502', 'd1214cbc-7027-40a9-9528-c2340cccc469.jpg', '70', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('172', '2019050502', '4ca86427-f90a-4c56-af5f-b63ff42114e9.jpg', '70', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('173', '2019050502', '7a06abfb-83d9-4b60-8b13-94d5ba2708aa.jpg', '70', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('174', '2019050502', '69b8b45e-2a05-480b-93f3-441d3cdea95e.jpg', '70', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('175', '2019050502', '695c7e3e-e15a-4d98-910c-4698ccfc4103.jpg', '70', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('176', '2019050502', '843a8492-1400-4f65-9722-9c7b94357270.jpg', '70', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('177', '2019050503', '78763760-afbc-402a-b434-c17d444f3186.jpg', '71', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('178', '2019050503', '823c7e36-6e5c-464d-9a4f-68aa16340b85.jpg', '71', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('179', '2019050503', 'b2ff7586-28c5-442d-8e57-c8bdceb7bc76.jpg', '71', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('180', '2019050503', 'b1252c98-1f19-4406-8ab2-ac133c15aec4.jpg', '71', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('181', '2019050503', '6a42285a-16e1-40e8-b889-ad72801e283f.jpg', '71', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('182', '2019050504', 'ff6b9232-8bbd-4bf3-a0b6-09d2ff14183a.jpg', '72', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('183', '2019050504', '7637ee95-93bd-4038-8bef-75477af6e391.jpg', '72', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('184', '2019050504', 'b07e32f5-340c-4325-91fd-65102da0c7d9.jpg', '72', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('185', '2019050504', 'c745ad8d-b3cb-406d-888a-8431d9f5ba25.jpg', '72', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('186', '2019050504', '877038d3-b12a-4b63-831b-d115e71ba380.jpg', '72', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('187', '2019050505', '99177936-233700975291830272-233700975291830274-1.jpg', '73', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('188', '2019050505', '1693367190-233700975291830272-233700975291830274-2.jpg', '73', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('189', '2019050505', '1264002741-233700975291830272-233700975291830274-3.jpg', '73', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('190', '2019050505', '1145694944-233700975291830272-233700975291830274-4.jpg', '73', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('191', '2019050505', '1095125917-233700975291830272-233700975291830274-15.jpg', '73', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('192', '2019050505', '313685577-233700975291830272-233700975291830274-16.jpg', '73', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('193', '2019050506', '8dd808a4-cd24-4157-8d6f-754994a39275.jpg', '74', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('194', '2019050506', '63583474-95b1-4791-a313-9cb8fd23af1a.jpg', '74', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('195', '2019050506', 'f724f31a-bacf-4950-b633-563e974bc9c9.jpg', '74', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('196', '2019050506', '49abc0c5-2838-4080-975c-153c91e47d97.jpg', '74', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('197', '2019050506', '03887593-5416-4cf9-90ac-dc9d95fc7239.jpg', '74', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('198', '2019040801', '1281785576-221597417577037834-221597417577042002-1.jpg', '76', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('199', '2019040801', '1281785576-221597417577037834-221597417577042002-2.jpg', '76', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('200', '2019040801', '2040125658-221597417577037834-221597417577042002-3.jpg', '76', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('201', '2019040801', '1310256995-221597417577037834-221597417577042002-4.jpg', '76', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('202', '2019040801', '1289865303-221597417577037834-221597417577042002-15.jpg', '76', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('203', '2019050507', '3cfd2ef8-4997-454a-8c0c-5a7b6bc0b9af.jpg', '77', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('204', '2019050507', '0ef317fe-21a7-45ee-9a28-373cb327a1ac.jpg', '77', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('205', '2019050507', 'e098abb3-8551-40c0-a430-ccc51adb8abc.jpg', '77', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('206', '2019050507', '0b2908ad-ed2c-4509-ac77-15911453bb98.jpg', '77', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('207', '2019050507', '0b136108-bb08-49dd-8db7-33069f1677dd.jpg', '78', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('208', '2019050507', '2702496e-ed5d-4294-82d5-48c4c5fb1e9e.jpg', '78', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('209', '2019050507', 'de324c1b-c482-4c77-a5f0-f9a661998160.jpg', '78', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('210', '2019050507', '17a83ea2-6d91-4a5d-95cc-d7aafbcaa68f.jpg', '78', '1', null, null);
INSERT INTO `tb_product_image` VALUES ('226', '2019052401', 'bdc1dbe5-0a07-48ce-8e9b-9c401bb991ca.jpg', '82', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('227', '2019052401', '3a0617a5-4540-4af7-afbd-6546ba0313ba.jpg', '82', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('228', '2019052401', '62d2f7eb-28d3-4dcb-8444-3038419a43c3.jpg', '82', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('229', '2019052401', '67f110a5-e7cf-4d4c-a0a5-02b4239d7bff.jpg', '82', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('230', '2019052401', 'b7136e71-a1e9-4408-a2ad-03e479ffa5de.jpg', '82', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('231', '2019052401', 'bd4dc730-5c1c-4f69-a50b-bb7d76bccb06.jpg', '83', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('232', '2019052401', 'a884c0ea-d31a-4869-95ed-043e59b19ca8.jpg', '83', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('233', '2019052401', '66fef4e0-5d22-4b65-aeba-c5d74d0762ba.jpg', '83', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('234', '2019052401', '4669ff67-53c7-4432-88c0-c6e7f3a70b88.jpg', '83', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('235', '2019052401', '68681ff9-dc53-4ca1-9f66-4bcb0dcc54ae.jpg', '83', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('236', '2019052401', '11b94e75-db7e-4240-a5a1-b983aa28db58.jpg', '84', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('237', '2019052401', '42634933-cdf2-47da-9cf3-5c6e2b49ce15.jpg', '84', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('238', '2019052401', '2cfa9ebe-7e1e-4841-9acc-167734152932.jpg', '84', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('239', '2019052401', 'd32b5878-6ff0-46ef-89c9-0ba816a7d13b.jpg', '84', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('240', '2019052401', '1c753c1c-81af-4aa2-9823-f5c6ca2435bb.jpg', '84', '1', '2019-05-24 17:58:33', null);
INSERT INTO `tb_product_image` VALUES ('241', '2019052501', '8a00fbdf-b93f-4daa-8646-e38ead807b43.jpg', '85', '1', '2019-05-25 18:30:53', null);
INSERT INTO `tb_product_image` VALUES ('242', '2019052501', '457b3f99-ecaa-4602-afbe-850cd18f175f.jpg', '85', '1', '2019-05-25 18:30:53', null);
INSERT INTO `tb_product_image` VALUES ('243', '2019052501', '49457dc1-eb9c-4641-9733-b486a7a2cc04.jpg', '85', '1', '2019-05-25 18:30:53', null);
INSERT INTO `tb_product_image` VALUES ('244', '2019052501', '054ce4ab-a607-4bb9-8421-e2303c307bfd.jpg', '85', '1', '2019-05-25 18:30:53', null);
INSERT INTO `tb_product_image` VALUES ('245', '2019052502', 'f6094ea9-420f-4765-9814-63f0fd6d4ff2.jpg', '86', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('246', '2019052502', '5a2998ef-2246-4e9d-94c9-614fcf39e514.jpg', '86', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('247', '2019052502', '82cce40f-c555-4c75-b980-eae477d502fc.jpg', '86', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('248', '2019052502', 'a8cdaca7-e3ad-4d6f-87a5-9d563785e4b6.jpg', '86', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('249', '2019052502', '92a8b9e8-456b-4264-88b3-4df99f8ed4b0.jpg', '86', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('250', '2019052502', 'e91c5b24-9cda-4e24-ab69-9a1e0bc6b454.jpg', '86', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('251', '2019052502', '535255ef-1837-4242-817f-b4a393364597.jpg', '86', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('252', '2019052502', '52c82be5-a194-446e-a5ab-0e28fc817520.jpg', '86', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('253', '2019052502', '2ec197b4-812a-43df-8f99-2fd4a8962545.jpg', '86', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('254', '2019052502', 'ab560228-d616-4612-884d-6643b3989204.jpg', '86', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('255', '2019052502', '36f4c9cd-5a58-453e-800c-88b83088c4a6.jpg', '86', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('256', '2019052502', '98659d23-b114-4b7b-98df-631be167016c.jpg', '86', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('257', '2019052502', 'f6094ea9-420f-4765-9814-63f0fd6d4ff2.jpg', '87', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('258', '2019052502', '5a2998ef-2246-4e9d-94c9-614fcf39e514.jpg', '87', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('259', '2019052502', '82cce40f-c555-4c75-b980-eae477d502fc.jpg', '87', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('260', '2019052502', 'a8cdaca7-e3ad-4d6f-87a5-9d563785e4b6.jpg', '87', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('261', '2019052502', '92a8b9e8-456b-4264-88b3-4df99f8ed4b0.jpg', '87', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('262', '2019052502', 'e91c5b24-9cda-4e24-ab69-9a1e0bc6b454.jpg', '87', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('263', '2019052502', '535255ef-1837-4242-817f-b4a393364597.jpg', '87', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('264', '2019052502', '52c82be5-a194-446e-a5ab-0e28fc817520.jpg', '87', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('265', '2019052502', '2ec197b4-812a-43df-8f99-2fd4a8962545.jpg', '87', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('266', '2019052502', 'ab560228-d616-4612-884d-6643b3989204.jpg', '87', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('267', '2019052502', '36f4c9cd-5a58-453e-800c-88b83088c4a6.jpg', '87', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('268', '2019052502', '98659d23-b114-4b7b-98df-631be167016c.jpg', '87', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('269', '2019052502', 'f6094ea9-420f-4765-9814-63f0fd6d4ff2.jpg', '88', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('270', '2019052502', '5a2998ef-2246-4e9d-94c9-614fcf39e514.jpg', '88', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('271', '2019052502', '82cce40f-c555-4c75-b980-eae477d502fc.jpg', '88', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('272', '2019052502', 'a8cdaca7-e3ad-4d6f-87a5-9d563785e4b6.jpg', '88', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('273', '2019052502', '92a8b9e8-456b-4264-88b3-4df99f8ed4b0.jpg', '88', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('274', '2019052502', 'e91c5b24-9cda-4e24-ab69-9a1e0bc6b454.jpg', '88', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('275', '2019052502', '535255ef-1837-4242-817f-b4a393364597.jpg', '88', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('276', '2019052502', '52c82be5-a194-446e-a5ab-0e28fc817520.jpg', '88', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('277', '2019052502', '2ec197b4-812a-43df-8f99-2fd4a8962545.jpg', '88', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('278', '2019052502', 'ab560228-d616-4612-884d-6643b3989204.jpg', '88', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('279', '2019052502', '36f4c9cd-5a58-453e-800c-88b83088c4a6.jpg', '88', '1', '2019-05-25 19:07:34', null);
INSERT INTO `tb_product_image` VALUES ('280', '2019052502', '98659d23-b114-4b7b-98df-631be167016c.jpg', '88', '1', '2019-05-25 19:07:34', null);

-- ----------------------------
-- Table structure for tb_product_num
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_num`;
CREATE TABLE `tb_product_num` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '库存ID',
  `productId` int(11) NOT NULL COMMENT '商品ID',
  `productColor` varchar(255) NOT NULL COMMENT '商品颜色',
  `productSize` varchar(255) NOT NULL COMMENT '商品尺寸',
  `num` int(11) NOT NULL COMMENT '库存',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `productColor` (`productColor`),
  KEY `productSize` (`productSize`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8 COMMENT='商品库存表';

-- ----------------------------
-- Records of tb_product_num
-- ----------------------------
INSERT INTO `tb_product_num` VALUES ('1', '2019040701', '漂白', 'XS', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('2', '2019040701', '漂白', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('3', '2019040701', '漂白', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('4', '2019040701', '漂白', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('5', '2019040701', '黑色', 'XS', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('6', '2019040701', '黑色', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('7', '2019040701', '黑色', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('8', '2019040701', '黑色', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('9', '2019040702', '黑色', 'XS', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('10', '2019040702', '黑色', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('11', '2019040702', '黑色', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('12', '2019040702', '黑色', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('13', '2019040702', '黑色', 'XL', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('14', '2019040702', '卡其色', 'XS', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('15', '2019040702', '卡其色', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('16', '2019040702', '卡其色', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('17', '2019040702', '卡其色', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('18', '2019040702', '卡其色', 'XL', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('19', '2019040901', '漂白', 'XS', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('20', '2019040901', '漂白', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('21', '2019040901', '漂白', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('22', '2019040901', '漂白', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('23', '2019040901', '漂白', 'XL', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('24', '2019040901', '灰粉红', 'XS', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('25', '2019040901', '灰粉红', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('26', '2019040901', '灰粉红', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('27', '2019040901', '灰粉红', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('28', '2019040901', '灰粉红', 'XL', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('29', '2019041001', '浅黄', 'XS', '9', null, null);
INSERT INTO `tb_product_num` VALUES ('30', '2019041001', '浅黄', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('31', '2019041001', '浅黄', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('32', '2019041001', '浅黄', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('33', '2019041001', '浅黄', 'XL', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('34', '2019041001', '浅粉', 'XS', '9', null, null);
INSERT INTO `tb_product_num` VALUES ('35', '2019041001', '浅粉', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('36', '2019041001', '浅粉', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('37', '2019041001', '浅粉', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('38', '2019041001', '浅粉', 'XL', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('39', '2019041001', '蓝色', 'XS', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('40', '2019041001', '蓝色', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('41', '2019041001', '蓝色', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('42', '2019041001', '蓝色', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('43', '2019041001', '蓝色', 'XL', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('44', '2019041001', '黑色', 'XS', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('45', '2019041001', '黑色', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('46', '2019041001', '黑色', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('47', '2019041001', '黑色', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('48', '2019041001', '黑色', 'XL', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('49', '2019050501', '默认', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('50', '2019050501', '默认', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('51', '2019050501', '默认', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('52', '2019050501', '默认', 'XL', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('53', '2019050502', '默认', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('54', '2019050502', '默认', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('55', '2019050502', '默认', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('56', '2019050502', '默认', 'XL', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('57', '2019050502', '默认', 'XXL', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('58', '2019050503', '默认', 'S', '9', null, null);
INSERT INTO `tb_product_num` VALUES ('59', '2019050503', '默认', 'M', '0', null, null);
INSERT INTO `tb_product_num` VALUES ('60', '2019050504', '默认', 'XS', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('61', '2019050504', '默认', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('62', '2019050504', '默认', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('63', '2019050504', '默认', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('64', '2019050505', '默认', 'XS', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('65', '2019050505', '默认', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('66', '2019050505', '默认', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('67', '2019050505', '默认', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('68', '2019050506', '默认', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('69', '2019050506', '默认', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('70', '2019050506', '默认', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('71', '2019050506', '默认', 'XL', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('72', '2019040801', '蓝色', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('73', '2019040801', '蓝色', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('74', '2019040801', '蓝色', 'L', '9', null, null);
INSERT INTO `tb_product_num` VALUES ('75', '2019040801', '黑色', 'S', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('76', '2019040801', '黑色', 'M', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('77', '2019040801', '黑色', 'L', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('78', '2019050507', '深绿色', '48A', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('79', '2019050507', '深绿色', '50A', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('80', '2019050507', '深绿色', '52A', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('81', '2019050507', '深绿色', '54A', '9', null, null);
INSERT INTO `tb_product_num` VALUES ('82', '2019050507', '藏青色', '48A', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('83', '2019050507', '藏青色', '50A', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('84', '2019050507', '藏青色', '52A', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('85', '2019050507', '藏青色', '54A', '10', null, null);
INSERT INTO `tb_product_num` VALUES ('119', '2019052401', '白色', '28(165/72A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('120', '2019052401', '白色', '29(170/76A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('121', '2019052401', '白色', '26(155/64A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('122', '2019052401', '白色', '27(160/68A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('123', '2019052401', '白色', '30(175/80A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('124', '2019052401', '白色', '32(185/88A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('125', '2019052401', '白色', '33(190/92A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('126', '2019052401', '白色', '31(180/84A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('127', '2019052401', '黑色', '28(165/72A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('128', '2019052401', '黑色', '29(170/76A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('129', '2019052401', '黑色', '26(155/64A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('130', '2019052401', '黑色', '27(160/68A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('131', '2019052401', '黑色', '30(175/80A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('132', '2019052401', '黑色', '32(185/88A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('133', '2019052401', '黑色', '33(190/92A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('134', '2019052401', '黑色', '31(180/84A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('135', '2019052401', '灰色', '28(165/72A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('136', '2019052401', '灰色', '29(170/76A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('137', '2019052401', '灰色', '26(155/64A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('138', '2019052401', '灰色', '27(160/68A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('139', '2019052401', '灰色', '30(175/80A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('140', '2019052401', '灰色', '32(185/88A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('141', '2019052401', '灰色', '33(190/92A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('142', '2019052401', '灰色', '31(180/84A)', '100', '2019-05-24 18:47:27', null);
INSERT INTO `tb_product_num` VALUES ('143', '2019052501', '默认', 'XXL', '100', '2019-05-25 18:31:57', null);
INSERT INTO `tb_product_num` VALUES ('144', '2019052501', '默认', 'XL', '100', '2019-05-25 18:31:57', null);
INSERT INTO `tb_product_num` VALUES ('145', '2019052501', '默认', 'M', '100', '2019-05-25 18:31:57', null);
INSERT INTO `tb_product_num` VALUES ('146', '2019052501', '默认', 'L', '100', '2019-05-25 18:31:57', null);
INSERT INTO `tb_product_num` VALUES ('147', '2019052501', '默认', 'S', '100', '2019-05-25 18:31:57', null);
INSERT INTO `tb_product_num` VALUES ('148', '2019052502', '丈青', 'M', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('149', '2019052502', '丈青', 'L', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('150', '2019052502', '丈青', 'XL', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('151', '2019052502', '丈青', 'XXL', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('152', '2019052502', '丈青', 'S', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('153', '2019052502', '本白', 'M', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('154', '2019052502', '本白', 'L', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('155', '2019052502', '本白', 'XL', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('156', '2019052502', '本白', 'XXL', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('157', '2019052502', '本白', 'S', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('158', '2019052502', '粉红', 'M', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('159', '2019052502', '粉红', 'L', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('160', '2019052502', '粉红', 'XL', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('161', '2019052502', '粉红', 'XXL', '100', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_num` VALUES ('162', '2019052502', '粉红', 'S', '100', '2019-05-25 19:07:37', null);

-- ----------------------------
-- Table structure for tb_product_size
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_size`;
CREATE TABLE `tb_product_size` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) DEFAULT NULL,
  `size` varchar(255) NOT NULL,
  `status` tinyint(4) DEFAULT '1',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `size` (`size`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8 COMMENT='商品尺寸表';

-- ----------------------------
-- Records of tb_product_size
-- ----------------------------
INSERT INTO `tb_product_size` VALUES ('111', '2019040701', 'XS', '1', '2019-04-08 09:39:32', null);
INSERT INTO `tb_product_size` VALUES ('112', '2019040701', 'S', '1', '2019-04-08 09:39:32', null);
INSERT INTO `tb_product_size` VALUES ('113', '2019040701', 'M', '1', '2019-04-08 09:39:32', null);
INSERT INTO `tb_product_size` VALUES ('114', '2019040701', 'L', '1', '2019-04-08 09:39:32', null);
INSERT INTO `tb_product_size` VALUES ('115', '2019040702', 'XS', '1', '2019-04-08 09:39:32', null);
INSERT INTO `tb_product_size` VALUES ('116', '2019040702', 'S', '1', '2019-04-08 09:39:32', null);
INSERT INTO `tb_product_size` VALUES ('117', '2019040702', 'M', '1', '2019-04-08 09:39:32', null);
INSERT INTO `tb_product_size` VALUES ('118', '2019040702', 'L', '1', '2019-04-08 09:39:32', null);
INSERT INTO `tb_product_size` VALUES ('119', '2019040702', 'XL', '1', '2019-04-08 09:39:32', null);
INSERT INTO `tb_product_size` VALUES ('120', '2019040901', 'XS', '1', '2019-04-09 17:58:04', null);
INSERT INTO `tb_product_size` VALUES ('121', '2019040901', 'S', '1', '2019-04-09 17:58:11', null);
INSERT INTO `tb_product_size` VALUES ('122', '2019040901', 'M', '1', '2019-04-09 17:58:11', null);
INSERT INTO `tb_product_size` VALUES ('123', '2019040901', 'L', '1', '2019-04-09 17:58:11', null);
INSERT INTO `tb_product_size` VALUES ('124', '2019040901', 'XL', '1', '2019-04-09 17:58:11', null);
INSERT INTO `tb_product_size` VALUES ('125', '2019041001', 'XS', '1', '2019-05-05 14:54:30', null);
INSERT INTO `tb_product_size` VALUES ('126', '2019041001', 'S', '1', '2019-05-05 14:54:30', null);
INSERT INTO `tb_product_size` VALUES ('127', '2019041001', 'M', '1', '2019-05-05 14:54:30', null);
INSERT INTO `tb_product_size` VALUES ('128', '2019041001', 'L', '1', '2019-05-05 14:54:30', null);
INSERT INTO `tb_product_size` VALUES ('129', '2019041001', 'XL', '1', '2019-05-05 14:54:30', null);
INSERT INTO `tb_product_size` VALUES ('130', '2019050501', 'S', '1', '2019-05-05 14:54:30', null);
INSERT INTO `tb_product_size` VALUES ('131', '2019050501', 'M', '1', '2019-05-05 14:54:30', null);
INSERT INTO `tb_product_size` VALUES ('132', '2019050501', 'L', '1', '2019-05-05 14:54:30', null);
INSERT INTO `tb_product_size` VALUES ('133', '2019050501', 'XL', '1', '2019-05-05 14:54:30', null);
INSERT INTO `tb_product_size` VALUES ('134', '2019050502', 'S', '1', '2019-05-05 15:05:29', null);
INSERT INTO `tb_product_size` VALUES ('135', '2019050502', 'M', '1', '2019-05-05 15:05:29', null);
INSERT INTO `tb_product_size` VALUES ('136', '2019050502', 'L', '1', '2019-05-05 15:05:29', null);
INSERT INTO `tb_product_size` VALUES ('137', '2019050502', 'XL', '1', '2019-05-05 15:05:29', null);
INSERT INTO `tb_product_size` VALUES ('138', '2019050502', 'XXL', '1', '2019-05-05 15:05:29', null);
INSERT INTO `tb_product_size` VALUES ('139', '2019050503', 'S', '1', '2019-05-05 15:57:11', null);
INSERT INTO `tb_product_size` VALUES ('140', '2019050503', 'M', '1', '2019-05-05 15:57:13', null);
INSERT INTO `tb_product_size` VALUES ('141', '2019050504', 'XS', '1', '2019-05-05 16:02:01', null);
INSERT INTO `tb_product_size` VALUES ('142', '2019050504', 'S', '1', '2019-05-05 16:02:01', null);
INSERT INTO `tb_product_size` VALUES ('143', '2019050504', 'M', '1', '2019-05-05 16:02:01', null);
INSERT INTO `tb_product_size` VALUES ('144', '2019050504', 'L', '1', '2019-05-05 16:02:01', null);
INSERT INTO `tb_product_size` VALUES ('145', '2019050505', 'XS', '1', '2019-05-05 16:07:34', null);
INSERT INTO `tb_product_size` VALUES ('146', '2019050505', 'S', '1', '2019-05-05 16:07:34', null);
INSERT INTO `tb_product_size` VALUES ('147', '2019050505', 'M', '1', '2019-05-05 16:07:34', null);
INSERT INTO `tb_product_size` VALUES ('148', '2019050505', 'L', '1', '2019-05-05 16:07:34', null);
INSERT INTO `tb_product_size` VALUES ('149', '2019050506', 'S', '1', '2019-05-05 16:23:32', null);
INSERT INTO `tb_product_size` VALUES ('150', '2019050506', 'M', '1', '2019-05-05 16:23:32', null);
INSERT INTO `tb_product_size` VALUES ('151', '2019050506', 'L', '1', '2019-05-05 16:23:32', null);
INSERT INTO `tb_product_size` VALUES ('152', '2019050506', 'XL', '1', '2019-05-05 16:23:32', null);
INSERT INTO `tb_product_size` VALUES ('153', '2019040801', 'S', '1', '2019-05-05 16:23:32', null);
INSERT INTO `tb_product_size` VALUES ('154', '2019040801', 'M', '1', '2019-05-05 16:23:32', null);
INSERT INTO `tb_product_size` VALUES ('155', '2019040801', 'L', '1', '2019-05-05 16:23:32', null);
INSERT INTO `tb_product_size` VALUES ('156', '2019050507', '48A', '1', '2019-05-05 17:43:24', null);
INSERT INTO `tb_product_size` VALUES ('157', '2019050507', '50A', '1', '2019-05-05 17:43:24', null);
INSERT INTO `tb_product_size` VALUES ('158', '2019050507', '52A', '1', '2019-05-05 17:43:24', null);
INSERT INTO `tb_product_size` VALUES ('159', '2019050507', '54A', '1', '2019-05-05 17:43:24', null);
INSERT INTO `tb_product_size` VALUES ('160', '2019052401', '26(155/64A)', '1', '2019-05-24 14:52:02', null);
INSERT INTO `tb_product_size` VALUES ('161', '2019052401', '27(160/68A)', '1', '2019-05-24 14:52:02', null);
INSERT INTO `tb_product_size` VALUES ('162', '2019052401', '28(165/72A)', '1', '2019-05-24 14:52:02', null);
INSERT INTO `tb_product_size` VALUES ('163', '2019052401', '29(170/76A)', '1', '2019-05-24 14:52:02', null);
INSERT INTO `tb_product_size` VALUES ('164', '2019052401', '30(175/80A)', '1', '2019-05-24 14:52:02', null);
INSERT INTO `tb_product_size` VALUES ('165', '2019052401', '31(180/84A)', '1', '2019-05-24 14:52:02', null);
INSERT INTO `tb_product_size` VALUES ('166', '2019052401', '32(185/88A)', '1', '2019-05-24 14:52:02', null);
INSERT INTO `tb_product_size` VALUES ('167', '2019052401', '33(190/92A)', '1', '2019-05-24 14:52:02', null);
INSERT INTO `tb_product_size` VALUES ('168', '2019052501', 'S', '1', '2019-05-25 18:31:39', null);
INSERT INTO `tb_product_size` VALUES ('169', '2019052501', 'M', '1', '2019-05-25 18:31:39', null);
INSERT INTO `tb_product_size` VALUES ('170', '2019052501', 'L', '1', '2019-05-25 18:31:39', null);
INSERT INTO `tb_product_size` VALUES ('171', '2019052501', 'XL', '1', '2019-05-25 18:31:39', null);
INSERT INTO `tb_product_size` VALUES ('172', '2019052501', 'XXL', '1', '2019-05-25 18:31:39', null);
INSERT INTO `tb_product_size` VALUES ('173', '2019052502', 'S', '1', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_size` VALUES ('174', '2019052502', 'M', '1', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_size` VALUES ('175', '2019052502', 'L', '1', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_size` VALUES ('176', '2019052502', 'XL', '1', '2019-05-25 19:07:37', null);
INSERT INTO `tb_product_size` VALUES ('177', '2019052502', 'XXL', '1', '2019-05-25 19:07:37', null);

-- ----------------------------
-- Table structure for tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL,
  `resource_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='url资源表';

-- ----------------------------
-- Records of tb_resource
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `name_zh` varchar(255) DEFAULT NULL,
  `create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role_people
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_people`;
CREATE TABLE `tb_role_people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `people_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员-角色表';

-- ----------------------------
-- Records of tb_role_people
-- ----------------------------

-- ----------------------------
-- Table structure for tb_seller
-- ----------------------------
DROP TABLE IF EXISTS `tb_seller`;
CREATE TABLE `tb_seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_no` varchar(27) DEFAULT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '商户名称',
  `show_image` varchar(255) DEFAULT NULL COMMENT '展示图片',
  `type` varchar(255) DEFAULT NULL COMMENT '经营类型',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='商户基本信息表';

-- ----------------------------
-- Records of tb_seller
-- ----------------------------
INSERT INTO `tb_seller` VALUES ('1', null, '1', 'betu', 'ias_154156218189909_570x273_90.jpg', '1', '1', '2019-05-05 16:55:01', null);
INSERT INTO `tb_seller` VALUES ('2', null, '2', '白鹿语好货直降专场', 'ias_155791361570328_570x273_90.jpg', '1', '1', '2019-05-05 16:55:01', null);
INSERT INTO `tb_seller` VALUES ('3', null, '3', '索菲丝尔', 'ias_155808277883499_570x273_90.jpg', '1', '1', '2019-05-05 16:55:01', null);
INSERT INTO `tb_seller` VALUES ('4', null, '4', '玖姿', 'ias_155797018421886_570x273_90.jpg', '1', '1', '2019-05-05 16:55:01', null);
INSERT INTO `tb_seller` VALUES ('5', null, '5', 'LUXLEAD', 'ias_155332492735371_570x273_90.jpg', '1', '1', '2019-05-05 16:55:01', null);
INSERT INTO `tb_seller` VALUES ('6', null, '6', '裂帛', 'ias_155210873864823_570x273_90.jpg', '1', '1', '2019-05-05 16:54:57', null);
INSERT INTO `tb_seller` VALUES ('7', null, '7', 'lagogo', 'ias_155410988763035_570x273_90.jpg', '1', '1', '2019-05-05 16:54:54', null);
INSERT INTO `tb_seller` VALUES ('8', null, '8', 'ochirly', 'ias_155427231581937_570x273_90.jpg', '1', '1', '2019-04-05 23:16:03', null);
INSERT INTO `tb_seller` VALUES ('9', null, '9', '七匹狼SEPTWOLVES男装特卖旗舰店', 'ias_155425665162408_570x273_90.jpg', '2', '1', '2019-05-05 17:38:14', null);

-- ----------------------------
-- Table structure for tb_seller_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_seller_account`;
CREATE TABLE `tb_seller_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_username` varchar(255) DEFAULT NULL,
  `seller_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家账户表';

-- ----------------------------
-- Records of tb_seller_account
-- ----------------------------

-- ----------------------------
-- Table structure for tb_seller_image
-- ----------------------------
DROP TABLE IF EXISTS `tb_seller_image`;
CREATE TABLE `tb_seller_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户图片表';

-- ----------------------------
-- Records of tb_seller_image
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_no` varchar(27) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT '唯品会会员' COMMENT '用户昵称',
  `telephone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `birthday` varchar(255) DEFAULT NULL COMMENT '生日',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `vip` varchar(4) DEFAULT '青铜' COMMENT '会员等级',
  `head_image` varchar(255) DEFAULT 'avatar_89373029_1496285287409.jpg' COMMENT '头像',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('26', 'U811520190619', '李慢慢是头猪', '17796645107', '大傻子', '男', '2019-06-19', '222@qq.com', '青铜', 'avatar_89373029_1496285287409.jpg', '1', '2019-06-19 11:57:07', null);
INSERT INTO `tb_user` VALUES ('27', 'U317720190619', '唯品会会员', '12345678900', null, null, null, null, '青铜', 'avatar_89373029_1496285287409.jpg', '1', '2019-06-19 16:24:40', null);

-- ----------------------------
-- Table structure for tb_user_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_address`;
CREATE TABLE `tb_user_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '收货人姓名',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '市区',
  `town` varchar(255) DEFAULT NULL COMMENT '乡镇',
  `area` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `postcode` varchar(255) DEFAULT NULL COMMENT '邮政编码',
  `telephone` varchar(255) DEFAULT NULL COMMENT '手机',
  `status` tinyint(4) DEFAULT '1',
  `is_default` tinyint(4) DEFAULT '0' COMMENT '是否默认收货地址',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='用户收货地址表';

-- ----------------------------
-- Records of tb_user_address
-- ----------------------------
INSERT INTO `tb_user_address` VALUES ('11', '26', '李慢慢1', '河南省1', '郑州市1', '龙湖镇1', '河南工程学院西区1', '66666661', '133337137191', '1', '0', '2019-06-19 15:18:08', '2019-06-19 15:28:47');

-- ----------------------------
-- Table structure for tb_user_collect_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_collect_product`;
CREATE TABLE `tb_user_collect_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户商品收藏类';

-- ----------------------------
-- Records of tb_user_collect_product
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_collect_seller
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_collect_seller`;
CREATE TABLE `tb_user_collect_seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户店铺收藏类';

-- ----------------------------
-- Records of tb_user_collect_seller
-- ----------------------------
INSERT INTO `tb_user_collect_seller` VALUES ('4', '26', '5');
INSERT INTO `tb_user_collect_seller` VALUES ('5', '26', '1');
INSERT INTO `tb_user_collect_seller` VALUES ('6', '26', '2');

-- ----------------------------
-- Table structure for tb_user_image
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_image`;
CREATE TABLE `tb_user_image` (
  `user_id` int(11) NOT NULL,
  `head_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_image
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_pwd
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_pwd`;
CREATE TABLE `tb_user_pwd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户密码';

-- ----------------------------
-- Records of tb_user_pwd
-- ----------------------------
INSERT INTO `tb_user_pwd` VALUES ('3', '26', '986531A0681D00306EA4515C54D5FB1E');
INSERT INTO `tb_user_pwd` VALUES ('4', '27', '986531A0681D00306EA4515C54D5FB1E');

-- ----------------------------
-- Table structure for tb_user_safe
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_safe`;
CREATE TABLE `tb_user_safe` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `question1` varchar(255) DEFAULT NULL COMMENT '安全问题1',
  `answer1` varchar(255) DEFAULT NULL COMMENT '答案1',
  `question2` varchar(255) DEFAULT NULL COMMENT '安全问题2',
  `answer2` varchar(255) DEFAULT NULL COMMENT '答案2',
  `question3` varchar(255) DEFAULT NULL COMMENT '安全问题3',
  `answer3` varchar(255) DEFAULT NULL COMMENT '答案3',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户安全信息表';

-- ----------------------------
-- Records of tb_user_safe
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_search
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_search`;
CREATE TABLE `tb_user_search` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `search_title` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户搜索历史表';

-- ----------------------------
-- Records of tb_user_search
-- ----------------------------
INSERT INTO `tb_user_search` VALUES ('6', '1', '李慢慢', null);
INSERT INTO `tb_user_search` VALUES ('7', '1', '李慢慢吃屁', null);
