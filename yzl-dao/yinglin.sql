/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : yinglin

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 19/07/2019 10:48:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for yzl_county_message
-- ----------------------------
DROP TABLE IF EXISTS `yzl_county_message`;
CREATE TABLE `yzl_county_message`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息内容',
  `number` int(11) DEFAULT NULL COMMENT '更新的任务条数',
  `statu` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yzl_district
-- ----------------------------
DROP TABLE IF EXISTS `yzl_district`;
CREATE TABLE `yzl_district`  (
  `dcode` int(11) NOT NULL AUTO_INCREMENT COMMENT '地区编号',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '市',
  `county` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '县',
  `pcode` int(11) DEFAULT NULL COMMENT '上级编号',
  `shortcode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简码',
  `citycode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '市级行政编号',
  `burg` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '镇',
  `village` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '村',
  `mark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '每个市县的标志',
  `flag` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '每个市的标志',
  `Anumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '县级行政编号',
  PRIMARY KEY (`dcode`) USING BTREE,
  INDEX `countyCodeKEy`(`Anumber`) USING BTREE COMMENT '县级行政编号'
) ENGINE = InnoDB AUTO_INCREMENT = 205 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_district
-- ----------------------------
INSERT INTO `yzl_district` VALUES (65, '南宁市', '隆安县', NULL, 'NNSLAX', '4501', NULL, NULL, 'NNS-LAX', 'GA', '450123');
INSERT INTO `yzl_district` VALUES (66, '南宁市', '上林县', NULL, 'NNSSLX', '4501', NULL, NULL, 'NNS-SLX', 'GA', '450125');
INSERT INTO `yzl_district` VALUES (67, '南宁市', '马山县', NULL, 'NNSMSX', '4501', NULL, NULL, 'NNS-MSX', 'GA', '450124');
INSERT INTO `yzl_district` VALUES (68, '南宁市', '武鸣区', NULL, 'NNSWMQ', '4501', NULL, NULL, 'NNS-WMQ', 'GA', '450122');
INSERT INTO `yzl_district` VALUES (69, '南宁市', '横县', NULL, 'NNSHX', '4501', NULL, NULL, 'NNS-HX', 'GA', '450127');
INSERT INTO `yzl_district` VALUES (70, '南宁市', '西乡塘区', NULL, 'NNSXXTQ', '4501', NULL, NULL, 'NNS-XXTQ', 'GA', '450107');
INSERT INTO `yzl_district` VALUES (71, '南宁市', '兴宁区', NULL, 'NNSXNQ', '4501', NULL, NULL, 'NNS-XNQ', 'GA', '450102');
INSERT INTO `yzl_district` VALUES (72, '南宁市', '青秀区', NULL, 'NNSQXQ', '4501', NULL, NULL, 'NNS-QXQ', 'GA', '450103');
INSERT INTO `yzl_district` VALUES (73, '南宁市', '邕宁区', NULL, 'NNSYNQ', '4501', NULL, NULL, 'NNS-YNQ', 'GA', '450109');
INSERT INTO `yzl_district` VALUES (74, '南宁市', '宾阳县', NULL, 'NNSBYX', '4501', NULL, NULL, 'NNS-BYX', 'GA', '450126');
INSERT INTO `yzl_district` VALUES (75, '南宁市', '江南区', NULL, 'NNSJNQ', '4501', NULL, NULL, 'NNS-JNQ', 'GA', '450105');
INSERT INTO `yzl_district` VALUES (76, '柳州市', '柳南区', NULL, 'LZSLNQ', '4502', NULL, NULL, 'LZS-LNQ', 'GB', '450204');
INSERT INTO `yzl_district` VALUES (77, '柳州市', '柳北区', NULL, 'LZSLBQ', '4502', NULL, NULL, 'LZS-LBQ', 'GB', '450205');
INSERT INTO `yzl_district` VALUES (78, '柳州市', '柳城县', NULL, 'LZSLCX', '4502', NULL, NULL, 'LZS-LCX', 'GB', '450222');
INSERT INTO `yzl_district` VALUES (79, '柳州市', '鹿寨县', NULL, 'LZSLZX', '4502', NULL, NULL, 'LZS-LZX', 'GB', '450223');
INSERT INTO `yzl_district` VALUES (80, '柳州市', '城中区', NULL, 'LZSCZQ', '4502', NULL, NULL, 'LZS-CZQ', 'GB', '450202');
INSERT INTO `yzl_district` VALUES (81, '柳州市', '融安县', NULL, 'LZSRAX', '4502', NULL, NULL, 'LZS-RAX', 'GB', '450224');
INSERT INTO `yzl_district` VALUES (82, '柳州市', '融水县', NULL, 'LZSRSX', '4502', NULL, NULL, 'LZS-RSX', 'GB', '450225');
INSERT INTO `yzl_district` VALUES (83, '柳州市', '柳江县', NULL, 'LZSLJX', '4502', NULL, NULL, 'LZS-LJX', 'GB', '450221');
INSERT INTO `yzl_district` VALUES (84, '柳州市', '三江县', NULL, 'LZSSJX', '4502', NULL, NULL, 'LZS-SJX', 'GB', '450226');
INSERT INTO `yzl_district` VALUES (85, '柳州市', '鱼峰区', NULL, 'LZSYFQ', '4502', NULL, NULL, 'LZS-YFQ', 'GB', '450203');
INSERT INTO `yzl_district` VALUES (86, '桂林市', '阳朔县', NULL, 'GLSYSX', '4503', NULL, NULL, 'GLS-YSX', 'GC', '450321');
INSERT INTO `yzl_district` VALUES (87, '桂林市', '灵川县', NULL, 'GLSLCX', '4503', NULL, NULL, 'GLS-LCX', 'GC', '450323');
INSERT INTO `yzl_district` VALUES (88, '桂林市', '全州县', NULL, 'GLSQZX', '4503', NULL, NULL, 'GLS-QZX', 'GC', '450324');
INSERT INTO `yzl_district` VALUES (89, '桂林市', '资源县', NULL, 'GLSZYX', '4503', NULL, NULL, 'GLS-ZYX', 'GC', '450329');
INSERT INTO `yzl_district` VALUES (92, '桂林市', '平乐县', NULL, 'GLSPLX', '4503', NULL, NULL, 'GLS-PLX', 'GC', '450330');
INSERT INTO `yzl_district` VALUES (93, '桂林市', '荔浦县', NULL, 'GLSLPX', '4503', NULL, NULL, 'GLS-LPX', 'GC', '450331');
INSERT INTO `yzl_district` VALUES (94, '桂林市', '临桂区', NULL, 'GLSLGQ', '4503', NULL, NULL, 'GLS-LGQ', 'GC', '450322');
INSERT INTO `yzl_district` VALUES (95, '桂林市', '永福县', NULL, 'GLSYFX', '4503', NULL, NULL, 'GLS-YFX', 'GC', '450326');
INSERT INTO `yzl_district` VALUES (96, '桂林市', '兴安县', NULL, 'GLSXAX', '4503', NULL, NULL, 'GLS-XAX', 'GC', '450325');
INSERT INTO `yzl_district` VALUES (97, '桂林市', '灌阳县', NULL, 'GLSGYX', '4503', NULL, NULL, 'GLS-GYX', 'GC', '450327');
INSERT INTO `yzl_district` VALUES (98, '桂林市', '雁山区', NULL, 'GLSYSQ', '4503', NULL, NULL, 'GLS-YSQ', 'GC', '450311');
INSERT INTO `yzl_district` VALUES (99, '桂林市', '叠彩区', NULL, 'GLSDCQ', '4503', NULL, NULL, 'GLS-DCQ', 'GC', '450303');
INSERT INTO `yzl_district` VALUES (100, '桂林市', '象山区', NULL, 'GLSXSQ', '4503', NULL, NULL, 'GLS-XSQ', 'GC', '450304');
INSERT INTO `yzl_district` VALUES (101, '桂林市', '秀峰区', NULL, 'GLSXFQ', '4503', NULL, NULL, 'GLS-XFQ', 'GC', '450302');
INSERT INTO `yzl_district` VALUES (102, '桂林市', '七星区', NULL, 'GLSQXQ', '4503', NULL, NULL, 'GLS-QXQ', 'GC', '450305');
INSERT INTO `yzl_district` VALUES (103, '梧州市', '藤县', NULL, NULL, '4504', NULL, NULL, NULL, 'GD', '450422');
INSERT INTO `yzl_district` VALUES (104, '梧州市', '苍梧县', NULL, NULL, '4504', NULL, NULL, NULL, 'GD', '450421');
INSERT INTO `yzl_district` VALUES (105, '梧州市', '岑溪市', NULL, NULL, '4504', NULL, NULL, NULL, 'GD', '450481');
INSERT INTO `yzl_district` VALUES (106, '梧州市', '蝶山区', NULL, NULL, '4504', NULL, NULL, NULL, 'GD', '450404');
INSERT INTO `yzl_district` VALUES (107, '梧州市', '蒙山县', NULL, NULL, '4504', NULL, NULL, NULL, 'GD', '450423');
INSERT INTO `yzl_district` VALUES (108, '梧州市', '长洲区', NULL, NULL, '4504', NULL, NULL, NULL, 'GD', '450405');
INSERT INTO `yzl_district` VALUES (109, '梧州市', '万秀区', NULL, NULL, '4504', NULL, NULL, NULL, 'GD', '450403');
INSERT INTO `yzl_district` VALUES (110, '梧州市', '龙圩区', NULL, NULL, '4504', NULL, NULL, NULL, 'GD', '450406');
INSERT INTO `yzl_district` VALUES (111, '防城港市', '市本级', NULL, NULL, '4506', NULL, NULL, NULL, 'GP', '450601');
INSERT INTO `yzl_district` VALUES (112, '防城港市', '防城区', NULL, NULL, '4506', NULL, NULL, NULL, 'GP', '450603');
INSERT INTO `yzl_district` VALUES (113, '防城港市', '上思县', NULL, NULL, '4506', NULL, NULL, NULL, 'GP', '450621');
INSERT INTO `yzl_district` VALUES (114, '防城港市', '东兴市', NULL, NULL, '4506', NULL, NULL, NULL, 'GP', '450681');
INSERT INTO `yzl_district` VALUES (115, '防城港市', '港口区', NULL, NULL, '4506', NULL, NULL, NULL, 'GP', '450602');
INSERT INTO `yzl_district` VALUES (116, '防城港市', '十万大山管理局', NULL, NULL, '4506', NULL, NULL, NULL, 'GP', '450603013');
INSERT INTO `yzl_district` VALUES (117, '钦州市', '钦北区', NULL, NULL, '4507', NULL, NULL, NULL, 'GN', '450703');
INSERT INTO `yzl_district` VALUES (118, '钦州市', '钦南区', NULL, NULL, '4507', NULL, NULL, NULL, 'GN', '450702');
INSERT INTO `yzl_district` VALUES (119, '钦州市', '灵山县', NULL, NULL, '4507', NULL, NULL, NULL, 'GN', '450721');
INSERT INTO `yzl_district` VALUES (120, '钦州市', '浦北县', NULL, NULL, '4507', NULL, NULL, NULL, 'GN', '450722');
INSERT INTO `yzl_district` VALUES (121, '贵港市', '桂平市', NULL, NULL, '4508', NULL, NULL, NULL, 'GR', '450881');
INSERT INTO `yzl_district` VALUES (122, '贵港市', '平南县', NULL, NULL, '4508', NULL, NULL, NULL, 'GR', '450821');
INSERT INTO `yzl_district` VALUES (123, '贵港市', '覃塘区', NULL, NULL, '4508', NULL, NULL, NULL, 'GR', '450804');
INSERT INTO `yzl_district` VALUES (124, '贵港市', '港北区', NULL, NULL, '4508', NULL, NULL, NULL, 'GR', '450802');
INSERT INTO `yzl_district` VALUES (125, '贵港市', '港南区', NULL, NULL, '4508', NULL, NULL, NULL, 'GR', '450803');
INSERT INTO `yzl_district` VALUES (126, '贵港市', '平天山林场', NULL, NULL, '4508', NULL, NULL, NULL, 'GR', '450802011');
INSERT INTO `yzl_district` VALUES (127, '贵港市', '覃塘林场', NULL, NULL, '4508', NULL, NULL, NULL, 'GR', '450804014');
INSERT INTO `yzl_district` VALUES (128, '贵港市', '贵港市苗圃', NULL, NULL, '4508', NULL, NULL, NULL, 'GR', '450805050');
INSERT INTO `yzl_district` VALUES (129, '玉林市', '玉林市本级', NULL, NULL, '4509', NULL, NULL, NULL, 'GK', '450901');
INSERT INTO `yzl_district` VALUES (130, '玉林市', '福绵区', NULL, NULL, '4509', NULL, NULL, NULL, 'GK', '450903');
INSERT INTO `yzl_district` VALUES (131, '玉林市', '博白县', NULL, NULL, '4509', NULL, NULL, NULL, 'GK', '450923');
INSERT INTO `yzl_district` VALUES (132, '玉林市', '容县', NULL, NULL, '4509', NULL, NULL, NULL, 'GK', '450921');
INSERT INTO `yzl_district` VALUES (133, '玉林市', '陆川县', NULL, NULL, '4509', NULL, NULL, NULL, 'GK', '450922');
INSERT INTO `yzl_district` VALUES (134, '玉林市', '兴业县', NULL, NULL, '4509', NULL, NULL, NULL, 'GK', '450924');
INSERT INTO `yzl_district` VALUES (135, '玉林市', '北流市', NULL, NULL, '4509', NULL, NULL, NULL, 'GK', '450981');
INSERT INTO `yzl_district` VALUES (136, '玉林市', '玉州新区', NULL, NULL, '4509', NULL, NULL, NULL, 'GK', '450902');
INSERT INTO `yzl_district` VALUES (137, '玉林市', '大容山林场', NULL, NULL, '4509', NULL, NULL, NULL, 'GK', '450924015');
INSERT INTO `yzl_district` VALUES (138, '百色市', '右江区', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451002');
INSERT INTO `yzl_district` VALUES (139, '百色市', '田阳县', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451021');
INSERT INTO `yzl_district` VALUES (140, '百色市', '田东县', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451022');
INSERT INTO `yzl_district` VALUES (141, '百色市', '平果县', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451023');
INSERT INTO `yzl_district` VALUES (142, '百色市', '靖西县', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451025');
INSERT INTO `yzl_district` VALUES (143, '百色市', '那坡县', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451026');
INSERT INTO `yzl_district` VALUES (144, '百色市', '凌云县', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451027');
INSERT INTO `yzl_district` VALUES (145, '百色市', '乐业县', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451028');
INSERT INTO `yzl_district` VALUES (146, '百色市', '田林县', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451029');
INSERT INTO `yzl_district` VALUES (147, '百色市', '隆林县', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451031');
INSERT INTO `yzl_district` VALUES (148, '百色市', '西林县', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451030');
INSERT INTO `yzl_district` VALUES (149, '百色市', '德保县', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451024');
INSERT INTO `yzl_district` VALUES (150, '百色市', '老山林场', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451029025');
INSERT INTO `yzl_district` VALUES (151, '百色市', '百林林场', NULL, NULL, '4510', NULL, NULL, NULL, 'GL', '451002010');
INSERT INTO `yzl_district` VALUES (152, '贺州市', '八步区', NULL, NULL, '4511', NULL, NULL, NULL, 'GJ', '451102');
INSERT INTO `yzl_district` VALUES (153, '贺州市', '平桂区', NULL, NULL, '4511', NULL, NULL, NULL, 'GJ', '451119');
INSERT INTO `yzl_district` VALUES (154, '贺州市', '富川县', NULL, NULL, '4511', NULL, NULL, NULL, 'GJ', '451123');
INSERT INTO `yzl_district` VALUES (155, '贺州市', '钟山县', NULL, NULL, '4511', NULL, NULL, NULL, 'GJ', '451122');
INSERT INTO `yzl_district` VALUES (156, '贺州市', '昭平县', NULL, NULL, '4511', NULL, NULL, NULL, 'GJ', '451121');
INSERT INTO `yzl_district` VALUES (157, '河池市', '金城江区', NULL, NULL, '4512', NULL, NULL, NULL, 'GM', '451202');
INSERT INTO `yzl_district` VALUES (158, '河池市', '宜州市', NULL, NULL, '4512', NULL, NULL, NULL, 'GM', '451281');
INSERT INTO `yzl_district` VALUES (159, '河池市', '环江县', NULL, NULL, '4512', NULL, NULL, NULL, 'GM', '451226');
INSERT INTO `yzl_district` VALUES (160, '河池市', '罗城县', NULL, NULL, '4512', NULL, NULL, NULL, 'GM', '451225');
INSERT INTO `yzl_district` VALUES (161, '河池市', '南丹县', NULL, NULL, '4512', NULL, NULL, NULL, 'GM', '451221');
INSERT INTO `yzl_district` VALUES (162, '河池市', '天峨县', NULL, NULL, '4512', NULL, NULL, NULL, 'GM', '451222');
INSERT INTO `yzl_district` VALUES (163, '河池市', '东兰县', NULL, NULL, '4512', NULL, NULL, NULL, 'GM', '451224');
INSERT INTO `yzl_district` VALUES (164, '河池市', '巴马县', NULL, NULL, '4512', NULL, NULL, NULL, 'GM', '451227');
INSERT INTO `yzl_district` VALUES (165, '河池市', '凤山县', NULL, NULL, '4512', NULL, NULL, NULL, 'GM', '451223');
INSERT INTO `yzl_district` VALUES (166, '河池市', '都安县', NULL, NULL, '4512', NULL, NULL, NULL, 'GM', '451228');
INSERT INTO `yzl_district` VALUES (167, '河池市', '大化县', NULL, NULL, '4512', NULL, NULL, NULL, 'GM', '451229');
INSERT INTO `yzl_district` VALUES (168, '来宾市', '兴宾区', NULL, NULL, '4513', NULL, NULL, NULL, 'GG', '451302');
INSERT INTO `yzl_district` VALUES (169, '来宾市', '象州县', NULL, NULL, '4513', NULL, NULL, NULL, 'GG', '451322');
INSERT INTO `yzl_district` VALUES (170, '来宾市', '武宣县', NULL, NULL, '4513', NULL, NULL, NULL, 'GG', '451323');
INSERT INTO `yzl_district` VALUES (171, '来宾市', '忻城县', NULL, NULL, '4513', NULL, NULL, NULL, 'GG', '451321');
INSERT INTO `yzl_district` VALUES (172, '来宾市', '金秀县', NULL, NULL, '4513', NULL, NULL, NULL, 'GG', '451324');
INSERT INTO `yzl_district` VALUES (173, '来宾市', '合山市', NULL, NULL, '4513', NULL, NULL, NULL, 'GG', '451381');
INSERT INTO `yzl_district` VALUES (174, '崇左市', '江州区', NULL, NULL, '4514', NULL, NULL, NULL, 'GF', '451402');
INSERT INTO `yzl_district` VALUES (175, '崇左市', '扶绥县', NULL, NULL, '4514', NULL, NULL, NULL, 'GF', '451421');
INSERT INTO `yzl_district` VALUES (176, '崇左市', '宁明县', NULL, NULL, '4514', NULL, NULL, NULL, 'GF', '451422');
INSERT INTO `yzl_district` VALUES (177, '崇左市', '龙州县', NULL, NULL, '4514', NULL, NULL, NULL, 'GF', '451423');
INSERT INTO `yzl_district` VALUES (178, '崇左市', '大新县', NULL, NULL, '4514', NULL, NULL, NULL, 'GF', '451424');
INSERT INTO `yzl_district` VALUES (179, '崇左市', '天等县', NULL, NULL, '4514', NULL, NULL, NULL, 'GF', '451425');
INSERT INTO `yzl_district` VALUES (180, '崇左市', '凭祥市', NULL, NULL, '4514', NULL, NULL, NULL, 'GF', '451481');
INSERT INTO `yzl_district` VALUES (181, '桂林市', '恭城县', NULL, NULL, '4503', NULL, NULL, NULL, 'GC', '450332');
INSERT INTO `yzl_district` VALUES (182, '桂林市', '龙胜县', NULL, NULL, '4503', NULL, NULL, NULL, 'GC', '450328');
INSERT INTO `yzl_district` VALUES (183, '区直单位', '雅长林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451501');
INSERT INTO `yzl_district` VALUES (184, '区直单位', '博白林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451502');
INSERT INTO `yzl_district` VALUES (185, '区直单位', '高峰林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451503');
INSERT INTO `yzl_district` VALUES (186, '区直单位', '黄冕林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451504');
INSERT INTO `yzl_district` VALUES (187, '区直单位', '大桂山林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451505');
INSERT INTO `yzl_district` VALUES (188, '区直单位', '六万林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451506');
INSERT INTO `yzl_district` VALUES (189, '区直单位', '钦廉林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451507');
INSERT INTO `yzl_district` VALUES (190, '区直单位', '七坡林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451508');
INSERT INTO `yzl_district` VALUES (191, '区直单位', '热林中心', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451509');
INSERT INTO `yzl_district` VALUES (192, '区直单位', '广西林科院', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451510');
INSERT INTO `yzl_district` VALUES (193, '区直单位', '雅长自然保护区管理局', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451511');
INSERT INTO `yzl_district` VALUES (194, '区直单位', '派阳山林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451512');
INSERT INTO `yzl_district` VALUES (195, '区直单位', '东门林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451513');
INSERT INTO `yzl_district` VALUES (196, '区直单位', '南宁树木园', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451514');
INSERT INTO `yzl_district` VALUES (197, '区直单位', '三门江林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451515');
INSERT INTO `yzl_district` VALUES (198, '区直单位', '维都林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451516');
INSERT INTO `yzl_district` VALUES (199, '区直单位', '丁当林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451517');
INSERT INTO `yzl_district` VALUES (200, '区直单位', '广西生态学院', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451517');
INSERT INTO `yzl_district` VALUES (201, '北海市', '银海区', NULL, NULL, '4505', NULL, NULL, NULL, 'GE', '450503');
INSERT INTO `yzl_district` VALUES (202, '北海市', '合浦县', NULL, NULL, '4505', NULL, NULL, NULL, 'GE', '450521');
INSERT INTO `yzl_district` VALUES (203, '区直单位', '测试林场', NULL, NULL, '4515', NULL, NULL, NULL, 'GZ', '451518');

-- ----------------------------
-- Table structure for yzl_epc
-- ----------------------------
DROP TABLE IF EXISTS `yzl_epc`;
CREATE TABLE `yzl_epc`  (
  `ecode` int(11) NOT NULL AUTO_INCREMENT COMMENT '工程编号',
  `ename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工程名称',
  `mark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标识',
  PRIMARY KEY (`ecode`) USING BTREE,
  UNIQUE INDEX `mark`(`mark`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_epc
-- ----------------------------
INSERT INTO `yzl_epc` VALUES (1, '新一轮退耕地造林', '1');
INSERT INTO `yzl_epc` VALUES (9, '退耕还林工程', '2');
INSERT INTO `yzl_epc` VALUES (13, '海防林/珠防林', '3');
INSERT INTO `yzl_epc` VALUES (14, '石漠化治理工程', '4');
INSERT INTO `yzl_epc` VALUES (32, '造林补贴', '5');
INSERT INTO `yzl_epc` VALUES (45, '巩固退耕还林', '6');
INSERT INTO `yzl_epc` VALUES (50, '油茶项目', '7');
INSERT INTO `yzl_epc` VALUES (51, '自治区部门预算珍贵树种', '8');
INSERT INTO `yzl_epc` VALUES (52, '国家珍贵及特殊树种培育', '9');
INSERT INTO `yzl_epc` VALUES (53, '低效公益林改造项目', '10');
INSERT INTO `yzl_epc` VALUES (54, '自治区林业经营性产业发展项目（木本油料）', '11');
INSERT INTO `yzl_epc` VALUES (57, '国家珍贵及特殊树种培育项目', '12');
INSERT INTO `yzl_epc` VALUES (59, '珠防林工程', '14');
INSERT INTO `yzl_epc` VALUES (60, '海防林工程', '15');
INSERT INTO `yzl_epc` VALUES (61, '退耕还林', '16');
INSERT INTO `yzl_epc` VALUES (66, '金山银山工程', '21');
INSERT INTO `yzl_epc` VALUES (67, '珠防林、海防林工程  ', '22');
INSERT INTO `yzl_epc` VALUES (68, '造林补贴项目', '23');
INSERT INTO `yzl_epc` VALUES (69, '油茶营造林项目', '24');
INSERT INTO `yzl_epc` VALUES (70, '自治区林业经营性产业发展项目(自治区油茶示范基地项目)', '25');
INSERT INTO `yzl_epc` VALUES (95, '测试1', '27');

-- ----------------------------
-- Table structure for yzl_epc_task_progress
-- ----------------------------
DROP TABLE IF EXISTS `yzl_epc_task_progress`;
CREATE TABLE `yzl_epc_task_progress`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `taskProgress` float DEFAULT NULL COMMENT '任务进度',
  `createTime` timestamp(0) DEFAULT NULL COMMENT '任务计划时间',
  `GCLB` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工程标识编号',
  `ZLLB` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务标识编号',
  `cityCode` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '市行政编码',
  `countyCode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '县行政编号/数据访问权限',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建的用户',
  `modifier` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改的用户',
  `updateTime` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `sendBack` int(11) DEFAULT NULL COMMENT '退回任务的用户',
  `ZYND` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '造林实施年度',
  `JHND` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '造林计划年度',
  `filesUrl` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上传的文件路径',
  `stat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_4`(`cityCode`) USING BTREE,
  INDEX `e1`(`GCLB`) USING BTREE,
  INDEX `t1`(`ZLLB`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32352 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_epc_task_progress
-- ----------------------------
INSERT INTO `yzl_epc_task_progress` VALUES (32349, 921, '2019-07-08 14:59:16', '1', '11', '4501', '450123', NULL, NULL, '2019-07-09 15:07:22', NULL, '2019', '2019', '', '0');
INSERT INTO `yzl_epc_task_progress` VALUES (32350, 111, '2019-07-08 15:30:41', '1', '11', '4507', '450721', 'Jack', NULL, '2019-07-11 17:02:18', NULL, '2019', '2019', '', '2');
INSERT INTO `yzl_epc_task_progress` VALUES (32351, 100, '2019-07-08 15:31:04', '2', '11', '4507', '450721', 'Jack', NULL, '2019-07-11 17:02:18', NULL, '2019', '2019', '', '2');

-- ----------------------------
-- Table structure for yzl_epx_district
-- ----------------------------
DROP TABLE IF EXISTS `yzl_epx_district`;
CREATE TABLE `yzl_epx_district`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dcode` bigint(20) DEFAULT NULL COMMENT '地区编号id',
  `ecode` bigint(20) DEFAULT NULL COMMENT '工程编号id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yzl_log
-- ----------------------------
DROP TABLE IF EXISTS `yzl_log`;
CREATE TABLE `yzl_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作的用户',
  `operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '执行的操作',
  `operate_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作的时间',
  `dcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '被操作的地区编号',
  `gclb` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '被操作的工程类别',
  `zllb` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '被操作的造林类别',
  `year` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '这个工程的时间',
  `filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名称',
  `lea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '留言',
  `stat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 769 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_log
-- ----------------------------
INSERT INTO `yzl_log` VALUES (738, '灵山县管理员', '提交', '2019-05-23 15:36:56', '450721', '11,', NULL, '2019', '33b7b6cd-22cd-4fe4-b67a-3010de79c8a6/20190523/05-05问题.docx,', '', '0');
INSERT INTO `yzl_log` VALUES (739, '钦州市管理员', '审核', '2019-05-23 15:43:04', '450721', '11,', NULL, '2019', NULL, '', '1');
INSERT INTO `yzl_log` VALUES (740, '灵山县管理员', '提交', '2019-05-23 15:43:41', '450721', '12,13,', NULL, '2019', NULL, '二个一起提交', '0');
INSERT INTO `yzl_log` VALUES (741, '钦州市管理员', '退回', '2019-05-23 15:44:08', '450721', '12,13,', NULL, '2019', NULL, '不同意。现退回', '1');
INSERT INTO `yzl_log` VALUES (742, '灵山县管理员', '提交', '2019-05-23 15:44:46', '450721', '12,13,', NULL, '2019', NULL, '重新提交', '0');
INSERT INTO `yzl_log` VALUES (743, '钦州市管理员', '审核', '2019-05-23 15:45:30', '450721', '12,13,', NULL, '2019', NULL, '同意审核', '1');
INSERT INTO `yzl_log` VALUES (744, 'mark', '自治区退回', '2019-05-23 15:47:09', '450721', '11,', NULL, '2019', NULL, '', '2');
INSERT INTO `yzl_log` VALUES (745, 'mark', '自治区退回', '2019-05-23 15:47:55', '450721', '12,13,', NULL, '2019', NULL, '', '2');
INSERT INTO `yzl_log` VALUES (746, '灵山县管理员', '提交', '2019-05-23 15:48:23', '450721', '11,12,13,', NULL, '2019', NULL, '三个一起提交', '0');
INSERT INTO `yzl_log` VALUES (747, '钦州市管理员', '审核', '2019-05-23 15:49:31', '450721', '11,12,13,', NULL, '2019', NULL, '', '1');
INSERT INTO `yzl_log` VALUES (748, 'mark', '提交', '2019-05-24 12:33:34', '450721', '11,12,', NULL, '2019', NULL, '', '0');
INSERT INTO `yzl_log` VALUES (749, 'mark', '审核', '2019-05-24 12:33:42', '450721', '11,12,', NULL, '2019', NULL, '', '1');
INSERT INTO `yzl_log` VALUES (750, 'mark', '提交', '2019-05-27 10:28:53', '450721', '11,', NULL, '2019', NULL, '', '0');
INSERT INTO `yzl_log` VALUES (751, 'mark', '审核', '2019-05-27 10:29:06', '450721', '11,', NULL, '2019', NULL, '', '1');
INSERT INTO `yzl_log` VALUES (752, 'mark', '提交', '2019-05-28 11:31:04', '450923', '11,15,', NULL, '2019', 'c757bbe8-7e7f-4b58-bea0-884626d3176a/20190528/销售出货单.Zrp,', '提', '0');
INSERT INTO `yzl_log` VALUES (753, 'mark', '审核', '2019-05-28 11:32:23', '450923', '11,', NULL, '2019', NULL, '', '1');
INSERT INTO `yzl_log` VALUES (754, 'mark', '审核', '2019-05-28 11:33:37', '450923', '15,', NULL, '2019', NULL, '', '1');
INSERT INTO `yzl_log` VALUES (755, 'mark', '提交', '2019-05-29 11:23:51', '450722', '11,', NULL, '2019', NULL, '', '0');
INSERT INTO `yzl_log` VALUES (756, 'mark', '退回', '2019-05-29 11:24:01', '450722', '11,', NULL, '2019', NULL, '', '1');
INSERT INTO `yzl_log` VALUES (757, 'mark', '提交', '2019-05-29 11:24:47', '450722', '11,', NULL, '2019', NULL, '', '0');
INSERT INTO `yzl_log` VALUES (758, 'mark', '自治区退回', '2019-05-29 11:27:42', '450721', '11,12,13,', NULL, '2019', NULL, '', '2');
INSERT INTO `yzl_log` VALUES (759, 'mark', '提交', '2019-05-29 11:33:43', '450721', '12,', NULL, '2019', NULL, '', '0');
INSERT INTO `yzl_log` VALUES (760, 'mark', '审核', '2019-06-01 15:41:53', '450721', '12,', NULL, '2019', NULL, '', '1');
INSERT INTO `yzl_log` VALUES (761, 'mark', '提交', '2019-06-05 21:59:47', '450123', '11,', NULL, '2019', NULL, '', '0');
INSERT INTO `yzl_log` VALUES (762, 'mark', '审核', '2019-06-05 22:13:05', '450123', '11,', NULL, '2019', NULL, '很不错', '1');
INSERT INTO `yzl_log` VALUES (763, 'mark', '提交', '2019-07-09 09:52:40', '450204', '11,', NULL, '2019', NULL, '', '0');
INSERT INTO `yzl_log` VALUES (764, 'mark', '提交', '2019-07-09 09:53:14', '450204', '11,', NULL, '2019', NULL, '44', '0');
INSERT INTO `yzl_log` VALUES (765, 'mark', '提交', '2019-07-11 16:58:37', '450123', '11,', NULL, '2019', NULL, '1', '0');
INSERT INTO `yzl_log` VALUES (766, 'mark', '审核', '2019-07-11 16:58:49', '450123', '11,', NULL, '2019', NULL, 'hao', '1');
INSERT INTO `yzl_log` VALUES (767, 'mark', '提交', '2019-07-11 17:02:04', '450721', '11,', NULL, '2019', NULL, '很好', '0');
INSERT INTO `yzl_log` VALUES (768, 'mark', '审核', '2019-07-11 17:02:18', '450721', '11,', NULL, '2019', NULL, '通过', '1');

-- ----------------------------
-- Table structure for yzl_menu
-- ----------------------------
DROP TABLE IF EXISTS `yzl_menu`;
CREATE TABLE `yzl_menu`  (
  `menu_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '编号/菜单表',
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父权限编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权码(多个用逗号分隔，如：user:list,user:create)\'',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描菜单述',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单url',
  `generatemenu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否生成菜单',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `icon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE,
  INDEX `AK_Key_2`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 680 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_menu
-- ----------------------------
INSERT INTO `yzl_menu` VALUES (1, NULL, '首页', NULL, NULL, 'home.jsp', '1', 0, 'icon-dashboard');
INSERT INTO `yzl_menu` VALUES (4, NULL, '营造林计划管理', 'sys:jh', NULL, NULL, '1', 1, 'icon-edit');
INSERT INTO `yzl_menu` VALUES (7, NULL, '营造林数据中心', 'sys:da', NULL, NULL, '1', 2, 'icon-edit');
INSERT INTO `yzl_menu` VALUES (9, NULL, '营造林辅助管理', 'sys:fz', NULL, NULL, '1', 3, 'icon-edit');
INSERT INTO `yzl_menu` VALUES (30, '2', '地区', 'sys:dis:dq', '10', '地区.jsp', '0', 0, NULL);
INSERT INTO `yzl_menu` VALUES (31, '2', '工程', 'sys:dis:gc', NULL, 'epc.jsp', '0', 1, NULL);
INSERT INTO `yzl_menu` VALUES (32, '2', '任务', 'sys:dis:rw', NULL, 'task.jsp', '0', 2, NULL);
INSERT INTO `yzl_menu` VALUES (33, '2', '工程模板', 'sys:dis:gcmb', NULL, 'EpcTemplate.jsp', '0', 3, NULL);
INSERT INTO `yzl_menu` VALUES (34, '3', '抽查情况', NULL, NULL, 'SpotCheck.jsp', '1', 0, NULL);
INSERT INTO `yzl_menu` VALUES (35, '3', '核查质量情况', NULL, NULL, 'CheckQuality.jsp', '1', 1, NULL);
INSERT INTO `yzl_menu` VALUES (36, '3', '任务完成情况', NULL, NULL, 'CompletionTask.jsp', '1', 2, NULL);
INSERT INTO `yzl_menu` VALUES (37, '3', '管理指标', NULL, NULL, 'ManagementIndicator.jsp', '1', 3, NULL);
INSERT INTO `yzl_menu` VALUES (38, '3', '监测统计模板', NULL, NULL, 'MonitorStatisticsTemplate.jsp', '1', 4, NULL);
INSERT INTO `yzl_menu` VALUES (39, '9', '权限管理', NULL, NULL, 'admin/function.jsp', '1', 0, NULL);
INSERT INTO `yzl_menu` VALUES (40, '9', '角色管理', NULL, NULL, 'admin/role.jsp', '1', 1, NULL);
INSERT INTO `yzl_menu` VALUES (41, '9', '用户管理', NULL, NULL, 'admin/userlist.jsp', '1', 2, NULL);
INSERT INTO `yzl_menu` VALUES (42, '6666666', 'pass2', 'sys:jh:jctj', NULL, 'MonitorStatistics.jsp', '2', 0, NULL);
INSERT INTO `yzl_menu` VALUES (44, '6666666', '造林任务发布', 'sys:jh:ckgc', NULL, 'taskIssued.jsp', '2', 0, NULL);
INSERT INTO `yzl_menu` VALUES (45, '5', '造林任务进度', NULL, NULL, '任务进度.jsp', '0', 0, NULL);
INSERT INTO `yzl_menu` VALUES (348, '6666666', '造林任务完成验收', NULL, '造林任务完成验收', 'taskFinished.jsp', '2', 3, NULL);
INSERT INTO `yzl_menu` VALUES (411, '7', '监测统计', NULL, NULL, 'monitoringstatistics.jsp', '1', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (451, '9', '南宁市数据权限', '4501', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (452, '9', '柳州市数据权限', '4502', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (453, '451', '兴宁区数据权限访问', '450102', '南宁市兴宁区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (454, '451', '横县数据权限访问', '450127', '南宁市横县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (455, '451', '青秀区数据权限访问', '450103', '南宁市青秀区数据权限访问', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (456, '451', '江南区数据权限访问', '450105', '南宁市江南区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (457, '451', '西乡塘区数据权限访问', '450107', '南宁市西乡塘区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (458, '451', '良庆区数据权限访问', '450106', '南宁市良庆区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (459, '451', '邕宁区数据权限访问', '450109', '南宁市邕宁区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (460, '451', '武鸣县数据权限访问', '450122', '南宁市武鸣县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (461, '451', '隆安县数据权限访问', '450123', '南宁市隆安县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (462, '451', '马山县数据权限访问', '450124', '南宁市马山县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (463, '451', '上林县数据权限访问', '450125', '南宁市上林县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (464, '451', '宾阳县数据权限访问', '450126', '南宁市宾阳县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (465, '452', '城中区数据权限访问', '450202', '柳州市城中区数据权限访问', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (466, '452', '鱼峰区数据权限访问', '450203', '柳州市鱼峰区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (467, '452', '柳南区数据权限访问', '450204', '柳州市柳南区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (468, '452', '柳北区数据权限访问', '450205', '柳州市柳北区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (469, '452', '柳江县数据权限访问', '450221', '柳州市柳江县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (470, '452', '柳城县数据权限访问', '450222', '柳州市柳城县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (471, '452', '鹿寨县数据权限访问', '450223', '柳州市鹿寨县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (472, '452', '融安县数据权限访问', '450224', '柳州市融安县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (473, '452', '融水县数据权限访问', '450225', '柳州市融水县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (474, '452', '三江县数据权限访问', '450226', '柳州市三江县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (475, '9', '桂林市数据权限访问', '4503', '桂林市数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (476, '475', '市辖区数据权限访问', '450301', '桂林市市辖区数据权限访问', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (477, '475', '秀峰区数据权限访问', '450302', '桂林市秀峰区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (478, '475', '叠彩区数据权限访问', '450303', '桂林市叠彩区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (479, '475', '象山区数据权限访问', '450304', '桂林市象山区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (480, '475', '七星区数据权限访问', '450305', '桂林市七星区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (481, '475', '雁山区数据权限访问', '450311', '桂林市雁山区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (482, '475', '阳朔县数据权限访问', '450321', '桂林市阳朔县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (483, '475', '临桂县数据权限访问', '450322', '桂林市临桂县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (484, '475', '灵川县数据权限访问', '450323', '桂林市灵川县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (485, '475', '全州县数据权限访问', '450324', '桂林市全州县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (486, '475', '兴安县数据权限访问', '450325', '桂林市兴安县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (487, '475', '永福县数据权限访问', '450326', '桂林市永福县数据权限访问', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (488, '475', '灌阳县数据权限访问', '450327', '桂林市灌阳县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (489, '475', '龙胜县数据权限访问', '450328', '桂林市龙胜县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (490, '475', '资源县数据权限访问', '450329', '桂林市资源县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (491, '475', '平乐县数据权限访问', '450330', '桂林市平乐县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (492, '475', '荔浦县数据权限访问', '450331', '桂林市荔浦县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (493, '475', '恭城县数据权限访问', '450332', '桂林市恭城县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (494, '40', '添加', 'sys:jsgl:tj', '角色管理添加', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (495, '39', '添加', 'sys:qxgl:tj', '权限管理添加', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (496, '41', '添加', 'sys:yhgl:xz', '用户添加', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (497, '40', '修改', 'sys:jsgl:xg', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (498, '39', '修改', 'sys:qxgl:xg', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (499, '41', '修改', 'sys:yhgl:xg', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (534, '44', '添加', 'sys:ckgc:tj', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `yzl_menu` VALUES (535, '44', '修改', 'sys:ckgc:xg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `yzl_menu` VALUES (536, '44', '删除', 'sys:ckgc:sc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `yzl_menu` VALUES (537, '44', '保存', 'sys:ckgc:bc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `yzl_menu` VALUES (538, '44', '取消编制', 'sys:ckgc:qxbj', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `yzl_menu` VALUES (539, '44', '导入', 'sys:ckgc:dr', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `yzl_menu` VALUES (540, '44', '导出', 'sys:ckgc:dc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `yzl_menu` VALUES (543, '9', '防城港市数据访问权限', '4506', '防城港市数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (544, '543', '市本级', '450601', '防城港市市本级数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (545, '543', '防城区', '450603', '防城港市防城区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (546, '543', '上思县', '450621', '防城港市上思县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (547, '543', '东兴市', '450681', '防城港市东兴市数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (548, '543', '港口区', '450602', '防城港市港口区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (549, '543', '十万大山管理局', '450603013', '防城港市十万大山管理局数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (550, '9', '钦州市数据访问权限', '4507', '钦州市数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (551, '550', '钦北区', '450703', '钦州市钦北区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (552, '550', '钦南区', '450702', '钦州市钦南区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (553, '550', '灵山县', '450721', '钦州市灵山县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (554, '550', '浦北县', '450722', '钦州市浦北县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (555, '9', '贵港市数据访问权限', '4508', '贵港市数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (556, '555', '桂平市', '450881', '贵港市桂平市数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (557, '555', '平南县', '450821', '贵港市平南县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (558, '555', '覃塘区', '450804', '贵港市覃塘区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (559, '555', '港北区', '450802', '贵港市港北区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (560, '555', '港南区', '450803', '贵港市港南区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (561, '555', '平天山林场', '450802011', '贵港市平天山林场数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (562, '555', '覃塘林场', '450804014', '贵港市覃塘林场数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (563, '555', '贵港市苗圃', '450805050', '贵港市贵港市苗圃数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (564, '9', '玉林市数据访问权限', '4509', '玉林市数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (565, '564', '玉林市本级', '450901', '玉林市玉林市本级数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (566, '564', '福绵区', '450903', '玉林市福绵区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (567, '564', '博白县', '450923', '玉林市博白县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (568, '564', '容县', '450921', '玉林市容县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (569, '564', '陆川县', '450922', '玉林市陆川县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (570, '564', '兴业县', '450924', '玉林市兴业县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (571, '564', '北流市', '450981', '玉林市北流市数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (572, '564', '玉州新区', '450902', '玉林市玉州新区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (573, '564', '大容山林场', '450924015', '玉林市大容山林场数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (574, '9', '百色市数据访问权限', '4510', '百色市数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (575, '574', '右江区', '451002', '百色市右江区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (576, '574', '田阳县', '451021', '百色市田阳县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (577, '574', '田东县', '451022', '百色市田东县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (578, '574', '平果县', '451023', '百色市平果县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (579, '574', '靖西县', '451025', '百色市靖西县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (580, '574', '那坡县', '451026', '百色市那坡县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (581, '574', '凌云县', '451027', '百色市凌云县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (582, '574', '乐业县', '451028', '百色市乐业县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (583, '574', '田林县', '451029', '百色市田林县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (584, '574', '隆林县', '451031', '百色市隆林县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (585, '574', '西林县', '451030', '百色市西林县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (586, '574', '德保县', '451024', '百色市德保县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (587, '574', '老山林场', '451029025', '百色市老山林场数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (588, '574', '百林林场', '451002010', '百色市百林林场数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (589, '9', '贺州市数据访问权限', '4511', '贺州市数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (590, '589', '八步区', '451102', '贺州市八步区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (591, '589', '平桂区', '451119', '贺州市平桂区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (592, '589', '富川县', '451123', '贺州市富川县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (593, '589', '钟山县', '451122', '贺州市钟山县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (594, '589', '昭平县', '451121', '贺州市昭平县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (595, '9', '河池市数据访问权限', '4512', '河池市数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (596, '595', '金城江区', '451202', '河池市金城江区数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (597, '595', '宜州市', '451281', '河池市宜州市数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (598, '595', '环江县', '451226', '河池市环江县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (599, '595', '罗城县', '451225', '河池市罗城县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (600, '595', '南丹县', '451221', '河池市南丹县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (601, '595', '天峨县', '451222', '河池市天峨县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (602, '595', '东兰县', '451224', '河池市东兰县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (603, '595', '巴马县', '451227', '河池市巴马县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (604, '595', '凤山县', '451223', '河池市凤山县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (605, '595', '都安县', '451228', '河池市都安县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (606, '595', '大化县', '451229', '河池市大化县数据访问权限', NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (607, '9', '来宾市数据访问权限', '4513', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (608, '607', '兴宾区', '451302', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (609, '607', '象州县', '451322', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (610, '607', '武宣县', '451323', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (611, '607', '忻城县', '451321', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (612, '607', '金秀县', '451324', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (613, '607', '合山市', '451381', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (614, '9', '崇左市数据访问权限', '4514', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (615, '614', '江州区', '451402', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (616, '614', '扶绥县', '451421', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (617, '614', '宁明县', '451422', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (618, '614', '龙州县', '451423', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (619, '614', '大新县', '451424', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (620, '614', '天等县', '451425', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (621, '614', '凭祥市', '451481', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (622, '9', '区直单位数据访问权限', '4515', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (623, '622', '雅长林场', '451501', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (624, '622', '博白林场', '451502', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (625, '622', '高峰林场', '451503', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (626, '622', '黄冕林场', '451504', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (627, '622', '大桂山林场', '451505', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (628, '622', '六万林场', '451506', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (629, '622', '钦廉林场', '451507', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (630, '622', '七坡林场', '451508', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (631, '622', '热林中心', '451509', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (632, '622', '广西林科院', '451510', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (633, '622', '雅长自然保护区管理局', '451511', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (634, '622', '派阳山林场', '451512', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (635, '622', '东门林场', '451513', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (636, '622', '南宁树木园', '451514', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (637, '39', '删除', 'sys:qxgl:sc', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (638, '40', '删除', 'sys:jsgl:sc', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (639, '41', '删除', 'sys:yhgl:sc', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (640, '541', '添加', 'sys:rwxf:tj', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (641, '541', '修改', 'sys:rwxf:xg', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (642, '541', '保存', 'sys:rwxf:bc', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (643, '541', '删除', 'sys:rwxf:sc', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (644, '541', '取消编辑', 'sys:rwxf:qxbj', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (645, '541', '导出', 'sys:rwxf:dc', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (646, '6666666', '保存并添加附件', 'sys:rwxf:bctjfj', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (647, '541', '导入', 'sys:rwxf:dr', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (648, '541', '查看', NULL, NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (649, '4', '任务下发', NULL, NULL, 'taskIssued.jsp', '1', 1, NULL);
INSERT INTO `yzl_menu` VALUES (650, '4', '任务工作中', NULL, NULL, 'taskWorking.jsp', '1', 2, NULL);
INSERT INTO `yzl_menu` VALUES (651, '4', '造林任务完成验收', NULL, NULL, 'taskFinished.jsp', '1', 3, NULL);
INSERT INTO `yzl_menu` VALUES (653, '649', '添加', 'sys:rwxf:tj', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (654, '649', '修改', 'sys:rwxf:xg', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (655, '649', '保存', 'sys:rwxf:bc', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (656, '649', '删除', 'sys:rwxf:sc', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (657, '649', '取消编辑', 'sys:rwxf:qxbj', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (658, '649', '导出', 'sys:rwxf:dc', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (659, '66666', '保存并添加附件', 'sys:rwxf:bctjfj', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (660, '649', '导入', 'sys:rwxf:dr', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (661, '649', '查看', NULL, NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (662, '650', '审核', 'sys:rwgzz:sh', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (663, '650', '退回', 'sys:rwgzz:th', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (664, '650', '提交', 'sys:rwgzz:tj', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (665, '650', '查看', NULL, NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (666, '651', '查看', NULL, NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (667, '651', '退回', 'sys:rwwc:th', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (668, '9', '北海市数据权限', '4505', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (669, '668', '银海区', '450503', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (670, '668', '合浦县', '450521', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (671, '7', '核查统计表', NULL, NULL, 'VerificationStatistical.jsp', '1', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (672, '649', '往年数据导入', 'sys:rwxf:wndr', NULL, NULL, '0', NULL, NULL);
INSERT INTO `yzl_menu` VALUES (675, '9', '工程管理', NULL, NULL, 'admin/engineeringManage.jsp', '1', 3, NULL);
INSERT INTO `yzl_menu` VALUES (676, '9', '造林任务管理', NULL, NULL, 'admin/task.jsp', '1', 4, NULL);
INSERT INTO `yzl_menu` VALUES (677, '9', '地区管理', NULL, NULL, 'area.jsp', '1', 5, NULL);
INSERT INTO `yzl_menu` VALUES (679, '622', '测试林场', '451518', '', '', '0', NULL, NULL);

-- ----------------------------
-- Table structure for yzl_message
-- ----------------------------
DROP TABLE IF EXISTS `yzl_message`;
CREATE TABLE `yzl_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id唯一标识',
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提示信息(内容)',
  `rid` int(11) DEFAULT NULL COMMENT '关联的角色id(保留字段，暂无用处)',
  `createtime` timestamp(0) DEFAULT NULL COMMENT '创建时间',
  `statu` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '记录用户的状态',
  `number` bigint(20) DEFAULT NULL COMMENT '被修改任务的数量',
  `countyCode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地区行政编号，作用于权限判断',
  `GCLB` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工程类别',
  `ZLLB` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '造林类别',
  `ZYND` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作业年度',
  `JHND` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '计划年度',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `角色id`(`rid`) USING BTREE,
  CONSTRAINT `角色id` FOREIGN KEY (`rid`) REFERENCES `yzl_role` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 479 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_message
-- ----------------------------
INSERT INTO `yzl_message` VALUES (399, NULL, NULL, '2019-05-23 15:25:38', ',1254254214552111b,sabhjdgsaudvashdjahsjh12313,1555038103121574T', 1, '450721', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (400, NULL, NULL, '2019-05-23 15:26:08', ',1254254214552111b,sabhjdgsaudvashdjahsjh12313,1555038103121574T', 1, '450721', '1', '12', '2019', '2019');
INSERT INTO `yzl_message` VALUES (401, NULL, NULL, '2019-05-23 15:26:36', ',1254254214552111b,sabhjdgsaudvashdjahsjh12313,1555038103121574T', 1, '450721', '1', '13', '2019', '2019');
INSERT INTO `yzl_message` VALUES (402, NULL, NULL, '2019-05-23 15:40:29', ',sabhjdgsaudvashdjahsjh12313,1555038103121574T,1254254214552111b', 1, '450721', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (403, NULL, NULL, '2019-05-24 09:20:21', ',1254254214552111b,215641651651616c', 1, '450127', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (404, NULL, NULL, '2019-05-24 09:41:03', ',1254254214552111b', 1, '450721', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (405, NULL, NULL, '2019-05-24 09:44:14', ',1254254214552111b', 1, '450721', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (406, NULL, NULL, '2019-05-24 09:45:45', ',1254254214552111b', 1, '450721', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (407, NULL, NULL, '2019-05-24 10:07:26', ',1254254214552111b', 1, '450721', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (408, NULL, NULL, '2019-05-24 10:08:15', ',1254254214552111b', 1, '450721', '1', '12', '2019', '2019');
INSERT INTO `yzl_message` VALUES (409, NULL, NULL, '2019-05-24 10:15:32', ',1254254214552111b', 1, '450721', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (410, NULL, NULL, '2019-05-24 11:07:53', NULL, 1, '南宁市', '2', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (411, NULL, NULL, '2019-05-24 11:07:53', NULL, 1, '钦州市', '2', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (412, NULL, NULL, '2019-05-24 11:07:53', NULL, 1, '贺州市', '2', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (413, NULL, NULL, '2019-05-24 11:07:53', NULL, 1, '来宾市', '2', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (414, NULL, NULL, '2019-05-24 11:07:53', NULL, 1, '横县', '2', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (415, NULL, NULL, '2019-05-24 11:07:53', NULL, 1, '合计', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (416, NULL, NULL, '2019-05-24 11:07:54', NULL, 1, '柳州市', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (417, NULL, NULL, '2019-05-24 11:07:54', NULL, 1, '桂林市', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (418, NULL, NULL, '2019-05-24 11:07:54', NULL, 1, '梧州市', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (419, NULL, NULL, '2019-05-24 11:07:54', NULL, 1, '百色市', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (420, NULL, NULL, '2019-05-24 11:07:54', NULL, 1, '贺州市', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (421, NULL, NULL, '2019-05-24 11:07:54', NULL, 1, '河池市', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (422, NULL, NULL, '2019-05-24 11:07:54', NULL, 1, '林场合计', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (423, NULL, NULL, '2019-05-24 11:07:54', NULL, 1, '高峰林场', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (424, NULL, NULL, '2019-05-24 11:07:55', NULL, 1, '七坡林场', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (425, NULL, NULL, '2019-05-24 11:07:55', NULL, 1, '黄冕林场', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (426, NULL, NULL, '2019-05-24 11:07:55', NULL, 1, '雅长林场', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (427, NULL, NULL, '2019-05-24 11:07:55', NULL, 1, '大桂山林场', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (428, NULL, NULL, '2019-05-24 11:07:55', NULL, 1, '南宁树木园', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (429, NULL, NULL, '2019-05-24 11:07:55', NULL, 1, '三门江林场', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (430, NULL, NULL, '2019-05-24 11:07:56', NULL, 1, '东门林场', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (431, NULL, NULL, '2019-05-24 11:07:56', NULL, 1, '六万林场', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (432, NULL, NULL, '2019-05-24 11:07:56', NULL, 1, '维都林场', '14', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (433, NULL, NULL, '2019-05-24 11:07:57', NULL, 1, '合计', '15', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (434, NULL, NULL, '2019-05-24 11:07:57', NULL, 1, '防城港市', '15', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (435, NULL, NULL, '2019-05-24 11:07:57', NULL, 1, '钦州市', '15', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (436, NULL, NULL, '2019-05-24 11:07:57', NULL, 1, '崇左市', '15', '11', '2020', '2020');
INSERT INTO `yzl_message` VALUES (437, NULL, NULL, '2019-05-24 11:40:08', ',1254254214552111b', 1, '450881', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (438, NULL, NULL, '2019-05-24 11:41:01', ',1254254214552111b', 1, '450722', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (439, NULL, NULL, '2019-05-24 11:42:28', ',1254254214552111b', 1, '450703', '2', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (440, NULL, NULL, '2019-05-24 11:42:53', ',1254254214552111b', 1, '450703', '1', '12', '2019', '2019');
INSERT INTO `yzl_message` VALUES (441, NULL, NULL, '2019-05-24 11:43:28', ',1254254214552111b', 1, '450703', '1', '13', '2019', '2019');
INSERT INTO `yzl_message` VALUES (442, NULL, NULL, '2019-05-24 15:57:08', ',1254254214552111b,215641651651616c', 1, '450125', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (443, NULL, NULL, '2019-05-24 17:28:53', ',1254254214552111b,215641651651616c', 1, '450123', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (444, NULL, NULL, '2019-05-24 17:32:06', ',1254254214552111b,215641651651616c', 1, '450123', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (445, NULL, NULL, '2019-05-24 17:32:23', ',1254254214552111b,215641651651616c', 1, '450124', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (446, NULL, NULL, '2019-05-24 17:32:36', ',1254254214552111b,215641651651616c', 1, '450124', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (447, NULL, NULL, '2019-05-24 18:45:33', ',1254254214552111b,215641651651616c', 1, '450124', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (448, NULL, NULL, '2019-05-24 18:45:53', ',1254254214552111b,215641651651616c', 1, '450127', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (449, NULL, NULL, '2019-05-27 10:27:24', ',1254254214552111b', 1, '450721', '4', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (450, NULL, NULL, '2019-05-28 11:26:00', ',1254254214552111b', 1, '450923', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (451, NULL, NULL, '2019-05-28 11:26:30', ',1254254214552111b', 1, '450923', '2', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (452, NULL, NULL, '2019-05-28 11:28:41', ',1254254214552111b', 1, '450923', '24', '15', '2019', '2019');
INSERT INTO `yzl_message` VALUES (453, NULL, NULL, '2019-05-29 11:42:53', ',1254254214552111b', 1, '450703', '22', '16', '2019', '2019');
INSERT INTO `yzl_message` VALUES (454, NULL, NULL, '2019-05-29 11:43:14', ',1254254214552111b', 1, '450703', '24', '16', '2019', '2019');
INSERT INTO `yzl_message` VALUES (455, NULL, NULL, '2019-05-29 11:56:20', ',1254254214552111b', 1, '450703', '1', '12', '2019', '2019');
INSERT INTO `yzl_message` VALUES (456, NULL, NULL, '2019-06-15 20:39:27', NULL, 1, '450123', '2', '12', '2019', '2019');
INSERT INTO `yzl_message` VALUES (457, NULL, NULL, '2019-06-15 20:39:30', NULL, 1, '450123', '5', '12', '2019', '2019');
INSERT INTO `yzl_message` VALUES (458, NULL, NULL, '2019-06-15 20:43:30', NULL, 1, '450123', '2', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (459, NULL, NULL, '2019-06-15 20:45:50', NULL, 1, '450123', '1', '14', '2019', '2019');
INSERT INTO `yzl_message` VALUES (460, NULL, NULL, '2019-06-15 21:11:28', NULL, 1, '450123', '3', '12', '2019', '2019');
INSERT INTO `yzl_message` VALUES (461, NULL, NULL, '2019-06-15 21:15:02', NULL, 1, '450123', '2', '13', '2019', '2019');
INSERT INTO `yzl_message` VALUES (462, NULL, NULL, '2019-06-17 21:42:14', NULL, 1, '4501', '', '25', '2019', '2019');
INSERT INTO `yzl_message` VALUES (463, NULL, NULL, '2019-06-29 18:37:45', NULL, 1, '450123', '', '30', '2019', '2019');
INSERT INTO `yzl_message` VALUES (464, NULL, NULL, '2019-06-29 18:37:46', NULL, 1, '450123', '', '30', '2019', '2019');
INSERT INTO `yzl_message` VALUES (465, NULL, NULL, '2019-06-29 18:37:46', NULL, 1, '450123', '', '30', '2019', '2019');
INSERT INTO `yzl_message` VALUES (466, NULL, NULL, '2019-06-29 18:37:46', NULL, 1, '450123', '4', '26', '2019', '2019');
INSERT INTO `yzl_message` VALUES (467, NULL, NULL, '2019-06-29 18:37:46', NULL, 1, '450123', '', '31', '2019', '2019');
INSERT INTO `yzl_message` VALUES (468, NULL, NULL, '2019-06-29 18:37:46', NULL, 1, '450123', '', '31', '2019', '2019');
INSERT INTO `yzl_message` VALUES (469, NULL, NULL, '2019-06-29 18:45:49', NULL, 1, '450123', '1', '12', '2019', '2019');
INSERT INTO `yzl_message` VALUES (470, NULL, NULL, '2019-07-08 14:23:30', NULL, 1, '450123', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (471, NULL, NULL, '2019-07-08 14:26:51', NULL, 1, '450721', '1', '12', '2019', '2019');
INSERT INTO `yzl_message` VALUES (472, NULL, NULL, '2019-07-08 14:59:16', NULL, 1, '450123', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (473, NULL, NULL, '2019-07-08 15:00:31', NULL, 1, '450123', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (474, NULL, NULL, '2019-07-08 15:17:50', NULL, 1, '450123', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (475, NULL, NULL, '2019-07-08 15:30:41', NULL, 1, '450721', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (476, NULL, NULL, '2019-07-08 15:31:04', NULL, 1, '450721', '2', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (477, NULL, NULL, '2019-07-08 15:41:20', NULL, 1, '450721', '1', '11', '2019', '2019');
INSERT INTO `yzl_message` VALUES (478, NULL, NULL, '2019-07-09 15:07:23', NULL, 1, '450123', '1', '11', '2019', '2019');

-- ----------------------------
-- Table structure for yzl_monitoringstatistics
-- ----------------------------
DROP TABLE IF EXISTS `yzl_monitoringstatistics`;
CREATE TABLE `yzl_monitoringstatistics`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '监测统计表',
  `did` bigint(20) DEFAULT NULL COMMENT '地区编号',
  `eid` bigint(20) DEFAULT NULL COMMENT '工程编号',
  `tid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务编号',
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '时间',
  `inspectionReportArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '自检上报面积',
  `verifyArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '核实面积',
  `certifiedArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '核实合格面积',
  `jobDesignArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作业设计面积',
  `designConstructionAccordingOperation` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '按作业设计施工面积',
  `fileSize` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '有档案面积',
  `countyInspectionArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '开展县级自检面积',
  `designForestArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计育林面积',
  `verifyForestArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '核实育林面积',
  `bareArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '抚育面积',
  `managementArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '管护面积',
  `areaVerificationRate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '面积核实率',
  `verifyAreaPassRate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '核实面积合格率',
  `reportAreaQualificationRate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上报面积合格率',
  `reportRetentionRateYear` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '当年上报面积保存率',
  `afforestationReportsEligibleYear` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '造林当年上报合格面积',
  `countySelfInspectionArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '全县自检合格面积',
  `calculatedVerificationArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '推算核实面积',
  `calculateQualifiedArea` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '推算完成合格面积',
  `scheduledTask` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '计划任务',
  `taskCompletionRate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务完成率',
  `jobDesignRate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作业设计率',
  `accordingDesignConstructionRate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '按设计施工率',
  `byInputtingRate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '建档率',
  `selfCheckingRate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '自查率',
  `selfCheckingsRate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '自检率',
  `fileRate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '档案率',
  `raisingRates` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '抚育率',
  `afforestationRate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '育林率',
  `theManagementRate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '管护率',
  `mark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SFYDA` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否有档案面积',
  `SFFY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否抚育',
  `SFGH` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否管护',
  `SFZJ` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否开展县级自检',
  `YLFS` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '育林方式',
  `CLMJ` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '成林面积',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_monitoringstatistics
-- ----------------------------
INSERT INTO `yzl_monitoringstatistics` VALUES (43, 450122, 1, '11', '2017', '123', '46', '78', '123', '45', '78', '78', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for yzl_role
-- ----------------------------
DROP TABLE IF EXISTS `yzl_role`;
CREATE TABLE `yzl_role`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '角色编号/角色表',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关键字',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `create_tine` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `AK_Key_2`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_role
-- ----------------------------
INSERT INTO `yzl_role` VALUES (1, '管理员', NULL, '', NULL);
INSERT INTO `yzl_role` VALUES (2, '超级管理员', NULL, '', NULL);
INSERT INTO `yzl_role` VALUES (32, '南宁市管理员', NULL, '', NULL);
INSERT INTO `yzl_role` VALUES (34, '横县管理员', NULL, '', NULL);
INSERT INTO `yzl_role` VALUES (35, '兴宁区管理员', NULL, '', NULL);
INSERT INTO `yzl_role` VALUES (44, '西乡塘区', NULL, '', NULL);
INSERT INTO `yzl_role` VALUES (45, '清秀区管理员', NULL, '', NULL);
INSERT INTO `yzl_role` VALUES (46, '江南区管理员', '1590acb0-f753-4587-abb1-1149f9b8f561', '', '2019-01-18 09:18:40');
INSERT INTO `yzl_role` VALUES (47, '隆安县管理员', NULL, '', NULL);
INSERT INTO `yzl_role` VALUES (48, '柳州市管理员', 'f75dccb3-bcd0-4d60-a6ec-48edd9e76ba8', '', '2019-02-12 11:34:08');
INSERT INTO `yzl_role` VALUES (49, '融安县管理员', '30b7ae16-976e-47ec-9f57-199beb711a21', '', '2019-02-12 13:47:52');
INSERT INTO `yzl_role` VALUES (50, '桂林市管理员', NULL, '', NULL);
INSERT INTO `yzl_role` VALUES (51, '灵川县', NULL, '', NULL);
INSERT INTO `yzl_role` VALUES (53, '钦州市管理员', NULL, '', NULL);
INSERT INTO `yzl_role` VALUES (55, '灵山县管理员', NULL, '', NULL);
INSERT INTO `yzl_role` VALUES (56, 'xxx', 'c726a0f9-fcc1-447b-9b26-f437b623f496', '', '2019-04-12 14:01:06');
INSERT INTO `yzl_role` VALUES (57, '浦北县', '0b08e941-4a87-4b91-ae33-71b443aca87b', '', '2019-04-12 14:04:42');
INSERT INTO `yzl_role` VALUES (58, 'xxxxx', 'f6f54013-1a0e-4d2f-b57b-5d17373df205', '', '2019-04-16 15:05:37');

-- ----------------------------
-- Table structure for yzl_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `yzl_role_menu`;
CREATE TABLE `yzl_role_menu`  (
  `role_id` int(32) DEFAULT NULL COMMENT '角色编号/角色与菜单关系表',
  `menu_id` int(32) DEFAULT NULL COMMENT '菜单编号',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9492 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_role_menu
-- ----------------------------
INSERT INTO `yzl_role_menu` VALUES (23, 1, 273);
INSERT INTO `yzl_role_menu` VALUES (23, 2, 274);
INSERT INTO `yzl_role_menu` VALUES (23, 30, 275);
INSERT INTO `yzl_role_menu` VALUES (23, 64, 276);
INSERT INTO `yzl_role_menu` VALUES (23, 66, 277);
INSERT INTO `yzl_role_menu` VALUES (23, 67, 278);
INSERT INTO `yzl_role_menu` VALUES (23, 32, 279);
INSERT INTO `yzl_role_menu` VALUES (23, 70, 280);
INSERT INTO `yzl_role_menu` VALUES (23, 33, 281);
INSERT INTO `yzl_role_menu` VALUES (23, 73, 282);
INSERT INTO `yzl_role_menu` VALUES (23, 74, 283);
INSERT INTO `yzl_role_menu` VALUES (24, 1, 284);
INSERT INTO `yzl_role_menu` VALUES (24, 2, 285);
INSERT INTO `yzl_role_menu` VALUES (24, 30, 286);
INSERT INTO `yzl_role_menu` VALUES (24, 64, 287);
INSERT INTO `yzl_role_menu` VALUES (24, 65, 288);
INSERT INTO `yzl_role_menu` VALUES (24, 66, 289);
INSERT INTO `yzl_role_menu` VALUES (24, 67, 290);
INSERT INTO `yzl_role_menu` VALUES (24, 31, 291);
INSERT INTO `yzl_role_menu` VALUES (24, 68, 292);
INSERT INTO `yzl_role_menu` VALUES (24, 69, 293);
INSERT INTO `yzl_role_menu` VALUES (24, 153, 294);
INSERT INTO `yzl_role_menu` VALUES (24, 32, 295);
INSERT INTO `yzl_role_menu` VALUES (24, 70, 296);
INSERT INTO `yzl_role_menu` VALUES (24, 71, 297);
INSERT INTO `yzl_role_menu` VALUES (24, 154, 298);
INSERT INTO `yzl_role_menu` VALUES (24, 33, 299);
INSERT INTO `yzl_role_menu` VALUES (24, 72, 300);
INSERT INTO `yzl_role_menu` VALUES (24, 73, 301);
INSERT INTO `yzl_role_menu` VALUES (24, 74, 302);
INSERT INTO `yzl_role_menu` VALUES (24, 155, 303);
INSERT INTO `yzl_role_menu` VALUES (24, 3, 304);
INSERT INTO `yzl_role_menu` VALUES (24, 38, 305);
INSERT INTO `yzl_role_menu` VALUES (24, 4, 306);
INSERT INTO `yzl_role_menu` VALUES (24, 44, 307);
INSERT INTO `yzl_role_menu` VALUES (24, 5, 308);
INSERT INTO `yzl_role_menu` VALUES (24, 45, 309);
INSERT INTO `yzl_role_menu` VALUES (24, 6, 310);
INSERT INTO `yzl_role_menu` VALUES (24, 46, 311);
INSERT INTO `yzl_role_menu` VALUES (24, 89, 312);
INSERT INTO `yzl_role_menu` VALUES (24, 90, 313);
INSERT INTO `yzl_role_menu` VALUES (24, 91, 314);
INSERT INTO `yzl_role_menu` VALUES (24, 163, 315);
INSERT INTO `yzl_role_menu` VALUES (24, 47, 316);
INSERT INTO `yzl_role_menu` VALUES (24, 92, 317);
INSERT INTO `yzl_role_menu` VALUES (24, 93, 318);
INSERT INTO `yzl_role_menu` VALUES (24, 94, 319);
INSERT INTO `yzl_role_menu` VALUES (24, 164, 320);
INSERT INTO `yzl_role_menu` VALUES (24, 48, 321);
INSERT INTO `yzl_role_menu` VALUES (24, 95, 322);
INSERT INTO `yzl_role_menu` VALUES (24, 96, 323);
INSERT INTO `yzl_role_menu` VALUES (24, 97, 324);
INSERT INTO `yzl_role_menu` VALUES (24, 165, 325);
INSERT INTO `yzl_role_menu` VALUES (24, 49, 326);
INSERT INTO `yzl_role_menu` VALUES (24, 98, 327);
INSERT INTO `yzl_role_menu` VALUES (24, 99, 328);
INSERT INTO `yzl_role_menu` VALUES (24, 100, 329);
INSERT INTO `yzl_role_menu` VALUES (24, 166, 330);
INSERT INTO `yzl_role_menu` VALUES (24, 50, 331);
INSERT INTO `yzl_role_menu` VALUES (24, 101, 332);
INSERT INTO `yzl_role_menu` VALUES (24, 102, 333);
INSERT INTO `yzl_role_menu` VALUES (24, 103, 334);
INSERT INTO `yzl_role_menu` VALUES (24, 167, 335);
INSERT INTO `yzl_role_menu` VALUES (24, 51, 336);
INSERT INTO `yzl_role_menu` VALUES (24, 104, 337);
INSERT INTO `yzl_role_menu` VALUES (24, 105, 338);
INSERT INTO `yzl_role_menu` VALUES (24, 106, 339);
INSERT INTO `yzl_role_menu` VALUES (24, 107, 340);
INSERT INTO `yzl_role_menu` VALUES (24, 108, 341);
INSERT INTO `yzl_role_menu` VALUES (24, 109, 342);
INSERT INTO `yzl_role_menu` VALUES (24, 168, 343);
INSERT INTO `yzl_role_menu` VALUES (24, 52, 344);
INSERT INTO `yzl_role_menu` VALUES (24, 110, 345);
INSERT INTO `yzl_role_menu` VALUES (24, 111, 346);
INSERT INTO `yzl_role_menu` VALUES (24, 112, 347);
INSERT INTO `yzl_role_menu` VALUES (24, 169, 348);
INSERT INTO `yzl_role_menu` VALUES (24, 7, 349);
INSERT INTO `yzl_role_menu` VALUES (24, 53, 350);
INSERT INTO `yzl_role_menu` VALUES (24, 113, 351);
INSERT INTO `yzl_role_menu` VALUES (24, 114, 352);
INSERT INTO `yzl_role_menu` VALUES (24, 115, 353);
INSERT INTO `yzl_role_menu` VALUES (24, 170, 354);
INSERT INTO `yzl_role_menu` VALUES (24, 54, 355);
INSERT INTO `yzl_role_menu` VALUES (24, 116, 356);
INSERT INTO `yzl_role_menu` VALUES (24, 117, 357);
INSERT INTO `yzl_role_menu` VALUES (24, 118, 358);
INSERT INTO `yzl_role_menu` VALUES (24, 171, 359);
INSERT INTO `yzl_role_menu` VALUES (24, 55, 360);
INSERT INTO `yzl_role_menu` VALUES (24, 119, 361);
INSERT INTO `yzl_role_menu` VALUES (24, 120, 362);
INSERT INTO `yzl_role_menu` VALUES (24, 121, 363);
INSERT INTO `yzl_role_menu` VALUES (24, 122, 364);
INSERT INTO `yzl_role_menu` VALUES (24, 56, 365);
INSERT INTO `yzl_role_menu` VALUES (24, 123, 366);
INSERT INTO `yzl_role_menu` VALUES (24, 124, 367);
INSERT INTO `yzl_role_menu` VALUES (24, 125, 368);
INSERT INTO `yzl_role_menu` VALUES (24, 173, 369);
INSERT INTO `yzl_role_menu` VALUES (24, 8, 370);
INSERT INTO `yzl_role_menu` VALUES (24, 57, 371);
INSERT INTO `yzl_role_menu` VALUES (24, 126, 372);
INSERT INTO `yzl_role_menu` VALUES (24, 127, 373);
INSERT INTO `yzl_role_menu` VALUES (24, 128, 374);
INSERT INTO `yzl_role_menu` VALUES (24, 174, 375);
INSERT INTO `yzl_role_menu` VALUES (24, 58, 376);
INSERT INTO `yzl_role_menu` VALUES (24, 129, 377);
INSERT INTO `yzl_role_menu` VALUES (24, 130, 378);
INSERT INTO `yzl_role_menu` VALUES (24, 131, 379);
INSERT INTO `yzl_role_menu` VALUES (24, 175, 380);
INSERT INTO `yzl_role_menu` VALUES (24, 59, 381);
INSERT INTO `yzl_role_menu` VALUES (24, 132, 382);
INSERT INTO `yzl_role_menu` VALUES (24, 133, 383);
INSERT INTO `yzl_role_menu` VALUES (24, 134, 384);
INSERT INTO `yzl_role_menu` VALUES (24, 176, 385);
INSERT INTO `yzl_role_menu` VALUES (24, 60, 386);
INSERT INTO `yzl_role_menu` VALUES (24, 135, 387);
INSERT INTO `yzl_role_menu` VALUES (24, 136, 388);
INSERT INTO `yzl_role_menu` VALUES (24, 137, 389);
INSERT INTO `yzl_role_menu` VALUES (24, 177, 390);
INSERT INTO `yzl_role_menu` VALUES (24, 61, 391);
INSERT INTO `yzl_role_menu` VALUES (24, 138, 392);
INSERT INTO `yzl_role_menu` VALUES (24, 139, 393);
INSERT INTO `yzl_role_menu` VALUES (24, 140, 394);
INSERT INTO `yzl_role_menu` VALUES (24, 178, 395);
INSERT INTO `yzl_role_menu` VALUES (24, 62, 396);
INSERT INTO `yzl_role_menu` VALUES (24, 141, 397);
INSERT INTO `yzl_role_menu` VALUES (24, 142, 398);
INSERT INTO `yzl_role_menu` VALUES (24, 143, 399);
INSERT INTO `yzl_role_menu` VALUES (24, 181, 400);
INSERT INTO `yzl_role_menu` VALUES (24, 9, 401);
INSERT INTO `yzl_role_menu` VALUES (24, 39, 402);
INSERT INTO `yzl_role_menu` VALUES (24, 144, 403);
INSERT INTO `yzl_role_menu` VALUES (24, 145, 404);
INSERT INTO `yzl_role_menu` VALUES (24, 146, 405);
INSERT INTO `yzl_role_menu` VALUES (24, 161, 406);
INSERT INTO `yzl_role_menu` VALUES (24, 40, 407);
INSERT INTO `yzl_role_menu` VALUES (24, 147, 408);
INSERT INTO `yzl_role_menu` VALUES (24, 148, 409);
INSERT INTO `yzl_role_menu` VALUES (24, 149, 410);
INSERT INTO `yzl_role_menu` VALUES (24, 179, 411);
INSERT INTO `yzl_role_menu` VALUES (24, 41, 412);
INSERT INTO `yzl_role_menu` VALUES (24, 150, 413);
INSERT INTO `yzl_role_menu` VALUES (24, 151, 414);
INSERT INTO `yzl_role_menu` VALUES (24, 152, 415);
INSERT INTO `yzl_role_menu` VALUES (24, 180, 416);
INSERT INTO `yzl_role_menu` VALUES (1, 5, 499);
INSERT INTO `yzl_role_menu` VALUES (1, 45, 500);
INSERT INTO `yzl_role_menu` VALUES (1, 182, 501);
INSERT INTO `yzl_role_menu` VALUES (1, 183, 502);
INSERT INTO `yzl_role_menu` VALUES (1, 184, 503);
INSERT INTO `yzl_role_menu` VALUES (1, 185, 504);
INSERT INTO `yzl_role_menu` VALUES (1, 186, 505);
INSERT INTO `yzl_role_menu` VALUES (44, 4, 7537);
INSERT INTO `yzl_role_menu` VALUES (44, 512, 7538);
INSERT INTO `yzl_role_menu` VALUES (44, 541, 7539);
INSERT INTO `yzl_role_menu` VALUES (44, 411, 7540);
INSERT INTO `yzl_role_menu` VALUES (44, 6, 7541);
INSERT INTO `yzl_role_menu` VALUES (44, 348, 7542);
INSERT INTO `yzl_role_menu` VALUES (44, 9, 7543);
INSERT INTO `yzl_role_menu` VALUES (44, 451, 7544);
INSERT INTO `yzl_role_menu` VALUES (44, 457, 7545);
INSERT INTO `yzl_role_menu` VALUES (46, 6, 7551);
INSERT INTO `yzl_role_menu` VALUES (46, 348, 7552);
INSERT INTO `yzl_role_menu` VALUES (46, 9, 7553);
INSERT INTO `yzl_role_menu` VALUES (46, 451, 7554);
INSERT INTO `yzl_role_menu` VALUES (46, 456, 7555);
INSERT INTO `yzl_role_menu` VALUES (48, 4, 7702);
INSERT INTO `yzl_role_menu` VALUES (48, 541, 7703);
INSERT INTO `yzl_role_menu` VALUES (48, 640, 7704);
INSERT INTO `yzl_role_menu` VALUES (48, 641, 7705);
INSERT INTO `yzl_role_menu` VALUES (48, 642, 7706);
INSERT INTO `yzl_role_menu` VALUES (48, 643, 7707);
INSERT INTO `yzl_role_menu` VALUES (48, 644, 7708);
INSERT INTO `yzl_role_menu` VALUES (48, 645, 7709);
INSERT INTO `yzl_role_menu` VALUES (48, 646, 7710);
INSERT INTO `yzl_role_menu` VALUES (48, 647, 7711);
INSERT INTO `yzl_role_menu` VALUES (48, 648, 7712);
INSERT INTO `yzl_role_menu` VALUES (48, 44, 7713);
INSERT INTO `yzl_role_menu` VALUES (48, 534, 7714);
INSERT INTO `yzl_role_menu` VALUES (48, 535, 7715);
INSERT INTO `yzl_role_menu` VALUES (48, 536, 7716);
INSERT INTO `yzl_role_menu` VALUES (48, 537, 7717);
INSERT INTO `yzl_role_menu` VALUES (48, 538, 7718);
INSERT INTO `yzl_role_menu` VALUES (48, 539, 7719);
INSERT INTO `yzl_role_menu` VALUES (48, 540, 7720);
INSERT INTO `yzl_role_menu` VALUES (48, 5, 7721);
INSERT INTO `yzl_role_menu` VALUES (48, 512, 7722);
INSERT INTO `yzl_role_menu` VALUES (48, 45, 7723);
INSERT INTO `yzl_role_menu` VALUES (48, 9, 7724);
INSERT INTO `yzl_role_menu` VALUES (48, 452, 7725);
INSERT INTO `yzl_role_menu` VALUES (48, 465, 7726);
INSERT INTO `yzl_role_menu` VALUES (48, 466, 7727);
INSERT INTO `yzl_role_menu` VALUES (48, 467, 7728);
INSERT INTO `yzl_role_menu` VALUES (48, 468, 7729);
INSERT INTO `yzl_role_menu` VALUES (48, 469, 7730);
INSERT INTO `yzl_role_menu` VALUES (48, 470, 7731);
INSERT INTO `yzl_role_menu` VALUES (48, 471, 7732);
INSERT INTO `yzl_role_menu` VALUES (48, 472, 7733);
INSERT INTO `yzl_role_menu` VALUES (48, 473, 7734);
INSERT INTO `yzl_role_menu` VALUES (48, 474, 7735);
INSERT INTO `yzl_role_menu` VALUES (49, 4, 7736);
INSERT INTO `yzl_role_menu` VALUES (49, 541, 7737);
INSERT INTO `yzl_role_menu` VALUES (49, 640, 7738);
INSERT INTO `yzl_role_menu` VALUES (49, 641, 7739);
INSERT INTO `yzl_role_menu` VALUES (49, 642, 7740);
INSERT INTO `yzl_role_menu` VALUES (49, 643, 7741);
INSERT INTO `yzl_role_menu` VALUES (49, 644, 7742);
INSERT INTO `yzl_role_menu` VALUES (49, 645, 7743);
INSERT INTO `yzl_role_menu` VALUES (49, 646, 7744);
INSERT INTO `yzl_role_menu` VALUES (49, 647, 7745);
INSERT INTO `yzl_role_menu` VALUES (49, 648, 7746);
INSERT INTO `yzl_role_menu` VALUES (49, 42, 7747);
INSERT INTO `yzl_role_menu` VALUES (49, 44, 7748);
INSERT INTO `yzl_role_menu` VALUES (49, 534, 7749);
INSERT INTO `yzl_role_menu` VALUES (49, 535, 7750);
INSERT INTO `yzl_role_menu` VALUES (49, 536, 7751);
INSERT INTO `yzl_role_menu` VALUES (49, 537, 7752);
INSERT INTO `yzl_role_menu` VALUES (49, 538, 7753);
INSERT INTO `yzl_role_menu` VALUES (49, 539, 7754);
INSERT INTO `yzl_role_menu` VALUES (49, 540, 7755);
INSERT INTO `yzl_role_menu` VALUES (49, 5, 7756);
INSERT INTO `yzl_role_menu` VALUES (49, 512, 7757);
INSERT INTO `yzl_role_menu` VALUES (49, 45, 7758);
INSERT INTO `yzl_role_menu` VALUES (49, 6, 7759);
INSERT INTO `yzl_role_menu` VALUES (49, 411, 7760);
INSERT INTO `yzl_role_menu` VALUES (49, 348, 7761);
INSERT INTO `yzl_role_menu` VALUES (49, 9, 7762);
INSERT INTO `yzl_role_menu` VALUES (49, 452, 7763);
INSERT INTO `yzl_role_menu` VALUES (49, 472, 7764);
INSERT INTO `yzl_role_menu` VALUES (45, 4, 8459);
INSERT INTO `yzl_role_menu` VALUES (45, 650, 8460);
INSERT INTO `yzl_role_menu` VALUES (45, 664, 8461);
INSERT INTO `yzl_role_menu` VALUES (45, 665, 8462);
INSERT INTO `yzl_role_menu` VALUES (45, 651, 8463);
INSERT INTO `yzl_role_menu` VALUES (45, 7, 8464);
INSERT INTO `yzl_role_menu` VALUES (45, 411, 8465);
INSERT INTO `yzl_role_menu` VALUES (45, 666, 8466);
INSERT INTO `yzl_role_menu` VALUES (45, 9, 8467);
INSERT INTO `yzl_role_menu` VALUES (45, 451, 8468);
INSERT INTO `yzl_role_menu` VALUES (45, 455, 8469);
INSERT INTO `yzl_role_menu` VALUES (47, 4, 8645);
INSERT INTO `yzl_role_menu` VALUES (47, 650, 8646);
INSERT INTO `yzl_role_menu` VALUES (47, 664, 8647);
INSERT INTO `yzl_role_menu` VALUES (47, 665, 8648);
INSERT INTO `yzl_role_menu` VALUES (47, 7, 8649);
INSERT INTO `yzl_role_menu` VALUES (47, 411, 8650);
INSERT INTO `yzl_role_menu` VALUES (47, 666, 8651);
INSERT INTO `yzl_role_menu` VALUES (47, 667, 8652);
INSERT INTO `yzl_role_menu` VALUES (47, 9, 8653);
INSERT INTO `yzl_role_menu` VALUES (47, 451, 8654);
INSERT INTO `yzl_role_menu` VALUES (47, 461, 8655);
INSERT INTO `yzl_role_menu` VALUES (35, 4, 8674);
INSERT INTO `yzl_role_menu` VALUES (35, 649, 8675);
INSERT INTO `yzl_role_menu` VALUES (35, 661, 8676);
INSERT INTO `yzl_role_menu` VALUES (35, 650, 8677);
INSERT INTO `yzl_role_menu` VALUES (35, 664, 8678);
INSERT INTO `yzl_role_menu` VALUES (35, 665, 8679);
INSERT INTO `yzl_role_menu` VALUES (35, 651, 8680);
INSERT INTO `yzl_role_menu` VALUES (35, 411, 8681);
INSERT INTO `yzl_role_menu` VALUES (35, 9, 8682);
INSERT INTO `yzl_role_menu` VALUES (35, 451, 8683);
INSERT INTO `yzl_role_menu` VALUES (35, 453, 8684);
INSERT INTO `yzl_role_menu` VALUES (50, 4, 8717);
INSERT INTO `yzl_role_menu` VALUES (50, 649, 8718);
INSERT INTO `yzl_role_menu` VALUES (50, 658, 8719);
INSERT INTO `yzl_role_menu` VALUES (50, 661, 8720);
INSERT INTO `yzl_role_menu` VALUES (50, 650, 8721);
INSERT INTO `yzl_role_menu` VALUES (50, 662, 8722);
INSERT INTO `yzl_role_menu` VALUES (50, 663, 8723);
INSERT INTO `yzl_role_menu` VALUES (50, 665, 8724);
INSERT INTO `yzl_role_menu` VALUES (50, 651, 8725);
INSERT INTO `yzl_role_menu` VALUES (50, 7, 8726);
INSERT INTO `yzl_role_menu` VALUES (50, 411, 8727);
INSERT INTO `yzl_role_menu` VALUES (50, 666, 8728);
INSERT INTO `yzl_role_menu` VALUES (50, 671, 8729);
INSERT INTO `yzl_role_menu` VALUES (50, 9, 8730);
INSERT INTO `yzl_role_menu` VALUES (50, 475, 8731);
INSERT INTO `yzl_role_menu` VALUES (50, 476, 8732);
INSERT INTO `yzl_role_menu` VALUES (50, 477, 8733);
INSERT INTO `yzl_role_menu` VALUES (50, 478, 8734);
INSERT INTO `yzl_role_menu` VALUES (50, 479, 8735);
INSERT INTO `yzl_role_menu` VALUES (50, 480, 8736);
INSERT INTO `yzl_role_menu` VALUES (50, 481, 8737);
INSERT INTO `yzl_role_menu` VALUES (50, 482, 8738);
INSERT INTO `yzl_role_menu` VALUES (50, 483, 8739);
INSERT INTO `yzl_role_menu` VALUES (50, 484, 8740);
INSERT INTO `yzl_role_menu` VALUES (50, 485, 8741);
INSERT INTO `yzl_role_menu` VALUES (50, 486, 8742);
INSERT INTO `yzl_role_menu` VALUES (50, 487, 8743);
INSERT INTO `yzl_role_menu` VALUES (50, 488, 8744);
INSERT INTO `yzl_role_menu` VALUES (50, 489, 8745);
INSERT INTO `yzl_role_menu` VALUES (50, 490, 8746);
INSERT INTO `yzl_role_menu` VALUES (50, 491, 8747);
INSERT INTO `yzl_role_menu` VALUES (50, 492, 8748);
INSERT INTO `yzl_role_menu` VALUES (50, 493, 8749);
INSERT INTO `yzl_role_menu` VALUES (51, 4, 8859);
INSERT INTO `yzl_role_menu` VALUES (51, 649, 8860);
INSERT INTO `yzl_role_menu` VALUES (51, 658, 8861);
INSERT INTO `yzl_role_menu` VALUES (51, 661, 8862);
INSERT INTO `yzl_role_menu` VALUES (51, 650, 8863);
INSERT INTO `yzl_role_menu` VALUES (51, 664, 8864);
INSERT INTO `yzl_role_menu` VALUES (51, 665, 8865);
INSERT INTO `yzl_role_menu` VALUES (51, 651, 8866);
INSERT INTO `yzl_role_menu` VALUES (51, 666, 8867);
INSERT INTO `yzl_role_menu` VALUES (51, 7, 8868);
INSERT INTO `yzl_role_menu` VALUES (51, 411, 8869);
INSERT INTO `yzl_role_menu` VALUES (51, 671, 8870);
INSERT INTO `yzl_role_menu` VALUES (51, 9, 8871);
INSERT INTO `yzl_role_menu` VALUES (51, 550, 8872);
INSERT INTO `yzl_role_menu` VALUES (51, 553, 8873);
INSERT INTO `yzl_role_menu` VALUES (56, 4, 8929);
INSERT INTO `yzl_role_menu` VALUES (56, 649, 8930);
INSERT INTO `yzl_role_menu` VALUES (56, 658, 8931);
INSERT INTO `yzl_role_menu` VALUES (56, 661, 8932);
INSERT INTO `yzl_role_menu` VALUES (56, 650, 8933);
INSERT INTO `yzl_role_menu` VALUES (56, 663, 8934);
INSERT INTO `yzl_role_menu` VALUES (56, 664, 8935);
INSERT INTO `yzl_role_menu` VALUES (56, 665, 8936);
INSERT INTO `yzl_role_menu` VALUES (56, 651, 8937);
INSERT INTO `yzl_role_menu` VALUES (56, 666, 8938);
INSERT INTO `yzl_role_menu` VALUES (56, 7, 8939);
INSERT INTO `yzl_role_menu` VALUES (56, 411, 8940);
INSERT INTO `yzl_role_menu` VALUES (56, 671, 8941);
INSERT INTO `yzl_role_menu` VALUES (56, 9, 8942);
INSERT INTO `yzl_role_menu` VALUES (56, 550, 8943);
INSERT INTO `yzl_role_menu` VALUES (56, 551, 8944);
INSERT INTO `yzl_role_menu` VALUES (57, 4, 8945);
INSERT INTO `yzl_role_menu` VALUES (57, 649, 8946);
INSERT INTO `yzl_role_menu` VALUES (57, 658, 8947);
INSERT INTO `yzl_role_menu` VALUES (57, 661, 8948);
INSERT INTO `yzl_role_menu` VALUES (57, 650, 8949);
INSERT INTO `yzl_role_menu` VALUES (57, 664, 8950);
INSERT INTO `yzl_role_menu` VALUES (57, 665, 8951);
INSERT INTO `yzl_role_menu` VALUES (57, 651, 8952);
INSERT INTO `yzl_role_menu` VALUES (57, 666, 8953);
INSERT INTO `yzl_role_menu` VALUES (57, 7, 8954);
INSERT INTO `yzl_role_menu` VALUES (57, 411, 8955);
INSERT INTO `yzl_role_menu` VALUES (57, 671, 8956);
INSERT INTO `yzl_role_menu` VALUES (57, 9, 8957);
INSERT INTO `yzl_role_menu` VALUES (57, 550, 8958);
INSERT INTO `yzl_role_menu` VALUES (57, 554, 8959);
INSERT INTO `yzl_role_menu` VALUES (58, 451, 9028);
INSERT INTO `yzl_role_menu` VALUES (58, 453, 9029);
INSERT INTO `yzl_role_menu` VALUES (58, 454, 9030);
INSERT INTO `yzl_role_menu` VALUES (58, 455, 9031);
INSERT INTO `yzl_role_menu` VALUES (58, 456, 9032);
INSERT INTO `yzl_role_menu` VALUES (58, 457, 9033);
INSERT INTO `yzl_role_menu` VALUES (58, 458, 9034);
INSERT INTO `yzl_role_menu` VALUES (58, 459, 9035);
INSERT INTO `yzl_role_menu` VALUES (58, 460, 9036);
INSERT INTO `yzl_role_menu` VALUES (58, 461, 9037);
INSERT INTO `yzl_role_menu` VALUES (58, 462, 9038);
INSERT INTO `yzl_role_menu` VALUES (58, 463, 9039);
INSERT INTO `yzl_role_menu` VALUES (58, 464, 9040);
INSERT INTO `yzl_role_menu` VALUES (58, 4, 9041);
INSERT INTO `yzl_role_menu` VALUES (58, 649, 9042);
INSERT INTO `yzl_role_menu` VALUES (58, 661, 9043);
INSERT INTO `yzl_role_menu` VALUES (58, 650, 9044);
INSERT INTO `yzl_role_menu` VALUES (58, 665, 9045);
INSERT INTO `yzl_role_menu` VALUES (58, 651, 9046);
INSERT INTO `yzl_role_menu` VALUES (58, 666, 9047);
INSERT INTO `yzl_role_menu` VALUES (58, 7, 9048);
INSERT INTO `yzl_role_menu` VALUES (58, 411, 9049);
INSERT INTO `yzl_role_menu` VALUES (55, 1, 9224);
INSERT INTO `yzl_role_menu` VALUES (55, 4, 9225);
INSERT INTO `yzl_role_menu` VALUES (55, 649, 9226);
INSERT INTO `yzl_role_menu` VALUES (55, 661, 9227);
INSERT INTO `yzl_role_menu` VALUES (55, 650, 9228);
INSERT INTO `yzl_role_menu` VALUES (55, 664, 9229);
INSERT INTO `yzl_role_menu` VALUES (55, 665, 9230);
INSERT INTO `yzl_role_menu` VALUES (55, 651, 9231);
INSERT INTO `yzl_role_menu` VALUES (55, 666, 9232);
INSERT INTO `yzl_role_menu` VALUES (55, 7, 9233);
INSERT INTO `yzl_role_menu` VALUES (55, 411, 9234);
INSERT INTO `yzl_role_menu` VALUES (55, 671, 9235);
INSERT INTO `yzl_role_menu` VALUES (55, 9, 9236);
INSERT INTO `yzl_role_menu` VALUES (55, 550, 9237);
INSERT INTO `yzl_role_menu` VALUES (55, 553, 9238);
INSERT INTO `yzl_role_menu` VALUES (53, 1, 9258);
INSERT INTO `yzl_role_menu` VALUES (53, 4, 9259);
INSERT INTO `yzl_role_menu` VALUES (53, 649, 9260);
INSERT INTO `yzl_role_menu` VALUES (53, 661, 9261);
INSERT INTO `yzl_role_menu` VALUES (53, 650, 9262);
INSERT INTO `yzl_role_menu` VALUES (53, 662, 9263);
INSERT INTO `yzl_role_menu` VALUES (53, 663, 9264);
INSERT INTO `yzl_role_menu` VALUES (53, 665, 9265);
INSERT INTO `yzl_role_menu` VALUES (53, 651, 9266);
INSERT INTO `yzl_role_menu` VALUES (53, 666, 9267);
INSERT INTO `yzl_role_menu` VALUES (53, 7, 9268);
INSERT INTO `yzl_role_menu` VALUES (53, 411, 9269);
INSERT INTO `yzl_role_menu` VALUES (53, 9, 9270);
INSERT INTO `yzl_role_menu` VALUES (53, 550, 9271);
INSERT INTO `yzl_role_menu` VALUES (53, 551, 9272);
INSERT INTO `yzl_role_menu` VALUES (53, 552, 9273);
INSERT INTO `yzl_role_menu` VALUES (53, 553, 9274);
INSERT INTO `yzl_role_menu` VALUES (53, 554, 9275);
INSERT INTO `yzl_role_menu` VALUES (32, 1, 9276);
INSERT INTO `yzl_role_menu` VALUES (32, 4, 9277);
INSERT INTO `yzl_role_menu` VALUES (32, 649, 9278);
INSERT INTO `yzl_role_menu` VALUES (32, 658, 9279);
INSERT INTO `yzl_role_menu` VALUES (32, 661, 9280);
INSERT INTO `yzl_role_menu` VALUES (32, 650, 9281);
INSERT INTO `yzl_role_menu` VALUES (32, 662, 9282);
INSERT INTO `yzl_role_menu` VALUES (32, 663, 9283);
INSERT INTO `yzl_role_menu` VALUES (32, 665, 9284);
INSERT INTO `yzl_role_menu` VALUES (32, 651, 9285);
INSERT INTO `yzl_role_menu` VALUES (32, 666, 9286);
INSERT INTO `yzl_role_menu` VALUES (32, 7, 9287);
INSERT INTO `yzl_role_menu` VALUES (32, 411, 9288);
INSERT INTO `yzl_role_menu` VALUES (32, 9, 9289);
INSERT INTO `yzl_role_menu` VALUES (32, 451, 9290);
INSERT INTO `yzl_role_menu` VALUES (32, 453, 9291);
INSERT INTO `yzl_role_menu` VALUES (32, 454, 9292);
INSERT INTO `yzl_role_menu` VALUES (32, 455, 9293);
INSERT INTO `yzl_role_menu` VALUES (32, 456, 9294);
INSERT INTO `yzl_role_menu` VALUES (32, 457, 9295);
INSERT INTO `yzl_role_menu` VALUES (32, 458, 9296);
INSERT INTO `yzl_role_menu` VALUES (32, 459, 9297);
INSERT INTO `yzl_role_menu` VALUES (32, 460, 9298);
INSERT INTO `yzl_role_menu` VALUES (32, 461, 9299);
INSERT INTO `yzl_role_menu` VALUES (32, 462, 9300);
INSERT INTO `yzl_role_menu` VALUES (32, 463, 9301);
INSERT INTO `yzl_role_menu` VALUES (32, 464, 9302);
INSERT INTO `yzl_role_menu` VALUES (34, 1, 9303);
INSERT INTO `yzl_role_menu` VALUES (34, 4, 9304);
INSERT INTO `yzl_role_menu` VALUES (34, 649, 9305);
INSERT INTO `yzl_role_menu` VALUES (34, 661, 9306);
INSERT INTO `yzl_role_menu` VALUES (34, 650, 9307);
INSERT INTO `yzl_role_menu` VALUES (34, 664, 9308);
INSERT INTO `yzl_role_menu` VALUES (34, 665, 9309);
INSERT INTO `yzl_role_menu` VALUES (34, 651, 9310);
INSERT INTO `yzl_role_menu` VALUES (34, 666, 9311);
INSERT INTO `yzl_role_menu` VALUES (34, 7, 9312);
INSERT INTO `yzl_role_menu` VALUES (34, 411, 9313);
INSERT INTO `yzl_role_menu` VALUES (34, 9, 9314);
INSERT INTO `yzl_role_menu` VALUES (34, 451, 9315);
INSERT INTO `yzl_role_menu` VALUES (34, 454, 9316);
INSERT INTO `yzl_role_menu` VALUES (2, 1, 9317);
INSERT INTO `yzl_role_menu` VALUES (2, 4, 9318);
INSERT INTO `yzl_role_menu` VALUES (2, 649, 9319);
INSERT INTO `yzl_role_menu` VALUES (2, 653, 9320);
INSERT INTO `yzl_role_menu` VALUES (2, 654, 9321);
INSERT INTO `yzl_role_menu` VALUES (2, 655, 9322);
INSERT INTO `yzl_role_menu` VALUES (2, 656, 9323);
INSERT INTO `yzl_role_menu` VALUES (2, 657, 9324);
INSERT INTO `yzl_role_menu` VALUES (2, 658, 9325);
INSERT INTO `yzl_role_menu` VALUES (2, 660, 9326);
INSERT INTO `yzl_role_menu` VALUES (2, 661, 9327);
INSERT INTO `yzl_role_menu` VALUES (2, 650, 9328);
INSERT INTO `yzl_role_menu` VALUES (2, 662, 9329);
INSERT INTO `yzl_role_menu` VALUES (2, 663, 9330);
INSERT INTO `yzl_role_menu` VALUES (2, 664, 9331);
INSERT INTO `yzl_role_menu` VALUES (2, 665, 9332);
INSERT INTO `yzl_role_menu` VALUES (2, 651, 9333);
INSERT INTO `yzl_role_menu` VALUES (2, 666, 9334);
INSERT INTO `yzl_role_menu` VALUES (2, 667, 9335);
INSERT INTO `yzl_role_menu` VALUES (2, 7, 9336);
INSERT INTO `yzl_role_menu` VALUES (2, 411, 9337);
INSERT INTO `yzl_role_menu` VALUES (2, 9, 9338);
INSERT INTO `yzl_role_menu` VALUES (2, 543, 9339);
INSERT INTO `yzl_role_menu` VALUES (2, 544, 9340);
INSERT INTO `yzl_role_menu` VALUES (2, 545, 9341);
INSERT INTO `yzl_role_menu` VALUES (2, 546, 9342);
INSERT INTO `yzl_role_menu` VALUES (2, 547, 9343);
INSERT INTO `yzl_role_menu` VALUES (2, 548, 9344);
INSERT INTO `yzl_role_menu` VALUES (2, 549, 9345);
INSERT INTO `yzl_role_menu` VALUES (2, 550, 9346);
INSERT INTO `yzl_role_menu` VALUES (2, 551, 9347);
INSERT INTO `yzl_role_menu` VALUES (2, 552, 9348);
INSERT INTO `yzl_role_menu` VALUES (2, 553, 9349);
INSERT INTO `yzl_role_menu` VALUES (2, 554, 9350);
INSERT INTO `yzl_role_menu` VALUES (2, 555, 9351);
INSERT INTO `yzl_role_menu` VALUES (2, 556, 9352);
INSERT INTO `yzl_role_menu` VALUES (2, 557, 9353);
INSERT INTO `yzl_role_menu` VALUES (2, 558, 9354);
INSERT INTO `yzl_role_menu` VALUES (2, 559, 9355);
INSERT INTO `yzl_role_menu` VALUES (2, 560, 9356);
INSERT INTO `yzl_role_menu` VALUES (2, 561, 9357);
INSERT INTO `yzl_role_menu` VALUES (2, 562, 9358);
INSERT INTO `yzl_role_menu` VALUES (2, 563, 9359);
INSERT INTO `yzl_role_menu` VALUES (2, 564, 9360);
INSERT INTO `yzl_role_menu` VALUES (2, 565, 9361);
INSERT INTO `yzl_role_menu` VALUES (2, 566, 9362);
INSERT INTO `yzl_role_menu` VALUES (2, 567, 9363);
INSERT INTO `yzl_role_menu` VALUES (2, 568, 9364);
INSERT INTO `yzl_role_menu` VALUES (2, 569, 9365);
INSERT INTO `yzl_role_menu` VALUES (2, 570, 9366);
INSERT INTO `yzl_role_menu` VALUES (2, 571, 9367);
INSERT INTO `yzl_role_menu` VALUES (2, 572, 9368);
INSERT INTO `yzl_role_menu` VALUES (2, 573, 9369);
INSERT INTO `yzl_role_menu` VALUES (2, 574, 9370);
INSERT INTO `yzl_role_menu` VALUES (2, 575, 9371);
INSERT INTO `yzl_role_menu` VALUES (2, 576, 9372);
INSERT INTO `yzl_role_menu` VALUES (2, 577, 9373);
INSERT INTO `yzl_role_menu` VALUES (2, 578, 9374);
INSERT INTO `yzl_role_menu` VALUES (2, 579, 9375);
INSERT INTO `yzl_role_menu` VALUES (2, 580, 9376);
INSERT INTO `yzl_role_menu` VALUES (2, 581, 9377);
INSERT INTO `yzl_role_menu` VALUES (2, 582, 9378);
INSERT INTO `yzl_role_menu` VALUES (2, 583, 9379);
INSERT INTO `yzl_role_menu` VALUES (2, 584, 9380);
INSERT INTO `yzl_role_menu` VALUES (2, 585, 9381);
INSERT INTO `yzl_role_menu` VALUES (2, 586, 9382);
INSERT INTO `yzl_role_menu` VALUES (2, 587, 9383);
INSERT INTO `yzl_role_menu` VALUES (2, 588, 9384);
INSERT INTO `yzl_role_menu` VALUES (2, 589, 9385);
INSERT INTO `yzl_role_menu` VALUES (2, 590, 9386);
INSERT INTO `yzl_role_menu` VALUES (2, 591, 9387);
INSERT INTO `yzl_role_menu` VALUES (2, 592, 9388);
INSERT INTO `yzl_role_menu` VALUES (2, 593, 9389);
INSERT INTO `yzl_role_menu` VALUES (2, 594, 9390);
INSERT INTO `yzl_role_menu` VALUES (2, 595, 9391);
INSERT INTO `yzl_role_menu` VALUES (2, 596, 9392);
INSERT INTO `yzl_role_menu` VALUES (2, 597, 9393);
INSERT INTO `yzl_role_menu` VALUES (2, 598, 9394);
INSERT INTO `yzl_role_menu` VALUES (2, 599, 9395);
INSERT INTO `yzl_role_menu` VALUES (2, 600, 9396);
INSERT INTO `yzl_role_menu` VALUES (2, 601, 9397);
INSERT INTO `yzl_role_menu` VALUES (2, 602, 9398);
INSERT INTO `yzl_role_menu` VALUES (2, 603, 9399);
INSERT INTO `yzl_role_menu` VALUES (2, 604, 9400);
INSERT INTO `yzl_role_menu` VALUES (2, 605, 9401);
INSERT INTO `yzl_role_menu` VALUES (2, 606, 9402);
INSERT INTO `yzl_role_menu` VALUES (2, 607, 9403);
INSERT INTO `yzl_role_menu` VALUES (2, 608, 9404);
INSERT INTO `yzl_role_menu` VALUES (2, 609, 9405);
INSERT INTO `yzl_role_menu` VALUES (2, 610, 9406);
INSERT INTO `yzl_role_menu` VALUES (2, 611, 9407);
INSERT INTO `yzl_role_menu` VALUES (2, 612, 9408);
INSERT INTO `yzl_role_menu` VALUES (2, 613, 9409);
INSERT INTO `yzl_role_menu` VALUES (2, 614, 9410);
INSERT INTO `yzl_role_menu` VALUES (2, 615, 9411);
INSERT INTO `yzl_role_menu` VALUES (2, 616, 9412);
INSERT INTO `yzl_role_menu` VALUES (2, 617, 9413);
INSERT INTO `yzl_role_menu` VALUES (2, 618, 9414);
INSERT INTO `yzl_role_menu` VALUES (2, 619, 9415);
INSERT INTO `yzl_role_menu` VALUES (2, 620, 9416);
INSERT INTO `yzl_role_menu` VALUES (2, 621, 9417);
INSERT INTO `yzl_role_menu` VALUES (2, 622, 9418);
INSERT INTO `yzl_role_menu` VALUES (2, 623, 9419);
INSERT INTO `yzl_role_menu` VALUES (2, 624, 9420);
INSERT INTO `yzl_role_menu` VALUES (2, 625, 9421);
INSERT INTO `yzl_role_menu` VALUES (2, 626, 9422);
INSERT INTO `yzl_role_menu` VALUES (2, 627, 9423);
INSERT INTO `yzl_role_menu` VALUES (2, 628, 9424);
INSERT INTO `yzl_role_menu` VALUES (2, 629, 9425);
INSERT INTO `yzl_role_menu` VALUES (2, 630, 9426);
INSERT INTO `yzl_role_menu` VALUES (2, 631, 9427);
INSERT INTO `yzl_role_menu` VALUES (2, 632, 9428);
INSERT INTO `yzl_role_menu` VALUES (2, 633, 9429);
INSERT INTO `yzl_role_menu` VALUES (2, 634, 9430);
INSERT INTO `yzl_role_menu` VALUES (2, 635, 9431);
INSERT INTO `yzl_role_menu` VALUES (2, 636, 9432);
INSERT INTO `yzl_role_menu` VALUES (2, 679, 9433);
INSERT INTO `yzl_role_menu` VALUES (2, 668, 9434);
INSERT INTO `yzl_role_menu` VALUES (2, 669, 9435);
INSERT INTO `yzl_role_menu` VALUES (2, 670, 9436);
INSERT INTO `yzl_role_menu` VALUES (2, 451, 9437);
INSERT INTO `yzl_role_menu` VALUES (2, 453, 9438);
INSERT INTO `yzl_role_menu` VALUES (2, 454, 9439);
INSERT INTO `yzl_role_menu` VALUES (2, 455, 9440);
INSERT INTO `yzl_role_menu` VALUES (2, 456, 9441);
INSERT INTO `yzl_role_menu` VALUES (2, 457, 9442);
INSERT INTO `yzl_role_menu` VALUES (2, 458, 9443);
INSERT INTO `yzl_role_menu` VALUES (2, 459, 9444);
INSERT INTO `yzl_role_menu` VALUES (2, 460, 9445);
INSERT INTO `yzl_role_menu` VALUES (2, 461, 9446);
INSERT INTO `yzl_role_menu` VALUES (2, 462, 9447);
INSERT INTO `yzl_role_menu` VALUES (2, 463, 9448);
INSERT INTO `yzl_role_menu` VALUES (2, 464, 9449);
INSERT INTO `yzl_role_menu` VALUES (2, 452, 9450);
INSERT INTO `yzl_role_menu` VALUES (2, 465, 9451);
INSERT INTO `yzl_role_menu` VALUES (2, 466, 9452);
INSERT INTO `yzl_role_menu` VALUES (2, 467, 9453);
INSERT INTO `yzl_role_menu` VALUES (2, 468, 9454);
INSERT INTO `yzl_role_menu` VALUES (2, 469, 9455);
INSERT INTO `yzl_role_menu` VALUES (2, 470, 9456);
INSERT INTO `yzl_role_menu` VALUES (2, 471, 9457);
INSERT INTO `yzl_role_menu` VALUES (2, 472, 9458);
INSERT INTO `yzl_role_menu` VALUES (2, 473, 9459);
INSERT INTO `yzl_role_menu` VALUES (2, 474, 9460);
INSERT INTO `yzl_role_menu` VALUES (2, 475, 9461);
INSERT INTO `yzl_role_menu` VALUES (2, 476, 9462);
INSERT INTO `yzl_role_menu` VALUES (2, 477, 9463);
INSERT INTO `yzl_role_menu` VALUES (2, 478, 9464);
INSERT INTO `yzl_role_menu` VALUES (2, 479, 9465);
INSERT INTO `yzl_role_menu` VALUES (2, 480, 9466);
INSERT INTO `yzl_role_menu` VALUES (2, 481, 9467);
INSERT INTO `yzl_role_menu` VALUES (2, 482, 9468);
INSERT INTO `yzl_role_menu` VALUES (2, 483, 9469);
INSERT INTO `yzl_role_menu` VALUES (2, 484, 9470);
INSERT INTO `yzl_role_menu` VALUES (2, 485, 9471);
INSERT INTO `yzl_role_menu` VALUES (2, 486, 9472);
INSERT INTO `yzl_role_menu` VALUES (2, 487, 9473);
INSERT INTO `yzl_role_menu` VALUES (2, 488, 9474);
INSERT INTO `yzl_role_menu` VALUES (2, 489, 9475);
INSERT INTO `yzl_role_menu` VALUES (2, 490, 9476);
INSERT INTO `yzl_role_menu` VALUES (2, 491, 9477);
INSERT INTO `yzl_role_menu` VALUES (2, 492, 9478);
INSERT INTO `yzl_role_menu` VALUES (2, 493, 9479);
INSERT INTO `yzl_role_menu` VALUES (2, 39, 9480);
INSERT INTO `yzl_role_menu` VALUES (2, 637, 9481);
INSERT INTO `yzl_role_menu` VALUES (2, 495, 9482);
INSERT INTO `yzl_role_menu` VALUES (2, 498, 9483);
INSERT INTO `yzl_role_menu` VALUES (2, 40, 9484);
INSERT INTO `yzl_role_menu` VALUES (2, 638, 9485);
INSERT INTO `yzl_role_menu` VALUES (2, 494, 9486);
INSERT INTO `yzl_role_menu` VALUES (2, 497, 9487);
INSERT INTO `yzl_role_menu` VALUES (2, 41, 9488);
INSERT INTO `yzl_role_menu` VALUES (2, 639, 9489);
INSERT INTO `yzl_role_menu` VALUES (2, 496, 9490);
INSERT INTO `yzl_role_menu` VALUES (2, 499, 9491);

-- ----------------------------
-- Table structure for yzl_task
-- ----------------------------
DROP TABLE IF EXISTS `yzl_task`;
CREATE TABLE `yzl_task`  (
  `tcode` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务编号id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务名称',
  `mark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标识',
  PRIMARY KEY (`tcode`) USING BTREE COMMENT '主键',
  INDEX `mark`(`mark`) USING BTREE COMMENT '任务类别编号'
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_task
-- ----------------------------
INSERT INTO `yzl_task` VALUES (20, '人工造林', '11');
INSERT INTO `yzl_task` VALUES (22, '迹地更新', '12');
INSERT INTO `yzl_task` VALUES (23, '低效林改造', '13');
INSERT INTO `yzl_task` VALUES (24, '补植补造', '14');
INSERT INTO `yzl_task` VALUES (25, '林下经济', '15');
INSERT INTO `yzl_task` VALUES (26, '封山育林', '16');
INSERT INTO `yzl_task` VALUES (45, '荒山荒地造林', '17');
INSERT INTO `yzl_task` VALUES (46, '荒山荒地人工造林', '18');
INSERT INTO `yzl_task` VALUES (47, '迹地人工更新造林', '19');
INSERT INTO `yzl_task` VALUES (48, '林下种植中药材', '20');
INSERT INTO `yzl_task` VALUES (49, '新造', '21');
INSERT INTO `yzl_task` VALUES (50, '低改', '22');
INSERT INTO `yzl_task` VALUES (51, '其它', '23');
INSERT INTO `yzl_task` VALUES (52, '第二批根据资金折算面积（110）', '24');
INSERT INTO `yzl_task` VALUES (53, '根据资金折算面积（350）', '25');
INSERT INTO `yzl_task` VALUES (54, '森林景观改造', '26');
INSERT INTO `yzl_task` VALUES (55, '森林质量提升', '27');
INSERT INTO `yzl_task` VALUES (56, '森林抚育补贴', '28');
INSERT INTO `yzl_task` VALUES (57, '特色经济林造林项目', '29');
INSERT INTO `yzl_task` VALUES (58, '油茶低改', '30');
INSERT INTO `yzl_task` VALUES (59, '树种结构调整', '31');
INSERT INTO `yzl_task` VALUES (63, '测试', '32');
INSERT INTO `yzl_task` VALUES (65, 'aaaa', '33');

-- ----------------------------
-- Table structure for yzl_task_epc
-- ----------------------------
DROP TABLE IF EXISTS `yzl_task_epc`;
CREATE TABLE `yzl_task_epc`  (
  `ecode` int(11) DEFAULT NULL,
  `tcode` int(11) DEFAULT NULL,
  INDEX `uniteKeyEcode`(`ecode`) USING BTREE,
  INDEX `uniteKeyTcode`(`tcode`) USING BTREE,
  CONSTRAINT `uniteKeyEcode` FOREIGN KEY (`ecode`) REFERENCES `yzl_epc` (`ecode`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `uniteKeyTcode` FOREIGN KEY (`tcode`) REFERENCES `yzl_task` (`tcode`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_task_epc
-- ----------------------------
INSERT INTO `yzl_task_epc` VALUES (1, 20);
INSERT INTO `yzl_task_epc` VALUES (1, 22);
INSERT INTO `yzl_task_epc` VALUES (1, 25);
INSERT INTO `yzl_task_epc` VALUES (32, 20);
INSERT INTO `yzl_task_epc` VALUES (32, 23);
INSERT INTO `yzl_task_epc` VALUES (14, 20);
INSERT INTO `yzl_task_epc` VALUES (14, 22);

-- ----------------------------
-- Table structure for yzl_task_progress_sheet
-- ----------------------------
DROP TABLE IF EXISTS `yzl_task_progress_sheet`;
CREATE TABLE `yzl_task_progress_sheet`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `taskProgress` float DEFAULT NULL COMMENT '任务进度',
  `createTime` timestamp(0) DEFAULT NULL COMMENT '创建时间/任务计划时间',
  `epcode` int(11) NOT NULL COMMENT '工程编号id',
  `tpcode` int(11) NOT NULL COMMENT '任务编号id',
  `dpcode` int(11) DEFAULT NULL COMMENT '地区id',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `updateTime` timestamp(0) DEFAULT NULL COMMENT '更新时间',
  `mark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '根据市名+‘-’+县名拼接成',
  `sendBack` int(11) DEFAULT NULL COMMENT '做保留字段',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_4`(`dpcode`) USING BTREE,
  INDEX `e11`(`epcode`) USING BTREE,
  INDEX `t11`(`tpcode`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 861 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务进度工作表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_task_progress_sheet
-- ----------------------------
INSERT INTO `yzl_task_progress_sheet` VALUES (859, 134, '2018-12-18 10:21:27', 1, 22, 69, 'mark', NULL, NULL, 'NNS-HX', NULL);
INSERT INTO `yzl_task_progress_sheet` VALUES (860, 10, '2018-12-18 10:21:24', 1, 20, 69, 'mark', NULL, NULL, 'NNS-HXfinish', NULL);

-- ----------------------------
-- Table structure for yzl_user
-- ----------------------------
DROP TABLE IF EXISTS `yzl_user`;
CREATE TABLE `yzl_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号/用户表',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录名称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `work` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作单位',
  `Email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `status` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态0禁用1正常',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建者id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `keywork` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关键字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_user
-- ----------------------------
INSERT INTO `yzl_user` VALUES (22, 'faker', '202cb962ac59075b964b07152d234b70', '未知', '1907402902@qq.com', '男', '李哥', '15578726364', '启用', NULL, NULL, 'Test', '654949843218947026h');
INSERT INTO `yzl_user` VALUES (32, 'tom', '202cb962ac59075b964b07152d234b70', '未知', 'xiaoazhu@qq.com', '男', 'user', '15844441111', '启用', NULL, NULL, '', '2312222211231121a');
INSERT INTO `yzl_user` VALUES (35, 'mark', '202cb962ac59075b964b07152d234b70', '某', 'xiaoazhu@qq.com', '男', 'Jack', '15665354654', '启用', NULL, NULL, '', '1254254214552111b');
INSERT INTO `yzl_user` VALUES (40, '兴宁区管理员', '202cb962ac59075b964b07152d234b70', '进度管理', 'gaogao@qq.com', '男', 'Toni', '15685653895', '启用', NULL, NULL, 'test', '213123123qweasd');
INSERT INTO `yzl_user` VALUES (43, 'm2', '202cb962ac59075b964b07152d234b70', '', 'xiaoazhu@qq.com', '男', '123', '13132771231', '启用', NULL, NULL, '', '2165165151651d51');
INSERT INTO `yzl_user` VALUES (44, '南宁市管理员', '202cb962ac59075b964b07152d234b70', '南宁市', '152666@qq.com', '男', '张某', '18277708541', '启用', NULL, NULL, '南宁市人员', '215641651651616c');
INSERT INTO `yzl_user` VALUES (45, '横县管理员', '202cb962ac59075b964b07152d234b70', '横县管理员', '111@qq.com', '男', '未知', '15614521451', '启用', NULL, NULL, '', '131516513032125d');
INSERT INTO `yzl_user` VALUES (46, '西乡塘区管理员', '202cb962ac59075b964b07152d234b70', '未知', 'xiaoazhu@qq.com', '男', 'xxx', '15844441111', '启用', NULL, '2019-01-17 16:26:26', '', '7894648411651656c');
INSERT INTO `yzl_user` VALUES (47, '清秀区管理员', '202cb962ac59075b964b07152d234b70', '', 'xiaoazhu@qq.com', '男', '', '15844441111', '启用', NULL, NULL, '', '16351684632132626d');
INSERT INTO `yzl_user` VALUES (48, '江南区管理员', '202cb962ac59075b964b07152d234b70', '', 'xiaoazhu@qq.com', '男', 'xxx', '15844441111', '启用', NULL, '2019-01-18 09:19:20', '', '216165161516526987o');
INSERT INTO `yzl_user` VALUES (49, '柳州市管理员', '202cb962ac59075b964b07152d234b70', '未知', 'sad@qq.com', '男', 'xx', '15844441111', '启用', NULL, '2019-02-12 11:35:13', '', '64651986516465658403l');
INSERT INTO `yzl_user` VALUES (50, '融安县管理员', '202cb962ac59075b964b07152d234b70', 'xxx', 'sad@qq.com', '男', 'xx', '15844441111', '启用', NULL, '2019-02-12 13:53:09', '', '321684651568461351123k');
INSERT INTO `yzl_user` VALUES (51, 'ky', '202cb962ac59075b964b07152d234b70', '暂无', '8675218@qq.com', '男', '咯x', '13420325491', '禁用', NULL, '2019-03-13 14:37:04', '123', '31654653216521513215313y');
INSERT INTO `yzl_user` VALUES (52, 'wangwu', 'c20ad4d76fe97759aa27a0c99bff6710', '213', '54111646@qq.com', '男', '12', '13552141511', '启用', NULL, '2019-03-13 14:38:23', '', '5465156141651561651m');
INSERT INTO `yzl_user` VALUES (53, 'cool', '698d51a19d8a121ce581499d7b701668', '暂无分配', '1234561@qq.com', '男', '咯次', '13521212121', '启用', NULL, '2019-03-13 15:37:19', '12123', '1552462639614565v');
INSERT INTO `yzl_user` VALUES (54, '青秀区管理员', '202cb962ac59075b964b07152d234b70', '未知', 'sad@qq.com', '男', 'xxx', '15844441111', '启用', NULL, '2019-03-17 17:25:16', '', '155165151651wadas51');
INSERT INTO `yzl_user` VALUES (55, '隆安县管理员', '202cb962ac59075b964b07152d234b70', '未知', 'sad@qq.com', '男', 'xxx', '15844441111', '启用', NULL, '2019-04-02 09:17:46', '', '1554167866943086M');
INSERT INTO `yzl_user` VALUES (56, '桂林市管理员', '202cb962ac59075b964b07152d234b70', '未知', 'sad@qq.com', '男', 'xxx', '15844441111', '启用', NULL, '2019-04-11 14:05:54', '', '1554962754012242R');
INSERT INTO `yzl_user` VALUES (57, '灵川县', '202cb962ac59075b964b07152d234b70', '未知', 'sad@qq.com', '男', 'xxx', '15844441111', '启用', NULL, '2019-04-11 15:01:52', '', '1554966112186150Z');
INSERT INTO `yzl_user` VALUES (58, '灵山县管理员', '698d51a19d8a121ce581499d7b701668', '', '99@qq.com', '男', '23', '18777762227', '启用', NULL, NULL, '', 'sabhjdgsaudvashdjahsjh12313');
INSERT INTO `yzl_user` VALUES (59, '钦州市管理员', '698d51a19d8a121ce581499d7b701668', '', '3433@qq.com', '男', '', '18777762227', '启用', NULL, '2019-04-12 11:01:43', '', '1555038103121574T');
INSERT INTO `yzl_user` VALUES (60, 'xxx', '202cb962ac59075b964b07152d234b70', '未知', 'sad@qq.com', '男', 'xxx', '15844441111', '启用', NULL, NULL, '', NULL);
INSERT INTO `yzl_user` VALUES (61, '浦北县', '202cb962ac59075b964b07152d234b70', '未知', 'sad@qq.com', '男', 'xxx', '15844441111', '启用', NULL, '2019-04-12 14:05:07', '', '1555049107973007G');

-- ----------------------------
-- Table structure for yzl_user_role
-- ----------------------------
DROP TABLE IF EXISTS `yzl_user_role`;
CREATE TABLE `yzl_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) DEFAULT NULL COMMENT '用户编号/用户角色关系表',
  `role_id` int(32) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 196 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_user_role
-- ----------------------------
INSERT INTO `yzl_user_role` VALUES (162, 43, 1);
INSERT INTO `yzl_user_role` VALUES (173, 35, 2);
INSERT INTO `yzl_user_role` VALUES (177, 44, 32);
INSERT INTO `yzl_user_role` VALUES (178, 45, 34);
INSERT INTO `yzl_user_role` VALUES (179, 40, 35);
INSERT INTO `yzl_user_role` VALUES (180, 46, 44);
INSERT INTO `yzl_user_role` VALUES (181, 47, 45);
INSERT INTO `yzl_user_role` VALUES (182, 48, 46);
INSERT INTO `yzl_user_role` VALUES (183, 49, 48);
INSERT INTO `yzl_user_role` VALUES (184, 50, 49);
INSERT INTO `yzl_user_role` VALUES (185, 54, 45);
INSERT INTO `yzl_user_role` VALUES (186, 55, 47);
INSERT INTO `yzl_user_role` VALUES (187, 56, 50);
INSERT INTO `yzl_user_role` VALUES (188, 57, 51);
INSERT INTO `yzl_user_role` VALUES (189, 59, 53);
INSERT INTO `yzl_user_role` VALUES (192, 58, 55);
INSERT INTO `yzl_user_role` VALUES (194, 61, 57);
INSERT INTO `yzl_user_role` VALUES (195, 60, 58);

-- ----------------------------
-- Table structure for yzl_xb
-- ----------------------------
DROP TABLE IF EXISTS `yzl_xb`;
CREATE TABLE `yzl_xb`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `BSM` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标识码',
  `CITY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市',
  `COUNTY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '县',
  `TOWN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '乡',
  `VILLAGE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '村',
  `LIN_BAN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '林班',
  `JYING_BAN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '经营班',
  `XIAO_BAN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '小班',
  `ZY_XB` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作业小班',
  `YDH` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '样地号',
  `TFH` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图幅号',
  `GCLB` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工程类别',
  `ZLLB` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '造林类别',
  `JHND` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '计划年度',
  `ZYND` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作业年度',
  `ZLQ_DILEI` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '造林（封育）前地类',
  `DILEI` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地类',
  `FYLX` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '封育类型',
  `FYFS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '封育方式',
  `LDQS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '林地权属',
  `LMQS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '林木权属',
  `LZ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '林种',
  `SZ1` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '树种1',
  `SZ2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '树种2',
  `BILI` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '比例',
  `ZBLX` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '植被类型',
  `PO_DU` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '坡度',
  `XTJSBMJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '县统计上报面积',
  `CHL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '成活率',
  `CHL_DJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '成活率等级',
  `YBD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '郁闭度（灌木林覆盖度）',
  `HSMJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '核实面积',
  `BHSMJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '不核实面积',
  `HGMJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '合格面积',
  `CLMJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '成林面积',
  `DBZMJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '待补植面积',
  `BHGMJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '失败（不合格）面积',
  `SSMJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '损失面积',
  `BHSYY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '不核实原因',
  `WCLYY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '未成林原因',
  `DBZYY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '待补植原因',
  `BHGYY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '不合格（失败）原因',
  `SSYY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '损失原因',
  `SFSJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否有作业设计',
  `SFSP` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作业设计是否审批',
  `SJDWZZ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计单位资质',
  `SFASJSS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否按设计施工',
  `SFYDA` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否有档案',
  `SFFY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否抚育',
  `SFGH` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否管护',
  `SFZJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否开展县级自检',
  `SFYHT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否签订合同',
  `SFYLQZ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否发放林权证',
  `YLFS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '育林方式',
  `SJYLMJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计育林面积',
  `HSYLMJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '核实育林面积',
  `ZLZTLX` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '造林主体类型',
  `BZ1` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注1',
  `BZ2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注2',
  `BZ3` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注3',
  `HCRY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '核查人员',
  `HCRQ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '核查日期',
  `PHOTO` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '核查照片',
  `stat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态',
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`, `CITY`, `COUNTY`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzl_xb
-- ----------------------------
INSERT INTO `yzl_xb` VALUES (75, NULL, '4507', '450721', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '11', '2019', '2019', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', NULL, NULL, NULL, NULL, NULL, '55', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL);
INSERT INTO `yzl_xb` VALUES (76, NULL, '4507', '450721', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '11', '2019', '2019', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '200', NULL, NULL, NULL, NULL, NULL, '55', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL);
INSERT INTO `yzl_xb` VALUES (77, NULL, '4507', '450721', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '13', '2019', '2019', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '300', NULL, NULL, NULL, NULL, NULL, '55', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL);
INSERT INTO `yzl_xb` VALUES (78, NULL, '4501', '450123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '11', '2019', '2019', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '300', NULL, NULL, NULL, NULL, NULL, '55', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL);
INSERT INTO `yzl_xb` VALUES (79, NULL, '4501', '450123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '11', '2019', '2019', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '50', NULL, NULL, NULL, NULL, NULL, '55', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL);

-- ----------------------------
-- Table structure for yzlxb
-- ----------------------------
DROP TABLE IF EXISTS `yzlxb`;
CREATE TABLE `yzlxb`  (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `BSM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CITY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '市',
  `COUNTY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '县',
  `TOWN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '乡镇',
  `VILLAGE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '村',
  `LIN_BAN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '林班',
  `JYING_BAN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `XIAO_BAN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ZY_XB` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TFH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `GCLB` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ZLLB` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `JHND` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ZYND` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ZLQ_DILEI` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DILEI` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `FYLX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `FYFS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LDQS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LMQS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LZ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SZ1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SZ2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BILI` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ZBLX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PO_DU` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `XTJSBMJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CHL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CHL_DJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `YBD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `HSMJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BHSMJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `HGMJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '合格面积',
  `CLMJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BHGMJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DBZMJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SSMJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BHSYY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `WCLYY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BHGYY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DBZYY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SSYY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SFSJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SFSP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SJDWZZ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SFASJSS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SFYDA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SFFY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SFGH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SFZJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SFYHT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SFYLQZ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `YLFS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SJYLMJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `HSYLMJ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ZLZTLX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BZ1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BZ2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BZ3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `HCRY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `HCRQ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PHOTO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `GEOMETRY` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `CREATED_TIME` datetime(0) DEFAULT NULL,
  `UPDATED_TIME` datetime(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 186 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yzlxb
-- ----------------------------
INSERT INTO `yzlxb` VALUES (95, '53DA1C9F5F5D493C87F8FDF3A61024E5', '4501', '450122', '', '', '124', '0', '1123', '0', '', '1', '11', '2016', '2108', '', '', '', '', '', '', '', '', '', '', '', '0', '6.0', '0', '', '0.0', '0.0', '0.0', ' 12', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '', '', '', '', ' ', ' ', ' ', '{\"rings\":[[[108.96293302476357,23.221632839484982],[108.97521824192307,23.246203273804156],[109.00439563267685,23.247738925949136],[109.01053824125665,23.247738925949136],[109.02896606699596,23.240060665224291],[109.02896606699596,23.236989360934501],[109.03817997986562,23.206276318035691],[109.03817997986562,23.186312840151572],[109.03817997986562,23.163278057977436],[109.03817997986562,23.146385884383108],[109.07349997919925,23.078817190005736],[109.01821650198133,23.0557824078316],[109.03357302343079,23.1064589286147],[109.00439563267685,23.118744145774144],[108.99671737195217,23.13870762365832],[108.9675399811984,23.175563275136938],[108.9506478076039,23.186312840151572],[108.96293302476357,23.221632839484982]]],\"spatialReference\":{\"wkid\":4610,\"latestWkid\":4610}}', '2019-01-02 23:44:11', '2019-06-27 13:14:07');
INSERT INTO `yzlxb` VALUES (96, '8E18DFE0627A46299394167A6FBF0B9D', '4501', '450122', ' ', ' ', '124', '0', '1125', '0', ' ', '3', '14', '2017', '2017', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '0', '6.2', '0', ' ', '0.0', '0.0', '0.0', ' 16', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '{\"rings\":[[[108.7233712901529,22.796257195336977],[108.74487042018222,22.846933716119906],[108.77558346308103,22.826970238235731],[108.80783215812465,22.830041542525578],[108.81704607099431,22.817756325366076],[108.85697302676272,22.810078064641459],[108.85697302676272,22.785507630322286],[108.87386520035705,22.750187630988705],[108.86925824392222,22.710260675220241],[108.85083041818291,22.679547632321487],[108.82472433171893,22.651905893712694],[108.78940433238535,22.658048502292502],[108.78479737595052,22.67801198017662],[108.76483389806634,22.67801198017662],[108.72797824658772,22.661119806582349],[108.6895869429643,22.64269198084304],[108.65119563934087,22.638085024408213],[108.62662520502192,22.647298937277867],[108.61433998786242,22.658048502292502],[108.60666172713758,22.681083284466467],[108.59591216212311,22.733295457394377],[108.59591216212311,22.75786589171355],[108.61280433571744,22.776293717452631],[108.65273129148585,22.777829369597669],[108.68037303009464,22.764008500293187],[108.70647911655863,22.75786589171355],[108.71108607299345,22.780900673887459],[108.70340781226878,22.785507630322286],[108.7233712901529,22.796257195336977]]],\"spatialReference\":{\"wkid\":4610,\"latestWkid\":4610}}', '2019-01-02 23:44:11', '2019-06-27 13:14:10');
INSERT INTO `yzlxb` VALUES (174, 'Y97A7FB1F5824E509D1C929B12BB5050', '4501', '450127', '450127021', '450127021010', '123', '0', '1234', '0', '', '1', '12', '2016', '2018', '', '', '', '', '', '', '', '', '', '', '', '0', '5.0', '0', '', '0.0', '0.0', '0.0', '88', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ' ', '{\"rings\":[[[108.56366346707932,23.290737186007391],[108.58055564067365,23.293808490297238],[108.6097330314276,23.304558055311702],[108.6220182485871,23.321450228906201],[108.65273129148585,23.316843272471374],[108.66655216079033,23.289201533862411],[108.66808781293514,23.270773708123102],[108.66808781293514,23.25541718667381],[108.6619452043555,23.246203273804156],[108.63276781360156,23.22623979591981],[108.61433998786242,23.233918056644654],[108.6097330314276,23.250810230238983],[108.56366346707932,23.290737186007391]]],\"spatialReference\":{\"wkid\":4610,\"latestWkid\":4610}}', '2019-01-06 17:45:59', '2019-07-07 22:19:20');
INSERT INTO `yzlxb` VALUES (182, 'YB957598E0194B3FA6B990B40CA2B6D9', '', '450721', '', '', '', '', '450100123', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL, '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', '', NULL, NULL, NULL, '{\"spatialReference\":{\"latestWkid\":3857,\"wkid\":102100},\"rings\":[[[12123727.444616621,2605576.5335374875],[12125561.933295464,2608863.3257537456],[12125638.37032375,2608863.3257537456],[12128848.725511722,2608328.2665557503],[12128848.725511722,2606875.963018334],[12128848.725511722,2606799.525990049],[12128160.792257156,2605347.2224526326]]]}', '2019-06-27 00:04:10', '2019-07-08 14:33:41');
INSERT INTO `yzlxb` VALUES (183, 'YB957598E0194B3FA6B990B40CA2B6D9', '', '450721', '', '', '', '', '450101124', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL, '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', '', NULL, NULL, NULL, '{\"spatialReference\":{\"latestWkid\":3857,\"wkid\":102100},\"rings\":[[[12123775.217759298,2608414.2582125715],[12125122.420382822,2607697.6610723985],[12125590.597181069,2607831.4258718975],[12126354.967463918,2609063.9729529945],[12125495.050895711,2609360.166437599]]]}', '2019-06-27 00:04:31', '2019-07-08 14:33:44');
INSERT INTO `yzlxb` VALUES (184, 'YB957598E0194B3FA6B990B40CA2B6D9', '', '450721', '', '', '', '', '450101126', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL, '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', '', NULL, NULL, NULL, '{\"spatialReference\":{\"latestWkid\":3857,\"wkid\":102100},\"rings\":[[[12122657.326220628,2606742.1982188355],[12123096.839133268,2607009.727817833],[12123106.393761802,2607009.727817833],[12123584.125188585,2606235.8029064466],[12123584.125188585,2606216.6936493753],[12123459.915017622,2605337.667824097],[12123459.915017622,2605318.5585670257],[12123450.360389085,2605318.5585670257],[12123431.251132015,2605318.5585670257],[12123421.696503479,2605318.5585670257],[12123393.032617873,2605318.5585670257],[12122580.889192343,2605777.1807367364]],[[12124558.69729922,2606856.8537612627],[12125695.69809496,2606981.063932226],[12125705.252723496,2606981.063932226],[12125619.261066675,2605920.500164771],[12124969.546326252,2605920.500164771],[12124759.344498469,2606312.2399347317]],[[12124128.739015115,2605442.768737989],[12124577.806556292,2605633.861308702],[12124587.361184826,2605633.861308702],[12124902.663926503,2605022.3650824213],[12124902.663926503,2605012.8104538857],[12123889.873301726,2604716.616969281],[12123870.764044654,2604716.616969281],[12123861.209416118,2604716.616969281],[12123851.654787583,2604716.616969281],[12123651.007588334,2604936.3734256006]]]}', '2019-06-27 00:06:46', '2019-07-08 14:33:47');
INSERT INTO `yzlxb` VALUES (185, 'Y8D30BA132324E06A288C11AD0EDDB4E', '', '450122', '', '', '', '', '', '', '', '1', '11', '', '', '130', '', '1', '', '', '', '', '', '', '', '', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', '', NULL, NULL, NULL, '{\"spatialReference\":{\"latestWkid\":3857,\"wkid\":102100},\"rings\":[[[12045811.165177695,2600225.279115645],[12048027.838997964,2602862.3565914803],[12048830.427794958,2600378.1531722154],[12048066.057512106,2598849.412606514],[12048027.838997964,2598811.1940923715],[12047989.620483821,2598772.975578229],[12047951.401969679,2598772.975578229],[12047913.183455536,2598772.975578229],[12047874.964941394,2598772.975578229],[12047836.746427251,2598772.975578229],[12047798.527913108,2598772.975578229],[12046804.846545402,2599116.9422055115]]]}', '2019-06-27 13:27:13', '2019-06-27 13:28:14');

SET FOREIGN_KEY_CHECKS = 1;
