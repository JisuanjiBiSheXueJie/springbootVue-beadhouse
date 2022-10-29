/*
 Navicat Premium Data Transfer

 Source Server         : localMysql8
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : nursing-home

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 29/10/2022 11:07:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for n_bx
-- ----------------------------
DROP TABLE IF EXISTS `n_bx`;
CREATE TABLE `n_bx` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `user_id` int DEFAULT NULL COMMENT 'm_user表的主键',
  `bx_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '报修标题',
  `bx_addresses` varbinary(255) DEFAULT NULL COMMENT '报修宿舍',
  `bx_body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '报修说明主体',
  `error_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '打回原因',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '状态',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
  `modified_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='报修信息表';

-- ----------------------------
-- Records of n_bx
-- ----------------------------
BEGIN;
INSERT INTO `n_bx` (`id`, `user_id`, `bx_title`, `bx_addresses`, `bx_body`, `error_reason`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (12, 1, '灯泡', 0x33263226323032, '不亮了', '重复', '4', '赵赵', '2022-05-14 11:06:32', '赵赵', '2022-05-14 11:07:16.79');
INSERT INTO `n_bx` (`id`, `user_id`, `bx_title`, `bx_addresses`, `bx_body`, `error_reason`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (13, 3, 'hhhhh', 0x31263126313032, '不知道咋坏了', NULL, '3', '管理员', '2022-10-29 10:12:18', '管理员', '2022-10-29 10:12:53.913');
INSERT INTO `n_bx` (`id`, `user_id`, `bx_title`, `bx_addresses`, `bx_body`, `error_reason`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (14, 3, '没有垃圾桶了给我一个垃圾桶吧', 0x31263126313032, '没有垃圾桶了给我一个垃圾桶吧哈哈哈哈哈哈哈哈哈或或或或或或', NULL, '3', '管理员', '2022-10-29 10:29:30', '管理员', '2022-10-29 10:30:11.312');
INSERT INTO `n_bx` (`id`, `user_id`, `bx_title`, `bx_addresses`, `bx_body`, `error_reason`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (15, 32, 'qqq', 0x31263126313032, 'qqqqqqqqq', NULL, '1', '胡英俊', '2022-10-29 11:07:07', NULL, '2022-10-29 11:07:07.041');
COMMIT;

-- ----------------------------
-- Table structure for n_config
-- ----------------------------
DROP TABLE IF EXISTS `n_config`;
CREATE TABLE `n_config` (
  `id` int NOT NULL,
  `now_remain_num` int DEFAULT NULL COMMENT '当前照顾人数',
  `max_remain_num` int DEFAULT NULL COMMENT '最大照顾人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='配置信息表';

-- ----------------------------
-- Records of n_config
-- ----------------------------
BEGIN;
INSERT INTO `n_config` (`id`, `now_remain_num`, `max_remain_num`) VALUES (1, NULL, 3);
COMMIT;

-- ----------------------------
-- Table structure for n_content
-- ----------------------------
DROP TABLE IF EXISTS `n_content`;
CREATE TABLE `n_content` (
  `id` bigint NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容简述',
  `body` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `hot_num` int DEFAULT '0' COMMENT '主标题1表示展示为大标题，2为小标题如二级标题',
  `status` int DEFAULT '1' COMMENT '1,正常；0：已删除',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of n_content
-- ----------------------------
BEGIN;
INSERT INTO `n_content` (`id`, `title`, `content`, `body`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (1, '高效的护理', '金色怡养连锁是以医养结合为中心理念的养老、护理机构，拥有十年从业经验积累，并通过连锁运营，建立行政、后勤一体化管理的完整的管理体系。', '      上海金色怡养养老服务有限公司旗下有宝山区金色晚年敬老院、护理院十多年积累的养老、医护从业经验为基础，以“医养结合”为中心理念，建立了3家护理院、3家养老院、一家在建、2000余张养老、护理床位。', 1, 1, '数据库维护', '2022-04-17 11:25:27', NULL, NULL);
INSERT INTO `n_content` (`id`, `title`, `content`, `body`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (2, '我国决定设立科技创新和普惠养老两项专项再贷款', '4月6日召开的国务院常务会议指出，支持重点领域和薄弱环节融资，设立科技创新和普惠养老两项专项再贷款，人民银行对贷款本金分别提供60%、100%的再贷款支持。', '4月6日召开的国务院常务会议指出，支持重点领域和薄弱环节融资，设立科技创新和普惠养老两项专项再贷款，人民银行对贷款本金分别提供60%、100%的再贷款支持。\r\n\r\n会议指出，要适时灵活运用再贷款等多种货币政策工具，更好发挥总量和结构双重功能，加大对实体经济的支持。\r\n\r\n复旦大学金融研究院兼职研究员董希淼表示，作为一种结构性货币政策工具，再贷款一是有助于降低金融机构资金成本，使金融机构以较低利率向实体经济提供信贷支持；二是有助于引导金融机构定向支持相关领域，例如支农支小再贷款较好地引导信贷资金流向小微企业和“三农”领域。\r\n\r\n董希淼认为，此次设立科技创新和普惠养老两项专项再贷款，将鼓励和引导金融机构加大对科技创新和普惠养老领域的定向支持，提高货币政策的精准性和直达性，助力提升我国科技自主创新能力和养老服务供给能力。\r\n\r\n此外，会议还指出，加大稳健的货币政策实施力度，保持流动性合理充裕。增加支农支小再贷款，用好普惠小微贷款支持工具，用市场化、法治化办法促进金融机构向实体经济合理让利，推动中小微企业融资增量、扩面、降价。研究采取金融支持消费和有效投资的举措，提升对新市民的金融服务水平，优化保障性住房金融服务，保障重点项目建设融资需求，推动制造业中长期贷款较快增长。做好用政府专项债补充中小银行资本等工作，增强银行信贷能力。', 0, 1, '数据库维护', '2022-04-17 11:25:34', NULL, NULL);
INSERT INTO `n_content` (`id`, `title`, `content`, `body`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (3, '“沈”情相伴！泰康之家·沈园试运营正式启动！', '泰康之家·沈园是世界500强泰康保险集团在东北地区布局的首个大规模、全功能、国际标准的高品质养老社区。目前，泰康之家养老社区已实现全国24城布局，服务居民超5800人。雄厚的企业实力和积累多年的服务口碑，使得沈园首期开放的房源一经推出预订超七成，目前已入住约50户，获得长辈们的青睐。', '泰康之家·沈园是世界500强泰康保险集团在东北地区布局的首个大规模、全功能、国际标准的高品质养老社区。目前，泰康之家养老社区已实现全国24城布局，服务居民超5800人。雄厚的企业实力和积累多年的服务口碑，使得沈园首期开放的房源一经推出预订超七成，目前已入住约50户，获得长辈们的青睐。\r\n\r\n辽宁省政协教科卫体委员会主任蔺晓刚、原沈阳市副市长孙祥剑、沈阳市民政局党组书记徐阳、辽宁省医疗保障事业服务中心主任刘德会、农工党辽宁省委员会副主任委员朱航、原沈阳市铁西区区委书记侯晓峰、沈阳市人大教科文卫委副主任委员韩春丽、沈阳高新区管委会副主任苏虹、原沈阳市旅游局局长柳秀芝等政府领导，以及北京大学社会学系教授、博导陆杰华、辽宁省老年服务协会名誉会长、管理学教授李春方、全国老龄健康专家库医养结合首席专家徐卫华教授、东北大学、沈阳市第十一人民医院等院校、机构、协会、商会领导莅临活动。泰康保险集团管委会成员、泰康健投高级副总裁兼泰康之家首席执行官邱建伟出席活动并发言。泰康人寿辽宁分公司总经理孙超、泰康养老辽宁分公司总经理栗志瑊、泰康拜博口腔吉林事业部总经理郑亚璐以及泰康在辽各机构相关负责人出席活动。共话长寿时代下医养融合新体验，共同见证泰康之家·沈园试运营的启动，为辽宁乃至东北地区长辈提供享老生活新样本。\r\n\r\n活动现场，沈阳市高新区管委会副主任苏虹发表致辞，她说：“希望泰康之家·沈园成为东三省首个大规模、全功能、国际标准的高品质医养社区，成为沈阳市一张新的靓丽城市名片！期待社会各界共同努力，为沈阳的长辈建设一个美丽和谐的安居家园！”\r\n\r\n辽宁省老龄产业协会会长王凤先发表致辞并表示，“泰康之家·沈园今天的落地及运营是辽宁养老产业中一道美丽的风景，未来可期。辽宁省老龄产业协会将与沈园携手共进，通过社会各界的共同努力，让全省更多老年人在沈园拥有一个美好的享老新生活。”\r\n\r\n泰康人寿辽宁分公司总经理孙超发表致辞并表示，作为泰康在东北“医养康宁”的重要一环，泰康之家·沈园的试运营标志着泰康大健康产业在东北地区进入崭新的发展阶段，也预示着客户在未来可以真正实现“三亚看海，阳澄观湖，龙坞望山，盛京赏雪”的美好老年生活！\r\n\r\n随着泰康之家·沈园试运营的启动，长寿时代下的泰康方案将进一步为辽宁乃至东北地区提供更多医养实体供给，推进区域大健康产业发展。此外，口腔专科连锁——泰康拜博口腔服务网络已覆盖沈阳、大连和长春，将与沈园协同发力，满足日益增长的多样化、多层次医养需求。\r\n\r\n\r\n目前，泰康之家养老社区已布局京津冀、环渤海、长三角、粤港澳大湾区、西南、华中等核心区域的24个重点城市，规划总地上建筑面积约367万平方米，可容纳约6.6万名老人，其中北京燕园、上海申园、上海锦绣府、广州粤园、武汉楚园、苏州吴园、成都蜀园、杭州大清谷、南昌赣园8地9家医养社区已投入运营。接下来，泰康之家·鹭园（厦门）、湘园（长沙）也将陆续绽放，为全国更多区域的长者带来高品质的医养服务。', 0, 1, '数据库维护', '2022-04-17 11:25:37', NULL, NULL);
INSERT INTO `n_content` (`id`, `title`, `content`, `body`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (4, '北京：关于发布北京市养老服务机构服务质量星级名单的公告', '为贯彻落实民政部《关于加快建立全国统一养老机构等级评定体系的指导意见》和《养老机构等级划分与评定》要求，北京市制定了地方标准《养老机构服务质量星级划分与评定》，在做好与民政部养老机构等级评定有效衔接的基础上，开展养老服务机构服务质量星级评定工作。截至2020年12月31日，北京市共有星级养老机构430家，其中五星级9家，四星级35家，三星级46家，二星级283家，一星级57家；截至2020年12月31日，北京市共有星级社区养老服务驿站101家，其中三星级4家，二星级36家，一星级61家。现将北京市养老服务机构服务质量星级名单公', '告如下，欢迎社会各界监督。\r\n\r\n北京市养老机构服务质量星级名单\r\n\r\n五星级（9家）：\r\n\r\n1. 北京市第一社会福利院（2012年度）\r\n\r\n2. 北京市石景山区寿山福海养老服务中心（2012年度）\r\n\r\n3. 北京市海淀区四季青镇敬老院（2016年度）\r\n\r\n4. 乐成老年事业投资有限公司（双井恭和苑）（2016年度）\r\n\r\n5. 北京市海淀区和熹会老年公寓（2019年度）\r\n\r\n6. 北京一福寿山福海养老服务中心（2019年度）\r\n\r\n7. 北京市大兴区寿山福海养老服务中心（2019年度）\r\n\r\n8. 北京市朝阳区恭和老年公寓（2019年度）\r\n\r\n9. 乐成老年事业投资有限公司双桥分公司（2020年度）\r\n\r\n四星级（35家）：', 0, 1, '数据库维护', '2022-04-17 11:25:39', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for n_drug
-- ----------------------------
DROP TABLE IF EXISTS `n_drug`;
CREATE TABLE `n_drug` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '药物编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '药物名称',
  `dose` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '剂量说明',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作用',
  `warning` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '警告信息',
  `status` int DEFAULT '1' COMMENT '1,正常；0：已删除',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='药品表';

-- ----------------------------
-- Records of n_drug
-- ----------------------------
BEGIN;
INSERT INTO `n_drug` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (1, '100001', '利尿剂　', '4剂/次', '适用于轻、中度高血压，敏感性高血压，合并肥胖或糖尿病、更年期女性和老年人高血压有较强降压效果。　', '利尿剂的主要不利作用是低血钾征和影响血脂、血糖、血尿酸代谢，往往发生在大剂量时，因此推荐使用小剂量，不良反应主要是乏力、尿量增多。痛风患者禁用，肾功能不全者禁用。', 1, '数据库维护', '2022-03-15 05:32:08', '管理员', '2022-10-29 10:25:50');
INSERT INTO `n_drug` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (2, '100002', 'β受体阻滞剂', '1', '适用于各种不同严重程度高血压，尤其是心律较快的中、青年患者或合并心绞痛患者，对老年人高血压疗效相对较差。　', '主要有心动过缓、乏力、四肢发冷。β受体阻滞剂对心肌收缩力、方式传导及窦性心律均有抑制作用，并可增加气道阻力。急性心力衰竭、支气管哮喘、病态窦房结综合症、房室传导阻滞和外周血管病患者禁用。', 0, '数据库维护', '2022-03-15 17:32:55', '管理员', '2022-05-14 00:31:46');
INSERT INTO `n_drug` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (3, '100002', '钙通道阻滞剂　', '2剂/次', '相对于其它降压药的优势是老年患者有较好的降压疗效，高钠摄入不影响降压疗效；在嗜酒的患者也有显著的降压作用；可用于合并糖尿病、冠心病或外周血管病患者；长期治疗还有抗动脉粥样硬化作用。', '开始治疗阶段有反射性交感神经活性增强，引起心率增快、面部潮红、头痛、下肢水肿，不宜在心力衰竭、窦房结功能低下或心脏传导阻滞者患者中应用。　', 1, '数据库维护', '2022-03-15 05:33:33', '管理员', '2022-05-14 00:36:32');
INSERT INTO `n_drug` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (4, '100003', '血管紧张素转换酶抑制剂（ACEI）', '5粒/次', 'ACEI抑制剂具有改善胰岛素抵抗和减少尿蛋白作用，在肥胖、糖尿病和心脏、肾脏靶器官受损的高血压患者具有相对较好的疗效，特别适用于伴有心力衰竭、心肌梗死后、糖耐量减退或糖尿病肾病的高血压患者。　', '刺激性干咳和血管性水肿。高钾血症、妊娠妇女和双侧肾动脉狭窄患者禁用。', 1, '数据库维护', '2022-03-15 05:34:43', '管理员', '2022-05-14 00:36:13');
INSERT INTO `n_drug` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (5, '524524', '1111111111111', '254542', '254', '245425', 0, '果果', '2022-05-07 07:57:45', '果果', '2022-05-07 20:13:35');
INSERT INTO `n_drug` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (6, '100004', '速效救心丸', '4-6粒/次', '行气活血，祛瘀止痛，能够增加冠脉血流量，缓解心绞痛，主要用于气滞血瘀型，冠心病及心绞痛。心绞痛急性发作时可以在舌下含服，能够起到缓解心绞痛的作用。它对血压影响不大，所以属于冠心病心绞痛的一类急救药物', '有速效救心丸过敏史的患者，禁止服用速效救心丸；寒凝血瘀、阴虚血瘀、胸痹、心脏疼痛者，不易单独使用速效救心丸；重度心力衰竭导致的心肌缺血者要慎用，可以在医生的指导下服用', 1, '管理员', '2022-05-14 00:34:24', '管理员', '2022-05-14 00:35:59');
INSERT INTO `n_drug` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (7, '100005', '感冒灵颗粒', '1袋/次', '用于感冒造成的头痛、发热、鼻塞、咽痛、流涕等等，主要的作用为解热镇痛', '严重肝肾功能不全者禁用。', 1, '管理员', '2022-05-14 00:35:33', NULL, '2022-05-14 00:35:33');
INSERT INTO `n_drug` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (8, '100006', '布洛芬缓释胶囊', '1粒/次', '主要是适用于活动性或者是风湿性类风湿性关节炎，主要就是解热镇痛的作用。也可以用于一些牙疼或者是躯体疼痛，也可用一些感冒发烧引起的发烧', '严重的肝、肾功能不全，以及心力衰竭的患者不能使用。 对解热镇痛类药物过敏的患者不能使用。 该药不能与其他非甾体抗炎药一起使用。', 1, '管理员', '2022-05-14 00:38:35', NULL, '2022-05-14 00:38:35');
COMMIT;

-- ----------------------------
-- Table structure for n_foods
-- ----------------------------
DROP TABLE IF EXISTS `n_foods`;
CREATE TABLE `n_foods` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '食品编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '食品名称',
  `dose` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '食品说明',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作用',
  `warning` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '警告信息',
  `status` int DEFAULT '1' COMMENT '1,正常；0：已删除',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='食品表';

-- ----------------------------
-- Records of n_foods
-- ----------------------------
BEGIN;
INSERT INTO `n_foods` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (1, '100001', '2022.5.14', '豆浆，油条，包子', '米饭套餐', '粥，馒头', 0, '数据库维护', '2022-03-16 10:41:17', '管理员', '2022-05-14 20:37:10');
INSERT INTO `n_foods` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (2, '100002', '五仁月饼', '打仗用的', '开炮', '牙口不好不能吃', 0, '数据库维护', '2022-03-16 10:42:03', '管理员', '2022-05-14 20:37:13');
INSERT INTO `n_foods` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (3, '100003', '法棍', '枪', '射击五仁月饼的', '优雅的吃法', 0, '数据库维护', '2022-03-16 10:42:43', '管理员', '2022-05-14 18:52:13');
INSERT INTO `n_foods` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (4, '1212', '22222', '121', '212', '121', 0, '果果', '2022-05-07 08:42:34', '果果', '2022-05-07 20:44:19');
INSERT INTO `n_foods` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (5, '100001', '2022.5.14', '油条，面包，豆浆，油条', '米饭套餐', '水果，面条', 1, '管理员', '2022-05-14 20:38:04', NULL, '2022-05-14 20:38:04');
INSERT INTO `n_foods` (`id`, `uuid`, `name`, `dose`, `role`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (6, '100002', '2022.5.15', '222', '222222', '22', 1, '管理员', '2022-05-14 20:38:57', NULL, '2022-05-14 20:38:57');
COMMIT;

-- ----------------------------
-- Table structure for n_healthpunch
-- ----------------------------
DROP TABLE IF EXISTS `n_healthpunch`;
CREATE TABLE `n_healthpunch` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `user_id` int DEFAULT NULL COMMENT 'm_user表的主键',
  `person_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户id',
  `temperature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '今日体温',
  `pulse` int DEFAULT NULL COMMENT '脉搏',
  `max_blood_pre` int DEFAULT NULL COMMENT '血压高压',
  `min_blood_pre` int DEFAULT NULL COMMENT '血压低压',
  `breathing_rate` int DEFAULT NULL COMMENT '呼吸频率',
  `adverse` int DEFAULT NULL COMMENT '是否需要医生上门',
  `instructions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '状况描述',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
  `modified_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='健康打卡表';

-- ----------------------------
-- Records of n_healthpunch
-- ----------------------------
BEGIN;
INSERT INTO `n_healthpunch` (`id`, `user_id`, `person_id`, `temperature`, `pulse`, `max_blood_pre`, `min_blood_pre`, `breathing_rate`, `adverse`, `instructions`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (29, NULL, '1', '36.6', 69, 120, 89, 50, 0, NULL, '1', '方磊', '2022-05-09 20:50:17', NULL, '2022-05-09 20:50:17.049');
INSERT INTO `n_healthpunch` (`id`, `user_id`, `person_id`, `temperature`, `pulse`, `max_blood_pre`, `min_blood_pre`, `breathing_rate`, `adverse`, `instructions`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (30, NULL, '1', '36.7', 78, 123, 67, 56, 0, NULL, '1', '方磊', '2022-05-10 21:52:29', NULL, '2022-05-10 21:52:28.519');
INSERT INTO `n_healthpunch` (`id`, `user_id`, `person_id`, `temperature`, `pulse`, `max_blood_pre`, `min_blood_pre`, `breathing_rate`, `adverse`, `instructions`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (31, NULL, '5', '36.6', 78, 123, 56, 105, 0, NULL, '1', '胡图图', '2022-05-14 21:17:55', NULL, '2022-05-14 21:17:54.659');
INSERT INTO `n_healthpunch` (`id`, `user_id`, `person_id`, `temperature`, `pulse`, `max_blood_pre`, `min_blood_pre`, `breathing_rate`, `adverse`, `instructions`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (32, NULL, '7', '36.8', 89, 133, 77, 55, 0, NULL, '1', '胡英俊', '2022-05-17 00:52:52', NULL, '2022-05-17 22:41:26.931');
INSERT INTO `n_healthpunch` (`id`, `user_id`, `person_id`, `temperature`, `pulse`, `max_blood_pre`, `min_blood_pre`, `breathing_rate`, `adverse`, `instructions`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (33, NULL, NULL, '36.9', 77, 111, 89, 77, 0, NULL, '1', '胡英俊', '2022-05-17 22:39:00', NULL, '2022-05-17 22:39:00.168');
INSERT INTO `n_healthpunch` (`id`, `user_id`, `person_id`, `temperature`, `pulse`, `max_blood_pre`, `min_blood_pre`, `breathing_rate`, `adverse`, `instructions`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (34, NULL, '7', '36.7', 78, 123, 55, 65, NULL, NULL, '1', '胡英俊', '2022-05-18 11:06:06', NULL, '2022-05-18 11:06:05.999');
INSERT INTO `n_healthpunch` (`id`, `user_id`, `person_id`, `temperature`, `pulse`, `max_blood_pre`, `min_blood_pre`, `breathing_rate`, `adverse`, `instructions`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (35, NULL, '8', '36.4', 77, 121, 56, 98, NULL, NULL, '1', '胡英俊', '2022-05-18 11:16:51', NULL, '2022-05-18 11:16:51.132');
INSERT INTO `n_healthpunch` (`id`, `user_id`, `person_id`, `temperature`, `pulse`, `max_blood_pre`, `min_blood_pre`, `breathing_rate`, `adverse`, `instructions`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (36, NULL, '7', '36.6', 78, 125, 56, 89, NULL, NULL, '1', '胡英俊', '2022-05-19 22:13:24', NULL, '2022-05-19 22:13:23.745');
INSERT INTO `n_healthpunch` (`id`, `user_id`, `person_id`, `temperature`, `pulse`, `max_blood_pre`, `min_blood_pre`, `breathing_rate`, `adverse`, `instructions`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (37, NULL, '7', '36.6', 78, 124, 77, 98, NULL, NULL, '1', '胡英俊', '2022-05-20 15:11:40', NULL, '2022-05-20 15:11:39.91');
INSERT INTO `n_healthpunch` (`id`, `user_id`, `person_id`, `temperature`, `pulse`, `max_blood_pre`, `min_blood_pre`, `breathing_rate`, `adverse`, `instructions`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (38, NULL, NULL, '37.0', 22, 2222, 22, 22, 1, NULL, '1', '管理员', '2022-10-29 10:29:03', NULL, '2022-10-29 10:29:03.218');
INSERT INTO `n_healthpunch` (`id`, `user_id`, `person_id`, `temperature`, `pulse`, `max_blood_pre`, `min_blood_pre`, `breathing_rate`, `adverse`, `instructions`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (39, NULL, '5', '38.0', 33, 55, 33, 33, 1, NULL, '1', '胡英俊', '2022-10-29 10:34:46', NULL, '2022-10-29 10:34:45.656');
COMMIT;

-- ----------------------------
-- Table structure for n_index_introduce
-- ----------------------------
DROP TABLE IF EXISTS `n_index_introduce`;
CREATE TABLE `n_index_introduce` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型',
  `body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '说明',
  `status` int DEFAULT '1' COMMENT '1,正常；0：已删除',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='主页分院说明';

-- ----------------------------
-- Records of n_index_introduce
-- ----------------------------
BEGIN;
INSERT INTO `n_index_introduce` (`id`, `name`, `type`, `body`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (8, '地址', 'zz', '<p>洛阳市洛龙区龙门大道813号(省工人龙门疗养院)<br/></p><p>九都西路龙鳞路交叉口向南1.7公里（150部队医院向北1公里）<br/></p><p>河南省洛阳市宜阳县灵山村1号（灵山寺向西200米）<br/></p><p>洛阳市九都路与太原路交叉口西北角六冶嘉苑一期商业楼二楼<br/></p><p>河南省洛阳市洛龙区洛河南岸牡丹桥东北1000米洛阳桥西南1300米（洛浦公园内）<br/></p><p>洛阳市洛龙大道813号:电话13613793109<br/></p><p>洛阳市西工区红山乡下沟村罗湾（华山路大桥西边）<br/></p><p>洛阳市定鼎北路上清宫森林公园<br/></p><p>洛阳市龙门镇龙门石窟旁边（龙门大道837号）<br/></p><p>偃师市高速引线与北环路交叉口西500米路南<br/></p><p>河南省洛阳市吉利区送庄村委对面<br/></p><p>河南省洛阳市瀍河回族区九都路与夹马营路交叉口向南100米大张超市南隔壁<br/></p><p>洛阳市洛龙区牡丹大道与张衡街交汇处亿和广场2楼<br/></p><p>涧西区武汉路206号<br/></p>', 1, '管理员', '2022-05-14 17:36:16', NULL, '2022-10-29 10:27:37');
INSERT INTO `n_index_introduce` (`id`, `name`, `type`, `body`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (9, '452425', 'zz', '<p>752452452</p>', -1, '管理员', '2022-05-14 18:24:39', NULL, '2022-05-14 20:49:20');
INSERT INTO `n_index_introduce` (`id`, `name`, `type`, `body`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (10, '地址', 'zz', '<p>郑州市建设西路与西四环交汇处向西200米路北<br/></p><p>河南省郑州市惠济区迎宾路与香山路交汇处向南50米路西<br/></p><p>二七区福华街街道新圃西街7号新圃西街社区养老服务中心3楼</p><p><span style=\"font-size: 14px;\">郑州市经开区航海东路与经开第二十大街交口航海东路1776号院内65/66幢</span></p><p>郑州市新郑市航空港区银港大道与如云路交汇处<span style=\"font-size: 14px;\"><br/></span></p><p>郑州市金水区经五路与红专路交叉口西100米<br/></p><p>郑州市上街区峡窝镇观沟新区孟津路与龙江路交叉口向西100米公交车站牌路北胡同内<br/></p><p>郑州市郑东新区陇海路与东周路向东150米路北<br/></p><p>高新区梧桐街与雪松路交叉口向南150米路西4楼养老中心<br/></p><p>河南省郑州新密市城关镇西瓦店社区6号楼<br/></p><p>郑州市经开区经开第五大街经北一路路东经开泰和卫生服务中心2号楼<br/></p><p>高新区电厂路79号五龙新城香馨园社区3号楼<br/></p><p>河南省郑州市农业路沙口路立交桥东北角郑州颐和老年公寓<br/></p><p>郑州市高新区化工路38号院济民中医院六楼<br/></p><p>郑州市郑东新区商务内环东四街奥美康复医院五楼（房管局隔壁）<br/></p>', 1, '管理员', '2022-05-14 18:31:41', NULL, '2022-05-14 21:00:53');
INSERT INTO `n_index_introduce` (`id`, `name`, `type`, `body`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (11, '地址', 'lh', '<p>漯河市召陵区中山路与渭河路交叉口向西100米<br/></p><p>郾城区嫩江路与王前路交叉口往南300米路东&nbsp;<br/></p><p>河南省漯河市淞江路与解放路交叉口向北500米路东<br/></p><p>漯河市郾城区黑龙潭乡生杨村<br/></p><p>漯河市郾城区孟庙镇文明路中段路南（太行山路与文明路交叉口）<br/></p><p>漯河市临颍县城关镇西五里头村<br/></p><p>漯河市丁湾菜市场南再100米路西<br/></p><p>文明路与井冈山路交叉口西路南150米路南康梦圆养老公寓<br/></p><p>河南省漯河市源汇区后谢乡解放路南段<br/></p><p>漯河市舞阳县辛安镇<br/></p><p>河南省郾城区大刘镇南王<br/></p><p>漯河市源汇区后谢乡清明李<br/></p><p>漯河市召陵区<br/></p><p>源汇区孙庄乡河滨<br/></p>', 1, '管理员', '2022-05-14 18:45:00', NULL, '2022-05-14 21:04:53');
INSERT INTO `n_index_introduce` (`id`, `name`, `type`, `body`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (12, '地址', 'jz', '<p>马村区白庄村翠苑北区<br/></p><p>焦作市解放区西城家园北门内50米<br/></p><p>解放区丰收路锦祥花园小区内社区办北邻<br/></p><p>人民路和文汇路交叉口北500米路西锦绣公馆东门<br/></p><p>艺新街道5号院社区东城美苑10号楼一楼<br/></p><p>焦作市马村区丽景社区中心广场处<br/></p><p>河南省焦作市山阳区住郭庄村蕾娜范老年公寓<br/></p><p>河南焦作人民路东段周庄中学北邻<br/></p><p>河南省焦作市山阳区中老年居乐部<br/></p><p>焦作市修武县方庄镇古汉村<br/></p><p>焦作市山阳区岗庄村<br/></p><p>河南省焦作市博爱县中山路东段体育馆东500米路北<br/></p><p>河南省孟州市长店社区老年公寓<br/></p><p>沁阳市建设北路与太行大道交叉口（原温泉宾馆院内）<br/></p><p>河南省博爱县孝敬镇<br/></p><p>焦作市山阳区焦东南路79号<br/></p><p>焦作市解放区果园路三巷6号<br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p>', 1, '管理员', '2022-05-14 21:01:24', NULL, '2022-05-14 21:08:41');
INSERT INTO `n_index_introduce` (`id`, `name`, `type`, `body`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (13, '地址', 'zk', '<p>河南省周口市川汇区太清路与腾飞路交叉口华耀城街道办事处锦华家园安置A区2号楼裙楼门面房<br/></p><p>河南省周口市淮阳县豆门乡楚庄<br/></p><p>项城是丁集路口东500米路南<br/></p><p>太康县淮海路88号<br/></p><p>周口市川汇区建设大道与汉阳路交叉口西北角宏德养老公寓<br/></p><p>沈丘县幸福老年公寓（总部）<br/></p><p>河南省西华县迟营乡<br/></p><p>项城市范集镇217省道东侧<br/></p><p>周口市太昊路东段民政福利院东<br/></p><p>周口市文昌大道幸福花开小区<br/></p><p>周口市川汇区建设路与中州路交叉口向北100米路西<br/></p><p>周口市川汇区太昊路与龙源路交叉口路北<br/></p><p>建设路与大闸路交叉口 向西800米路北<br/></p><p>周口市太昊路与五一路交叉口<br/></p>', 1, '管理员', '2022-05-14 21:01:38', NULL, '2022-05-14 21:11:38');
INSERT INTO `n_index_introduce` (`id`, `name`, `type`, `body`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (14, '1', 'ly', '<p>121</p>', -1, '胡英俊', '2022-05-17 01:02:25', NULL, '2022-05-17 01:03:27');
INSERT INTO `n_index_introduce` (`id`, `name`, `type`, `body`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (15, '位置', 'jz', '<p>11</p>', -1, '胡英俊', '2022-05-17 01:04:26', NULL, '2022-05-17 01:04:57');
COMMIT;

-- ----------------------------
-- Table structure for n_leave
-- ----------------------------
DROP TABLE IF EXISTS `n_leave`;
CREATE TABLE `n_leave` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `user_id` int DEFAULT NULL COMMENT 'm_user表的主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请假标题',
  `addresses` varbinary(255) DEFAULT NULL COMMENT '请假宿舍',
  `identity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '身份',
  `body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请假原因说明',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `error_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '驳回原因',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `bx_date` datetime DEFAULT NULL COMMENT '发生时间',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
  `modified_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='请假/外出信息表';

-- ----------------------------
-- Records of n_leave
-- ----------------------------
BEGIN;
INSERT INTO `n_leave` (`id`, `user_id`, `title`, `addresses`, `identity`, `body`, `start_time`, `end_time`, `error_reason`, `status`, `bx_date`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (1, 5, '不知道为什么就想请假', 0x31263226323032, '护工', '我就想请假', '2022-03-08 00:00:00', '2022-03-30 00:00:00', '哈哈哈哈', '1', NULL, '果果', '2022-03-12 15:35:43', '方磊', '2022-05-09 23:22:16.659');
INSERT INTO `n_leave` (`id`, `user_id`, `title`, `addresses`, `identity`, `body`, `start_time`, `end_time`, `error_reason`, `status`, `bx_date`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (2, 1, '我想回家回家回家', 0x31263226323032, '护工', '我想回家回家回家我想回家回家回家我想回家回家回家我想回家回家', '2022-03-09 00:00:00', '2022-03-30 00:00:00', '疫情', '0', NULL, '果果', '2022-03-12 15:36:48', '管理员', '2022-05-14 01:51:02.791');
INSERT INTO `n_leave` (`id`, `user_id`, `title`, `addresses`, `identity`, `body`, `start_time`, `end_time`, `error_reason`, `status`, `bx_date`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (3, 1, '121', 0x31263226323032, '护工', '122222222', '2022-05-03 00:00:00', '2022-04-26 00:00:00', NULL, '2', NULL, '果果', '2022-05-07 22:15:08', '管理员', '2022-05-14 18:58:51.024');
INSERT INTO `n_leave` (`id`, `user_id`, `title`, `addresses`, `identity`, `body`, `start_time`, `end_time`, `error_reason`, `status`, `bx_date`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (4, 2, '请假', 0x6E756C6C266E756C6C266E756C6C, '护工', '参加婚礼', '2022-05-14 00:00:00', '2022-05-15 00:00:00', NULL, '1', NULL, '赵赵', '2022-05-14 01:38:58', NULL, '2022-05-14 01:38:57.722');
INSERT INTO `n_leave` (`id`, `user_id`, `title`, `addresses`, `identity`, `body`, `start_time`, `end_time`, `error_reason`, `status`, `bx_date`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (5, 1, '请假', 0x31263326333032, '护工', '参加婚礼', '2022-05-14 00:00:00', '2022-05-15 00:00:00', NULL, '2', NULL, '赵赵', '2022-05-14 17:21:03', '管理员', '2022-05-14 18:51:19.469');
INSERT INTO `n_leave` (`id`, `user_id`, `title`, `addresses`, `identity`, `body`, `start_time`, `end_time`, `error_reason`, `status`, `bx_date`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (6, 1, '请假', 0x33263226323036, '客户', '参加婚礼', '2022-05-14 00:00:00', '2022-05-15 00:00:00', NULL, '2', NULL, '赵赵', '2022-05-14 17:21:18', '2452452452', '2022-05-14 18:30:28.757');
INSERT INTO `n_leave` (`id`, `user_id`, `title`, `addresses`, `identity`, `body`, `start_time`, `end_time`, `error_reason`, `status`, `bx_date`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (7, 31, '11', 0x32263426343034, '护工', '11', '2022-05-17 00:00:00', '2022-05-16 00:00:00', NULL, '2', NULL, '胡图', '2022-05-14 19:33:55', '管理员', '2022-05-14 19:34:04.013');
INSERT INTO `n_leave` (`id`, `user_id`, `title`, `addresses`, `identity`, `body`, `start_time`, `end_time`, `error_reason`, `status`, `bx_date`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (8, 31, '请假', 0x31263326333031, '护工', '参加婚礼', '2022-05-15 00:00:00', '2022-05-17 00:00:00', NULL, '1', NULL, '胡图图', '2022-05-14 21:22:33', NULL, '2022-05-14 21:22:32.819');
INSERT INTO `n_leave` (`id`, `user_id`, `title`, `addresses`, `identity`, `body`, `start_time`, `end_time`, `error_reason`, `status`, `bx_date`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (9, 3, '测试请假', 0x31263226323031, '客户', '测试', '2022-05-14 00:00:00', '2022-05-15 00:00:00', NULL, '2', NULL, '管理员', '2022-05-15 23:39:39', '管理员', '2022-05-15 23:52:15.252');
INSERT INTO `n_leave` (`id`, `user_id`, `title`, `addresses`, `identity`, `body`, `start_time`, `end_time`, `error_reason`, `status`, `bx_date`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (10, 3, 'hhhh', 0x31263226323031, '护工', NULL, '2022-10-29 00:00:00', '2022-10-30 00:00:00', NULL, '2', NULL, '管理员', '2022-10-29 10:28:24', '管理员', '2022-10-29 10:28:34.674');
COMMIT;

-- ----------------------------
-- Table structure for n_leave_message
-- ----------------------------
DROP TABLE IF EXISTS `n_leave_message`;
CREATE TABLE `n_leave_message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '留言人名称',
  `mess` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '记事',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `is_contact` int DEFAULT '0' COMMENT '是否联系',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '状态',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
  `modified_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='主页留言';

-- ----------------------------
-- Records of n_leave_message
-- ----------------------------
BEGIN;
INSERT INTO `n_leave_message` (`id`, `name`, `mess`, `phone`, `is_contact`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (1, '果果', '已联系', '17703795206', 1, '0', NULL, '2022-04-30 20:03:30', NULL, '2022-05-14 00:53:14.996');
INSERT INTO `n_leave_message` (`id`, `name`, `mess`, `phone`, `is_contact`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (2, 'guoguo', NULL, '1008611', 0, '0', '前端用户添加', '2022-04-30 20:05:00', NULL, '2022-05-14 00:52:48.771');
INSERT INTO `n_leave_message` (`id`, `name`, `mess`, `phone`, `is_contact`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (3, 'guoguo', NULL, '1008611', 0, '0', '前端用户添加', '2022-04-30 20:05:14', NULL, '2022-05-14 00:52:48.757');
INSERT INTO `n_leave_message` (`id`, `name`, `mess`, `phone`, `is_contact`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (4, '郭帆', '452452452', '17703795206', 0, '0', 'guest', '2022-05-04 18:06:04', NULL, '2022-05-14 00:52:58.692');
INSERT INTO `n_leave_message` (`id`, `name`, `mess`, `phone`, `is_contact`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (5, '875', '已联系', '587', 0, '0', 'guest', '2022-05-09 21:05:44', NULL, '2022-05-14 00:53:14.981');
INSERT INTO `n_leave_message` (`id`, `name`, `mess`, `phone`, `is_contact`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (6, '赵赵', '已联系', '110110110', 0, '2', 'guest', '2022-05-14 00:51:54', NULL, '2022-05-14 00:54:01.001');
INSERT INTO `n_leave_message` (`id`, `name`, `mess`, `phone`, `is_contact`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (7, '胡英俊', '已联系', '1762887332', 0, '2', 'guest', '2022-05-15 01:15:50', NULL, '2022-05-15 01:17:08.743');
INSERT INTO `n_leave_message` (`id`, `name`, `mess`, `phone`, `is_contact`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (8, '李四', '已联系', '131111111111', 0, '2', 'guest', '2022-05-16 22:37:00', NULL, '2022-10-29 10:26:49.52');
INSERT INTO `n_leave_message` (`id`, `name`, `mess`, `phone`, `is_contact`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (9, '2122', '已联系', '1213243432423', 0, '2', 'guest', '2022-05-16 22:56:40', NULL, '2022-10-29 10:26:50.231');
INSERT INTO `n_leave_message` (`id`, `name`, `mess`, `phone`, `is_contact`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (10, '阿拉基', NULL, '99999999', 0, '1', 'guest', '2022-10-29 10:36:20', NULL, '2022-10-29 10:36:20.402');
COMMIT;

-- ----------------------------
-- Table structure for n_listinfo
-- ----------------------------
DROP TABLE IF EXISTS `n_listinfo`;
CREATE TABLE `n_listinfo` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ss_name_id` int DEFAULT NULL COMMENT '楼名id',
  `ss_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '宿舍楼名称',
  `ss_floor_num` int DEFAULT '7' COMMENT '几层楼',
  `ss_houses` int DEFAULT '30' COMMENT '单层几个房间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='门牌号、楼牌号信息';

-- ----------------------------
-- Records of n_listinfo
-- ----------------------------
BEGIN;
INSERT INTO `n_listinfo` (`id`, `ss_name_id`, `ss_name`, `ss_floor_num`, `ss_houses`) VALUES (1, 1, '立雪楼', 7, 30);
INSERT INTO `n_listinfo` (`id`, `ss_name_id`, `ss_name`, `ss_floor_num`, `ss_houses`) VALUES (2, 2, '信笃楼', 7, 30);
INSERT INTO `n_listinfo` (`id`, `ss_name_id`, `ss_name`, `ss_floor_num`, `ss_houses`) VALUES (3, 3, '黄鹤楼', 7, 30);
COMMIT;

-- ----------------------------
-- Table structure for n_news
-- ----------------------------
DROP TABLE IF EXISTS `n_news`;
CREATE TABLE `n_news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '主题',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分类',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '简介',
  `is_delete` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '是否删除',
  `species` int DEFAULT NULL COMMENT '种类',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片uuid',
  `hot_num` int DEFAULT NULL COMMENT '热门登记',
  `status` int DEFAULT '1' COMMENT '1,未读；0：已删除；2，已读',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='新闻信息表';

-- ----------------------------
-- Records of n_news
-- ----------------------------
BEGIN;
INSERT INTO `n_news` (`id`, `title`, `body`, `type`, `content`, `is_delete`, `species`, `file_name`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (15, '452524', '<p>451277257527</p>', '1', NULL, '1', 0, NULL, 1, -1, '管理员', '2022-05-14 18:23:13', '管理员', '2022-05-14 21:12:07');
INSERT INTO `n_news` (`id`, `title`, `body`, `type`, `content`, `is_delete`, `species`, `file_name`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (16, '3333', '<p>213333333333333333</p>', '2', NULL, '1', 0, '77f1640e7fe24068aa0fd3c4ed2d8589', 1, -1, '管理员', '2022-05-14 18:23:49', '2452452452', '2022-05-14 18:34:04');
INSERT INTO `n_news` (`id`, `title`, `body`, `type`, `content`, `is_delete`, `species`, `file_name`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (17, '524', '<p>sfsefsefsef</p>', '2', NULL, '1', 1, NULL, 2, -1, '管理员', '2022-05-14 19:02:25', '管理员', '2022-05-14 21:12:09');
INSERT INTO `n_news` (`id`, `title`, `body`, `type`, `content`, `is_delete`, `species`, `file_name`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (18, '通知', '<p>明天九点大厅开会</p>', '2', NULL, '1', 1, NULL, 1, 2, '管理员', '2022-05-14 21:12:50', NULL, '2022-10-29 10:35:48');
INSERT INTO `n_news` (`id`, `title`, `body`, `type`, `content`, `is_delete`, `species`, `file_name`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (19, '通知', '<p>明天早上九点在大厅开会</p>', '2', NULL, '1', 1, NULL, 1, 2, '管理员', '2022-05-14 21:28:07', NULL, '2022-10-29 10:13:07');
INSERT INTO `n_news` (`id`, `title`, `body`, `type`, `content`, `is_delete`, `species`, `file_name`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (20, '放假通知', '<p>明天开始放假</p>', '2', NULL, '1', 0, NULL, 1, 0, '管理员', '2022-05-16 23:20:26', '管理员', '2022-05-21 14:40:43');
INSERT INTO `n_news` (`id`, `title`, `body`, `type`, `content`, `is_delete`, `species`, `file_name`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (21, '放假通知', '<p>明天开始放假1212121</p>', '2', NULL, '1', 0, NULL, 1, 0, '管理员', '2022-05-16 23:26:25', '管理员', '2022-05-21 14:40:43');
INSERT INTO `n_news` (`id`, `title`, `body`, `type`, `content`, `is_delete`, `species`, `file_name`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (22, '放假通知', '<p>明天开始放假1212121</p>', '2', NULL, '1', 0, NULL, 1, 0, '管理员', '2022-05-16 23:38:12', '管理员', '2022-05-21 14:40:44');
INSERT INTO `n_news` (`id`, `title`, `body`, `type`, `content`, `is_delete`, `species`, `file_name`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (23, '测试课件', '<p>1212342432424234</p>', '2', NULL, '1', 1, NULL, 2, 2, '管理员', '2022-05-17 00:03:41', '管理员', '2022-10-29 10:13:09');
INSERT INTO `n_news` (`id`, `title`, `body`, `type`, `content`, `is_delete`, `species`, `file_name`, `hot_num`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (24, 'ggggggggg', '<p>d恩家扩扩扩扩扩扩扩扩扩扩扩过过过过</p>', '1', NULL, '1', 0, 'cde8f8b7ee1e40ab81a777d82d586794', 1, 2, '管理员', '2022-10-29 10:27:15', NULL, '2022-10-29 10:35:50');
COMMIT;

-- ----------------------------
-- Table structure for n_news_type
-- ----------------------------
DROP TABLE IF EXISTS `n_news_type`;
CREATE TABLE `n_news_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_id` int DEFAULT NULL COMMENT '分类id',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类名称',
  `status` int DEFAULT NULL COMMENT '是否删除',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='新闻类型表';

-- ----------------------------
-- Records of n_news_type
-- ----------------------------
BEGIN;
INSERT INTO `n_news_type` (`id`, `type_id`, `type_name`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (1, 1, '新闻类', 1, '数据库维护', '2022-03-10 11:20:43', '数据库维护', '2022-03-10 11:26:40');
INSERT INTO `n_news_type` (`id`, `type_id`, `type_name`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (2, 2, '通知类', 1, '管理员', '2022-05-14 00:55:57', '管理员', '2022-05-14 00:56:09');
COMMIT;

-- ----------------------------
-- Table structure for n_personnel
-- ----------------------------
DROP TABLE IF EXISTS `n_personnel`;
CREATE TABLE `n_personnel` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '护工id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '绑定的用户名',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '身份证件号',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '家庭住址',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '本人联系电话',
  `family_phone1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '紧急联系人1',
  `family_phone2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `warning` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '注意事项',
  `status` int DEFAULT '1' COMMENT '1,正常；0：停止使用',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='客户表(老人)';

-- ----------------------------
-- Records of n_personnel
-- ----------------------------
BEGIN;
INSERT INTO `n_personnel` (`id`, `user_id`, `user_name`, `name`, `uuid`, `address`, `phone`, `family_phone1`, `family_phone2`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (1, 5, NULL, '王', '410327192606309636', '河南省洛阳市', '17703795206', '13783141541', '13783145545', '注意', 0, '数据库维护', '2022-03-15 02:18:26', '果果', '2022-05-13 23:26:54');
INSERT INTO `n_personnel` (`id`, `user_id`, `user_name`, `name`, `uuid`, `address`, `phone`, `family_phone1`, `family_phone2`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (2, 2, NULL, '刘奶奶', '410101197410314847', '云南省 丽江市 其它区', '18026617444', '18512044762', '18152506003', '不吃麻辣烫，吃麻辣烫不吃麻不吃烫', 0, '数据库维护', '2022-03-15 15:39:35', '果果', '2022-05-13 23:26:52');
INSERT INTO `n_personnel` (`id`, `user_id`, `user_name`, `name`, `uuid`, `address`, `phone`, `family_phone1`, `family_phone2`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (3, 5, NULL, '王大爷', '410101198601219679', '新疆维吾尔自治区 吐鲁番地区 鄯善县', '18972494730', '15343290517', '15094506658', ' 阿鼻地狱阿党比周 ', 0, '数据库维护', '2022-03-15 03:44:52', '果果', '2022-05-13 23:26:45');
INSERT INTO `n_personnel` (`id`, `user_id`, `user_name`, `name`, `uuid`, `address`, `phone`, `family_phone1`, `family_phone2`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (4, 5, NULL, '875', '758', '875', '785875', '785', '785785', '785', 0, '果果', '2022-05-09 02:40:25', '果果', '2022-05-13 23:26:42');
INSERT INTO `n_personnel` (`id`, `user_id`, `user_name`, `name`, `uuid`, `address`, `phone`, `family_phone1`, `family_phone2`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (5, 32, NULL, '王翠花', '267387376539872098', '河南省 郑州市 金水区', '17628762543', '1876276287', '1243665276', '无', 1, '管理员', '2022-05-14 00:27:17', '管理员', '2022-10-29 10:30:45');
INSERT INTO `n_personnel` (`id`, `user_id`, `user_name`, `name`, `uuid`, `address`, `phone`, `family_phone1`, `family_phone2`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (6, 31, NULL, '李大锤', '276528762887628726', '河南省 郑州市 中原区', '12872653763', '12872872872', '12766738732', '无', 1, '管理员', '2022-05-14 00:30:04', '管理员', '2022-05-14 20:13:15');
INSERT INTO `n_personnel` (`id`, `user_id`, `user_name`, `name`, `uuid`, `address`, `phone`, `family_phone1`, `family_phone2`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (7, 32, NULL, '陈秀娟', '287529276251762538', '河南省 周口市 川汇区', '18726426272', '192876398231', '19287265202', NULL, 1, '管理员', '2022-05-14 20:15:03', NULL, '2022-05-14 20:15:03');
INSERT INTO `n_personnel` (`id`, `user_id`, `user_name`, `name`, `uuid`, `address`, `phone`, `family_phone1`, `family_phone2`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (8, 34, NULL, '祝志强', '187645326755198726', '河南省 郑州市 中原区', '19876754321', '10298712981', '10981262543', '不要吃辣的', 1, '管理员', '2022-05-14 08:16:21', '管理员', '2022-10-29 10:25:38');
INSERT INTO `n_personnel` (`id`, `user_id`, `user_name`, `name`, `uuid`, `address`, `phone`, `family_phone1`, `family_phone2`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (9, 33, NULL, '俞锋', '287629873634510982', '河南省 周口市 淮阳区', '12872652319', '19827620982', '19287623111', NULL, 1, '管理员', '2022-05-14 20:17:31', NULL, '2022-05-14 20:17:31');
INSERT INTO `n_personnel` (`id`, `user_id`, `user_name`, `name`, `uuid`, `address`, `phone`, `family_phone1`, `family_phone2`, `warning`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (10, 33, NULL, '赵文兴', '198276254317262812', '河南省 郑州市 高新区', '12876254212', '10928726512', '10981762982', NULL, 1, '管理员', '2022-05-14 20:18:45', NULL, '2022-05-14 20:18:45');
COMMIT;

-- ----------------------------
-- Table structure for n_personnel_drug
-- ----------------------------
DROP TABLE IF EXISTS `n_personnel_drug`;
CREATE TABLE `n_personnel_drug` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `personnel_id` int DEFAULT NULL COMMENT '客户id',
  `drug_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '药物id',
  `dose` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '剂量说明',
  `status` int DEFAULT '1' COMMENT '1,正常；0：已删除',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='客户使用药品信息表';

-- ----------------------------
-- Records of n_personnel_drug
-- ----------------------------
BEGIN;
INSERT INTO `n_personnel_drug` (`id`, `personnel_id`, `drug_id`, `dose`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (1, 1, '100001', '一日一次', 0, '数据库维护', '2022-03-15 17:38:52', NULL, '2022-05-10 21:51:27');
INSERT INTO `n_personnel_drug` (`id`, `personnel_id`, `drug_id`, `dose`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (2, 1, '100002', '一日两次', 1, '数据库维护', '2022-03-15 17:39:13', NULL, NULL);
INSERT INTO `n_personnel_drug` (`id`, `personnel_id`, `drug_id`, `dose`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (3, 1, '100003', '一日三次', 1, '数据库维护', '2022-03-15 17:39:29', NULL, NULL);
INSERT INTO `n_personnel_drug` (`id`, `personnel_id`, `drug_id`, `dose`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (4, 5, '100001', '1', 1, NULL, '2022-10-29 10:34:26', NULL, '2022-10-29 10:34:26');
COMMIT;

-- ----------------------------
-- Table structure for n_personnel_food
-- ----------------------------
DROP TABLE IF EXISTS `n_personnel_food`;
CREATE TABLE `n_personnel_food` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `personnel_id` int DEFAULT NULL COMMENT '客户id',
  `foods_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '食品id',
  `dose` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '食品说明',
  `status` int DEFAULT '1' COMMENT '1,正常；0：已删除',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='客户使用食物表';

-- ----------------------------
-- Records of n_personnel_food
-- ----------------------------
BEGIN;
INSERT INTO `n_personnel_food` (`id`, `personnel_id`, `foods_id`, `dose`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (1, 1, '100001', '少糖少盐', 1, '数据库维护', '2022-03-16 10:43:39', NULL, NULL);
INSERT INTO `n_personnel_food` (`id`, `personnel_id`, `foods_id`, `dose`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (2, 5, '100001', '1111', 1, NULL, '2022-10-29 10:34:32', NULL, '2022-10-29 10:34:32');
COMMIT;

-- ----------------------------
-- Table structure for n_user
-- ----------------------------
DROP TABLE IF EXISTS `n_user`;
CREATE TABLE `n_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '表主键id',
  `account` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `admin` bit(1) DEFAULT b'0' COMMENT '是否管理员',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐值',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `uuid` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号码',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系地址',
  `status` int DEFAULT '1' COMMENT '1,正常；0：已删除',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of n_user
-- ----------------------------
BEGIN;
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (1, '2815959477', 'F3D3FA9A684BAA5478BBB4D774509A42', b'1', 'E3133902-7449-4F97-9024-786D3BF9B3A3', '赵赵', '410323200108269532', '15290698853', '河南省 郑州市 金水区', 1, '数据库创建', '2022-03-10 11:53:52', '管理员', '2022-05-14 00:07:28');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (2, '17703795206', '24BDF4F3C496B03BEA72D9C975521AA7', b'0', '6DCE774C-35D4-488C-8A71-3FE3AD3BDE6D', '赵赵', '130201199206161502', '15290698853', '河南省 开封市 其它区', 0, '数据库创建', '2022-03-16 11:53:55', '果果', '2022-05-13 23:27:53');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (3, 'admin222', 'C5ABB83E1786835FEA0C3F22366D2BF0', b'1', 'B05A86AE-7FA4-49EA-8544-442C84718F9E', '管理员', '640201198004212928', '11011011011', '安徽省 阜阳市 太和县', 1, '数据库维护', '2022-03-16 11:55:27', '果果', '2022-05-13 23:59:53');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (4, 'guest', 'F31F6C97114813F11D49E030690D21CC', b'0', '688FF6CD-EB13-471A-927D-AE230F4D881B', '王果果', '230101200311107739', '15060080362', '江苏省 泰州市 海陵区', 0, '果果', '2022-03-16 16:56:43', '果果', '2022-05-07 20:42:51');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (5, '14710787199', 'EC37CD2031BBEF99007FE4BAA2650522', b'0', '4D3682BE-369E-4E4D-AF45-7D2AB76B9905', '胡图图', '630101198505124742', '14982804555', '山东省 青岛市 平度市', 0, '果果', '2022-03-16 17:31:58', '果果', '2022-05-13 23:27:56');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (6, '18883803858', '14549D5B200BA0555E0998D9F17C466D', b'0', 'DE84C757-30F5-408C-BE6C-D89C2D543A0F', '胡英俊', '350101199712214967', '15670572994', '安徽省 六安市 舒城县', 0, '果果', '2022-03-16 17:32:54', '果果', '2022-05-13 23:27:58');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (7, '13839453452', 'F091F6934B0A9423094179A720A4B25C', b'0', '62acbc0a-5526-4a0a-b139-bbc22ae38262', '黄刚', '640101199502136701', '17687256425', ' 新疆维吾尔自治区 博尔塔拉蒙古自治州 精河县', 0, '果果', '2022-03-16 17:33:51', '果果', '2022-05-13 23:24:19');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (8, '15070329908', '33B661364476CAA427FD4A083101676B', b'0', '7d44eb92-4011-43f4-aeb5-140a3eb30394', ' 邱艳', '430101198911215650', '18143126526', '贵州省 黔南布依族苗族自治州 福泉市', 0, '果果', '2022-03-16 17:34:13', '果果', '2022-05-13 23:24:27');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (9, '19359074543', '5EAE4EFF7316D87DED34EF03ABB45DCE', b'0', 'a7e73f16-479b-4f0f-8a69-d9ee9460c885', '石洋', '310101198611011886', '19352430493', '云南省 保山市 龙陵县', 0, '果果', '2022-03-16 17:34:43', '果果', '2022-05-13 23:23:20');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (10, '18339016649', '5ECF7D019456158CF4983F7AFA669A93', b'0', '61bbd87a-7d55-49fe-a65b-b19c8cf71990', '朱超', '370201199311229016', '15960157192', '贵州省 黔南布依族苗族自治州 平塘县', 0, '果果', '2022-03-16 17:35:05', '果果', '2022-03-17 17:58:22');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (11, '17791380699', '98E307AA8899D4F3A4C0039F1A71F7DE', b'0', 'b2326e66-39ee-4726-ba59-8175918fdc34', '姜刚', '220201200203051620', '18842655993', '贵州省 安顺市 紫云苗族布依族自治县', 0, '果果', '2022-03-16 17:35:30', '果果', '2022-05-13 23:23:14');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (12, '15846323495', '5A15E4600C726D55AFC2C73E16EE4E80', b'0', '6de9f14d-f8d6-45d6-9ec3-979e656844c9', '崔洋', '460101197706134094', '15564927460', '湖南省 株洲市 株洲县', 0, '果果', '2022-03-16 17:36:04', '果果', '2022-05-13 23:21:40');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (13, '19510149610', '5A15E4600C726D55AFC2C73E16EE4E80', b'0', '6de9f14d-f8d6-45d6-9ec3-979e656844c9', '汤敏', '650101199101145641', ' 13159916859', '吉林省 白城市 通榆县', 0, '果果', '2022-03-22 17:36:30', '果果', '2022-05-13 23:21:30');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (16, '123', '7614120A586928C10CBCE4A6698D4032', b'0', '6c77f64c-1440-4cd9-bc94-64a3539da530', '咋', '122', '1112', '11111', 0, '果果', '2022-05-13 23:54:40', '果果', '2022-05-13 23:54:45');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (17, '15290698853', '140D7B72B97D740180A87E93B3B41B33', b'0', '140E2C3A-81AC-4561-A5FC-D41AD1887255', '果果', '152765278926726254', '15538307738', '河南省 郑州市 金水区', 0, '果果', '2022-05-13 23:57:01', '管理员', '2022-05-14 00:10:19');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (18, '15538302232', '819D68F2E56E19E45E567132FD5FAEA9', b'0', '5db9953c-7274-4dd8-91cd-b98fdbd359b6', '胡图图', '725526255687239628', '17655723452', '陕西省 西安市 大雁塔', 0, '果果', '2022-05-13 23:59:08', '管理员', '2022-05-14 00:10:22');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (19, '18336567620', '05DD3B3B7FE1685DEDDE129BB131E6F4', b'0', '15987b56-ae21-40cf-bbe1-c896eb7f1a02', '胡英俊', '265378762476389826', '1672542287', '贵州省 贵阳市', 0, '管理员', '2022-05-14 00:03:37', '管理员', '2022-05-14 00:10:34');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (20, '15290698853', '5997940F4ED80F8AC14B0EA07088C6A8', b'0', '31D26BF1-2D4B-4443-A547-CB66905EAD39', '胡图图', '652432755276524254', '1762548876', '浙江省 杭州市 翻斗花园', 0, '管理员', '2022-05-14 00:12:00', '管理员', '2022-05-14 19:24:18');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (21, '15538302232', 'F80FC1B9D978DBAD663647101B50AC5D', b'0', 'be2c01db-2b58-4483-ac05-57071293885b', '胡英俊', '265428265498762976', '17625687652', '贵州省 贵阳市 未知区', 0, '管理员', '2022-05-14 00:13:25', '管理员', '2022-05-14 19:57:35');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (22, '18336567620', '7E886D2300EC33BDBACC3761698EE0B8', b'0', '369d7450-9c8f-441d-9985-73fbf2819d0d', '林林', '165526287629763865', '18724322361', '山西省 太原市 富贵区', 0, '管理员', '2022-05-14 00:14:52', '管理员', '2022-05-14 19:58:08');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (23, '110110110', 'EA491B4495080195079AC87141487686', b'0', 'a29dd8e8-e2c4-418f-9dd9-4a5b669dc8c8', '海绵宝宝', '376454276538762431', '19876354289', '山东省 青岛市 海底区', 0, '管理员', '2022-05-14 00:16:11', '管理员', '2022-05-14 19:57:41');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (24, '220220220', '8EF5AC60EA91CBDEA001FFD2AC503B31', b'0', 'ca006602-cd66-495c-87dd-fba5ddd07e55', '喜羊羊', '456378276524568723', '14326753434', '广东省 广州市 青青草原', 0, '管理员', '2022-05-14 00:17:35', '管理员', '2022-05-14 19:57:42');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (25, '330330330', '10A9AA2CB0CEAFCDB67B8E473C26EED4', b'0', '05f43130-6c35-4c26-8661-c6ab41420e86', '灰太狼', '365437365428763876', '12348736983', '海南省 海口市 狼堡', 0, '管理员', '2022-05-14 00:19:22', '管理员', '2022-05-14 19:57:53');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (26, '440440440', 'E43B900CDB360696DF9EFA4E03799D47', b'0', '6b9c1424-99f2-4eeb-9631-a2182ba5fbeb', '光头强', '345672673529871986', '12683763456', '湖南省 长沙市 森林区', 0, '管理员', '2022-05-14 00:20:57', '管理员', '2022-05-14 19:57:44');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (27, '550550550', '3E96D3DCB80CD7799837C7B49E54BA33', b'0', '31eebf7b-b8e9-44d8-b45e-11a9af41d79e', '晴天小猪', '276376277629872976', '15576277253', '黑龙江省 哈尔滨市 城堡区', 0, '管理员', '2022-05-14 00:22:56', '管理员', '2022-05-14 19:57:54');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (28, '660660660', 'C62BBD821C39E86D81E89E3EF529BB20', b'0', 'b2ab9d4e-5d5d-4da3-9b10-196b9f179a5c', '龙猫', '376354376352652876', '18762875572', '吉林省 吉林市 森林区', 0, '管理员', '2022-05-14 00:25:00', '管理员', '2022-05-14 19:57:46');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (29, '770770770', 'CF006CF5EF8621F8D55FCB047CBD37DB', b'0', 'afa12047-1172-497a-9da1-b29654f65d92', '小丽', '265427652437652876', '19826528743', '河南省 洛阳市 未知区', 0, '赵赵', '2022-05-14 01:17:51', '管理员', '2022-05-14 19:57:56');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (30, '2452452452', '98D3E0B2F9925A37722673F88AC897C1', b'0', '6E7B6A8D-65A7-45F3-8E23-697F2DB70027', '2452452452', '2452452452', '2452452452', '2452452452', 0, '管理员', '2022-05-14 18:25:07', '管理员', '2022-05-14 19:57:58');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (31, '15290698851', 'CF1EF2376FE0699323FAD926291CF90B', b'0', '216D54B7-4AC8-43CB-8721-235B48C6433B', '胡图图', '263874384938943', '1627392839', '河南省 郑州市', 0, '管理员', '2022-05-14 19:25:38', '管理员', '2022-05-15 22:28:22');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (32, '11011101', '023520153919BB8F7743EE7372AEE58E', b'0', 'CCCFD149-E9CE-4909-A77D-8B06831C7086', '胡英俊', '374834899782329', '12732792223', '山东省 青岛市 翻斗花园', 1, '管理员', '2022-05-14 20:00:39', '管理员', '2022-10-29 10:32:15');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (33, '22022202', 'C3D6462DF42789E0D42BC4B8847F6A66', b'0', '0A4E7D85-A2F1-4F38-B00D-7E9DFDF4FC61', '灰太狼', '2727392898438493', '1727829289', '陕西省 西安市 狼堡', 1, '管理员', '2022-05-14 20:01:40', '管理员', '2022-05-14 20:02:06');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (34, '33033303', '6810E245A3426DDC445C3F5B260BCE35', b'0', '8AAA8958-C4F0-4A59-8BA7-84352C7F99FF', '喜羊羊', '28832930294943', '1737643894', '吉林省 吉林市 青青草原', 1, '管理员', '2022-05-14 20:03:52', '管理员', '2022-05-14 20:03:52');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (35, '44044404', '36A396B87353BC0848BE378FC6A37442', b'0', '14E3D624-C125-494C-88DA-20A803FB32AF', '海绵宝宝', '63733928397392893', '1762823828', '湖南省 长沙市 海底区', 1, '管理员', '2022-05-14 20:04:45', NULL, '2022-05-14 20:04:45');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (36, '55055505', '009F1DA1A2B663615A1632DBA19828B3', b'0', 'AE6B4563-61A7-47E9-AA54-4F6A0599F3A1', '龙猫', '273839302938498', '1272392222', '广东省 广州市 森林区', 1, '管理员', '2022-05-14 20:06:10', NULL, '2022-05-14 20:06:10');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (37, '66066606', 'B5410BFA641BAD254BDDA0B8B121D7BD', b'0', 'D5FCCDE2-3597-4D44-AEC2-B3E823B5EE25', '光头强', '276389302930328', '1728802920', '河南省 洛阳市 森林区', 1, '管理员', '2022-05-14 20:08:47', NULL, '2022-05-14 20:08:47');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (38, '77077707', '81D8DF3A778820CF8692D44F45C63253', b'0', '39528ED7-7FCF-4256-9F83-5911AAFE0751', '阿莉埃蒂', '172932099438453', '1822832902', '河北省 廊坊市 小人区', 1, '管理员', '2022-05-14 20:09:46', NULL, '2022-05-14 20:09:46');
INSERT INTO `n_user` (`id`, `account`, `password`, `admin`, `salt`, `name`, `uuid`, `phone`, `address`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (39, '88088808', '6D534678B6E135032990064F5AB81F6F', b'0', '60ED6770-542F-4C86-B7D2-8F38703913D7', '晴天小猪', '28792382930292', '1128298202', '海南省 海口市 城堡区', 1, '管理员', '2022-05-14 20:12:22', NULL, '2022-05-14 20:12:22');
COMMIT;

-- ----------------------------
-- Table structure for n_visitors
-- ----------------------------
DROP TABLE IF EXISTS `n_visitors`;
CREATE TABLE `n_visitors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '访客',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '访客手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` int DEFAULT '1' COMMENT '1,正常；0：已删除',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='访客表';

-- ----------------------------
-- Records of n_visitors
-- ----------------------------
BEGIN;
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (1, '121', '1212', '12', NULL, NULL, 0, 'guest', '2022-04-30 21:19:52', NULL, '2022-05-13 23:15:21');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (2, '121', '1212', '12', NULL, NULL, 0, 'guest', '2022-04-30 21:20:16', NULL, '2022-05-13 23:15:23');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (3, '121', '1212', '12', NULL, NULL, 0, 'guest', '2022-04-30 21:20:48', NULL, '2022-05-13 23:15:25');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (4, '121', '212', '12', NULL, NULL, 0, 'guest', '2022-05-06 15:28:52', NULL, '2022-05-13 23:15:27');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (5, '11111111111111111111', '11111111111111111111', '11111111111111111111', NULL, NULL, 0, 'guest', '2022-05-06 15:35:11', NULL, '2022-05-13 23:15:29');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (6, '11111111111111111111', '11111111111111111111', '11111111111111111111', NULL, NULL, 0, 'guest', '2022-05-06 15:36:18', NULL, '2022-05-13 23:15:31');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (7, '111111111111111', '111111111111111', '111111111111111', NULL, NULL, 0, 'guest', '2022-05-06 15:47:31', NULL, '2022-05-13 23:15:32');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (8, '111111111111111', '111111111111111', '111111111111111', NULL, NULL, 0, 'guest', '2022-05-06 15:47:34', NULL, '2022-05-13 23:15:34');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (9, '111111111111111', '111111111111111', '111111111111111', '2022-05-06 15:47:19', '2022-05-06 15:47:21', 1, 'guest', '2022-05-06 15:47:34', NULL, '2022-05-10 22:19:05');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (10, '111111111111111', '111111111111111', '111111111111111', '2022-05-06 15:47:19', '2022-05-06 15:47:21', 1, 'guest', '2022-05-06 15:47:34', NULL, '2022-05-09 15:30:48');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (11, '赵翠花', '11011011011', '123zhaozhao@qq.com', NULL, NULL, 0, 'guest', '2022-05-13 23:16:33', NULL, '2022-05-14 21:31:08');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (12, '胡图图', '11223344551', '123tutuo@qq.com', NULL, NULL, 0, 'guest', '2022-05-13 23:17:36', NULL, '2022-05-14 21:31:13');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (13, '赵', '110110', '6799@qq.com', NULL, NULL, 0, 'guest', '2022-05-14 02:19:25', NULL, '2022-05-14 15:25:14');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (14, '派大星', '18927872828', '1172718@qq.com', NULL, NULL, 0, 'guest', '2022-05-14 17:17:37', NULL, '2022-05-14 21:31:15');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (15, '胡图图', '1567655433', '4577899@qq.com', NULL, NULL, 0, 'guest', '2022-05-14 18:36:55', NULL, '2022-05-14 18:53:10');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (16, 'xiaokkk', '10082611', '2555@qq.com', NULL, NULL, 0, 'guest', '2022-05-14 19:06:06', NULL, '2022-05-14 21:31:19');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (17, '123', '231', '3213', '2022-05-14 19:21:29', '2022-05-14 19:21:38', 1, 'guest', '2022-05-14 19:21:55', NULL, '2022-05-14 19:21:55');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (18, '派大星', '178929290202', '67899877@qq.com', '2022-05-15 09:00:00', '2022-05-15 11:00:00', 1, 'guest', '2022-05-14 21:30:45', NULL, '2022-05-14 21:30:45');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (19, '派大星', '178929290202', '67899877@qq.com', '2022-05-15 09:00:00', '2022-05-15 11:00:00', 1, 'guest', '2022-05-15 01:15:10', NULL, '2022-05-15 01:15:10');
INSERT INTO `n_visitors` (`id`, `name`, `phone`, `email`, `start_time`, `end_time`, `status`, `create_user`, `create_time`, `modified_user`, `modified_time`) VALUES (20, '法外狂徒张三', '12345678975', '32432423@qq.com', '2022-05-16 00:00:00', '2022-05-17 00:00:00', 1, 'guest', '2022-05-16 00:02:45', NULL, '2022-05-16 00:02:45');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
