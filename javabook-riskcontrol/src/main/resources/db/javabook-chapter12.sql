DROP TABLE IF EXISTS rc_blacklist;
CREATE TABLE rc_blacklist (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  username varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  type varchar(16) NOT NULL DEFAULT '' COMMENT '类型：LOGIN_TIMES, ORDERS, ONLINE_DURATION, BUY_MONEY',
  details varchar(512) DEFAULT NULL COMMENT '详情',
  createtime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '黑名单表';

DROP TABLE IF EXISTS rc_user;
CREATE TABLE rc_user (
  id int(11) NOT NULL COMMENT '用户编码',
  username varchar(32) NOT NULL COMMENT '用户名',
  password varchar(32) NOT NULL COMMENT '密码',
  enabled tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用或禁用：0:禁用，1:启用',
  createtime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

INSERT INTO rc_user VALUES (1, 'lixingyun', '123456', 1, CURRENT_TIMESTAMP);

DROP TABLE IF EXISTS rc_order;
CREATE TABLE rc_order (
  id int(11) NOT NULL COMMENT '订单编码',
  username varchar(32) NOT NULL COMMENT '用户名',
  details varchar(32) NOT NULL COMMENT '订单详情',
  status tinyint(1) NOT NULL DEFAULT '0' COMMENT '订单状态：0:未支付，1:已支付',
  money int(11) NOT NULL DEFAULT '0' COMMENT '订单金额',
  createtime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

INSERT INTO rc_order VALUES (1, 'lixingyun', '', 0, 0, '2024-01-01 03:12:02');
INSERT INTO rc_order VALUES (2, 'lixingyun', '', 0, 0, '2024-01-01 03:47:29');
INSERT INTO rc_order VALUES (3, 'lixingyun', '', 0, 0, '2024-01-01 04:18:35');
