package com.mzyao.ytool.controller;

import org.springframework.web.bind.annotation.*;
import com.mzyao.ytool.service.SysMenuService;

import javax.annotation.Resource;

/**
 * 系统菜单表
 *
 * @author mzyao
 * @since 2025-06-02 12:57:15
 */
@RestController
@RequestMapping("/menu/sysMenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

}