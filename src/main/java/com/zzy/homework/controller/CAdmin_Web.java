package com.zzy.homework.controller;

import com.zzy.homework.entity.Admin;
import com.zzy.homework.entity.Borrow;
import com.zzy.homework.service.admin_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
public class CAdmin_Web {

    @Autowired
    private admin_service admin_svc;

    @RequestMapping("check_admin_login_web")
    public String checkAdminLoginWeb(HttpServletRequest request, Model model) throws ParseException {
        HttpSession session = request.getSession();
        String admin_id = (String) session.getAttribute("admin_id");
        String admin_pwd = (String) session.getAttribute("admin_pwd");
        Admin admin = null;
        admin = admin_svc.checkAdminLoginWeb(admin_id, admin_pwd);
        if (admin == null) {
            return "redirect:show_login";
        }
        session.setAttribute("admin_info", admin);
        List<Borrow> borrowList = admin_svc.getAllBorrowInfo();
        model.addAttribute("borrow_list", borrowList);
        return "pages/examples/admin-projects";
    }

    @RequestMapping("show_admin_main")
    private String showAdminMain(Model model) throws ParseException {
        List<Borrow> borrowList = admin_svc.getAllBorrowInfo();
        model.addAttribute("borrow_list", borrowList);
        return "pages/examples/admin-projects";
    }

    @RequestMapping("vbi")
    private String viewBorrowInfo(HttpServletRequest request, Model model) {
        String unique_id = request.getParameter("unique_id");
        Borrow borrow = admin_svc.viewStuInfo(unique_id);
        model.addAttribute("borrowObject", borrow);
        return "pages/examples/view_borrow_info";
    }

    @RequestMapping("see_mbi")
    private String seeModifyBorrowInfo(HttpServletRequest request, Model model) {
        String unique_id = request.getParameter("unique_id");
        Borrow borrow = admin_svc.viewStuInfo(unique_id);
        model.addAttribute("borrowObject", borrow);
        int id = borrow.getUnique_id();
        HttpSession session = request.getSession();
        session.setAttribute("unique_id", id);
        return "pages/examples/modify_borrow_info";
    }

    @RequestMapping("mbi")
    private String modifyBorrowInfo(HttpServletRequest request, Model model) throws ParseException {
        int num = Integer.parseInt(request.getParameter("allowed_day"));
        HttpSession session = request.getSession();
        int unique_id = (int) session.getAttribute("unique_id");
        System.out.println("unique_id = " + unique_id);
        System.out.println("num = " + num);
        int flag = admin_svc.mbi(unique_id, num);
        List<Borrow> borrowList = admin_svc.getAllBorrowInfo();
        model.addAttribute("borrow_list", borrowList);
        return "pages/examples/admin-projects";
    }

}
