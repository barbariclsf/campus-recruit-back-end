/*
Navicat MySQL Data Transfer

Source Server         : windows
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : campus-recruit

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2021-12-06 16:58:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_attachment_resume`
-- ----------------------------
DROP TABLE IF EXISTS `t_attachment_resume`;
CREATE TABLE `t_attachment_resume` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `resume_url` varchar(200) DEFAULT NULL COMMENT '简历地址',
  `resume_name` varchar(100) DEFAULT NULL COMMENT '简历名称',
  `public_date` datetime DEFAULT NULL COMMENT '发布时间',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_attachment_resume
-- ----------------------------
INSERT INTO `t_attachment_resume` VALUES ('20', '54642583', 'http://r2apv4kp8.hn-bkt.clouddn.com/d9ac55a3a636477dbee49e3a33e3315c.pdf', '22届java后端开发-江西农业大学李声福.pdf', '2021-11-10 15:59:14', null);
INSERT INTO `t_attachment_resume` VALUES ('21', '54642583', 'http://r2apv4kp8.hn-bkt.clouddn.com/c0ea65cfe14d42fd91b1fa488494f1ab.pdf', '22届java后端开发-江西农业大学李声福.pdf', '2021-11-11 23:27:37', null);
INSERT INTO `t_attachment_resume` VALUES ('22', '54642583', 'http://r2apv4kp8.hn-bkt.clouddn.com/ceb3ac5a2a01449cb8107b22b780cd55.pdf', '22届java后端开发-江西农业大学李声福.pdf', '2021-11-18 20:31:36', null);

-- ----------------------------
-- Table structure for `t_collect`
-- ----------------------------
DROP TABLE IF EXISTS `t_collect`;
CREATE TABLE `t_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `content_id` int(11) DEFAULT NULL COMMENT '职位id或者公司id',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_collect
-- ----------------------------
INSERT INTO `t_collect` VALUES ('34', '54642583', '88196353', '0');
INSERT INTO `t_collect` VALUES ('35', '54642583', '454545', '1');
INSERT INTO `t_collect` VALUES ('36', '54642583', '232323', '1');

-- ----------------------------
-- Table structure for `t_company`
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT NULL COMMENT '公司id',
  `company_name` varchar(30) DEFAULT NULL COMMENT '公司名称',
  `logo` varchar(500) DEFAULT NULL COMMENT '公司logo',
  `trade` int(11) DEFAULT NULL COMMENT '公司类型',
  `scale` varchar(30) DEFAULT NULL COMMENT '公司规模',
  `description` varchar(1000) DEFAULT NULL COMMENT '公司介绍',
  `location` varchar(50) DEFAULT NULL COMMENT '工作地点',
  `postion_num` int(11) DEFAULT NULL COMMENT '在招人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_company
-- ----------------------------
INSERT INTO `t_company` VALUES ('1', '232323', '中国移动', 'https://bkimg.cdn.bcebos.com/pic/4bed2e738bd4b31c87013885e799307f9e2f070852f7?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg', '1', '1000-10000', '通信', '北京市', '30');
INSERT INTO `t_company` VALUES ('2', '454545', '阿里巴巴', 'https://bkimg.cdn.bcebos.com/pic/908fa0ec08fa513defeb0567316d55fbb3fbd9c2?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg', '3', '500-1000', '金融银行', '上海市', '32');
INSERT INTO `t_company` VALUES ('3', '567546', '字节跳动', 'https://bkimg.cdn.bcebos.com/pic/a50f4bfbfbedab64034ff52fb97cb8c379310b55b080?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg', '1', '1000-10000', '互联网', '北京市', '120');
INSERT INTO `t_company` VALUES ('4', '432345', '转转', 'https://bkimg.cdn.bcebos.com/pic/b21bb051f8198618367a44d31fbd39738bd4b31c4811?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg', '1', '10000以上', '互联网电商', '北京市', '34');
INSERT INTO `t_company` VALUES ('5', '897535', '腾讯', 'https://bkimg.cdn.bcebos.com/pic/5bafa40f4bfbfbedab645645fda0e036afc37931b1f3?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto', '1', '1000-10000', '互联网游戏', '深圳市', '45');

-- ----------------------------
-- Table structure for `t_deliver`
-- ----------------------------
DROP TABLE IF EXISTS `t_deliver`;
CREATE TABLE `t_deliver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deliver_id` int(11) DEFAULT NULL COMMENT '投递者id',
  `resume_id` int(11) DEFAULT NULL COMMENT '简历id',
  `postion_id` int(11) DEFAULT NULL COMMENT '职位id',
  `publicer_id` int(11) DEFAULT NULL COMMENT '发布者id',
  `deliver_date` datetime DEFAULT NULL COMMENT '投递时间',
  `type` int(11) DEFAULT NULL COMMENT '简历类别',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_deliver
-- ----------------------------
INSERT INTO `t_deliver` VALUES ('5', '54642583', '20', '75621152', '54642583', '2021-11-11 23:31:09', '1', '0');
INSERT INTO `t_deliver` VALUES ('6', '54642583', '0', '84144196', '54642583', '2021-11-18 20:29:01', '0', '0');
INSERT INTO `t_deliver` VALUES ('7', '54642583', '20', '61825448', '54642583', '2021-11-18 20:34:02', '1', '0');

-- ----------------------------
-- Table structure for `t_education_experience`
-- ----------------------------
DROP TABLE IF EXISTS `t_education_experience`;
CREATE TABLE `t_education_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `school_name` varchar(30) DEFAULT NULL COMMENT '学校名称',
  `major` varchar(30) DEFAULT NULL COMMENT '专业',
  `degree` varchar(20) DEFAULT NULL COMMENT '学位',
  `start_date` varchar(30) DEFAULT NULL COMMENT '开始时间',
  `end_date` varchar(30) DEFAULT NULL COMMENT '结束时间',
  `description` varchar(500) DEFAULT NULL COMMENT '在校经历',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_education_experience
-- ----------------------------
INSERT INTO `t_education_experience` VALUES ('4', '54642583', '江西农业大学', '软件工程', '本科', '2019-01-01', '2021-01-01', '<p>哈哈哈哈啦啦啦啦</p><p>nib</p>');

-- ----------------------------
-- Table structure for `t_internship_experience`
-- ----------------------------
DROP TABLE IF EXISTS `t_internship_experience`;
CREATE TABLE `t_internship_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `company_name` varchar(30) DEFAULT NULL COMMENT '公司名称',
  `postion` varchar(30) DEFAULT NULL COMMENT '担任职位',
  `start_date` varchar(30) DEFAULT NULL COMMENT '开始时间',
  `end_date` varchar(30) DEFAULT NULL COMMENT '结束时间',
  `description` varchar(500) DEFAULT NULL COMMENT '工作描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_internship_experience
-- ----------------------------
INSERT INTO `t_internship_experience` VALUES ('3', '54642583', '转转集团', 'java工程师', '2018-01-01', '2021-01-01', '<p>后端功能开发</p><p>数据库设计</p>');
INSERT INTO `t_internship_experience` VALUES ('4', '54642583', '字节', '后端开发', '2021-01-01', '2021-01-01', '<p>全栈</p>');

-- ----------------------------
-- Table structure for `t_jobintention`
-- ----------------------------
DROP TABLE IF EXISTS `t_jobintention`;
CREATE TABLE `t_jobintention` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `trade` varchar(50) DEFAULT NULL COMMENT '期望行业',
  `postion` varchar(50) DEFAULT NULL COMMENT '期望职位',
  `salary` varchar(30) DEFAULT NULL COMMENT '期望薪资',
  `location` varchar(100) DEFAULT NULL COMMENT '期望地点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_jobintention
-- ----------------------------
INSERT INTO `t_jobintention` VALUES ('2', '54642583', '互联网', '后端开发工程师', '15000以上', '北京市');

-- ----------------------------
-- Table structure for `t_postion`
-- ----------------------------
DROP TABLE IF EXISTS `t_postion`;
CREATE TABLE `t_postion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `postion_id` int(11) DEFAULT NULL COMMENT '职位id',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id',
  `publicer_id` int(11) DEFAULT NULL COMMENT '发布者id',
  `postion_name` varchar(50) DEFAULT NULL COMMENT '职位名称',
  `salary` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '薪资',
  `demand_education` varchar(20) DEFAULT NULL COMMENT '学历要求',
  `demand_major` varchar(30) DEFAULT NULL COMMENT '专业要求',
  `description` varchar(2000) DEFAULT NULL COMMENT '职位描述',
  `location` varchar(30) DEFAULT NULL COMMENT '工作地点',
  `public_date` datetime DEFAULT NULL COMMENT '发布时间',
  `num` int(11) DEFAULT NULL COMMENT '简历投递数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_postion
-- ----------------------------
INSERT INTO `t_postion` VALUES ('3', '75621152', '232323', '54642583', 'java工程师', '8000-1500', '硕士', '不限', '<p>我们需要:</p><p><br></p><p>1.参与产品需求分析,研究产品技术细节;根据产品需求编写相应的技术文档;</p><p><br></p><p>2.熟悉Java基础，对SpringBoot, SpringMVC等 常用框架原理熟悉;</p><p><br></p><p>3.具有刻苦学习、专研精神，对常用的算法、数据结构有一定的了解，良好的代码风格;</p><p><br></p><p>4.对自己的工作成果认真负责，会写单元测试，论证自己的代码准确性，保证质量;</p><p><br></p><p>5.认真细致，踏实勤奋，具有较好的团队协作精神和沟通能力。</p><p><br></p><p>希望你:</p><p><br></p><p>1.岗位要求: 2022届全日制本科学历，电子、通信、网络工程、计算机类及相关专业优先。</p><p><br></p><p>2.工作地点:北京</p><p><br></p><p>我们有:</p><p><br></p><p>1.完善的员工培训机制:</p><p><br></p><p>A.业务培训+技能培训+管理培训，业内最优教学资源B.储备干部+轮岗定岗+管理人才培养计划</p><p><br></p><p>2.全员参与晋升，全方位发展:</p><p><br></p><p>A.管理方向:基层管理者→中层管理者→高层管理者B.专业方向:初级专业人才→中级专业人才→高级专业人才→资深专业人才→技术专家</p><p><br></p><p>这是一群充满青春活力的90后程序员，他们热衷逻辑;这是标准的互联网公司结构，但是不一样的氛围文化,没有喧嚣的口号，只有以人为本的精神;</p><p><br></p><p>自由是你能拥有的空间，自律是你所具备的条件，自驱是你所努力的方向;</p><p><br></p><p>鼓励学习、积极向上、创新未来、不负青春。</p>', '北京', '2021-11-10 00:49:07', '43');
INSERT INTO `t_postion` VALUES ('4', '92192269', '897535', '54642583', 'java工程师', '2000-5000', '硕士', '计算机专业', '<p>我们需要:</p><p><br></p><p>1.参与产品需求分析,研究产品技术细节;根据产品需求编写相应的技术文档;</p><p><br></p><p>2.熟悉Java基础，对SpringBoot, SpringMVC等 常用框架原理熟悉;</p><p><br></p><p>3.具有刻苦学习、专研精神，对常用的算法、数据结构有一定的了解，良好的代码风格;</p><p><br></p><p>4.对自己的工作成果认真负责，会写单元测试，论证自己的代码准确性，保证质量;</p><p><br></p><p>5.认真细致，踏实勤奋，具有较好的团队协作精神和沟通能力。</p><p><br></p><p>希望你:</p><p><br></p><p>1.岗位要求: 2022届全日制本科学历，电子、通信、网络工程、计算机类及相关专业优先。</p><p><br></p><p>2.工作地点:北京</p><p><br></p><p>我们有:</p><p><br></p><p>1.完善的员工培训机制:</p><p><br></p><p>A.业务培训+技能培训+管理培训，业内最优教学资源B.储备干部+轮岗定岗+管理人才培养计划</p><p><br></p><p>2.全员参与晋升，全方位发展:</p><p><br></p><p>A.管理方向:基层管理者→中层管理者→高层管理者B.专业方向:初级专业人才→中级专业人才→高级专业人才→资深专业人才→技术专家</p><p><br></p><p>这是一群充满青春活力的90后程序员，他们热衷逻辑;这是标准的互联网公司结构，但是不一样的氛围文化,没有喧嚣的口号，只有以人为本的精神;</p><p><br></p><p>自由是你能拥有的空间，自律是你所具备的条件，自驱是你所努力的方向;</p><p><br></p><p>鼓励学习、积极向上、创新未来、不负青春。</p>', '北京', '2021-11-10 20:28:53', '45');
INSERT INTO `t_postion` VALUES ('5', '84144196', '897535', '54642583', '前端工程师', '2000-5000', '本科', '不限', '<p>我们需要:</p><p><br></p><p>1.参与产品需求分析,研究产品技术细节;根据产品需求编写相应的技术文档;</p><p><br></p><p>2.熟悉Java基础，对SpringBoot, SpringMVC等 常用框架原理熟悉;</p><p><br></p><p>3.具有刻苦学习、专研精神，对常用的算法、数据结构有一定的了解，良好的代码风格;</p><p><br></p><p>4.对自己的工作成果认真负责，会写单元测试，论证自己的代码准确性，保证质量;</p><p><br></p><p>5.认真细致，踏实勤奋，具有较好的团队协作精神和沟通能力。</p><p><br></p><p>希望你:</p><p><br></p><p>1.岗位要求: 2022届全日制本科学历，电子、通信、网络工程、计算机类及相关专业优先。</p><p><br></p><p>2.工作地点:北京</p><p><br></p><p>我们有:</p><p><br></p><p>1.完善的员工培训机制:</p><p><br></p><p>A.业务培训+技能培训+管理培训，业内最优教学资源B.储备干部+轮岗定岗+管理人才培养计划</p><p><br></p><p>2.全员参与晋升，全方位发展:</p><p><br></p><p>A.管理方向:基层管理者→中层管理者→高层管理者B.专业方向:初级专业人才→中级专业人才→高级专业人才→资深专业人才→技术专家</p><p><br></p><p>这是一群充满青春活力的90后程序员，他们热衷逻辑;这是标准的互联网公司结构，但是不一样的氛围文化,没有喧嚣的口号，只有以人为本的精神;</p><p><br></p><p>自由是你能拥有的空间，自律是你所具备的条件，自驱是你所努力的方向;</p><p><br></p><p>鼓励学习、积极向上、创新未来、不负青春。</p>', '北京', '2021-11-10 00:58:32', '56');
INSERT INTO `t_postion` VALUES ('6', '92191158', '432345', '54642583', 'java工程师', '2000-5000', '本科', '不限', '<p>我们需要:</p><p><br></p><p>1.参与产品需求分析,研究产品技术细节;根据产品需求编写相应的技术文档;</p><p><br></p><p>2.熟悉Java基础，对SpringBoot, SpringMVC等 常用框架原理熟悉;</p><p><br></p><p>3.具有刻苦学习、专研精神，对常用的算法、数据结构有一定的了解，良好的代码风格;</p><p><br></p><p>4.对自己的工作成果认真负责，会写单元测试，论证自己的代码准确性，保证质量;</p><p><br></p><p>5.认真细致，踏实勤奋，具有较好的团队协作精神和沟通能力。</p><p><br></p><p>希望你:</p><p><br></p><p>1.岗位要求: 2022届全日制本科学历，电子、通信、网络工程、计算机类及相关专业优先。</p><p><br></p><p>2.工作地点:北京</p><p><br></p><p>我们有:</p><p><br></p><p>1.完善的员工培训机制:</p><p><br></p><p>A.业务培训+技能培训+管理培训，业内最优教学资源B.储备干部+轮岗定岗+管理人才培养计划</p><p><br></p><p>2.全员参与晋升，全方位发展:</p><p><br></p><p>A.管理方向:基层管理者→中层管理者→高层管理者B.专业方向:初级专业人才→中级专业人才→高级专业人才→资深专业人才→技术专家</p><p><br></p><p>这是一群充满青春活力的90后程序员，他们热衷逻辑;这是标准的互联网公司结构，但是不一样的氛围文化,没有喧嚣的口号，只有以人为本的精神;</p><p><br></p><p>自由是你能拥有的空间，自律是你所具备的条件，自驱是你所努力的方向;</p><p><br></p><p>鼓励学习、积极向上、创新未来、不负青春。</p>', '北京', '2021-11-10 00:59:44', '43');
INSERT INTO `t_postion` VALUES ('7', '61825448', '232323', '54642583', '全栈工程师', '8000-1500', '本科', '计算机专业', '<p>岗位职责</p><p>大疆创新的基础架构及运维部，致力于为大疆创新数字化转型打下坚实的基础。在这里，你将和DJI的各项业务一起，共同见证DJI为用户提供的优质服务，成为云原生应用运维，容器化，CI/CD，信息安全等领域的资深专家和大牛！</p><p><br></p><p>同时，你将有一份宽松的工作环境，永远不给自己设限。遵从自己内心最美好的东西，和团队一起去勇敢实现对未来的期待和雄心。</p><p><br></p><p>1. 负责公司运维监控系统的设计、实现；</p><p><br></p><p>2. 参与公司运维自动化工具的开发；</p><p><br></p><p>3. 参与部门运维流程的落地实现与集成优化；</p><p><br></p><p>4. 负责部门运营管理的平台信息化运维。</p><p>岗位要求</p><p>1. 本科及以上学历，计算机、通信、自动化等相关专业；</p><p><br></p><p>2. 精通 Linux 操作系统，熟悉 Nginx、Zabbix、ELK、Jenkins 等常用开源软件的配置、优化及高可用架构；</p><p><br></p><p>3. 熟悉Docker/K8S、云上系统运维、CI/CD流程；</p><p><br></p><p>4. 熟悉云计算相关技术，如虚拟化技术，软件定义存储等；</p><p><br></p><p>5. 熟练的脚本编写能力，能够熟练使用Python，Perl 等脚本语言；</p><p><br></p><p>6. 有流程意识，具有良好的工作习惯，注重细节，一丝不苟。</p>', '北京', '2021-11-10 10:05:20', '12');
INSERT INTO `t_postion` VALUES ('9', '75121438', '232323', '54642583', 'java后端开发工程师', '9000-19000', '本科', '计算机', '<p>nb</p>', '北京', '2021-11-10 10:59:14', '32');
INSERT INTO `t_postion` VALUES ('10', '75689748', '232323', '54642583', 'java后端开发工程师', '9000-19000', '本科', '计算机', '<p>哈哈哈哈nnnnnnnnnnnnnnnnnnnnnn</p>', '北京', '2021-11-10 10:59:39', '43');
INSERT INTO `t_postion` VALUES ('13', '73392326', '232323', '54642583', 'java工程师', '8000-1500', '本科', '计算机专业', '<p>我们需要:1111</p><p><br></p><p>1.参与产品需求分析,研究产品技术细节;根据产品需求编写相应的技术文档;</p><p><br></p><p>2.熟悉Java基础，对SpringBoot,&nbsp;SpringMVC等&nbsp;常用框架原理熟悉;</p><p><br></p><p>3.具有刻苦学习、专研精神，对常用的算法、数据结构有一定的了解，良好的代码风格;</p><p><br></p><p>4.对自己的工作成果认真负责，会写单元测试，论证自己的代码准确性，保证质量;</p><p><br></p><p>5.认真细致，踏实勤奋，具有较好的团队协作精神和沟通能力。</p><p><br></p><p>希望你:</p><p><br></p><p>1.岗位要求:&nbsp;2022届全日制本科学历，电子、通信、网络工程、计算机类及相关专业优先。</p><p><br></p><p>2.工作地点:北京</p><p><br></p><p>我们有:</p><p><br></p><p>1.完善的员工培训机制:</p><p><br></p><p>A.业务培训+技能培训+管理培训，业内最优教学资源B.储备干部+轮岗定岗+管理人才培养计划</p><p><br></p><p>2.全员参与晋升，全方位发展:</p><p><br></p><p>A.管理方向:基层管理者→中层管理者→高层管理者B.专业方向:初级专业人才→中级专业人才→高级专业人才→资深专业人才→技术专家</p><p><br></p><p>这是一群充满青春活力的90后程序员，他们热衷逻辑;这是标准的互联网公司结构，但是不一样的氛围文化,没有喧嚣的口号，只有以人为本的精神;</p><p><br></p><p>自由是你能拥有的空间，自律是你所具备的条件，自驱是你所努力的方向;</p><p><br></p><p>鼓励学习、积极向上、创新未来、不负青春。</p>', '北京', '2021-11-10 11:01:40', '32');
INSERT INTO `t_postion` VALUES ('14', '61972764', '232323', '54642583', 'java工程师', '8000-1500', '本科', '计算机专业', '<p>我们需要:</p><p>1111111111111111111111111111111111</p><p>1.参与产品需求分析,研究产品技术细节;根据产品需求编写相应的技术文档;</p><p><br></p><p>2.熟悉Java基础，对SpringBoot,&nbsp;SpringMVC等&nbsp;常用框架原理熟悉;</p><p><br></p><p>3.具有刻苦学习、专研精神，对常用的算法、数据结构有一定的了解，良好的代码风格;</p><p><br></p><p>4.对自己的工作成果认真负责，会写单元测试，论证自己的代码准确性，保证质量;</p><p><br></p><p>5.认真细致，踏实勤奋，具有较好的团队协作精神和沟通能力。</p><p><br></p><p>希望你:</p><p><br></p><p>1.岗位要求:&nbsp;2022届全日制本科学历，电子、通信、网络工程、计算机类及相关专业优先。</p><p><br></p><p>2.工作地点:北京</p><p><br></p><p>我们有:</p><p><br></p><p>1.完善的员工培训机制:</p><p><br></p><p>A.业务培训+技能培训+管理培训，业内最优教学资源B.储备干部+轮岗定岗+管理人才培养计划</p><p><br></p><p>2.全员参与晋升，全方位发展:</p><p><br></p><p>A.管理方向:基层管理者→中层管理者→高层管理者B.专业方向:初级专业人才→中级专业人才→高级专业人才→资深专业人才→技术专家</p><p><br></p><p>这是一群充满青春活力的90后程序员，他们热衷逻辑;这是标准的互联网公司结构，但是不一样的氛围文化,没有喧嚣的口号，只有以人为本的精神;</p><p><br></p><p>自由是你能拥有的空间，自律是你所具备的条件，自驱是你所努力的方向;</p><p><br></p><p>鼓励学习、积极向上、创新未来、不负青春。</p>', '北京', '2021-11-10 11:01:50', '44');
INSERT INTO `t_postion` VALUES ('15', '39694348', '232323', '54642583', '全栈工程师', '8000-1500', '本科', '计算机专业', '<p>岗位职责11111111111111</p><p>大疆创新的基础架构及运维部，致力于为大疆创新数字化转型打下坚实的基础。在这里，你将和DJI的各项业务一起，共同见证DJI为用户提供的优质服务，成为云原生应用运维，容器化，CI/CD，信息安全等领域的资深专家和大牛！</p><p><br></p><p>同时，你将有一份宽松的工作环境，永远不给自己设限。遵从自己内心最美好的东西，和团队一起去勇敢实现对未来的期待和雄心。</p><p><br></p><p>1.&nbsp;负责公司运维监控系统的设计、实现；</p><p><br></p><p>2.&nbsp;参与公司运维自动化工具的开发；</p><p><br></p><p>3.&nbsp;参与部门运维流程的落地实现与集成优化；</p><p><br></p><p>4.&nbsp;负责部门运营管理的平台信息化运维。</p><p>岗位要求</p><p>1.&nbsp;本科及以上学历，计算机、通信、自动化等相关专业；</p><p><br></p><p>2.&nbsp;精通&nbsp;Linux&nbsp;操作系统，熟悉&nbsp;Nginx、Zabbix、ELK、Jenkins&nbsp;等常用开源软件的配置、优化及高可用架构；</p><p><br></p><p>3.&nbsp;熟悉Docker/K8S、云上系统运维、CI/CD流程；</p><p><br></p><p>4.&nbsp;熟悉云计算相关技术，如虚拟化技术，软件定义存储等；</p><p><br></p><p>5.&nbsp;熟练的脚本编写能力，能够熟练使用Python，Perl&nbsp;等脚本语言；</p><p><br></p><p>6.&nbsp;有流程意识，具有良好的工作习惯，注重细节，一丝不苟。</p>', '北京', '2021-11-10 11:02:09', '66');
INSERT INTO `t_postion` VALUES ('16', '88196353', '454545', '54642583', 'java工程师', '2000-5000', '硕士', '不限', '<p>岗位职责</p><p>1、 参与独立产品线产品需求分析，进行需求分析和软件设计；</p><p>2、 根据设计文档完成代码编写，跟踪并推进产品和项目开发进度；</p><p>3、 参与公司现有技术平台产品的持续改进与优化产品，提高产品用户体验；</p><p>4、 编写软件开发相关技术文档；</p><p>5、 维护产品版本输出。</p><p>岗位要求</p><p>1、计算机、软件工程、网络安全、通信、人工智能、微电子、自动化等相关专业硕士，优秀的本科生可考虑；</p><p>2、掌握C/C++,python,go,java，javascript 任意一种或一种以上的开发语言，了解网络协议栈；</p><p>3、熟练掌握windows，linux操作；</p><p>4、熟练掌握研发代码配置Git，有过开源项目开发经验者优先；</p><p>5、有一定的团队合作经验，良好的合作精神和沟通能力。</p>', '上海', '2021-11-22 12:24:25', '11');

-- ----------------------------
-- Table structure for `t_professional_skill`
-- ----------------------------
DROP TABLE IF EXISTS `t_professional_skill`;
CREATE TABLE `t_professional_skill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `skill_name` varchar(30) DEFAULT NULL COMMENT '技能名称',
  `description` varchar(300) DEFAULT NULL COMMENT '技能描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_professional_skill
-- ----------------------------
INSERT INTO `t_professional_skill` VALUES ('2', '54642583', 'java', '<p>熟悉多线程</p><p>熟悉jvm</p>');

-- ----------------------------
-- Table structure for `t_project_experience`
-- ----------------------------
DROP TABLE IF EXISTS `t_project_experience`;
CREATE TABLE `t_project_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `project_name` varchar(30) DEFAULT NULL COMMENT '项目名称',
  `postion` varchar(30) DEFAULT NULL COMMENT '职位',
  `start_date` varchar(30) DEFAULT NULL COMMENT '开始时间',
  `end_date` varchar(30) DEFAULT NULL COMMENT '结束时间',
  `description` varchar(500) DEFAULT NULL COMMENT '项目描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_project_experience
-- ----------------------------
INSERT INTO `t_project_experience` VALUES ('3', '54642583', '校园招聘微信小程序', '全栈开发', '2019-01-01', '2021-03-01', '前后端所有功能开发，nb');

-- ----------------------------
-- Table structure for `t_trade`
-- ----------------------------
DROP TABLE IF EXISTS `t_trade`;
CREATE TABLE `t_trade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trade_name` varchar(20) DEFAULT '' COMMENT '行业名称',
  `logo` varchar(200) DEFAULT NULL COMMENT 'logo',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_trade
-- ----------------------------
INSERT INTO `t_trade` VALUES ('1', '互联网', 'http://r2apv4kp8.hn-bkt.clouddn.com/hulianwang.png');
INSERT INTO `t_trade` VALUES ('2', '建筑', 'http://r2apv4kp8.hn-bkt.clouddn.com/jianzhu.png');
INSERT INTO `t_trade` VALUES ('3', '金融银行', 'http://r2apv4kp8.hn-bkt.clouddn.com/yinhang.png');
INSERT INTO `t_trade` VALUES ('5', '生产管理', 'http://r2apv4kp8.hn-bkt.clouddn.com/shengchanguanli.png');
INSERT INTO `t_trade` VALUES ('6', '教育培训', 'http://r2apv4kp8.hn-bkt.clouddn.com/hiaoyu01.png');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `open_id` varchar(50) DEFAULT NULL COMMENT 'open_id',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名字',
  `birth` varchar(30) DEFAULT NULL COMMENT '出生日期',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(11) DEFAULT NULL COMMENT '电话',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `highest_degree` varchar(20) DEFAULT NULL COMMENT '最高学历',
  `school_name` varchar(50) DEFAULT NULL COMMENT '最高学历学校',
  `major` varchar(50) DEFAULT NULL,
  `graduation_year` varchar(10) DEFAULT NULL COMMENT '毕业年份',
  `type` int(10) DEFAULT '0' COMMENT '用户类型',
  `state` int(11) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '54642583', 'oN4825SNAwbukIW-cfrWn5vLerU8', 'https://thirdwx.qlogo.cn/mmopen/vi_32/PnLpvUTkAx0wDgN7U4rWbYkU61mr6BHThyBq9FQib2ddIKfqKeu2OrsSa91ldmpQ48P9PArMdGH6VuuwzEgZ1MQ/132', 'L', '2018-11-07', '0', '7719726722qq.com', '15970028629', '江西赣州', '本科', '江西农业大学', '软件工程', '2022', '1', '1');

-- ----------------------------
-- Table structure for `t_verify`
-- ----------------------------
DROP TABLE IF EXISTS `t_verify`;
CREATE TABLE `t_verify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '申请者id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '申请者名称',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id',
  `department` varchar(255) DEFAULT NULL COMMENT '30',
  `job_number` varchar(255) DEFAULT NULL COMMENT '工号',
  `apply_date` datetime DEFAULT NULL COMMENT '申请时间',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_verify
-- ----------------------------
INSERT INTO `t_verify` VALUES ('1', '54642583', '张三', '454545', '研发部', '10086', '2021-11-09 21:12:07', '1');
