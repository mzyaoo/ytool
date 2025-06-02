package com.mzyao.ytool.entity.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 系统菜单表
 *
 * @author mzyao
 * @since 2025-06-02 12:57:15
 */
@Data
@TableName("sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @NotBlank(message = "【主键ID】不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父菜单ID（顶级菜单为 0）
     */
    @NotBlank(message = "【父菜单ID（顶级菜单为 0）】不能为空")
    private Long parentId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "【菜单名称】不能为空")
    private String name;

    /**
     * 路由路径或链接地址
     */
    @NotBlank(message = "【路由路径或链接地址】不能为空")
    private String path;

    /**
     * 菜单图标（可选）
     */
    @NotBlank(message = "【菜单图标（可选）】不能为空")
    private String icon;

    /**
     * 排序（升序）
     */
    @NotBlank(message = "【排序（升序）】不能为空")
    private Integer sort;

    /**
     * 是否显示：0-隐藏，1-显示
     */
    @NotBlank(message = "【是否显示：0-隐藏，1-显示】不能为空")
    private Integer visible;

    /**
     * 前端组件路径（如有）
     */
    @NotBlank(message = "【前端组件路径（如有）】不能为空")
    private String component;

    /**
     * 备注
     */
    @NotBlank(message = "【备注】不能为空")
    private String remark;

    /**
     * 创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @NotBlank(message = "【更新时间】不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private List<SysMenu> children;

}