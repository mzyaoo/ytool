# 数据库初始化

-- 创建库
create database if not exists ytool;

-- 切换库
use ytool;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                            `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID（顶级菜单为 0）',
                            `name` varchar(50) NOT NULL COMMENT '菜单名称',
                            `path` varchar(200) DEFAULT NULL COMMENT '路由路径或链接地址',
                            `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标（可选）',
                            `sort` int DEFAULT '0' COMMENT '排序（升序）',
                            `visible` tinyint(1) DEFAULT '1' COMMENT '是否显示：0-隐藏，1-显示',
                            `component` varchar(100) DEFAULT NULL COMMENT '前端组件路径（如有）',
                            `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                            `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `icon`, `sort`, `visible`, `component`, `remark`, `create_time`, `update_time`) VALUES (1, 0, '代码生成', NULL, NULL, 2, 0, NULL, NULL, '2025-06-02 05:27:59', '2025-06-04 03:48:52');
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `icon`, `sort`, `visible`, `component`, `remark`, `create_time`, `update_time`) VALUES (2, 0, '用户管理', '/view/generate/db', NULL, 1, 0, NULL, NULL, '2025-06-02 05:27:59', '2025-06-04 03:48:52');
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `icon`, `sort`, `visible`, `component`, `remark`, `create_time`, `update_time`) VALUES (3, 0, 'DDL代码生成', '/view/generate/ddl', NULL, 2, 1, NULL, NULL, '2025-06-02 05:27:59', '2025-06-04 03:48:52');
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `icon`, `sort`, `visible`, `component`, `remark`, `create_time`, `update_time`) VALUES (5, 0, '日志管理', NULL, NULL, 2, 0, NULL, NULL, '2025-06-02 05:27:59', '2025-06-04 03:48:52');
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `icon`, `sort`, `visible`, `component`, `remark`, `create_time`, `update_time`) VALUES (6, 0, '快速开始', '/view/start', NULL, 1, 1, NULL, NULL, '2025-06-02 06:47:21', '2025-06-02 07:21:05');
COMMIT;
