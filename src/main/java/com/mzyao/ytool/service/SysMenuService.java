package com.mzyao.ytool.service;

import com.mzyao.ytool.entity.pojo.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author mzyao
 * @since 2025-06-02 12:57:15
 */
public interface SysMenuService extends IService<SysMenu> {


    /**
     * 获取要展示的菜单数据
     *
     * @return
     */
    public List<SysMenu> getVisibleMenuTree();

}