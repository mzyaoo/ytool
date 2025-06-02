package com.mzyao.ytool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzyao.ytool.mapper.SysMenuMapper;
import com.mzyao.ytool.entity.pojo.SysMenu;
import com.mzyao.ytool.service.SysMenuService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author mzyao
 * @since 2025-06-02 12:57:15
 */
@Service
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> getVisibleMenuTree() {
        List<SysMenu> allMenus = sysMenuMapper.selectList(
                new QueryWrapper<SysMenu>()
                        .eq("visible", 1)
                        .orderByAsc("parent_id", "sort")
        );

        return buildTree(allMenus);
    }

    private List<SysMenu> buildTree(List<SysMenu> menuList) {
        Map<Long, SysMenu> map = menuList.stream()
                .collect(Collectors.toMap(SysMenu::getId, m -> m));
        List<SysMenu> roots = new ArrayList<>();

        for (SysMenu menu : menuList) {
            if (menu.getParentId() == 0) {
                roots.add(menu);
            } else {
                SysMenu parent = map.get(menu.getParentId());
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(menu);
                }
            }
        }

        return roots;
    }

}