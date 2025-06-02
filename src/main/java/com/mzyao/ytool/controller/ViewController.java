package com.mzyao.ytool.controller;

import com.mzyao.ytool.entity.pojo.SysMenu;
import com.mzyao.ytool.service.SysMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视图控制器
 *
 * @author mzyao
 * @since 2025-06-02 12:57:15
 */
@Controller
@RequestMapping("view")
public class ViewController {

    @Resource
    private SysMenuService sysMenuService;


    @GetMapping("generate/db")
    public String generateCode() {
        return "/html/generate/dbconnect/index";
    }

    @GetMapping("generate/ddl")
    public String ddlHtml() {
        return "/html/generate/ddl/index";
    }

    @GetMapping("index")
    public String layout(Model model) {
        List<SysMenu> menuTree = sysMenuService.getVisibleMenuTree();
        model.addAttribute("menus", menuTree);
        return "/html/layout/index";
    }

    @GetMapping("404")
    public String notfound() {
        return "/html/404/index";
    }

    @GetMapping("error")
    public String error() {
        return "/html/error/index";
    }

    @GetMapping("start")
    public String start() {
        return "/html/start";
    }


}
