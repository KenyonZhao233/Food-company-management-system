/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50718
Source Host           : cdb-9mi11dym.bj.tencentcdb.com:10229
Source Database       : FoodManage

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-12-21 15:57:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for advance
-- ----------------------------
DROP TABLE IF EXISTS `advance`;
CREATE TABLE `advance` (
  `order_id` varchar(40) NOT NULL DEFAULT '',
  `mn_ad` float(11,4) NOT NULL,
  `mn_re` float(11,4) NOT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customer_id` varchar(40) NOT NULL,
  `customer_tp` int(11) NOT NULL,
  `customer_name` varchar(40) NOT NULL,
  `customer_wg` int(11) DEFAULT '0',
  `customer_tele` varchar(40) NOT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `customer_ibfk_1` (`customer_tp`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`customer_tp`) REFERENCES `customer_type` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for customer_type
-- ----------------------------
DROP TABLE IF EXISTS `customer_type`;
CREATE TABLE `customer_type` (
  `type_id` int(11) NOT NULL COMMENT '客户类型',
  `type_name` varchar(40) NOT NULL COMMENT '客户类型名',
  `type_pay` float NOT NULL COMMENT '预付款最低比率',
  PRIMARY KEY (`type_id`),
  KEY `type_id` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for finance
-- ----------------------------
DROP TABLE IF EXISTS `finance`;
CREATE TABLE `finance` (
  `finance_id` varchar(40) NOT NULL COMMENT '财务编号',
  `finance_user` varchar(40) NOT NULL COMMENT '责任人',
  `finance_mn` float(11,0) NOT NULL COMMENT '金额',
  `finance_time` datetime(6) DEFAULT NULL COMMENT '时间',
  `finance_inf` varchar(40) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`finance_id`),
  KEY `finance_user` (`finance_user`),
  CONSTRAINT `finance_user` FOREIGN KEY (`finance_user`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_product
-- ----------------------------
DROP TABLE IF EXISTS `order_product`;
CREATE TABLE `order_product` (
  `order_id` varchar(40) NOT NULL,
  `product_name` varchar(40) NOT NULL,
  `product_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_name`),
  CONSTRAINT `order_product` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` varchar(40) NOT NULL COMMENT '订单编号',
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单时间',
  `order_zt` varchar(20) NOT NULL COMMENT '订单状态',
  `order_custom` varchar(40) NOT NULL COMMENT '客户编号',
  `order_type` varchar(20) NOT NULL COMMENT '付款类型',
  `order_fzr` varchar(40) NOT NULL COMMENT '生成订单工作人员',
  PRIMARY KEY (`order_id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` varchar(40) NOT NULL COMMENT '产品编号',
  `product_name` varchar(40) NOT NULL COMMENT '产品名',
  `product_p1` float NOT NULL COMMENT '普通零售商价格',
  `product_p2` float NOT NULL COMMENT 'VIP零售商价格',
  `product_p3` float NOT NULL COMMENT '普通批发商价格',
  `product_bzq` int(11) NOT NULL COMMENT '保质期',
  PRIMARY KEY (`product_id`),
  KEY `product_name` (`product_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_ck
-- ----------------------------
DROP TABLE IF EXISTS `product_ck`;
CREATE TABLE `product_ck` (
  `product_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  `product_id` varchar(40) NOT NULL COMMENT '产品编号',
  `product_rm` int(11) NOT NULL COMMENT '剩余数量',
  `product_in` varchar(40) NOT NULL COMMENT '所在仓库编号',
  PRIMARY KEY (`product_date`,`product_id`),
  KEY `product_in` (`product_in`),
  KEY `product_ck_ibfk_2` (`product_id`),
  CONSTRAINT `product_ck_ibfk_1` FOREIGN KEY (`product_in`) REFERENCES `storehouse` (`ck_id`),
  CONSTRAINT `product_ck_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_raw
-- ----------------------------
DROP TABLE IF EXISTS `product_raw`;
CREATE TABLE `product_raw` (
  `product_id` varchar(40) NOT NULL COMMENT '成品编号',
  `raw_id` varchar(40) NOT NULL COMMENT '原料编号',
  `raw_num` float(40,2) NOT NULL COMMENT '原料数目',
  PRIMARY KEY (`product_id`,`raw_id`),
  KEY `raw_id` (`raw_id`),
  CONSTRAINT `product_raw_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_raw_ibfk_2` FOREIGN KEY (`raw_id`) REFERENCES `raw` (`raw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_rec
-- ----------------------------
DROP TABLE IF EXISTS `product_rec`;
CREATE TABLE `product_rec` (
  `product_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `product_id` varchar(40) NOT NULL COMMENT '产品编号',
  `product_lx` varchar(11) NOT NULL COMMENT '操作类型',
  `product_num` int(11) NOT NULL COMMENT '数量',
  `product_fzrid` varchar(40) NOT NULL COMMENT '负责人工号',
  PRIMARY KEY (`product_date`,`product_id`),
  KEY `product_id` (`product_id`),
  KEY `product_fzrid` (`product_fzrid`),
  CONSTRAINT `product_rec_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `produce_id` varchar(40) NOT NULL COMMENT '生产编号',
  `produce_type` varchar(40) NOT NULL COMMENT '完成情况',
  `produce_wp` varchar(40) NOT NULL COMMENT '产品编号',
  `produce_num` int(11) NOT NULL COMMENT '数量',
  `produce_sdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '制定时间',
  `produce_edate` datetime DEFAULT NULL COMMENT '完成时间',
  `produce_zrr` varchar(40) NOT NULL COMMENT '责任人',
  `produce_fzr` varchar(40) DEFAULT NULL,
  `produce_ddl` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`produce_id`),
  KEY `project_ibfk_1` (`produce_wp`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`produce_wp`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project_rec
-- ----------------------------
DROP TABLE IF EXISTS `project_rec`;
CREATE TABLE `project_rec` (
  `project_id` varchar(40) NOT NULL,
  `project_zt` varchar(40) NOT NULL,
  `project_num` int(11) DEFAULT NULL,
  `project_zrr` varchar(40) NOT NULL,
  `project_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`project_id`,`project_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project_status
-- ----------------------------
DROP TABLE IF EXISTS `project_status`;
CREATE TABLE `project_status` (
  `project_id` varchar(40) NOT NULL,
  `now_num` int(11) NOT NULL DEFAULT '0',
  `aim_num` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`project_id`),
  CONSTRAINT `project_status_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`produce_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for raw
-- ----------------------------
DROP TABLE IF EXISTS `raw`;
CREATE TABLE `raw` (
  `raw_id` varchar(40) NOT NULL COMMENT '原材料编号',
  `raw_name` varchar(40) NOT NULL COMMENT '原材料名',
  `raw_bzq` int(11) NOT NULL COMMENT '保质期',
  `raw_pri` float NOT NULL COMMENT '单价',
  PRIMARY KEY (`raw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for raw_ck
-- ----------------------------
DROP TABLE IF EXISTS `raw_ck`;
CREATE TABLE `raw_ck` (
  `raw_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `raw_id` varchar(40) NOT NULL,
  `raw_rm` int(11) NOT NULL,
  `raw_in` varchar(40) NOT NULL,
  PRIMARY KEY (`raw_date`,`raw_id`),
  KEY `raw_id` (`raw_id`),
  KEY `raw_in` (`raw_in`),
  CONSTRAINT `raw_ck_ibfk_1` FOREIGN KEY (`raw_id`) REFERENCES `raw` (`raw_id`),
  CONSTRAINT `raw_ck_ibfk_2` FOREIGN KEY (`raw_in`) REFERENCES `storehouse` (`ck_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for raw_rec
-- ----------------------------
DROP TABLE IF EXISTS `raw_rec`;
CREATE TABLE `raw_rec` (
  `raw_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `raw_id` varchar(40) NOT NULL COMMENT '原材料编号',
  `raw_lx` varchar(11) NOT NULL COMMENT '操作类型',
  `raw_num` int(11) NOT NULL COMMENT '数量',
  `raw_fzrid` varchar(40) NOT NULL COMMENT '负责人工号',
  PRIMARY KEY (`raw_date`,`raw_id`),
  KEY `raw_id` (`raw_id`),
  KEY `raw_fzrid` (`raw_fzrid`),
  CONSTRAINT `raw_rec_ibfk_1` FOREIGN KEY (`raw_id`) REFERENCES `raw` (`raw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for refund
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund` (
  `return_id` varchar(40) NOT NULL COMMENT '退货编号',
  `order_money` float(11,2) NOT NULL DEFAULT '0.00' COMMENT '退货金额',
  `order_id` varchar(40) NOT NULL COMMENT '订单编号',
  `return_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '退货时间',
  `return_reason` varchar(255) DEFAULT NULL COMMENT '退货原因',
  `return_state` varchar(10) NOT NULL COMMENT '状态：待退款与已退款',
  PRIMARY KEY (`return_id`),
  KEY `refund_ibfk_1` (`order_id`),
  CONSTRAINT `refund_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for right_end
-- ----------------------------
DROP TABLE IF EXISTS `right_end`;
CREATE TABLE `right_end` (
  `staff_id` varchar(40) NOT NULL DEFAULT '',
  `right_call` tinyint(1) DEFAULT '1',
  `right_out` tinyint(1) DEFAULT '1',
  `right_in` tinyint(1) DEFAULT '1',
  `right_des` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`staff_id`),
  CONSTRAINT `staff_id_end` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for right_finance
-- ----------------------------
DROP TABLE IF EXISTS `right_finance`;
CREATE TABLE `right_finance` (
  `staff_id` varchar(40) NOT NULL,
  `right_receive` tinyint(1) DEFAULT '1',
  `right_refund` tinyint(1) DEFAULT '1',
  `right_in` tinyint(1) DEFAULT '1',
  `right_out` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`staff_id`),
  CONSTRAINT `rightf` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for right_plan
-- ----------------------------
DROP TABLE IF EXISTS `right_plan`;
CREATE TABLE `right_plan` (
  `staff_id` varchar(40) NOT NULL,
  `right_plans` tinyint(4) NOT NULL DEFAULT '1',
  `right_pros` tinyint(4) NOT NULL DEFAULT '1',
  `right_raws` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`staff_id`),
  CONSTRAINT `staffs` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for right_raw
-- ----------------------------
DROP TABLE IF EXISTS `right_raw`;
CREATE TABLE `right_raw` (
  `staff_id` varchar(40) NOT NULL,
  `right_in` tinyint(1) DEFAULT '1',
  `right_des` tinyint(1) DEFAULT '1',
  `right_out` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`staff_id`),
  CONSTRAINT `staff_id` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for right_sale
-- ----------------------------
DROP TABLE IF EXISTS `right_sale`;
CREATE TABLE `right_sale` (
  `staff_id` varchar(40) NOT NULL COMMENT '员工号',
  `right_register` tinyint(1) DEFAULT '1',
  `right_create` tinyint(1) DEFAULT '1',
  `right_cancel` tinyint(1) DEFAULT '1',
  `right_return` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`staff_id`),
  CONSTRAINT `right_sale_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `staff_id` varchar(40) NOT NULL COMMENT '工号',
  `staff_name` varchar(40) NOT NULL COMMENT '姓名',
  `staff_sex` varchar(40) NOT NULL COMMENT '性别',
  `staff_sfz` varchar(40) NOT NULL COMMENT '身份证号',
  `staff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职日期',
  `staff_pw` varchar(40) NOT NULL COMMENT '密码',
  PRIMARY KEY (`staff_id`),
  KEY `staff_id` (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for staff_job
-- ----------------------------
DROP TABLE IF EXISTS `staff_job`;
CREATE TABLE `staff_job` (
  `staff_id` varchar(40) NOT NULL,
  `staff_bm` varchar(40) NOT NULL,
  `staff_zw` varchar(40) NOT NULL,
  `staff_workshop` varchar(40) DEFAULT 'null',
  PRIMARY KEY (`staff_id`,`staff_bm`,`staff_zw`),
  CONSTRAINT `staff_job_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for storehouse
-- ----------------------------
DROP TABLE IF EXISTS `storehouse`;
CREATE TABLE `storehouse` (
  `ck_id` varchar(40) NOT NULL COMMENT '仓库编号',
  `ck_pos` varchar(255) NOT NULL COMMENT '仓库位置',
  PRIMARY KEY (`ck_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for unpaid
-- ----------------------------
DROP TABLE IF EXISTS `unpaid`;
CREATE TABLE `unpaid` (
  `order_id` varchar(40) NOT NULL COMMENT '订单编号',
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出单时间',
  `order_mn` float NOT NULL COMMENT '金额',
  `order_custom` varchar(40) NOT NULL COMMENT '客户编号',
  PRIMARY KEY (`order_id`),
  CONSTRAINT `unpaid_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for workshop
-- ----------------------------
DROP TABLE IF EXISTS `workshop`;
CREATE TABLE `workshop` (
  `cj_id` varchar(40) NOT NULL COMMENT '车间编号',
  `cj_fzr` varchar(40) NOT NULL COMMENT '车间负责人',
  `cj_num` int(11) NOT NULL COMMENT '员工数量',
  PRIMARY KEY (`cj_id`) USING BTREE,
  KEY `cj_fzr` (`cj_fzr`),
  CONSTRAINT `cj_fzr` FOREIGN KEY (`cj_fzr`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for pick
-- ----------------------------
DROP VIEW IF EXISTS `pick`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `pick` AS select `orders`.`order_id` AS `order_id`,`orders`.`order_type` AS `order_type`,`orders`.`order_custom` AS `order_custom`,`orders`.`order_date` AS `order_date` from `orders` where (`orders`.`order_zt` = '待收货') ;

-- ----------------------------
-- View structure for product_ck_all
-- ----------------------------
DROP VIEW IF EXISTS `product_ck_all`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `product_ck_all` AS select `product`.`product_id` AS `product_id`,sum(`product_ck`.`product_rm`) AS `sum` from (`product` left join `product_ck` on((`product`.`product_id` = `product_ck`.`product_id`))) group by `product`.`product_id` ;

-- ----------------------------
-- View structure for product_ck_now
-- ----------------------------
DROP VIEW IF EXISTS `product_ck_now`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `product_ck_now` AS select (`product_ck_all`.`sum` - `product_ck_order`.`sum`) AS `sum`,`product_ck_all`.`product_id` AS `product_id` from (`product_ck_all` join `product_ck_order`) where (`product_ck_all`.`product_id` = `product_ck_order`.`product_id`) ;

-- ----------------------------
-- View structure for product_ck_order
-- ----------------------------
DROP VIEW IF EXISTS `product_ck_order`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `product_ck_order` AS select `product`.`product_id` AS `product_id`,ifnull(0,sum(`product_ck_sum`.`product_num`)) AS `sum` from (`product` left join `product_ck_sum` on(`product`.`product_name`)) group by `product`.`product_id` ;

-- ----------------------------
-- View structure for product_ck_sum
-- ----------------------------
DROP VIEW IF EXISTS `product_ck_sum`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `product_ck_sum` AS select `order_product`.`product_name` AS `product_name`,`order_product`.`product_num` AS `product_num` from (`order_product` join `orders`) where ((`orders`.`order_id` = `order_product`.`order_id`) and (`orders`.`order_zt` = '待收货')) ;

-- ----------------------------
-- Procedure structure for Change_Cp
-- ----------------------------
DROP PROCEDURE IF EXISTS `Change_Cp`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Change_Cp`(IN `id` varchar(40),IN `proname` varchar(40),IN `p1` float,IN `p2` float,IN `p3` float,IN `bzq` int)
BEGIN
	UPDATE product
	SET product.product_name = proname,
			product.product_p1 = p1,
			product.product_p2 = p2,
			product.product_p3 = p3,
			product.product_bzq = bzq
	WHERE product_id = id;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Confirm_Plan
-- ----------------------------
DROP PROCEDURE IF EXISTS `Confirm_Plan`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Confirm_Plan`(IN `id` varchar(40))
BEGIN
	UPDATE project
	SET project.produce_type = '已完成'
	WHERE project.produce_id = id;

	DELETE FROM project_status
	WHERE project_status.project_id = id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Delete_Cp
-- ----------------------------
DROP PROCEDURE IF EXISTS `Delete_Cp`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Delete_Cp`(IN `id` varchar(40),OUT `flag` int)
BEGIN
	DECLARE kcs int DEFAULT NULL;
	DECLARE ids VARCHAR(40) DEFAULT NULL;
	
	SELECT SUM(product_ck.product_rm) INTO kcs
	FROM product_ck
	WHERE product_ck.product_id = id;
	
	SELECT produce_id INTO ids
	FROM product, project
	WHERE product.product_id = id AND product.product_id = project.produce_id AND produce_type IN ('待执行', '执行中', '审核中', '已完成');

	IF (kcs = 0 OR kcs IS NULL) AND ids IS NULL THEN
		DELETE FROM product
		WHERE product.product_id = id;
		SET flag = 1;
	ELSE 
		SET flag = 0;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Delete_OrderItem
-- ----------------------------
DROP PROCEDURE IF EXISTS `Delete_OrderItem`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Delete_OrderItem`(IN `id` varchar(40),IN `proname` varchar(40))
BEGIN
	DELETE FROM orders
	WHERE orders.order_id = id AND orders.order_product = proname;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Delete_Staff
-- ----------------------------
DROP PROCEDURE IF EXISTS `Delete_Staff`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Delete_Staff`(IN `staff_id` varchar(40),IN `staff_bm` varchar(40),IN `staff_zw` varchar(40))
BEGIN
	DELETE 
	FROM staff_job
	WHERE staff_job.staff_id = staff_id AND staff_job.staff_bm = staff_bm AND staff_job.staff_zw = staff_zw;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Do_Plan
-- ----------------------------
DROP PROCEDURE IF EXISTS `Do_Plan`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Do_Plan`(IN `id` varchar(40),IN `nows` int,IN `aim` int,IN `fzr` varchar(40))
BEGIN
	UPDATE project
	SET project.produce_type = '执行中'
	WHERE project.produce_id = id;

	INSERT INTO project_status(project_status.project_id, project_status.now_num, project_status.aim_num, project_status.state)
	VALUES(id, nows, aim, 0);

	INSERT INTO project_rec(project_rec.project_id, project_rec.project_zt, project_rec.project_num, project_rec.project_zrr)
	VALUES(id, '开始执行', 0, fzr);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for GLY_Search
-- ----------------------------
DROP PROCEDURE IF EXISTS `GLY_Search`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `GLY_Search`(IN `uid` varchar(40))
BEGIN
	IF uid = 'NULL' THEN
		select staff.staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date from staff, staff_job where staff.staff_id = staff_job.staff_id and staff_zw in ('管理员', '管理者') and staff_bm != '系统' group by staff.staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex,staff_date;
	ELSE
		select staff.staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex, staff_date from staff , staff_job where staff.staff_id = uid and staff.staff_id = staff_job.staff_id and staff_zw in ('管理员', '管理者') and staff_bm != '系统' group by staff.staff_id, staff_bm, staff_zw, staff_name, staff_sfz, staff_sex,staff_date;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Insert_Cp
-- ----------------------------
DROP PROCEDURE IF EXISTS `Insert_Cp`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Insert_Cp`(IN `id` varchar(40),IN `proname` varchar(40),IN `p1` float,IN `p2` float,IN `p3` float,IN `bzq` int)
BEGIN
	INSERT INTO product
	VALUES(id, proname, p1, p2, p3, bzq);

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Over_Plan
-- ----------------------------
DROP PROCEDURE IF EXISTS `Over_Plan`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Over_Plan`(IN `id` varchar(40), IN `fzr` varchar(40))
BEGIN
	UPDATE project
	SET project.produce_type = '待审核',
			project.produce_edate = CURRENT_TIME
	WHERE project.produce_id = id;

	UPDATE project_status
	SET project_status.state = 1
	WHERE project_id = id;

	INSERT INTO project_rec(project_rec.project_id, project_rec.project_zt, project_rec.project_num, project_rec.project_zrr)
	VALUES(id, '完成计划', 0, fzr);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Push_Plan
-- ----------------------------
DROP PROCEDURE IF EXISTS `Push_Plan`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Push_Plan`(IN `id` varchar(40),IN `num` int,IN `pnum` int,IN `fzr` varchar(40))
BEGIN
	UPDATE project_status
	SET project_status.now_num = num
	WHERE project_status.project_id = id;

	INSERT INTO project_rec(project_rec.project_id, project_rec.project_zt, project_rec.project_num, project_rec.project_zrr)
	VALUES(id, '交付', pnum, fzr);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Raw_Add
-- ----------------------------
DROP PROCEDURE IF EXISTS `Raw_Add`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Raw_Add`(IN `id` varchar(40),IN `rawname` varchar(40),IN `bzq` int,IN `pri` float)
BEGIN
	INSERT INTO raw(raw.raw_id, raw.raw_name, raw.raw_bzq, raw.raw_pri)
	VALUES (id, rawname, bzq, pri);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Resets
-- ----------------------------
DROP PROCEDURE IF EXISTS `Resets`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Resets`(IN `id` varchar(40))
BEGIN
	DECLARE sfz VARCHAR(40) DEFAULT NULL;

	SELECT SUBSTR(staff.staff_sfz FROM 13 FOR 6) INTO sfz
	FROM staff
	WHERE staff.staff_id = id;

	UPDATE staff
	SET staff_pw = MD5(sfz)
	WHERE staff_id = id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Search_CpId
-- ----------------------------
DROP PROCEDURE IF EXISTS `Search_CpId`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Search_CpId`(IN `id` varchar(40))
BEGIN
	IF id != 'null' THEN
		SELECT product.product_id, product.product_name, product_p1, product_p2, product_p3, product_bzq
		FROM product
		WHERE product.product_id = id
		GROUP BY product.product_id, product.product_name, product_p1, product_p2, product_p3
		ORDER BY product.product_id;
	ELSE
		SELECT product.product_id, product.product_name, product_p1, product_p2, product_p3, product_bzq
		FROM product
		GROUP BY product.product_id, product.product_name, product_p1, product_p2, product_p3
		ORDER BY product.product_id;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Search_CpName
-- ----------------------------
DROP PROCEDURE IF EXISTS `Search_CpName`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Search_CpName`(IN `cpname` varchar(40))
BEGIN
	IF cpname != 'null' THEN
		SELECT product.product_id, product.product_name, product_p1, product_p2, product_p3,  product_bzq
		FROM product
		WHERE LOCATE(cpname,product.product_name)>0 
		GROUP BY product.product_id, product.product_name, product_p1, product_p2, product_p3
		ORDER BY product.product_id;
	ELSE
		SELECT product.product_id, product.product_name, product_p1, product_p2, product_p3,  product_bzq
		FROM product
		GROUP BY product.product_id, product.product_name, product_p1, product_p2, product_p3
		ORDER BY product.product_id;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Search_CpRm
-- ----------------------------
DROP PROCEDURE IF EXISTS `Search_CpRm`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Search_CpRm`(IN `id` varchar(40),OUT `nums` int)
BEGIN
	DECLARE ids VARCHAR(40) DEFAULT NULL;
	DECLARE nn INT DEFAULT 0;
	
  SELECT product_id INTO ids
	FROM product_ck
	where product_ck.product_id = id
	GROUP BY product_ck.product_id;

	IF ids IS NULL THEN
		SET nums = 0;
	ELSE
		SELECT SUM(product_ck.product_rm) INTO nn
		FROM product_ck
		where product_ck.product_id = id;
		
		SET nums = nn;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Search_Plan_Date
-- ----------------------------
DROP PROCEDURE IF EXISTS `Search_Plan_Date`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Search_Plan_Date`(IN `id` varchar(40),IN `sdate` datetime,IN `edate` datetime,IN `zt` varchar(40))
BEGIN
	IF id = '全部' AND zt = '全部' THEN
		SELECT *
		FROM project
		WHERE produce_sdate BETWEEN sdate AND edate;
	ELSEIF zt = '全部' THEN
		SELECT *
		FROM project
		WHERE project.produce_id = id AND produce_sdate BETWEEN sdate AND edate;
	ELSEIF id = '全部' THEN
		SELECT *
		FROM project
		WHERE project.produce_type = zt AND produce_sdate BETWEEN sdate AND edate;
	ELSE
		SELECT *
		FROM project
		WHERE project.produce_id = id AND project.produce_type = zt AND produce_sdate BETWEEN sdate AND edate;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Search_Plan_IdZt
-- ----------------------------
DROP PROCEDURE IF EXISTS `Search_Plan_IdZt`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Search_Plan_IdZt`(IN `id` varchar(40),IN `zt` varchar(40))
BEGIN
	IF id = '全部' AND zt = '全部' THEN
		SELECT *
		FROM project;
	ELSEIF zt = '全部' THEN
		SELECT *
		FROM project
		WHERE project.produce_id = id;
	ELSEIF id = '全部' THEN
		SELECT *
		FROM project
		WHERE project.produce_type = zt;
	ELSE
		SELECT *
		FROM project
		WHERE project.produce_id = id AND project.produce_type = zt;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Search_PlanRaw
-- ----------------------------
DROP PROCEDURE IF EXISTS `Search_PlanRaw`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Search_PlanRaw`(IN `id` varchar(40),OUT `num` float)
BEGIN
	DECLARE ids VARCHAR(40) DEFAULT NULL;
	DECLARE nn FLOAT DEFAULT 0;

	SELECT product_raw.raw_id INTO ids
	FROM project, product_raw
	WHERE project.produce_type = '待执行' AND product_raw.raw_id = id LIMIT 1;

	IF ids IS NULL THEN
		SET num = 0;
	ELSE
		SELECT SUM(project.produce_num * product_raw.raw_num) INTO nn
		FROM project, product_raw
		WHERE project.produce_type = '待执行' AND product_raw.raw_id = id AND product_raw.product_id = project.produce_wp;
			
		SET num = nn;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Search_RawId
-- ----------------------------
DROP PROCEDURE IF EXISTS `Search_RawId`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Search_RawId`(IN `id` varchar(40))
BEGIN
	IF id != 'null' THEN
		SELECT raw.raw_id, raw.raw_name, raw.raw_pri, raw.raw_bzq
		FROM raw
		WHERE raw.raw_id = id
		GROUP BY raw.raw_id, raw.raw_name, raw.raw_pri, raw.raw_bzq
		ORDER BY raw.raw_id;
	ELSE
		SELECT raw.raw_id, raw.raw_name, raw.raw_pri, raw.raw_bzq
		FROM raw
		GROUP BY raw.raw_id, raw.raw_name, raw.raw_pri, raw.raw_bzq
		ORDER BY raw.raw_id;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Search_RawName
-- ----------------------------
DROP PROCEDURE IF EXISTS `Search_RawName`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Search_RawName`(IN `rawname` varchar(40))
BEGIN
	IF rawname != 'null' THEN
		SELECT raw.raw_id, raw.raw_name, raw.raw_pri, raw.raw_bzq
		FROM raw
		WHERE LOCATE(rawname,raw.raw_name)>0
		GROUP BY raw.raw_id, raw.raw_name, raw.raw_pri, raw.raw_bzq
		ORDER BY raw.raw_id;
	ELSE
		SELECT raw.raw_id, raw.raw_name, raw.raw_pri, raw.raw_bzq
		FROM raw
		GROUP BY raw.raw_id, raw.raw_name, raw.raw_pri, raw.raw_bzq
		ORDER BY raw.raw_id;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Search_RawRm
-- ----------------------------
DROP PROCEDURE IF EXISTS `Search_RawRm`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Search_RawRm`(IN `idd` varchar(40),OUT `nums` float)
BEGIN
	DECLARE ids VARCHAR(40) DEFAULT NULL;
	DECLARE nn FLOAT DEFAULT 0;
	
  SELECT raw_id INTO ids
	FROM raw_ck
	where raw_ck.raw_id = idd
	GROUP BY raw_ck.raw_id;

	IF ids IS NULL THEN
		SET nums = 0;
	ELSE
		SELECT SUM(raw_ck.raw_rm) INTO nn
		FROM raw_ck
		where raw_ck.raw_id = idd;
		
		SET nums = nn;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Search_XL
-- ----------------------------
DROP PROCEDURE IF EXISTS `Search_XL`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Search_XL`(IN `sdate` datetime,IN `edate` datetime)
BEGIN
	SELECT product.product_name, SUM(order_product.product_num) AS sums
	FROM product, orders, order_product
	where orders.order_id = order_product.order_id AND product.product_name = order_product.product_name AND orders.order_type != '已取消' AND orders.order_date BETWEEN sdate AND edate
	GROUP BY product.product_name
	ORDER BY sums DESC;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Staff_Add
-- ----------------------------
DROP PROCEDURE IF EXISTS `Staff_Add`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Staff_Add`(IN `uid` varchar(40),IN `bm` varchar(40),IN `zw` varchar(40),IN `staname` varchar(40),IN `sex` varchar(40),IN `sfz` varchar(40),IN `psw` varchar(40),OUT `flag` int)
BEGIN
	DECLARE UIDS VARCHAR(40) DEFAULT NULL;
	DECLARE REC VARCHAR(40) DEFAULT NULL;
	SELECT staff_id INTO UIDS
	FROM staff
	WHERE staff_id = uid;

	IF UIDS IS NULL THEN
		INSERT INTO staff(staff.staff_id, staff.staff_name, staff.staff_sex, staff.staff_sfz, staff.staff_pw)
		VALUES(uid, staname, sex, sfz, MD5(psw));
	END IF;

	SELECT staff_job.staff_id INTO REC
	FROM staff_job
	WHERE staff_job.staff_id = uid AND staff_job.staff_bm = bm AND staff_job.staff_zw = zw;
	
	IF REC IS NULL THEN
		INSERT INTO staff_job(staff_job.staff_id, staff_job.staff_bm, staff_job.staff_zw)
		VALUES(uid, bm, zw);
		SET flag = 1;
	ELSE
		SET flag = 0;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Staff_Update
-- ----------------------------
DROP PROCEDURE IF EXISTS `Staff_Update`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Staff_Update`(IN `preUid` varchar(40),IN `preBm` varchar(40),IN `preZw` varchar(40),IN `nowBm` varchar(40),IN `nowZw` varchar(40),IN `nowName` varchar(40),IN `nowSex` varchar(40),IN `nowSfz` varchar(40))
BEGIN
	DECLARE Uid VARCHAR(40) DEFAULT NULL;
	SELECT staff_job.staff_id INTO Uid
	FROM staff_job
	WHERE staff_job.staff_id = preUid AND staff_job.staff_bm = preBm AND staff_job.staff_zw = preZw;
	
	UPDATE staff
	SET	staff.staff_name = nowName,
			staff.staff_sex = nowSex,
			staff.staff_sfz = nowSfz
	WHERE
			staff.staff_id = preUid;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_insert_staff`;
DELIMITER ;;
CREATE TRIGGER `trigger_insert_staff` AFTER INSERT ON `staff_job` FOR EACH ROW BEGIN
	IF NEW.staff_zw = '业务人员' THEN
		IF NEW.staff_bm = '财务部' THEN
			INSERT right_finance(staff_id)
			VALUES (NEW.staff_id);
		ELSEIF NEW.staff_bm = '销售部' THEN
			INSERT right_sale(staff_id)
			VALUES (NEW.staff_id);
		ELSEIF NEW.staff_bm = '原料库' THEN
			INSERT right_raw(staff_id)
			VALUES (NEW.staff_id);
		ELSEIF NEW.staff_bm = '成品库' THEN
			INSERT right_end(staff_id)
			VALUES (NEW.staff_id);
		ELSEIF NEW.staff_bm = '生产计划部' THEN
			INSERT right_plan(staff_id)
			VALUES (NEW.staff_id);
		END IF;
	END IF;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_delete_staff`;
DELIMITER ;;
CREATE TRIGGER `trigger_delete_staff` AFTER DELETE ON `staff_job` FOR EACH ROW BEGIN

	IF OLD.staff_zw = '业务人员' THEN
		IF OLD.staff_bm = '财务部' THEN
			DELETE FROM right_finance
			WHERE right_finance.staff_id = OLD.staff_id;
		ELSEIF OLD.staff_bm = '销售部' THEN
			DELETE FROM right_sale
			WHERE right_sale.staff_id = OLD.staff_id;
		ELSEIF OLD.staff_bm = '成品库' THEN
			DELETE FROM right_end
			WHERE right_end.staff_id = OLD.staff_id;
		ELSEIF OLD.staff_bm = '原料库' THEN
			DELETE FROM right_raw
			WHERE right_raw.staff_id = OLD.staff_id;
		ELSEIF OLD.staff_bm = '生产计划部' THEN
			DELETE FROM right_plan
			WHERE right_plan.staff_id = OLD.staff_id;
		END IF;
	END IF;
END
;;
DELIMITER ;
