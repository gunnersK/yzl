/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : yinglin

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-07-09 22:41:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yzl_xb
-- ----------------------------
DROP TABLE IF EXISTS `yzl_xb`;
CREATE TABLE `yzl_xb` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `BSM` varchar(50) DEFAULT NULL COMMENT '标识码',
  `CITY` varchar(50) NOT NULL COMMENT '市',
  `COUNTY` varchar(50) NOT NULL COMMENT '县',
  `TOWN` varchar(50) DEFAULT NULL COMMENT '乡',
  `VILLAGE` varchar(50) DEFAULT NULL COMMENT '村',
  `LIN_BAN` varchar(50) DEFAULT NULL COMMENT '林班',
  `JYING_BAN` varchar(50) DEFAULT NULL COMMENT '经营班',
  `XIAO_BAN` varchar(50) DEFAULT NULL COMMENT '小班',
  `ZY_XB` varchar(50) DEFAULT NULL COMMENT '作业小班',
  `YDH` varchar(50) DEFAULT NULL COMMENT '样地号',
  `TFH` varchar(50) DEFAULT NULL COMMENT '图幅号',
  `GCLB` varchar(50) NOT NULL COMMENT '工程类别',
  `ZLLB` varchar(50) NOT NULL COMMENT '造林类别',
  `JHND` varchar(50) NOT NULL COMMENT '计划年度',
  `ZYND` varchar(50) NOT NULL COMMENT '作业年度',
  `ZLQ_DILEI` varchar(50) DEFAULT NULL COMMENT '造林（封育）前地类',
  `DILEI` varchar(50) DEFAULT NULL COMMENT '地类',
  `FYLX` varchar(50) DEFAULT NULL COMMENT '封育类型',
  `FYFS` varchar(50) DEFAULT NULL COMMENT '封育方式',
  `LDQS` varchar(50) DEFAULT NULL COMMENT '林地权属',
  `LMQS` varchar(50) DEFAULT NULL COMMENT '林木权属',
  `LZ` varchar(50) DEFAULT NULL COMMENT '林种',
  `SZ1` varchar(50) DEFAULT NULL COMMENT '树种1',
  `SZ2` varchar(50) DEFAULT NULL COMMENT '树种2',
  `BILI` varchar(50) DEFAULT NULL COMMENT '比例',
  `ZBLX` varchar(50) DEFAULT NULL COMMENT '植被类型',
  `PO_DU` varchar(50) DEFAULT NULL COMMENT '坡度',
  `XTJSBMJ` varchar(50) DEFAULT NULL COMMENT '县统计上报面积',
  `CHL` varchar(50) DEFAULT NULL COMMENT '成活率',
  `CHL_DJ` varchar(50) DEFAULT NULL COMMENT '成活率等级',
  `YBD` varchar(50) DEFAULT NULL COMMENT '郁闭度（灌木林覆盖度）',
  `HSMJ` varchar(50) DEFAULT NULL COMMENT '核实面积',
  `BHSMJ` varchar(50) DEFAULT NULL COMMENT '不核实面积',
  `HGMJ` varchar(50) DEFAULT NULL COMMENT '合格面积',
  `CLMJ` varchar(50) DEFAULT NULL COMMENT '成林面积',
  `DBZMJ` varchar(50) DEFAULT NULL COMMENT '待补植面积',
  `BHGMJ` varchar(50) DEFAULT NULL COMMENT '失败（不合格）面积',
  `SSMJ` varchar(50) DEFAULT NULL COMMENT '损失面积',
  `BHSYY` varchar(50) DEFAULT NULL COMMENT '不核实原因',
  `WCLYY` varchar(50) DEFAULT NULL COMMENT '未成林原因',
  `DBZYY` varchar(50) DEFAULT NULL COMMENT '待补植原因',
  `BHGYY` varchar(50) DEFAULT NULL COMMENT '不合格（失败）原因',
  `SSYY` varchar(50) DEFAULT NULL COMMENT '损失原因',
  `SFSJ` varchar(50) DEFAULT NULL COMMENT '是否有作业设计',
  `SFSP` varchar(50) DEFAULT NULL COMMENT '作业设计是否审批',
  `SJDWZZ` varchar(50) DEFAULT NULL COMMENT '设计单位资质',
  `SFASJSS` varchar(50) DEFAULT NULL COMMENT '是否按设计施工',
  `SFYDA` varchar(50) DEFAULT NULL COMMENT '是否有档案',
  `SFFY` varchar(50) DEFAULT NULL COMMENT '是否抚育',
  `SFGH` varchar(50) DEFAULT NULL COMMENT '是否管护',
  `SFZJ` varchar(50) DEFAULT NULL COMMENT '是否开展县级自检',
  `SFYHT` varchar(50) DEFAULT NULL COMMENT '是否签订合同',
  `SFYLQZ` varchar(50) DEFAULT NULL COMMENT '是否发放林权证',
  `YLFS` varchar(50) DEFAULT NULL COMMENT '育林方式',
  `SJYLMJ` varchar(50) DEFAULT NULL COMMENT '设计育林面积',
  `HSYLMJ` varchar(50) DEFAULT NULL COMMENT '核实育林面积',
  `ZLZTLX` varchar(50) DEFAULT NULL COMMENT '造林主体类型',
  `BZ1` varchar(50) DEFAULT NULL COMMENT '备注1',
  `BZ2` varchar(50) DEFAULT NULL COMMENT '备注2',
  `BZ3` varchar(50) DEFAULT NULL COMMENT '备注3',
  `HCRY` varchar(50) DEFAULT NULL COMMENT '核查人员',
  `HCRQ` varchar(50) DEFAULT NULL COMMENT '核查日期',
  `PHOTO` varchar(50) DEFAULT NULL COMMENT '核查照片',
  `stat` varchar(50) DEFAULT NULL COMMENT '状态',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(50) DEFAULT NULL COMMENT '修改时间',
  `GEOMETRY` text,
  PRIMARY KEY (`id`,`CITY`,`COUNTY`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of yzl_xb
-- ----------------------------
